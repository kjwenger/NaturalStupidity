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
    - [Discovering New AI CLI Tools](#discovering-new-ai-cli-tools)
    - [Aider CLI](#aider-cli)
      - [Using Aider with Local LLMs via LM Studio](#using-aider-with-local-llms-via-lm-studio)
    - [Aider-CE CLI](#aider-ce-cli)
    - [Claude CLI](#claude-cli)
    - [Codex CLI](#codex-cli)
      - [Using Codex with Local LLMs via LM Studio](#using-codex-with-local-llms-via-lm-studio)
    - [Copilot CLI](#copilot-cli)
    - [DeepSeek CLI](#deepseek-cli)
    - [Factory CLI](#factory-cli)
    - [Gemini CLI](#gemini-cli)
    - [Goose CLI](#goose-cli)
      - [Using Goose with Local LLMs via LM Studio](#using-goose-with-local-llms-via-lm-studio)
    - [Grok CLI](#grok-cli)
    - [OpenHands CLI](#openhands-cli)
    - [OpenCode CLI](#opencode-cli)
      - [Using OpenCode with Local LLMs via LM Studio](#using-opencode-with-local-llms-via-lm-studio)
    - [OpenSpec CLI](#openspec-cli)
    - [Qwen CLI](#qwen-cli)
      - [Using Qwen with Local LLMs via LM Studio](#using-qwen-with-local-llms-via-lm-studio)
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

#### Discovering New AI CLI Tools

The AI CLI landscape is rapidly evolving. To stay up-to-date with the latest and most popular tools:

**OpenRouter AI Rankings:**
Visit [OpenRouter AI Rankings - Apps](https://openrouter.ai/rankings#apps) to see real-time usage statistics and trending AI applications and CLI tools. This provides insights into:
- Most popular AI tools by active usage
- Emerging tools gaining traction
- Usage patterns and adoption trends
- Community favorites across different AI platforms

**Other Resources for Discovery:**
- **GitHub Topics**: Search for [#ai-cli](https://github.com/topics/ai-cli) or [#ai-coding-assistant](https://github.com/topics/ai-coding-assistant)
- **Package Managers**: Browse npm, PyPI, and Homebrew for AI-related packages
- **Community Forums**: Follow discussions on Discord, Reddit (r/LocalLLaMA, r/artificial), and Hacker News
- **AI News Aggregators**: Sites like [There's An AI For That](https://theresanaiforthat.com/) and [Future Tools](https://www.futuretools.io/)

**Evaluating New Tools:**
When considering a new AI CLI tool, check for:
- Active development and recent updates
- Community size and support
- Documentation quality
- License and cost model
- Local vs. cloud options
- Integration capabilities

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

#### Aider-CE CLI

Aider-CE (cecli) is a community-driven fork of Aider, providing bleeding-edge features and rapid development in the AI pair programming space.

**Install using pip:**
```bash
pip install cecli-dev
```

**Install using uv (recommended for isolation):**
```bash
uv tool install --native-tls --python python3.12 cecli-dev
```

**Configuration:**

Create a `.cecli.conf.yml` file in your project directory:

```yaml
model: <your-model>
agent: true
auto-commits: true
auto-save: true
cache-prompts: true
tui: true
```

Create a `.aider.env` file for API keys:
```bash
ANTHROPIC_API_KEY="..."
OPENAI_API_KEY="..."
```

**Usage:**

Start Aider-CE:
```bash
cecli
```

Configure terminal setup (recommended on first run):
```bash
cecli --terminal-setup
```

**Docker:**
```bash
docker pull dustinwashington/aider-ce
docker run -it --user $(id -u):$(id -g) \
  --volume $(pwd):/app \
  --volume $(pwd)/.aider.conf.yml:/.aider.conf.yml \
  --volume $(pwd)/.aider.env:/.aider/.env \
  dustinwashington/aider-ce --config /app/.aider.conf.yml
```

For more information, visit [Aider-CE GitHub repository](https://github.com/dwash96/cecli) and join the [Discord community](https://discord.gg/AX9ZEA7nJn).

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

##### Using Codex with Local LLMs via LM Studio

You can use Codex CLI with local LLMs served by LM Studio's OpenAI-compatible API:

1. Start LM Studio and load your preferred model
2. Enable the Local Server feature in LM Studio (default port: 1234)
3. Configure using one of the following methods:

**Method 1: Using environment variables:**

```bash
export OPENAI_API_BASE=http://localhost:1234/v1
export OPENAI_API_KEY=lm-studio
export OPENAI_MODEL=your-model-name
```

Replace `your-model-name` with your actual model from LM Studio (e.g., `qwen3-coder-30b-a3b-instruct` or `gpt-oss-120b`).

**Method 2: Using configuration file (~/.codex/config.toml):**

Create or edit `~/.codex/config.toml`:

```toml
# Codex CLI Configuration for Local LLM via LM Studio

[model_providers.lm_studio]
name = "LM Studio"
base_url = "http://localhost:1234/v1"

[profiles.gpt-oss-120b]
model_provider = "lm_studio"
model = "openai/gpt-oss-120b"

[profiles.qwen3-coder]
model_provider = "lm_studio"
model = "qwen3-coder-30b-a3b-instruct"

[projects."/path/to/your/project"]
trust_level = "trusted"
```

Then use the profile with:
```bash
codex --profile gpt-oss-120b
# or
codex --profile qwen3-coder
```

For more information, see [Codex CLI with Local Models](https://dev.to/shashikant86/codex-cli-running-gpt-oss-and-local-coding-models-with-ollama-lm-studio-and-mlx-403g).

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

#### DeepSeek CLI

DeepSeek CLI is an AI coding assistant leveraging DeepSeek Coder models for code generation, refactoring, and development workflows.

**Install using npm:**
```bash
npm install -g run-deepseek-cli
```

**Local Setup (Recommended - Free & Private):**

1. **Install Ollama:**
```bash
# macOS
brew install ollama

# Linux
curl -fsSL https://ollama.ai/install.sh | sh

# Windows: Download from https://ollama.ai
```

2. **Start Ollama and install DeepSeek model:**
```bash
# Start Ollama service
ollama serve

# Install DeepSeek Coder model (choose one)
ollama pull deepseek-coder:6.7b    # Recommended (4GB)
ollama pull deepseek-coder:1.3b    # Lightweight (1GB)
ollama pull deepseek-coder:33b     # Most capable (19GB)
```

3. **Start using:**
```bash
deepseek
```

**Cloud Setup (Requires API Key):**

Configure API access:
```bash
export DEEPSEEK_API_KEY="your_api_key_here"
export DEEPSEEK_USE_LOCAL=false
```

Get your API key from [DeepSeek Platform](https://platform.deepseek.com/api_keys).

**Usage:**

```bash
deepseek
> Create a FastAPI web application with user authentication
```

For more information, visit [DeepSeek CLI GitHub repository](https://github.com/holasoymalva/deepseek-cli).

#### Factory CLI

Factory CLI enables AI-powered automation across the software development lifecycle, from CI/CD to code migrations and maintenance.

**Install using installation script:**

**macOS/Linux:**
```bash
curl -fsSL https://factory.ai/install.sh | sh
```

**Windows:**
```powershell
irm https://factory.ai/install.ps1 | iex
```

**Setup:**

Sign up and authenticate:
```bash
factory auth
```

Configure your API key:
```bash
export FACTORY_API_KEY=your-api-key-here
```

**Usage:**

Run Factory Droids for various tasks:
```bash
factory run "your automation task"
```

For CI/CD integration and advanced features, see the [Factory CLI documentation](https://factory.ai/product/cli).

For more information, visit [Factory.ai](https://factory.ai/).

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

##### Using Goose with Local LLMs via LM Studio

You can use Goose with local LLMs served by LM Studio's OpenAI-compatible API:

1. Start LM Studio and load your preferred model
2. Enable the Local Server feature in LM Studio (default port: 1234)
3. Configure environment variables:

```bash
export GOOSE_PROVIDER=openai
export OPENAI_API_BASE=http://localhost:1234/v1
export OPENAI_API_KEY=lm-studio
# export GOOSE_MODEL=qwen3-coder-30b-a3b-instruct
export GOOSE_MODEL=openai/gpt-oss-120b
```

Replace the model identifier with your actual model from LM Studio. The commented-out line shows an alternative model example.

#### OpenHands CLI

OpenHands CLI brings AI-powered development assistance directly to your terminal, enabling autonomous coding, debugging, and task execution.

**Install using npm:**
```bash
npm install -g openhands-cli
```

**Install using pip:**
```bash
pip install openhands-cli
```

**Setup:**

Configure your API key using environment variables:
```bash
export OPENHANDS_API_KEY=your-api-key-here
```

You can also configure the LLM provider:
```bash
export OPENHANDS_LLM_PROVIDER=openai  # or anthropic, azure, etc.
export OPENHANDS_LLM_MODEL=gpt-4      # your preferred model
```

**Usage:**
```bash
openhands "your task description"
```

For more information, visit [OpenHands CLI blog post](https://openhands.dev/blog/the-openhands-cli-ai-powered-development-in-your-terminal) and [OpenHands documentation](https://openhands.dev/).

#### OpenCode CLI

OpenCode CLI is an AI-powered coding assistant that provides a terminal UI (TUI) and command-line interface for code generation, review, and automation.

**Install using curl:**
```bash
curl -fsSL https://opencode.ai/install.sh | sh
```

**Install using npm:**
```bash
npm install -g opencode-ai
```

**Install using pnpm:**
```bash
pnpm add -g opencode-ai
```

**Install using bun:**
```bash
bun add -g opencode-ai
```

**Install using Homebrew (macOS/Linux):**
```bash
brew install opencode-ai/tap/opencode
```

**Setup:**

Authenticate with your OpenCode account:
```bash
opencode auth
```

**Usage:**

Start the interactive TUI:
```bash
opencode
```

Run AI agent tasks:
```bash
opencode agent "your task description"
```

For GitHub integration:
```bash
opencode github install
```

For more information, visit [OpenCode CLI documentation](https://opencode.ai/docs/cli/).

##### Using OpenCode with Local LLMs via LM Studio

You can use OpenCode with local LLMs served by LM Studio's OpenAI-compatible API:

1. Start LM Studio and load your preferred model
2. Enable the Local Server feature in LM Studio (default port: 1234)
3. Create or edit the OpenCode configuration file at `~/.config/opencode/opencode.json`:

```json
{
  "$schema": "https://opencode.ai/config.json",
  "provider": {
    "lm-studio": {
      "npm": "@ai-sdk/openai-compatible",
      "name": "LM Studio",
      "options": {
        "baseURL": "http://localhost:1234/v1"
      },
      "models": {
        "qwen3-coder-30b-a3b-instruct": {
          "name": "qwen3-coder-30b-a3b-instruct"
        },
        "gpt-oss-120b": {
          "name": "openai/gpt-oss-120b"
        }
      }
    }
  }
}
```

Replace the model identifiers with your actual models from LM Studio.

**Using Ollama:**

You can also use OpenCode with Ollama:

1. Start Ollama and pull your preferred model
2. Increase the context window (OpenCode requires at least 64k tokens for best results):
```bash
ollama run qwen3:8b
>>> /set parameter num_ctx 65536
>>> /save qwen3:8b-64k
```

3. Create or edit `~/.config/opencode/opencode.json`:

```json
{
  "$schema": "https://opencode.ai/config.json",
  "provider": {
    "ollama": {
      "npm": "@ai-sdk/openai-compatible",
      "name": "Ollama",
      "options": {
        "baseURL": "http://localhost:11434/v1"
      },
      "models": {
        "qwen3:8b-64k": {
          "name": "qwen3:8b-64k"
        }
      }
    }
  }
}
```

**Quick Setup with `ollama launch`:**

Ollama provides a convenient command to set up OpenCode automatically:
```bash
ollama launch opencode
```

This configures OpenCode to work with your local Ollama models without manual configuration.

For more information, see [OpenCode Providers documentation](https://opencode.ai/docs/providers/) and [Ollama OpenCode integration](https://docs.ollama.com/integrations/opencode).

#### OpenSpec CLI

OpenSpec is a spec-driven development (SDD) framework for AI coding assistants. It helps you create structured specifications that AI tools can follow, improving consistency and quality of AI-generated code. OpenSpec works alongside other AI CLI tools like Claude Code, Cursor, Gemini CLI, OpenCode, and more.

**Install using npm:**
```bash
npm install -g @fission-ai/openspec@latest
```

**Install using pnpm:**
```bash
pnpm add -g @fission-ai/openspec@latest
```

**Install using yarn:**
```bash
yarn global add @fission-ai/openspec@latest
```

**Install using bun:**
```bash
bun add -g @fission-ai/openspec@latest
```

**Install using Nix (run directly without installation):**
```bash
nix run github:Fission-AI/OpenSpec -- init
```

**Install using Nix (to your profile):**
```bash
nix profile install github:Fission-AI/OpenSpec
```

**Prerequisites:** Requires Node.js 20.19.0 or higher.

**Setup:**

Initialize OpenSpec in your project:
```bash
openspec init
```

During initialization, you'll be prompted to select which AI tools to configure. You can also use non-interactive setup:
```bash
openspec init --tools claude,cursor,gemini
```

Supported tools include: `claude`, `cursor`, `github-copilot`, `cline`, `continue`, `gemini`, `opencode`, `codex`, and more. Use `--tools all` to configure all supported tools.

**Usage:**

The OpenSpec workflow consists of three main steps:

1. **Proposal** - Create a specification document:
```bash
openspec proposal create "feature-name"
```

2. **Apply** - Let AI implement according to the specification

3. **Archive** - Archive completed specifications:
```bash
openspec archive
```

**Configuration:**

View and modify settings:
```bash
openspec config
openspec config edit
```

Configuration is stored in `openspec/config.yaml`.

**Note:** OpenSpec is a workflow framework that works alongside AI coding assistants rather than making direct LLM calls itself. It provides structured specifications that your AI tools (Claude Code, OpenCode, Cursor, etc.) follow during implementation. Configure local LLM support in your chosen AI tool, and OpenSpec will work with whatever provider that tool uses.

For more information, visit [OpenSpec GitHub repository](https://github.com/Fission-AI/OpenSpec) and the [GitHub Blog on spec-driven development](https://github.blog/ai-and-ml/generative-ai/spec-driven-development-with-ai-get-started-with-a-new-open-source-toolkit/).

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

##### Using Qwen with Local LLMs via LM Studio

You can use Qwen with local LLMs served by LM Studio's OpenAI-compatible API:

1. Start LM Studio and load your preferred model
2. Enable the Local Server feature in LM Studio (default port: 1234)
3. Configure environment variables:

```bash
export OPENAI_HOST=http://localhost:1234
export OPENAI_BASE_PATH=v1
export OPENAI_BASE_URL=http://localhost:1234/v1
export OPENAI_API_KEY=lm-studio
export OPENAI_MODEL=openai/qwen3-coder-30b-a3b-instruct
```

Replace the model identifier with your actual model from LM Studio.

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
