# Local LLM Runtimes

<!-- TOC -->
* [Local LLM Runtimes](#local-llm-runtimes)
  * [LM Studio Installation](#lm-studio-installation)
    * [macOS](#macos)
    * [macOS with Homebrew](#macos-with-homebrew)
    * [Linux](#linux)
    * [Linux with AppImage](#linux-with-appimage)
    * [Windows](#windows)
    * [Recommended Models for LM Studio](#recommended-models-for-lm-studio)
    * [Recommended Models from Gemini (as of 20206-02-10)](#recommended-models-from-gemini-as-of-20206-02-10)
    * [Recommended Models from Claude](#recommended-models-from-claude)
  * [Ollama Installation](#ollama-installation)
    * [macOS (Ollama)](#macos-ollama)
    * [macOS with Homebrew (Ollama)](#macos-with-homebrew-ollama)
    * [Linux (Ollama)](#linux-ollama)
    * [Windows (Ollama)](#windows-ollama)
    * [Docker (Ollama)](#docker-ollama)
    * [Pulling and Running Models (Ollama)](#pulling-and-running-models-ollama)
  * [MLX Installation (macOS Only)](#mlx-installation-macos-only)
    * [pip (MLX)](#pip-mlx)
    * [Homebrew (MLX)](#homebrew-mlx)
    * [Running Models (MLX)](#running-models-mlx)
    * [OpenAI-Compatible Server (MLX)](#openai-compatible-server-mlx)
  * [EXO Installation](#exo-installation)
    * [macOS (EXO)](#macos-exo)
    * [macOS App (EXO)](#macos-app-exo)
    * [Linux (EXO)](#linux-exo)
    * [Running Models (EXO)](#running-models-exo)
<!-- TOC -->

## LM Studio Installation

LM Studio allows you to run Large Language Models locally on your machine. It provides an OpenAI-compatible API server that can be used with various AI CLI tools.

### macOS

Download the latest version from [lmstudio.ai/download](https://lmstudio.ai/download):
1. Download the `.dmg` file for macOS
2. Open the downloaded file
3. Drag LM Studio to your Applications folder
4. Launch LM Studio from Applications

### macOS with Homebrew

Install using Homebrew:
```bash
brew install --cask lm-studio
```

### Linux

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

### Linux with AppImage

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

### Windows

Download the latest version from [lmstudio.ai/download](https://lmstudio.ai/download):
1. Download the `.exe` installer for Windows
2. Run the installer
3. Follow the installation wizard
4. Launch LM Studio from the Start Menu

For more information, visit [LM Studio documentation](https://lmstudio.ai/docs/app/basics).

### Recommended Models for LM Studio

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

### Recommended Models from Claude

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

## Ollama Installation

Ollama is a lightweight, open-source (MIT) framework for running LLMs locally. It functions like "Docker for LLMs" — pull a model and interact with it in seconds. Ollama is CLI-first and provides a built-in OpenAI-compatible API on port `11434`, making it ideal for scripting, automation, and integration with AI CLI tools.

### macOS (Ollama)

Download the latest version from [ollama.com/download](https://ollama.com/download):
1. Download the `.dmg` file for macOS
2. Open the downloaded file and install
3. Ollama runs as a background service automatically

### macOS with Homebrew (Ollama)

Install using Homebrew:
```bash
brew install ollama
```

### Linux (Ollama)

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

### Windows (Ollama)

Download `OllamaSetup.exe` from [ollama.com/download/windows](https://ollama.com/download/windows):
1. Run the installer (no Administrator privileges required)
2. Ollama is added to the system PATH
3. The server starts in the background automatically and auto-starts on boot

### Docker (Ollama)

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

### Pulling and Running Models (Ollama)

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

## MLX Installation (macOS Only)

MLX is an open-source array framework for machine learning on Apple Silicon, created by Apple Machine Learning Research. **mlx-lm** is the companion package for running, serving, and fine-tuning LLMs. MLX is designed specifically for the unified memory architecture of Apple Silicon chips (M1, M2, M3, M4, M5) and provides an OpenAI-compatible API server on port `8080`.

**Requirements:**
- macOS with Apple Silicon (M1 or later)
- macOS >= 14.0 (Sonoma)
- Python >= 3.10 (native ARM, not Rosetta x86)

### pip (MLX)

```bash
# Install mlx-lm (includes mlx as a dependency)
pip install mlx-lm
```

Or using conda:
```bash
conda install -c conda-forge mlx-lm
```

### Homebrew (MLX)

```bash
brew install mlx-lm
```

### Running Models (MLX)

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

### OpenAI-Compatible Server (MLX)

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

## EXO Installation

EXO is a distributed AI inference framework from [EXO Labs](https://exolabs.net/) that connects multiple devices into a single AI cluster, enabling you to run models that would not fit on a single device. It uses peer-to-peer discovery — devices on the same network find each other automatically with zero configuration. EXO provides an OpenAI-compatible API on port `52415`.

**Prerequisites (all platforms):**
- Python >= 3.12.0
- [uv](https://astral.sh/uv) (Python package/project manager)
- Node.js v18+ and npm (for building the dashboard)
- Rust nightly toolchain

### macOS (EXO)

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

### macOS App (EXO)

A pre-built DMG is available for macOS Tahoe 26.2 or later:

Download from: https://assets.exolabs.net/EXO-latest.dmg

### Linux (EXO)

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

### Running Models (EXO)

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
