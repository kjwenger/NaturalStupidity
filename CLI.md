# AI CLI Tools

<!-- TOC -->
* [AI CLI Tools](#ai-cli-tools)
  * [Discovering New AI CLI Tools](#discovering-new-ai-cli-tools)
  * [Installing All AI CLI Tools](#installing-all-ai-cli-tools)
  * [Installing Bash Completion for All CLI Tools](#installing-bash-completion-for-all-cli-tools)
  * [Enabling Bash Completion for Zsh](#enabling-bash-completion-for-zsh)
  * [Aider CLI](#aider-cli)
    * [Using Aider with Local LLMs via LM Studio](#using-aider-with-local-llms-via-lm-studio)
    * [Aider Config Gists (LM Studio)](#aider-config-gists-lm-studio)
    * [Bash Completion on Linux (Aider)](#bash-completion-on-linux-aider)
  * [Aider-CE CLI](#aider-ce-cli)
    * [Bash Completion on Linux (Aider-CE)](#bash-completion-on-linux-aider-ce)
    * [Using Aider-CE with Local LLMs via LM Studio](#using-aider-ce-with-local-llms-via-lm-studio)
  * [Claude CLI](#claude-cli)
    * [Bash Completion on Linux (Claude)](#bash-completion-on-linux-claude)
    * [Using Claude with Local LLMs via LM Studio](#using-claude-with-local-llms-via-lm-studio)
  * [Codex CLI](#codex-cli)
    * [Using Codex with Local LLMs via LM Studio](#using-codex-with-local-llms-via-lm-studio)
    * [Bash Completion on Linux (Codex)](#bash-completion-on-linux-codex)
  * [Copilot CLI](#copilot-cli)
    * [Bash Completion on Linux (Copilot)](#bash-completion-on-linux-copilot)
    * [Using Copilot with Local LLMs via LM Studio](#using-copilot-with-local-llms-via-lm-studio)
  * [DeepSeek CLI](#deepseek-cli)
    * [Bash Completion on Linux (DeepSeek)](#bash-completion-on-linux-deepseek)
    * [Using DeepSeek with Local LLMs via LM Studio](#using-deepseek-with-local-llms-via-lm-studio)
  * [Factory CLI](#factory-cli)
    * [Using Factory with Local LLMs via LM Studio](#using-factory-with-local-llms-via-lm-studio)
  * [Gemini CLI](#gemini-cli)
    * [Bash Completion on Linux (Gemini)](#bash-completion-on-linux-gemini)
    * [Using Gemini with Local LLMs via LM Studio](#using-gemini-with-local-llms-via-lm-studio)
  * [Grok CLI](#grok-cli)
    * [Using Grok with Local LLMs via LM Studio](#using-grok-with-local-llms-via-lm-studio)
  * [Goose CLI](#goose-cli)
    * [Using Goose with Local LLMs via LM Studio](#using-goose-with-local-llms-via-lm-studio)
    * [Bash Completion on Linux (Goose)](#bash-completion-on-linux-goose)
  * [OpenHands CLI](#openhands-cli)
    * [Bash Completion on Linux (OpenHands)](#bash-completion-on-linux-openhands)
    * [Using OpenHands with Local LLMs via LM Studio](#using-openhands-with-local-llms-via-lm-studio)
  * [OpenCode CLI](#opencode-cli)
    * [Using OpenCode with Local LLMs via LM Studio](#using-opencode-with-local-llms-via-lm-studio)
    * [OpenCode Config Gist (LM Studio)](#opencode-config-gist-lm-studio)
    * [Bash Completion on Linux (OpenCode)](#bash-completion-on-linux-opencode)
  * [Qwen CLI](#qwen-cli)
    * [Using Qwen with Local LLMs via LM Studio](#using-qwen-with-local-llms-via-lm-studio)
    * [Qwen Config Gist (LM Studio)](#qwen-config-gist-lm-studio)
    * [Bash Completion on Linux (Qwen)](#bash-completion-on-linux-qwen)
  * [Warp CLI](#warp-cli)
    * [Using Warp with Local LLMs via LM Studio](#using-warp-with-local-llms-via-lm-studio)
<!-- TOC -->

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

## Claude CLI

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

## Codex CLI

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

## Gemini CLI

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

### Bash Completion on Linux (Gemini)

Gemini CLI does not yet have built-in bash completion support. There is an open [feature request (#1855)](https://github.com/google-gemini/gemini-cli/issues/1855) with a draft PR. Gemini CLI is built on [oclif](https://oclif.io/), which supports completions via plugin, so native support is expected in a future release.

### Using Gemini with Local LLMs via LM Studio

Gemini CLI does not natively support custom OpenAI-compatible endpoints. It is designed to work exclusively with Google's Gemini API.

**Feature request:** [#16504](https://github.com/google-gemini/gemini-cli/issues/16504) — Add support for custom/local model endpoints.

Third-party proxy solutions exist (e.g., [geminicli2api](https://github.com/search?q=geminicli2api), [gemini-openai-proxy](https://github.com/search?q=gemini-openai-proxy)) that translate between the Gemini API protocol and OpenAI-compatible APIs, but these are not officially supported by Google.

## Grok CLI

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

## OpenCode CLI

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

