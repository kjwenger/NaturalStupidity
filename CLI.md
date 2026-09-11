# AI CLI Tools

<!-- TOC -->
* [AI CLI Tools](#ai-cli-tools)
  * [Discovering New AI CLI Tools](#discovering-new-ai-cli-tools)
  * [Understanding Token & Context Overhead](#understanding-token--context-overhead)
  * [Installing All AI CLI Tools](#installing-all-ai-cli-tools)
  * [Installing Bash Completion for All CLI Tools](#installing-bash-completion-for-all-cli-tools)
  * [Enabling Bash Completion for Zsh](#enabling-bash-completion-for-zsh)
  * [Aider CLI](#aider-cli)
    * [Using Aider with Local LLMs via LM Studio](#using-aider-with-local-llms-via-lm-studio)
    * [Aider Config Gists (LM Studio)](#aider-config-gists-lm-studio)
    * [Bash Completion on Linux (Aider)](#bash-completion-on-linux-aider)
    * [Using Aider with Mammouth AI](#using-aider-with-mammouth-ai)
  * [Aider-CE CLI](#aider-ce-cli)
    * [Bash Completion on Linux (Aider-CE)](#bash-completion-on-linux-aider-ce)
    * [Using Aider-CE with Local LLMs via LM Studio](#using-aider-ce-with-local-llms-via-lm-studio)
    * [Using Aider-CE with Mammouth AI](#using-aider-ce-with-mammouth-ai)
  * [Amp CLI](#amp-cli)
  * [Claude CLI](#claude-cli)
    * [Bash Completion on Linux (Claude)](#bash-completion-on-linux-claude)
    * [Using Claude with Local LLMs via LM Studio](#using-claude-with-local-llms-via-lm-studio)
    * [Using Claude with Mammouth AI](#using-claude-with-mammouth-ai)
  * [Cline CLI](#cline-cli)
    * [Using Cline with Local LLMs via LM Studio and Ollama](#using-cline-with-local-llms-via-lm-studio-and-ollama)
    * [Using Cline with Mammouth AI](#using-cline-with-mammouth-ai)
  * [Codex CLI](#codex-cli)
    * [Using Codex with Local LLMs via LM Studio](#using-codex-with-local-llms-via-lm-studio)
    * [Bash Completion on Linux (Codex)](#bash-completion-on-linux-codex)
    * [Using Codex with Mammouth AI](#using-codex-with-mammouth-ai)
  * [Copilot CLI](#copilot-cli)
    * [Bash Completion on Linux (Copilot)](#bash-completion-on-linux-copilot)
    * [Using Copilot with Local LLMs via LM Studio](#using-copilot-with-local-llms-via-lm-studio)
    * [Using Copilot with Mammouth AI](#using-copilot-with-mammouth-ai)
  * [Crush CLI](#crush-cli)
    * [Using Crush with Local LLMs via LM Studio and Ollama](#using-crush-with-local-llms-via-lm-studio-and-ollama)
    * [Using Crush with Mammouth AI](#using-crush-with-mammouth-ai)
  * [Cursor CLI](#cursor-cli)
  * [DeepSeek CLI](#deepseek-cli)
    * [Bash Completion on Linux (DeepSeek)](#bash-completion-on-linux-deepseek)
    * [Using DeepSeek with Local LLMs via LM Studio](#using-deepseek-with-local-llms-via-lm-studio)
    * [Using DeepSeek with Mammouth AI](#using-deepseek-with-mammouth-ai)
  * [DeepSeek Harness CLI](#deepseek-harness-cli)
    * [Configuring DeepSeek Harness](#configuring-deepseek-harness)
    * [Using DeepSeek Harness with the DeepSeek Cloud API](#using-deepseek-harness-with-the-deepseek-cloud-api)
    * [Using DeepSeek Harness with OpenAI](#using-deepseek-harness-with-openai)
    * [Using DeepSeek Harness with Local LLMs via LM Studio](#using-deepseek-harness-with-local-llms-via-lm-studio)
    * [Using DeepSeek Harness with Local LLMs via Ollama](#using-deepseek-harness-with-local-llms-via-ollama)
    * [Using DeepSeek Harness with Mammouth AI](#using-deepseek-harness-with-mammouth-ai)
    * [Troubleshooting (DeepSeek Harness)](#troubleshooting-deepseek-harness)
  * [Factory CLI](#factory-cli)
    * [Using Factory with Local LLMs via LM Studio](#using-factory-with-local-llms-via-lm-studio)
    * [Using Factory with Mammouth AI](#using-factory-with-mammouth-ai)
  * [Gemini CLI (Deprecated)](#gemini-cli-deprecated)
    * [Bash Completion on Linux (Gemini)](#bash-completion-on-linux-gemini)
    * [Using Gemini with Local LLMs via LM Studio](#using-gemini-with-local-llms-via-lm-studio)
    * [Using Gemini with Mammouth AI](#using-gemini-with-mammouth-ai)
  * [Antigravity CLI](#antigravity-cli)
    * [Migrating from Gemini CLI](#migrating-from-gemini-cli)
    * [Authentication](#authentication-antigravity)
    * [Using Antigravity with Local LLMs via LM Studio](#using-antigravity-with-local-llms-via-lm-studio)
    * [Using Antigravity with Mammouth AI](#using-antigravity-with-mammouth-ai)
  * [Grok CLI](#grok-cli)
    * [Using Grok with Local LLMs via LM Studio](#using-grok-with-local-llms-via-lm-studio)
    * [Using Grok with Mammouth AI](#using-grok-with-mammouth-ai)
  * [Grok Build CLI](#grok-build-cli)
    * [Using Grok Build with Custom/Local Endpoints](#using-grok-build-with-customlocal-endpoints)
  * [Goose CLI](#goose-cli)
    * [Using Goose with Local LLMs via LM Studio](#using-goose-with-local-llms-via-lm-studio)
    * [Bash Completion on Linux (Goose)](#bash-completion-on-linux-goose)
    * [Using Goose with Mammouth AI](#using-goose-with-mammouth-ai)
  * [Hermes Agent CLI](#hermes-agent-cli)
    * [Configuring Hermes Agent](#configuring-hermes-agent)
    * [Using Hermes Agent with the Nous Portal / OpenRouter (Online)](#using-hermes-agent-with-the-nous-portal--openrouter-online)
    * [Using Hermes Agent with OpenAI](#using-hermes-agent-with-openai)
    * [Using Hermes Agent with Local LLMs via LM Studio](#using-hermes-agent-with-local-llms-via-lm-studio)
    * [Using Hermes Agent with Local LLMs via Ollama](#using-hermes-agent-with-local-llms-via-ollama)
    * [Using Hermes Agent with Mammouth AI](#using-hermes-agent-with-mammouth-ai)
    * [Troubleshooting (Hermes Agent)](#troubleshooting-hermes-agent)
  * [Kilo Code CLI](#kilo-code-cli)
    * [Using Kilo Code with Local LLMs via LM Studio and Ollama](#using-kilo-code-with-local-llms-via-lm-studio-and-ollama)
    * [Using Kilo Code with Mammouth AI](#using-kilo-code-with-mammouth-ai)
  * [Kimi Code CLI](#kimi-code-cli)
    * [Configuring Kimi Code](#configuring-kimi-code)
    * [Using Kimi Code with Local LLMs via LM Studio](#using-kimi-code-with-local-llms-via-lm-studio)
    * [Using Kimi Code with Local LLMs via Ollama](#using-kimi-code-with-local-llms-via-ollama)
    * [Using Kimi Code with Mammouth AI](#using-kimi-code-with-mammouth-ai)
  * [Oh My Pi CLI](#oh-my-pi-cli)
    * [Using Oh My Pi with Local LLMs via LM Studio and Ollama](#using-oh-my-pi-with-local-llms-via-lm-studio-and-ollama)
    * [Using Oh My Pi with Mammouth AI](#using-oh-my-pi-with-mammouth-ai)
  * [OpenHands CLI](#openhands-cli)
    * [Bash Completion on Linux (OpenHands)](#bash-completion-on-linux-openhands)
    * [Using OpenHands with Local LLMs via LM Studio](#using-openhands-with-local-llms-via-lm-studio)
    * [Using OpenHands with Mammouth AI](#using-openhands-with-mammouth-ai)
  * [OpenCode CLI](#opencode-cli)
    * [Using OpenCode with Local LLMs via LM Studio](#using-opencode-with-local-llms-via-lm-studio)
    * [OpenCode Config Gist (LM Studio)](#opencode-config-gist-lm-studio)
    * [Bash Completion on Linux (OpenCode)](#bash-completion-on-linux-opencode)
    * [Using OpenCode with Mammouth AI](#using-opencode-with-mammouth-ai)
    * [Reducing Token Overhead (OpenCode)](#reducing-token-overhead-opencode)
  * [Pi Agent CLI](#pi-agent-cli)
    * [Using Pi Agent with Local LLMs via LM Studio and Ollama](#using-pi-agent-with-local-llms-via-lm-studio-and-ollama)
  * [Qwen CLI](#qwen-cli)
    * [Using Qwen with Local LLMs via LM Studio](#using-qwen-with-local-llms-via-lm-studio)
    * [Qwen Config Gist (LM Studio)](#qwen-config-gist-lm-studio)
    * [Switching from Local LLMs to Qwen Cloud LLMs](#switching-from-local-llms-to-qwen-cloud-llms)
    * [Bash Completion on Linux (Qwen)](#bash-completion-on-linux-qwen)
    * [Using Qwen with Mammouth AI](#using-qwen-with-mammouth-ai)
  * [Warp CLI](#warp-cli)
    * [Using Warp with Local LLMs via LM Studio](#using-warp-with-local-llms-via-lm-studio)
    * [Using Warp with Mammouth AI](#using-warp-with-mammouth-ai)
<!-- TOC -->

Every "Using [Tool] with Local LLMs via LM Studio/Ollama" section below assumes a generic `localhost:1234`/`localhost:11434` setup. If you're pointing one of these tools at this repo's own hardware, see the dedicated hardware guides instead: **[STRIX-HALO.md](./STRIX-HALO.md)** (AMD Ryzen AI Max+ "Strix Halo", 128GB) and **[MAC-MINI-M4.md](./MAC-MINI-M4.md)** (Mac Mini M4, 16GB) — both cover the runtime setup those generic sections don't (GPU memory sizing, ROCm/MLX install, model picks for that hardware, exposing the server on your network).

## Discovering New AI CLI Tools

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

## Understanding Token & Context Overhead

Every agentic harness in this document sends far more to the LLM per request than the message you typed — and it's easy to never notice, because none of the CLI tools surface it by default. This is worth understanding before you pick a harness or a local model for it, since it directly affects cost, latency, and how much of your context window is actually available for your own conversation.

**What's actually being sent.** A minimal user message like "hello" typically rides alongside a full system prompt (behavioral instructions, formatting rules, when to use tools) plus the complete JSON schema for every tool the agent has enabled — tool name, description, every parameter, often hundreds to thousands of characters *each*. One measured example: a stock agentic-coding harness sent a 9.5k-character (~114-line) system prompt plus 11 full tool definitions (a single `bash` tool description ran 4,700 characters on its own) for a single word of user input — roughly 8,000 tokens total, of which the actual "hello" was about 0.025%.

**Hidden auxiliary calls are a separate, easy-to-miss cost.** Some harnesses make LLM calls you didn't ask for — the same measured example showed the harness silently generating a conversation title in a second, separate ~2,000-token request before your actual message was even answered. If a harness feels like it costs more than your usage pattern implies, check whether it's making calls like this in the background (see [OpenCode's token-overhead options](#reducing-token-overhead-opencode) below for a concrete, verified example, including the official fix).

**Prompt caching reduces $ cost, but not the other costs.** Providers cache repeated system-prompt/tool-definition prefixes and charge less for the cached portion on a second request — but the full prompt is still transmitted over your connection, still occupies your context window, and still adds to latency, every single time. The cache is a cost optimization on the provider's compute, not a reduction in what you're actually sending or how much room is left for your own conversation.

**How to see this for yourself, for any harness.** Point the tool's provider config at a local MITM (man-in-the-middle) proxy — e.g. [mitmproxy](https://mitmproxy.org/) — sitting between the harness and the LLM API, and log the full request/response bodies for any call containing a `system` role message. This is a small, mechanical script (on the order of 40 lines of Python), and it will show you exactly what system prompt and tool definitions a given harness sends, since almost none of them surface this through their own UI or logs. Several harnesses in this document (Hermes Agent, Kilo Code, DeepSeek Harness, Oh My Pi, Pi Agent) already support pointing their provider config at an arbitrary base URL, which is exactly what a MITM proxy needs.

**The general mitigation, across harnesses:** start with the fewest tools/capabilities your task actually needs, and add more back in only when you hit a wall — rather than running a full "everything enabled" agent for a quick question. Most harnesses with a subagent/custom-agent system (OpenCode, Claude Code, Hermes Agent, Kilo Code, and others in this document) let you define a stripped-down agent for exactly this. See the [Composio benchmark numbers](#claude-cli) already cited throughout this document's per-harness sections — the wide token-usage spread they measured across harnesses on identical tasks (from Pi Agent's leanest runs to Kimi Code's 15.27M tokens across 24 tasks) is the same phenomenon showing up as a real, measured cost difference, not just a theoretical concern.

**One caveat on the numbers above:** they're drawn from one specific measured example (one harness, one model, one point in time) rather than something re-verified against every tool in this document — treat the *pattern* (large fixed per-request overhead, hidden auxiliary calls, caching not solving either) as the generalizable finding, and the exact token counts as illustrative rather than universal.

## Installing All AI CLI Tools

The following commands install all the AI CLI tools covered in this guide in one go. Pick and choose the ones you need, or run them all for a complete setup. Tools are grouped by package manager. Some tools require additional configuration (API keys, authentication) after installation — see each tool's section for details.

```bash
# --- npm-based tools ---
npm install -g @anthropic-ai/claude-cli         # Claude CLI
npm install -g @openai/codex                    # Codex CLI
npm install -g @githubnext/github-copilot-cli   # Copilot CLI
npm install -g run-deepseek-cli                 # DeepSeek CLI
npm install -g @google/gemini-cli               # Gemini CLI
npm install -g @vibe-kit/grok-cli               # Grok CLI
npm install -g openhands-cli                    # OpenHands CLI
npm install -g opencode-ai                      # OpenCode CLI
npm install -g @qwen-code/qwen-code@latest      # Qwen CLI

# --- pip / uv-based tools ---
pip install aider-install                       # Aider CLI
pip install cecli-dev                           # Aider-CE CLI
# or with uv (recommended for isolation):
# uv tool install --native-tls --python python3.12 cecli-dev

# --- Homebrew-based tools (macOS / Linux) ---
brew install gemini-cli                         # Gemini CLI (alternative)
brew install opencode-ai/tap/opencode           # OpenCode CLI (alternative)
brew install qwen-code                          # Qwen CLI (alternative)
brew install --cask warp                        # Warp terminal

# --- Installation script-based tools ---
# Factory CLI (macOS/Linux)
curl -fsSL https://factory.ai/install.sh | sh

# Goose CLI (macOS/Linux)
curl -fsSL https://github.com/block/goose/releases/latest/download/download_cli.sh | sh
```

## Installing Bash Completion for All CLI Tools

The following commands set up bash tab-completion for every AI CLI tool that supports it. All scripts are installed into the user-level `~/.local/share/bash-completion/completions/` directory, which integrates with the standard `bash-completion` framework and loads lazily. Ensure `bash-completion` is installed on your system (`sudo apt install bash-completion` on Debian/Ubuntu). See each tool's individual section for alternative installation methods (system-wide, dynamic sourcing in `~/.bashrc`).

```bash
# Create the user completions directory
mkdir -p ~/.local/share/bash-completion/completions

# Aider (built-in --shell-completions flag)
aider --shell-completions bash > ~/.local/share/bash-completion/completions/aider

# Aider-CE (built-in --shell-completions flag, same as Aider)
cecli --shell-completions bash > ~/.local/share/bash-completion/completions/cecli

# Claude CLI (community script — requires git clone first)
git clone https://github.com/cldotdev/claude-bash-completion.git ~/.local/share/claude-bash-completion
ln -s ~/.local/share/claude-bash-completion/claude-completion.bash ~/.local/share/bash-completion/completions/claude

# Codex CLI (built-in completion subcommand)
codex completion bash > ~/.local/share/bash-completion/completions/codex

# gh CLI (covers gh copilot and all gh subcommands)
gh completion -s bash > ~/.local/share/bash-completion/completions/gh

# Goose CLI (built-in completion subcommand)
goose completion bash > ~/.local/share/bash-completion/completions/goose

# OpenCode CLI (built-in completion subcommand)
opencode completion > ~/.local/share/bash-completion/completions/opencode

# Qwen CLI (manual yargs-based completion script)
cat > ~/.local/share/bash-completion/completions/qwen << 'EOF'
# Bash completion for Qwen Code CLI (@qwen-code/qwen-code)
_qwen_yargs_completions()
{
    local cur_word args type_list

    cur_word="${COMP_WORDS[COMP_CWORD]}"
    args=("${COMP_WORDS[@]}")

    type_list=$(qwen --get-yargs-completions "${args[@]}" 2>/dev/null)
    COMPREPLY=($(compgen -W "${type_list}" -- "${cur_word}"))

    if [ ${#COMPREPLY[@]} -eq 0 ]; then
        COMPREPLY=()
    fi

    return 0
}
complete -o bashdefault -o default -F _qwen_yargs_completions qwen
EOF
```

## Enabling Bash Completion for Zsh

If you use **zsh** (the default shell on macOS) instead of bash, you can still use bash completion scripts by enabling zsh's built-in bash compatibility layer. Add the following to your `~/.zshrc`:

```bash
# Enable bash completion compatibility in zsh
autoload -Uz bashcompinit && bashcompinit
autoload -Uz compinit && compinit
```

After adding those lines, source your profile:

```bash
source ~/.zshrc
```

With this enabled, zsh will load bash completion scripts from the standard `~/.local/share/bash-completion/completions/` directory. All the completion scripts installed in the previous section will work in zsh without modification.

**Tool-native zsh completions:**

Some tools generate zsh-native completions directly, which may provide a better experience than the bash compatibility layer:

```bash
# Create the zsh completions directory
mkdir -p ~/.local/share/zsh/site-functions

# Codex CLI
codex completion zsh > ~/.local/share/zsh/site-functions/_codex

# Goose CLI
goose completion zsh > ~/.local/share/zsh/site-functions/_goose

# gh CLI (covers gh copilot and all gh subcommands)
gh completion -s zsh > ~/.local/share/zsh/site-functions/_gh
```

Ensure the directory is on your `fpath` by adding this to `~/.zshrc` (before `compinit`):

```bash
fpath=(~/.local/share/zsh/site-functions $fpath)
autoload -Uz compinit && compinit
```

**Note:** If you use both `bashcompinit` and native zsh completions, place `bashcompinit` *after* `compinit` in your `~/.zshrc`:

```bash
fpath=(~/.local/share/zsh/site-functions $fpath)
autoload -Uz compinit && compinit
autoload -Uz bashcompinit && bashcompinit
```

## Aider CLI

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

###  Using Aider with Local LLMs via LM Studio

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

### Aider Config Gists (LM Studio)

Pre-built Aider configuration files for all LM Studio local models are available as public Gists.

| File                                                                                           | Destination                   | Description                                         |
|------------------------------------------------------------------------------------------------|-------------------------------|-----------------------------------------------------|
| [.aider.conf.yml](https://gist.github.com/kjwenger/32b131b72c5193e32ae2c5d72f893d3e)           | `~/.aider.conf.yml`           | Global config: default model, API base URL, aliases |
| [.aider.model.settings.yml](https://gist.github.com/kjwenger/500fdc480080a0f74f3c34e57836dbad) | `~/.aider.model.settings.yml` | Model settings: edit format, repo map, max tokens   |

**Download and install both configs:**
```bash
gh gist view 32b131b72c5193e32ae2c5d72f893d3e --raw > ~/.aider.conf.yml
gh gist view 500fdc480080a0f74f3c34e57836dbad --raw > ~/.aider.model.settings.yml
```

### Bash Completion on Linux (Aider)

Aider has built-in shell completion support via the `--shell-completions` flag (added in v0.77.0, powered by the [shtab](https://github.com/iterative/shtab) library). Supported shells: bash, zsh, tcsh.

**Option A: Install to user bash-completion directory (recommended):**
```bash
mkdir -p ~/.local/share/bash-completion/completions
aider --shell-completions bash > ~/.local/share/bash-completion/completions/aider
```

This integrates with the standard `bash-completion` framework and loads lazily. Ensure `bash-completion` is installed (`sudo apt install bash-completion` on Debian/Ubuntu).

**Option B: Install system-wide:**
```bash
sudo bash -c 'aider --shell-completions bash > /usr/share/bash-completion/completions/aider'
```

**Option C: Source dynamically in `~/.bashrc`:**
```bash
echo 'eval "$(aider --shell-completions bash)"' >> ~/.bashrc
source ~/.bashrc
```

This regenerates the completion script on every new shell, staying in sync after upgrades at the cost of a small startup delay.

### Using Aider with Mammouth AI

[Mammouth AI](https://mammouth.ai/) is an OpenAI-compatible cloud provider giving access to GPT, Claude, Gemini, Mistral, DeepSeek, and more under a single API key. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).

**Method 1: Using generic OpenAI environment variables:**
```bash
export OPENAI_API_BASE=https://api.mammouth.ai/v1
export OPENAI_API_KEY=your-mammouth-api-key
export OPENAI_MODEL=openai/gpt-4.1
```

**Method 2: Using Aider-specific environment variables:**
```bash
export AIDER_OPENAI_API_BASE=https://api.mammouth.ai/v1
export AIDER_OPENAI_API_KEY=your-mammouth-api-key
export AIDER_MODEL=openai/gpt-4.1
```

Replace `gpt-4.1` with your preferred model. Mammouth AI supports models from multiple providers — for example `openai/claude-sonnet-4-6`, `openai/mistral`, or `openai/deepseek-v3`. Use the `openai/` prefix to tell Aider to route the call through the OpenAI-compatible API. To see all available models, call `https://api.mammouth.ai/v1/models` with your API key or visit the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

## Aider-CE CLI

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

### Bash Completion on Linux (Aider-CE)

Aider-CE (cecli) is built on the same Click framework as Aider and likely inherits the `--shell-completions` flag (powered by [shtab](https://github.com/iterative/shtab)). Supported shells: bash, zsh, tcsh.

**Option A: Install to user bash-completion directory (recommended):**
```bash
mkdir -p ~/.local/share/bash-completion/completions
cecli --shell-completions bash > ~/.local/share/bash-completion/completions/cecli
```

This integrates with the standard `bash-completion` framework and loads lazily. Ensure `bash-completion` is installed (`sudo apt install bash-completion` on Debian/Ubuntu).

**Option B: Install system-wide:**
```bash
sudo bash -c 'cecli --shell-completions bash > /usr/share/bash-completion/completions/cecli'
```

**Option C: Source dynamically in `~/.bashrc`:**
```bash
echo 'eval "$(cecli --shell-completions bash)"' >> ~/.bashrc
source ~/.bashrc
```

This regenerates the completion script on every new shell, staying in sync after upgrades at the cost of a small startup delay.

### Using Aider-CE with Local LLMs via LM Studio

You can use Aider-CE (cecli) with local LLMs served by LM Studio's OpenAI-compatible API. Aider-CE is a fork of Aider and supports the same configuration methods:

1. Start LM Studio and load your preferred model
2. Enable the Local Server feature in LM Studio (default port: 1234)
3. Configure environment variables:

**Method 1: Using generic OpenAI environment variables:**
```bash
export OPENAI_API_BASE=http://localhost:1234/v1
export OPENAI_API_KEY=lm-studio
export OPENAI_MODEL=openai/qwen3-coder-30b-a3b-instruct
```

**Method 2: Using Aider-specific environment variables (also recognized by cecli):**
```bash
export AIDER_OPENAI_API_BASE=http://localhost:1234/v1
export AIDER_OPENAI_API_KEY=lm-studio
export AIDER_MODEL=openai/qwen3-coder-30b-a3b-instruct
```

Replace the model identifier with your actual model from LM Studio (e.g., `openai/qwen3-coder-30b-a3b-instruct` or `openai/openai/gpt-oss-120b`).

### Using Aider-CE with Mammouth AI

Aider-CE (cecli) is a fork of Aider and supports the same OpenAI-compatible configuration. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).

**Method 1: Using generic OpenAI environment variables:**
```bash
export OPENAI_API_BASE=https://api.mammouth.ai/v1
export OPENAI_API_KEY=your-mammouth-api-key
export OPENAI_MODEL=openai/gpt-4.1
```

**Method 2: Using Aider-specific environment variables (also recognized by cecli):**
```bash
export AIDER_OPENAI_API_BASE=https://api.mammouth.ai/v1
export AIDER_OPENAI_API_KEY=your-mammouth-api-key
export AIDER_MODEL=openai/gpt-4.1
```

Replace `gpt-4.1` with your preferred Mammouth AI model (e.g., `openai/claude-sonnet-4-6`, `openai/mistral`, `openai/deepseek-v3`). For the full list of available model IDs, visit [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

## Amp CLI

[Amp](https://ampcode.com/) is Sourcegraph's coding agent — available as a terminal CLI as well as extensions for VS Code, JetBrains, Neovim, and Zed, all sharing the same threads/sessions. It runs Sourcegraph-selected frontier models (Claude Opus/Sonnet, GPT-5) rather than letting you bring your own.

**Install with pnpm (recommended):**
```bash
pnpm add -g @ampcode/cli
```

**Install with npm:**
```bash
npm install -g @ampcode/cli
```

**Install with yarn:**
```bash
yarn global add @ampcode/cli
```

**Install with the official script (single-file executable):**
```bash
curl -fsSL https://ampcode.com/install.sh | bash
```

> The npm package was renamed from `@sourcegraph/amp` to `@ampcode/cli`; if a tutorial or lockfile still references the old name, switch to `@ampcode/cli`.

Authenticate interactively:
```bash
amp login
```
or, for CI/scripting, set an API key from [ampcode.com/settings](https://ampcode.com/settings):
```bash
export AMP_API_KEY="your-api-key"
```
Settings live in `~/.config/amp/settings.json` (override the path with `AMP_SETTINGS_FILE`); this is also where MCP servers are configured.

**Local LLMs and custom endpoints:** unlike most other harnesses in this document, Amp does not document support for custom OpenAI-compatible endpoints, bringing your own API key for a third-party model, or connecting to a local server like LM Studio or Ollama — it's built around Sourcegraph's own hosted model selection, similar to Copilot CLI and Cursor CLI below. If you need a local-LLM-capable harness, see [Aider](#aider-cli), [OpenCode](#opencode-cli), [Goose](#goose-cli), or most of the other tools in this document instead.

For more information, visit [ampcode.com](https://ampcode.com/) and the [Amp CLI guide](https://github.com/sourcegraph/amp-examples-and-guides/blob/main/guides/cli/README.md).

## Claude CLI

Claude CLI provides command-line access to Anthropic's Claude AI.

*Composio's two cents:* in its [agent-harness benchmark](https://composio.dev/content/best-ai-agent-harnesses) (25 business-app tasks, all 8 harnesses driving the same external Kimi K3 model via OpenRouter, to isolate the harness from the model), Claude Code posted a respectable 76% pass rate but the **highest cost per success ($1.96)** and **slowest median runtime (330.5s)** of the field — its file tools, shell access, MCP, and subagent support are mature, but that overhead was most visible when it wasn't driving Anthropic's own models.

Install using npm:
```bash
npm install -g @anthropic-ai/claude-cli
```

Configure with your Anthropic API key:
```bash
export ANTHROPIC_API_KEY=your-api-key-here
```

For more information, visit [Anthropic's documentation](https://www.anthropic.com/).

### Bash Completion on Linux (Claude)

Claude CLI does not ship with a built-in completion subcommand ([feature request #7738](https://github.com/anthropics/claude-code/issues/7738)). A community-maintained bash completion script is available at [cldotdev/claude-bash-completion](https://github.com/cldotdev/claude-bash-completion). It provides tab completion for all built-in slash commands (e.g. `/help`, `/model`, `/config`) and auto-discovers custom commands and skills from `~/.claude/commands/` and project-level `.claude/commands/` directories.

**Option A: Install to user bash-completion directory (recommended):**
```bash
git clone https://github.com/cldotdev/claude-bash-completion.git ~/.local/share/claude-bash-completion
ln -s ~/.local/share/claude-bash-completion/claude-completion.bash ~/.local/share/bash-completion/completions/claude
```

This integrates with the standard `bash-completion` framework and loads lazily. Ensure `bash-completion` is installed (`sudo apt install bash-completion` on Debian/Ubuntu).

**Option B: Install system-wide:**
```bash
git clone https://github.com/cldotdev/claude-bash-completion.git /tmp/claude-bash-completion
sudo cp /tmp/claude-bash-completion/claude-completion.bash /etc/bash_completion.d/claude
```

**Option C: Source directly in `~/.bashrc`:**
```bash
git clone https://github.com/cldotdev/claude-bash-completion.git ~/.local/share/claude-bash-completion
echo 'source ~/.local/share/claude-bash-completion/claude-completion.bash' >> ~/.bashrc
source ~/.bashrc
```

### Using Claude with Local LLMs via LM Studio

*Based on [Claude Code + LM Studio: Local Models with Cloud Code](https://www.youtube.com/watch?v=Cyn_Dm05_eU).*

LM Studio 0.4.1+ exposes an Anthropic-compatible `/v1/messages` endpoint, allowing Claude CLI (Claude Code) to connect directly to local models. This means you can run an LLM on your own machine and have Claude Code talk to it instead of the Anthropic cloud API.

**Prerequisites:**

- LM Studio 0.4.1 or later (download from [lmstudio.ai](https://lmstudio.ai/) — available for macOS, Windows, and Linux)
- A downloaded model in LM Studio (MLX format for Apple Silicon, GGUF for cross-platform portability)
- Claude CLI installed (`npm install -g @anthropic-ai/claude-cli` or `curl -fsSL https://docs.anthropic.com/claude-code/install.sh | sh`)

**Step 1: Load a model in LM Studio's Developer tab**

Open LM Studio and navigate to the **Developer** tab (not the Chat tab). This serves the model as an API endpoint accessible by other applications like Claude Code.

- Select your model and load it
- Increase the context length to maximum (e.g., 131,072 tokens) so Claude Code can pass files and full conversation context to the model
- The server will be reachable at `http://localhost:1234` by default

**Step 2: Create a settings file for Claude Code**

Create a JSON settings file at `~/.claude/lmstudio-settings.json`:

```json
{
  "env": {
    "ANTHROPIC_BASE_URL": "http://localhost:1234",
    "ANTHROPIC_AUTH_TOKEN": "",
    "ANTHROPIC_MODEL": "default-model"
  }
}
```

- **`ANTHROPIC_BASE_URL`**: Points to LM Studio's local server (default port `1234`, visible on the Developer tab)
- **`ANTHROPIC_AUTH_TOKEN`**: Leave empty (no authentication needed for local models, unless you configured an auth token in LM Studio)
- **`ANTHROPIC_MODEL`**: Use `"default-model"` to automatically use whatever model is loaded in LM Studio, avoiding the need to edit this file when swapping models

**Step 3: Launch Claude Code with the LM Studio settings**

```bash
claude --settings ~/.claude/lmstudio-settings.json
```

Claude Code will now connect to your local LM Studio instance instead of the Anthropic cloud API. You can verify the active model by opening the model selector inside Claude Code — it will show `default-model` (or your configured model name).

**Alternative: Using environment variables directly**

Instead of a settings file, you can export the variables in your shell:

```bash
export ANTHROPIC_BASE_URL=http://localhost:1234
export ANTHROPIC_API_KEY=lm-studio
claude
```

**Model size and performance considerations:**

- **Larger models produce better results** for agentic coding tasks. In testing, a 20B parameter model failed to properly update project dependencies, while a 120B parameter model succeeded.
- **Context length matters**: Claude Code sends large prompts (files, conversation history, tool context). Set the context length as high as your hardware allows.
- **Prompt processing time** can be significant with large contexts. Hardware with fast prompt processing (e.g., DGX Spark) helps. Apple Silicon Macs are faster at token generation but slower at prompt processing.
- **Memory requirements**: The model size on disk is smaller than the runtime memory footprint. Adding a large context window significantly increases memory usage. Use an LLM memory calculator to estimate your needs.
- **Model format**: On Apple Silicon, prefer MLX models for better performance. GGUF models are cross-platform (Mac, Windows, Linux) and portable.
- **Start small**: Try a smaller model first to understand the workflow before committing to large downloads.

**Note:** This requires LM Studio 0.4.1 or later, which added native Anthropic API compatibility. Earlier versions only expose the OpenAI-compatible endpoint and will not work with Claude CLI directly.

For more information, see [LM Studio + Claude Code integration guide](https://lmstudio.ai/blog/claudecode).

### Using Claude with Mammouth AI

[Mammouth AI](https://mammouth.ai/) exposes an OpenAI-compatible API, whereas Claude CLI (Claude Code) expects an **Anthropic-compatible** `/v1/messages` endpoint. Direct connection is therefore not supported without a translation layer. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).

**Option: LiteLLM proxy**

[LiteLLM](https://github.com/BerriAI/litellm) can act as a local proxy that translates Claude Code's Anthropic API calls to Mammouth AI's OpenAI-compatible format:

1. Install LiteLLM:
```bash
pip install litellm[proxy]
```

2. Create a LiteLLM config (`~/litellm-mammouth.yaml`):
```yaml
model_list:
  - model_name: claude-sonnet-4-6
    litellm_params:
      model: openai/claude-sonnet-4-6
      api_base: https://api.mammouth.ai/v1
      api_key: your-mammouth-api-key
```

3. Start the proxy:
```bash
litellm --config ~/litellm-mammouth.yaml --port 4000
```

4. Point Claude Code at the proxy via a settings file (`~/.claude/mammouth-settings.json`):
```json
{
  "env": {
    "ANTHROPIC_BASE_URL": "http://localhost:4000",
    "ANTHROPIC_AUTH_TOKEN": "litellm",
    "ANTHROPIC_MODEL": "claude-sonnet-4-6"
  }
}
```

5. Launch Claude Code:
```bash
claude --settings ~/.claude/mammouth-settings.json
```

**Note:** This is an advanced configuration that relies on a third-party proxy. For Mammouth AI model IDs (e.g., `gpt-4.1`, `claude-sonnet-4-6`, `mistral`), see the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

## Cline CLI

[Cline](https://cline.bot/cli) is the terminal counterpart to the popular Cline VS Code extension — one of the most-starred open-source coding agents (~67k GitHub stars). The CLI is fully scriptable/headless: pipe a `git diff` into it for an automated review, wire it into GitHub Actions, or drive it with `--json` output for programmatic parsing.

**Install with npm:**
```bash
npm install -g cline
```
Requires Node.js 22+, and either a free Cline account or your own API key from a supported provider (Anthropic, OpenAI, Google, and 30+ others via OpenAI-compatible endpoints, including local Ollama).

**Usage:**
```bash
cline auth                        # authenticate (Cline account, ClinePass, or your own provider key)
cline                              # interactive session
cline "your task here"             # one-shot task
cline -p "task"                    # plan-first mode
cline --auto-approve true "task"   # auto-approve tool calls
cline --json "task"                # structured output for scripts/CI
cline config                       # configure providers interactively
```
Override the model/provider per invocation with `-m/--model` and `-P/--provider`. Headless mode triggers automatically when `--json` is used, stdin is piped, or output is redirected — handy for cron jobs and CI without any extra flag.

For more information, visit the [Cline CLI overview](https://docs.cline.bot/usage/cli-overview) and [GitHub repository](https://github.com/cline/cline).

### Using Cline with Local LLMs via LM Studio and Ollama

Cline supports any OpenAI-compatible endpoint, with dedicated `ollama` and `lmstudio` provider ids (same ones the VS Code extension uses):

1. Start LM Studio's local server (port `1234`) or Ollama (port `11434`), and load a model.
2. Run `cline config` and select **Ollama** (base URL `http://localhost:11434`) or **LM Studio** (base URL `http://localhost:1234`) as the provider, then pick the detected model — or force it per-invocation:
```bash
cline -P ollama -m qwen3-coder:30b "your task"
cline -P lmstudio -m qwen3-coder-30b-a3b-instruct "your task"
```

### Using Cline with Mammouth AI

[Mammouth AI](https://mammouth.ai/) is an OpenAI-compatible gateway, reachable through Cline's generic OpenAI-compatible provider. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai). Run `cline config`, choose the OpenAI-compatible provider option, and set the base URL to `https://api.mammouth.ai/v1` with your Mammouth AI key; then select a model such as `gpt-4.1`, `claude-sonnet-4-6`, or `deepseek-v3` (see the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/) for the full list).

## Codex CLI

OpenAI Codex CLI for code generation and completion.

*Composio's two cents:* in the same [harness benchmark](https://composio.dev/content/best-ai-agent-harnesses) (25 business-app tasks, all harnesses driving an external Kimi K3 model), Codex posted the **lowest pass rate of the eight harnesses tested (68%)**, at a mid-pack $0.66 cost per success. Codex is purpose-built around OpenAI's own models and sandboxed approval flow, so this likely says more about pairing it with a non-OpenAI model than about the harness itself — worth factoring in if you plan to run Codex against Mammouth AI or a local model rather than OpenAI's API.

Install using npm:
```bash
npm install -g @openai/codex
```

Set your OpenAI API key:
```bash
export OPENAI_API_KEY=your-api-key-here
```

For more information, visit [OpenAI Codex CLI documentation](https://developers.openai.com/codex/cli/).

### Using Codex with Local LLMs via LM Studio

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

### Bash Completion on Linux (Codex)

Codex CLI has built-in shell completion support via the `codex completion` subcommand. Supported shells: bash, zsh, fish, PowerShell, elvish. Always specify the shell explicitly.

**Option A: Install to user bash-completion directory (recommended):**
```bash
mkdir -p ~/.local/share/bash-completion/completions
codex completion bash > ~/.local/share/bash-completion/completions/codex
```

This integrates with the standard `bash-completion` framework and loads lazily. Ensure `bash-completion` is installed (`sudo apt install bash-completion` on Debian/Ubuntu).

**Option B: Install system-wide:**
```bash
sudo bash -c 'codex completion bash > /usr/share/bash-completion/completions/codex'
```

**Option C: Source dynamically in `~/.bashrc`:**
```bash
echo 'eval "$(codex completion bash)"' >> ~/.bashrc
source ~/.bashrc
```

This regenerates the completion script on every new shell, staying in sync after upgrades at the cost of a small startup delay.

### Using Codex with Mammouth AI

[Mammouth AI](https://mammouth.ai/) is a drop-in replacement for the OpenAI endpoint in Codex CLI. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).

**Method 1: Using environment variables:**
```bash
export OPENAI_API_BASE=https://api.mammouth.ai/v1
export OPENAI_API_KEY=your-mammouth-api-key
export OPENAI_MODEL=gpt-4.1
```

**Method 2: Using configuration file (`~/.codex/config.toml`):**

Create or edit `~/.codex/config.toml`:
```toml
# Codex CLI Configuration for Mammouth AI

[model_providers.mammouth]
name = "Mammouth AI"
base_url = "https://api.mammouth.ai/v1"
env_key = "MAMMOUTH_API_KEY"

[profiles.mammouth-gpt]
model_provider = "mammouth"
model = "gpt-4.1"

[profiles.mammouth-claude]
model_provider = "mammouth"
model = "claude-sonnet-4-6"
```

Export your key and use the profile:
```bash
export MAMMOUTH_API_KEY=your-mammouth-api-key
codex --profile mammouth-gpt
# or
codex --profile mammouth-claude
```

For a full list of available Mammouth AI model IDs, visit the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

## Copilot CLI

GitHub Copilot CLI brings GitHub Copilot to the command line.

Install using npm:
```bash
npm install -g @githubnext/github-copilot-cli
```

Authenticate with GitHub:
```bash
github-copilot-cli auth
```

For more information, visit [GitHub Copilot CLI documentation](https://docs.github.com/en/copilot/concepts/agents/about-copilot-cli).

### Bash Completion on Linux (Copilot)

The current Copilot CLI (`copilot` command from `@github/copilot`) does not ship with a built-in completion subcommand. The previous `gh copilot alias` mechanism was [removed in v0.0.389](https://github.com/github/copilot-cli/issues/1063) and no replacement has been added yet. Since the CLI is primarily an interactive REPL with slash commands inside the session, traditional shell completion is of limited value.

If you also use the `gh` CLI (which previously hosted the Copilot extension), you can enable completion for all `gh` subcommands:

**Option A: Install to user bash-completion directory (recommended):**
```bash
mkdir -p ~/.local/share/bash-completion/completions
gh completion -s bash > ~/.local/share/bash-completion/completions/gh
```

This integrates with the standard `bash-completion` framework and loads lazily. Ensure `bash-completion` is installed (`sudo apt install bash-completion` on Debian/Ubuntu).

**Option B: Install system-wide:**
```bash
sudo bash -c 'gh completion -s bash > /usr/share/bash-completion/completions/gh'
```

**Option C: Source dynamically in `~/.bashrc`:**
```bash
echo 'eval "$(gh completion -s bash)"' >> ~/.bashrc
source ~/.bashrc
```

This regenerates the completion script on every new shell, staying in sync after upgrades at the cost of a small startup delay.

### Using Copilot with Local LLMs via LM Studio

The `gh copilot` command is locked to GitHub's hosted backend and does not support custom API endpoints. However, for the broader GitHub Copilot SDK ecosystem, custom providers with OpenAI-compatible base URLs are supported via BYOK (Bring Your Own Key) configuration.

For LM Studio integration, a proxy like LiteLLM can bridge the connection between GitHub Copilot and your local LM Studio instance:

1. Start LM Studio and load your preferred model
2. Enable the Local Server feature in LM Studio (default port: 1234)
3. Set up LiteLLM as a proxy layer between Copilot and LM Studio

For a detailed walkthrough of this advanced/experimental setup, see [Using LiteLLM with GitHub Copilot](https://parsiya.net/blog/litellm-ghc-aad/).

**Note:** This is an advanced configuration that relies on third-party proxy software and is not officially supported by GitHub.

### Using Copilot with Mammouth AI

The `gh copilot` command is locked to GitHub's hosted backend and does not support custom API endpoints. As with LM Studio, a [LiteLLM](https://github.com/BerriAI/litellm) proxy can bridge GitHub Copilot to Mammouth AI's OpenAI-compatible endpoint. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).

1. Install LiteLLM and start a proxy pointed at Mammouth AI:
```bash
pip install litellm[proxy]
litellm --model openai/gpt-4.1 --api_base https://api.mammouth.ai/v1 --api_key your-mammouth-api-key --port 4000
```

2. Configure GitHub Copilot or your IDE's Copilot extension to use `http://localhost:4000` as its custom endpoint.

For a detailed walkthrough of this pattern, see [Using LiteLLM with GitHub Copilot](https://parsiya.net/blog/litellm-ghc-aad/).

**Note:** This is an advanced configuration that relies on third-party proxy software and is not officially supported by GitHub. For Mammouth AI model IDs, see the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

## Crush CLI

[Crush](https://github.com/charmbracelet/crush) (`crush`) is Charm's open-source, terminal-native coding agent — from the makers of Bubble Tea/Glow/Gum — built to work with a wide range of models across OpenAI, Anthropic, and other providers, with the ability to switch models mid-session while preserving context.

**Install with Homebrew:**
```bash
brew install charmbracelet/tap/crush
```

**Install with npm:**
```bash
npm install -g @charmland/crush
```

**Install on Debian/Ubuntu (apt):**
```bash
sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://repo.charm.sh/apt/gpg.key | sudo gpg --dearmor -o /etc/apt/keyrings/charm.gpg
echo "deb [signed-by=/etc/apt/keyrings/charm.gpg] https://repo.charm.sh/apt/ * *" | sudo tee /etc/apt/sources.list.d/charm.list
sudo apt update && sudo apt install crush
```

Configuration lives in `.crushrc` (project root, checked before the global `~/.config/crush/crushrc`) using Bash syntax with Crush-specific builtins. For more information, visit the [Crush GitHub repository](https://github.com/charmbracelet/crush).

### Using Crush with Local LLMs via LM Studio and Ollama

Crush has first-class commands for registering local providers, rather than requiring you to hand-edit a config file:

```bash
# Ollama
provider add ollama \
  --name Ollama \
  --type ollama \
  --base-url "http://localhost:11434/v1/"

# LM Studio
provider add lmstudio \
  --name "LM Studio" \
  --type openai-compat \
  --base-url "http://localhost:1234/v1"

# Register a model on a provider (repeat per model you want available)
model add ollama/qwen3-coder:30b --name "Qwen3 Coder 30B" --context-window 128000
```

### Using Crush with Mammouth AI

[Mammouth AI](https://mammouth.ai/) is added the same way, as an `openai-compat` provider. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).
```bash
provider add mammouth \
  --name "Mammouth AI" \
  --type openai-compat \
  --base-url "https://api.mammouth.ai/v1" \
  --api-key "your-mammouth-api-key"

model add mammouth/gpt-4.1 --name "GPT-4.1"
```
For the full model list, visit the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

## Cursor CLI

[Cursor CLI](https://cursor.com/cli) (binary name `agent`, not `cursor` or `cursor-agent` — despite that being the package/product name) brings Cursor's coding agent to the terminal, GitHub Actions, and scripts, running headless over SSH or in tmux. It's currently in beta.

**Install on macOS/Linux/WSL:**
```bash
curl https://cursor.com/install -fsS | bash
```

**Install on Windows (PowerShell):**
```powershell
irm 'https://cursor.com/install?win32=true' | iex
```

**Usage:**
```bash
agent                       # interactive session
agent -p "your task"        # headless, for CI/scripts
agent --model "gpt-5"       # pick a model from Cursor's catalog
```
Authenticate with `export CURSOR_API_KEY=your_api_key_here` for scripted/headless use.

**Local LLMs and custom endpoints:** Cursor CLI is built around Cursor's own hosted model catalog and subscription — like Amp CLI and Copilot CLI above, it does not document support for a custom OpenAI-compatible endpoint or a local server like LM Studio/Ollama. If local-model support is a requirement, reach for one of the other harnesses in this document instead.

For more information, visit the [Cursor CLI docs](https://cursor.com/docs/cli/overview).

## DeepSeek CLI

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

### Bash Completion on Linux (DeepSeek)

DeepSeek CLI does not currently provide built-in bash completion support, and no community-maintained completion scripts are available at this time.

### Using DeepSeek with Local LLMs via LM Studio

DeepSeek CLI already supports local models via Ollama (documented above). LM Studio's OpenAI-compatible API serves on the same `/v1/chat/completions` endpoint format and can be used as an alternative local backend:

1. Start LM Studio and load a DeepSeek model (or any compatible model)
2. Enable the Local Server feature in LM Studio (default port: 1234)
3. Configure environment variables:

```bash
export DEEPSEEK_USE_LOCAL=true
```

Point DeepSeek CLI to LM Studio at `http://localhost:1234/v1` instead of Ollama's default `http://localhost:11434`.

**Note:** Compatibility depends on the DeepSeek CLI version and may require loading a DeepSeek-family model in LM Studio (e.g., `deepseek-r1-0528-qwen3-8b` or `deepseek-coder`). Check the [DeepSeek CLI GitHub repository](https://github.com/holasoymalva/deepseek-cli) for the latest supported configurations.

### Using DeepSeek with Mammouth AI

[Mammouth AI](https://mammouth.ai/) includes DeepSeek models (e.g., `deepseek-v3`, `deepseek-v3-reasoning`) through its OpenAI-compatible API. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).

**Configuration:**
```bash
export DEEPSEEK_USE_LOCAL=false
export OPENAI_API_BASE=https://api.mammouth.ai/v1
export OPENAI_API_KEY=your-mammouth-api-key
```

**Note:** Exact environment variable support varies by DeepSeek CLI version. If the above does not work, check the [DeepSeek CLI GitHub repository](https://github.com/holasoymalva/deepseek-cli) for the current configuration options for custom base URLs.

## DeepSeek Harness CLI

[DeepSeek Harness](https://github.com/deepseek-ai/deepseek-harness) (`dsh`) is a separate, unrelated project from the [DeepSeek CLI](#deepseek-cli) above — an open-source, plugin-based agent framework built on DeepSeek's Cordis composability framework. Rather than a chat-only terminal REPL, it runs a local Web UI. The project is currently in **developer preview**, so expect rapid, potentially compatibility-breaking changes.

**Run instantly with npx (no installation required):**
```bash
npx @deepseek-ai/dsh web
```
This launches the Web UI at `http://127.0.0.1:3080` and opens it in your default browser. Pass `--no-open` to start the server without launching a browser (useful on a headless box reached over SSH port-forwarding).

**Install from source:**
```bash
git clone https://github.com/deepseek-ai/deepseek-harness.git
cd deepseek-harness
pnpm install
pnpm run build
pnpm dsh web
```
Requires Node.js and [pnpm](https://pnpm.io/).

For more information, visit the [DeepSeek Harness GitHub repository](https://github.com/deepseek-ai/deepseek-harness) and its [documentation site](https://deepseek-harness.github.io/deepseek-harness/).

### Configuring DeepSeek Harness

DeepSeek's own API, OpenAI, and any local/self-hosted OpenAI-compatible server are all configured the same way, from **Settings → Models** in the Web UI:

- **Add provider** installs one of the providers `dsh` ships with (provider ids such as `anthropic`, `openai`, `moonshotai` for Kimi, or `zai` for GLM) — just supply its API key; the installed catalog supplies the endpoint, protocol, and model list.
- **Add a custom provider** is for anything else: a company gateway, a self-hosted server, or a local LLM runner like LM Studio or Ollama. It needs a lowercase Provider ID, a base URL, an API protocol (`openai-completions` for OpenAI Chat Completions, `openai-responses` for the OpenAI Responses API, or `anthropic-messages` for the Anthropic Messages API), a credential, and at least one model. Use **Fetch available models** under **Model catalog** to auto-discover the model list from the endpoint's `GET /models`, or add model ids by hand if discovery doesn't work.

API keys entered through the UI are stored in `$DSH_HOME/.credentials.yaml` (`$DSH_HOME` defaults to `~/.dsh`); `settings.yaml` (`$DSH_HOME/settings.yaml`) retains only a credential reference (`apiKeyEnv`, an environment-variable name). Fields the Web UI form doesn't expose — reasoning-effort levels, image input (`input: [text, image]`), request-compatibility switches (`compat.*`) — are set by editing `$DSH_HOME/settings.yaml` directly, or via **Open configuration file** in the Settings header when the browser runs on the same machine as the server. The adapters re-read the file on the next request, so nothing needs a restart.

### Using DeepSeek Harness with the DeepSeek Cloud API

Open **Settings → Models** and enter your API key on the DeepSeek card. Get a key from the [DeepSeek Platform](https://platform.deepseek.com/api_keys). The key is read from the `DEEPSEEK_API_KEY` environment variable (and, for a non-default endpoint, `DEEPSEEK_BASE_URL`).

To set the default reasoning effort the model picker starts from, edit `$DSH_HOME/settings.yaml`:
```yaml
llm-deepseek:
  reasoningEffort: max   # off | low | high | max
```

### Using DeepSeek Harness with OpenAI

Choose **Add provider** and pick `openai` from the built-in catalog, then supply a credential backed by `OPENAI_API_KEY`. This talks to OpenAI's own Chat Completions/Responses endpoints without needing a custom provider.

To route OpenAI through a proxy or gateway instead, add a **custom provider** (or edit `$DSH_HOME/settings.yaml`):
```yaml
llm-pi-ai:
  providers:
    openai:
      apiKeyEnv: OPENAI_API_KEY
      api: openai-responses      # or openai-completions
      baseURL: https://api.openai.com/v1
      models:
        - id: gpt-4.1
```

### Using DeepSeek Harness with Local LLMs via LM Studio

LM Studio's local server speaks the OpenAI Chat Completions protocol, so it is added as a custom provider:

1. Start LM Studio and load your preferred model.
2. Enable the Local Server feature in LM Studio (default port `1234`).
3. In the Web UI, choose **Add a custom provider** (or edit `$DSH_HOME/settings.yaml` directly):
```yaml
llm-pi-ai:
  providers:
    lm-studio:
      apiKeyEnv: LM_STUDIO_API_KEY
      api: openai-completions
      baseURL: http://localhost:1234/v1
      models:
        - id: qwen3-coder-30b-a3b-instruct
        - id: openai/gpt-oss-120b
```
4. Export a value for the credential env var — LM Studio itself doesn't check it, but `dsh` still requires the referenced variable to be set or it refuses the request with `MISSING_CREDENTIAL`:
```bash
export LM_STUDIO_API_KEY=lm-studio
```
5. Select the model from the picker, or use **Fetch available models** to pull LM Studio's currently-loaded model list automatically. Replace the model ids above with whatever you've actually loaded.

If LM Studio refuses requests from a reasoning-capable model (its system prompt sent as the `developer` role, or an output cap it doesn't recognize), add compatibility switches to the route:
```yaml
      compat:
        supportsDeveloperRole: false
        maxTokensField: max_tokens
```

### Using DeepSeek Harness with Local LLMs via Ollama

Ollama also exposes an OpenAI-compatible endpoint, at `/v1` on its default port `11434`:

1. Install and start Ollama:
```bash
# macOS
brew install ollama
# Linux
curl -fsSL https://ollama.ai/install.sh | sh

ollama serve
```
2. Pull a model:
```bash
ollama pull deepseek-coder:6.7b     # or qwen2.5-coder, deepseek-r1, etc.
```
3. Add it as a custom provider in `$DSH_HOME/settings.yaml`:
```yaml
llm-pi-ai:
  providers:
    ollama:
      apiKeyEnv: OLLAMA_API_KEY
      api: openai-completions
      baseURL: http://localhost:11434/v1
      models:
        - id: deepseek-coder:6.7b
```
4. Export a dummy credential (Ollama ignores it, but `dsh` still needs the env var set):
```bash
export OLLAMA_API_KEY=ollama
```

**Docker note:** if `dsh web` runs inside a container while Ollama runs on the host, use `http://host.docker.internal:11434/v1` instead of `localhost`.

### Using DeepSeek Harness with Mammouth AI

[Mammouth AI](https://mammouth.ai/) is an OpenAI-compatible cloud gateway, so it plugs in the same way as a local server — just with a cloud `baseURL` and a real key. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).

```yaml
llm-pi-ai:
  providers:
    mammouth:
      apiKeyEnv: MAMMOUTH_API_KEY
      api: openai-completions
      baseURL: https://api.mammouth.ai/v1
      models:
        - id: gpt-4.1
        - id: claude-sonnet-4-6
        - id: deepseek-v3
```
```bash
export MAMMOUTH_API_KEY=your-mammouth-api-key
```
For the full list of available model IDs, visit the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

### Troubleshooting (DeepSeek Harness)

- **`MISSING_CREDENTIAL`** — Store the provider key through the Models page, or export the environment variable named in `apiKeyEnv` (LM Studio and Ollama don't validate it, but `dsh` still requires it to be set).
- **`UNKNOWN_MODEL`** — Select a configured model, or add the missing model id to the custom provider's `models` list.
- **Fetching available models returns 401** — Check the key. Discovery calls the OpenAI-compatible `GET /models`; enter models by hand for endpoints (including some Ollama versions) that don't provide it.
- **Fetching available models reports neither a `data` array nor a `models` object** — The endpoint's listing format isn't one discovery reads. Enter the models by hand instead.
- **The gateway refuses every request although the key and URL are right** — Its request shape differs from OpenAI's. Start with `compat.supportsDeveloperRole: false` and `compat.maxTokensField: max_tokens` on the route.
- **Only reasoning models fail** — pi-ai sends their system prompt as the `developer` role, which the gateway rejects. Set `compat.supportsDeveloperRole: false`.

For the full guide (image input, reasoning-effort mapping, more compat switches), see the upstream [Configure models guide](https://github.com/deepseek-ai/deepseek-harness/blob/master/docs/user/guide/providers.md).

## Factory CLI

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

### Using Factory with Local LLMs via LM Studio

Factory supports BYOK (Bring Your Own Key) with custom providers. You can use the `/model` command or configuration to add a custom provider pointing to LM Studio:

1. Start LM Studio and load your preferred model
2. Enable the Local Server feature in LM Studio (default port: 1234)
3. Configure a custom provider in Factory:

- **Provider type:** `generic-chat-completion-api`
- **Base URL:** `http://localhost:1234/v1`
- **API Key:** `lm-studio`

For more information on BYOK configuration, see [Factory BYOK documentation](https://docs.factory.ai/cli/byok/overview).

### Using Factory with Mammouth AI

Factory's BYOK (Bring Your Own Key) feature supports any OpenAI-compatible provider. Configure [Mammouth AI](https://mammouth.ai/) as a custom provider using the `/model` command or Factory's configuration UI. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).

**Custom provider settings:**
- **Provider type:** `generic-chat-completion-api`
- **Base URL:** `https://api.mammouth.ai/v1`
- **API Key:** `your-mammouth-api-key`

This gives Factory access to all models available on Mammouth AI (GPT-4, Claude, Gemini, Mistral, DeepSeek, and more) under your subscription. For more information on BYOK configuration, see [Factory BYOK documentation](https://docs.factory.ai/cli/byok/overview).

## Gemini CLI (Deprecated)

Google's original Gemini AI CLI tool (`@google/gemini-cli`).

> **⚠️ Deprecated.** Google announced retirement of Gemini CLI at I/O on May 19, 2026, and shut it down for Google AI Pro/Ultra users and free individual Gemini Code Assist on **June 18, 2026** — the `gemini` command simply stops serving requests on affected tiers, with no advance per-user warning. Only Gemini Code Assist Standard/Enterprise and Gemini Enterprise Agent Platform API-key customers are unaffected. Its replacement is **[Antigravity CLI](#antigravity-cli)**, covered below — see that section, including [Migrating from Gemini CLI](#migrating-from-gemini-cli). The rest of this section is kept for reference (e.g. if you're on an unaffected enterprise tier or maintaining legacy automation).

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

### Bash Completion on Linux (Gemini)

Gemini CLI does not yet have built-in bash completion support. There is an open [feature request (#1855)](https://github.com/google-gemini/gemini-cli/issues/1855) with a draft PR. Gemini CLI is built on [oclif](https://oclif.io/), which supports completions via plugin, so native support is expected in a future release.

### Using Gemini with Local LLMs via LM Studio

Gemini CLI does not natively support custom OpenAI-compatible endpoints. It is designed to work exclusively with Google's Gemini API.

**Feature request:** [#16504](https://github.com/google-gemini/gemini-cli/issues/16504) — Add support for custom/local model endpoints.

Third-party proxy solutions exist (e.g., [geminicli2api](https://github.com/search?q=geminicli2api), [gemini-openai-proxy](https://github.com/search?q=gemini-openai-proxy)) that translate between the Gemini API protocol and OpenAI-compatible APIs, but these are not officially supported by Google.

### Using Gemini with Mammouth AI

Gemini CLI is designed to work exclusively with Google's Gemini API and does not natively support custom OpenAI-compatible endpoints. Direct connection to [Mammouth AI](https://mammouth.ai/) is not supported.

**Note:** Mammouth AI offers Gemini models (e.g., `gemini-2.5-flash`, `gemini-2.5-pro`) through its OpenAI-compatible API. To use them, choose a CLI tool that supports custom base URLs (e.g., Aider, OpenCode, Goose) and select a Gemini model ID from [PROVIDERS.md — Available Gemini Models](./PROVIDERS.md#available-gemini-models-gemini-api).

## Antigravity CLI

[Antigravity CLI](https://antigravity.google/) is Google's replacement for Gemini CLI — a closed-source, Go-based rewrite (command name `agy`, not `gemini`) built as the terminal-first surface of the wider **Antigravity** agentic development platform (which also ships an agentic IDE, IDE extensions, and a Python SDK). It's a fresh install with its own binary, config layout, and quota model (weekly rather than daily limits) rather than an in-place upgrade — see [Migrating from Gemini CLI](#migrating-from-gemini-cli) below.

**Install on macOS/Linux:**
```bash
curl -fsSL https://antigravity.google/cli/install.sh | bash
```
Installs to `~/.local/bin/agy`.

**Install on Windows (PowerShell):**
```powershell
irm https://antigravity.google/cli/install.ps1 | iex
```

**Install on Windows (CMD):**
```cmd
curl -fsSL https://antigravity.google/cli/install.cmd -o install.cmd && install.cmd && del install.cmd
```

Once installed, run `agy` to start. For the full desktop app (Antigravity 2.0, a GUI "command center" for managing multiple local agents) rather than just the CLI, download it from [antigravity.google/download](https://antigravity.google/download) — macOS (Apple Silicon/Intel `.dmg`, macOS 12+), Windows (x64/ARM64 `.exe`, Windows 10+), and Linux (x64/ARM64 `.tar.gz`, glibc ≥ 2.28) builds are available.

For more information, visit the [Antigravity CLI GitHub repository](https://github.com/google-antigravity/antigravity-cli) and the [Getting Started docs](https://antigravity.google/docs/cli/getting-started/).

### Migrating from Gemini CLI

Antigravity CLI keeps your skills, hooks, subagents, and extensions (now called Antigravity plugins) conceptually, but does **not** read Gemini CLI's old config directly — it needs re-authentication and re-testing, not a drop-in swap:

1. Install the `agy` binary (above) alongside, or in place of, `gemini`.
2. Authenticate with the same Google account used for Gemini CLI (see [Authentication](#authentication-antigravity) below).
3. Walk through Google's migration guide at [antigravity.google/docs/gcli-migration](https://antigravity.google/docs/gcli-migration) to carry over settings.
4. **Update automation:** any script, CI/CD job, or cron task invoking `gemini` breaks outright on an affected tier once Gemini CLI stops serving requests — audit for `gemini` invocations and repoint them at `agy` before relying on it unattended.
5. Re-test MCP servers, custom commands, Agent Skills, Hooks, and Subagents under the new harness — Antigravity CLI is explicitly a multi-agent orchestration harness, not a like-for-like conversational REPL, so some behaviors differ.

### Authentication (Antigravity)

**Google account sign-in (default):** on a local machine, `agy` opens your default browser to sign in automatically. Over SSH, it prints an authorization URL to open in a local browser, plus a short code to paste back into the terminal.

**API key (headless/CI):** for automation, authenticate with a Gemini API key instead of an account session — with an API key, requests go straight to the Gemini API and no account session is established. Create a key at [Google AI Studio](https://aistudio.google.com/app/api-keys), then set it in `~/.gemini/antigravity-cli/settings.json`:
```json
{
    "modelProvider": "gemini"
}
```
```bash
export GEMINI_API_KEY=your-api-key-here
```
`GEMINI_API_KEY` alone has no effect without `modelProvider` set in `settings.json`. `/logout` ends an account session but does nothing under API-key auth.

### Using Antigravity with Local LLMs via LM Studio

Like Gemini CLI before it, Antigravity CLI does not support OpenAI-compatible endpoints. Its one endpoint override, `GOOGLE_GEMINI_BASE_URL`, redirects requests to a different **Gemini-protocol-compatible** endpoint (e.g. an internal Gemini API proxy) — it does not speak the OpenAI Chat Completions format LM Studio (or Ollama) exposes, so pointing it at `http://localhost:1234/v1` will not work. As with Gemini CLI, a translation proxy would be required, and none is officially supported by Google as of this writing.

### Using Antigravity with Mammouth AI

For the same reason as LM Studio above, Antigravity CLI cannot connect directly to [Mammouth AI](https://mammouth.ai/)'s OpenAI-compatible endpoint — `GOOGLE_GEMINI_BASE_URL` only accepts a Gemini-protocol endpoint. Mammouth AI does offer Gemini models (e.g., `gemini-2.5-flash`, `gemini-2.5-pro`) through its OpenAI-compatible API; to use them, pick a CLI tool that supports custom OpenAI-compatible base URLs (e.g., Aider, OpenCode, Goose) instead, and select a Gemini model ID from [PROVIDERS.md — Available Gemini Models](./PROVIDERS.md#available-gemini-models-gemini-api).

## Grok CLI

Grok CLI ([`@vibe-kit/grok-cli`](https://github.com/superagent-ai/grok-cli)) is a community, open-source conversational AI CLI tool powered by xAI's Grok API, with intelligent text editor capabilities and tool usage.

> **⚠️ Naming collision:** this is a different, unofficial project from xAI's own **[Grok Build](#grok-build-cli)** (`xai-org/grok-build`), covered below. Confusingly, both install a binary named `grok` and both default to a config directory under `~/.grok/` (`user-settings.json` here vs. `config.toml` for Grok Build) — installing both on the same machine means the second install wins the `grok` name on your `PATH`. Composio's [harness benchmark](https://composio.dev/content/best-ai-agent-harnesses) tested the official **Grok Build**, not this community CLI — see the [Grok Build CLI](#grok-build-cli) section for those numbers.

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

### Using Grok with Local LLMs via LM Studio

Grok CLI supports custom API endpoints via its configuration file or CLI flags, allowing you to point it at LM Studio's OpenAI-compatible API:

1. Start LM Studio and load your preferred model
2. Enable the Local Server feature in LM Studio (default port: 1234)
3. Configure using one of the following methods:

**Method 1: Edit `~/.grok/user-settings.json`:**
```json
{
  "baseURL": "http://localhost:1234/v1",
  "apiKey": "lm-studio"
}
```

**Method 2: Use the `--base-url` CLI flag:**
```bash
grok --base-url http://localhost:1234/v1
```

The default `baseURL` is `https://api.x.ai/v1`. Replace the model identifier with your actual model from LM Studio.

### Using Grok with Mammouth AI

Grok CLI supports custom API endpoints via its configuration file or `--base-url` flag. You can point it at [Mammouth AI](https://mammouth.ai/)'s OpenAI-compatible endpoint to access its full model catalog. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).

**Method 1: Edit `~/.grok/user-settings.json`:**
```json
{
  "baseURL": "https://api.mammouth.ai/v1",
  "apiKey": "your-mammouth-api-key"
}
```

**Method 2: Use the `--base-url` CLI flag:**
```bash
grok --base-url https://api.mammouth.ai/v1 --api-key your-mammouth-api-key
```

For available Mammouth AI model IDs, visit the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

## Grok Build CLI

[Grok Build](https://github.com/xai-org/grok-build) is xAI's own official coding-agent harness and TUI — fullscreen, mouse-interactive, extensible — distinct from the community [Grok CLI](#grok-cli) above (see the naming-collision warning there). It's available to SuperGrok and X Premium Plus subscribers.

*Composio's two cents:* in its [harness benchmark](https://composio.dev/content/best-ai-agent-harnesses) (25 business-app tasks, all harnesses driving an external Kimi K3 model via OpenRouter), Grok Build had a fast median runtime (196.2s) but made by far the **most tool calls of any harness tested (402)** without a pass-rate edge to show for it (72%, tied for mid-pack) — a sign it's more trigger-happy with tool invocations than its peers rather than more efficient with them.

**Install on macOS/Linux:**
```bash
curl -fsSL https://x.ai/cli/install.sh | bash
```

**Install on Windows (PowerShell):**
```powershell
irm https://x.ai/cli/install.ps1 | iex
```

The installer places the `grok` binary on your `PATH` and sets up `~/.grok/` as the config directory. On first launch it opens your browser to authenticate with your xAI account; run `grok --plan` to force plan-first execution (index the repo, propose a step-by-step plan, then wait for confirmation before touching files).

For more information, visit the [Grok Build GitHub repository](https://github.com/xai-org/grok-build) and [documentation](https://docs.x.ai/build/overview).

### Using Grok Build with Custom/Local Endpoints

Grok Build defaults to xAI's own `grok-4.6` model, but a custom model/provider can be added to `~/.grok/config.toml` (`%USERPROFILE%\.grok\config.toml` on Windows):
```toml
[model.lm-studio]
model = "qwen3-coder-30b-a3b-instruct"
base_url = "http://localhost:1234/v1"
name = "LM Studio (local)"
env_key = "LM_STUDIO_API_KEY"

[models]
default = "lm-studio"
```
```bash
export LM_STUDIO_API_KEY=lm-studio
```
Select it explicitly, or verify it was picked up:
```bash
grok -p "Hello" -m lm-studio
grok inspect    # confirms Grok Build sees the custom model
```
The same `[model.<name>]` pattern works for Ollama (`base_url = "http://localhost:11434/v1"`) or [Mammouth AI](https://mammouth.ai/) (`base_url = "https://api.mammouth.ai/v1"`, `env_key = "MAMMOUTH_API_KEY"`) — see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai) for account setup. This is documented but not as heavily exercised as xAI's own models, so treat it as functional rather than a first-class, fully-tuned path.

## Goose CLI

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

### Using Goose with Local LLMs via LM Studio

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

### Bash Completion on Linux (Goose)

Goose has built-in shell completion support via the `goose completion` subcommand. Supported shells: bash, zsh, fish, elvish, PowerShell.

**Option A: Install to user bash-completion directory (recommended):**
```bash
mkdir -p ~/.local/share/bash-completion/completions
goose completion bash > ~/.local/share/bash-completion/completions/goose
```

This integrates with the standard `bash-completion` framework and loads lazily. Ensure `bash-completion` is installed (`sudo apt install bash-completion` on Debian/Ubuntu).

**Option B: Install system-wide:**
```bash
sudo bash -c 'goose completion bash > /usr/share/bash-completion/completions/goose'
```

**Option C: Source dynamically in `~/.bashrc`:**
```bash
echo 'eval "$(goose completion bash)"' >> ~/.bashrc
source ~/.bashrc
```

This regenerates the completion script on every new shell, staying in sync after upgrades at the cost of a small startup delay.

### Using Goose with Mammouth AI

Goose supports OpenAI-compatible providers via the `openai` provider type and a custom base URL. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).

**Configuration:**
```bash
export GOOSE_PROVIDER=openai
export OPENAI_API_BASE=https://api.mammouth.ai/v1
export OPENAI_API_KEY=your-mammouth-api-key
export GOOSE_MODEL=gpt-4.1
```

Replace `gpt-4.1` with your preferred model. Mammouth AI supports models from multiple providers — for example `claude-sonnet-4-6`, `mistral`, or `deepseek-v3`. For the full model list, visit the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

## Hermes Agent CLI

[Hermes Agent](https://github.com/NousResearch/hermes-agent) (`hermes`) is Nous Research's open-source (MIT), model-agnostic agent — it runs as a CLI/TUI, but also as a gateway that connects the same agent to Telegram, Discord, Slack, WhatsApp, Signal, and email. It leans on a built-in learning loop (skills created and improved from experience, cross-session memory) rather than being a single-shot coding assistant.

*Composio's two cents:* in its [harness benchmark](https://composio.dev/content/best-ai-agent-harnesses) (25 business-app tasks, all 8 harnesses driving the same external Kimi K3 model), Hermes Agent had the **lowest cost per success of any harness tested ($0.46)** and the second-fastest median runtime (164.0s), with an 80% pass rate — third-best in the field, behind Oh My Pi and Kimi Code. A strong efficiency-per-dollar pick, if not quite the top for raw reliability.

**Install on Linux, macOS, WSL2, or Termux:**
```bash
curl -fsSL https://hermes-agent.nousresearch.com/install.sh | bash
```

**Install on Windows (PowerShell):**
```powershell
iex (irm https://hermes-agent.nousresearch.com/install.ps1)
```
The Windows installer bundles Git Bash, Python 3.11, Node.js, ripgrep, and ffmpeg alongside `hermes` itself.

**Install via pip:**
```bash
pip install -U hermes-agent
```

**Install via npm:**
```bash
npm install -g hermes-agent
```

For more information, visit the [Hermes Agent GitHub repository](https://github.com/NousResearch/hermes-agent) and [documentation](https://hermes-agent.nousresearch.com/docs/).

### Configuring Hermes Agent

```bash
hermes setup    # interactive configuration wizard (auth + default model)
hermes model    # provider + model selector; also how you add new providers later
hermes          # start an interactive chat session
```

Settings persist to `~/.hermes/config.yaml`; secrets can also be kept in `~/.hermes/.env`. `hermes` recognizes dozens of first-class `--provider` values (`nous`, `openrouter`, `openai-api`, `anthropic`, `gemini`, `deepseek`, `ollama-cloud`, `lmstudio`, `bedrock`, and many more), and anything else OpenAI-compatible via a **custom endpoint** — either interactively (`hermes model` → *Custom endpoint*) or directly in `config.yaml`:
```yaml
providers:
  my-gateway:
    api: https://llm.internal.example.com/v1
    api_key: "${MY_GATEWAY_API_KEY}"
```
Switch providers mid-session with `/model <provider>:<model>` (e.g. `/model custom:local:qwen-2.5`). Hermes auto-detects local endpoints (loopback/private addresses) and relaxes streaming timeouts for them (read timeout raised from 120s to 1800s); for a slow CPU-only backend you can also raise it explicitly:
```bash
export HERMES_STREAM_READ_TIMEOUT=1800
```

### Using Hermes Agent with the Nous Portal / OpenRouter (Online)

The Nous Portal is Hermes' own hosted option — a single subscription covering 300+ models — and is what `hermes setup --portal` OAuths into in one step:
```bash
hermes setup --portal
```
OpenRouter works the same way as a first-class provider:
```bash
hermes model    # choose "openrouter", paste your OpenRouter API key
```

### Using Hermes Agent with OpenAI

OpenAI is a first-class provider (`--provider openai-api`); select it through the wizard:
```bash
hermes model    # choose "openai-api", paste your OPENAI_API_KEY
```
or force it per-invocation:
```bash
hermes chat --provider openai-api -m gpt-4.1
```

### Using Hermes Agent with Local LLMs via LM Studio

LM Studio is a first-class provider (`lmstudio`), with JIT model loading, higher context (64K+), and reasoning-effort support built in:

1. Start LM Studio's local server (default port `1234`):
```bash
lms server start --port 1234
```
2. Load a model with at least ~64K context — Hermes' tool-calling and memory features consume substantial context.
3. Run `hermes setup` (new install) or `hermes model` (existing install) and select **LM Studio** as the provider; Hermes finds it on `localhost:1234` automatically.

For more detail, see the [LM Studio × Hermes Agent integration guide](https://lmstudio.ai/docs/integrations/hermes).

### Using Hermes Agent with Local LLMs via Ollama

Ollama has no dedicated first-class provider id — it's wired up as a **custom endpoint** at its OpenAI-compatible `/v1` path:

1. Install and start Ollama, then pull a tool-calling-capable model:
```bash
curl -fsSL https://ollama.com/install.sh | sh
ollama pull qwen3-coder:30b    # or another model with tool-call support
```
2. Ollama's default 2K context is too small for an agent — raise it (minimum ~64K recommended):
```bash
export OLLAMA_CONTEXT_LENGTH=65536
```
3. Run `hermes model` (or `hermes setup`) and choose **Custom endpoint**:
   - **Base URL:** `http://localhost:11434/v1`
   - **API Key:** leave empty, or `no-key`
   - **Model:** the tag you pulled, e.g. `qwen3-coder:30b`

   Or edit `~/.hermes/config.yaml` directly:
```yaml
model:
  default: "qwen3-coder:30b"
  provider: "custom"
  base_url: "http://localhost:11434/v1"
```
4. If you set a custom `num_ctx` on the Ollama side (e.g. via a Modelfile or `ollama run --num_ctx 65536`), set the matching context length on the Hermes side too — Ollama's `/api/show` reports the model's *maximum* context, not the effective `num_ctx` you configured, so Hermes can't infer it automatically:
```yaml
providers:
  custom:
    models:
      qwen3-coder:30b:
        context_length: 65536
```

**WSL2 note:** if Ollama runs on the Windows host and Hermes runs inside WSL2, either add `networkingMode=mirrored` to `.wslconfig` (Windows 11 22H2+), or bind Ollama to `0.0.0.0` and use the host's WSL-visible IP instead of `localhost`.

### Using Hermes Agent with Mammouth AI

[Mammouth AI](https://mammouth.ai/) is an OpenAI-compatible gateway, so — like Ollama above — it's added as a **custom endpoint** rather than a first-class provider. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).

```yaml
providers:
  mammouth:
    api: https://api.mammouth.ai/v1
    api_key: "${MAMMOUTH_API_KEY}"
```
```bash
export MAMMOUTH_API_KEY=your-mammouth-api-key
hermes chat --provider custom:mammouth -m gpt-4.1
```
Mammouth AI supports models from multiple providers — for example `claude-sonnet-4-6`, `mistral`, or `deepseek-v3`. For the full model list, visit the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

### Troubleshooting (Hermes Agent)

- **Tool calls silently fail or aren't attempted on a local model** — Not every GGUF/model supports tool calling well; prefer a model documented as tool-call capable (e.g. `qwen3-coder`, `gemma4`). On vLLM, tool calling additionally requires `--enable-auto-tool-choice --tool-call-parser hermes`; on `llama-server`, pass `--jinja`.
- **Requests to a local endpoint time out or drop mid-stream** — Hermes auto-relaxes timeouts for detected local addresses, but a very slow CPU-only backend may still need `HERMES_STREAM_READ_TIMEOUT` raised further (see [Configuring Hermes Agent](#configuring-hermes-agent)).
- **Context gets truncated on Ollama despite a large `num_ctx`** — Hermes reads Ollama's advertised *maximum* context, not your configured `num_ctx`; set `context_length` explicitly for that model under `providers.custom.models` in `config.yaml`.
- **Can't reach a local server from WSL2 (or vice versa)** — See the WSL2 networking note under [Using Hermes Agent with Local LLMs via Ollama](#using-hermes-agent-with-local-llms-via-ollama).
- **`hermes` picks up the wrong config or credentials** — Pass `--ignore-user-config` to skip `~/.hermes/config.yaml` (credentials in `.env` still load), or `--safe-mode` to disable all customizations while debugging.

For the full guide, see the upstream [LLM and Model Providers](https://hermes-agent.nousresearch.com/docs/integrations/providers/) and [Local Models](https://hermes-agent.nousresearch.com/docs/user-guide/local-models) documentation.

## Kilo Code CLI

[Kilo Code](https://kilo.ai/cli) (`kilo`) is the CLI face of the Kilo agentic engineering platform — the same agent also ships as VS Code and JetBrains extensions, sharing config and sessions. It supports 500+ models across providers and multiple built-in modes (Architect, Coder, Debugger).

**Install with npm:**
```bash
npm install -g @kilocode/cli
```

Alternative installs: curl script, pnpm, bun, Homebrew, and the AUR (Arch Linux) are all documented at [kilo.ai/cli](https://kilo.ai/cli). Upgrade in place with:
```bash
kilo upgrade
```

Run `kilo` in a project directory, then use `/connect` for the interactive wizard that configures API keys per provider. Config lives in `~/.config/kilo/kilo.json[c]` (global) or `./kilo.json[c]` / `./.kilo/` (project-level):
```json
{
  "$schema": "https://app.kilo.ai/config.json",
  "model": "anthropic/claude-sonnet-4-20250514",
  "provider": {
    "anthropic": {
      "options": {
        "apiKey": "{env:ANTHROPIC_API_KEY}"
      }
    }
  }
}
```

For more information, visit the [Kilo Code GitHub repository](https://github.com/Kilo-Org/kilocode) and [CLI documentation](https://kilo.ai/docs/code-with-ai/platforms/cli).

### Using Kilo Code with Local LLMs via LM Studio and Ollama

Any provider — including a local OpenAI-compatible server — is registered under `provider.<provider_id>` in the config file, with models declared under `provider.<provider_id>.models`:
```jsonc
{
  "provider": {
    "lm-studio": {
      "options": { "baseURL": "http://localhost:1234/v1", "apiKey": "lm-studio" },
      "models": { "qwen3-coder-30b-a3b-instruct": {} }
    },
    "ollama": {
      "options": { "baseURL": "http://localhost:11434/v1", "apiKey": "ollama" },
      "models": { "qwen3-coder:30b": {} }
    }
  },
  "model": "lm-studio/qwen3-coder-30b-a3b-instruct"
}
```

### Using Kilo Code with Mammouth AI

[Mammouth AI](https://mammouth.ai/) plugs in the same way, as a named provider. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).
```jsonc
{
  "provider": {
    "mammouth": {
      "options": { "baseURL": "https://api.mammouth.ai/v1", "apiKey": "{env:MAMMOUTH_API_KEY}" },
      "models": { "gpt-4.1": {}, "claude-sonnet-4-6": {}, "deepseek-v3": {} }
    }
  },
  "model": "mammouth/gpt-4.1"
}
```
For the full model list, visit the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

## Kimi Code CLI

[Kimi Code](https://github.com/MoonshotAI/kimi-code) (`kimi`) is Moonshot AI's own terminal coding agent, built as the native harness for their Kimi models — reads/edits code, runs shell commands, searches files, fetches web pages, takes video input (drop in a screen recording), and configures MCP servers conversationally via `/mcp-config`. Single-binary distribution — no Node.js required for the standard install.

*Composio's two cents:* in its [harness benchmark](https://composio.dev/content/best-ai-agent-harnesses) (25 business-app tasks, all 8 harnesses driving the same external Kimi K3 model via OpenRouter), Kimi Code posted the **second-highest pass rate (84%)** — but also burned **the most tokens of any harness tested (15.27M across 24 tasks)**. Capable, especially when paired with its own home-team model, but not the lean choice.

**Install on macOS/Linux:**
```bash
curl -fsSL https://code.kimi.com/kimi-code/install.sh | bash
```

**Install on Windows (PowerShell):**
```powershell
irm https://code.kimi.com/kimi-code/install.ps1 | iex
```
Windows requires Git for Windows first — Kimi Code uses its bundled Git Bash as the shell environment.

**Install via npm:**
```bash
npm install -g @moonshot-ai/kimi-code
```

Run `kimi` in a project directory, then `/login` to authenticate (OAuth device-code flow, or a direct API key from the [Kimi Platform](https://platform.moonshot.ai/)). For more information, visit the [Kimi Code GitHub repository](https://github.com/MoonshotAI/kimi-code) and [documentation](https://moonshotai.github.io/kimi-code/en/guides/getting-started).

### Configuring Kimi Code

Settings live in `~/.kimi-code/config.toml` (TOML, snake_case keys like `default_model`); relocate the whole directory with `KIMI_CODE_HOME`. Kimi Code reads credentials **only** from `config.toml` — a plain `export OPENAI_API_KEY=...` in your shell is not picked up automatically, so put keys directly in the file (or under a provider's `env` sub-table).

### Using Kimi Code with Local LLMs via LM Studio

1. Start LM Studio's local server (default port `1234`) and load a model.
2. Add a custom OpenAI-compatible provider to `~/.kimi-code/config.toml`:
```toml
[providers."lm-studio"]
type = "openai"
base_url = "http://localhost:1234/v1"
api_key = "lm-studio"
```
3. Select it as the default model provider (via `default_model`, or interactively if the TUI offers a picker).

### Using Kimi Code with Local LLMs via Ollama

Same pattern, pointed at Ollama's OpenAI-compatible endpoint:
```toml
[providers."ollama"]
type = "openai"
base_url = "http://localhost:11434/v1"
api_key = "ollama"
```

### Using Kimi Code with Mammouth AI

[Mammouth AI](https://mammouth.ai/) is an OpenAI-compatible gateway, so it plugs in the same way. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).
```toml
[providers."mammouth"]
type = "openai"
base_url = "https://api.mammouth.ai/v1"
api_key = "your-mammouth-api-key"
```
For available model IDs, visit the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

## Oh My Pi CLI

[Oh My Pi](https://github.com/can1357/oh-my-pi) (`omp`) is a fork of Mario Zechner's minimal [Pi](#pi-agent-cli) coding agent that adds "everything you're missing": 60+ providers, 31 built-in tools, LSP and DAP integration, and persistent Python/Bun workers that the agent's own tools can call back into over a loopback bridge.

*Composio's two cents:* in its [harness benchmark](https://composio.dev/content/best-ai-agent-harnesses) (25 business-app tasks, all 8 harnesses driving the same external Kimi K3 model via OpenRouter), Oh My Pi came out **on top overall** — the **highest pass rate of any harness tested (88%, 22/25 tasks)** at a low $0.52 cost per success, the strongest balance of reliability and resource use in the whole bake-off.

**Install on macOS/Linux (shell script):**
```bash
curl -fsSL https://omp.sh/install | sh
```

**Install with Homebrew:**
```bash
brew install can1357/tap/omp
```

**Install with Bun (recommended for the npm-style package):**
```bash
bun install -g @oh-my-pi/pi-coding-agent
```

**Install on Windows (PowerShell):**
```powershell
irm https://omp.sh/install.ps1 | iex
```

**Nix:**
```bash
nix run github:can1357/oh-my-pi
# or
nix profile install github:can1357/oh-my-pi
```

**Alpine/musl note:** the prebuilt musl binary dynamically links `libstdc++`/`libgcc`, which stock Alpine doesn't ship — install them first: `apk add libstdc++ libgcc`.

For more information, visit the [Oh My Pi GitHub repository](https://github.com/can1357/oh-my-pi).

### Using Oh My Pi with Local LLMs via LM Studio and Ollama

Custom OpenAI-compatible providers — LM Studio, Ollama, llama.cpp, vLLM, LiteLLM, and more are all supported this way — are defined in `~/.omp/agent/models.yml`:
```yaml
providers:
  lm-studio:
    baseUrl: http://localhost:1234/v1
    api: openai-completions
    apiKey: dummy
    models:
      - id: qwen3-coder-30b-a3b-instruct

  ollama:
    baseUrl: http://localhost:11434/v1
    api: openai-completions
    apiKey: dummy
    models:
      - id: qwen3-coder:30b
```
A local instance typically needs only a placeholder `apiKey` — neither LM Studio nor Ollama validates it.

### Using Oh My Pi with Mammouth AI

[Mammouth AI](https://mammouth.ai/) plugs in the same way, as a named provider in `~/.omp/agent/models.yml`. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).
```yaml
providers:
  mammouth:
    baseUrl: https://api.mammouth.ai/v1
    api: openai-completions
    apiKey: "${MAMMOUTH_API_KEY}"
    models:
      - id: gpt-4.1
      - id: claude-sonnet-4-6
      - id: deepseek-v3
```
For the full model list, visit the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

## OpenHands CLI

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

### Bash Completion on Linux (OpenHands)

OpenHands CLI may support bash completion via the `--install-completion` flag if built with [Typer](https://typer.tiangolo.com/) or [Click](https://click.palletsprojects.com/). Try running:

```bash
openhands --install-completion
```

If the flag is available, it will automatically install completion for your current shell. If it is not recognized, bash completion is not yet supported. Check the [OpenHands documentation](https://openhands.dev/) for updates.

### Using OpenHands with Local LLMs via LM Studio

OpenHands has documented support for local LLMs. You can connect it to LM Studio's OpenAI-compatible API:

1. Start LM Studio and load your preferred model
2. Enable the Local Server feature in LM Studio (default port: 1234)
3. Configure environment variables:

```bash
export LLM_BASE_URL=http://localhost:1234/v1
export LLM_API_KEY=lm-studio
export LLM_MODEL=openai/qwen3-coder-30b-a3b-instruct
```

Replace the model identifier with your actual model from LM Studio.

**Docker note:** If running OpenHands in Docker, use `http://host.docker.internal:1234/v1` instead of `http://localhost:1234/v1` to reach LM Studio on the host machine.

For more information, see [OpenHands Local LLMs documentation](https://docs.openhands.dev/openhands/usage/llms/local-llms).

### Using OpenHands with Mammouth AI

[Mammouth AI](https://mammouth.ai/) is an OpenAI-compatible cloud provider and works with OpenHands via the same `LLM_BASE_URL` environment variables used for local LLMs. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).

**Configuration:**
```bash
export LLM_BASE_URL=https://api.mammouth.ai/v1
export LLM_API_KEY=your-mammouth-api-key
export LLM_MODEL=openai/gpt-4.1
```

Replace `gpt-4.1` with your preferred Mammouth AI model (e.g., `openai/claude-sonnet-4-6`, `openai/mistral`, `openai/deepseek-v3`). For all available model IDs, visit the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

## OpenCode CLI

OpenCode CLI is an AI-powered coding assistant that provides a terminal UI (TUI) and command-line interface for code generation, review, and automation.

*Composio's two cents:* in its [harness benchmark](https://composio.dev/content/best-ai-agent-harnesses) (25 business-app tasks, all 8 harnesses driving the same external Kimi K3 model), OpenCode landed at a 72% pass rate and the **highest cost per success of the mid-pack ($0.72)** — it handled genuinely complex, multi-step tasks (e.g. a CRM migration) but showed "mixed guardrail performance" along the way, i.e. it doesn't always stay on the rails as cleanly as the top finishers.

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

### Using OpenCode with Local LLMs via LM Studio

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

### OpenCode Config Gist (LM Studio)

A pre-built OpenCode configuration with all LM Studio local models is available as a public Gist.

| File                                                                               | Destination                        | Description                               |
|------------------------------------------------------------------------------------|------------------------------------|-------------------------------------------|
| [opencode.json](https://gist.github.com/kjwenger/24249c61cc3904e4bcff90f14e012fb1) | `~/.config/opencode/opencode.json` | Provider config with all LM Studio models |

**Download and install:**
```bash
mkdir -p ~/.config/opencode
gh gist view 24249c61cc3904e4bcff90f14e012fb1 --raw > ~/.config/opencode/opencode.json
```

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

### Bash Completion on Linux (OpenCode)

OpenCode has built-in bash completion support via the `opencode completion` subcommand (powered by [yargs](https://yargs.js.org/)).

**Option A: Install to user bash-completion directory (recommended):**
```bash
mkdir -p ~/.local/share/bash-completion/completions
opencode completion > ~/.local/share/bash-completion/completions/opencode
```

This integrates with the standard `bash-completion` framework and loads lazily. Ensure `bash-completion` is installed (`sudo apt install bash-completion` on Debian/Ubuntu).

**Option B: Append to `~/.bashrc`:**
```bash
opencode completion >> ~/.bashrc
source ~/.bashrc
```

**Option C: Source dynamically in `~/.bashrc`:**
```bash
echo 'eval "$(opencode completion)"' >> ~/.bashrc
source ~/.bashrc
```

This regenerates the completion script on every new shell, staying in sync after upgrades at the cost of a small startup delay.

### Using OpenCode with Mammouth AI

OpenCode supports custom providers via its `~/.config/opencode/opencode.json` configuration file using the `@ai-sdk/openai-compatible` package. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).

Create or edit `~/.config/opencode/opencode.json`:
```json
{
  "$schema": "https://opencode.ai/config.json",
  "provider": {
    "mammouth": {
      "npm": "@ai-sdk/openai-compatible",
      "name": "Mammouth AI",
      "options": {
        "baseURL": "https://api.mammouth.ai/v1",
        "apiKey": "your-mammouth-api-key"
      },
      "models": {
        "gpt-4.1": {
          "name": "gpt-4.1"
        },
        "claude-sonnet-4-6": {
          "name": "claude-sonnet-4-6"
        },
        "mistral": {
          "name": "mistral"
        },
        "deepseek-v3": {
          "name": "deepseek-v3"
        }
      }
    }
  }
}
```

Add or remove models from the `models` block to match your preferences. For the full list of available Mammouth AI model IDs, visit the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

### Reducing Token Overhead (OpenCode)

See [Understanding Token & Context Overhead](#understanding-token--context-overhead) above for the general phenomenon this addresses. OpenCode is a concrete, measured example: its default `build` agent (all 11 tools enabled) has been observed sending roughly 8,000 tokens — a ~9.5k-character system prompt plus full tool-definition JSON for every tool — for a single-word "hello", before counting a separate, unrequested ~2,000-token call OpenCode makes to auto-generate the session title.

**Custom, minimal agents** are the primary fix. Define one as a markdown file with YAML frontmatter:
- **Project-level:** `.opencode/agents/<name>.md`
- **Global:** `~/.config/opencode/agents/<name>.md`

```markdown
---
description: Minimal agent for quick questions and small edits — no tools
mode: primary
model: anthropic/claude-sonnet-4-6
temperature: 0.2
---

You are a coding assistant. Answer directly and concisely. You have no tools available.
```
`mode: primary` makes it selectable with the **Tab** key (cycles between primary agents) alongside the built-in `build`/`plan` agents; `mode: subagent` makes it reachable via `@name` instead. Other frontmatter fields: `permission` (see below), `steps` (cap agentic iterations), `disable`, `hidden` (hide a subagent from `@` autocomplete), `top_p`.

**Fine-grained tool restriction**, as a lighter-touch alternative to a fully separate agent — add a `permission` block to any agent (including a copy of `build`) to allow/ask/deny individual tools, down to specific command patterns:
```yaml
permission:
  edit: deny
  bash:
    "git push": ask
    "grep *": allow
```

**The built-in `plan` agent** is a lighter middle ground with no custom config needed — file edits and bash commands default to `ask` rather than running freely, useful for analysis/planning without modification.

**For the title-generation overhead specifically:** set `small_model` in `opencode.json` to route that (and other lightweight, non-primary-task) calls through a cheaper/faster model instead of your main one:
```json
{
  "small_model": "anthropic/claude-haiku-4-5"
}
```
There is no officially documented way to skip title generation entirely as of this writing — an [open feature request](https://github.com/anomalyco/opencode/issues/33140) tracks that ask upstream.

Reported result of combining a minimal custom agent with the above: the same "hello" that cost ~8,000 tokens on stock `build` dropped to roughly 300-500 tokens — over a 90% reduction — with the obvious trade-off that a tool-less agent can't edit files, run commands, delegate to subagents, or fetch web pages. Start minimal and re-add specific tools/permissions as you actually need them, rather than running `build` by default for everything.

## Pi Agent CLI

[Pi](https://github.com/earendil-works/pi) (`pi`, package `@earendil-works/pi-coding-agent`) is a small, opinionated, extensible terminal coding harness — the base project that [Oh My Pi](#oh-my-pi-cli) above forks and extends. It ships four default tools (`read`, `write`, `edit`, `bash`), session management (resume/branch), TypeScript extensions, skills, and prompt templates, and supports 30+ providers (subscriptions like Claude Pro/Max, ChatGPT Plus/Pro, GitHub Copilot, or API keys for OpenAI, DeepSeek, Gemini, Mistral, Groq, and more).

*Composio's two cents:* in its [harness benchmark](https://composio.dev/content/best-ai-agent-harnesses) (25 business-app tasks, all 8 harnesses driving the same external Kimi K3 model), Pi Agent had the **fastest median runtime of any harness tested (156.2s)** and the fewest tokens used, at a 72% pass rate — a deliberately lean harness, trading a bit of reliability for speed and resource efficiency; if that trade-off doesn't work for you, [Oh My Pi](#oh-my-pi-cli) is the same lineage aimed at higher pass rates instead.

**Install via npm:**
```bash
npm install -g --ignore-scripts @earendil-works/pi-coding-agent
```

**Install via curl:**
```bash
curl -fsSL https://pi.dev/install.sh | sh
```

Run `pi`, then authenticate with an environment variable (e.g. `export ANTHROPIC_API_KEY=sk-ant-...`) or interactively with `/login` to pick a provider. Switch models mid-session with `/model`, or launch directly against one: `pi --model openai/gpt-4o "Your request"`. For more information, visit the [Pi GitHub repository](https://github.com/earendil-works/pi) and [documentation](https://pi.dev/docs/latest).

### Using Pi Agent with Local LLMs via LM Studio and Ollama

Pi's built-in `llama.cpp` support (`/login llama.cpp`, then `/llama` to manage models) covers one local path, but LM Studio and Ollama are added as custom OpenAI-compatible providers via a small TypeScript extension — drop this in Pi's extensions directory and it registers on startup:
```typescript
export default async function (pi: ExtensionAPI) {
  pi.registerProvider("lm-studio", {
    baseUrl: "http://localhost:1234/v1",
    apiKey: "$LM_STUDIO_API_KEY",
    api: "openai-completions",
    models: [{ id: "qwen3-coder-30b-a3b-instruct", contextWindow: 128000, maxTokens: 4096 }]
  });

  pi.registerProvider("ollama", {
    baseUrl: "http://localhost:11434/v1",
    apiKey: "$OLLAMA_API_KEY",
    api: "openai-completions",
    models: [{ id: "qwen3-coder:30b", contextWindow: 128000, maxTokens: 4096 }]
  });
}
```
```bash
export LM_STUDIO_API_KEY=lm-studio
export OLLAMA_API_KEY=ollama
```
Then select either with `/model lm-studio/qwen3-coder-30b-a3b-instruct` or `/model ollama/qwen3-coder:30b`. The same `pi.registerProvider()` mechanism works for [Mammouth AI](https://mammouth.ai/) — swap in `baseUrl: "https://api.mammouth.ai/v1"` and a real `apiKey` env var; see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai) for account setup. Full details are in the [Custom Providers guide](https://pi.dev/docs/latest/custom-provider.md).

## Qwen CLI

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

### Using Qwen with Local LLMs via LM Studio

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

### Qwen Config Gist (LM Studio)

A pre-built Qwen settings file with all LM Studio local models is available as a public Gist.

| File                                                                               | Destination             | Description                                         |
|------------------------------------------------------------------------------------|-------------------------|-----------------------------------------------------|
| [settings.json](https://gist.github.com/kjwenger/36062285c744156ac253aa26d9063255) | `~/.qwen/settings.json` | API base URL, model providers, all LM Studio models |

**Download and install:**
```bash
mkdir -p ~/.qwen
gh gist view 36062285c744156ac253aa26d9063255 --raw > ~/.qwen/settings.json
```

### Switching from Local LLMs to Qwen Cloud LLMs

If you have previously configured Qwen to use a local LM Studio backend (via `OPENAI_*` environment variables or `~/.qwen/settings.json`), you need to clear those settings before connecting to Qwen's cloud models.

**Step 1: Unset local environment variables (if set)**

If you exported LM Studio variables in your current shell session, unset them:
```bash
unset OPENAI_HOST
unset OPENAI_BASE_PATH
unset OPENAI_BASE_URL
unset OPENAI_API_KEY
unset OPENAI_MODEL
```

**Step 2: Remove or suspend the local settings file (if present)**

If you installed the LM Studio settings file, move it aside rather than deleting it so you can restore it later:
```bash
mv ~/.qwen/settings.json ~/.qwen/settings.json.lmstudio
```

**Step 3: Authenticate with Qwen's cloud**

Start Qwen and run the `/auth` slash command inside the session:
```bash
qwen
```
```
/auth
```

Select **Qwen OAuth (recommended & free)** to sign in with your `qwen.ai` account via browser. This grants access to Qwen's hosted cloud models at no cost and stores a token locally so you do not need to repeat this step.

**Alternative: DashScope API key**

If you have a [DashScope API key](https://dashscope.aliyuncs.com/), set it in the environment instead of going through OAuth:
```bash
export DASHSCOPE_API_KEY="sk-your-key-here"
qwen
```

Or add it permanently to `~/.qwen/settings.json`:
```json
{
  "env": {
    "DASHSCOPE_API_KEY": "sk-your-key-here"
  }
}
```

**Switching back to local LLMs**

Restore the LM Studio settings file and re-export the variables:
```bash
mv ~/.qwen/settings.json.lmstudio ~/.qwen/settings.json
```

> **Note:** Shell environment variables take priority over `~/.qwen/settings.json`. If `OPENAI_BASE_URL` is still exported in your shell, Qwen will continue routing to the local backend even after OAuth authentication.

### Bash Completion on Linux (Qwen)

Qwen Code does not ship with a built-in `completion` subcommand, but it uses [yargs](https://yargs.js.org/) internally which exposes the `--get-yargs-completions` mechanism. You can create a completion script manually.

**Option A: Install to user bash-completion directory (recommended):**
```bash
mkdir -p ~/.local/share/bash-completion/completions
cat > ~/.local/share/bash-completion/completions/qwen << 'EOF'
# Bash completion for Qwen Code CLI (@qwen-code/qwen-code)
_qwen_yargs_completions()
{
    local cur_word args type_list

    cur_word="${COMP_WORDS[COMP_CWORD]}"
    args=("${COMP_WORDS[@]}")

    type_list=$(qwen --get-yargs-completions "${args[@]}" 2>/dev/null)
    COMPREPLY=($(compgen -W "${type_list}" -- "${cur_word}"))

    if [ ${#COMPREPLY[@]} -eq 0 ]; then
        COMPREPLY=()
    fi

    return 0
}
complete -o bashdefault -o default -F _qwen_yargs_completions qwen
EOF
```

This integrates with the standard `bash-completion` framework and loads lazily. Ensure `bash-completion` is installed (`sudo apt install bash-completion` on Debian/Ubuntu).

**Option B: Add directly to `~/.bashrc`:**

Append the same completion function to your `~/.bashrc`:
```bash
cat >> ~/.bashrc << 'EOF'

# Qwen Code CLI bash completion (yargs-based)
_qwen_yargs_completions()
{
    local cur_word args type_list

    cur_word="${COMP_WORDS[COMP_CWORD]}"
    args=("${COMP_WORDS[@]}")

    type_list=$(qwen --get-yargs-completions "${args[@]}" 2>/dev/null)
    COMPREPLY=($(compgen -W "${type_list}" -- "${cur_word}"))

    if [ ${#COMPREPLY[@]} -eq 0 ]; then
        COMPREPLY=()
    fi

    return 0
}
complete -o bashdefault -o default -F _qwen_yargs_completions qwen
EOF
source ~/.bashrc
```

### Using Qwen with Mammouth AI

[Mammouth AI](https://mammouth.ai/) is a straightforward replacement for any OpenAI-compatible backend, and Qwen CLI's `OPENAI_*` environment variables can be pointed at it directly. For account setup and API key instructions, see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai).

**Configuration:**
```bash
export OPENAI_HOST=https://api.mammouth.ai
export OPENAI_BASE_PATH=v1
export OPENAI_BASE_URL=https://api.mammouth.ai/v1
export OPENAI_API_KEY=your-mammouth-api-key
export OPENAI_MODEL=gpt-4.1
```

Replace `gpt-4.1` with your preferred Mammouth AI model (e.g., `claude-sonnet-4-6`, `mistral`, `deepseek-v3`). For the full model list, visit the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

**Note:** To switch back to Qwen's cloud models, unset these variables and run `/auth` inside a Qwen session (see *Switching from Local LLMs to Qwen Cloud LLMs* above for details).

## Warp CLI

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

### Using Warp with Local LLMs via LM Studio

Warp's AI features use built-in cloud providers (Anthropic, OpenAI, Google). Custom LLM endpoints (BYO LLM) are only available on Enterprise plans and are not configurable in the free or Pro tiers.

Local model support is the [#1 feature request (#4339)](https://github.com/warpdotdev/Warp/issues/4339) on Warp's public issue tracker.

### Using Warp with Mammouth AI

Warp's BYO LLM (custom endpoint) feature is restricted to Enterprise plans and is not available on free or Pro tiers. Direct integration with [Mammouth AI](https://mammouth.ai/) is therefore not configurable in standard Warp installations.

**Note:** Mammouth AI provides access to many of the same frontier models (GPT-4, Claude, Gemini) that Warp already uses natively through its built-in providers. For a fully configurable alternative with custom LLM provider support, consider tools like Aider, OpenCode, or Goose configured with Mammouth AI (see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai)).

