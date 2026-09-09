# Mac Mini M4: 16GB Local LLM Setup

<!-- TOC -->
* [Mac Mini M4: 16GB Local LLM Setup](#mac-mini-m4-16gb-local-llm-setup)
  * [The Unified Memory Model (macOS)](#the-unified-memory-model-macos)
  * [Raising the GPU Memory Ceiling](#raising-the-gpu-memory-ceiling)
    * [Sizing for 16GB](#sizing-for-16gb)
    * [Making It Persistent](#making-it-persistent)
  * [Installing MLX](#installing-mlx)
  * [Running a Model](#running-a-model)
  * [Recommended Models for a 16GB Mac Mini](#recommended-models-for-a-16gb-mac-mini)
  * [Making the Mac Mini Available to Harnesses](#making-the-mac-mini-available-to-harnesses)
  * [Working with BosGameM5 (Strix Halo)](#working-with-bosgamem5-strix-halo)
  * [Alternative Runtimes: Ollama and LM Studio](#alternative-runtimes-ollama-and-lm-studio)
  * [Troubleshooting and Gotchas](#troubleshooting-and-gotchas)
  * [Sources](#sources)
<!-- TOC -->

This is a hardware-specific companion to [RUNTIMES.md](./RUNTIMES.md) (general local LLM runtimes) and [STRIX-HALO.md](./STRIX-HALO.md) (this repo's other machine, an AMD Ryzen AI Max+ "Strix Halo" box with 128GB unified memory). This one covers a **Mac Mini M4 with 16GB of unified memory** — a much smaller, much simpler machine than Strix Halo, best used as a fast small-model companion rather than a standalone heavy-lifting box. See [Working with BosGameM5](#working-with-bosgamem5-strix-halo) below for how the two fit together.

## The Unified Memory Model (macOS)

Apple Silicon shares one memory pool between CPU and GPU, the same way Strix Halo does — but macOS's version of this is far less involved than Strix Halo's BIOS/GRUB dance. There's no BIOS setting to fight, no kernel boot parameter, no reboot required to change the GPU's memory ceiling. macOS reserves a fraction of total RAM for the GPU automatically (by default, roughly 75% of total RAM is available to the GPU as "wired" memory), and that ceiling can be raised with a single runtime `sysctl` command — see [Raising the GPU Memory Ceiling](#raising-the-gpu-memory-ceiling) below.

On a 16GB machine, macOS's default (~12GB) is already generally sufficient for the model sizes this machine is realistically good for (see [Recommended Models](#recommended-models-for-a-16gb-mac-mini)) — you may not need to touch this at all. It's documented here mainly for completeness and for the rare case you want a bit more headroom for a slightly larger model or longer context.

## Raising the GPU Memory Ceiling

```bash
sudo sysctl iogpu.wired_limit_mb=<MB>
```
No reboot required — this takes effect immediately, and this is the one genuinely "dynamic, no restart needed" memory tweak in either machine's setup (Strix Halo's equivalent, `ttm.pages_limit`, needs a reboot — see [STRIX-HALO.md](./STRIX-HALO.md#kernel--grub-configuration)).

Revert to Apple's default at any time:
```bash
sudo sysctl iogpu.wired_limit_mb=0
```

**Note:** this is an unsupported/undocumented macOS tweak, not an Apple-published API — it's worked consistently across recent macOS versions but could change behavior in a future update.

### Sizing for 16GB

macOS's own default already grants the GPU roughly 75% of total RAM (~12GB on a 16GB Mac). Published guidance for larger Macs recommends leaving 8-16GB of headroom for the OS — that doesn't scale down linearly to a 16GB machine (you'd have nothing left for the GPU at all), so here the practical range is much narrower:

| Profile | GPU allocation | Host headroom | `iogpu.wired_limit_mb` |
|---|---|---|---|
| Default (no tuning needed) | ~12 GB | ~4 GB | *(leave unset)* |
| Balanced | 13 GB | 3 GB | `13312` |
| Dedicated (this Mac does little else) | 14 GB | 2 GB | `14336` |

Given the [recommended models](#recommended-models-for-a-16gb-mac-mini) for this machine run comfortably inside the default ~12GB, **most people won't need to change this at all** — reach for Balanced or Dedicated only if you want extra room for a longer context window.

### Making It Persistent

The `sysctl` command above only lasts until reboot. macOS's `/etc/sysctl.conf` is not reliably honored by modern launchd-based boot (older guides that suggest it may not actually work, and some report needing SIP changes to even edit it) — a `LaunchDaemon` that runs the command at boot is the more reliable approach:

```bash
sudo tee /Library/LaunchDaemons/com.local.iogpu-wired-limit.plist > /dev/null <<'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
    <key>Label</key>
    <string>com.local.iogpu-wired-limit</string>
    <key>ProgramArguments</key>
    <array>
        <string>/usr/sbin/sysctl</string>
        <string>iogpu.wired_limit_mb=13312</string>
    </array>
    <key>RunAtLoad</key>
    <true/>
</dict>
</plist>
EOF

sudo launchctl load /Library/LaunchDaemons/com.local.iogpu-wired-limit.plist
```
Adjust `13312` to your chosen value from the table above. Given most users won't need this at all on a 16GB Mac, treat this as optional.

## Installing MLX

MLX is Apple's own array framework, and `mlx-lm` the companion package for running LLMs — see [RUNTIMES.md — MLX Installation](./RUNTIMES.md#mlx-installation-macos-only) for the full install steps (pip/conda/Homebrew), requirements (macOS 14+, Python 3.10+ native ARM), and general usage. Nothing about installation is different on a 16GB machine versus a larger Mac; the difference is purely in which models fit.

## Running a Model

```bash
mlx_lm.server --model mlx-community/Qwen3.5-9B-4bit \
    --host 0.0.0.0 --port 8080 --max-tokens 4096
```
See [Making the Mac Mini Available to Harnesses](#making-the-mac-mini-available-to-harnesses) below before binding to `0.0.0.0` on a network you don't fully trust — mlx-lm's server is explicitly documented as a development server, not a production/authenticated one.

## Recommended Models for a 16GB Mac Mini

Keep total model footprint at roughly 60% of unified memory (~9-10GB) to leave safe room for macOS, an editor, a browser, and the KV cache during longer agent loops — this machine runs *up to* ~13B parameters at 4-bit without complaint, but the sweet spot for agentic/coding use is smaller:

| Model | Size loaded (approx.) | Notes |
|---|---|---|
| `mlx-community/Qwen3.5-9B-4bit` | ~5.6 GB | Widely recommended default for this RAM tier; ~17-22 tok/s on M4. |
| Ornith 1.0 9B (MLX build) | ~5.6 GB | Purpose-built for agentic coding (multi-step planning, tool calls, state across a task); similar footprint and throughput to Qwen3.5-9B — worth comparing directly against it for your workflow. |
| A 7-8B class model (Q4) | ~4-5 GB | Fastest option (~28-35 tok/s reported), if you want more headroom for context/other apps over raw capability. |

**What doesn't fit:** the [Qwen3.8-27B](./STRIX-HALO.md#recommended-models-for-a-128gb-strix-halo) model this repo recommends for the Strix Halo box needs far more than 16GB even at a 4-bit quant — this Mac is not the machine for that model. That asymmetry is exactly the point; see [Working with BosGameM5](#working-with-bosgamem5-strix-halo) below.

## Making the Mac Mini Available to Harnesses

Once `mlx_lm.server` (or Ollama/LM Studio, see [below](#alternative-runtimes-ollama-and-lm-studio)) is running and bound to `0.0.0.0`, point any harness from [CLI.md](./CLI.md) at it from another machine on the network:
```bash
export OPENAI_API_BASE=http://<mac-mini-ip>:8080/v1
export OPENAI_API_KEY=mlx   # required by some tools but ignored by mlx-lm
```

**Before binding beyond `127.0.0.1`, be aware:** mlx-lm's server has no built-in authentication — anyone who can reach the port can use it. On a private home LAN this is usually an acceptable risk (the same way it's an acceptable risk for `llama-server`/Ollama on the Strix Halo box), but:
- Confirm your Mac's firewall (System Settings → Network → Firewall) isn't wide open to more than your LAN.
- For access beyond your LAN (e.g. reaching it while away from home), use a VPN like Tailscale or WireGuard rather than port-forwarding the raw endpoint to the internet.
- LM Studio's own server has an actual [authentication option](https://lmstudio.ai/docs/developer/core/authentication) if you want real auth rather than just network isolation — see [Alternative Runtimes](#alternative-runtimes-ollama-and-lm-studio) below.

## Working with BosGameM5 (Strix Halo)

The full breakdown of how to combine this Mac with the Strix Halo box lives in **[STRIX-HALO.md — Combining with a Second Machine](./STRIX-HALO.md#combining-with-a-second-machine-eg-a-mac-mini)**, written from that side; the short version from this Mac's perspective:

- **[Option 1 — More agents, no extra tooling](./STRIX-HALO.md#option-1-more-agents-no-extra-tooling-recommended-starting-point):** run this Mac's `mlx_lm.server` as its own independent endpoint (as set up above) and point a second harness instance at it — you now have two machines' worth of concurrent agent capacity for free.
- **[Option 2 — Asymmetric task routing](./STRIX-HALO.md#option-2-asymmetric-task-routing-small-model-on-the-mac-big-model-on-strix-halo):** the recommended way to use this Mac's role deliberately — this Mac serves a fast small model (see the table above) for quick edits/planning, Strix Halo serves the heavy-lifting model, both registered as named providers in the same harness config.
- **[Option 3 — llama.cpp RPC model sharding](./STRIX-HALO.md#option-3-model-sharding-across-both-machines-llamacpp-rpc):** pooling this Mac's 16GB into a single model spanning both machines. Rarely worth it given how little 16GB adds to Strix Halo's 128GB, but the build/run commands (including the Mac's `-DGGML_METAL=ON` side) are documented there.

## Alternative Runtimes: Ollama and LM Studio

Both work identically on a Mac Mini as on any other machine — see [RUNTIMES.md](./RUNTIMES.md) for full install/usage instructions ([Ollama](./RUNTIMES.md#ollama-installation), [LM Studio](./RUNTIMES.md#lm-studio-installation)). Two Mac-specific notes worth calling out:

- **LM Studio** has a real **Serve on Local Network** toggle in Settings, plus an actual authentication option — likely the easiest, safest path to exposing a model on your LAN if you don't want to deal with `mlx_lm.server`'s lack of auth:
  ```bash
  lms server start --bind 0.0.0.0
  ```
  Enable authentication in Settings before relying on this beyond a fully trusted LAN.
- **Ollama** on macOS runs as a native background service the same as on Linux, with the same `OLLAMA_HOST=0.0.0.0` pattern for LAN exposure — see [RUNTIMES.md — OpenAI-Compatible API (Ollama)](./RUNTIMES.md#pulling-and-running-models-ollama).

MLX generally edges out both in raw throughput on Apple Silicon (Apple's own framework, no translation layer), but Ollama/LM Studio's GUI and simpler model management may be worth the small performance gap for a machine you're not optimizing to the last token/sec.

## Troubleshooting and Gotchas

- **A 9B model loads fine but the machine feels sluggish under a long agent session.** You're likely hitting memory pressure from KV cache growth over a long conversation, not the model weights themselves — reduce `--max-tokens`/context length, or see [Raising the GPU Memory Ceiling](#raising-the-gpu-memory-ceiling) for a small bump.
- **Server refuses connections from another machine.** Confirm you started it with `--host 0.0.0.0` (mlx-lm), `--bind 0.0.0.0` (LM Studio), or `OLLAMA_HOST=0.0.0.0` (Ollama) — all three default to localhost-only.
- **`iogpu.wired_limit_mb` change disappeared after a restart.** Expected — it's a runtime-only setting; see [Making It Persistent](#making-it-persistent).
- **Trying to load the same big model recommended for Strix Halo.** It won't fit — see [What doesn't fit](#recommended-models-for-a-16gb-mac-mini) above. This machine's role is the small/fast side of the pairing, not a scaled-down clone of the Strix Halo setup.

## Sources

- [ivanopcode/devnote-override-macos-metal-vram-cap](https://github.com/ivanopcode/devnote-override-macos-metal-vram-cap) — `iogpu.wired_limit_mb` command, persistence caveats, safe headroom guidance.
- [modelpiper.com — iogpu.wired_limit_mb on Mac](https://modelpiper.com/blog/iogpu-wired-limit-mb-mac) and [Peddals Blog — Optimizing VRAM Settings for Local LLM on macOS](https://blog.peddals.com/en/fine-tune-vram-size-of-mac-for-llm/) — cross-checks on default GPU memory percentage and tuning approach.
- [LM Studio — Serve on Local Network](https://lmstudio.ai/docs/developer/core/server/serve-on-network) and [LM Studio — Authentication](https://lmstudio.ai/docs/developer/core/authentication) — `lms server start --bind`, and the auth option mlx-lm's server lacks.
- [analyticsvidhya.com — 5 Best Local LLMs You Can Run on a Mac mini in 2026](https://www.analyticsvidhya.com/blog/2026/09/best-local-llms-mac-mini-2026/) and [mayhemcode.com — Best Local LLM Setup for Mac Mini M4 16GB](https://www.mayhemcode.com/2026/07/best-local-llm-setup-for-mac-mini-m4.html) — 16GB-tier model recommendations, throughput figures, and the 60%-of-RAM footprint guideline.

This document was assembled from the above sources; none of it was independently benchmarked on the author's own hardware — treat the specific numbers (throughput, exact wired-limit sizing) as a well-sourced starting point to verify on your own machine, not a guarantee.
