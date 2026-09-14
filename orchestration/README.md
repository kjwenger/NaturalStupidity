# Three-machine local LLM stack — setup order

For the reasoning behind these files — verified upstream sourcing, the CUDA-13/Pascal and Flash-Attention caveats for the GTX 1060 route, and how far Hermes' auxiliary slots and DSH's subagent delegation actually go today — see [../ORCHESTRATION.md](../ORCHESTRATION.md#a-ready-to-run-starter-bundle). That document also flags two things to fix here before a first run: drop `-fa` from the GS63/GTX-1060 `llama-server` command below (that card doesn't support Flash Attention), and double-check the `auxiliary.*` key names in `hermes_config_snippet.yaml` against whatever Hermes version you have installed.

Files in this bundle:
- `systemd/llama-server.service` — supervised-service template for Strix Halo and the GS63 (both Linux)
- `launchd/com.local.mlx-server.plist` — supervised-service template for the Mac Mini
- `fleet.sh` — SSH fan-out script to start/stop/restart/check all three once the above are installed
- `litellm_config.yaml` — unifying router in front of all three machines
- `hermes_config_snippet.yaml` — merge into `~/.hermes/config.yaml`
- `dsh_settings_snippet.yaml` — merge into `~/.dsh/settings.yaml`

Replace every hostname/port/model-id placeholder with your real values before
starting. Get exact model ids by curling each server's `/v1/models` once it's up.

## 1. Start a model server on each machine

**For anything beyond a quick manual test**, install these as supervised services instead of running the raw commands below in a terminal you might close — use `systemd/llama-server.service` (Strix Halo, GS63) and `launchd/com.local.mlx-server.plist` (Mac Mini), then `fleet.sh` to control all three at once. See [../ORCHESTRATION.md#starting-and-supervising-the-fleet](../ORCHESTRATION.md#starting-and-supervising-the-fleet) for the full reasoning and install steps. The raw commands below are still useful for a first-time smoke test before wrapping them in a service file.

**Strix Halo (128GB unified) — heavy model, llama.cpp with Vulkan (or ROCm if
your build supports it):**
```bash
./llama-server --jinja -fa -c 65536 -ngl 99 \
  -m models/qwen2.5-72b-instruct-Q4_K_M.gguf \
  --host 0.0.0.0 --port 8080
```
`--jinja` is required — without it, tool calls come back as plain text instead
of real tool calls, which breaks both Hermes and DSH.

**Mac Mini M4 (16GB) — mid model, MLX for best memory efficiency:**
```bash
pip install mlx-lm
mlx_lm.server --model mlx-community/Qwen2.5-14B-Instruct-4bit \
  --host 0.0.0.0 --port 8080
```
(llama.cpp with Metal also works here if you'd rather keep one server type
across all three machines — just point at a GGUF instead.)

**MSI laptop (GTX 1060, 6GB) — small model, llama.cpp with partial GPU offload:**
```bash
./llama-server --jinja -c 16384 -ngl 20 \
  -m models/llama-3.1-8b-instruct-Q4_K_M.gguf \
  --host 0.0.0.0 --port 8080
```
`-ngl 20` (not 99) — 6GB VRAM can't hold all layers of an 8B model; tune this
down further if you see OOM, or up if you have headroom. No `-fa` here —
unlike the other two machines, this card's compute capability (6.1) is below
what Flash Attention requires (≥7.5); see [../ORCHESTRATION.md](../ORCHESTRATION.md#no-flash-attention-on-this-card).

Verify each server before moving on:
```bash
curl http://<host>:<port>/v1/models
```

## 2. Start the LiteLLM router

```bash
pip install "litellm[proxy]"
litellm --config litellm_config.yaml --port 4000
```
This gives you one endpoint (`http://localhost:4000/v1`) with automatic
fallback if the Strix Halo box is busy or offline.

## 3. Point Hermes at it

```bash
hermes model
# Select "Custom endpoint (self-hosted / VLLM / etc.)"
# URL: http://localhost:4000/v1, key: sk-local-anything (or your master_key)
```
Then merge `hermes_config_snippet.yaml`'s `providers:`, `auxiliary:`, and
`fallback_providers:` sections into `~/.hermes/config.yaml` by hand for the
per-machine routing (titles/compression on the laptop or Mac Mini, main loop
on Strix Halo).

## 4. Point DeepSeek Harness at it

```bash
mkdir -p ~/.dsh
# merge dsh_settings_snippet.yaml into ~/.dsh/settings.yaml
export STRIX_API_KEY=local MAC_API_KEY=local LAPTOP_API_KEY=local
dsh --profile headless "reply with the word ready"   # smoke test
```
Settings → Models in the DSH web UI will show all three routes with a
"Custom" badge and a green status dot once credentials resolve.

## 5. Claude Code

Claude Code expects Anthropic's Messages API shape, not OpenAI's — point it
at a translation proxy (e.g. an open-source "claude-code-proxy") sitting in
front of the LiteLLM endpoint, rather than at LiteLLM directly.

## Gotchas worth knowing up front
- **Context length**: Ollama silently caps context by VRAM tier (as low as
  4,096 tokens) unless you set `OLLAMA_CONTEXT_LENGTH` explicitly — both
  Hermes and DSH need at least ~16-64k for agent use with tools.
- **Tool calling**: llama.cpp needs `--jinja`; vLLM needs
  `--enable-auto-tool-choice --tool-call-parser <model-specific-name>` or
  every agent turn fails with a 400.
- **Shared GPU conflicts**: if you ever run two servers on the same GPU
  (e.g. testing Ollama and vLLM together on one box), unload one before
  starting the other — they don't share VRAM gracefully.
