# Orchestrating Three Machines: Strix Halo + Mac Mini M4 + a 6GB GTX 1060 Laptop

<!-- TOC -->
* [Orchestrating Three Machines: Strix Halo + Mac Mini M4 + a 6GB GTX 1060 Laptop](#orchestrating-three-machines-strix-halo--mac-mini-m4--a-6gb-gtx-1060-laptop)
  * [The Three Machines at a Glance](#the-three-machines-at-a-glance)
  * [The Missing Piece: an MSI GS63 VR with a GTX 1060 6GB](#the-missing-piece-an-msi-gs63-vr-with-a-gtx-1060-6gb)
    * [Pin CUDA 12.x — CUDA 13 Dropped Pascal](#pin-cuda-12x--cuda-13-dropped-pascal)
    * [No Flash Attention on This Card](#no-flash-attention-on-this-card)
    * [Building and Running llama.cpp for Pascal](#building-and-running-llamacpp-for-pascal)
    * [Recommended Models for 6GB](#recommended-models-for-6gb)
    * [The More Realistic Role: Embeddings and Speech-to-Text](#the-more-realistic-role-embeddings-and-speech-to-text)
  * [Three Ways to Combine Them](#three-ways-to-combine-them)
    * [Option 1: Three Independent Lanes](#option-1-three-independent-lanes)
    * [Option 2: Asymmetric Task Routing via Named Providers](#option-2-asymmetric-task-routing-via-named-providers)
    * [Option 3: Model Sharding — Why Not Here](#option-3-model-sharding--why-not-here)
  * [Starting and Supervising the Fleet](#starting-and-supervising-the-fleet)
    * [Should This Be Ansible?](#should-this-be-ansible)
    * [systemd (Strix Halo and the GS63 — Both Linux)](#systemd-strix-halo-and-the-gs63--both-linux)
    * [launchd (Mac Mini)](#launchd-mac-mini)
    * [The Fleet Script](#the-fleet-script)
  * [Unifying Behind One Endpoint: LiteLLM](#unifying-behind-one-endpoint-litellm)
  * [Hermes Agent Across Three Machines](#hermes-agent-across-three-machines)
    * [Auxiliary Task Slots](#auxiliary-task-slots)
  * [DeepSeek Harness Across Three Machines](#deepseek-harness-across-three-machines)
    * [Model-Directed Delegation via Subagents](#model-directed-delegation-via-subagents)
  * [Remote Access: The LiteLLM Router on JoNAS](#remote-access-the-litellm-router-on-jonas)
    * [How It's Wired](#how-its-wired)
    * [Issuing and Revoking Keys](#issuing-and-revoking-keys)
    * [Using It From Claude Code](#using-it-from-claude-code)
    * [What to Tell a Friend](#what-to-tell-a-friend)
  * [A Ready-to-Run Starter Bundle](#a-ready-to-run-starter-bundle)
  * [Bring-Up Order](#bring-up-order)
  * [Troubleshooting and Gotchas](#troubleshooting-and-gotchas)
  * [Sources](#sources)
<!-- TOC -->

This document ties together the per-machine setup guides already in this repo — [STRIX-HALO.md](./STRIX-HALO.md) (128GB unified memory, ROCm) and [MAC-MINI-M4.md](./MAC-MINI-M4.md) (16GB unified memory, MLX) — with a third, smaller machine neither of those documents covers, and shows how to point the harnesses in [CLI.md](./CLI.md) (Hermes Agent, DeepSeek Harness) at all three at once. It leans on [PROVIDERS.md — LiteLLM](./PROVIDERS.md#litellm) for the router piece and [RUNTIMES.md](./RUNTIMES.md) for the underlying local runtimes (llama.cpp, MLX, Ollama). Nothing here was benchmarked on the author's actual three-machine fleet — see the disclaimer at the bottom.

## The Three Machines at a Glance

| Machine                            | Memory                                                                                                                             | GPU compute                                   | Best-fit role                                                                                                                                   | Runtime                    |
|------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------|
| Strix Halo (this repo's BosGameM5) | 128GB unified (112-124GB usable, see [STRIX-HALO.md — Sizing the GTT Aperture](./STRIX-HALO.md#sizing-the-gtt-aperture-for-128gb)) | Radeon 8060S, gfx1151, ROCm/Vulkan            | The heavy reasoning model — 27B-120B class, see [STRIX-HALO.md — Recommended Models](./STRIX-HALO.md#recommended-models-for-a-128gb-strix-halo) | llama.cpp (ROCm/HIP), vLLM |
| Mac Mini M4                        | 16GB unified (~12GB to GPU by default, see [MAC-MINI-M4.md — Sizing for 16GB](./MAC-MINI-M4.md#sizing-for-16gb))                   | Apple GPU, Metal via MLX                      | The fast small model — 7-9B class, see [MAC-MINI-M4.md — Recommended Models](./MAC-MINI-M4.md#recommended-models-for-a-16gb-mac-mini)           | MLX, Ollama, LM Studio     |
| MSI GS63 VR laptop                 | 6GB dedicated VRAM (GTX 1060) + system RAM                                                                                         | GTX 1060, Pascal, CUDA compute capability 6.1 | The weakest link for reasoning; genuinely useful for embeddings, STT, or a small 7-8B model with partial offload — see below                    | llama.cpp (CUDA)           |

This is the same "match workload to machine" idea [STRIX-HALO.md](./STRIX-HALO.md#combining-with-a-second-machine-eg-a-mac-mini) already lays out for the Strix Halo + Mac Mini pair, extended to a third, much weaker box. The GTX 1060's 6GB is not a rounding error the way the Mac Mini's 16GB is next to Strix Halo's 128GB — it's a genuinely different, more constrained profile (Pascal, no tensor cores, dedicated-not-unified VRAM), so it gets its own section below rather than just a row in someone else's table.

## The Missing Piece: an MSI GS63 VR with a GTX 1060 6GB

This repo doesn't have a dedicated per-machine file for this laptop the way it does for Strix Halo and the Mac Mini — the setup is small enough to cover inline here.

### Pin CUDA 12.x — CUDA 13 Dropped Pascal

**CUDA Toolkit 13.0 removed offline-compilation and library support for Maxwell, Pascal, and Volta** (everything below Turing/compute capability 7.5). The GTX 1060 is Pascal, compute capability **6.1 (`sm_61`)** — squarely in the dropped range. You can still *run* binaries built by an older toolkit under a newer driver, but to **build** llama.cpp (or anything else CUDA-based) targeting this card, install **CUDA Toolkit 12.x**, not 13.x. Treat this the same way [STRIX-HALO.md](./STRIX-HALO.md#troubleshooting-and-gotchas) treats pinning a ROCm version for gfx1151 — check compatibility before blanket-upgrading.

### No Flash Attention on This Card

Flash Attention in llama.cpp's CUDA backend requires compute capability **≥ 7.5**. At 6.1, the GTX 1060 doesn't qualify — don't pass `--flash-attn on` expecting a speedup here; it either falls back or isn't available, unlike the Strix Halo `--cache-type-v q8_0` + `--flash-attn on` pairing in [STRIX-HALO.md's troubleshooting section](./STRIX-HALO.md#troubleshooting-and-gotchas). The card also has no tensor cores, so FP16/INT8 math runs on ordinary CUDA cores — slower than any RTX-class card at the same quant — and its ~192GB/s memory bandwidth is the real ceiling on decode speed, not compute.

### Building and Running llama.cpp for Pascal

Same source as [RUNTIMES.md — llama.cpp Installation](./RUNTIMES.md#llamacpp-installation), with the architecture pinned explicitly so CMake doesn't try to also target archs your toolkit can't build for:

```bash
git clone https://github.com/ggml-org/llama.cpp && cd llama.cpp
cmake -B build -DGGML_CUDA=ON -DCMAKE_CUDA_ARCHITECTURES=61 -DCMAKE_BUILD_TYPE=Release
cmake --build build -j --target llama-server
```

Run it bound to the network the same way the other two machines are (see [STRIX-HALO.md — Option 1](./STRIX-HALO.md#option-1-more-agents-no-extra-tooling-recommended-starting-point)):

```bash
./build/bin/llama-server -m /path/to/model.gguf -ngl 999 --host 0.0.0.0 --port 8080
```

If a model doesn't fully fit in 6GB, lower `-ngl` (number of layers offloaded to GPU) below the model's total layer count instead of `999` — the rest runs on CPU, slower but still functional, rather than failing to load.

### Recommended Models for 6GB

| Model                                            | Quant                  | Notes                                                                                                                                                                                                                                                |
|--------------------------------------------------|------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| A 7-8B general model (Qwen2.5-7B/Qwen3-8B class) | Q4_K_M                 | The realistic ceiling for full GPU offload at 6GB with a modest context window; matches the "fastest option" row already given for the Mac Mini in [MAC-MINI-M4.md](./MAC-MINI-M4.md#recommended-models-for-a-16gb-mac-mini), just on a slower card. |
| A 13-14B model                                   | Q3_K / partial offload | Fits only with some layers pushed to CPU (`-ngl` below full layer count) — noticeably slower; only worth it if you specifically need that model's capability and can tolerate the speed.                                                             |

Don't expect this machine to carry serious agentic reasoning load — the same caution [STRIX-HALO.md](./STRIX-HALO.md#combining-with-a-second-machine-eg-a-mac-mini) gives about not pooling a 16GB Mac's memory into a 128GB box applies even harder here.

### The More Realistic Role: Embeddings and Speech-to-Text

Given the constraints above, this laptop's most useful job in a three-machine fleet usually isn't chat completion at all:

- **An embedding model** for RAG pipelines — small (100M-600M parameter class), fits trivially in 6GB, and frees the other two machines from ever loading an embedding model alongside their main one. Serve it via llama.cpp's `--embedding` flag on `llama-server`, or Ollama's embedding models. For higher retrieval quality, Perplexity's `perplexity-ai/pplx-embed-v2-late-0.6b` (340M active, MIT) fits too, but it is a multi-vector (ColBERT-style) model that needs `sentence-transformers` rather than an embedding endpoint; its 9B sibling belongs on the Strix Halo. See [STRIX-HALO.md — Perplexity's Open Models](./STRIX-HALO.md#perplexitys-open-models).
- **Whisper for speech-to-text** — [whisper.cpp](https://github.com/ggml-org/whisper.cpp) (same project family as llama.cpp) runs comfortably on this card with CUDA offload, giving your fleet a dedicated transcription endpoint without tying up either of the other two machines' GPU memory.

Both are legitimate, low-effort ways to make this card pull its weight instead of sitting idle because it can't compete on raw model size.

## Three Ways to Combine Them

These mirror the three options [STRIX-HALO.md](./STRIX-HALO.md#combining-with-a-second-machine-eg-a-mac-mini) already lays out for a two-machine pairing, extended to three.

### Option 1: Three Independent Lanes

The same "no extra tooling" starting point as [STRIX-HALO.md — Option 1](./STRIX-HALO.md#option-1-more-agents-no-extra-tooling-recommended-starting-point): each machine runs its own OpenAI-compatible server (`llama-server`, `mlx_lm.server`, Ollama — whichever fits), each bound to `0.0.0.0` on its own port, and you point three separate harness sessions at three separate endpoints. Zero shared infrastructure, nothing to keep in sync, and it's the fastest way to turn "three machines" into "three agents running concurrently on three different tasks."

### Option 2: Asymmetric Task Routing via Named Providers

Extending [STRIX-HALO.md — Option 2](./STRIX-HALO.md#option-2-asymmetric-task-routing-small-model-on-the-mac-big-model-on-strix-halo) to a third provider — every harness in [CLI.md](./CLI.md) that supports multiple named custom providers can register all three machines at once:

```yaml
# Oh My Pi example (~/.omp/agent/models.yml) — see CLI.md's Oh My Pi CLI section
providers:
  strix-heavy:
    baseUrl: http://<strix-halo-ip>:8080/v1
    api: openai-completions
    apiKey: dummy
    models:
      - id: qwen3.8-27b

  mac-fast:
    baseUrl: http://<mac-mini-ip>:1234/v1
    api: openai-completions
    apiKey: dummy
    models:
      - id: qwen3.8-9b-distill

  gs63-embed:
    baseUrl: http://<gs63-ip>:8080/v1
    api: openai-completions
    apiKey: dummy
    models:
      - id: qwen2.5-7b-instruct
```

Which task goes where is then a manual `/model` switch or a per-task config choice — see the [Hermes Agent](#hermes-agent-across-three-machines) and [DeepSeek Harness](#deepseek-harness-across-three-machines) sections below for what each harness actually offers beyond that.

### Option 3: Model Sharding — Why Not Here

[STRIX-HALO.md — Option 3](./STRIX-HALO.md#option-3-model-sharding-across-both-machines-llamacpp-rpc) covers llama.cpp's `rpc-server` for pooling a Strix Halo + Mac Mini pair's memory into one model. Adding the GS63 as a third RPC worker is possible in principle (it's just another `--rpc host:port` on the `llama-server` command line) but is a worse idea here than it already is for the two-machine case:

- The two-machine version already trades "can't run this at all" for "can run this, slowly," bottlenecked at the slowest link. A third, weaker, differently-backed node (CUDA, versus ROCm and Metal on the other two) adds another network hop and another mismatched backend to keep in version lockstep, for 6GB of contribution.
- `rpc-server` has no authentication (same caveat as in STRIX-HALO.md) — three machines on the same trusted network is a larger attack surface than two.

Reach for this only if you have one specific model that genuinely doesn't fit even on Strix Halo alone — for everyday use, [Option 1](#option-1-three-independent-lanes) or [Option 2](#option-2-asymmetric-task-routing-via-named-providers) will serve better.

## Starting and Supervising the Fleet

The [`orchestration/`](./orchestration/) bundle's raw `llama-server`/`mlx_lm.server` commands are fine for a one-off test in a foreground terminal or tmux session, but they don't answer "how do these come back after a reboot or a crash" — and per [STRIX-HALO.md's own warning](./STRIX-HALO.md#troubleshooting-and-gotchas), a backgrounded shell you then close is actively the wrong way to try (it orphans the process still holding the GPU, the port, and the model in memory). The right fix isn't a better way to launch it over SSH — it's not launching it over SSH at all: each machine should supervise its own server as a native background service, the same way [MAC-MINI-M4.md](./MAC-MINI-M4.md#making-it-persistent) already uses a `launchd` LaunchDaemon to persist the `iogpu.wired_limit_mb` setting.

### Should This Be Ansible?

Not at three machines. Ansible's real value is **idempotent config management** — "ensure this service file exists, is enabled, and is running" (rerun-safe), plus one inventory targeting heterogeneous hosts — and that pays for itself once you're also pushing config changes to all three regularly (an updated `litellm_config.yaml`, a new model path, a kernel-parameter change). Just to *start* three servers, that's overhead a one-time service file per machine plus a short SSH fan-out script for restart/status already covers, with no new dependency. Reach for Ansible later if the fleet grows past three or you find yourself editing all three machines' configs often — not for this step.

### systemd (Strix Halo and the GS63 — Both Linux)

Both machines being Linux means both get the same unit shape — [`orchestration/systemd/llama-server.service`](./orchestration/systemd/llama-server.service) is one template, copied to `/etc/systemd/system/llama-server.service` on each with the marked `ExecStart` block swapped:

- **Strix Halo** needs `Environment="ROCM_HOME=..."` and `Environment="HSA_OVERRIDE_GFX_VERSION=11.5.1"` in the unit — per [STRIX-HALO.md — Building llama.cpp for gfx1151](./STRIX-HALO.md#building-llamacpp-for-gfx1151), these have to be set in *every* shell that runs `llama-server`, systemd's included, or ROCm won't recognize the gfx1151 device.
- **GS63** needs neither ROCm variable, and must not pass `--flash-attn on` — see [No Flash Attention on This Card](#no-flash-attention-on-this-card).

These are **system** units (not `--user`), so they start at boot with no login session and no `loginctl enable-linger` dance — manage them with `sudo systemctl enable --now llama-server`, `sudo systemctl restart llama-server`, etc.

### launchd (Mac Mini)

[`orchestration/launchd/com.local.mlx-server.plist`](./orchestration/launchd/com.local.mlx-server.plist) is the same idea for `mlx_lm.server`, installed as a LaunchDaemon:

```bash
sudo cp com.local.mlx-server.plist /Library/LaunchDaemons/
sudo launchctl bootstrap system /Library/LaunchDaemons/com.local.mlx-server.plist
```

One gotcha this plist calls out inline: a LaunchDaemon runs as **root with a minimal `PATH`**, so a bare `mlx_lm.server` in `ProgramArguments` won't resolve the way it does in your interactive shell — use the full path from `which mlx_lm.server` (Homebrew's default is `/opt/homebrew/bin`; a venv install lives under that venv's `bin/` instead). Restart after a config change with `sudo launchctl kickstart -k system/com.local.mlx-server`; fully stop it with `sudo launchctl bootout system/com.local.mlx-server` — note that unloads the job (including `KeepAlive` supervision) entirely, so bringing it back needs `bootstrap` again, not `kickstart`.

### The Fleet Script

[`orchestration/fleet.sh`](./orchestration/fleet.sh) is the thin remote-control layer on top of the above — `./fleet.sh {start|stop|restart|status}` fans an SSH command out to all three hosts. It assumes the service files above are already installed; it's not a substitute for them, and it needs passwordless `sudo` scoped to these specific commands on each host (a narrow `/etc/sudoers.d` entry, not blanket `NOPASSWD`) or you'll get an interactive password prompt per host, per run.

**Worth watching, with a tempered expectation now:** [Magnitude](./RUNTIMES.md#magnitude-installation) bundles most of this section into one tool — hardware profiling, model download/selection, and its own service supervision (`magnitude service install/start`) in place of the systemd/launchd files above. It's not a drop-in replacement, though, and not just because of unconfirmed unknowns anymore: tested directly on the GS63, its catalog had **zero models compatible with a GTX 1060 6GB** card, and it didn't recognize already-downloaded community GGUFs bridged correctly into its own cache — see [RUNTIMES.md — Confirmed on Real Hardware](./RUNTIMES.md#confirmed-on-real-hardware-discovery-doesnt-adopt-arbitrary-local-ggufs). Whether it fares better on the Mac Mini or Strix Halo (bigger, more mainstream hardware its catalog may actually target) is still open — see [RUNTIMES.md's remaining unconfirmed points](./RUNTIMES.md#whats-unconfirmed) — but on this fleet's weakest machine specifically, it currently has nothing to offer over the setup above.

## Unifying Behind One Endpoint: LiteLLM

[PROVIDERS.md — LiteLLM](./PROVIDERS.md#litellm) already covers installation and the general config shape for cloud backends (Anthropic, Bedrock). The same proxy works identically for local OpenAI-compatible servers — just point `litellm_params.api_base` at each machine instead of a cloud endpoint:

```yaml
# ~/litellm-fleet.yaml
model_list:
  - model_name: strix-heavy
    litellm_params:
      model: openai/qwen3.8-27b
      api_base: http://<strix-halo-ip>:8080/v1
      api_key: dummy

  - model_name: mac-fast
    litellm_params:
      model: openai/qwen3.8-9b-distill
      api_base: http://<mac-mini-ip>:1234/v1
      api_key: dummy

  - model_name: gs63-embed
    litellm_params:
      model: openai/qwen2.5-7b-instruct
      api_base: http://<gs63-ip>:8080/v1
      api_key: dummy
```

```bash
litellm --config ~/litellm-fleet.yaml --port 4000
```

Every harness now points at one URL (`http://localhost:4000/v1`) and picks a machine by `model_name`, per [PROVIDERS.md — API Endpoint (LiteLLM)](./PROVIDERS.md#api-endpoint-litellm). This is also the piece that lets **Claude Code** — which speaks the Anthropic `/v1/messages` shape, not OpenAI's — reach any of these three local models: the same translation pattern already documented for [Claude with Mammouth AI](./CLI.md#using-claude-with-mammouth-ai) applies unchanged, just with `api_base` pointed at one of the three machines above instead of a cloud gateway. (If a given machine runs [Magnitude](./RUNTIMES.md#magnitude-installation) instead, this translation step isn't needed at all *for a harness running on that same machine* — Magnitude exposes an Anthropic-compatible route directly. It doesn't help Claude Code reach Magnitude on a *different* machine, though, since Magnitude's LAN-binding story is unconfirmed — see the caveat above.)

LiteLLM itself can also do the availability-based part of "routing" — fallback chains and load balancing across the `model_list` entries — but that's routing by whether an endpoint is up, not by what a given task actually needs. For task-aware routing, see what Hermes Agent and DeepSeek Harness each actually offer below.

## Hermes Agent Across Three Machines

[CLI.md — Configuring Hermes Agent](./CLI.md#configuring-hermes-agent) already documents the single-provider custom-endpoint pattern. Registering all three machines follows the same shape, one entry per machine, in `~/.hermes/config.yaml`:

```yaml
providers:
  custom:
    strix-heavy:
      api: http://<strix-halo-ip>:8080/v1
      transport: chat_completions
      default_model: qwen3.8-27b
    mac-fast:
      api: http://<mac-mini-ip>:1234/v1
      transport: chat_completions
      default_model: qwen3.8-9b-distill
    gs63-embed:
      api: http://<gs63-ip>:8080/v1
      transport: chat_completions
      default_model: qwen2.5-7b-instruct
```

Switch between them mid-session with `/model custom:strix-heavy:qwen3.8-27b` etc., exactly as [CLI.md](./CLI.md#configuring-hermes-agent) describes. Hermes auto-detects loopback/private addresses and relaxes streaming timeouts for them — see [CLI.md's `HERMES_STREAM_READ_TIMEOUT` note](./CLI.md#configuring-hermes-agent) if the GS63 in particular is slow enough to need it raised further.

### Auxiliary Task Slots

Beyond the main chat model, Hermes' [configuring-models documentation](https://github.com/NousResearch/hermes-agent/blob/main/website/docs/user-guide/configuring-models.md) describes a set of **auxiliary task slots** — separate model assignments for specific built-in jobs (title generation, vision, context compression, and others) — each addressed as `auxiliary.<task>.provider` / `auxiliary.<task>.model`, defaulting to `auto` (meaning "use the main chat model for this too"):

```yaml
auxiliary:
  title_generation:
    provider: auto      # falls back to the main model
  vision:
    provider: custom
    model: mac-fast:qwen3.8-9b-distill   # only if it's a vision-capable build (this one isn't)
```

This is a real, static, config-time assignment — it lets you point cheap bookkeeping tasks at the Mac Mini or the GS63 so Strix Halo's throughput isn't spent titling sessions, matching the "small model for cheap tasks, big model for real reasoning" split this repo already recommends elsewhere. **It is not automatic task-aware routing** — genuine "the harness decides which of your three machines suits this specific request" is an open feature request upstream ([NousResearch/hermes-agent#32704](https://github.com/NousResearch/hermes-agent/issues/32704)) rather than a shipped capability. Given how actively this project is developing, verify the exact task names and keys against the current version of that doc file before relying on them — the list above is illustrative, not exhaustive.

## DeepSeek Harness Across Three Machines

[CLI.md — Configuring DeepSeek Harness](./CLI.md#configuring-deepseek-harness) documents adding one custom provider per endpoint via **Settings → Models**, or directly in `$DSH_HOME/settings.yaml`. Three machines is three such providers, same shape as the [LM Studio](./CLI.md#using-deepseek-harness-with-local-llms-via-lm-studio) and [Ollama](./CLI.md#using-deepseek-harness-with-local-llms-via-ollama) examples already there:

```yaml
llm-pi-ai:
  providers:
    strix-heavy:
      apiKeyEnv: STRIX_API_KEY
      api: openai-completions
      baseURL: http://<strix-halo-ip>:8080/v1
      models:
        - id: qwen3.8-27b
    mac-fast:
      apiKeyEnv: MAC_API_KEY
      api: openai-completions
      baseURL: http://<mac-mini-ip>:1234/v1
      models:
        - id: qwen3.8-9b-distill
    gs63-embed:
      apiKeyEnv: GS63_API_KEY
      api: openai-completions
      baseURL: http://<gs63-ip>:8080/v1
      models:
        - id: qwen2.5-7b-instruct
```

Export dummy values for each `apiKeyEnv` variable, same as the LM Studio/Ollama examples in CLI.md — the referenced env var must exist even though these local servers don't check it, or `dsh` refuses the request with `MISSING_CREDENTIAL`.

### Model-Directed Delegation via Subagents

This is the piece that goes beyond a static per-task assignment. DeepSeek Harness's plugin architecture treats **subagents** as a capability seam with multiple coexisting provider implementations — a locally spawned child agent (`"spawn"`), a forked child sharing history (`"fork"`), or a delegated turn in another product (`"claude-code"`, `"codex"`) — registered by name via `ctx.subagents.registerProvider(...)` ([deepseek-harness/docs/architecture.md](https://github.com/deepseek-ai/deepseek-harness/blob/master/docs/architecture.md), [docs/subsystems/subagent.md](https://github.com/deepseek-ai/deepseek-harness/blob/master/docs/subsystems/subagent.md)).

The `"spawn"` provider's start request accepts optional **provider/model overrides** (`SubagentStartRequest.agentOptions`, gated behind `SubagentCapabilities.agentOptions`) — meaning the *orchestrating model*, in the normal course of calling the subagent tool, can hand a subtask to a child agent running against a **different provider/model than its own**. Concretely, this is the mechanism that would let a Strix Halo-driven main agent delegate a quick lookup to the `mac-fast` or `gs63-embed` provider above, as a tool call it decides to make — genuine inference-time routing, not a fixed slot.

Two honest caveats before building on this: it's a **TypeScript API contract** documented at the architecture level, not (as of this writing) a simple `settings.yaml` toggle with a worked example — expect to read `docs/subsystems/subagent.md` at whatever version you install and possibly write a small plugin to wire it up. And `dsh` is still a **developer preview** (per [CLI.md](./CLI.md#deepseek-harness-cli)), so treat this section as a pointer to the right mechanism, not a copy-pasteable recipe — verify the exact config surface against the version you install before depending on it.

## Remote Access: The LiteLLM Router on JoNAS

To use the fleet from outside the house — yourself, or a friend — the router lives on **JoNAS**, the always-on Raspberry Pi 4 NAS (its own repo, `../JoNAS`, stack `stacks/litellm/`), not on any of the GPU boxes. JoNAS already runs the pieces a public endpoint needs: **Caddy** with Let's Encrypt TLS, **ddns-updater** keeping `pornbach.ddns.net` / `pornbach.myddns.me` on the current public IP, and the FRITZ!Box port forwards. Being always on, it also answers with a clean error when a GPU box is asleep, instead of a connection timeout.

Remote users get **an API key, never a machine account**: no SSH, no shell, nothing but inference routes.

```
client ──HTTPS :8443──► FRITZ!Box ──► JoNAS :443  Caddy  /litellm/* ──► litellm:4000 (+ Postgres)
                                                                          │ per-person key, model
                                                                          │ allowlist, rate limits
                                                                          ▼
                                                    BosGameM5 LM Studio :1234   (ufw: JoNAS only)
                                                    Mac Mini LM Studio :1234     (qwen3.8-9b-distill)
```

### How It's Wired

- **FRITZ!Box**: external `8443/tcp` → JoNAS `443` (external 443 belongs to the Synology), external `80/tcp` → JoNAS `80` for Let's Encrypt. **Never forward** `1234`, `4000`, or any model server port.
- **JoNAS**: `stacks/litellm/` runs LiteLLM + Postgres on the `edge` network; Caddy's `handle_path /litellm/*` block strips the prefix and proxies with `flush_interval -1` so streamed tokens aren't buffered. LAN clients can also use `http://192.168.178.17:4000` directly. Secrets live in JoNAS's gitignored `config/litellm.env`.
- **BosGameM5**: LM Studio serves on all interfaces (`lms server start --bind 0.0.0.0`), but [`scripts/llm-firewall.sh`](./scripts/llm-firewall.sh) lets **only JoNAS** (`192.168.178.17`) reach `:1234` — LM Studio has no authentication of its own, so the rest of the LAN is denied. The script also turns on ufw generally (deny incoming; your LANs and Docker keep full access) and backs up `/etc/ufw` before each run.
- **Mac Mini M4 (EmmFour)**: LM Studio on `192.168.178.71:1234` serves one model, `qwen3.8-9b-distill` (Q4_K_M GGUF, loaded with a 140032-token context) — 16GB holds only one model at a time. At 170000 tokens every request failed with LM Studio's `Compute error.`, even though the model loaded fine: on 16GB the context has to stay smaller. Unlike BosGameM5 there's **no firewall in front of it yet**: the macOS firewall is off and `:1234` is open to the whole LAN, not just JoNAS.
- The router's model list (JoNAS `stacks/litellm/litellm_config.yaml`) addresses backends by **LAN IP** — containers can't resolve mDNS `.local` names — so give each GPU box a fixed DHCP lease on the FRITZ!Box. Keep the list short: LM Studio loads models on demand, so every listed model is one a remote client can make BosGameM5 load.

### Issuing and Revoking Keys

```bash
scripts/llm-guest-key.sh add alex                                   # all router models, 20 req/min, 2 at a time, no expiry
scripts/llm-guest-key.sh add alex --models qwen3.8-27b --days 30    # one model, expires in 30 days
scripts/llm-guest-key.sh list
scripts/llm-guest-key.sh revoke alex
```

The script talks to `http://192.168.178.17:4000` and fetches the master key from JoNAS over key-based SSH, so it never sits on the client. `add` prints the key once together with the settings to send — use a private channel (Signal, etc.), not email. Revocation applies to the next request. There's no dollar budget (local tokens are free); `--rpm` and `--parallel` are what protect your own agents' share of the GPU.

Verified: from outside (mobile network), a valid key gets `200` and only its allowed models; no key or a wrong key gets `401`; a disallowed model gets `403`; a guest key calling `/key/generate` gets `401`.

### Using It From Claude Code

`~/.claude/litellm-remote-ddns-net.json` and `~/.claude/litellm-remote-myddns-me.json` (mode 600, personal key `kjwenger-remote`) point Claude Code at the router:

```json
{ "env": {
    "ANTHROPIC_BASE_URL": "https://pornbach.ddns.net:8443/litellm",
    "ANTHROPIC_AUTH_TOKEN": "<key>",
    "ANTHROPIC_MODEL": "qwen3-coder-next",
    "ANTHROPIC_DEFAULT_OPUS_MODEL": "qwen3-coder-next",
    "ANTHROPIC_DEFAULT_SONNET_MODEL": "qwen3-coder-next",
    "ANTHROPIC_DEFAULT_HAIKU_MODEL": "qwen3-coder-next",
    "CLAUDE_CODE_SUBAGENT_MODEL": "qwen3-coder-next",
    "CLAUDE_CODE_MAX_CONTEXT_TOKENS": "262144" } }
```

```bash
claude --settings ~/.claude/litellm-remote-ddns-net.json
```

Both files use `qwen3-coder-next`; the next section explains why.

The first version of this file only had the first three lines. A friend using it got this warning at startup:

> "qwen3.8-27b" isn't described by this version's model catalog; … auto-compact keeps this session within 200k tokens (the context window it assumes) …

It's a **warning, not an error**: the session runs, but Claude Code doesn't know the model's context window, so it assumes 200k. The extra lines fix that and one more problem:

- **`CLAUDE_CODE_MAX_CONTEXT_TOKENS`** gives Claude Code the model's real window, as loaded in LM Studio on BosGameM5. This stops the warning and puts auto-compact at the right point. Use `262144` for `qwen3-coder-next` and `qwen3.8-27b`, `131072` for `gpt-oss-20b`, and `140032` for `qwen3.8-9b-distill` (as loaded in LM Studio on the Mac Mini). The last two matter more: those windows are *smaller* than the assumed 200k, so without the setting a long session overflows instead of compacting.
- **`ANTHROPIC_DEFAULT_{OPUS,SONNET,HAIKU}_MODEL` and `CLAUDE_CODE_SUBAGENT_MODEL`** send every request to the router model. Otherwise Claude Code asks for Claude-named models for background work and subagents, and the router turns those down because a guest key only allows its listed models.

`scripts/llm-guest-key.sh add` prints this full block, with the model and window already filled in for the key's first allowed model.

At home, `~/.claude/litellm-settings.json` (alias `claude-local`) keeps using the personal LiteLLM on BosGameM5's `localhost:4000`. Always resume a LiteLLM-backed session **with** its `--settings` file: without it, Claude Code sends the transcript to Anthropic's API, which rejects the empty thinking blocks local models leave behind (`each thinking block must contain thinking`).

### Choosing the Model: Reading the Prompt Is the Bottleneck

For a coding agent on local hardware, the speed that matters is how fast the model **reads**, not how fast it writes. Claude Code sends about 30,700 tokens of instructions and tool definitions before your first message (measured in LM Studio's logs; OpenCode sends about 7,000), and every tool call is a new request that includes the whole conversation so far. Whatever isn't in the prompt cache has to be read again. This matches what Ash ("I do the math") reported in [*Can a local AI replace a $200 coding subscription?*](https://youtu.be/csUpbxXNMgE): one developer's 9-minute job took 66 minutes, about 50 of them spent rereading.

BosGameM5's LM Studio logs for October 2026 showed the same pattern with the dense `qwen3.8-27b`: 262 requests, **133 minutes spent reading prompts**, and 58% of that time came from 24 requests that reread the whole conversation. Prompt reading ran at a median 197 tokens/s, so a cold Claude Code request spent about 2.5 minutes reading before writing anything.

The fix is a mixture-of-experts model, which uses only a fraction of its weights per token. `qwen3-coder-next` (80B total, about 3B active per token, Q6_K, already in the LM Studio library) measured on BosGameM5 on 2026-10-15:

| | `qwen3-coder-next` | `qwen3.8-27b` (dense) |
|---|---|---|
| Prompt reading | ~650–720 tokens/s | ~197 tokens/s (median) |
| Writing | 42–50 tokens/s | ~16 tokens/s |
| Cold Claude Code request through the public URL | ~30 s | ~2.5 min for the prompt alone |
| GPU memory at 262144 context | ~72 GiB | ~17 GiB weights + ~16 GiB context |

It's on the JoNAS router as `qwen3-coder-next`, and both remote settings files use it. The 27B is still the better choice for careful reasoning on a hard problem, but for routine agent work, which is most of it, the coder model is three to four times faster.

**Memory per token of context.** Both Qwen models are hybrid: only every fourth layer uses full attention, the rest use a linear-attention (state-space) layer with no growing cache. Read from the GGUF headers, `qwen3.8-27b` needs **64 KiB per token** (16 full-attention layers × 4 KV heads × 256 dimensions × 2 for keys and values × 2 bytes), i.e. 16 GiB at 262144 tokens. `qwen3-coder-next` needs about 24 KiB per token, about 6 GiB at full context.

**They don't fit together.** At full context the two need about 105 GiB, more than the 96 GiB GPU carve-out. Unload one before using the other; a router request for the unloaded one makes LM Studio try to load it on demand, which fails or is refused by its memory guardrails.

**LM Studio settings for `qwen3-coder-next`.** Saved as the model's defaults in `~/.lmstudio/.internal/user-concrete-model-default-config/lmstudio-community/Qwen3-Coder-Next-GGUF/`, so they also apply when LM Studio reloads it on its own (without this, an on-demand reload once came up with an 8192-token context and broke a long session):

- **Context `262144`** (`llm.load.contextLength`).
- **2 parallel slots** instead of the default 4 (`llm.load.numParallelSessions`, or `lms load --parallel 2`). Slots share the GPU and one context budget. With 4, overlapping requests dropped below 3 tokens/s of prompt reading, and the log showed 43 "failed to find free space in the KV cache" warnings. Two is enough for you plus one friend.
- **Batch size left at the default 512.** The video's "read in bigger chunks" fix (raising `llm.load.llama.evalBatchSize` and `llm.load.llama.physicalBatchSize` to 2048) brought nothing here: a 28,441-token prompt read at 650 tokens/s against 688 before. It only costs GPU memory, so it was reverted.

**Prompt caching.** LM Studio (llama.cpp) reuses the cached prefix within a conversation, so a follow-up request only reads what's new. Two things still cause full rereads:

- Every new Claude Code session and subagent starts with a different first line (`x-anthropic-billing-header: cc_version=…`, 30 distinct values across 447 logged requests), so it begins with a cold cache.
- Subagents and background calls on the same model evict the main session's cache. That's the case for sending them to another machine, e.g. the Mac Mini's `qwen3.8-9b-distill` via `CLAUDE_CODE_SUBAGENT_MODEL` (see [Option 2: Asymmetric Task Routing](#option-2-asymmetric-task-routing-via-named-providers)).

**Thinking effort.** At ~45 tokens/s, every 1,000 thinking tokens is about 20 seconds; on the 27B, about a minute. In the video, Simon Willison's pelican test took 21 minutes with full thinking and 2 with thinking off. Keep thinking low for routine work. Whether Claude Code's thinking budget reaches LM Studio through LiteLLM still needs testing.

### Using It From Other Harnesses

The same router serves Pi Agent, Hermes Agent, OpenCode and DeepSeek Harness. The working configurations are in [`config/`](./config/) (with where each file goes, a smoke test and the pitfalls hit while setting them up) and the launchers in [`scripts/`](./scripts/). None of them contains a key: each reads it from `~/.claude/litellm-remote-ddns-net.json`, so the key is stored in one place only.

| Harness | Start it with | Tokens sent for a one-line prompt | Extra requests |
|---|---|---|---|
| Pi Agent | `pi-remote` | **1,657** | none |
| DeepSeek Harness | `dsh-remote web` or `dsh-remote headless "…"` | 5,972 | one of 122 tokens |
| OpenCode | `opencode-remote` | 10,650 | a 539-token session-title request first |
| Hermes Agent | `hermes-remote` | 12,855 | none seen |
| Claude Code | `claude --settings ~/.claude/litellm-remote-ddns-net.json` | ~30,700 | titles, subagents |

Measured on 2026-10-15 from the router's spend log (`/spend/logs/v2` on JoNAS), each harness sending "Reply with just: pong" to `qwen3-coder-next`. That's the fixed cost of every request a harness makes: its instructions plus its tool definitions. Inside a session the prompt cache absorbs most of it, but every new session and every subagent pays it again (see [Choosing the Model](#choosing-the-model-reading-the-prompt-is-the-bottleneck)). At ~660 tokens/s that's about 2.5 s for Pi against about 46 s for Claude Code before the first word.

Fewer tokens isn't automatically better: Claude Code's large prompt buys more capable tools, and in Composio's benchmark (see [CLI.md](./CLI.md#understanding-token--context-overhead)) Hermes needed the fewest dollars per *successful* task despite its overhead. For routine local work (tests, small fixes, boilerplate) Pi is the fastest start; for long multi-file tasks Claude Code or Hermes.

All launchers pass `qwen3-coder-next` explicitly. Several harnesses' own defaults still name local models (Hermes defaults to `qwen3.8-27b` on local LM Studio), and on BosGameM5 a request that loads a second large model next to `qwen3-coder-next` overfills the 96 GiB of GPU memory: on 2026-10-15 that crashed the whole desktop session, not just LM Studio.

### What to Tell a Friend

- **Privacy:** the router on JoNAS keeps who / which model / token counts / when (`turn_off_message_logging: true`) — not prompt or response text. **But LM Studio on BosGameM5 logs full prompts** to `~/.lmstudio/server-logs/` while its server setting `logSensitiveData` is on (the default seen here). Turn that off in LM Studio's server settings if you promise anyone more than "I could read it" — and either way, you control the boxes; say so plainly.
- **Availability and speed:** it's up when BosGameM5 is. With `qwen3-coder-next` a fresh Claude Code session takes about 30 seconds to start answering; when your own sessions are using the GPU, or with the dense 27B, it can take minutes.
- **Setup:** `OPENAI_API_BASE=https://pornbach.ddns.net:8443/litellm/v1` plus their key, or the complete Claude Code `env` block above (the model-alias and context-window lines included; without them they get the "isn't described by this version's model catalog" warning).

**Never** forward model server ports, give out SSH/desktop accounts, or expose `rpc-server` (no authentication at all). For admin access to LAN-only services from outside, JoNAS's WireGuard (`docs/VPN.md` there) is the right tool, not more port forwards.

## A Ready-to-Run Starter Bundle

The [`orchestration/`](./orchestration/) directory next to this file is a copy-paste starter kit covering the same ground as the four sections above: `litellm_config.yaml`, `hermes_config_snippet.yaml`, `dsh_settings_snippet.yaml`, and its own `README.md` walking through bring-up in order. Use it as the thing you actually edit and run; use this document for the *why* behind each piece, the verified upstream sourcing, and the caveats below that the bundle itself doesn't spell out.

Two things worth fixing before you run it as-is:

- **`llama-server ... -fa` on the GS63 command in `orchestration/README.md`** — drop `-fa` (or leave it off) on that machine specifically. Per [No Flash Attention on This Card](#no-flash-attention-on-this-card) above, a GTX 1060's compute capability (6.1) is below the ≥7.5 Flash Attention requires; forcing it on hardware that doesn't support it will not give the intended speedup and, depending on your llama.cpp build, may warn or refuse to start rather than silently doing the right thing.
- **The exact auxiliary-task key names in `hermes_config_snippet.yaml`** (`auxiliary.title`, `auxiliary.compression`) — these weren't independently confirmed against the same source as [Auxiliary Task Slots](#auxiliary-task-slots) above, which found similar but not identical names (e.g. `title_generation`). Check both against whatever version of [configuring-models.md](https://github.com/NousResearch/hermes-agent/blob/main/website/docs/user-guide/configuring-models.md) you actually have installed before assuming either is exactly right.

The bundle's model choices (`qwen2.5-72b-instruct` on Strix Halo, `qwen3.8-9b-distill` via LM Studio on the Mac Mini — the one actually in use, `llama-3.1-8b-instruct-q4` on the GS63) are also a different, equally valid pick from this document's own [Recommended Models](#the-three-machines-at-a-glance) references — swap in whichever you've actually downloaded; the two aren't meant to match model-for-model.

## Bring-Up Order

1. Install each machine's model server as a supervised service (see [Starting and Supervising the Fleet](#starting-and-supervising-the-fleet)), independently, and confirm each one answers `curl http://<ip>:<port>/v1/models` before wiring anything else to it.
2. Confirm [Option 1](#option-1-three-independent-lanes) works — point one harness instance at each endpoint by hand.
3. Add [Option 2](#option-2-asymmetric-task-routing-via-named-providers)'s named-provider config to whichever harness you're using, and confirm `/model` switching reaches all three.
4. Only then add LiteLLM in front, if you want one URL instead of three. Test the proxy against each backend individually (`model_name` per machine) before pointing a harness at it.
5. Leave subagent/auxiliary-slot wiring for last — it depends on everything above already working, and is the least stable part of this whole stack (Hermes' auxiliary slots are shipped but narrower than full task routing; DSH's subagent model-override path is a developer-preview API).

## Troubleshooting and Gotchas

- **A harness times out reaching the GS63 specifically.** This is usually the slowest of the three machines by a wide margin — raise timeouts the same way [CLI.md](./CLI.md#configuring-hermes-agent) recommends `HERMES_STREAM_READ_TIMEOUT` for a slow CPU-only backend; the same logic applies to any harness's own timeout setting when pointed at this card.
- **`MISSING_CREDENTIAL` from DeepSeek Harness against a local server.** Every custom provider's `apiKeyEnv` variable must be exported even though llama.cpp/Ollama/LM Studio don't check it — see [CLI.md's troubleshooting section](./CLI.md#troubleshooting-deepseek-harness).
- **CMake fails to target the GTX 1060 on a fresh CUDA 13.x install.** Expected — see [Pin CUDA 12.x](#pin-cuda-12x--cuda-13-dropped-pascal). Reinstall CUDA Toolkit 12.x alongside (or instead of) 13.x for building against this card.
- **`rpc-server` handshake hangs when adding the GS63 as a third RPC worker.** Mismatched llama.cpp build/tag across the three machines is the most common cause — see the same warning in [STRIX-HALO.md — Option 3](./STRIX-HALO.md#option-3-model-sharding-across-both-machines-llamacpp-rpc); all three nodes need the identical tag, and given [Option 3's downsides](#option-3-model-sharding--why-not-here) here, reconsider whether you need this at all.

## Sources

- [NVIDIA — Pascal Compatibility Guide (CUDA 13.3)](https://docs.nvidia.com/cuda/pascal-compatibility-guide/index.html) and [CUDA Toolkit 13.1 Release Notes](https://docs.nvidia.com/cuda/archive/13.1.0/cuda-toolkit-release-notes/index.html) — confirmation that CUDA 13.0 removed Pascal (and Maxwell/Volta) offline-compilation and library support.
- [ggml-org/llama.cpp discussions and issues on GTX 1060 CUDA performance](https://github.com/ggml-org/llama.cpp/discussions/15013) — compute capability 6.1, no tensor cores, and the Flash-Attention ≥7.5 requirement.
- [NousResearch/hermes-agent — configuring-models.md](https://github.com/NousResearch/hermes-agent/blob/main/website/docs/user-guide/configuring-models.md) — auxiliary task slot mechanism and `auto` default behavior.
- [NousResearch/hermes-agent issue #32704 — Capability-Based Multi-Model Routing](https://github.com/NousResearch/hermes-agent/issues/32704) — confirmation that automatic task-aware routing across models is a feature request, not a shipped capability.
- [deepseek-ai/deepseek-harness — docs/architecture.md](https://github.com/deepseek-ai/deepseek-harness/blob/master/docs/architecture.md) and [docs/subsystems/subagent.md](https://github.com/deepseek-ai/deepseek-harness/blob/master/docs/subsystems/subagent.md) — the subagent capability seam, named providers (`spawn`, `fork`, `claude-code`, `codex`), and the `agentOptions` provider/model override mechanism.
- Ash, "I do the math": [*Can a local AI replace a $200 coding subscription?*](https://youtu.be/csUpbxXNMgE) (YouTube, 2026) — prompt reading as the bottleneck for local coding agents, Claude Code's ~33k-token system prompt, Qwen3-Coder-Next vs. the dense Qwen 27B, thinking effort and caching settings. Cross-checked against BosGameM5's own LM Studio logs and benchmarks in [Choosing the Model](#choosing-the-model-reading-the-prompt-is-the-bottleneck).
- This repo's own [STRIX-HALO.md](./STRIX-HALO.md), [MAC-MINI-M4.md](./MAC-MINI-M4.md), [CLI.md](./CLI.md), and [PROVIDERS.md](./PROVIDERS.md) — the two-machine patterns, per-harness config schemas, and LiteLLM setup this document extends to three machines rather than restating from scratch.
- [ss64.com — launchctl](https://ss64.com/mac/launchctl.html) and community `launchctl` references — the modern `bootstrap`/`bootout`/`kickstart` syntax and `system`/`gui/$UID` domain distinction used in [Starting and Supervising the Fleet](#starting-and-supervising-the-fleet).
- Community reports on ROCm environment variables under systemd (e.g. `HSA_OVERRIDE_GFX_VERSION` needing an explicit unit-file `Environment=` line, not just shell inheritance) — cross-checked against this repo's own [STRIX-HALO.md — Building llama.cpp for gfx1151](./STRIX-HALO.md#building-llamacpp-for-gfx1151), which already calls out the same requirement.

This document was assembled from the above sources and cross-checked against this repo's existing per-machine and per-harness documentation; none of it was independently run on an actual GS63/GTX 1060 laptop by the author. Treat the GS63-specific commands and model recommendations as a well-sourced starting point to verify on your own hardware, and treat the Hermes auxiliary-slot and DeepSeek Harness subagent sections as pointers to the right upstream docs to check at whatever version you install, not copy-pasteable guarantees — both projects are moving fast enough that exact keys and APIs may have shifted since this was written.
