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
  * [Lemonade Installation](#lemonade-installation)
    * [Windows (Lemonade)](#windows-lemonade)
    * [Linux (Lemonade)](#linux-lemonade)
    * [macOS (Lemonade)](#macos-lemonade)
    * [Pulling and Running Models (Lemonade)](#pulling-and-running-models-lemonade)
    * [OpenAI-Compatible API (Lemonade)](#openai-compatible-api-lemonade)
  * [Unsloth Studio Installation](#unsloth-studio-installation)
    * [Unsloth Desktop (Native App)](#unsloth-desktop-native-app)
    * [Unsloth Studio (Web UI / Server)](#unsloth-studio-web-ui--server)
    * [Unsloth Core (Python Library, for Fine-Tuning)](#unsloth-core-python-library-for-fine-tuning)
    * [Running Models / OpenAI-Compatible API (Unsloth)](#running-models--openai-compatible-api-unsloth)
    * [Sharing Already-Downloaded LM Studio Models with Unsloth Studio](#sharing-already-downloaded-lm-studio-models-with-unsloth-studio)
    * [Fine-Tuning (Training) — the One Thing This Tool Does That the Others Can't](#fine-tuning-training--the-one-thing-this-tool-does-that-the-others-cant)
  * [llama.cpp Installation](#llamacpp-installation)
    * [macOS (llama.cpp)](#macos-llamacpp)
    * [Linux (llama.cpp)](#linux-llamacpp)
    * [Windows (llama.cpp)](#windows-llamacpp)
    * [Running a Model with llama-server](#running-a-model-with-llama-server)
  * [vLLM Installation](#vllm-installation)
    * [pip (vLLM)](#pip-vllm)
    * [Docker (vLLM)](#docker-vllm)
    * [Running Models / OpenAI-Compatible API (vLLM)](#running-models--openai-compatible-api-vllm)
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

The following models were originally recommended by Claude (Opus 4.6) to maximize a **Ryzen AI MAX 395+ / Radeon 8060S** — the box is actually **128 GB**, not the 96 GB this section (and its sources) originally assumed; the table below is unaffected (everything in it fits comfortably with room to spare), but the framing numbers and "tight fit" callouts are now conservative rather than accurate. See [STRIX-HALO.md — Recommended Models for a 128GB Strix Halo](./STRIX-HALO.md#recommended-models-for-a-128gb-strix-halo) for picks that take advantage of the extra ~32 GB (including a 235B-class MoE model that only fits once you're past 96GB).

**For the ROCm/Linux setup itself** (BIOS, GRUB/GTT sizing, ROCm install, building llama.cpp for gfx1151, and the KV-cache math behind the "leaving headroom" guidance below), see [STRIX-HALO.md](./STRIX-HALO.md).

**New Models**

| Model                                                                                                          | Parameters            | Quantization | Size   | Hugging Face                                                                                                                            | Why This Model                                                                                                                                                                                                                                                 |
|----------------------------------------------------------------------------------------------------------------|-----------------------|--------------|--------|-----------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Qwen3-Coder-Next](https://huggingface.co/unsloth/Qwen3-Coder-Next-GGUF)                                       | 80B (3B active MoE)   | UD-Q8_K_XL   | ~85 GB | [unsloth/Qwen3-Coder-Next-GGUF](https://huggingface.co/unsloth/Qwen3-Coder-Next-GGUF)                                                   | Purpose-built for coding agents and IDE integration (Claude Code, Qwen Code, Cline). 256K context, excels at long-horizon reasoning, tool use, and failure recovery. Only 3B active params means fast inference despite 80B total.                             |
| [DeepSeek R1 Distill Llama 70B](https://huggingface.co/lmstudio-community/DeepSeek-R1-Distill-Llama-70B-GGUF)  | 70B                   | Q8_0         | ~75 GB | [lmstudio-community/DeepSeek-R1-Distill-Llama-70B-GGUF](https://huggingface.co/lmstudio-community/DeepSeek-R1-Distill-Llama-70B-GGUF)   | Strongest open reasoning model in the 70B class. Distilled from DeepSeek-R1, competitive with OpenAI o1 on math, code, and reasoning benchmarks. At Q8_0, near-lossless quality.                                                                               |
| [Devstral 2 123B Instruct 2512](https://huggingface.co/bartowski/mistralai_Devstral-2-123B-Instruct-2512-GGUF) | 123B                  | Q4_K_M       | ~75 GB | [bartowski/mistralai_Devstral-2-123B-Instruct-2512-GGUF](https://huggingface.co/bartowski/mistralai_Devstral-2-123B-Instruct-2512-GGUF) | Latest Mistral coding model (Dec 2025), successor to Mistral Large 2411. 123B dense params purpose-tuned for code generation. Newer and more capable than the Mistral Large Instruct 2411 already installed.                                                   |
| [Qwen 2.5 72B Instruct](https://huggingface.co/lmstudio-community/Qwen2.5-72B-Instruct-GGUF)                   | 72B                   | Q8_0         | ~77 GB | [lmstudio-community/Qwen2.5-72B-Instruct-GGUF](https://huggingface.co/lmstudio-community/Qwen2.5-72B-Instruct-GGUF)                     | Top general-purpose 70B-class model for research and reasoning tasks. Excels at math word problems and structured code-then-explain workflows. Complements the coding-focused Qwen 2.5 Coder models already installed.                                         |
| [Qwen3 235B A22B Instruct 2507](https://huggingface.co/unsloth/Qwen3-235B-A22B-Instruct-2507-GGUF)             | 235B (22B active MoE) | UD-Q2_K_XL   | ~88 GB | [unsloth/Qwen3-235B-A22B-Instruct-2507-GGUF](https://huggingface.co/unsloth/Qwen3-235B-A22B-Instruct-2507-GGUF)                         | Qwen3 flagship with 235B total params and 22B active per token. State-of-the-art instruction following, reasoning, and tool use. Tight fit at ~88 GB -- usable with shorter context lengths. Unsloth Dynamic 2.0 quantization preserves quality even at 2-bit. |

**Quantization Upgrades to Installed Models**

These models are already installed at lower quantization levels. With 128 GB (originally documented as 96 GB — see the correction above) VRAM, they can be upgraded to significantly higher quality:

| Model                                                                                                     | Current Quant | Recommended Quant | New Size | Hugging Face                                                                                                                      | Why Upgrade                                                                                                                                                                   |
|-----------------------------------------------------------------------------------------------------------|---------------|-------------------|----------|-----------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Llama 3.3 70B Instruct](https://huggingface.co/lmstudio-community/Llama-3.3-70B-Instruct-GGUF)           | Q3_K_L        | **Q8_0**          | ~75 GB   | [lmstudio-community/Llama-3.3-70B-Instruct-GGUF](https://huggingface.co/lmstudio-community/Llama-3.3-70B-Instruct-GGUF)           | Biggest quality jump: 3-bit to 8-bit on a 70B dense model. Comparable to Llama 405B performance at a fraction of the size. Clean formatting and strong instruction following. |
| [Mistral Large Instruct 2411](https://huggingface.co/lmstudio-community/Mistral-Large-Instruct-2411-GGUF) | Q4_K_M        | **Q5_K_M**        | ~87 GB   | [lmstudio-community/Mistral-Large-Instruct-2411-GGUF](https://huggingface.co/lmstudio-community/Mistral-Large-Instruct-2411-GGUF) | 123B dense model benefits noticeably from the 4-bit to 5-bit bump, especially in nuanced reasoning and instruction adherence. At 128GB there's room to go to Q6_K/Q8_0 instead if you want to push further.              |
| [Qwen 2.5 Coder 32B](https://huggingface.co/lmstudio-community/Qwen2.5-Coder-32B-GGUF)                    | Q4_K_M        | **Q8_0**          | ~34 GB   | [lmstudio-community/Qwen2.5-Coder-32B-GGUF](https://huggingface.co/lmstudio-community/Qwen2.5-Coder-32B-GGUF)                     | At 34 GB, easily fits. Q8_0 is near-lossless for a 32B model, maximizing code generation accuracy with no practical trade-off.                                       |
| [QwQ 32B](https://huggingface.co/lmstudio-community/QwQ-32B-GGUF)                                         | Q4_K_M        | **Q8_0**          | ~34 GB   | [lmstudio-community/QwQ-32B-GGUF](https://huggingface.co/lmstudio-community/QwQ-32B-GGUF)                                         | QwQ is a reasoning-specialized model. Q8_0 preserves its chain-of-thought fidelity at a size that leaves ample room for long context windows.                                 |

**Hardware Context:**
- **Target hardware:** AMD Ryzen AI MAX 395+ with Radeon 8060S ("Strix Halo"), **128 GB** unified GPU memory (BosGameM5 — corrected from an earlier 96 GB assumption in this section), ~215 GB/s memory bandwidth (256-bit LPDDR5X-8000). See [STRIX-HALO.md](./STRIX-HALO.md) for the full ROCm setup.
- **Expected throughput:** ~10-15 tok/s on 70B Q8 dense models; faster on MoE models since only active parameters are read per token.
- **Memory budget:** the table above only spends up to ~88 GB, leaving a large amount of headroom unused on a 128GB box — see [STRIX-HALO.md's GTT sizing profiles](./STRIX-HALO.md#sizing-the-gtt-aperture-for-128gb) (112-124GB usable) and its [128GB model picks](./STRIX-HALO.md#recommended-models-for-a-128gb-strix-halo) for options that actually use the extra memory.

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

For hardware-specific tuning on a memory-constrained Mac (GPU memory ceiling, model picks, exposing the server on your network) see [MAC-MINI-M4.md](./MAC-MINI-M4.md) — written for a 16GB Mac Mini M4, but the `iogpu.wired_limit_mb` guidance there applies to any Apple Silicon Mac.

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

## Lemonade Installation

[Lemonade](https://github.com/lemonade-sdk/lemonade) is an open-source (Apache 2.0) local AI server built by AMD, aimed at giving "the same capabilities as cloud APIs, except 100% free and private." It is particularly optimized for AMD hardware — Ryzen AI NPUs (XDNA2, e.g. gfx1100/gfx1151), Radeon GPUs (RDNA3/RDNA4), and Strix Halo iGPUs — but also runs on NVIDIA CUDA GPUs (Turing through Blackwell), Apple Silicon, and plain x86_64/ARM64 CPUs via Vulkan. Beyond chat, it supports speech recognition, text-to-speech, and image generation through the same server.

### Windows (Lemonade)

Download and run the MSI installer from the [latest release](https://github.com/lemonade-sdk/lemonade/releases/latest/download/lemonade.msi).

### Linux (Lemonade)

Install via your distribution's package manager:

```bash
# Debian/Ubuntu (24.04+)
sudo apt install lemonade-server

# Fedora (43+)
sudo dnf install lemonade-server

# Arch Linux
sudo pacman -S lemonade-server

# Snap (any distro)
sudo snap install lemonade-server
```

### macOS (Lemonade)

Download the `.pkg` installer from the [releases page](https://github.com/lemonade-sdk/lemonade/releases/latest) (optimized for Apple Silicon).

### Pulling and Running Models (Lemonade)

```bash
# Browse available models
lemonade list

# Download a model
lemonade pull Gemma-4-E2B-it-GGUF

# Run a model (starts the server and loads the model)
lemonade run Gemma-4-E2B-it-GGUF

# Quick integration setup for an AI CLI tool (Claude Code, Codex, etc.)
lemonade launch claude

# Show available inference backends (CPU / GPU / NPU) for this machine
lemonade backends
```

Models are pulled from Hugging Face or ModelScope in GGUF, FLM, or ONNX format; expect roughly 10 GB of disk per model and 8 GB+ RAM as a baseline.

### OpenAI-Compatible API (Lemonade)

Lemonade serves an OpenAI-compatible API at `http://localhost:13305/api/v1`:

```bash
curl http://localhost:13305/api/v1/chat/completions \
  -H "Content-Type: application/json" \
  -d '{
    "model": "Gemma-4-E2B-it-GGUF",
    "messages": [{"role": "user", "content": "Hello!"}]
  }'
```

To use with AI CLI tools, configure them to point to Lemonade's endpoint:
```bash
export OPENAI_API_BASE=http://localhost:13305/api/v1
export OPENAI_API_KEY=lemonade   # required by some tools but ignored by Lemonade
```

For more information, visit the [Lemonade GitHub repository](https://github.com/lemonade-sdk/lemonade).

## Unsloth Studio Installation

[Unsloth](https://github.com/unslothai/unsloth) is best known as a fast, memory-efficient LLM **fine-tuning** framework, but the same project also ships a local inference stack under the "Unsloth Studio" / "Unsloth Desktop" names — not to be confused with LM Studio above, a separate, unrelated product with a similar name. Unsloth Core (the fine-tuning library) is Apache 2.0; Unsloth Studio's UI components are AGPL-3.0.

### Unsloth Desktop (Native App)

Pre-built installers are available from [GitHub Releases](https://github.com/unslothai/unsloth/releases):
- **Windows:** `Unsloth-Desktop-Windows.exe`
- **macOS:** `Unsloth-Desktop-MacOS.dmg`
- **Linux (deb):** `Unsloth-Desktop-Ubuntu.deb`
- **Linux (AppImage):** `Unsloth-Desktop-Linux.AppImage`

### Unsloth Studio (Web UI / Server)

**macOS, Linux, WSL:**
```bash
curl -fsSL https://unsloth.ai/install.sh | sh
unsloth studio
```

**Windows (PowerShell):**
```powershell
irm https://unsloth.ai/install.ps1 | iex
unsloth studio
```

Serve over HTTPS instead of plain HTTP:
```bash
unsloth studio --secure
```

**Docker:**
```bash
docker run -d --gpus all --ipc=host \
  -p 8000:8000 -p 8888:8888 \
  -e UNSLOTH_STUDIO_PASSWORD="mypassword" \
  unsloth/unsloth
```
This exposes the Studio web UI at `http://localhost:8000` (user: `unsloth`) and a JupyterLab instance at `http://localhost:8888`.

### Unsloth Core (Python Library, for Fine-Tuning)

**Linux/WSL:**
```bash
curl -LsSf https://astral.sh/uv/install.sh | sh
uv venv unsloth_env --python 3.13
source unsloth_env/bin/activate
uv pip install unsloth --torch-backend=auto
```

**Windows (PowerShell):**
```powershell
winget install -e --id Python.Python.3.13
uv venv unsloth_env --python 3.13
.\unsloth_env\Scripts\activate
uv pip install unsloth --torch-backend=auto
```

Requires Python 3.12+ (3.13 recommended). Supports NVIDIA CUDA, AMD ROCm, Intel GPUs, and a CPU/Vulkan fallback; this is the piece used for actually fine-tuning models, as opposed to just serving them.

### Running Models / OpenAI-Compatible API (Unsloth)

`unsloth run` loads a model and starts the local server (default port `8000`, or `8888` when launched via Unsloth Studio's own chat UI); pass `-p` for a custom port and `-H 0.0.0.0` to expose it on your local network:
```bash
unsloth run --model unsloth/gemma-4-26B-A4B-it-GGUF:UD-Q4_K_XL -H 0.0.0.0 -p 8888
```

`unsloth start` is the quick-integration launcher for AI CLI tools, analogous to Ollama's `launch` and Lemonade's `launch` above:
```bash
unsloth start claude --model unsloth/Qwen3.8-27B-GGUF:UD-Q4_K_XL
unsloth start codex
unsloth start hermes
```

Both `/v1/chat/completions` (OpenAI Chat Completions) and `/v1/messages` (Anthropic Messages) endpoints are served, so it can be used directly with either family of AI CLI tools. Every request needs an API key generated from the Studio UI (avatar → **Settings → API** → **Create**, keys are prefixed `sk-unsloth-` and shown once):
```bash
curl http://localhost:8888/v1/chat/completions \
  -H "Authorization: Bearer sk-unsloth-xxxxxxxxxxxx" \
  -H "Content-Type: application/json" \
  -d '{"model": "gemma-4-26B-A4B-it-GGUF", "messages": [{"role": "user", "content": "Hello!"}]}'
```

To use with AI CLI tools that speak OpenAI's API:
```bash
export OPENAI_API_BASE=http://localhost:8888/v1
export OPENAI_API_KEY=sk-unsloth-xxxxxxxxxxxx
```

For more information, visit the [Unsloth GitHub repository](https://github.com/unslothai/unsloth) and [Unsloth documentation](https://unsloth.ai/docs).

### Sharing Already-Downloaded LM Studio Models with Unsloth Studio

Unsloth Studio has no built-in "point me at another app's model folder" feature yet — [issue #8568](https://github.com/unslothai/unsloth/issues/8568) is an open feature request (filed August 2026, no PR as of this writing) asking for exactly that, generalized to text/image/audio/video model folders shared across LM Studio, Ollama, ComfyUI, and Forge. Until it lands, a directory symlink works today, because GGUF is just a file format — both apps already use the same on-disk layout:

- **Unsloth Studio** auto-detects local models placed under `~/.unsloth/studio/models` (it also scans your Hugging Face hub cache, but that's a separate, unrelated path — not where LM Studio keeps anything).
- **LM Studio** stores models at `~/.lmstudio/models/<publisher>/<repo>/<file>.gguf` (see [LM Studio Installation](#lm-studio-installation) above).

Since both use `<publisher>/<repo>/<file>.gguf`, symlinking each publisher folder across bridges them — and new models LM Studio downloads under an already-linked publisher show up in Unsloth Studio automatically, with no extra step:

**macOS/Linux:**
```bash
mkdir -p ~/.unsloth/studio/models
for pub_dir in ~/.lmstudio/models/*/; do
  ln -s "$pub_dir" ~/.unsloth/studio/models/"$(basename "$pub_dir")"
done
```

**Windows (PowerShell):** plain symlinks are often blocked without Developer Mode or admin rights; directory junctions aren't:
```powershell
foreach ($pub in Get-ChildItem "$env:USERPROFILE\.lmstudio\models" -Directory) {
    cmd /c mklink /J "$env:USERPROFILE\.unsloth\studio\models\$($pub.Name)" $pub.FullName
}
```

A new publisher LM Studio hasn't downloaded from before needs the loop re-run once. Unsloth Studio's own docs note that GGUF models are **inference-only** — they won't show up as fine-tunable in the Fine-tuned tab, only in the chat/inference model picker, which is the same way LM Studio itself treats them.

### Fine-Tuning (Training) — the One Thing This Tool Does That the Others Can't

Every other runtime in this document (LM Studio, Ollama, MLX, EXO, Lemonade, llama.cpp, vLLM) is **inference-only** — they run models, they don't train them. Unsloth is the exception, and it's the reason to actually install it rather than treat it as a third redundant way to serve GGUFs you already run through LM Studio or llama.cpp.

**AMD ROCm (including Strix Halo) is explicitly, officially supported for training**, not just inference: Unsloth's own AMD documentation lists "Strix Halo powered Ryzen AI Max systems" by name alongside Radeon RX 7000/9000 series and Instinct MI300/MI350 datacenter GPUs, across Windows, WSL, and Linux. The collaboration with AMD claims **up to 2x faster training and 70% less VRAM usage** versus a naive baseline, with a published benchmark of 1.39x faster / 1.33x less memory on a Llama-3.1-8B LoRA run — no accuracy loss reported. QLoRA, LoRA, and reinforcement-learning workflows are all supported; the installer (same `curl -fsSL https://unsloth.ai/install.sh | sh` from [Unsloth Studio (Web UI / Server)](#unsloth-studio-web-ui--server) above) handles ROCm and PyTorch setup automatically.

On a 128GB Strix Halo box specifically, that memory budget makes QLoRA fine-tuning of ~30B-class models realistic — see [STRIX-HALO.md — Fine-Tuning with Unsloth](./STRIX-HALO.md#fine-tuning-with-unsloth) for what that looks like against the models this repo already recommends for that hardware, and how a fine-tuned model exports straight back into the existing GGUF workflow.

**Apple Silicon (MLX) training is not yet available** — as of this writing, Unsloth's own docs state plainly: *"MacOS and CPU work for Chat GGUF inference. MLX training coming soon."* Today, a Mac can only serve/chat with models through Unsloth Studio, not train them; that's an announced-but-unshipped feature, not something to plan around yet. (An unofficial third-party project, `mlx-tune` — formerly `unsloth-mlx` — offers Unsloth-style training on MLX today, but it isn't the official Unsloth project and carries the usual caveats of a community tool filling a gap ahead of upstream support.)

## llama.cpp Installation

[llama.cpp](https://github.com/ggml-org/llama.cpp) is the C/C++ LLM inference engine that most of the GGUF-based tooling in this document is actually built on — LM Studio's local server, Ollama's engine, and the `llama.cpp` backend several harnesses in [CLI.md](./CLI.md) call out by name (Hermes Agent's Mac guide, Pi Agent's built-in `/login llama.cpp` support, Oh My Pi's and Hermes Agent's lists of supported local servers) are all running it, or something derived from it, under the hood. Running it directly gives you the least abstraction and the widest hardware support (CPU, CUDA, Metal, Vulkan, ROCm, SYCL) of anything in this document.

**Why build it yourself instead of just using LM Studio or Ollama?** Both of those already bundle a llama.cpp build, and for most people that's the right call — less setup, a GUI or simple CLI, automatic updates. Reach for building it directly when you need something they don't expose: hardware-specific build flags tuned for a particular GPU target (see the ROCm/gfx1151 case below), the very latest upstream model support before it lands in a packaged release, the `rpc-server` distributed-inference backend (see [STRIX-HALO.md — Option 3](./STRIX-HALO.md#option-3-model-sharding-across-both-machines-llamacpp-rpc)), or just wanting to know exactly what's running your models.

### macOS (llama.cpp)

```bash
brew install llama.cpp
```
This installs `llama-cli` (interactive REPL / one-shot generation) and `llama-server` (the OpenAI-compatible HTTP server) as separate binaries.

### Linux (llama.cpp)

Pre-built binaries aren't packaged as widely as on macOS; build from source (CMake, with your accelerator's flags):
```bash
git clone https://github.com/ggml-org/llama.cpp
cd llama.cpp

# CPU-only
cmake -B build
cmake --build build --config Release -j

# NVIDIA CUDA
cmake -B build -DGGML_CUDA=ON
cmake --build build --config Release -j

# AMD ROCm
cmake -B build -DGGML_HIP=ON
cmake --build build --config Release -j
```
Binaries land under `build/bin/` (`llama-cli`, `llama-server`, and others).

**AMD ROCm note:** the bare `-DGGML_HIP=ON` above builds for cmake's auto-detected GPU target, which is not reliable on every AMD GPU — on this repo's own Strix Halo (gfx1151) box it needs an explicit `-DAMDGPU_TARGETS=gfx1151` plus several other stability/performance flags, a pinned ROCm version, and a `HSA_OVERRIDE_GFX_VERSION` override to be recognized correctly at all. It's also not automatically the fastest choice — llama.cpp's Vulkan backend beats ROCm on this hardware for some workloads. See [STRIX-HALO.md — Building llama.cpp for gfx1151](./STRIX-HALO.md#building-llamacpp-for-gfx1151) and [ROCm vs Vulkan: Which Backend?](./STRIX-HALO.md#rocm-vs-vulkan-which-backend) for the full picture if you're on that hardware specifically.

### Windows (llama.cpp)

Download a pre-built release `.zip` from the [releases page](https://github.com/ggml-org/llama.cpp/releases) (CPU, CUDA, and Vulkan builds are all provided), extract it, and run `llama-server.exe` / `llama-cli.exe` directly — no installer needed.

### Running a Model with llama-server

`llama-server` can pull a model straight from Hugging Face by repo/quant, or load a local `.gguf` file:
```bash
# Pull and serve directly from Hugging Face (downloads once, caches locally)
llama-server -hf ggml-org/gpt-oss-20b-GGUF --ctx-size 0 --jinja -ngl 99 -fa

# Serve a local GGUF file, bound to all interfaces
llama-server -m ~/models/Qwen3.5-9B-Q4_K_M.gguf \
  --host 0.0.0.0 --port 8080 -ngl 99 -c 131072 -fa on
```
`-ngl 99` offloads all layers to GPU (drop it, or lower it, for CPU-only or partial offload); `--jinja` enables the model's own chat template (needed for reliable tool-calling); `-fa` / `-fa on` enables flash attention.

The server exposes an OpenAI-compatible API at `http://localhost:8080/v1`, plus a built-in web chat UI at `http://localhost:8080/`:
```bash
curl http://localhost:8080/v1/chat/completions \
  -H "Content-Type: application/json" \
  -d '{
    "model": "gpt-oss-20b",
    "messages": [{"role": "user", "content": "Hello!"}]
  }'
```

To use with AI CLI tools, configure them to point to llama-server's endpoint:
```bash
export OPENAI_API_BASE=http://localhost:8080/v1
export OPENAI_API_KEY=llama-cpp   # required by some tools but ignored by llama.cpp
```

For more information, visit the [llama.cpp GitHub repository](https://github.com/ggml-org/llama.cpp).

## vLLM Installation

[vLLM](https://github.com/vllm-project/vllm) is a high-throughput, production-grade inference and serving engine for NVIDIA (and increasingly AMD/TPU) GPUs, built around PagedAttention for efficient KV-cache memory use under concurrent load. It shows up by name across this document's own recommendations (the [Gemini-suggested 96GB-VRAM setup](#recommended-models-from-gemini-as-of-20206-02-10) above calls it out for "maximum speed, especially in a Docker environment") and in [CLI.md](./CLI.md) (Hermes Agent's troubleshooting notes tool-calling requires `--enable-auto-tool-choice --tool-call-parser hermes`; Oh My Pi lists it among its supported local backends) — it's the natural next step up from Ollama/LM Studio once you're serving concurrent requests rather than a single interactive session.

### pip (vLLM)

```bash
pip install vllm
```
Requires an NVIDIA GPU with CUDA (or AMD ROCm / Intel / TPU builds — see the [installation guide](https://docs.vllm.ai/en/stable/getting_started/installation/) for non-NVIDIA hardware); CPU-only is possible but slow.

### Docker (vLLM)

The official image bundles every GPU dependency, so it's the path of least friction:
```bash
docker pull vllm/vllm-openai:latest

docker run -d --gpus all --ipc=host \
  -v ~/.cache/huggingface:/root/.cache/huggingface \
  -p 8000:8000 \
  vllm/vllm-openai:latest \
  --model meta-llama/Llama-3.1-8B-Instruct \
  --host 0.0.0.0 --port 8000
```
`--ipc=host` (or `--shm-size` as an alternative) is required — vLLM uses PyTorch shared memory for tensor-parallel inference. Swap the `--model` argument for any Hugging Face repo id; it downloads (and caches) on first run.

### Running Models / OpenAI-Compatible API (vLLM)

However it's started, vLLM serves an OpenAI-compatible API on port `8000` by default:
```bash
curl http://localhost:8000/v1/chat/completions \
  -H "Content-Type: application/json" \
  -d '{
    "model": "meta-llama/Llama-3.1-8B-Instruct",
    "messages": [{"role": "user", "content": "Hello!"}]
  }'
```

To use with AI CLI tools, configure them to point to vLLM's endpoint:
```bash
export OPENAI_API_BASE=http://localhost:8000/v1
export OPENAI_API_KEY=vllm   # required by some tools but ignored by vLLM
```

For tool-calling / agentic use (Hermes Agent and similar harnesses), enable auto tool-choice with a parser matching your model family:
```bash
--enable-auto-tool-choice --tool-call-parser hermes
```

For more information, visit the [vLLM GitHub repository](https://github.com/vllm-project/vllm) and [documentation](https://docs.vllm.ai/).
