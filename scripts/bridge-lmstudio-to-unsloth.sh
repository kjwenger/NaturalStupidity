#!/usr/bin/env bash
# scripts/bridge-lmstudio-to-unsloth.sh
#
# Makes every model already downloaded by LM Studio visible to Unsloth Studio,
# by symlinking each publisher folder across — see RUNTIMES.md's
# "Sharing Already-Downloaded LM Studio Models with Unsloth Studio" section
# for the full explanation. This works because both apps use the same
# on-disk layout (<publisher>/<repo>/<file>.gguf), unlike the Hugging Face
# Hub cache bridge in bridge-lmstudio-to-hf-cache.sh, which needs a real
# format conversion. A plain directory symlink is all that's needed here.
#
# New models LM Studio downloads later, under a publisher folder already
# linked here, show up in Unsloth Studio automatically with no re-run needed.
# Re-run this script only when LM Studio downloads from a brand-new publisher.
#
# Usage:
#   ./bridge-lmstudio-to-unsloth.sh [--dry-run]
#
# Windows: use bridge-lmstudio-to-unsloth.ps1 instead (plain symlinks are
# often blocked there without Developer Mode/admin rights; that script uses
# directory junctions instead, which aren't).

set -euo pipefail

LMSTUDIO_MODELS="${LMSTUDIO_MODELS:-$HOME/.lmstudio/models}"
UNSLOTH_MODELS="${UNSLOTH_MODELS:-$HOME/.unsloth/studio/models}"

DRY_RUN=false
for arg in "$@"; do
  case "$arg" in
    --dry-run) DRY_RUN=true ;;
    *) echo "Unknown argument: $arg" >&2; exit 1 ;;
  esac
done

if [ ! -d "$LMSTUDIO_MODELS" ]; then
  echo "No LM Studio models directory found at $LMSTUDIO_MODELS — nothing to do." >&2
  exit 0
fi

mkdir -p "$UNSLOTH_MODELS"

linked=0
skipped=0

for pub_dir in "$LMSTUDIO_MODELS"/*/; do
  [ -d "$pub_dir" ] || continue
  pub_name="$(basename "$pub_dir")"
  target="$UNSLOTH_MODELS/$pub_name"

  if [ -L "$target" ]; then
    # Already a symlink — check it points at the right place.
    current="$(readlink "$target")"
    if [ "$current" = "${pub_dir%/}" ]; then
      echo "skip (already linked): $pub_name"
      skipped=$((skipped + 1))
      continue
    else
      echo "re-linking (was pointing elsewhere): $pub_name"
      [ "$DRY_RUN" = true ] || rm "$target"
    fi
  elif [ -e "$target" ]; then
    echo "WARNING: $target already exists and is not a symlink — leaving it alone, skipping $pub_name." >&2
    skipped=$((skipped + 1))
    continue
  fi

  echo "linking: $pub_name"
  if [ "$DRY_RUN" = false ]; then
    ln -s "${pub_dir%/}" "$target"
  fi
  linked=$((linked + 1))
done

echo ""
echo "Done. Linked: $linked, skipped: $skipped."
[ "$DRY_RUN" = true ] && echo "(dry run — nothing was actually linked)"
echo "Note: GGUF models are inference-only in Unsloth Studio — they show up in the chat/inference model picker, not the Fine-tuned tab."
