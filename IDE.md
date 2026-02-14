# AI IDE Plugins

<!-- TOC -->
* [AI IDE Plugins](#ai-ide-plugins)
  * [IntelliJ IDEA Plugins](#intellij-idea-plugins)
    * [GitHub Copilot Plugin](#github-copilot-plugin)
    * [Continue Plugin](#continue-plugin)
    * [CodeGPT Plugin](#codegpt-plugin)
    * [Other AI Plugins](#other-ai-plugins)
    * [Automating Plugin Installation and Configuration](#automating-plugin-installation-and-configuration)
  * [VSCode and VSCodium Extensions](#vscode-and-vscodium-extensions)
    * [GitHub Copilot Extension](#github-copilot-extension)
    * [Continue Extension](#continue-extension)
    * [Cline Extension](#cline-extension)
    * [Roo Code Extension](#roo-code-extension)
    * [Gemini Code Assist Extension](#gemini-code-assist-extension)
    * [Claude Dev Extension](#claude-dev-extension)
    * [Chat Extensions](#chat-extensions)
    * [Other VSCode AI Extensions](#other-vscode-ai-extensions)
    * [Automating Extension Installation and Configuration](#automating-extension-installation-and-configuration)
<!-- TOC -->

## IntelliJ IDEA Plugins

IntelliJ IDEA supports multiple AI coding assistants through plugins, allowing you to use GitHub Copilot, Claude, and local LLMs directly in your IDE.

### GitHub Copilot Plugin

Official GitHub Copilot integration for IntelliJ IDEA.

**Installation:**
```
Settings → Plugins → Marketplace → Search "GitHub Copilot" → Install → Restart
```

**Features:**
- Inline code completions
- Chat interface
- Code explanations
- Test generation
- Commit message generation

**Requirements:**
- Active GitHub Copilot subscription
- GitHub account authentication

**Usage:**
After installation, sign in with your GitHub account. Copilot will automatically suggest code completions as you type.

For more information, visit [GitHub Copilot for IntelliJ](https://docs.github.com/en/copilot/using-github-copilot/getting-started-with-github-copilot?tool=jetbrains).

**Note on Local LLMs:**
GitHub Copilot does not support local LLMs or custom OpenAI-compatible endpoints. It only works with GitHub's hosted Copilot service.

### Continue Plugin

Free and open-source AI coding assistant supporting multiple LLM providers including Claude, OpenAI, and local LLMs.

**Installation:**
```
Settings → Plugins → Marketplace → Search "Continue" → Install → Restart
```

**Features:**
- Multiple LLM provider support (Claude, OpenAI, local models)
- Inline code editing
- Chat interface
- Custom prompts
- Context-aware completions

**Configuration:**

Create or edit `~/.continue/config.json`:

```json
{
  "models": [
    {
      "title": "Claude Sonnet",
      "provider": "anthropic",
      "model": "claude-3-5-sonnet-20241022",
      "apiKey": "your-claude-api-key"
    },
    {
      "title": "LM Studio Local",
      "provider": "openai",
      "model": "openai/gpt-oss-120b",
      "apiBase": "http://localhost:1234/v1",
      "apiKey": "lm-studio"
    },
    {
      "title": "OpenAI GPT-4",
      "provider": "openai",
      "model": "gpt-4",
      "apiKey": "your-openai-api-key"
    }
  ]
}
```

**Using with LM Studio:**
1. Start LM Studio and load your preferred model
2. Enable Local Server in LM Studio (default port: 1234)
3. Add the LM Studio configuration to `config.json` as shown above
4. Switch between models in Continue's interface

**Using with Ollama:**
1. Start Ollama: `ollama serve`
2. Pull a model: `ollama pull qwen3-coder:30b`
3. Add Ollama configuration to `config.json`:
```json
{
  "title": "Ollama Qwen",
  "provider": "ollama",
  "model": "qwen3-coder:30b"
}
```

**Using with Any OpenAI-Compatible API:**
```json
{
  "title": "Custom Local LLM",
  "provider": "openai",
  "model": "your-model-name",
  "apiBase": "http://your-server:port/v1",
  "apiKey": "your-api-key-if-required"
}
```

For more information, visit [Continue.dev](https://continue.dev/) and [Continue Documentation](https://docs.continue.dev/).

### CodeGPT Plugin

AI coding assistant with support for custom OpenAI-compatible endpoints.

**Installation:**
```
Settings → Plugins → Marketplace → Search "CodeGPT" → Install → Restart
```

**Features:**
- OpenAI API support
- Custom API endpoints (for local LLMs)
- Chat interface
- Code generation and refactoring

**Configuration:**
```
Settings → Tools → CodeGPT
```

Add custom endpoint for local LLMs:
- API URL: `http://localhost:1234/v1`
- API Key: `lm-studio`

**Using with Local LLMs via OpenAI-Compatible API:**

1. **LM Studio Setup:**
   - Start LM Studio and load your model
   - Enable Local Server (port 1234)
   - In CodeGPT settings:
     - Provider: Custom OpenAI
     - API URL: `http://localhost:1234/v1`
     - API Key: `lm-studio`
     - Model: `openai/gpt-oss-120b` (or your model name)

2. **Ollama Setup:**
   - Start Ollama: `ollama serve`
   - Pull model: `ollama pull qwen3-coder:30b`
   - In CodeGPT settings:
     - Provider: Custom OpenAI
     - API URL: `http://localhost:11434/v1`
     - Model: `qwen3-coder:30b`

3. **Other OpenAI-Compatible Servers:**
   - Configure the server URL and authentication
   - Use the same settings pattern as above

For more information, visit [CodeGPT Plugin](https://plugins.jetbrains.com/plugin/21056-codegpt).

### Other AI Plugins

**Codeium (Free):**
- Installation: Settings → Plugins → "Codeium"
- Free AI code completions
- Chat interface
- Supports 70+ languages
- Website: [codeium.com](https://codeium.com/)
- **Local LLMs:** Not supported - uses Codeium's cloud service only

**Tabnine (Freemium):**
- Installation: Settings → Plugins → "Tabnine"
- AI code completions
- Local model option (Pro)
- Team learning capabilities
- Website: [tabnine.com](https://www.tabnine.com/)
- **Local LLMs:** Pro version supports local model deployment (proprietary, not OpenAI-compatible API)

**JetBrains AI Assistant (Paid):**
- Built-in to newer IntelliJ versions
- Integrated with IntelliJ ecosystem
- Multiple LLM providers
- Context-aware suggestions
- Requires separate JetBrains AI Assistant subscription
- Website: [jetbrains.com/ai](https://www.jetbrains.com/ai/)
- **Local LLMs:** May support custom endpoints in enterprise plans (check documentation)

**Recommended Setup:**

For users with GitHub Copilot, Claude API access, and local LLMs:

1. **GitHub Copilot Plugin** - For your Copilot subscription
   - Best for inline code completions
   - Native GitHub integration

2. **Continue Plugin** - For Claude and local LLMs
   - Configure multiple providers
   - Switch between models easily
   - Free and open source

This combination gives you access to all your AI resources without conflicts.

### Automating Plugin Installation and Configuration

IntelliJ IDEA does not offer an agentic mode for automated plugin installation and configuration through natural language. However, it provides command-line interfaces and project-level configuration for automation.

**Command-Line Plugin Installation:**

```bash
# Install plugins from command line
/media/kjwenger/D/Jetbrains/apps/intellij-idea/bin/idea.sh installPlugins \
  com.github.copilot \
  Continue.continue \
  ee.carlrobert.codegpt
```

**Project-Level Plugin Dependencies:**

Create `.idea/externalDependencies.xml` in your project:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project version="4">
  <component name="ExternalDependencies">
    <plugin id="com.github.copilot" />
    <plugin id="Continue.continue" />
    <plugin id="ee.carlrobert.codegpt" />
  </component>
</project>
```

IntelliJ IDEA will prompt users to install these plugins when opening the project.

**Settings Sync:**
- Navigate to: Settings → Settings Sync → Enable Settings Sync
- Sign in with JetBrains account
- Sync plugins and settings across machines
- Note: Does not support automated script-based configuration

**Automation Script Example:**

```bash
#!/bin/bash
# setup-idea-plugins.sh

# Install IDEA plugins
/media/kjwenger/D/Jetbrains/apps/intellij-idea/bin/idea.sh installPlugins \
  com.github.copilot \
  Continue.continue \
  ee.carlrobert.codegpt

# Copy pre-configured Continue settings (non-sensitive)
mkdir -p ~/.continue
cp ~/dotfiles/continue-config.json ~/.continue/config.json

echo "Manual steps:"
echo "1. Add your API keys to ~/.continue/config.json"
echo "2. Sign in to GitHub Copilot in IntelliJ IDEA"
```

**Using AI CLI Tools for Script Generation:**

```bash
# Use Aider to create setup scripts
aider "Create a bash script that installs GitHub Copilot and Continue plugins for IntelliJ IDEA"

# Use Goose for setup assistance
goose "Help me automate IntelliJ IDEA plugin installation for Continue with LM Studio"
```

**Limitations:**
- ❌ No agentic/conversational plugin installation
- ❌ No natural language configuration
- ❌ No CLI for settings configuration (only plugin installation)
- ❌ No official API for programmatic settings management
- ⚠️ Settings files are XML-based but format changes between versions (not recommended to edit directly)

**Why No Agentic Configuration?**
1. **Security**: API keys shouldn't be passed through conversational interfaces
2. **IDE Limitations**: IDEA doesn't expose comprehensive configuration APIs
3. **Complexity**: Settings structures vary significantly between plugins and versions
4. **Vendor Preferences**: JetBrains prefers Settings Sync for cross-machine configuration

**Future Possibilities:**
- JetBrains AI Assistant may add conversational plugin management
- Enhanced CLI support for settings configuration
- Better template/project configuration options

## VSCode and VSCodium Extensions

VSCode and VSCodium support a wide variety of AI coding assistants through extensions. These extensions work identically in both editors.

### GitHub Copilot Extension

Official GitHub Copilot integration for VSCode/VSCodium.

**Installation:**
- VSCode: Extensions → Search "GitHub Copilot" → Install
- VSCodium: Install from [Open VSX Registry](https://open-vsx.org/extension/GitHub/copilot)
- Extension ID: `GitHub.copilot`

**Features:**
- Inline code completions
- Multi-line suggestions
- Chat interface (`GitHub.copilot-chat`)
- Code explanations
- Test generation
- Commit message generation
- Voice interaction (in beta)

**Requirements:**
- Active GitHub Copilot subscription
- GitHub account authentication

**Usage:**
After installation, sign in with your GitHub account. Use:
- Inline suggestions: Type code and accept suggestions with Tab
- Chat: Open with `Ctrl+Shift+I` (Windows/Linux) or `Cmd+Shift+I` (Mac)
- Command palette: `Copilot: Enable/Disable`

For more information, visit [GitHub Copilot for VSCode](https://docs.github.com/en/copilot/using-github-copilot/getting-started-with-github-copilot?tool=vscode).

**Note on Local LLMs:**
GitHub Copilot does not support local LLMs or custom OpenAI-compatible endpoints. It only works with GitHub's hosted Copilot service.

### Continue Extension

Free and open-source AI coding assistant supporting multiple LLM providers including Claude, OpenAI, and local LLMs. Same codebase as the IntelliJ plugin.

**Installation:**
- VSCode: Extensions → Search "Continue" → Install
- VSCodium: Install from [Open VSX Registry](https://open-vsx.org/extension/Continue/continue)
- Extension ID: `Continue.continue`

**Features:**
- Multiple LLM provider support (Claude, OpenAI, Ollama, LM Studio, etc.)
- Inline code editing with `Cmd+I` / `Ctrl+I`
- Chat interface in sidebar
- Custom prompts and slash commands
- Context-aware completions
- Codebase indexing
- Tab autocomplete

**Configuration:**

Press `Cmd/Ctrl+Shift+P` → "Continue: Open config.json" or edit `~/.continue/config.json`:

```json
{
  "models": [
    {
      "title": "Claude Sonnet",
      "provider": "anthropic",
      "model": "claude-3-5-sonnet-20241022",
      "apiKey": "your-claude-api-key"
    },
    {
      "title": "LM Studio Local",
      "provider": "openai",
      "model": "openai/gpt-oss-120b",
      "apiBase": "http://localhost:1234/v1",
      "apiKey": "lm-studio"
    },
    {
      "title": "Ollama",
      "provider": "ollama",
      "model": "qwen3-coder:30b"
    }
  ],
  "tabAutocompleteModel": {
    "title": "Qwen Coder",
    "provider": "ollama",
    "model": "qwen3-coder:8b"
  }
}
```

**Using with LM Studio:**
1. Start LM Studio and load your preferred model
2. Enable Local Server in LM Studio (default port: 1234)
3. Add the LM Studio configuration to `config.json` as shown above
4. Use `Cmd/Ctrl+L` to open chat, select model from dropdown

**Using with Ollama:**
1. Start Ollama: `ollama serve`
2. Pull a model: `ollama pull qwen3-coder:30b`
3. Configuration is shown in the JSON above (Ollama section)
4. Ollama models appear automatically in Continue

**Using with Any OpenAI-Compatible API:**
```json
{
  "title": "Custom Local LLM",
  "provider": "openai",
  "model": "your-model-name",
  "apiBase": "http://your-server:port/v1",
  "apiKey": "your-api-key-if-required"
}
```

**Tab Autocomplete with Local Models:**
Continue supports tab autocomplete with fast local models:
```json
{
  "tabAutocompleteModel": {
    "title": "Fast Local Model",
    "provider": "ollama",
    "model": "qwen3-coder:8b"
  }
}
```
Recommended: Use smaller, faster models (7-8B) for tab autocomplete, larger models (30B+) for chat.

For more information, visit [Continue.dev](https://continue.dev/) and [Continue Documentation](https://docs.continue.dev/).

### Cline Extension

Autonomous AI coding agent that can edit files, run commands, and use browser automation. Formerly known as "Claude Dev".

**Installation:**
- VSCode: Extensions → Search "Cline" → Install
- VSCodium: Install from [Open VSX Registry](https://open-vsx.org/extension/saoudrizwan/claude-dev)
- Extension ID: `saoudrizwan.claude-dev`

**Features:**
- Autonomous task execution
- File creation and editing
- Terminal command execution
- Browser automation (via puppeteer)
- Multistep workflows
- Approval mode for safety
- Support for Claude, OpenAI, OpenRouter, and OpenAI-compatible APIs

**Configuration:**

Open Cline sidebar → Settings icon → Configure providers:

```json
{
  "anthropic": {
    "apiKey": "your-claude-api-key"
  },
  "openai": {
    "apiKey": "your-openai-api-key"
  },
  "openaiCompatible": {
    "baseUrl": "http://localhost:1234/v1",
    "apiKey": "lm-studio",
    "modelId": "openai/gpt-oss-120b"
  }
}
```

**Usage:**
1. Open Cline panel from sidebar
2. Describe your task in natural language
3. Review and approve suggested actions
4. Cline executes autonomously with your approval

**Using with Local LLMs:**
- Configure as "OpenAI Compatible" provider
- Point to LM Studio or Ollama endpoint
- Works best with larger models (30B+)

**Detailed Local LLM Setup:**

1. **LM Studio:**
   ```json
   {
     "openaiCompatible": {
       "baseUrl": "http://localhost:1234/v1",
       "apiKey": "lm-studio",
       "modelId": "openai/gpt-oss-120b"
     }
   }
   ```

2. **Ollama:**
   ```json
   {
     "openaiCompatible": {
       "baseUrl": "http://localhost:11434/v1",
       "apiKey": "ollama",
       "modelId": "qwen3-coder:30b"
     }
   }
   ```

3. **Other OpenAI-Compatible Servers:**
   - Replace `baseUrl` with your server endpoint
   - Use appropriate authentication if required
   - Specify the model ID as required by your server

**Performance Tips:**
- Use models with 30B+ parameters for best results
- Enable streaming for better UX
- Consider using approval mode to review actions before execution

For more information, visit [Cline GitHub](https://github.com/cline/cline).

### Roo Code Extension

AI coding assistant focused on codebase understanding and multi-file editing.

**Installation:**
- VSCode: Extensions → Search "Roo Code" → Install
- VSCodium: Check [Open VSX Registry](https://open-vsx.org/)
- Extension ID: `RooCode.roo-code` (if available)

**Features:**
- Deep codebase understanding
- Multi-file refactoring
- Semantic code search
- Context-aware suggestions
- Works with Claude, OpenAI, and local models

**Configuration:**

Open Roo Code settings → Configure API:

```json
{
  "rooCode.apiProvider": "anthropic",
  "rooCode.apiKey": "your-api-key",
  "rooCode.model": "claude-3-5-sonnet-20241022"
}
```

Or for local models:
```json
{
  "rooCode.apiProvider": "openai",
  "rooCode.apiBaseUrl": "http://localhost:1234/v1",
  "rooCode.apiKey": "lm-studio",
  "rooCode.model": "openai/gpt-oss-120b"
}
```

**Using with Local LLMs via OpenAI-Compatible API:**

1. **LM Studio:**
   - Start LM Studio and enable Local Server (port 1234)
   - Configure Roo Code:
     ```json
     {
       "rooCode.apiProvider": "openai",
       "rooCode.apiBaseUrl": "http://localhost:1234/v1",
       "rooCode.apiKey": "lm-studio",
       "rooCode.model": "openai/gpt-oss-120b"
     }
     ```

2. **Ollama:**
   - Start Ollama: `ollama serve`
   - Pull model: `ollama pull qwen3-coder:30b`
   - Configure Roo Code:
     ```json
     {
       "rooCode.apiProvider": "openai",
       "rooCode.apiBaseUrl": "http://localhost:11434/v1",
       "rooCode.apiKey": "ollama",
       "rooCode.model": "qwen3-coder:30b"
     }
     ```

3. **Other Servers:**
   - Set `apiBaseUrl` to your server's endpoint
   - Adjust `apiKey` and `model` as needed

For more information, visit [Roo Code](https://roosoft.io/).

### Gemini Code Assist Extension

Google's AI coding assistant powered by Gemini models.

**Installation:**
- VSCode: Extensions → Search "Gemini Code Assist" → Install
- Extension ID: `GoogleCloudTools.cloudcode` (part of Cloud Code extension)

**Features:**
- Code completions powered by Gemini
- Chat interface
- Code explanations
- Integration with Google Cloud
- Multi-language support

**Requirements:**
- Google Cloud account
- Gemini API access

**Configuration:**

Sign in with Google Cloud account and configure in settings:

```json
{
  "cloudcode.gemini.enabled": true,
  "cloudcode.gemini.model": "gemini-pro"
}
```

**Note on Local LLMs:**
Gemini Code Assist does not support local LLMs or custom OpenAI-compatible endpoints. It only works with Google's Gemini API.

For more information, visit [Gemini Code Assist](https://cloud.google.com/gemini/docs/codeassist/overview).

### Claude Dev Extension

Note: This extension has been renamed to **Cline** (see above). The "Claude Dev" name is deprecated but may still appear in searches.

**Migration:**
If you have the old "Claude Dev" extension:
1. Uninstall "Claude Dev"
2. Install "Cline" (same functionality, updated name)
3. Settings and API keys will be migrated automatically

### Chat Extensions

**GitHub Copilot Chat:**
- Extension ID: `GitHub.copilot-chat`
- Included with GitHub Copilot subscription
- Inline chat with `Cmd/Ctrl+I`
- Sidebar chat for longer conversations
- Slash commands: `/explain`, `/fix`, `/tests`, etc.
- **Local LLMs:** Not supported

**ChatGPT - Official OpenAI Extension:**
- Extension ID: `openai.chatgpt-vscode`
- Installation: Extensions → Search "ChatGPT"
- Features:
  - GPT-4 and GPT-3.5 support
  - Code generation and explanation
  - Refactoring assistance
- Requires: OpenAI API key
- **Local LLMs:** Not officially supported, but may work with OpenAI-compatible APIs (experimental)

**Bito AI:**
- Extension ID: `Bito.Bito`
- Free tier available
- Chat interface
- Code generation
- Multi-LLM support
- **Local LLMs:** Check Bito documentation for custom endpoint support

**Tabnine Chat:**
- Extension ID: `TabNine.tabnine-vscode`
- Included with Tabnine Pro
- Privacy-focused
- Local model option
- **Local LLMs:** Pro version supports local deployment (proprietary, not OpenAI-compatible API)

### Other VSCode AI Extensions

**Codeium (Free):**
- Extension ID: `Codeium.codeium`
- Free AI code completions
- Chat interface
- Supports 70+ languages
- No API key required
- Website: [codeium.com](https://codeium.com/)
- **Local LLMs:** Not supported - uses Codeium's cloud service only

**Tabnine:**
- Extension ID: `TabNine.tabnine-vscode`
- AI code completions
- Free and Pro tiers
- Local model option (Pro)
- Team learning capabilities
- Website: [tabnine.com](https://www.tabnine.com/)
- **Local LLMs:** Pro version supports local deployment (proprietary, not OpenAI-compatible API)

**Amazon CodeWhisperer (Free):**
- Extension ID: `AmazonWebServices.aws-toolkit-vscode`
- Free for individual use
- Code suggestions
- Security scanning
- AWS integration
- Website: [aws.amazon.com/codewhisperer](https://aws.amazon.com/codewhisperer/)
- **Local LLMs:** Not supported - uses AWS cloud service only

**Cursor:**
- Note: Cursor is a fork of VSCode with built-in AI, not an extension
- Standalone editor with AI-first design
- Multimodel support (GPT-4, Claude, local)
- Website: [cursor.sh](https://cursor.sh/)
- **Local LLMs:** Supports custom OpenAI-compatible endpoints in settings

**Recommended Setup for VSCode/VSCodium:**

For users with GitHub Copilot, Claude API access, and local LLMs:

**Option 1: Comprehensive Setup**
1. **GitHub Copilot** - For inline completions and chat
2. **Continue** - For multimodel support (Claude, local LLMs)
3. **Cline** - For autonomous coding tasks

**Option 2: Minimal Setup**
1. **GitHub Copilot** - For inline completions
2. **Cline** - Configure with Claude and local LLM support

**Option 3: Free & Open Source**
1. **Continue** - Configure with Claude API and local LLMs
2. **Codeium** - For additional completions (free)

**VSCodium Notes:**
- VSCodium uses Open VSX Registry instead of Microsoft Marketplace
- Most extensions are available on Open VSX
- Some Microsoft extensions (like Copilot) require manual installation
- Manual installation: Download `.vsix` from GitHub releases → Extensions → Install from VSIX

### Automating Extension Installation and Configuration

VSCode and VSCodium do not offer an agentic mode for automated extension installation and configuration through natural language. However, both provide robust command-line interfaces for automation.

**Command-Line Extension Installation:**

```bash
# Install single extension
code --install-extension GitHub.copilot

# Install multiple extensions
code --install-extension GitHub.copilot \
     --install-extension Continue.continue \
     --install-extension saoudrizwan.claude-dev \
     --install-extension RooCode.roo-code

# For VSCodium (uses Open VSX Registry)
codium --install-extension Continue.continue
```

**Project-Level Extension Recommendations:**

Create `.vscode/extensions.json` in your project:

```json
{
  "recommendations": [
    "GitHub.copilot",
    "GitHub.copilot-chat",
    "Continue.continue",
    "saoudrizwan.claude-dev",
    "RooCode.roo-code"
  ]
}
```

VSCode will prompt users to install recommended extensions when opening the project.

**Automated Configuration Script:**

```bash
#!/bin/bash
# install-vscode-ai-extensions.sh

# Install extensions
code --install-extension GitHub.copilot
code --install-extension GitHub.copilot-chat
code --install-extension Continue.continue
code --install-extension saoudrizwan.claude-dev

# Configure Continue (manual API key entry required)
mkdir -p ~/.continue
cat > ~/.continue/config.json << 'EOF'
{
  "models": [
    {
      "title": "Claude Sonnet",
      "provider": "anthropic",
      "model": "claude-3-5-sonnet-20241022",
      "apiKey": "${ANTHROPIC_API_KEY}"
    },
    {
      "title": "LM Studio Local",
      "provider": "openai",
      "model": "openai/gpt-oss-120b",
      "apiBase": "http://localhost:1234/v1",
      "apiKey": "lm-studio"
    }
  ]
}
EOF

echo "Extensions installed. Replace \${ANTHROPIC_API_KEY} with your actual API key."
```

Make the script executable and run:
```bash
chmod +x install-vscode-ai-extensions.sh
./install-vscode-ai-extensions.sh
```

**Settings Sync:**
- Settings → Settings Sync → Sign in with GitHub/Microsoft
- Syncs extensions and settings across machines
- Note: Does not support scripted automation

**Using AI CLI Tools for Script Generation:**

```bash
# Use Aider to create setup scripts
aider "Create a bash script that installs GitHub Copilot, Continue, and Cline extensions for VSCode, and configures Continue with LM Studio support"

# Use Goose for autonomous setup assistance
goose "Help me set up VSCode with Continue extension configured for Claude API and local LM Studio"

# Use Continue or Cline to generate configuration
# Open VSCode → Continue/Cline chat → "Generate a Continue config.json for Claude and LM Studio"
```

**Infrastructure as Code Approach:**

Create a dotfiles repository with IDE configuration scripts:

```bash
#!/bin/bash
# ~/dotfiles/setup-vscode.sh

# VSCode extensions
code --install-extension GitHub.copilot
code --install-extension Continue.continue
code --install-extension saoudrizwan.claude-dev

# Copy pre-configured settings (non-sensitive)
cp ~/dotfiles/vscode-settings.json ~/.config/Code/User/settings.json
cp ~/dotfiles/continue-config.json ~/.continue/config.json

echo "Manual steps:"
echo "1. Add your API keys to ~/.continue/config.json"
echo "2. Sign in to GitHub Copilot"
```

**Template Repository Approach:**

Create template repositories with pre-configured:
- `.vscode/extensions.json` - Recommended extensions
- `.vscode/settings.json` - Project-specific settings
- `.continue/config.json.example` - Configuration template

Users clone the template and add their API keys.

**Limitations:**
- ❌ No agentic/conversational extension installation
- ❌ No natural language configuration
- ❌ No built-in automation for secure API key entry
- ⚠️ Settings files require manual editing (but safer than IDEA's XML)

**Why No Agentic Configuration?**
1. **Security**: API keys shouldn't be passed through conversational interfaces or stored in scripts
2. **IDE Limitations**: VSCode doesn't expose comprehensive configuration APIs for automation
3. **Complexity**: Extension settings vary significantly
4. **User Control**: Manual configuration ensures users understand their setup

**Current State Summary (February 2026):**

✅ **Available:**
- Command-line extension installation
- Project-level extension recommendations
- Settings sync across machines
- Script-based configuration (with manual API key entry)

❌ **Not Available:**
- Natural language: "Install Copilot and configure with my Claude key"
- Conversational setup and configuration
- Fully automated API key management
- Agentic mode for IDE configuration

**Future Possibilities:**
- GitHub Copilot may integrate extension management in VSCode
- New AI-first IDEs (Cursor, Windsurf, Zed) may offer better automation
- Browser-based IDEs (GitHub Codespaces, Gitpod) already have better automation support

