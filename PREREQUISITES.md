# Prerequisites

<!-- TOC -->
* [Prerequisites](#prerequisites)
  * [Node.js Installation](#nodejs-installation)
    * [Linux](#linux)
      * [Using `curl` or `wget` (Recommended for fresh installations)](#using-curl-or-wget-recommended-for-fresh-installations)
    * [macOS](#macos)
      * [Using Homebrew (If Homebrew is already installed)](#using-homebrew-if-homebrew-is-already-installed)
    * [Windows](#windows)
    * [Using `nvm` to Install Node.js](#using-nvm-to-install-nodejs)
  * [pnpm](#pnpm)
  * [Bun](#bun)
  * [UV CLI](#uv-cli)
  * [Hugging Face Hub CLI (`hf`)](#hugging-face-hub-cli-hf)
    * [Authentication (hf)](#authentication-hf)
    * [Downloading Models Directly (hf)](#downloading-models-directly-hf)
    * [Discovering Models (hf)](#discovering-models-hf)
    * [Managing the Local Cache (hf)](#managing-the-local-cache-hf)
    * [Bridging Already-Downloaded LM Studio Models (hf)](#bridging-already-downloaded-lm-studio-models-hf)
    * [Connecting to Claude Code and Other Agents (hf)](#connecting-to-claude-code-and-other-agents-hf)
<!-- TOC -->

## Node.js Installation

To manage Node.js versions, it's recommended to install `nvm` (Node Version Manager).

### Linux

To install `nvm` on Linux or macOS, you have a couple of options:

#### Using `curl` or `wget` (Recommended for fresh installations)

You can use the following `curl` or `wget` commands:

```bash
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
```
or
```bash
wget -qO- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
```

### macOS

#### Using Homebrew (If Homebrew is already installed)

If you have Homebrew installed on macOS, you can install `nvm` using:

```bash
brew install nvm
```

After installing with Homebrew, you'll need to follow the instructions provided by Homebrew to set up `nvm` in your shell. This usually involves adding lines to your shell's profile file (e.g., `~/.bashrc`, `~/.zshrc`).

After installation (using either method), close and reopen your terminal, or source your shell's profile script (e.g., `source ~/.bashrc`, `source ~/.zshrc`).

### Windows

For Windows, it is recommended to use `nvm-windows`. You can download the latest installer from the [nvm-windows GitHub repository](https://github.com/coreybutler/nvm-windows/releases).

Follow the installation instructions provided on the GitHub page. After installation, you can use commands like:
```bash
nvm install latest
nvm use latest
```

### Using `nvm` to Install Node.js

After `nvm` is installed and configured, you can install Node.js versions.

To install the latest **LTS** (Long Term Support) version of Node.js and set it as default:
```bash
nvm install --lts
nvm alias default 'lts/*'
```

To install the **latest** available version of Node.js (which might not be LTS) and set it as default:
```bash
nvm install node
nvm alias default node
```

To use a specific installed version:
```bash
nvm use <version>
```

For example, to use Node.js version 18:
```bash
nvm use 18
```

## pnpm

`pnpm` is a fast, disk-space-efficient npm-compatible package manager, required (in place of plain `npm`) to build several tools covered in [CLI.md](./CLI.md) from source — [DeepSeek Harness](./CLI.md#deepseek-harness-cli) (`pnpm install && pnpm run build`) and Kimi Code's development setup among them.

**Install via Corepack (bundled with Node.js ≥ 16.13, recommended):**
```bash
corepack enable
corepack prepare pnpm@latest --activate
```

**Install using the standalone script (macOS/Linux):**
```bash
curl -fsSL https://get.pnpm.io/install.sh | sh -
```

**Install using Homebrew (macOS/Linux):**
```bash
brew install pnpm
```

**Install using npm:**
```bash
npm install -g pnpm
```

**Windows (PowerShell):**
```powershell
iwr https://get.pnpm.io/install.ps1 -useb | iex
```

Verify the install:
```bash
pnpm --version
```

For more information, visit [pnpm.io](https://pnpm.io/installation).

## Bun

Bun is an all-in-one JavaScript/TypeScript runtime, bundler, and package manager, offered as a faster alternative to `npm`/`npx` by several tools in [CLI.md](./CLI.md) — [Grok CLI](./CLI.md#grok-cli) (`bun add -g @vibe-kit/grok-cli`) and [Oh My Pi](./CLI.md#oh-my-pi-cli) (`bun install -g @oh-my-pi/pi-coding-agent`) both recommend it over npm.

**Install using the official script (macOS/Linux/WSL):**
```bash
curl -fsSL https://bun.sh/install | bash
```

**Install using Homebrew (macOS/Linux):**
```bash
brew install bun
```

**Install using npm:**
```bash
npm install -g bun
```

**Windows (PowerShell):**
```powershell
powershell -c "irm bun.sh/install.ps1 | iex"
```

Verify the install:
```bash
bun --version
```

Install a global CLI tool with Bun the same way you would with `npm install -g`:
```bash
bun add -g <package-name>
```

For more information, visit [bun.sh](https://bun.sh/docs/installation).

## UV CLI

UV is an extremely fast Python package installer, resolver, and virtual environment manager from Astral, the makers of Ruff. It is designed as a single, high-performance binary to replace `pip`, `pip-tools`, `pip-compile`, and `venv`. It can also be used to install and run Python CLI tools in isolated environments, similar to `pipx`.

**Install using installation script (macOS/Linux):**
```bash
curl -Lsf https://astral.sh/uv/install.sh | sh
```

**Install using pip or pipx:**
```bash
# Using pip to install into the current environment
pip install uv

# Using pipx for isolated installation (recommended)
pipx install uv
```

**Install using Homebrew (macOS/Linux):**
```bash
brew install uv
```

**Windows:**
```powershell
powershell -c "irm https://astral.sh/uv/install.ps1 | iex"
```

**Usage for installing tools:**

You can use `uv tool install` to install Python-based CLI tools, which will then be available on your `PATH`.

```bash
# Example: Installing Aider-CE using uv
uv tool install --native-tls --python python3.12 cecli-dev
```

**Note on Local LLMs:**

`uv` is a development tool and package manager; it does not connect to Large Language Models (LLMs) directly. You can use `uv` to install other AI CLI tools (like Aider-CE as shown above), and then configure those tools to use local LLMs according to their own documentation.

## Hugging Face Hub CLI (`hf`)

Nearly every tool in [RUNTIMES.md](./RUNTIMES.md) pulls models from the Hugging Face Hub under the hood (LM Studio, Ollama, MLX, llama.cpp's `-hf` shorthand, Unsloth, [Magnitude](./RUNTIMES.md#magnitude-installation)) — but none of them expose the Hub's own CLI directly. `hf` (the successor to the older `huggingface-cli` name, shipped by the `huggingface_hub` Python package) is worth having installed on its own for three things this repo's other tools don't cover: authenticating once for every gated repo, downloading or filtering a specific file/quant without going through a runtime's own UI, and managing the shared local cache that all of the above tools read from and write to.

**Standalone installer (recommended — also installs an agent Skill, see [below](#connecting-to-claude-code-and-other-agents-hf)):**
```bash
# macOS/Linux
curl -LsSf https://hf.co/cli/install.sh | bash

# Windows
powershell -ExecutionPolicy ByPass -c "irm https://hf.co/cli/install.ps1 | iex"
```

**No install (uvx, always latest, isolated):**
```bash
uvx hf auth login
uvx hf download ...
```

**pip (ships with the core package):**
```bash
pip install -U "huggingface_hub"
```

**Homebrew:**
```bash
brew install hf
```

Keep it updated (detects how it was installed and runs the matching update path):
```bash
hf update
```

### Authentication (hf)

Several models this repo references (Llama, Gemma) are gated and need a logged-in session to download:
```bash
hf auth login
```
This opens a browser device-code flow by default and saves the resulting token locally. To authenticate non-interactively (scripts, CI) instead, pass a token directly:
```bash
hf auth login --token $HF_TOKEN --add-to-git-credential
```
Check who you're logged in as, or log out, with:
```bash
hf auth whoami
hf auth logout
```

### Downloading Models Directly (hf)

Useful when you want one specific quant/file rather than whatever a runtime's own downloader grabs:
```bash
# An entire repo
hf download HuggingFaceH4/zephyr-7b-beta

# Just the files you actually need — e.g. one GGUF quant out of a multi-file repo
hf download unsloth/Qwen3.8-27B-GGUF --include "*UD-Q6_K_XL*"

# A specific revision/branch/tag
hf download bigcode/the-stack --repo-type dataset --revision v1.1

# Preview what would download (size, file count) without fetching anything
hf download openai-community/gpt2 --dry-run
```
By default, files land in the same shared cache every other tool in this document reads from (`$HF_HOME`/`$HF_HUB_CACHE`, see [Managing the Local Cache](#managing-the-local-cache-hf) below) — pass `--local-dir <path>` instead if you want a plain directory of files, git-checkout style.

### Discovering Models (hf)

Directly relevant to this repo's recurring "which model/quant fits my hardware" question:
```bash
# Only models a given runtime can actually run
hf models ls --apps llama.cpp

# Filter by parameter count
hf models ls --num-parameters min:6B,max:32B

# Skip anything gated
hf models ls --no-gated --author Qwen

# Read a model's card (README) without opening a browser
hf models card unsloth/Qwen3.8-27B-GGUF --text
```

### Managing the Local Cache (hf)

Every tool in [RUNTIMES.md](./RUNTIMES.md) that pulls from the Hub shares the same on-disk cache — this is the one place to see and reclaim space across all of them at once, which matters given how large some of the models discussed in this repo are (e.g. Colibri's models running [hundreds of GB on disk](./RUNTIMES.md#colibri-installation)):
```bash
# See what's cached and how large, aggregated by repo
hf cache ls

# Drill into individual snapshots, filtered by size
hf cache ls --filter "size>30g" --revisions

# Remove a specific cached repo (prompts for confirmation; --dry-run to preview, --yes to skip the prompt)
hf cache rm model/LiquidAI/LFM2-VL-1.6B

# Reclaim space from detached/unreferenced revisions and leftover partial (.incomplete) downloads
hf cache prune

# Validate a cached model's files against the Hub's checksums
hf cache verify deepseek-ai/DeepSeek-OCR
```
The cache location is controlled by the `HF_HOME`/`HF_HUB_CACHE` environment variables (defaulting to `~/.cache/huggingface/hub`) — the same variables [Magnitude](./RUNTIMES.md#magnitude-installation) already checks (Unsloth Studio also scans a Hugging Face cache, per its [own section above](./RUNTIMES.md#sharing-already-downloaded-lm-studio-models-with-unsloth-studio), but that section doesn't confirm it honors these same variables — check `hf env`'s output against Unsloth's actual behavior before assuming they'll agree). Pointing this cache at a larger disk (or a shared network volume) benefits at least Magnitude and `hf` itself at once. Run `hf env` to see your current settings when filing a bug report against any of these tools — it prints the resolved cache paths, token status, and library versions in one block.

### Bridging Already-Downloaded LM Studio Models (hf)

If you already have models under `~/.lmstudio/models/<publisher>/<repo>/<file>.gguf` (LM Studio's own layout — see [LM Studio Installation](./RUNTIMES.md#lm-studio-installation)), neither `hf cache ls` nor [Magnitude's](./RUNTIMES.md#magnitude-installation) documented "discovers GGUF packages already sitting in your Hugging Face Hub cache" behavior will recognize them — both only look at the Hub's own content-addressed cache layout, which is a completely different shape:

```
models--<publisher>--<repo>/
├── blobs/<hash>                                  # the actual file, named by its hash
├── refs/main                                     # the commit hash for "main"
└── snapshots/<commit-hash>/<file> -> ../../blobs/<hash>
```

[`scripts/bridge-lmstudio-to-hf-cache.sh`](../scripts/bridge-lmstudio-to-hf-cache.sh) builds that layout for every model already under `~/.lmstudio/models`, **without copying or re-downloading anything** — each blob is a symlink straight back to the real LM Studio file:

```bash
./scripts/bridge-lmstudio-to-hf-cache.sh --dry-run   # preview first
./scripts/bridge-lmstudio-to-hf-cache.sh             # then actually bridge everything
```

It resolves each repo's real current commit hash via `huggingface_hub`'s `resolve_revision()` (one Hub API call per repo, needs network) so a *future* `hf download`/`hf_hub_download()`/`snapshot_download()` call for that same repo+revision recognizes the bridged entry and skips re-fetching, rather than just making `hf cache ls` cosmetically list it. Requires `python3` with `huggingface_hub` importable, or falls back to `uvx --from huggingface_hub` if `uv` is installed.

Afterward, verify with `hf cache ls`. **Don't expect Magnitude to pick these up, though** — tested directly on this repo's own GS63 (GTX 1060 6GB) with a correctly-bridged, independently-verified cache (confirmed via `huggingface_hub`'s own `scan_cache_dir()`), Magnitude's discovery still reported 0 models found. See [RUNTIMES.md — Confirmed on Real Hardware](./RUNTIMES.md#confirmed-on-real-hardware-discovery-doesnt-adopt-arbitrary-local-ggufs) for the full finding: Magnitude's "discovery" appears to mean matching its own curated catalog, not adopting arbitrary local GGUFs. The `hf`-side benefit (cache visibility, skipping a future re-download) holds regardless of whether Magnitude ever sees these files.

**Worth being upfront about:** this hand-constructs cache internals that `huggingface_hub`'s own documentation describes as implementation detail (see the [manage-cache guide](https://huggingface.co/docs/huggingface_hub/guides/manage-cache)), not an officially supported "adopt an external file" API. It matches the documented layout as of this writing — verified against `scan_cache_dir()` reporting the bridged entries with no warnings — but isn't guaranteed stable across future `huggingface_hub` versions.

**None of this is needed just to *run* these models** — `llama-server -m ~/.lmstudio/models/.../file.gguf` (or Ollama, or anything llama.cpp-based) works directly from that path exactly as-is. The cache format only matters for `hf`'s and Magnitude's own bookkeeping/dedup, not for serving.

**If you'd rather make these models visible to Unsloth Studio instead** (or as well) — that's a much simpler plain directory symlink, since both apps already share the same `<publisher>/<repo>/<file>.gguf` layout: see [RUNTIMES.md — Sharing Already-Downloaded LM Studio Models with Unsloth Studio](./RUNTIMES.md#sharing-already-downloaded-lm-studio-models-with-unsloth-studio) and [`scripts/bridge-lmstudio-to-unsloth.sh`](../scripts/bridge-lmstudio-to-unsloth.sh) (macOS/Linux) / [`scripts/bridge-lmstudio-to-unsloth.ps1`](../scripts/bridge-lmstudio-to-unsloth.ps1) (Windows).

### Connecting to Claude Code and Other Agents (hf)

`hf` ships an installable agent Skill that teaches a coding harness to search, inspect, and download from the Hub directly rather than you doing it by hand and pasting results back in. The standalone installer above installs it globally by default (pass `--exclude-skill` to skip); to add it by hand, or scope it to one project:
```bash
# Global, for Codex/Cursor/OpenCode/any agent reading ~/.agents/skills
hf skills add --global

# Global, specifically for Claude CLI
hf skills add --claude --global

# Project-scoped instead of global — drop --global from either command above
hf skills add --claude
```
For [Claude CLI](./CLI.md#claude-cli) specifically, the plugin-marketplace route is an alternative to the standalone installer:
```
/plugin marketplace add huggingface/skills
/plugin install hf-cli@huggingface/skills
```
`hf update` refreshes an already-installed Skill (never re-adds one you removed); `hf skills update -g` does the same on its own without a full CLI update.

For the complete command reference, see the [Hugging Face Hub CLI guide](https://huggingface.co/docs/huggingface_hub/guides/cli) and the [CLI for AI Agents guide](https://huggingface.co/docs/hub/agents-cli).
