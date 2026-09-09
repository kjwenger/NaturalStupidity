# Strix Halo + ROCm: 128GB Unified Memory LLM Setup

<!-- TOC -->
* [Strix Halo + ROCm: 128GB Unified Memory LLM Setup](#strix-halo--rocm-128gb-unified-memory-llm-setup)
  * [The Unified Memory Model (Read This First)](#the-unified-memory-model-read-this-first)
  * [BIOS Configuration](#bios-configuration)
  * [Kernel / GRUB Configuration](#kernel--grub-configuration)
    * [Sizing the GTT Aperture for 128GB](#sizing-the-gtt-aperture-for-128gb)
  * [ROCm Installation](#rocm-installation)
    * [Verifying the GPU Is Visible](#verifying-the-gpu-is-visible)
  * [Building llama.cpp for gfx1151](#building-llamacpp-for-gfx1151)
  * [Running a Model](#running-a-model)
  * [Quantization Strategy: Why "4-bit" Isn't One Thing](#quantization-strategy-why-4-bit-isnt-one-thing)
    * [Four Concepts That Get Mixed Up](#four-concepts-that-get-mixed-up)
    * [KV Cache Math on Hybrid-Attention Models](#kv-cache-math-on-hybrid-attention-models)
    * [What Actually Works on Strix Halo (and What Doesn't)](#what-actually-works-on-strix-halo-and-what-doesnt)
    * [Dynamic GGUF: the Right Default Here](#dynamic-gguf-the-right-default-here)
  * [Recommended Models for a 128GB Strix Halo](#recommended-models-for-a-128gb-strix-halo)
  * [Combining with a Second Machine (e.g. a Mac Mini)](#combining-with-a-second-machine-eg-a-mac-mini)
    * [Option 1: More Agents, No Extra Tooling (Recommended Starting Point)](#option-1-more-agents-no-extra-tooling-recommended-starting-point)
    * [Option 2: Asymmetric Task Routing (Small Model on the Mac, Big Model on Strix Halo)](#option-2-asymmetric-task-routing-small-model-on-the-mac-big-model-on-strix-halo)
    * [Option 3: Model Sharding Across Both Machines (llama.cpp RPC)](#option-3-model-sharding-across-both-machines-llamacpp-rpc)
  * [vLLM on ROCm (for Concurrent/Throughput Workloads)](#vllm-on-rocm-for-concurrentthroughput-workloads)
  * [Troubleshooting and Gotchas](#troubleshooting-and-gotchas)
  * [Sources](#sources)
<!-- TOC -->

This is a hardware-specific companion to [RUNTIMES.md](./RUNTIMES.md) — that document covers local LLM runtimes in general (LM Studio, Ollama, MLX, EXO, Lemonade, Unsloth Studio, llama.cpp, vLLM); this one is about getting the most out of an **AMD Ryzen AI Max+ 300-series ("Strix Halo") APU with 128GB of unified LPDDR5X memory** (this repo's own BosGameM5 box), on Linux, via ROCm — plus how to put a second machine (e.g. a 16GB Mac Mini M4) to work alongside it.

## The Unified Memory Model (Read This First)

Strix Halo has no discrete VRAM. The Radeon 8060S iGPU (gfx1151, RDNA 3.5, 40 compute units) shares the same LPDDR5X pool as the CPU. Linux will often report something absurd like **1 GiB of "VRAM"** — that is *not* your ceiling. That 1 GiB is a small BIOS-carved framebuffer; the GPU actually reaches the rest of system memory through a much larger **GTT/TTM aperture**, sized by kernel boot parameters, not the BIOS framebuffer setting. Don't panic at `rocm-smi` showing 1 GiB — check the GTT limit instead.

This matters because it's the whole reason "how much VRAM do I have" is a *kernel config* question on this hardware, not a hardware spec question — and it's the first thing every setup guide gets wrong if you skip it.

## BIOS Configuration

1. **UMA Frame Buffer Size:** set to **512MB**. This is just the small carve-out Linux reports as "VRAM" — it does not limit how much memory the GPU can actually use once the GTT aperture is configured below.
2. **IOMMU:** leave enabled unless you have a specific reason not to. Fully disabling it (`amd_iommu=off`) is reported to give a small (~6%) throughput bump on some setups, but it also disables IOMMU-based isolation. A middle ground some ROCm/vLLM setups use instead is **IOMMU passthrough** (`iommu=pt`, set at the kernel/GRUB level below) — keeps IOMMU nominally on while avoiding most of the translation overhead. Pick disable-entirely only if this box is a dedicated inference appliance you don't otherwise care about isolating.
3. **TDP / power mode:** optional — some guides bump this to 85W for sustained inference throughput on APUs that ship with a lower default.

## Kernel / GRUB Configuration

You need a reasonably recent kernel (6.16+ recommended; 6.17 is confirmed working). Edit `/etc/default/grub`:

```bash
sudo nano /etc/default/grub
```

Add (or extend) `GRUB_CMDLINE_LINUX_DEFAULT`:
```
GRUB_CMDLINE_LINUX_DEFAULT="quiet splash iommu=pt amdgpu.gttsize=<MiB> ttm.pages_limit=<pages>"
```
Then regenerate GRUB and reboot:
```bash
# Debian/Ubuntu
sudo update-grub

# Fedora/RHEL
sudo grub2-mkconfig -o /boot/grub2/grub.cfg

sudo reboot
```

### Sizing the GTT Aperture for 128GB

`amdgpu.gttsize` is in **MiB**; `ttm.pages_limit` is in **4KiB pages**, and the two must agree: `pages_limit = gttsize_MiB × 256`.

Published guides for 128GB Strix Halo boxes allocate anywhere from ~115GB (conservative, ~13GB left for the OS) to ~124GB (aggressive, ~4GB left) to the GPU — leave a headroom amount that matches how you actually use this machine, not a fixed percentage:

| Profile | GPU allocation | Host headroom | `gttsize` (MiB) | `ttm.pages_limit` |
|---|---|---|---|---|
| Desktop (browser, IDE, etc. running alongside) | 112 GB | 16 GB | `114688` | `29360128` |
| Balanced (recommended default) | 120 GB | 8 GB | `122880` | `31457280` |
| Dedicated inference box (headless) | 124 GB | 4 GB | `126976` | `32505856` |

(The Dedicated row matches a real published 128GB Strix Halo config verbatim — a useful independent sanity check on the formula above.)

Start with **Balanced**. Push toward Dedicated only once you're confident nothing else on the box needs headroom — an out-of-memory GPU allocation under ROCm on this platform tends to manifest as a KFD driver hang (spinning, unresponsive), not a clean error, so don't be aggressive on a machine you also use for daily work.

## ROCm Installation

Pin **ROCm 7.2.2** for llama.cpp — it's the version multiple independent Strix Halo build guides confirm has working gfx1151 device libraries. Newer official ROCm point releases have been reported to *drop* gfx1151 support in some intermediate builds, so don't blindly `apt upgrade` ROCm packages later without checking. Follow AMD's [ROCm install docs](https://rocm.docs.amd.com/projects/install-on-linux/en/latest/) for your distro to get it installed to `/opt/rocm-7.2.2`.

Make sure your user is in the right groups (log out/in, or reboot, after this):
```bash
sudo usermod -aG render,video $USER
```

### Verifying the GPU Is Visible

```bash
/opt/rocm-7.2.2/bin/rocminfo | grep -m1 -A2 gfx1151
/opt/rocm-7.2.2/bin/rocm-smi --showmeminfo vram | head
```
Don't be alarmed if `rocm-smi` reports only ~1 GiB of VRAM here — see [The Unified Memory Model](#the-unified-memory-model-read-this-first) above. What matters is that `gfx1151` shows up at all.

## Building llama.cpp for gfx1151

```bash
sudo apt update
sudo apt install build-essential cmake ninja-build git curl libcurl4-openssl-dev

export ROCM_HOME=/opt/rocm-7.2.2
export HSA_OVERRIDE_GFX_VERSION=11.5.1
export PYTORCH_ROCM_ARCH=gfx1151

git clone https://github.com/ggml-org/llama.cpp
cd llama.cpp

cmake -B build -G Ninja \
  -DGGML_HIP=ON \
  -DAMDGPU_TARGETS=gfx1151 \
  -DCMAKE_BUILD_TYPE=Release \
  -DGGML_HIP_ROCWMMA_FATTN=ON \
  -DLLAMA_CURL=ON

cmake --build build -j --target llama-server
```

`HSA_OVERRIDE_GFX_VERSION=11.5.1` is required — without it, ROCm doesn't correctly recognize gfx1151 hardware. Export both `ROCM_HOME` and `HSA_OVERRIDE_GFX_VERSION` in every shell (or a systemd unit's `Environment=`) that runs `llama-server`, not just the build shell.

## Running a Model

```bash
export ROCM_HOME=/opt/rocm-7.2.2
export HSA_OVERRIDE_GFX_VERSION=11.5.1
export AMD_SERIALIZE_KERNEL=3   # helps surface real errors instead of silent corruption, small perf cost

./build/bin/llama-server \
  -m /path/to/your-model-Q4.gguf \
  -ngl 99 -c 32768 --host 0.0.0.0 --port 8080 \
  --no-mmap --flash-attn on --cache-type-k q8_0 --cache-type-v q8_0
```

Notes on the flags:
- `-ngl 99` — offload all layers to GPU.
- `--no-mmap` — recommended on Strix Halo for GPU backends, and close to mandatory for MoE/hybrid models under memory pressure; without it (and without a cgroup memory budget), overflowing the GTT aperture has been observed to cause KFD driver thrashing rather than a clean failure.
- `--flash-attn on` — required if you use `--cache-type-v q8_0` (quantized V-cache needs flash attention).
- `--cache-type-k q8_0 --cache-type-v q8_0` — 8-bit KV cache; see the [quantization](#kv-cache-math-on-hybrid-attention-models) section below for why this matters more than the weight quant on a long-context hybrid model.

Rough expectations on this hardware: a 26-27B dense/hybrid model at Q4 cold-loads in roughly 20-40 seconds; expect single-digit-to-teens tokens/sec on 70B-class dense models fully resident in the unified memory pool, faster on MoE/hybrid architectures where only a fraction of parameters are active per token.

## Quantization Strategy: Why "4-bit" Isn't One Thing

This section distills the technical substance of [a YouTube deep-dive on quantization](https://www.youtube.com/watch?v=vW0KY_8z4q0) (channel: RepoCad), cross-checked against independent sources — its core claims about Qwen3.8-27B's architecture and the Bonsai 27B ternary model both check out against current documentation, so the framework below is trustworthy, not just a transcript summary.

### Four Concepts That Get Mixed Up

A "4-bit quant" download can mean four different things, and mixing them up is why two files both labeled "4-bit" can have wildly different quality and speed:

1. **Numeric representation** — the actual data type: FP8, INT4, NVFP4, ternary, etc.
2. **Quantization algorithm** — the math used to decide how to round weights with minimal error: AWQ, GPTQ, importance-matrix (imatrix) calibration.
3. **Container format** — how it's packaged on disk: GGUF, EXL3, safetensors.
4. **Inference kernel** — the actual code executing the matmuls on *your* hardware: a ROCm/HIP kernel, a CUDA kernel, Metal.

**GGUF is a container, not a quantization method.** Two GGUF files can use completely different algorithms and bit depths per tensor. This is also why quantization *quality* is a representation+algorithm question, while quantization *speed* is a representation+hardware+kernel question — a format tuned for one architecture (Nvidia Blackwell's native FP4 tensor cores, Apple Silicon's affine 4-bit via MLX) tells you nothing about how it'll run on ROCm/gfx1151.

### KV Cache Math on Hybrid-Attention Models

Modern mid-size models (Qwen3.8-27B and similar) are **hybrid**: most layers use cheap linear attention (Gated DeltaNet — O(1) per token, no growing KV cache), while a minority of layers use full attention (which does need a KV cache that grows with context). For Qwen3.8-27B specifically: 64 total layers, only 16 use full gated attention, the remaining 48 use Gated DeltaNet.

For the full-attention layers, per-token KV cache size is:
```
layers × kv_heads × head_dim × 2 (K and V) × bytes_per_element
```
For Qwen3.8-27B's 16 full-attention layers (4 KV heads, head dim 256), that's `16 × 4 × 256 × 2 = 32,768` values/token — 64 KiB/token at 16-bit precision. That scales to:

| Context | KV cache @ FP16 | KV cache @ 8-bit | KV cache @ 4-bit |
|---|---|---|---|
| 32K tokens | ~2 GB | ~1 GB | ~0.5 GB |
| 128K tokens | ~8 GB | ~4 GB | ~2 GB |
| 262K tokens (native max) | ~16 GB | ~8.5 GB | ~4.5 GB |

**The takeaway that matters most for a fixed-memory unified-memory box:** a quant that "fits" at a short prompt can blow your budget the moment you feed it a long document or codebase, because the KV cache — not just the weights — grows with context. On Strix Halo there's no PCIe spillover to fall back on gracefully; you're sharing one memory pool, so an overflow degrades everything on the box, not just the model. This is exactly why `--cache-type-k q8_0 --cache-type-v q8_0` in the [Running a Model](#running-a-model) command above is not a minor detail — quantizing the KV cache is often a bigger lever on usable context length than shaving another bit off the weights.

### What Actually Works on Strix Halo (and What Doesn't)

The video (correctly) surveys several 4-bit approaches — but most of them are tied to specific hardware you don't have:

- **ExLlamaV3 / EXL3** (vector + trellis quantization, derived from QTIP) — Nvidia CUDA only. Not usable on ROCm/gfx1151.
- **NVFP4** — requires Nvidia Blackwell's native FP4 tensor cores. Not usable on any AMD hardware, including Strix Halo.
- **MLX 4-bit affine quantization** — Apple Silicon only, via the unified-memory-but-different `mlx-lm` runtime (see [RUNTIMES.md — MLX](./RUNTIMES.md#mlx-installation-macos-only)). Not usable here.
- **AMD's own low-precision path is MXFP4 via Quark** — the AMD-side answer to Nvidia's NVFP4, but tooling and model availability for it lags; treat it as emerging rather than the safe default today.
- **Calibrated INT4 (AWQ/GPTQ/AutoRound), served via vLLM** — this *does* work on ROCm, and is the right choice once you're serving concurrent requests rather than a single interactive session (see [vLLM on ROCm](#vllm-on-rocm-for-concurrentthroughput-workloads) below).
- **Bonsai 27B (ternary, ~1.71 bits/weight)** — a genuinely different category: it's a from-scratch low-bit *retraining* of Qwen3.6-27B by PrismML (not a post-training quant of Qwen3.8), using custom ternary kernels, at ~5.9GB. It recovers math ability well but shows a real drop in tool-calling and complex instruction-following — worth trying as a curiosity for a phone/tiny-footprint use case, not as your daily driver on a 128GB box that doesn't need to compress that hard.

### Dynamic GGUF: the Right Default Here

For llama.cpp on ROCm, **GGUF with per-layer ("dynamic") quantization is the correct default** — specifically Unsloth's Dynamic 3.0 recipes. Instead of applying one bit depth uniformly, it uses importance-matrix calibration (Unsloth's current sets run thousands of calibration chunks across agentic coding, chat, and multilingual text) to keep sensitive layers (attention projections, embeddings) at higher precision while compressing the bulk feed-forward matrices harder. The result is measurably better quality at the same file size than a naive uniform quant — Unsloth's own testing claims >10% better top-1% accuracy at matched size versus older uniform recipes.

Look for the `UD-` prefix on Hugging Face (e.g. `UD-Q4_K_XL`, `UD-Q4_K_M`, `UD-Q3_K_XL`) under the `unsloth/` org — these are exactly the [Recommended Models](./RUNTIMES.md#recommended-models-for-lm-studio) already called out elsewhere in this repo for LM Studio, and they work identically well through `llama-server` on ROCm.

## Recommended Models for a 128GB Strix Halo

This complements — and updates — the hardware-specific recommendations already in [RUNTIMES.md — Recommended Models from Claude](./RUNTIMES.md#recommended-models-from-claude), which targets this exact class of hardware (Ryzen AI Max 300-series / Radeon 8060S, 128GB). With the memory budget from [Sizing the GTT Aperture](#sizing-the-gtt-aperture-for-128gb) above (112-124GB usable), reasonable picks:

| Model | Size on disk (approx.) | Notes |
|---|---|---|
| `unsloth/Qwen3.8-27B-GGUF` (`UD-Q6_K_XL` or `UD-Q8_K_XL`) | ~22-29 GB | Hybrid attention, 262K native context — see the KV-cache math above. Huge headroom for context at this size on a 128GB box; a good default for interactive agent use. |
| `unsloth/Qwen3-Coder-Next-GGUF` (`UD-Q8_K_XL`) | ~85 GB | MoE (80B total, 3B active) — fast decode despite the large total size; comfortable fit at any GTT profile, still leaves real room for a long context. |
| `Qwen/Qwen3.6-35B-A3B` (MoE, 3B active) | ~24 GB in BF16 | Good middle ground; also the model the vLLM/ROCm toolbox guide below defaults to. |
| GLM-4.5-Air (~106B, MoE) | Varies by quant | Repeatedly recommended elsewhere in this repo as a strong "daily driver" for 96-128GB-class hardware; even more comfortable at 128GB. |
| GPT-OSS-120B (`UD-` GGUF, MXFP4) | ~65 GB | High-end pick; at 128GB there's real headroom left for context — check the actual file size against your chosen GTT profile before committing. |
| Qwen3-235B-A22B (MoE, 22B active) at a low-bit UD quant (~88 GB, `UD-Q2_K_XL`) | ~88 GB | Only realistically fits once you're past 96GB — the extra 32GB on this box specifically opens the door to this one. Tight on context at any GTT profile; use the "Dedicated" profile if you go this route. |

Always check the actual on-disk size and context-length trade-off for the *specific* quant file you pick against the KV-cache table above — "it downloaded" isn't the same as "it fits with the context length you actually need."

## Combining with a Second Machine (e.g. a Mac Mini)

A 16GB Mac Mini M4 and this 128GB Strix Halo box are a genuinely useful pair, but not because you literally glue their memory together into one giant pool — a 16GB contribution is a rounding error next to 128GB, and (as [Option 3](#option-3-model-sharding-across-both-machines-llamacpp-rpc) below explains) combining them that way makes everything run at the speed of your *network link*, not your fastest machine. The two setups that are actually worth doing are running them as **two independent lanes** (more throughput) or **two asymmetric roles** (small/fast + large/capable). Both are things you can wire into the harnesses in [CLI.md](./CLI.md) today with zero extra tooling.

### Option 1: More Agents, No Extra Tooling (Recommended Starting Point)

If what you actually want is "more machines for the agents to run on," you already have that the moment both boxes are each running their own model server:

- **Strix Halo:** `llama-server` (from [Building llama.cpp for gfx1151](#building-llamacpp-for-gfx1151) above) or Ollama, serving on `0.0.0.0:8080` or `0.0.0.0:11434`.
- **Mac Mini:** MLX (see [RUNTIMES.md — MLX Installation](./RUNTIMES.md#mlx-installation-macos-only)) or Ollama, serving locally or on `0.0.0.0` on its own port.

Point two *separate* instances of any harness from [CLI.md](./CLI.md) at the two endpoints (e.g. `OPENAI_API_BASE=http://<strix-halo-ip>:8080/v1` in one terminal/session, `OPENAI_API_BASE=http://<mac-mini-ip>:1234/v1` — or just `localhost` if you're running the harness on the Mac itself — in another), and you have two fully independent agents working on two different tasks/repos/branches concurrently. No distributed-inference tooling, no shared cluster to keep in sync, no single point of failure — just two ordinary local-LLM setups your agents happen to be pointed at. This is the highest-value, lowest-effort answer to "get more machines for the agents to run on."

### Option 2: Asymmetric Task Routing (Small Model on the Mac, Big Model on Strix Halo)

For "use a smaller LLM on the Mac for some processing and the Strix Halo for the other" specifically: the Mac Mini's 16GB comfortably handles a fast ~7-9B model via MLX (Qwen3.5-9B-class is a commonly recommended fit — see [RUNTIMES.md — Running Models (MLX)](./RUNTIMES.md#running-models-mlx)), while Strix Halo runs whatever large model you picked from the table above. Rather than manually switching endpoints, register **both as named providers in the same harness config** and let the harness (or you, per task) pick which one handles what — this is exactly the multi-provider pattern already documented for several harnesses in [CLI.md](./CLI.md):

```yaml
# Oh My Pi example (~/.omp/agent/models.yml) — see CLI.md's Oh My Pi CLI section
providers:
  mac-fast:
    baseUrl: http://<mac-mini-ip>:8080/v1   # mlx_lm.server on the Mac
    api: openai-completions
    apiKey: dummy
    models:
      - id: qwen3.5-9b

  strix-heavy:
    baseUrl: http://localhost:8080/v1        # llama-server on Strix Halo itself
    api: openai-completions
    apiKey: dummy
    models:
      - id: qwen3.8-27b
```
The same pattern applies to [Kilo Code](./CLI.md#using-kilo-code-with-local-llms-via-lm-studio-and-ollama), [Pi Agent](./CLI.md#using-pi-agent-with-local-llms-via-lm-studio-and-ollama), [Hermes Agent](./CLI.md#using-hermes-agent-with-local-llms-via-lm-studio), [Kimi Code](./CLI.md#using-kimi-code-with-local-llms-via-lm-studio), and [DeepSeek Harness](./CLI.md#using-deepseek-harness-with-local-llms-via-lm-studio) — every one of those already supports multiple named custom providers, so "small model for quick edits/planning, big model for the hard reasoning step" is a config choice, not new infrastructure. Which task goes where is then either a manual `/model` switch, or — for harnesses with an architect/planner + editor split (Aider's `--architect`, Claude Code subagents, Hermes Agent's multi-agent orchestration) — an automatic routing decision the harness already makes for you.

### Option 3: Model Sharding Across Both Machines (llama.cpp RPC)

If you specifically want to pool memory across both machines to run *one* model too big for 128GB alone (rare, but real for some MoE flagships), two tools can theoretically do this — but only one is actually ready today:

- **EXO** (already covered in [RUNTIMES.md — EXO Installation](./RUNTIMES.md#exo-installation)) is built exactly for this and would be the more elegant choice — *except* its Linux backend is still CPU-only as of this writing (no ROCm/Vulkan support upstream; [tracking issue](https://github.com/exo-explore/exo/issues/434)). Combining a Mac (Metal, fast) with a Strix Halo node running EXO on CPU alone would bottleneck badly and waste the iGPU entirely. Don't use EXO for this pairing yet.
- **llama.cpp's RPC backend** is mature, ROCm-compatible, and the practical choice today. It treats a remote machine as just another device you hand a slice of layers to.

**On the Strix Halo box** (build with `-DGGML_RPC=ON` added to the [existing build command](#building-llamacpp-for-gfx1151)):
```bash
cmake -B build -G Ninja \
  -DGGML_HIP=ON -DAMDGPU_TARGETS=gfx1151 -DGGML_RPC=ON \
  -DCMAKE_BUILD_TYPE=Release -DGGML_HIP_ROCWMMA_FATTN=ON -DLLAMA_CURL=ON
cmake --build build -j --target llama-server rpc-server
```

**On the Mac Mini** (needs the *identical* llama.cpp version/tag — mismatched builds hang at the handshake):
```bash
xcode-select --install
brew install cmake
git clone https://github.com/ggml-org/llama.cpp && cd llama.cpp
git checkout <same-tag-as-strix-halo>
cmake -B build -DGGML_RPC=ON -DGGML_METAL=ON -DCMAKE_BUILD_TYPE=Release
cmake --build build --target rpc-server -j
```
Then start the Mac as an RPC worker (16GB machine, so advertise conservatively):
```bash
./build/bin/rpc-server -H 0.0.0.0 -p 50052 -m 12000
```
And drive it from Strix Halo as the primary node:
```bash
./build/bin/llama-server \
  -m /path/to/model-too-big-for-128GB-alone.gguf \
  --rpc <mac-mini-ip>:50052 \
  -ngl 99 --tensor-split 90,12 \
  --host 0.0.0.0 --port 8080
```
`--tensor-split 90,12` weights the layer split roughly by each machine's free memory (adjust to taste — it does not need to be exact).

**Before you do this, know the tradeoffs:**
- **`rpc-server` has no authentication.** Only run this on a trusted private network, or tunnel it over Tailscale/WireGuard.
- **Network quality dominates.** Under ~5ms latency on wired gigabit, overhead is barely noticeable; WiFi has been measured dropping throughput from ~20 tok/s to ~2 tok/s on the same setup. Use Ethernet.
- **RPC turns "can't run this at all" into "can run this, slowly"** — it does not turn slow into fast, and generation speed bottlenecks at the slowest link in the chain. Given Strix Halo alone already covers most models worth running locally (see the table above), reach for this only when you have a specific model that genuinely doesn't fit in 128GB — for everyday use, [Option 1](#option-1-more-agents-no-extra-tooling-recommended-starting-point) or [Option 2](#option-2-asymmetric-task-routing-small-model-on-the-mac-big-model-on-strix-halo) will serve you better.

## vLLM on ROCm (for Concurrent/Throughput Workloads)

llama.cpp is the right tool for a single interactive session. Once you want to serve multiple concurrent requests efficiently, vLLM is the better engine — and current ROCm support for gfx1151 in vLLM is newer/less stable than llama.cpp's, so the practical path is a prebuilt container rather than fighting the host package manager:

```bash
# Requires podman + toolbox
sudo apt install git podman toolbox   # or: sudo dnf install git toolbox

git clone https://github.com/kyuz0/amd-strix-halo-vllm-toolboxes.git
cd amd-strix-halo-vllm-toolboxes/
./refresh_toolbox.sh

toolbox enter vllm
```

Before entering the toolbox, add the GRUB parameters from [Sizing the GTT Aperture](#sizing-the-gtt-aperture-for-128gb) above if you haven't already — vLLM needs the larger GTT aperture exposed the same way llama.cpp does.

```bash
vllm serve Qwen/Qwen3.6-35B-A3B \
  --host 0.0.0.0 --port 8000 \
  --tensor-parallel-size 1 \
  --max-num-seqs 1 \
  --max-model-len 32768 \
  --gpu-memory-utilization 0.90 \
  --dtype auto \
  --trust-remote-code \
  --attention-backend TRITON_ATTN
```

Notes:
- `--gpu-memory-utilization 0.90` — a bit more conservative than the container's own default (0.95); this matches the 128GB reference systems these container images were built against, so 0.95 is also fine to try once you've confirmed stability at 0.90.
- First request after starting the server is slow (Triton kernel compilation); subsequent requests use the cache at `~/.cache/vllm/`.
- This exposes the same OpenAI-compatible `/v1/chat/completions` API as everything else in [RUNTIMES.md](./RUNTIMES.md) — point any tool from [CLI.md](./CLI.md) at `http://localhost:8000/v1`.

## Troubleshooting and Gotchas

- **`rocm-smi` shows ~1 GiB VRAM and you're worried the setup is broken.** It isn't — see [The Unified Memory Model](#the-unified-memory-model-read-this-first). Check `amdgpu.gttsize` instead.
- **The box hangs/spins instead of erroring on an out-of-memory load.** This is a known Strix Halo/KFD-driver failure mode when the GTT aperture is exceeded — there's no graceful spillover. Use `--no-mmap`, pick a smaller quant or shorter context, and consider a cgroup memory budget if you're running multiple models.
- **Don't launch `llama-server`/`vllm serve` in a detached/backgrounded shell you then close.** Both guides sourced for this document explicitly warn this creates an orphaned process that still owns the port, GPU memory, and model state — use a proper systemd unit or a terminal multiplexer (tmux/screen) instead.
- **GPU resets on experimental backends.** If the GPU resets, find and kill the actual owning process before restarting anything else — check `ps -eo pid,ppid,lstart,cmd --forest`, `ss -ltnp`, and `journalctl -k --since '-15 minutes' | grep -iE 'amdgpu|reset|fault'`.
- **A newer ROCm point release "breaks" gfx1151.** Pin the version in [ROCm Installation](#rocm-installation) above; don't blanket-upgrade ROCm packages without checking gfx1151 support for the specific new version first.
- **Quantized V-cache (`--cache-type-v q8_0`) refuses to start.** It requires `--flash-attn on` — add it.

## Sources

- [RepoCad — quantization deep-dive (YouTube)](https://www.youtube.com/watch?v=vW0KY_8z4q0&list=PLIVW7clnv28ov8Dg_4JKH0oXNRM5jwGI1&index=16) — the numeric-representation/algorithm/container/kernel framework and the KV-cache math in this document are drawn from and cross-checked against this video.
- [Gygeek/Framework-strix-halo-llm-setup](https://github.com/Gygeek/Framework-strix-halo-llm-setup) — BIOS/kernel/ROCm/llama.cpp setup for a 128GB Strix Halo box.
- [LucRoot/Strix-Halo-Linux-Llama_cpp-ROCm](https://github.com/LucRoot/Strix-Halo-Linux-Llama_cpp-ROCm) — pinned ROCm version, build flags, multi-model systemd fleet, KV-cache sizing.
- [soothill.io — ROCm on Strix Halo: setup and recovery](https://www.soothill.io/blog/2026/08/03/rocm-on-strix-halo-without-folklore/) — unified-memory model explanation, service-ownership and GPU-reset gotchas.
- [kyuz0/amd-strix-halo-vllm-toolboxes](https://github.com/kyuz0/amd-strix-halo-vllm-toolboxes) and [blog.jreb.nl — vLLM on Ryzen AI Max+ 395](https://blog.jreb.nl/2026/04/16/setupv-llmamdryzen-aimax/) — vLLM-on-ROCm container setup.
- [Unsloth — Dynamic 3.0 GGUFs](https://unsloth.ai/docs/basics/dynamic-3.0-ggufs) and [Qwen3.8 — How to Run Locally](https://unsloth.ai/docs/models/qwen3.8) — dynamic quantization methodology and Qwen3.8-27B specifics.
- [PrismML — Bonsai 27B](https://prismml.com/news/bonsai-27b) — ternary/1-bit Qwen3.6-27B builds.
- [sharedllm.org — llama.cpp RPC backend: distributed inference across multiple machines](https://sharedllm.org/blog/llama-cpp-rpc-distributed-inference.html) and [Splitting Llama across two MacBook Pros with llama.cpp RPC](https://sharedllm.org/blog/llama-cpp-rpc-two-macs.html) — RPC backend setup, flags, and measured network-latency impact.
- [exo-explore/exo issue #434 — ROCm support planned](https://github.com/exo-explore/exo/issues/434) — current status of EXO's (lack of) Linux GPU backend.

This document was assembled from the above sources; none of it was independently benchmarked on the author's own hardware — treat the specific numbers (throughput, exact GTT sizing) as a well-sourced starting point to verify on your own box, not a guarantee.
