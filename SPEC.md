# AI Spec Tools

<!-- TOC -->
* [AI Spec Tools](#ai-spec-tools)
  * [OpenSpec CLI](#openspec-cli)
    * [Using OpenSpec with Mammouth AI](#using-openspec-with-mammouth-ai)
  * [GitHub Spec Kit](#github-spec-kit)
    * [Using GitHub Spec Kit with Mammouth AI](#using-github-spec-kit-with-mammouth-ai)
<!-- TOC -->

## OpenSpec CLI

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

**Note:** OpenSpec is a workflow framework that works alongside AI coding assistants rather than making direct LLM calls. It provides structured specifications that your AI tools (Claude Code, OpenCode, Cursor, etc.) follow during implementation. Configure local LLM support in your chosen AI tool, and OpenSpec will work with whatever provider that tool uses.

For more information, visit [OpenSpec GitHub repository](https://github.com/Fission-AI/OpenSpec) and the [GitHub Blog on spec-driven development](https://github.blog/ai-and-ml/generative-ai/spec-driven-development-with-ai-get-started-with-a-new-open-source-toolkit/).

### Using OpenSpec with Mammouth AI

OpenSpec does not make direct LLM calls — it generates structured specification documents that your chosen AI coding assistant reads and follows. To use OpenSpec with [Mammouth AI](https://mammouth.ai/):

1. Set up a Mammouth AI account and API key (see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai)), then configure your AI coding assistant (e.g., Aider, OpenCode, Goose) to use it (see the corresponding tool's *Using [Tool] with Mammouth AI* section in [CLI.md](./CLI.md)).
2. Initialize OpenSpec in your project as usual:
```bash
openspec init --tools claude  # or whichever AI tool you configured with Mammouth AI
```
3. Create a specification and let your Mammouth AI-backed assistant implement it:
```bash
openspec proposal create "feature-name"
```

OpenSpec will automatically work through whichever AI tool you have configured. No additional Mammouth AI-specific configuration is needed in OpenSpec itself.

## GitHub Spec Kit

GitHub Spec Kit is a fork of OpenSpec, designed for spec-driven development (SDD) in AI-powered workflows. It helps structure project requirements, motivations, and technical details before AI agents build the software. It includes the `specify` CLI for bootstrapping projects with SDD scaffolding and a set of templates and helper scripts.

**Prerequisites:**
- `uv` (for package management)
- Python 3.11+
- Git
- An AI coding agent (e.g., Claude Code, GitHub Copilot, Gemini CLI)

**Installation:**

The recommended persistent installation uses `uv tool install` to make the `specify` command available globally:
```bash
uv tool install specify-cli --from git+https://github.com/github/spec-kit.git
```
After installation, you can use the `specify` command. For example, to create a new project:
```bash
specify init <PROJECT_NAME>
```
Or to initialize in an existing project:
```bash
specify init . --ai claude
```
You can check installed tools with `specify check`.

**One-time Usage:**
For a one-time run without persistent installation, use `uvx`:
```bash
uvx --from git+https://github.com/github/spec-kit.git specify init <PROJECT_NAME>
```

### Using GitHub Spec Kit with Mammouth AI

GitHub Spec Kit (the `specify` CLI) generates specification scaffolding and relies on an external AI coding agent to implement the generated specs. It does not make direct LLM calls. To use it with [Mammouth AI](https://mammouth.ai/):

1. Set up a Mammouth AI account and API key (see [PROVIDERS.md — Mammouth AI](./PROVIDERS.md#mammouth-ai)), then configure your AI coding agent to use it — see the corresponding tool's *Using [Tool] with Mammouth AI* section in [CLI.md](./CLI.md) (e.g., *Using Claude with Mammouth AI* or *Using OpenCode with Mammouth AI*).
2. Initialize the Spec Kit in your project, passing the AI tool you have configured:
```bash
specify init . --ai claude   # or whichever agent is configured with Mammouth AI
```
3. Let your Mammouth AI-backed agent implement the generated specification. No additional Mammouth AI-specific configuration is required in the Spec Kit itself.
