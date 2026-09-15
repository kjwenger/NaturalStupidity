# scripts/bridge-lmstudio-to-unsloth.ps1
#
# Windows equivalent of bridge-lmstudio-to-unsloth.sh — see that file and
# RUNTIMES.md's "Sharing Already-Downloaded LM Studio Models with Unsloth
# Studio" section for the full explanation.
#
# Uses directory junctions (mklink /J) rather than plain symlinks: plain
# symlinks are often blocked on Windows without Developer Mode or admin
# rights, junctions aren't.
#
# Usage:
#   .\bridge-lmstudio-to-unsloth.ps1 [-DryRun]

param(
    [switch]$DryRun
)

$LmStudioModels = if ($env:LMSTUDIO_MODELS) { $env:LMSTUDIO_MODELS } else { "$env:USERPROFILE\.lmstudio\models" }
$UnslothModels  = if ($env:UNSLOTH_MODELS)  { $env:UNSLOTH_MODELS }  else { "$env:USERPROFILE\.unsloth\studio\models" }

if (-not (Test-Path $LmStudioModels)) {
    Write-Warning "No LM Studio models directory found at $LmStudioModels — nothing to do."
    exit 0
}

New-Item -ItemType Directory -Force -Path $UnslothModels | Out-Null

$linked = 0
$skipped = 0

foreach ($pub in Get-ChildItem $LmStudioModels -Directory) {
    $target = Join-Path $UnslothModels $pub.Name

    if (Test-Path $target) {
        $item = Get-Item $target
        if ($item.LinkType -eq "Junction" -and $item.Target -contains $pub.FullName) {
            Write-Host "skip (already linked): $($pub.Name)"
            $skipped++
            continue
        } else {
            Write-Warning "$target already exists and is not a matching junction — leaving it alone, skipping $($pub.Name)."
            $skipped++
            continue
        }
    }

    Write-Host "linking: $($pub.Name)"
    if (-not $DryRun) {
        cmd /c mklink /J "$target" "$($pub.FullName)" | Out-Null
    }
    $linked++
}

Write-Host ""
Write-Host "Done. Linked: $linked, skipped: $skipped."
if ($DryRun) { Write-Host "(dry run - nothing was actually linked)" }
Write-Host "Note: GGUF models are inference-only in Unsloth Studio - they show up in the chat/inference model picker, not the Fine-tuned tab."
