# Cloud LLM Providers

<!-- TOC -->
* [Cloud LLM Providers](#cloud-llm-providers)
  * [Provider Comparison](#provider-comparison)
  * [Anthropic API](#anthropic-api)
    * [Account Setup (Anthropic)](#account-setup-anthropic)
    * [Getting an API Key (Anthropic)](#getting-an-api-key-anthropic)
    * [API Endpoint (Anthropic)](#api-endpoint-anthropic)
    * [Available Claude Models (Anthropic)](#available-claude-models-anthropic)
    * [Quick API Test (Anthropic)](#quick-api-test-anthropic)
    * [Using Anthropic API with AI CLI Tools](#using-anthropic-api-with-ai-cli-tools)
  * [Mammouth AI](#mammouth-ai)
    * [Account Setup (Mammouth)](#account-setup-mammouth)
    * [Getting an API Key (Mammouth)](#getting-an-api-key-mammouth)
    * [API Endpoint (Mammouth)](#api-endpoint-mammouth)
    * [Available Models (Mammouth)](#available-models-mammouth)
    * [Quick API Test (Mammouth)](#quick-api-test-mammouth)
    * [Using Mammouth AI with AI CLI Tools](#using-mammouth-ai-with-ai-cli-tools)
  * [OpenRouter](#openrouter)
    * [Account Setup (OpenRouter)](#account-setup-openrouter)
    * [Getting an API Key (OpenRouter)](#getting-an-api-key-openrouter)
    * [API Endpoint (OpenRouter)](#api-endpoint-openrouter)
    * [Available Claude Models (OpenRouter)](#available-claude-models-openrouter)
    * [Quick API Test (OpenRouter)](#quick-api-test-openrouter)
    * [Using OpenRouter with AI CLI Tools](#using-openrouter-with-ai-cli-tools)
  * [Portkey](#portkey)
    * [Account Setup (Portkey)](#account-setup-portkey)
    * [Getting an API Key (Portkey)](#getting-an-api-key-portkey)
    * [API Endpoint (Portkey)](#api-endpoint-portkey)
    * [Available Claude Models (Portkey)](#available-claude-models-portkey)
    * [Quick API Test (Portkey)](#quick-api-test-portkey)
    * [Using Portkey with AI CLI Tools](#using-portkey-with-ai-cli-tools)
  * [LiteLLM](#litellm)
    * [Installation (LiteLLM)](#installation-litellm)
    * [Proxy Setup (LiteLLM)](#proxy-setup-litellm)
    * [API Endpoint (LiteLLM)](#api-endpoint-litellm)
    * [Quick API Test (LiteLLM)](#quick-api-test-litellm)
    * [Using LiteLLM with AI CLI Tools](#using-litellm-with-ai-cli-tools)
  * [AWS Bedrock](#aws-bedrock)
    * [Account Setup (Bedrock)](#account-setup-bedrock)
    * [Authentication (Bedrock)](#authentication-bedrock)
    * [API Endpoint (Bedrock)](#api-endpoint-bedrock)
    * [Available Claude Models (Bedrock)](#available-claude-models-bedrock)
    * [Quick API Test (Bedrock)](#quick-api-test-bedrock)
    * [Using AWS Bedrock with AI CLI Tools](#using-aws-bedrock-with-ai-cli-tools)
  * [Google Gemini API](#google-gemini-api)
    * [Account Setup (Gemini API)](#account-setup-gemini-api)
    * [Getting an API Key (Gemini API)](#getting-an-api-key-gemini-api)
    * [API Endpoint (Gemini API)](#api-endpoint-gemini-api)
    * [Available Gemini Models (Gemini API)](#available-gemini-models-gemini-api)
    * [Quick API Test (Gemini API)](#quick-api-test-gemini-api)
    * [Using Google Gemini API with AI CLI Tools](#using-google-gemini-api-with-ai-cli-tools)
  * [Google Vertex AI](#google-vertex-ai)
    * [Account Setup (Vertex AI)](#account-setup-vertex-ai)
    * [Authentication (Vertex AI)](#authentication-vertex-ai)
    * [API Endpoint (Vertex AI)](#api-endpoint-vertex-ai)
    * [Available Claude Models (Vertex AI)](#available-claude-models-vertex-ai)
    * [Quick API Test (Vertex AI)](#quick-api-test-vertex-ai)
    * [Using Google Vertex AI with AI CLI Tools](#using-google-vertex-ai-with-ai-cli-tools)
  * [Microsoft Azure AI Foundry](#microsoft-azure-ai-foundry)
    * [Account Setup (Azure)](#account-setup-azure)
    * [Authentication (Azure)](#authentication-azure)
    * [API Endpoint (Azure)](#api-endpoint-azure)
    * [Available Claude Models (Azure)](#available-claude-models-azure)
    * [Quick API Test (Azure)](#quick-api-test-azure)
    * [Using Azure AI Foundry with AI CLI Tools](#using-azure-ai-foundry-with-ai-cli-tools)
<!-- TOC -->

This document covers cloud-hosted LLM API providers that can be used with the AI CLI tools in this repository. It is the cloud complement to [RUNTIMES.md](./RUNTIMES.md), which covers local LLM runtimes. For tool-specific setup instructions, see [CLI.md](./CLI.md).

## Provider Comparison

| Provider                                                  | Type           | Claude models                 | OpenAI-compatible          | Pricing model               | Best for                                |
|-----------------------------------------------------------|----------------|-------------------------------|----------------------------|-----------------------------|-----------------------------------------|
| [Anthropic API](#anthropic-api)                           | Direct         | Full catalog, latest first    | Limited (testing only)     | Pay-per-token               | Full API features, lowest latency       |
| [Mammouth AI](#mammouth-ai)                               | Aggregator     | Sonnet 4.6, Opus 4.6          | Yes                        | Flat subscription + credits | Budget-conscious, multi-model access    |
| [OpenRouter](#openrouter)                                 | Aggregator     | Full catalog + older versions | Yes                        | Pay-per-token, no markup    | Model variety, provider fallback        |
| [Portkey](#portkey)                                       | Gateway        | Full catalog                  | Yes                        | Pass-through + gateway fee  | Observability, caching, routing         |
| [LiteLLM](#litellm)                                       | Proxy          | Full catalog                  | Yes (translates)           | Free OSS / cloud plan       | Self-hosted, multi-provider abstraction |
| [AWS Bedrock](#aws-bedrock)                               | Cloud platform | Full catalog                  | Yes (via Bedrock endpoint) | AWS pay-per-token           | AWS integration, compliance (FedRAMP)   |
| [Google Gemini API](#google-gemini-api)                   | Direct         | —                             | Yes                        | Free tier + pay-per-token   | Gemini models, simplest Google setup    |
| [Google Vertex AI](#google-vertex-ai)                     | Cloud platform | Full catalog                  | Yes (via Vertex endpoint)  | GCP pay-per-token           | GCP integration, data residency         |
| [Microsoft Azure AI Foundry](#microsoft-azure-ai-foundry) | Cloud platform | Full catalog                  | No                         | Azure Marketplace pricing   | Azure / Entra ID integration            |

---

## Anthropic API

The [Anthropic API](https://platform.claude.com/) is the authoritative, direct source for all Claude models. It is the canonical provider — every other provider in this document either proxies or re-hosts models from Anthropic's API. Using it directly gives you the lowest latency, immediate access to the latest models and features (extended thinking, prompt caching, files, batches), and the simplest billing relationship.

**Key characteristics:**
- **Native Anthropic format** — uses `/v1/messages`, not `/v1/chat/completions`
- **Limited OpenAI compatibility** — a compatibility shim exists (`/v1/` messages mapped to OpenAI format) but is intended for testing only, not production
- **Pay-per-token** — billed per input/output token with usage-tier discounts at scale
- **Latest features first** — extended thinking, prompt caching, Files API, Message Batches API

### Account Setup (Anthropic)

1. Visit [console.anthropic.com](https://console.anthropic.com/) and create an account.
2. Add a payment method under **Billing**.
3. Optionally purchase credits upfront; otherwise usage is billed at the end of each month.

**Note:** A [claude.ai](https://claude.ai/) subscription (Free/Pro/Max) is a **separate product** and does **not** include API access. You need a console.anthropic.com account for programmatic access.

### Getting an API Key (Anthropic)

1. Log in to [console.anthropic.com](https://console.anthropic.com/).
2. Navigate to **Settings → API Keys**.
3. Click **Create Key**, give it a name, and copy it immediately — it is only shown once.
4. Store it securely:

```bash
export ANTHROPIC_API_KEY=sk-ant-your-key-here
```

**Security note:** Never commit your API key to version control. Add `.env` to your `.gitignore`.

### API Endpoint (Anthropic)

| Property               | Value                                                  |
|------------------------|--------------------------------------------------------|
| Base URL               | `https://api.anthropic.com/v1`                         |
| Auth header            | `x-api-key: <key>`                                     |
| Version header         | `anthropic-version: 2023-06-01` (required)             |
| Protocol               | Native Anthropic (`/messages`)                         |
| OpenAI-compatible shim | `https://api.anthropic.com/v1` (limited, testing only) |

### Available Claude Models (Anthropic)

```bash
# List all available models
curl -s https://api.anthropic.com/v1/models \
  -H "x-api-key: $ANTHROPIC_API_KEY" \
  -H "anthropic-version: 2023-06-01" | jq '.data[].id'
```

| Model ID            | Class  | Notes                                    |
|---------------------|--------|------------------------------------------|
| `claude-opus-4-6`   | Opus   | Most capable, best for complex reasoning |
| `claude-sonnet-4-6` | Sonnet | Balanced speed and capability            |
| `claude-haiku-4-5`  | Haiku  | Fastest and most compact                 |
| `claude-opus-4-5`   | Opus   | Previous Opus generation                 |
| `claude-sonnet-4-5` | Sonnet | Previous Sonnet generation               |

For the full list including older versions, see the [Anthropic models documentation](https://platform.claude.com/docs/en/models/overview).

### Quick API Test (Anthropic)

```bash
curl -s https://api.anthropic.com/v1/messages \
  -H "x-api-key: $ANTHROPIC_API_KEY" \
  -H "anthropic-version: 2023-06-01" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "claude-haiku-4-5",
    "max_tokens": 32,
    "messages": [{"role": "user", "content": "Say hello in one sentence."}]
  }' | jq '.content[0].text'
```

### Using Anthropic API with AI CLI Tools

Most tools that natively support Claude (Claude CLI, Aider, OpenCode, etc.) already default to Anthropic's API when you set `ANTHROPIC_API_KEY`. The general pattern:

```bash
export ANTHROPIC_API_KEY=sk-ant-your-key-here
```

For tools that use the OpenAI format (`OPENAI_API_BASE`), the Anthropic OpenAI-compatibility shim can be used for testing:

```bash
export OPENAI_API_BASE=https://api.anthropic.com/v1
export OPENAI_API_KEY=$ANTHROPIC_API_KEY
export OPENAI_MODEL=claude-sonnet-4-6
```

**Note:** The OpenAI-compatibility shim is not recommended for production. For tool-specific setup see [CLI.md](./CLI.md).

For more information, visit the [Anthropic API documentation](https://platform.claude.com/docs/en/api/getting-started).

---

## Mammouth AI

[Mammouth AI](https://mammouth.ai/) is a subscription-based platform that bundles access to multiple state-of-the-art LLMs — from OpenAI, Anthropic, Google, Mistral, Meta, xAI, DeepSeek, Alibaba, and more — under a single API key and a unified OpenAI-compatible endpoint. Instead of managing separate subscriptions and API keys for each provider, you get one endpoint, one key, and one monthly bill.

**Key characteristics:**
- **OpenAI-compatible API** — a drop-in replacement for `api.openai.com/v1` for any tool that supports custom base URLs
- **Multi-provider model catalog** — GPT, Claude, Gemini, Mistral, Llama, Grok, DeepSeek, Qwen, and more in one place
- **Subscription-based pricing** — flat monthly plans with included credits rather than per-token billing per provider
- **Single API key** — one key authenticates across all models on the platform

### Account Setup (Mammouth)

1. Visit [mammouth.ai](https://mammouth.ai/) and sign up for an account.
2. Choose a subscription plan:

| Plan     | Monthly Price | Included Credits |
|----------|---------------|------------------|
| Starter  | ~€4           | ~$2              |
| Standard | ~€8           | ~$4              |
| Expert   | ~€19          | ~$10             |

Credits are consumed per request based on the model used. Unused credits do not roll over. Check [mammouth.ai](https://mammouth.ai/) for current pricing, as plans and rates may change.

### Getting an API Key (Mammouth)

1. Log in to your [Mammouth AI dashboard](https://mammouth.ai/).
2. Navigate to **Settings → API Keys** (or the API section of your account).
3. Create a new API key and copy it immediately — it is only shown once.
4. Store it securely:

```bash
export MAMMOUTH_API_KEY=your-api-key-here
```

**Security note:** Never commit your API key to version control. Add `.env` to your `.gitignore`.

### API Endpoint (Mammouth)

| Property    | Value                                              |
|-------------|----------------------------------------------------|
| Base URL    | `https://api.mammouth.ai/v1`                       |
| Auth header | `Authorization: Bearer <key>`                      |
| Protocol    | OpenAI-compatible (`/chat/completions`, `/models`) |

### Available Models (Mammouth)

```bash
# List all available models
curl -s https://api.mammouth.ai/v1/models \
  -H "Authorization: Bearer $MAMMOUTH_API_KEY" | jq '.data[].id'
```

| Family     | Model ID                | Notes                                 |
|------------|-------------------------|---------------------------------------|
| OpenAI     | `gpt-4.1`               | Latest GPT-4 class model              |
| OpenAI     | `gpt-4.1-mini`          | Faster, cheaper GPT-4 variant         |
| OpenAI     | `gpt-5.2`               | GPT-5 class model                     |
| OpenAI     | `gpt-5.2-thinking`      | GPT-5 with extended reasoning         |
| Anthropic  | `claude-sonnet-4-6`     | Claude Sonnet 4.6                     |
| Anthropic  | `claude-opus-4-6`       | Claude Opus 4.6 (most capable Claude) |
| Google     | `gemini-2.5-flash`      | Fast Gemini 2.5                       |
| Google     | `gemini-2.5-pro`        | Full Gemini 2.5                       |
| Mistral    | `mistral`               | Alias for latest Mistral medium       |
| Mistral    | `mistral-large-3`       | Mistral Large 3                       |
| Mistral    | `magistral`             | Mistral reasoning model               |
| Meta       | `llama-4-maverick`      | Llama 4 Maverick                      |
| xAI        | `grok-4.1`              | Grok 4.1                              |
| DeepSeek   | `deepseek-v3`           | DeepSeek V3                           |
| DeepSeek   | `deepseek-v3-reasoning` | DeepSeek V3 with chain-of-thought     |
| Alibaba    | `qwen3-coder`           | Qwen 3 Coder (coding-specialist)      |
| Moonshot   | `kimi-k2`               | Kimi K2                               |
| Perplexity | `sonar-pro`             | Perplexity Sonar Pro (web-grounded)   |
| Codestral  | `codestral`             | Mistral code-specialist model         |

**Notes:**
- Model IDs that are plain names (e.g., `mistral`) are aliases Mammouth maintains; they always resolve to the current recommended version of that model family.
- Pricing per model varies. Check the Mammouth dashboard or the [API documentation](https://info.mammouth.ai/docs/api-quick-start/) for current rates.
- Model availability may change. Always verify with the `/v1/models` endpoint.

### Quick API Test (Mammouth)

```bash
curl -s https://api.mammouth.ai/v1/chat/completions \
  -H "Authorization: Bearer $MAMMOUTH_API_KEY" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "claude-sonnet-4-6",
    "messages": [{"role": "user", "content": "Say hello in one sentence."}],
    "max_tokens": 32
  }' | jq '.choices[0].message.content'
```

### Using Mammouth AI with AI CLI Tools

Because Mammouth AI exposes a standard OpenAI-compatible API, the general pattern for wiring it into any supporting tool is:

```bash
export OPENAI_API_BASE=https://api.mammouth.ai/v1
export OPENAI_API_KEY=$MAMMOUTH_API_KEY
export OPENAI_MODEL=claude-sonnet-4-6   # or any model ID from the table above
```

For tool-specific setup (Aider, Codex, OpenCode, Goose, etc.) see the **Using [Tool] with Mammouth AI** subsections in [CLI.md](./CLI.md). For spec-driven development tools (OpenSpec, GitHub Spec Kit) see [SPEC.md](./SPEC.md).

For more information, visit the [Mammouth AI website](https://mammouth.ai/) and the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

---

## OpenRouter

[OpenRouter](https://openrouter.ai/) is a unified API layer that provides access to 400+ models from every major provider — Anthropic, OpenAI, Google, Meta, Mistral, xAI, DeepSeek, and more — through a single OpenAI-compatible endpoint. Unlike Mammouth AI's subscription model, OpenRouter uses pay-per-token pricing with no markup on provider costs. It also supports automatic **provider fallback**: if Anthropic's API is overloaded, OpenRouter silently retries via AWS Bedrock or Google Vertex AI.

**Key characteristics:**
- **OpenAI-compatible API** — drop-in replacement for `api.openai.com/v1`
- **400+ models** — including the full Anthropic Claude catalog with older versions
- **No markup** — you pay the same per-token rates as going direct to each provider
- **Provider fallback** — automatic retry across hosting providers for resilience
- **Claude Code integration** — first-class support via a dedicated Anthropic-compatible "skin"

### Account Setup (OpenRouter)

1. Visit [openrouter.ai](https://openrouter.ai/) and sign up (GitHub, Google, or email).
2. Add credits under **Credits** — OpenRouter uses a prepaid credits system. There is no subscription; you pay only for what you use.

### Getting an API Key (OpenRouter)

1. Log in to [openrouter.ai](https://openrouter.ai/).
2. Navigate to **Keys** and click **Create Key**.
3. Optionally set a credit limit on the key.
4. Copy the key immediately and store it securely:

```bash
export OPENROUTER_API_KEY=sk-or-your-key-here
```

### API Endpoint (OpenRouter)

| Property                                     | Value                                |
|----------------------------------------------|--------------------------------------|
| Base URL (OpenAI-compatible)                 | `https://openrouter.ai/api/v1`       |
| Base URL (Anthropic-compatible, Claude Code) | `https://openrouter.ai/api`          |
| Auth header                                  | `Authorization: Bearer <key>`        |
| Protocol                                     | OpenAI-compatible + Anthropic "skin" |

OpenRouter exposes two modes for Anthropic models:
- **OpenAI-compatible** (`/v1/chat/completions`) — for tools that use `OPENAI_API_BASE`
- **Anthropic-compatible** (`/api`) — a passthrough that behaves exactly like `api.anthropic.com`, including native tool use and extended thinking; designed for Claude Code and Anthropic SDK users

### Available Claude Models (OpenRouter)

Model IDs on OpenRouter are prefixed with the provider name (`anthropic/`):

```bash
# List available Anthropic models
curl -s https://openrouter.ai/api/v1/models \
  -H "Authorization: Bearer $OPENROUTER_API_KEY" \
  | jq '[.data[] | select(.id | startswith("anthropic/")) | .id]'
```

| Model ID                      | Notes                      |
|-------------------------------|----------------------------|
| `anthropic/claude-opus-4-6`   | Claude Opus 4.6            |
| `anthropic/claude-sonnet-4-6` | Claude Sonnet 4.6          |
| `anthropic/claude-haiku-4-5`  | Claude Haiku 4.5           |
| `anthropic/claude-sonnet-4-5` | Previous Sonnet generation |
| `anthropic/claude-opus-4-5`   | Previous Opus generation   |

Older model versions are also available. See the [OpenRouter Anthropic provider page](https://openrouter.ai/provider/anthropic) for the full list.

### Quick API Test (OpenRouter)

```bash
curl -s https://openrouter.ai/api/v1/chat/completions \
  -H "Authorization: Bearer $OPENROUTER_API_KEY" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "anthropic/claude-sonnet-4-6",
    "messages": [{"role": "user", "content": "Say hello in one sentence."}],
    "max_tokens": 32
  }' | jq '.choices[0].message.content'
```

### Using OpenRouter with AI CLI Tools

**For OpenAI-compatible tools** (Aider, Codex, OpenCode, Goose, etc.):

```bash
export OPENAI_API_BASE=https://openrouter.ai/api/v1
export OPENAI_API_KEY=$OPENROUTER_API_KEY
export OPENAI_MODEL=anthropic/claude-sonnet-4-6
```

**For Claude CLI (Claude Code)** — OpenRouter provides an Anthropic-compatible endpoint that Claude Code can use natively:

```bash
export ANTHROPIC_BASE_URL=https://openrouter.ai/api
export ANTHROPIC_AUTH_TOKEN=$OPENROUTER_API_KEY
```

Or via a settings file (`~/.claude/openrouter-settings.json`):
```json
{
  "env": {
    "ANTHROPIC_BASE_URL": "https://openrouter.ai/api",
    "ANTHROPIC_AUTH_TOKEN": "sk-or-your-key-here"
  }
}
```
```bash
claude --settings ~/.claude/openrouter-settings.json
```

For more information, visit [OpenRouter documentation](https://openrouter.ai/docs) and the [Claude Code integration guide](https://openrouter.ai/docs/guides/guides/claude-code-integration).

---

## Portkey

[Portkey](https://portkey.ai/) is an AI gateway and production control plane for LLM workloads. It sits in front of any provider (Anthropic, OpenAI, Bedrock, Vertex, and 250+ more) and adds observability, prompt management, request caching, retries, rate limiting, and routing — all behind a single OpenAI-compatible endpoint. It is most useful when you need visibility into production LLM usage or want to implement fallback/load-balancing across providers.

**Key characteristics:**
- **AI gateway** — adds observability, caching, retries, and routing on top of any provider
- **OpenAI-compatible API** — existing tooling requires no code changes
- **Bring your own keys** — you supply provider API keys; Portkey routes and monitors requests
- **Prompt management** — version and A/B test prompts via the dashboard

### Account Setup (Portkey)

1. Visit [app.portkey.ai](https://app.portkey.ai/) and create an account.
2. Under **Integrations → Virtual Keys**, add your Anthropic API key (and any other provider keys you want to use). This creates a "virtual key" that Portkey uses when routing requests.

### Getting an API Key (Portkey)

1. Log in to [app.portkey.ai](https://app.portkey.ai/).
2. Navigate to **API Keys** and create a new key.
3. Store it securely:

```bash
export PORTKEY_API_KEY=your-portkey-key-here
```

You will also need the virtual key ID created when you added your Anthropic key.

### API Endpoint (Portkey)

| Property           | Value                                                                      |
|--------------------|----------------------------------------------------------------------------|
| Base URL           | `https://api.portkey.ai/v1`                                                |
| Auth header        | `x-portkey-api-key: <portkey-key>`                                         |
| Provider header    | `x-portkey-provider: anthropic`                                            |
| Virtual key header | `x-portkey-virtual-key: <virtual-key-id>` (alternative to provider header) |
| Protocol           | OpenAI-compatible (`/chat/completions`)                                    |

### Available Claude Models (Portkey)

Portkey routes to whichever models your connected provider keys support. Claude model IDs are specified using the provider-prefixed format:

| Model ID            | Notes                                            |
|---------------------|--------------------------------------------------|
| `claude-opus-4-6`   | Claude Opus 4.6 (via your Anthropic virtual key) |
| `claude-sonnet-4-6` | Claude Sonnet 4.6                                |
| `claude-haiku-4-5`  | Claude Haiku 4.5                                 |

For the full list, see the [Portkey Anthropic integration docs](https://portkey.ai/docs/integrations/llms/anthropic).

### Quick API Test (Portkey)

```bash
curl -s https://api.portkey.ai/v1/chat/completions \
  -H "x-portkey-api-key: $PORTKEY_API_KEY" \
  -H "x-portkey-provider: anthropic" \
  -H "x-portkey-virtual-key: your-virtual-key-id" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "claude-sonnet-4-6",
    "messages": [{"role": "user", "content": "Say hello in one sentence."}],
    "max_tokens": 32
  }' | jq '.choices[0].message.content'
```

### Using Portkey with AI CLI Tools

For OpenAI-compatible tools, set the base URL and pass the Portkey headers. Most tools support additional headers via environment variables or config:

```bash
export OPENAI_API_BASE=https://api.portkey.ai/v1
export OPENAI_API_KEY=$PORTKEY_API_KEY
export OPENAI_MODEL=claude-sonnet-4-6
```

Note that Portkey requires `x-portkey-provider` or `x-portkey-virtual-key` headers to identify the backend. Support for custom headers varies by tool — for tools that don't support additional headers natively, use [LiteLLM](#litellm) as an intermediary proxy instead.

For more information, visit [Portkey documentation](https://portkey.ai/docs/) and the [Anthropic integration guide](https://portkey.ai/docs/integrations/llms/anthropic).

---

## LiteLLM

[LiteLLM](https://github.com/BerriAI/litellm) is an open-source Python library and proxy server that translates calls between API formats (OpenAI, Anthropic, Bedrock, Vertex, Azure, and 100+ more) into a unified OpenAI-compatible interface. Unlike the other providers in this document, LiteLLM is **self-hosted** — you run it locally or on your own server. This makes it the right choice when you need a translation layer between tools that speak OpenAI and providers that don't (e.g., pointing Claude CLI at AWS Bedrock).

**Key characteristics:**
- **Open-source** (MIT) — free to self-host, no per-token gateway fee
- **OpenAI-compatible proxy** — any tool that supports `OPENAI_API_BASE` can use it
- **Format translation** — converts OpenAI `/chat/completions` calls to Anthropic `/messages`, Bedrock `InvokeModel`, Vertex AI, and more
- **Multi-provider** — route different models to different backends from a single config

### Installation (LiteLLM)

```bash
pip install litellm[proxy]
```

Or with uv:
```bash
uv tool install litellm[proxy]
```

### Proxy Setup (LiteLLM)

**Quick start — single model:**
```bash
export ANTHROPIC_API_KEY=sk-ant-your-key-here
litellm --model anthropic/claude-sonnet-4-6 --port 4000
```

**Config file (`~/litellm-config.yaml`) — multiple models:**
```yaml
model_list:
  - model_name: claude-sonnet-4-6
    litellm_params:
      model: anthropic/claude-sonnet-4-6
      api_key: sk-ant-your-key-here

  - model_name: claude-opus-4-6
    litellm_params:
      model: anthropic/claude-opus-4-6
      api_key: sk-ant-your-key-here

  - model_name: claude-via-bedrock
    litellm_params:
      model: bedrock/us.anthropic.claude-sonnet-4-6
      aws_region_name: us-east-1
```

```bash
litellm --config ~/litellm-config.yaml --port 4000
```

### API Endpoint (LiteLLM)

| Property    | Value                                                |
|-------------|------------------------------------------------------|
| Base URL    | `http://localhost:4000/v1` (default local port)      |
| Auth header | `Authorization: Bearer <master-key>` (if configured) |
| Protocol    | OpenAI-compatible                                    |

### Quick API Test (LiteLLM)

```bash
curl -s http://localhost:4000/v1/chat/completions \
  -H "Content-Type: application/json" \
  -d '{
    "model": "claude-sonnet-4-6",
    "messages": [{"role": "user", "content": "Say hello in one sentence."}],
    "max_tokens": 32
  }' | jq '.choices[0].message.content'
```

### Using LiteLLM with AI CLI Tools

Once the proxy is running, point any OpenAI-compatible tool at it:

```bash
export OPENAI_API_BASE=http://localhost:4000/v1
export OPENAI_API_KEY=anything   # ignored if no master key configured
export OPENAI_MODEL=claude-sonnet-4-6
```

LiteLLM is especially useful for tools that natively speak only the Anthropic format (e.g., Claude CLI) but need to be routed to a non-Anthropic backend (e.g., AWS Bedrock, Google Vertex AI). In that case, configure LiteLLM to translate in the other direction — see the Claude CLI sections in [CLI.md](./CLI.md) for the proxy pattern.

For more information, visit the [LiteLLM GitHub repository](https://github.com/BerriAI/litellm) and [LiteLLM documentation](https://docs.litellm.ai/).

---

## AWS Bedrock

[Amazon Bedrock](https://aws.amazon.com/bedrock/) is a fully managed AWS service that provides access to foundation models — including the full Claude catalog — through the AWS infrastructure. It is the choice for teams already in the AWS ecosystem, needing FedRAMP High compliance, or wanting Claude billing consolidated into their AWS bill.

**Key characteristics:**
- **AWS-native** — IAM-based auth, billed via AWS, integrates with VPC, CloudWatch, etc.
- **OpenAI-compatible endpoint** — Bedrock now provides an OpenAI-compatible API alongside its native Invoke/Converse APIs
- **FedRAMP High** — available in AWS GovCloud for regulated workloads
- **Cross-region inference profiles** — automatically routes requests to the lowest-latency region

### Account Setup (Bedrock)

1. Log in to the [AWS Management Console](https://console.aws.amazon.com/).
2. Navigate to **Amazon Bedrock → Model access**.
3. Request access to the Anthropic Claude models you want to use. Access is granted per-model and per-region; approval is usually instant.
4. Ensure your IAM user or role has the `AmazonBedrockFullAccess` policy (or a scoped equivalent with `bedrock:InvokeModel`).

### Authentication (Bedrock)

Bedrock supports two authentication methods:

**Method 1: AWS credentials (IAM) — recommended:**
```bash
# Configure AWS credentials (choose one)
aws configure                          # interactive setup
export AWS_ACCESS_KEY_ID=your-key
export AWS_SECRET_ACCESS_KEY=your-secret
export AWS_DEFAULT_REGION=us-east-1
```

**Method 2: Long-term API key (for OpenAI-compatible endpoint):**
```bash
# Generate a long-term API key in the Bedrock console
# AWS Console → Bedrock → API Keys → Create API key
export AWS_BEDROCK_API_KEY=your-bedrock-api-key
```

### API Endpoint (Bedrock)

| Property                   | Value                                           |
|----------------------------|-------------------------------------------------|
| OpenAI-compatible base URL | `https://bedrock-mantle.<region>.api.aws/v1`    |
| Auth (API key)             | `Authorization: Bearer <bedrock-api-key>`       |
| Auth (IAM)                 | AWS SigV4 signing (handled by AWS SDKs)         |
| Protocol                   | OpenAI-compatible + native Bedrock Converse API |

Replace `<region>` with your AWS region (e.g., `us-east-1`, `eu-west-1`).

### Available Claude Models (Bedrock)

Bedrock model IDs use a `<region-prefix>.anthropic.<model>` format:

```bash
# List available Claude models in your region
aws bedrock list-foundation-models \
  --by-provider Anthropic \
  --query 'modelSummaries[].modelId' --output table
```

| Model ID                                      | Notes                                                  |
|-----------------------------------------------|--------------------------------------------------------|
| `us.anthropic.claude-opus-4-6`                | Claude Opus 4.6 (US regions)                           |
| `us.anthropic.claude-sonnet-4-6`              | Claude Sonnet 4.6 (US regions)                         |
| `us.anthropic.claude-haiku-4-5-20251001-v1:0` | Claude Haiku 4.5 (US regions)                          |
| `eu.anthropic.claude-sonnet-4-6`              | Claude Sonnet 4.6 (EU regions)                         |
| `global.anthropic.claude-sonnet-4-6`          | Global endpoint (auto-routes to lowest-latency region) |

For the full list, see the [Bedrock Claude models documentation](https://docs.aws.amazon.com/bedrock/latest/userguide/claude-messages-supported-models.html).

### Quick API Test (Bedrock)

**Using the OpenAI-compatible endpoint:**
```bash
curl -s https://bedrock-mantle.us-east-1.api.aws/v1/chat/completions \
  -H "Authorization: Bearer $AWS_BEDROCK_API_KEY" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "us.anthropic.claude-sonnet-4-6",
    "messages": [{"role": "user", "content": "Say hello in one sentence."}],
    "max_tokens": 32
  }' | jq '.choices[0].message.content'
```

**Using the AWS CLI:**
```bash
aws bedrock-runtime converse \
  --model-id us.anthropic.claude-sonnet-4-6 \
  --messages '[{"role":"user","content":[{"text":"Say hello in one sentence."}]}]' \
  --region us-east-1 \
  | jq '.output.message.content[0].text'
```

### Using AWS Bedrock with AI CLI Tools

**For OpenAI-compatible tools** (using the Bedrock OpenAI-compatible endpoint):
```bash
export OPENAI_API_BASE=https://bedrock-mantle.us-east-1.api.aws/v1
export OPENAI_API_KEY=$AWS_BEDROCK_API_KEY
export OPENAI_MODEL=us.anthropic.claude-sonnet-4-6
```

**For Claude CLI (Claude Code)** — via a settings file (`~/.claude/bedrock-settings.json`):
```json
{
  "env": {
    "ANTHROPIC_BASE_URL": "https://bedrock-mantle.us-east-1.api.aws",
    "ANTHROPIC_AUTH_TOKEN": "your-bedrock-api-key",
    "ANTHROPIC_MODEL": "us.anthropic.claude-sonnet-4-6"
  }
}
```
```bash
claude --settings ~/.claude/bedrock-settings.json
```

For more information, visit the [Amazon Bedrock documentation](https://docs.aws.amazon.com/bedrock/) and [Claude on Bedrock](https://www.anthropic.com/news/claude-in-amazon-bedrock-fedramp-high).

---

## Google Gemini API

The [Google Gemini API](https://ai.google.dev/gemini-api/docs) (accessed via [Google AI Studio](https://aistudio.google.com/)) is the direct, developer-friendly way to use Google's Gemini model family. It has a generous free tier, requires only a single API key, and exposes a full OpenAI-compatible endpoint — making it the simplest Google entry point compared to the enterprise [Vertex AI](#google-vertex-ai) platform.

**Key characteristics:**
- **OpenAI-compatible endpoint** — drop-in replacement for `api.openai.com/v1` via `generativelanguage.googleapis.com/v1beta/openai/`
- **Free tier** — rate-limited free access to Gemini models; no payment method required to get started
- **Single API key** — obtained from Google AI Studio in seconds, no GCP project required
- **Gemini-native models** — Gemini 2.5 Pro/Flash, Gemini 3 series, Imagen, Veo, and more

### Account Setup (Gemini API)

1. Visit [aistudio.google.com](https://aistudio.google.com/) and sign in with a Google account.
2. No payment method or GCP project is required to use the free tier. For paid access beyond the free-tier rate limits, add a billing account in Google AI Studio settings.

### Getting an API Key (Gemini API)

1. In [Google AI Studio](https://aistudio.google.com/), click **Get API key** in the left sidebar.
2. Click **Create API key** and copy it immediately.
3. Store it securely:

```bash
export GEMINI_API_KEY=your-api-key-here
```

**Security note:** Never commit your API key to version control. Add `.env` to your `.gitignore`.

### API Endpoint (Gemini API)

| Property                   | Value                                                                             |
|----------------------------|-----------------------------------------------------------------------------------|
| Native base URL            | `https://generativelanguage.googleapis.com/v1beta`                                |
| OpenAI-compatible base URL | `https://generativelanguage.googleapis.com/v1beta/openai/`                        |
| Auth (native)              | `x-goog-api-key: <key>` header, or `?key=<key>` query parameter                   |
| Auth (OpenAI-compatible)   | `Authorization: Bearer <key>`                                                     |
| Protocol                   | Native Gemini + OpenAI-compatible (`/chat/completions`, `/embeddings`, `/models`) |

The OpenAI-compatible endpoint supports chat completions (including streaming, function calling, vision, structured outputs, and reasoning/thinking modes), embeddings, image generation (Imagen), and model listing.

### Available Gemini Models (Gemini API)

```bash
# List all available models
curl -s "https://generativelanguage.googleapis.com/v1beta/openai/models" \
  -H "Authorization: Bearer $GEMINI_API_KEY" | jq '.data[].id'
```

| Model ID                        | Series     | Notes                                                   |
|---------------------------------|------------|---------------------------------------------------------|
| `gemini-3.1-pro-preview`        | Gemini 3   | Advanced intelligence, complex reasoning, agentic tasks |
| `gemini-3-flash-preview`        | Gemini 3   | Frontier performance at lower cost                      |
| `gemini-3.1-flash-lite-preview` | Gemini 3   | High performance, most cost-efficient                   |
| `gemini-2.5-pro`                | Gemini 2.5 | Most capable 2.5 model, deep reasoning                  |
| `gemini-2.5-flash`              | Gemini 2.5 | Best price-performance, low latency                     |
| `gemini-2.5-flash-lite`         | Gemini 2.5 | Fastest and most budget-friendly                        |

**Notes:**
- Preview models may be renamed or deprecated; verify with the `/models` endpoint.
- Gemini 2.5 Pro and Flash are generally available; Gemini 3 series models are in preview as of early 2026.
- For the full model catalog including image generation (Imagen 4), video (Veo 3), and audio models, see the [Gemini models documentation](https://ai.google.dev/gemini-api/docs/models).

### Quick API Test (Gemini API)

**Via the OpenAI-compatible endpoint:**
```bash
curl -s https://generativelanguage.googleapis.com/v1beta/openai/chat/completions \
  -H "Authorization: Bearer $GEMINI_API_KEY" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "gemini-2.5-flash",
    "messages": [{"role": "user", "content": "Say hello in one sentence."}],
    "max_tokens": 32
  }' | jq '.choices[0].message.content'
```

**Via the native endpoint:**
```bash
curl -s "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=$GEMINI_API_KEY" \
  -H "Content-Type: application/json" \
  -d '{
    "contents": [{"parts": [{"text": "Say hello in one sentence."}]}]
  }' | jq '.candidates[0].content.parts[0].text'
```

### Using Google Gemini API with AI CLI Tools

Because the Gemini API exposes a full OpenAI-compatible endpoint, the general pattern for wiring it into any supporting tool is:

```bash
export OPENAI_API_BASE=https://generativelanguage.googleapis.com/v1beta/openai/
export OPENAI_API_KEY=$GEMINI_API_KEY
export OPENAI_MODEL=gemini-2.5-flash
```

**For Gemini CLI** — the native Gemini CLI tool authenticates directly via Google account (OAuth) or via `GEMINI_API_KEY` and does not need any base URL override:
```bash
export GEMINI_API_KEY=your-api-key-here
gemini
```

For other CLI tools that support OpenAI-compatible endpoints (Aider, OpenCode, Goose, Grok, etc.), the `OPENAI_API_BASE` pattern above applies. See [CLI.md](./CLI.md) for tool-specific instructions.

For more information, visit the [Gemini API documentation](https://ai.google.dev/gemini-api/docs) and [OpenAI compatibility guide](https://ai.google.dev/gemini-api/docs/openai).

---

## Google Vertex AI

[Google Vertex AI](https://cloud.google.com/vertex-ai) is Google Cloud's managed ML platform, which includes access to Claude models under a partnership with Anthropic. It is the natural choice for teams already on GCP, needing data residency controls, or wanting to use Claude alongside Google Cloud services (BigQuery, GCS, etc.).

**Key characteristics:**
- **GCP-native** — IAM auth via `gcloud`, billed via GCP, integrates with VPC, Cloud Logging, etc.
- **Native Anthropic format** — uses the Anthropic Messages API format, not OpenAI
- **Regional endpoints** — control where your data is processed; global endpoint available for auto-routing
- **FedRAMP High** — available in supported GCP regions

### Account Setup (Vertex AI)

1. Log in to the [Google Cloud Console](https://console.cloud.google.com/) and create or select a project.
2. Enable the **Vertex AI API**: `gcloud services enable aiplatform.googleapis.com`
3. Request Claude model access in the [Vertex AI Model Garden](https://console.cloud.google.com/vertex-ai/model-garden) — search for "Claude" and enable the models you need.
4. Ensure your account has the `roles/aiplatform.user` IAM role on the project.

### Authentication (Vertex AI)

Vertex AI uses Google Cloud's Application Default Credentials (ADC):

```bash
# Authenticate with your Google account
gcloud auth application-default login

# Set your project
export ANTHROPIC_VERTEX_PROJECT_ID=your-gcp-project-id
export CLOUD_ML_REGION=us-east5   # or your preferred region
```

For service accounts (CI/CD, servers):
```bash
export GOOGLE_APPLICATION_CREDENTIALS=/path/to/service-account.json
```

### API Endpoint (Vertex AI)

Vertex AI does not have a single fixed base URL — it is project- and region-scoped:

| Property          | Value                                                                                                                                      |
|-------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| Regional endpoint | `https://<region>-aiplatform.googleapis.com/v1/projects/<project>/locations/<region>/publishers/anthropic/models/<model>:streamRawPredict` |
| Global endpoint   | `https://aiplatform.googleapis.com/v1/projects/<project>/locations/global/publishers/anthropic/models/<model>:streamRawPredict`            |
| Auth              | Google OAuth2 Bearer token (from `gcloud auth print-access-token`)                                                                         |
| Protocol          | Native Anthropic Messages API format                                                                                                       |

The Anthropic Python SDK handles endpoint construction automatically when `ANTHROPIC_VERTEX_PROJECT_ID` is set.

### Available Claude Models (Vertex AI)

```bash
# List available Anthropic models in your region
gcloud ai models list --region=us-east5 --filter="displayName:claude"
```

| Model ID            | Notes                      |
|---------------------|----------------------------|
| `claude-opus-4-6`   | Claude Opus 4.6            |
| `claude-sonnet-4-6` | Claude Sonnet 4.6          |
| `claude-haiku-4-5`  | Claude Haiku 4.5           |
| `claude-opus-4-5`   | Previous Opus generation   |
| `claude-sonnet-4-5` | Previous Sonnet generation |

For the full list and regional availability, see the [Vertex AI Claude documentation](https://cloud.google.com/vertex-ai/generative-ai/docs/partner-models/use-claude).

### Quick API Test (Vertex AI)

```bash
ACCESS_TOKEN=$(gcloud auth print-access-token)
PROJECT_ID=$ANTHROPIC_VERTEX_PROJECT_ID
REGION=us-east5

curl -s \
  "https://${REGION}-aiplatform.googleapis.com/v1/projects/${PROJECT_ID}/locations/${REGION}/publishers/anthropic/models/claude-sonnet-4-6:rawPredict" \
  -H "Authorization: Bearer $ACCESS_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "anthropic_version": "vertex-2023-10-16",
    "max_tokens": 32,
    "messages": [{"role": "user", "content": "Say hello in one sentence."}]
  }' | jq '.content[0].text'
```

### Using Google Vertex AI with AI CLI Tools

**For Claude CLI (Claude Code)** — via a settings file (`~/.claude/vertex-settings.json`):
```json
{
  "env": {
    "ANTHROPIC_VERTEX_PROJECT_ID": "your-gcp-project-id",
    "CLOUD_ML_REGION": "us-east5"
  }
}
```
```bash
claude --settings ~/.claude/vertex-settings.json
```

**For tools that require OpenAI-compatible endpoints**, use [LiteLLM](#litellm) as a translation proxy:
```bash
litellm --model vertex_ai/claude-sonnet-4-6 --port 4000
# Then point tools at http://localhost:4000/v1
```

For more information, visit the [Vertex AI documentation](https://cloud.google.com/vertex-ai/docs) and [Claude on Vertex AI](https://cloud.google.com/vertex-ai/generative-ai/docs/partner-models/use-claude).

---

## Microsoft Azure AI Foundry

[Microsoft Azure AI Foundry](https://azure.microsoft.com/en-us/products/ai-foundry) (formerly Azure AI Studio) is Microsoft's cloud platform for deploying and managing AI models, including Claude via an Anthropic partnership. It is the choice for teams in the Microsoft ecosystem, needing Azure RBAC or Entra ID authentication, or wanting Claude billing on an existing Azure subscription.

**Key characteristics:**
- **Azure-native** — RBAC, Entra ID, private networking, Azure Monitor, Azure Marketplace billing
- **Native Anthropic format** — uses the Anthropic Messages API format, not OpenAI
- **Azure Marketplace pricing** — equivalent to Anthropic's standard API rates, charged to your Azure subscription
- **No Batch API or Models API** — some Anthropic API features are not available via Foundry

### Account Setup (Azure)

1. Log in to the [Azure Portal](https://portal.azure.com/) and create or select a subscription.
2. Create an **Azure AI Foundry resource** (search "Azure AI Foundry" in the Marketplace).
3. Once the resource is created, navigate to it and enable the Claude models you need under **Model catalog**.
4. Note the **resource name** — you will need it for the endpoint URL.

### Authentication (Azure)

**Method 1: API key** (simpler):
```bash
# Azure Portal → your Foundry resource → Keys and Endpoint
export ANTHROPIC_FOUNDRY_API_KEY=your-azure-api-key
export ANTHROPIC_FOUNDRY_RESOURCE=your-resource-name
```

**Method 2: Azure Entra ID** (enterprise / passwordless):
```bash
az login
ACCESS_TOKEN=$(az account get-access-token --resource https://cognitiveservices.azure.com/ --query accessToken -o tsv)
```

### API Endpoint (Azure)

| Property        | Value                                                        |
|-----------------|--------------------------------------------------------------|
| Base URL        | `https://<resource-name>.services.ai.azure.com/anthropic/v1` |
| Auth (API key)  | `x-api-key: <azure-api-key>` or `api-key: <azure-api-key>`   |
| Auth (Entra ID) | `Authorization: Bearer <access-token>`                       |
| Protocol        | Native Anthropic Messages API format                         |

Replace `<resource-name>` with your Azure AI Foundry resource name.

### Available Claude Models (Azure)

| Model ID            | Notes                      |
|---------------------|----------------------------|
| `claude-opus-4-6`   | Claude Opus 4.6            |
| `claude-sonnet-4-6` | Claude Sonnet 4.6          |
| `claude-haiku-4-5`  | Claude Haiku 4.5           |
| `claude-opus-4-5`   | Previous Opus generation   |
| `claude-sonnet-4-5` | Previous Sonnet generation |

For the current list, see the [Azure AI Foundry Claude documentation](https://platform.claude.com/docs/en/build-with-claude/claude-in-microsoft-foundry).

### Quick API Test (Azure)

```bash
RESOURCE=your-resource-name

curl -s "https://${RESOURCE}.services.ai.azure.com/anthropic/v1/messages" \
  -H "x-api-key: $ANTHROPIC_FOUNDRY_API_KEY" \
  -H "anthropic-version: 2023-06-01" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "claude-sonnet-4-6",
    "max_tokens": 32,
    "messages": [{"role": "user", "content": "Say hello in one sentence."}]
  }' | jq '.content[0].text'
```

### Using Azure AI Foundry with AI CLI Tools

**For Claude CLI (Claude Code)** — via a settings file (`~/.claude/azure-settings.json`):
```json
{
  "env": {
    "ANTHROPIC_BASE_URL": "https://your-resource-name.services.ai.azure.com/anthropic",
    "ANTHROPIC_AUTH_TOKEN": "your-azure-api-key",
    "ANTHROPIC_MODEL": "claude-sonnet-4-6"
  }
}
```
```bash
claude --settings ~/.claude/azure-settings.json
```

**For tools that require OpenAI-compatible endpoints**, use [LiteLLM](#litellm) as a translation proxy:
```bash
export ANTHROPIC_FOUNDRY_API_KEY=your-azure-api-key
litellm --model azure_ai/claude-sonnet-4-6 \
  --api_base "https://your-resource-name.services.ai.azure.com/anthropic/v1" \
  --port 4000
# Then point tools at http://localhost:4000/v1
```

For more information, visit the [Azure AI Foundry documentation](https://learn.microsoft.com/en-us/azure/ai-studio/) and [Claude in Microsoft Foundry](https://platform.claude.com/docs/en/build-with-claude/claude-in-microsoft-foundry).
