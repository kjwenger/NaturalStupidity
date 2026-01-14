# NaturalStupidity
Playground for all things Artificial Intelligence

## Table of Contents
- [Prerequisites](#prerequisites)
  - [LM Studio Installation](#lm-studio-installation)
    - [macOS](#macos)
    - [macOS with Homebrew](#macos-with-homebrew)
    - [Linux](#linux)
    - [Linux with AppImage](#linux-with-appimage)
    - [Windows](#windows-1)
  - [Node.js Installation](#nodejs-installation)
    - [Linux and macOS](#linux-and-macos)
    - [Using `curl` or `wget` (Recommended for fresh installs)](#using-curl-or-wget-recommended-for-fresh-installs)
    - [Using Homebrew (If Homebrew is already installed)](#using-homebrew-if-homebrew-is-already-installed)
    - [Windows](#windows)
    - [Using `nvm` to Install Node.js](#using-nvm-to-install-nodejs)
  - [AI CLI Tools](#ai-cli-tools)
    - [Aider CLI](#aider-cli)
    - [Claude CLI](#claude-cli)
    - [Codex CLI](#codex-cli)
    - [Copilot CLI](#copilot-cli)
    - [Gemini CLI](#gemini-cli)
    - [Goose CLI](#goose-cli)
    - [Grok CLI](#grok-cli)
    - [Qwen CLI](#qwen-cli)
    - [Warp CLI](#warp-cli)

## Prerequisites

### LM Studio Installation

LM Studio allows you to run Large Language Models locally on your machine. It provides an OpenAI-compatible API server that can be used with various AI CLI tools.

#### macOS

Download the latest version from [lmstudio.ai/download](https://lmstudio.ai/download):
1. Download the `.dmg` file for macOS
2. Open the downloaded file
3. Drag LM Studio to your Applications folder
4. Launch LM Studio from Applications

#### macOS with Homebrew

Install using Homebrew:
```bash
brew install --cask lm-studio
```

#### Linux

Download the latest version from [lmstudio.ai/download](https://lmstudio.ai/download):
1. Download the `.deb` or `.rpm` package for your distribution
2. Install the package using your package manager

For Debian/Ubuntu:
```bash
sudo dpkg -i LM-Studio-*.deb
```

For Fedora/RHEL:
```bash
sudo rpm -i LM-Studio-*.rpm
```

#### Linux with AppImage

Download and run the AppImage:
1. Download the `.AppImage` file from [lmstudio.ai/download](https://lmstudio.ai/download)
2. Make it executable:
```bash
chmod +x LM-Studio-*.AppImage
```
3. Run the AppImage:
```bash
./LM-Studio-*.AppImage
```

#### Windows

Download the latest version from [lmstudio.ai/download](https://lmstudio.ai/download):
1. Download the `.exe` installer for Windows
2. Run the installer
3. Follow the installation wizard
4. Launch LM Studio from the Start Menu

For more information, visit [LM Studio documentation](https://lmstudio.ai/docs/app/basics).

### Node.js Installation

To manage Node.js versions, it's recommended to install `nvm` (Node Version Manager).

### Linux and macOS

To install `nvm` on Linux or macOS, you have a couple of options:

#### Using `curl` or `wget` (Recommended for fresh installs)

You can use the following `curl` or `wget` commands:

```bash
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
```
or
```bash
wget -qO- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
```

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

### AI CLI Tools

#### Aider CLI

Aider is an AI pair programming tool in your terminal.

Install using pip:
```bash
pip install aider-install
```

Or using pipx (recommended for isolated installation):
```bash
pipx install aider-install
```

After installation, you'll need to set your API key (e.g., OpenAI):
```bash
export OPENAI_API_KEY=your-api-key-here
```

##### Using Aider with Local LLMs via LM Studio

You can use Aider with local LLMs served by LM Studio's OpenAI-compatible API:

1. Start LM Studio and load your preferred model
2. Enable the Local Server feature in LM Studio (default port: 1234)
3. Configure environment variables:

**Method 1: Using generic OpenAI environment variables:**
```bash
export OPENAI_HOST=http://localhost:1234
export OPENAI_BASE_PATH=v1
export OPENAI_BASE_URL=http://localhost:1234/v1
export OPENAI_API_KEY=lm-studio
export OPENAI_MODEL=openai/qwen3-coder-30b-a3b-instruct
```

**Method 2: Using Aider-specific environment variables:**
```bash
export AIDER_OPENAI_API_BASE=http://localhost:1234/v1
export AIDER_OPENAI_API_KEY=lm-studio
export AIDER_MODEL=openai/openai/gpt-oss-120b
```

Replace the model identifier with your actual model from LM Studio (e.g., `openai/qwen3-coder-30b-a3b-instruct` or `openai/openai/gpt-oss-120b`).

For more information, visit [Aider's installation documentation](https://aider.chat/docs/install.html).

#### Claude CLI

Claude CLI provides command-line access to Anthropic's Claude AI.

Install using npm:
```bash
npm install -g @anthropic-ai/claude-cli
```

Configure with your Anthropic API key:
```bash
export ANTHROPIC_API_KEY=your-api-key-here
```

For more information, visit [Anthropic's documentation](https://www.anthropic.com/).

#### Codex CLI

OpenAI Codex CLI for code generation and completion.

Install using npm:
```bash
npm install -g @openai/codex
```

Set your OpenAI API key:
```bash
export OPENAI_API_KEY=your-api-key-here
```

For more information, visit [OpenAI Codex CLI documentation](https://developers.openai.com/codex/cli/).

#### Copilot CLI

GitHub Copilot CLI brings GitHub Copilot to the command line.

Install using npm:
```bash
npm install -g @githubnext/github-copilot-cli
```

Authenticate with GitHub:
```bash
github-copilot-cli auth
```

For more information, visit [GitHub Copilot CLI documentation](https://githubnext.com/projects/copilot-cli/).

#### Gemini CLI

Google's Gemini AI CLI tool.

**Run instantly with npx (no installation required):**
```bash
npx @google/gemini-cli
```

**Install globally with npm:**
```bash
npm install -g @google/gemini-cli
```

**Install globally with Homebrew (macOS/Linux):**
```bash
brew install gemini-cli
```

After installation, you'll be prompted to authenticate with your Google account on first use.

For more information, visit [Gemini CLI's GitHub repository](https://github.com/google-gemini/gemini-cli).

#### Grok CLI

Grok CLI is a conversational AI CLI tool powered by xAI's Grok with intelligent text editor capabilities and tool usage.

**Install globally with Bun (recommended):**
```bash
bun add -g @vibe-kit/grok-cli
```

**Install globally with npm:**
```bash
npm install -g @vibe-kit/grok-cli
```

**Setup:**

Get your Grok API key from [X.AI](https://x.ai) and configure it using one of these methods:

Environment variable:
```bash
export GROK_API_KEY=your_api_key_here
```

Or create `~/.grok/user-settings.json`:
```json
{
  "apiKey": "your_api_key_here"
}
```

Or use the command line flag:
```bash
grok --api-key your_api_key_here
```

For more information, visit [Grok CLI's GitHub repository](https://github.com/superagent-ai/grok-cli).

#### Goose CLI

Goose is an open-source, extensible AI agent that automates engineering tasks, capable of building projects, executing code, debugging, and orchestrating workflows autonomously.

**Install using installation script (macOS/Linux):**
```bash
curl -fsSL https://github.com/block/goose/releases/latest/download/download_cli.sh | sh
```

**Install using pre-built binaries:**

Download the appropriate binary for your platform from the [latest release](https://github.com/block/goose/releases/latest):

**macOS:**
- Apple Silicon (M1/M2): `goose-aarch64-apple-darwin.tar.bz2`
- Intel: `goose-x86_64-apple-darwin.tar.bz2` or `Goose_intel_mac.zip`

**Linux:**
- ARM64: `goose-aarch64-unknown-linux-gnu.tar.bz2`
- x86_64: `goose-x86_64-unknown-linux-gnu.tar.bz2`
- Debian/Ubuntu: `goose_*_amd64.deb`
- Fedora/RHEL: `Goose-*.x86_64.rpm`

**Windows:**
- `goose-x86_64-pc-windows-gnu.zip` or `Goose-win32-x64.zip`

Extract the binary and add it to your PATH, or use your package manager to install the .deb or .rpm package.

**Desktop App:**

Goose is also available as a desktop application. Download `Goose.zip` from the [latest release](https://github.com/block/goose/releases/latest).

For more information, visit [Goose's documentation](https://block.github.io/goose/docs/getting-started/installation) and [GitHub repository](https://github.com/block/goose).

#### Qwen CLI

Qwen Code is Alibaba Cloud's AI-powered coding assistant CLI.

**Install using npm (recommended):**
```bash
npm install -g @qwen-code/qwen-code@latest
```

**Install using Homebrew (macOS, Linux):**
```bash
brew install qwen-code
```

Start Qwen Code:
```bash
qwen
```

On first use, you'll be prompted to sign in. You can run `/auth` anytime to switch authentication methods.

For more information, visit [Qwen Code's GitHub repository](https://github.com/QwenLM/qwen-code).

#### Warp CLI

Warp is a modern terminal with AI-powered command suggestions.

**macOS:**
Download from [Warp's website](https://www.warp.dev/) or install using Homebrew:
```bash
brew install --cask warp
```

**Linux:**
Download the appropriate package from [Warp's website](https://www.warp.dev/).

**Windows:**
Download the installer from [Warp's website](https://www.warp.dev/).

After installation, launch Warp and sign in to access AI features.
