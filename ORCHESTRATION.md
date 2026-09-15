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

- **An embedding model** for RAG pipelines — small (100M-600M parameter class), fits trivially in 6GB, and frees the other two machines from ever loading an embedding model alongside their main one. Serve it via llama.cpp's `--embedding` flag on `llama-server`, or Ollama's embedding models.
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
    baseUrl: http://<mac-mini-ip>:8080/v1
    api: openai-completions
    apiKey: dummy
    models:
      - id: qwen3.5-9b

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

**Worth watching:** [Magnitude](./RUNTIMES.md#magnitude-installation) bundles most of this section into one tool — hardware profiling, model download/selection, and its own service supervision (`magnitude service install/start`) in place of the systemd/launchd files above. It's not a drop-in replacement yet, though: whether it can bind beyond loopback for LAN access (needed for a networked three-machine setup at all) isn't documented either way, and its AMD/ROCm depth for Strix Halo specifically is unconfirmed. See [RUNTIMES.md's caveats](./RUNTIMES.md#whats-unconfirmed) before trying it in place of the setup above.

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
      model: openai/qwen3.5-9b
      api_base: http://<mac-mini-ip>:8080/v1
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
      api: http://<mac-mini-ip>:8080/v1
      transport: chat_completions
      default_model: qwen3.5-9b
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
    model: mac-fast:qwen3.5-9b   # only if it's a vision-capable build
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
      baseURL: http://<mac-mini-ip>:8080/v1
      models:
        - id: qwen3.5-9b
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

## A Ready-to-Run Starter Bundle

The [`orchestration/`](./orchestration/) directory next to this file is a copy-paste starter kit covering the same ground as the four sections above: `litellm_config.yaml`, `hermes_config_snippet.yaml`, `dsh_settings_snippet.yaml`, and its own `README.md` walking through bring-up in order. Use it as the thing you actually edit and run; use this document for the *why* behind each piece, the verified upstream sourcing, and the caveats below that the bundle itself doesn't spell out.

Two things worth fixing before you run it as-is:

- **`llama-server ... -fa` on the GS63 command in `orchestration/README.md`** — drop `-fa` (or leave it off) on that machine specifically. Per [No Flash Attention on This Card](#no-flash-attention-on-this-card) above, a GTX 1060's compute capability (6.1) is below the ≥7.5 Flash Attention requires; forcing it on hardware that doesn't support it will not give the intended speedup and, depending on your llama.cpp build, may warn or refuse to start rather than silently doing the right thing.
- **The exact auxiliary-task key names in `hermes_config_snippet.yaml`** (`auxiliary.title`, `auxiliary.compression`) — these weren't independently confirmed against the same source as [Auxiliary Task Slots](#auxiliary-task-slots) above, which found similar but not identical names (e.g. `title_generation`). Check both against whatever version of [configuring-models.md](https://github.com/NousResearch/hermes-agent/blob/main/website/docs/user-guide/configuring-models.md) you actually have installed before assuming either is exactly right.

The bundle's model choices (`qwen2.5-72b-instruct` on Strix Halo, `qwen2.5-14b-instruct-4bit` on the Mac Mini, `llama-3.1-8b-instruct-q4` on the GS63) are also a different, equally valid pick from this document's own [Recommended Models](#the-three-machines-at-a-glance) references — swap in whichever you've actually downloaded; the two aren't meant to match model-for-model.

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
- This repo's own [STRIX-HALO.md](./STRIX-HALO.md), [MAC-MINI-M4.md](./MAC-MINI-M4.md), [CLI.md](./CLI.md), and [PROVIDERS.md](./PROVIDERS.md) — the two-machine patterns, per-harness config schemas, and LiteLLM setup this document extends to three machines rather than restating from scratch.
- [ss64.com — launchctl](https://ss64.com/mac/launchctl.html) and community `launchctl` references — the modern `bootstrap`/`bootout`/`kickstart` syntax and `system`/`gui/$UID` domain distinction used in [Starting and Supervising the Fleet](#starting-and-supervising-the-fleet).
- Community reports on ROCm environment variables under systemd (e.g. `HSA_OVERRIDE_GFX_VERSION` needing an explicit unit-file `Environment=` line, not just shell inheritance) — cross-checked against this repo's own [STRIX-HALO.md — Building llama.cpp for gfx1151](./STRIX-HALO.md#building-llamacpp-for-gfx1151), which already calls out the same requirement.

This document was assembled from the above sources and cross-checked against this repo's existing per-machine and per-harness documentation; none of it was independently run on an actual GS63/GTX 1060 laptop by the author. Treat the GS63-specific commands and model recommendations as a well-sourced starting point to verify on your own hardware, and treat the Hermes auxiliary-slot and DeepSeek Harness subagent sections as pointers to the right upstream docs to check at whatever version you install, not copy-pasteable guarantees — both projects are moving fast enough that exact keys and APIs may have shifted since this was written.
