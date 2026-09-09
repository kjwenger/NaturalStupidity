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
