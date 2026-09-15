#!/usr/bin/env bash
# scripts/bridge-lmstudio-to-hf-cache.sh
#
# Adopts every model already downloaded by LM Studio (~/.lmstudio/models/
# <publisher>/<repo>/<file>) into the Hugging Face Hub's own cache layout
# (~/.cache/huggingface/hub/models--<publisher>--<repo>/{blobs,refs,snapshots}),
# WITHOUT copying or re-downloading anything — blobs are symlinked straight
# back to the real LM Studio files.
#
# Why: neither `hf cache ls` nor Magnitude's documented "discovers GGUF
# packages already sitting in your Hugging Face Hub cache" behavior looks at
# LM Studio's flat <publisher>/<repo>/<file> layout — only the Hub's own
# content-addressed cache layout. This script builds that layout so both
# tools (and any future `hf download`/`hf_hub_download`/`snapshot_download`
# call for the same repo+revision) recognize these files as already present.
#
# See PREREQUISITES.md#hugging-face-hub-cli-hf for the full explanation and
# caveats — this hand-constructs cache internals that huggingface_hub's own
# docs describe as implementation detail, not an officially supported "adopt
# an external file" API. It works against the documented layout as of this
# writing; it is not guaranteed stable across future huggingface_hub versions.
#
# Usage:
#   ./bridge-lmstudio-to-hf-cache.sh [--dry-run] [--force]
#
#   --dry-run   Print what would be done without creating/linking anything.
#   --force     Re-bridge a repo even if a cache entry for it already exists
#               (e.g. after LM Studio re-downloaded a different revision).
#
# Requirements: python3 with `huggingface_hub` importable (pip install
# huggingface_hub, or it comes with the `hf` CLI's pip install path — see
# PREREQUISITES.md). Falls back to `uvx --from huggingface_hub` if a plain
# `python3 -c` import fails and `uv` is installed.

set -euo pipefail

LMSTUDIO_MODELS="${LMSTUDIO_MODELS:-$HOME/.lmstudio/models}"
HF_CACHE_ROOT="${HF_HOME:-$HOME/.cache/huggingface}/hub"

DRY_RUN=false
FORCE=false
for arg in "$@"; do
  case "$arg" in
    --dry-run) DRY_RUN=true ;;
    --force) FORCE=true ;;
    *) echo "Unknown argument: $arg" >&2; exit 1 ;;
  esac
done

if [ ! -d "$LMSTUDIO_MODELS" ]; then
  echo "No LM Studio models directory found at $LMSTUDIO_MODELS — nothing to do." >&2
  exit 0
fi

# --- sha256 helper: prefer sha256sum (Linux/coreutils), fall back to shasum -a 256 (macOS default) ---
sha256_of() {
  if command -v sha256sum >/dev/null 2>&1; then
    sha256sum "$1" | cut -d' ' -f1
  else
    shasum -a 256 "$1" | cut -d' ' -f1
  fi
}

# --- Resolve the real current commit hash for a repo's "main" branch, so a
# future hf_hub_download()/snapshot_download() call for this repo at that
# revision recognizes our bridged entry as already cached instead of
# re-fetching. Requires network access (one Hub API call per repo). ---
resolve_main_commit() {
  local repo_id="$1"
  local py_snippet="from huggingface_hub import resolve_revision; print(resolve_revision('$repo_id').resolved)"
  if python3 -c "import huggingface_hub" >/dev/null 2>&1; then
    python3 -c "$py_snippet" 2>/dev/null
  elif command -v uvx >/dev/null 2>&1; then
    uvx --from huggingface_hub python -c "$py_snippet" 2>/dev/null
  else
    echo "" # signal failure to the caller
  fi
}

bridged=0
skipped=0
failed=0

# Walk <publisher>/<repo>/ directories two levels deep under LM Studio's models folder.
for publisher_dir in "$LMSTUDIO_MODELS"/*/; do
  [ -d "$publisher_dir" ] || continue
  publisher="$(basename "$publisher_dir")"

  for repo_dir_src in "$publisher_dir"*/; do
    [ -d "$repo_dir_src" ] || continue
    repo="$(basename "$repo_dir_src")"
    repo_id="$publisher/$repo"

    # Collect every file directly inside this repo folder (the .gguf plus any
    # sidecar file such as an mmproj-*.gguf for vision models).
    files=()
    while IFS= read -r -d '' f; do files+=("$f"); done < <(find "$repo_dir_src" -maxdepth 1 -type f -print0)
    if [ "${#files[@]}" -eq 0 ]; then
      continue
    fi

    cache_repo_dir="$HF_CACHE_ROOT/models--${publisher}--${repo}"

    if [ -d "$cache_repo_dir" ] && [ "$FORCE" = false ]; then
      echo "skip (already bridged): $repo_id"
      skipped=$((skipped + 1))
      continue
    fi

    echo "bridging: $repo_id (${#files[@]} file(s))"

    if [ "$DRY_RUN" = true ]; then
      for f in "${files[@]}"; do
        echo "  would link: $(basename "$f")"
      done
      continue
    fi

    # `|| true`: under `set -e`, a failed resolve (unresolvable repo, no
    # network) would otherwise abort the whole script instead of just
    # skipping this one repo — the empty-string check right below handles it.
    hash="$(resolve_main_commit "$repo_id" || true)"
    if [ -z "$hash" ]; then
      echo "  WARNING: could not resolve '$repo_id' on the Hub (private, renamed, or no network) — skipping." >&2
      failed=$((failed + 1))
      continue
    fi

    mkdir -p "$cache_repo_dir/blobs" "$cache_repo_dir/refs" "$cache_repo_dir/snapshots/$hash"
    # printf, not echo: a trailing newline in refs/main makes huggingface_hub's
    # own cache scanner report the reference as pointing at a "missing" commit
    # (it does not trim whitespace when matching against the snapshot dir name).
    printf '%s' "$hash" > "$cache_repo_dir/refs/main"

    for f in "${files[@]}"; do
      fname="$(basename "$f")"
      blob_hash="$(sha256_of "$f")"
      # Symlink the blob straight to the real LM Studio file — no copying.
      ln -sf "$f" "$cache_repo_dir/blobs/$blob_hash"
      ln -sf "../../blobs/$blob_hash" "$cache_repo_dir/snapshots/$hash/$fname"
      echo "  linked: $fname"
    done
    bridged=$((bridged + 1))
  done
done

echo ""
echo "Done. Bridged: $bridged, skipped (already present): $skipped, failed: $failed."
if [ "$DRY_RUN" = true ]; then
  echo "(dry run — nothing was actually created)"
else
  echo "Verify with: hf cache ls"
  echo "Then check Magnitude picks them up with: magnitude catalog list"
fi
