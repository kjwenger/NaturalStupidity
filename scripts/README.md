# Scripts

Small, standalone scripts referenced from this repo's documentation — each one is explained in context where it's linked from, not just here.

- **`bridge-lmstudio-to-hf-cache.sh`** — adopts every model already downloaded by LM Studio into the Hugging Face Hub's own cache layout, without copying or re-downloading anything, so `hf cache ls` and Magnitude's HF-cache auto-discovery both recognize them. See [PREREQUISITES.md — Bridging Already-Downloaded LM Studio Models](../PREREQUISITES.md#bridging-already-downloaded-lm-studio-models-hf) for the full explanation and caveats.
- **`bridge-lmstudio-to-unsloth.sh`** (macOS/Linux) and **`bridge-lmstudio-to-unsloth.ps1`** (Windows) — symlinks (junctions on Windows) each LM Studio publisher folder into Unsloth Studio's model directory, since both use the same `<publisher>/<repo>/<file>.gguf` layout. See [RUNTIMES.md — Sharing Already-Downloaded LM Studio Models with Unsloth Studio](../RUNTIMES.md#sharing-already-downloaded-lm-studio-models-with-unsloth-studio).

All three scripts:
- Are read-only with respect to your LM Studio files — they only ever create symlinks/junctions and small metadata files elsewhere, never touch or move anything under `~/.lmstudio/models`.
- Support `--dry-run` (`-DryRun` for the PowerShell one) to preview what would happen first.
- Are idempotent — safe to re-run after LM Studio downloads something new; already-bridged/linked entries are skipped, not redone (pass `--force` to `bridge-lmstudio-to-hf-cache.sh` to re-bridge a repo anyway).
- Read the source LM Studio path from `$LMSTUDIO_MODELS` (`$env:LMSTUDIO_MODELS` on Windows) if set, defaulting to `~/.lmstudio/models`.

`bridge-lmstudio-to-hf-cache.sh` additionally needs `python3` with `huggingface_hub` importable, or `uv` installed as a fallback (`uvx --from huggingface_hub`) — it makes one Hub API call per repo to resolve the real current commit hash, so a future `hf download`/`hf_hub_download()`/`snapshot_download()` call for that same repo recognizes the bridged entry instead of re-fetching.
