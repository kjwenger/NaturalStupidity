# NaturalStupidity
Playground for all things Artificial Intelligence

## Table of Contents

- [Prerequisites](#prerequisites)
  - [Node.js Installation](#nodejs-installation)
    - [Linux and macOS](#linux-and-macos)
      - [Using curl or wget](#using-curl-or-wget-recommended-for-fresh-installations)
      - [Using Homebrew](#using-homebrew-if-homebrew-is-already-installed)
    - [Windows](#windows-1)
    - [Using nvm to Install Node.js](#using-nvm-to-install-nodejs)
- [Local LLM Runtimes](#local-llm-runtimes)
  - [LM Studio](#lm-studio-installation)
    - [macOS](#macos)
    - [macOS with Homebrew](#macos-with-homebrew)
    - [Linux](#linux)
    - [Linux with AppImage](#linux-with-appimage)
    - [Windows](#windows)
    - [Recommended Models for LM Studio](#recommended-models-for-lm-studio)
    - [Recommended Models from Gemini](#recommended-models-from-gemini-as-of-20206-02-10)
    - [Recommended Models from Claude](#recommended-models-from-claude)
  - [Ollama](#ollama-installation)
    - [macOS (Ollama)](#macos-ollama)
    - [macOS with Homebrew (Ollama)](#macos-with-homebrew-ollama)
    - [Linux (Ollama)](#linux-ollama)
    - [Windows (Ollama)](#windows-ollama)
    - [Docker (Ollama)](#docker-ollama)
    - [Pulling and Running Models (Ollama)](#pulling-and-running-models-ollama)
  - [MLX (macOS Only)](#mlx-installation-macos-only)
    - [pip (MLX)](#pip-mlx)
    - [Homebrew (MLX)](#homebrew-mlx)
    - [Running Models (MLX)](#running-models-mlx)
    - [OpenAI-Compatible Server (MLX)](#openai-compatible-server-mlx)
  - [EXO](#exo-installation)
    - [macOS (EXO)](#macos-exo)
    - [macOS App (EXO)](#macos-app-exo)
    - [Linux (EXO)](#linux-exo)
    - [Running Models (EXO)](#running-models-exo)
- [AI CLI Tools](#ai-cli-tools)
  - [Discovering New AI CLI Tools](#discovering-new-ai-cli-tools)
  - [Aider CLI](#aider-cli)
    - [Using Aider with Local LLMs via LM Studio](#using-aider-with-local-llms-via-lm-studio)
    - [Aider Config Gists (LM Studio)](#aider-config-gists-lm-studio)
    - [Bash Completion on Linux (Aider)](#bash-completion-on-linux-aider)
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
    - [OpenCode Config Gist (LM Studio)](#opencode-config-gist-lm-studio)
    - [Bash Completion on Linux (OpenCode)](#bash-completion-on-linux-opencode)
  - [Qwen CLI](#qwen-cli)
    - [Using Qwen with Local LLMs via LM Studio](#using-qwen-with-local-llms-via-lm-studio)
    - [Qwen Config Gist (LM Studio)](#qwen-config-gist-lm-studio)
    - [Bash Completion on Linux (Qwen)](#bash-completion-on-linux-qwen)
  - [Warp CLI](#warp-cli)
- [AI Spec Tools](#ai-spec-tools)
  - [OpenSpec CLI](#openspec-cli)
  - [GitHub Spec Kit](#github-spec-kit)
- [AI IDE Plugins](#ai-ide-plugins)
  - [IntelliJ IDEA Plugins](#intellij-idea-plugins)
    - [GitHub Copilot Plugin](#github-copilot-plugin)
    - [Continue Plugin](#continue-plugin)
    - [CodeGPT Plugin](#codegpt-plugin)
    - [Other AI Plugins](#other-ai-plugins)
    - [Automating Plugin Installation and Configuration](#automating-plugin-installation-and-configuration)
  - [VSCode and VSCodium Plugins](#vscode-and-vscodium-plugins)
    - [GitHub Copilot Extension](#github-copilot-extension)
    - [Continue Extension](#continue-extension)
    - [Cline Extension](#cline-extension)
    - [Roo Code Extension](#roo-code-extension)
    - [Gemini Code Assist Extension](#gemini-code-assist-extension)
    - [Claude Dev Extension](#claude-dev-extension)
    - [Chat Extensions](#chat-extensions)
    - [Other VSCode AI Extensions](#other-vscode-ai-extensions)
    - [Automating Extension Installation and Configuration](#automating-extension-installation-and-configuration)
- [Developer Tools](#developer-tools)
  - [UV CLI](#uv-cli)

## Prerequisites

### Node.js Installation

To manage Node.js versions, it's recommended to install `nvm` (Node Version Manager).

### Linux and macOS

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

## Local LLM Runtimes

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

#### Recommended Models for LM Studio

The following models are recommended for use with LM Studio and the AI CLI tools in this repository. Models marked with **Vision** include multimodal (image) capabilities via a mmproj projection file.

| Model                                                                                                                     | Parameters       | Quantization | Vision | Hugging Face                                                                                                                                      |
|---------------------------------------------------------------------------------------------------------------------------|------------------|--------------|--------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| [CodeLlama 70B Instruct](https://huggingface.co/TheBloke/CodeLlama-70B-Instruct-GGUF)                                     | 70B              | Q5_K_M       | No     | [TheBloke/CodeLlama-70B-Instruct-GGUF](https://huggingface.co/TheBloke/CodeLlama-70B-Instruct-GGUF)                                               |
| [DeepSeek R1 0528 Qwen3 8B](https://huggingface.co/lmstudio-community/DeepSeek-R1-0528-Qwen3-8B-GGUF)                     | 8B               | Q4_K_M       | No     | [lmstudio-community/DeepSeek-R1-0528-Qwen3-8B-GGUF](https://huggingface.co/lmstudio-community/DeepSeek-R1-0528-Qwen3-8B-GGUF)                     |
| [Devstral Small 2507](https://huggingface.co/lmstudio-community/Devstral-Small-2507-GGUF)                                 | 24B              | Q4_K_M       | Yes    | [lmstudio-community/Devstral-Small-2507-GGUF](https://huggingface.co/lmstudio-community/Devstral-Small-2507-GGUF)                                 |
| [Devstral Small 2507 (unsloth)](https://huggingface.co/unsloth/Devstral-Small-2507-GGUF)                                  | 24B              | UD-Q8_K_XL   | Yes    | [unsloth/Devstral-Small-2507-GGUF](https://huggingface.co/unsloth/Devstral-Small-2507-GGUF)                                                       |
| [Gemma 3 12B IT](https://huggingface.co/lmstudio-community/gemma-3-12b-it-GGUF)                                           | 12B              | Q4_K_M       | Yes    | [lmstudio-community/gemma-3-12b-it-GGUF](https://huggingface.co/lmstudio-community/gemma-3-12b-it-GGUF)                                           |
| [Gemma 3 27B IT QAT](https://huggingface.co/lmstudio-community/gemma-3-27B-it-qat-GGUF)                                   | 27B              | QAT-Q4_0     | Yes    | [lmstudio-community/gemma-3-27B-it-qat-GGUF](https://huggingface.co/lmstudio-community/gemma-3-27B-it-qat-GGUF)                                   |
| [GPT-OSS 20B](https://huggingface.co/unsloth/gpt-oss-20b-GGUF)                                                            | 20B              | F16          | No     | [unsloth/gpt-oss-20b-GGUF](https://huggingface.co/unsloth/gpt-oss-20b-GGUF)                                                                       |
| [GPT-OSS 120B](https://huggingface.co/lmstudio-community/gpt-oss-120b-GGUF)                                               | 120B             | MXFP4        | No     | [lmstudio-community/gpt-oss-120b-GGUF](https://huggingface.co/lmstudio-community/gpt-oss-120b-GGUF)                                               |
| [Llama 3.3 70B Instruct](https://huggingface.co/lmstudio-community/Llama-3.3-70B-Instruct-GGUF)                           | 70B              | Q3_K_L       | No     | [lmstudio-community/Llama-3.3-70B-Instruct-GGUF](https://huggingface.co/lmstudio-community/Llama-3.3-70B-Instruct-GGUF)                           |
| [Llama 4 Scout 17B 16E Instruct](https://huggingface.co/lmstudio-community/Llama-4-Scout-17B-16E-Instruct-GGUF)           | 17B (16 experts) | Q3_K_L       | Yes    | [lmstudio-community/Llama-4-Scout-17B-16E-Instruct-GGUF](https://huggingface.co/lmstudio-community/Llama-4-Scout-17B-16E-Instruct-GGUF)           |
| [Mistral Large Instruct 2411](https://huggingface.co/lmstudio-community/Mistral-Large-Instruct-2411-GGUF)                 | 123B             | Q4_K_M       | No     | [lmstudio-community/Mistral-Large-Instruct-2411-GGUF](https://huggingface.co/lmstudio-community/Mistral-Large-Instruct-2411-GGUF)                 |
| [Mistral Small 3.2 24B Instruct 2506](https://huggingface.co/lmstudio-community/Mistral-Small-3.2-24B-Instruct-2506-GGUF) | 24B              | Q4_K_M       | Yes    | [lmstudio-community/Mistral-Small-3.2-24B-Instruct-2506-GGUF](https://huggingface.co/lmstudio-community/Mistral-Small-3.2-24B-Instruct-2506-GGUF) |
| [Qwen 2.5 Coder 14B Instruct](https://huggingface.co/lmstudio-community/Qwen2.5-Coder-14B-Instruct-GGUF)                  | 14B              | Q4_K_M       | No     | [lmstudio-community/Qwen2.5-Coder-14B-Instruct-GGUF](https://huggingface.co/lmstudio-community/Qwen2.5-Coder-14B-Instruct-GGUF)                   |
| [Qwen 2.5 Coder 32B](https://huggingface.co/lmstudio-community/Qwen2.5-Coder-32B-GGUF)                                    | 32B              | Q4_K_M       | No     | [lmstudio-community/Qwen2.5-Coder-32B-GGUF](https://huggingface.co/lmstudio-community/Qwen2.5-Coder-32B-GGUF)                                     |
| [Qwen3 Coder 30B A3B Instruct](https://huggingface.co/unsloth/Qwen3-Coder-30B-A3B-Instruct-GGUF)                          | 30B (3B active)  | UD-Q8_K_XL   | No     | [unsloth/Qwen3-Coder-30B-A3B-Instruct-GGUF](https://huggingface.co/unsloth/Qwen3-Coder-30B-A3B-Instruct-GGUF)                                     |
| [QwQ 32B](https://huggingface.co/lmstudio-community/QwQ-32B-GGUF)                                                         | 32B              | Q4_K_M       | No     | [lmstudio-community/QwQ-32B-GGUF](https://huggingface.co/lmstudio-community/QwQ-32B-GGUF)                                                         |

**Notes:**
- Models with **Vision** capability include an `mmproj` projection file for processing images alongside text.
- **Q4_K_M** and **Q3_K_L** quantizations offer a good balance of quality and memory usage for consumer hardware.
- **F16** and **UD-Q8_K_XL** quantizations provide higher fidelity but require significantly more VRAM/RAM.
- **MXFP4** and **QAT** are specialized quantization formats optimized for specific hardware or quality targets.
- Multipart GGUF files (GPT-OSS 120B, Llama 4 Scout, Mistral Large) are automatically loaded together by LM Studio.
- The **Qwen3 Coder 30B A3B** is a mixture-of-experts model with only 3B parameters active per inference, making it efficient despite its 30B total size.

### Recommended Models from Gemini (as of 20206-02-10)

Q: Best local LLM for coders with 96GB GPU memory

With 96GB of GPU memory (such as an NVIDIA RTX 6000 Ada/Blackwell or a 96GB Mac Studio M3 Ultra), you are in a high-end,
"GPU-rich"  position that allows you to run large, state-of-the-art coding models  with high context lengths,
or highly capable models with lower quantization. 

Here are the best local LLMs for coding with 96GB VRAM as of late 2025/early 2026:

Top Contenders for 96GB VRAM 

- **Qwen3-Coder-Next (80B - 235B):**   
  Qwen3 models are frequently cited as the best, with the 235B (using 2-3 bit quantization) or the 80B (at high precision) being elite for  coding.
- **GLM-4-Air/4.7-Flash (100B+):**   
  Considered among the best in the ~100B range for general coding, offering high precision and smart reasoning.
- **DeepSeek-Coder-V2/R1 (MoE):**   
  Highly regarded for code generation. A mixture-of-experts (MoE)  approach allows these to fit while maintaining high performance.
- **GPT-OSS-120B:**   
  Often recommended for high-end setups due to its capability, running well with 3-bit or 4-bit quantization. 

Recommended Configurations for 96GB

1. **Maximum Intelligence (Mid-Size Model):** **Qwen3-Next-80B (FP8 or Q8)**. Running this at 80B parameters allows you to keep the model in VRAM for very high-speed, accurate coding.
2. **Maximum Model Size (High-End):** **Qwen3-235B (Q2KXL or 3-bpw EXL3)**. With 96GB, you can use specialized low-bit quants to fit this massive model, which is superior for complex reasoning.
3. **Balanced Performance:** **GLM 4.5 Air (106B)**, often recommended as a daily driver for high-end workstations. 

Best Tools to Run Them

- **vLLM:** Recommended for maximum speed, especially in a Docker environment.
- **EXL2/EXL3:** Excellent for high-speed inference on Nvidia cards, especially for loading large models.
- **[Ollama](https://ollama.com/):** Simple interface for quickly testing these high-parameter models.
- **[LM Studio](https://lmstudio.ai/):** Great for visual management, with 96GB allowing for high-context, high-quantization setups. 

**Summary Recommendation:** Use **Qwen3-Next-80B** with a high-quality (Q6-Q8) quantization for the fastest and most accurate daily coding, or **Qwen3-235B (IQ3/2-bit)** if you require maximum reasoning for complex, multi-file projects.

#### Recommended Models from Claude

The following models are recommended by Claude (Opus 4.6) to maximize a **Ryzen AI MAX 395+ / Radeon 8060S with 96 GB GPU memory**. The focus is on the highest parameter counts and quantization levels that fit within the 96 GB VRAM budget while leaving headroom for KV cache and inference overhead. Models are split into new additions and quantization upgrades to already-installed models.

**New Models**

| Model                                                                                                          | Parameters            | Quantization | Size   | Hugging Face                                                                                                                            | Why This Model                                                                                                                                                                                                                                                 |
|----------------------------------------------------------------------------------------------------------------|-----------------------|--------------|--------|-----------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Qwen3-Coder-Next](https://huggingface.co/unsloth/Qwen3-Coder-Next-GGUF)                                       | 80B (3B active MoE)   | UD-Q8_K_XL   | ~85 GB | [unsloth/Qwen3-Coder-Next-GGUF](https://huggingface.co/unsloth/Qwen3-Coder-Next-GGUF)                                                   | Purpose-built for coding agents and IDE integration (Claude Code, Qwen Code, Cline). 256K context, excels at long-horizon reasoning, tool use, and failure recovery. Only 3B active params means fast inference despite 80B total.                             |
| [DeepSeek R1 Distill Llama 70B](https://huggingface.co/lmstudio-community/DeepSeek-R1-Distill-Llama-70B-GGUF)  | 70B                   | Q8_0         | ~75 GB | [lmstudio-community/DeepSeek-R1-Distill-Llama-70B-GGUF](https://huggingface.co/lmstudio-community/DeepSeek-R1-Distill-Llama-70B-GGUF)   | Strongest open reasoning model in the 70B class. Distilled from DeepSeek-R1, competitive with OpenAI o1 on math, code, and reasoning benchmarks. At Q8_0, near-lossless quality.                                                                               |
| [Devstral 2 123B Instruct 2512](https://huggingface.co/bartowski/mistralai_Devstral-2-123B-Instruct-2512-GGUF) | 123B                  | Q4_K_M       | ~75 GB | [bartowski/mistralai_Devstral-2-123B-Instruct-2512-GGUF](https://huggingface.co/bartowski/mistralai_Devstral-2-123B-Instruct-2512-GGUF) | Latest Mistral coding model (Dec 2025), successor to Mistral Large 2411. 123B dense params purpose-tuned for code generation. Newer and more capable than the Mistral Large Instruct 2411 already installed.                                                   |
| [Qwen 2.5 72B Instruct](https://huggingface.co/lmstudio-community/Qwen2.5-72B-Instruct-GGUF)                   | 72B                   | Q8_0         | ~77 GB | [lmstudio-community/Qwen2.5-72B-Instruct-GGUF](https://huggingface.co/lmstudio-community/Qwen2.5-72B-Instruct-GGUF)                     | Top general-purpose 70B-class model for research and reasoning tasks. Excels at math word problems and structured code-then-explain workflows. Complements the coding-focused Qwen 2.5 Coder models already installed.                                         |
| [Qwen3 235B A22B Instruct 2507](https://huggingface.co/unsloth/Qwen3-235B-A22B-Instruct-2507-GGUF)             | 235B (22B active MoE) | UD-Q2_K_XL   | ~88 GB | [unsloth/Qwen3-235B-A22B-Instruct-2507-GGUF](https://huggingface.co/unsloth/Qwen3-235B-A22B-Instruct-2507-GGUF)                         | Qwen3 flagship with 235B total params and 22B active per token. State-of-the-art instruction following, reasoning, and tool use. Tight fit at ~88 GB -- usable with shorter context lengths. Unsloth Dynamic 2.0 quantization preserves quality even at 2-bit. |

**Quantization Upgrades to Installed Models**

These models are already installed at lower quantization levels. With 96 GB VRAM, they can be upgraded to significantly higher quality:

| Model                                                                                                     | Current Quant | Recommended Quant | New Size | Hugging Face                                                                                                                      | Why Upgrade                                                                                                                                                                   |
|-----------------------------------------------------------------------------------------------------------|---------------|-------------------|----------|-----------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Llama 3.3 70B Instruct](https://huggingface.co/lmstudio-community/Llama-3.3-70B-Instruct-GGUF)           | Q3_K_L        | **Q8_0**          | ~75 GB   | [lmstudio-community/Llama-3.3-70B-Instruct-GGUF](https://huggingface.co/lmstudio-community/Llama-3.3-70B-Instruct-GGUF)           | Biggest quality jump: 3-bit to 8-bit on a 70B dense model. Comparable to Llama 405B performance at a fraction of the size. Clean formatting and strong instruction following. |
| [Mistral Large Instruct 2411](https://huggingface.co/lmstudio-community/Mistral-Large-Instruct-2411-GGUF) | Q4_K_M        | **Q5_K_M**        | ~87 GB   | [lmstudio-community/Mistral-Large-Instruct-2411-GGUF](https://huggingface.co/lmstudio-community/Mistral-Large-Instruct-2411-GGUF) | 123B dense model benefits noticeably from the 4-bit to 5-bit bump, especially in nuanced reasoning and instruction adherence. Pushes close to the 96 GB ceiling.              |
| [Qwen 2.5 Coder 32B](https://huggingface.co/lmstudio-community/Qwen2.5-Coder-32B-GGUF)                    | Q4_K_M        | **Q8_0**          | ~34 GB   | [lmstudio-community/Qwen2.5-Coder-32B-GGUF](https://huggingface.co/lmstudio-community/Qwen2.5-Coder-32B-GGUF)                     | At 34 GB, easily fits in 96 GB. Q8_0 is near-lossless for a 32B model, maximizing code generation accuracy with no practical trade-off.                                       |
| [QwQ 32B](https://huggingface.co/lmstudio-community/QwQ-32B-GGUF)                                         | Q4_K_M        | **Q8_0**          | ~34 GB   | [lmstudio-community/QwQ-32B-GGUF](https://huggingface.co/lmstudio-community/QwQ-32B-GGUF)                                         | QwQ is a reasoning-specialized model. Q8_0 preserves its chain-of-thought fidelity at a size that leaves ample room for long context windows.                                 |

**Hardware Context:**
- **Target hardware:** AMD Ryzen AI MAX 395+ with Radeon 8060S, 96 GB unified GPU memory, ~215 GB/s memory bandwidth (256-bit LPDDR5X-8000).
- **Expected throughput:** ~10-15 tok/s on 70B Q8 dense models; faster on MoE models since only active parameters are read per token.
- **Memory budget:** ~80-88 GB for model weights leaves 8-16 GB for KV cache, context, and runtime overhead. Larger context windows consume more KV cache memory.

**Sources:**
- [AMD: Run up to 128B parameter LLMs on Ryzen AI MAX+ with LM Studio](https://www.amd.com/en/blogs/2025/amd-ryzen-ai-max-upgraded-run-up-to-128-billion-parameter-llms-lm-studio.html) -- hardware capabilities and LM Studio integration for Strix Halo
- [Strix Halo (Ryzen AI MAX+ 395) LLM Benchmark Results](https://forum.level1techs.com/t/strix-halo-ryzen-ai-max-395-llm-benchmark-results/233796) -- community benchmarks for token throughput on this hardware
- [AMD Strix Halo GPU LLM Performance Tests (Framework)](https://community.frame.work/t/amd-strix-halo-ryzen-ai-max-395-gpu-llm-performance-tests/72521) -- additional performance data
- [10 Best Open-Source LLMs (2025)](https://huggingface.co/blog/daya-shankar/open-source-llms) -- model landscape overview for Llama 4, Qwen 3, DeepSeek R1
- [Best Local LLMs for Coding](https://www.mslinn.com/llm/7900-coding-llms.html) -- comparative analysis of 70B-class coding models (DeepSeek, Qwen, Llama)
- [Unsloth Dynamic 2.0 Quantization](https://unsloth.ai/docs/models/qwen3-how-to-run-and-fine-tune/qwen3-2507) -- how UD quants achieve superior accuracy at low bit rates
- [Qwen3-Coder-Next: How to Run Locally](https://unsloth.ai/docs/models/qwen3-coder-next) -- model capabilities, memory requirements, and recommended inference settings

### Ollama Installation

Ollama is a lightweight, open-source (MIT) framework for running LLMs locally. It functions like "Docker for LLMs" — pull a model and interact with it in seconds. Ollama is CLI-first and provides a built-in OpenAI-compatible API on port `11434`, making it ideal for scripting, automation, and integration with AI CLI tools.

#### macOS (Ollama)

Download the latest version from [ollama.com/download](https://ollama.com/download):
1. Download the `.dmg` file for macOS
2. Open the downloaded file and install
3. Ollama runs as a background service automatically

#### macOS with Homebrew (Ollama)

Install using Homebrew:
```bash
brew install ollama
```

#### Linux (Ollama)

Install using the official install script (recommended):
```bash
curl -fsSL https://ollama.com/install.sh | sh
```

This auto-detects your system architecture, installs the binary, and configures Ollama as a systemd service. Running it again will update Ollama.

Manage the service:
```bash
sudo systemctl start ollama
sudo systemctl status ollama
sudo systemctl enable ollama    # start on boot
```

#### Windows (Ollama)

Download `OllamaSetup.exe` from [ollama.com/download/windows](https://ollama.com/download/windows):
1. Run the installer (no Administrator privileges required)
2. Ollama is added to the system PATH
3. The server starts in the background automatically and auto-starts on boot

#### Docker (Ollama)

Ollama has an official Docker image at `ollama/ollama` on [Docker Hub](https://hub.docker.com/r/ollama/ollama).

**CPU only:**
```bash
docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
```

**NVIDIA GPU:**
```bash
docker run -d --gpus all -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
```

**AMD GPU (ROCm):**
```bash
docker run -d --device /dev/kfd --device /dev/dri \
  -v ollama:/root/.ollama -p 11434:11434 \
  --name ollama ollama/ollama:rocm
```

Run a model inside the container:
```bash
docker exec -it ollama ollama run llama3.2
```

#### Pulling and Running Models (Ollama)

```bash
# Pull a model
ollama pull llama3.2
ollama pull qwen3
ollama pull deepseek-r1
ollama pull codellama

# Run a model (pulls automatically if not already downloaded)
ollama run llama3.2

# Run with a specific prompt (non-interactive)
ollama run llama3.2 "Explain quantum computing in one sentence"

# List locally installed models
ollama list

# Show currently running models
ollama ps

# Remove a model
ollama rm llama3.2
```

Browse the full model library at [ollama.com/library](https://ollama.com/library).

**OpenAI-Compatible API:**

Ollama provides an OpenAI-compatible API at `http://localhost:11434/v1`:

```bash
curl -X POST http://localhost:11434/v1/chat/completions \
  -H "Content-Type: application/json" \
  -d '{
    "model": "llama3.2",
    "messages": [{"role": "user", "content": "Hello!"}]
  }'
```

To use with AI CLI tools, configure them to point to Ollama's endpoint:
```bash
export OPENAI_API_BASE=http://localhost:11434/v1
export OPENAI_API_KEY=ollama   # required by some tools but ignored by Ollama
```

**Ollama Launch (Quick Tool Integration):**

Ollama provides a `launch` command for setting up integrations with AI CLI tools:
```bash
ollama launch    # Interactive setup for Claude Code, OpenCode, Codex, etc.
```

**Key Differences from LM Studio:**
- CLI-first vs. GUI-first design
- Runs as a background service (systemd on Linux, auto-start on macOS/Windows)
- Uses its own model registry and format instead of GGUF files from Hugging Face
- Official Docker image available
- MIT open-source license (LM Studio is proprietary, free for personal use)
- Better suited for automation, scripting, and server deployments

For more information, visit [Ollama documentation](https://docs.ollama.com/) and [GitHub repository](https://github.com/ollama/ollama).

### MLX Installation (macOS Only)

MLX is an open-source array framework for machine learning on Apple Silicon, created by Apple Machine Learning Research. **mlx-lm** is the companion package for running, serving, and fine-tuning LLMs. MLX is designed specifically for the unified memory architecture of Apple Silicon chips (M1, M2, M3, M4, M5) and provides an OpenAI-compatible API server on port `8080`.

**Requirements:**
- macOS with Apple Silicon (M1 or later)
- macOS >= 14.0 (Sonoma)
- Python >= 3.10 (native ARM, not Rosetta x86)

#### pip (MLX)

```bash
# Install mlx-lm (includes mlx as a dependency)
pip install mlx-lm
```

Or using conda:
```bash
conda install -c conda-forge mlx-lm
```

#### Homebrew (MLX)

```bash
brew install mlx-lm
```

#### Running Models (MLX)

```bash
# Generate text (auto-downloads model from Hugging Face on first use)
mlx_lm.generate --model mlx-community/Mistral-7B-Instruct-v0.3-4bit \
    --prompt "Write a Python function to sort a list"

# Interactive chat
mlx_lm.chat --model mlx-community/Qwen3-8B-4bit

# Convert and quantize a model from Hugging Face
mlx_lm.convert --hf-path meta-llama/Llama-3.2-3B-Instruct -q --q-bits 4
```

Models are auto-downloaded from Hugging Face and cached locally. Pre-quantized models are available from the [mlx-community](https://huggingface.co/mlx-community) organization on Hugging Face.

#### OpenAI-Compatible Server (MLX)

mlx-lm provides a built-in OpenAI-compatible API server:

```bash
# Start the server (default: localhost:8080)
mlx_lm.server --model mlx-community/Qwen3-8B-4bit

# Custom host, port, and max tokens
mlx_lm.server --model mlx-community/Qwen3-8B-4bit \
    --host 0.0.0.0 --port 8080 --max-tokens 128000
```

Test with curl:
```bash
curl http://localhost:8080/v1/chat/completions \
  -H "Content-Type: application/json" \
  -d '{
    "model": "mlx-community/Qwen3-8B-4bit",
    "messages": [{"role": "user", "content": "Hello!"}],
    "max_tokens": 512
  }'
```

To use with AI CLI tools, configure them to point to the MLX server:
```bash
export OPENAI_API_BASE=http://localhost:8080/v1
export OPENAI_API_KEY=mlx   # required by some tools but ignored by mlx-lm
```

**Notes:**
- The default `max_tokens` is 512, which is too low for coding tasks. Set `--max-tokens 128000` when starting the server.
- The server is designed for local development use, not production deployment.
- MLX leverages Apple Silicon's unified memory, allowing models to use the full system RAM without CPU-GPU transfer overhead.

For more information, visit the [MLX GitHub repository](https://github.com/ml-explore/mlx), [mlx-lm GitHub repository](https://github.com/ml-explore/mlx-lm), and [MLX documentation](https://ml-explore.github.io/mlx/).

### EXO Installation

EXO is a distributed AI inference framework from [EXO Labs](https://exolabs.net/) that connects multiple devices into a single AI cluster, enabling you to run models that would not fit on a single device. It uses peer-to-peer discovery — devices on the same network find each other automatically with zero configuration. EXO provides an OpenAI-compatible API on port `52415`.

**Prerequisites (all platforms):**
- Python >= 3.12.0
- [uv](https://astral.sh/uv) (Python package/project manager)
- Node.js v18+ and npm (for building the dashboard)
- Rust nightly toolchain

#### macOS (EXO)

```bash
# Install prerequisites via Homebrew
brew install uv macmon node

# Install Rust nightly
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh
rustup toolchain install nightly

# Clone and build
git clone https://github.com/exo-explore/exo
cd exo/dashboard && npm install && npm run build && cd ..

# Run
uv run exo
```

#### macOS App (EXO)

A pre-built DMG is available for macOS Tahoe 26.2 or later:

Download from: https://assets.exolabs.net/EXO-latest.dmg

#### Linux (EXO)

```bash
# Install prerequisites (Ubuntu/Debian)
sudo apt update
sudo apt install nodejs npm
curl -LsSf https://astral.sh/uv/install.sh | sh
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh
rustup toolchain install nightly

# Clone and build
git clone https://github.com/exo-explore/exo
cd exo/dashboard && npm install && npm run build && cd ..

# Run
uv run exo
```

**Note:** EXO currently runs on CPU only on Linux. GPU support (CUDA, Vulkan) is under active development.

**Windows:** Not officially supported. See [tracking issue #606](https://github.com/exo-explore/exo/issues/606).

#### Running Models (EXO)

Once started with `uv run exo`, the dashboard is available at `http://localhost:52415` and the OpenAI-compatible API is served on the same port.

```bash
# List available models
curl http://localhost:52415/v1/models

# Run inference
curl -X POST http://localhost:52415/v1/chat/completions \
  -H "Content-Type: application/json" \
  -d '{
    "model": "llama-3.2-1b",
    "messages": [{"role": "user", "content": "Hello!"}],
    "stream": true
  }'
```

You can use shorthand model IDs (e.g., `llama-3.2-1b`) or full Hugging Face identifiers (e.g., `mlx-community/Llama-3.2-1B-Instruct-4bit`).

To use with AI CLI tools, configure them to point to the EXO endpoint:
```bash
export OPENAI_API_BASE=http://localhost:52415/v1
export OPENAI_API_KEY=exo   # required by some tools but ignored by EXO
```

**Key Features:**
- **Automatic device discovery** — devices on the same network find each other with zero configuration
- **Topology-aware auto-parallelism** — automatically determines the best way to split a model across devices
- **RDMA over Thunderbolt 5** — 99% latency reduction between connected devices (macOS Tahoe 26.2+)
- **Built-in web dashboard** for monitoring and management
- Supports models too large for a single device (e.g., DeepSeek v3.1 671B, Qwen3-235B)

For more information, visit the [EXO GitHub repository](https://github.com/exo-explore/exo) and [EXO Labs website](https://exolabs.net/).

## AI CLI Tools

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

##### Aider Config Gists (LM Studio)

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

##### Bash Completion on Linux (Aider)

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

For more information, visit [GitHub Copilot CLI documentation](https://docs.github.com/en/copilot/concepts/agents/about-copilot-cli).

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

##### OpenCode Config Gist (LM Studio)

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

##### Bash Completion on Linux (OpenCode)

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

##### Qwen Config Gist (LM Studio)

A pre-built Qwen settings file with all LM Studio local models is available as a public Gist.

| File                                                                               | Destination             | Description                                         |
|------------------------------------------------------------------------------------|-------------------------|-----------------------------------------------------|
| [settings.json](https://gist.github.com/kjwenger/36062285c744156ac253aa26d9063255) | `~/.qwen/settings.json` | API base URL, model providers, all LM Studio models |

**Download and install:**
```bash
mkdir -p ~/.qwen
gh gist view 36062285c744156ac253aa26d9063255 --raw > ~/.qwen/settings.json
```

##### Bash Completion on Linux (Qwen)

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

## AI Spec Tools

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

**Note:** OpenSpec is a workflow framework that works alongside AI coding assistants rather than making direct LLM calls. It provides structured specifications that your AI tools (Claude Code, OpenCode, Cursor, etc.) follow during implementation. Configure local LLM support in your chosen AI tool, and OpenSpec will work with whatever provider that tool uses.

For more information, visit [OpenSpec GitHub repository](https://github.com/Fission-AI/OpenSpec) and the [GitHub Blog on spec-driven development](https://github.blog/ai-and-ml/generative-ai/spec-driven-development-with-ai-get-started-with-a-new-open-source-toolkit/).

#### GitHub Spec Kit

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

## AI IDE Plugins

### IntelliJ IDEA Plugins

IntelliJ IDEA supports multiple AI coding assistants through plugins, allowing you to use GitHub Copilot, Claude, and local LLMs directly in your IDE.

#### GitHub Copilot Plugin

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

#### Continue Plugin

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

#### CodeGPT Plugin

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

#### Other AI Plugins

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

#### Automating Plugin Installation and Configuration

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

### VSCode and VSCodium Plugins

VSCode and VSCodium support a wide variety of AI coding assistants through extensions. These extensions work identically in both editors.

#### GitHub Copilot Extension

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

#### Continue Extension

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

#### Cline Extension

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

#### Roo Code Extension

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

#### Gemini Code Assist Extension

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

#### Claude Dev Extension

Note: This extension has been renamed to **Cline** (see above). The "Claude Dev" name is deprecated but may still appear in searches.

**Migration:**
If you have the old "Claude Dev" extension:
1. Uninstall "Claude Dev"
2. Install "Cline" (same functionality, updated name)
3. Settings and API keys will be migrated automatically

#### Chat Extensions

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

#### Other VSCode AI Extensions

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

#### Automating Extension Installation and Configuration

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

## Developer Tools

#### UV CLI

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
