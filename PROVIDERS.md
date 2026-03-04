# Cloud LLM Providers

<!-- TOC -->
* [Cloud LLM Providers](#cloud-llm-providers)
  * [Mammouth AI](#mammouth-ai)
    * [Account Setup](#account-setup)
    * [Getting an API Key](#getting-an-api-key)
    * [API Endpoint](#api-endpoint)
    * [Available Models](#available-models)
    * [Quick API Test](#quick-api-test)
    * [Using Mammouth AI with AI CLI Tools](#using-mammouth-ai-with-ai-cli-tools)
<!-- TOC -->

## Mammouth AI

[Mammouth AI](https://mammouth.ai/) is a subscription-based platform that bundles access to multiple state-of-the-art LLMs — from OpenAI, Anthropic, Google, Mistral, Meta, xAI, DeepSeek, Alibaba, and more — under a single API key and a unified OpenAI-compatible endpoint. Instead of managing separate subscriptions and API keys for each provider, you get one endpoint, one key, and one monthly bill.

**Key characteristics:**
- **OpenAI-compatible API** — a drop-in replacement for `api.openai.com/v1` for any tool that supports custom base URLs
- **Multi-provider model catalog** — GPT, Claude, Gemini, Mistral, Llama, Grok, DeepSeek, Qwen, and more in one place
- **Subscription-based pricing** — flat monthly plans with included credits rather than per-token billing per provider
- **Single API key** — one key authenticates across all models on the platform

### Account Setup

1. Visit [mammouth.ai](https://mammouth.ai/) and sign up for an account.
2. Choose a subscription plan:

| Plan     | Monthly Price | Included Credits |
|----------|---------------|-----------------|
| Starter  | ~€4           | ~$2             |
| Standard | ~€8           | ~$4             |
| Expert   | ~€19          | ~$10            |

Credits are consumed per request based on the model used. Unused credits do not roll over. Check [mammouth.ai](https://mammouth.ai/) for current pricing, as plans and rates may change.

### Getting an API Key

1. Log in to your [Mammouth AI dashboard](https://mammouth.ai/).
2. Navigate to **Settings → API Keys** (or the API section of your account).
3. Create a new API key and copy it immediately — it is only shown once.
4. Store it securely, for example in your shell profile or a `.env` file:

```bash
export MAMMOUTH_API_KEY=your-api-key-here
```

**Security note:** Never commit your API key to version control. Add `.env` to your `.gitignore`.

### API Endpoint

| Property   | Value                            |
|------------|----------------------------------|
| Base URL   | `https://api.mammouth.ai/v1`     |
| Auth header | `Authorization: Bearer <key>`   |
| Protocol   | OpenAI-compatible (chat/completions, models) |

The API is a superset-compatible replacement for `https://api.openai.com/v1`. Any tool configured with a custom `OPENAI_API_BASE` or equivalent can be pointed at Mammouth AI by changing only the base URL and API key.

### Available Models

Mammouth AI aggregates models from multiple providers. Model availability evolves with the platform. The following table lists representative models available at the time of writing; for the authoritative current list, call the models endpoint or visit the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).

```bash
# List all available models
curl -s https://api.mammouth.ai/v1/models \
  -H "Authorization: Bearer $MAMMOUTH_API_KEY" | jq '.data[].id'
```

| Family    | Model ID                    | Notes                                  |
|-----------|-----------------------------|----------------------------------------|
| OpenAI    | `gpt-4.1`                   | Latest GPT-4 class model               |
| OpenAI    | `gpt-4.1-mini`              | Faster, cheaper GPT-4 variant          |
| OpenAI    | `gpt-5.2`                   | GPT-5 class model                      |
| OpenAI    | `gpt-5.2-thinking`          | GPT-5 with extended reasoning          |
| Anthropic | `claude-sonnet-4-6`         | Claude Sonnet 4.6                      |
| Anthropic | `claude-opus-4-6`           | Claude Opus 4.6 (most capable Claude)  |
| Google    | `gemini-2.5-flash`          | Fast Gemini 2.5                        |
| Google    | `gemini-2.5-pro`            | Full Gemini 2.5                        |
| Mistral   | `mistral`                   | Alias for latest Mistral medium        |
| Mistral   | `mistral-large-3`           | Mistral Large 3                        |
| Mistral   | `magistral`                 | Mistral reasoning model                |
| Meta      | `llama-4-maverick`          | Llama 4 Maverick                       |
| xAI       | `grok-4.1`                  | Grok 4.1                               |
| DeepSeek  | `deepseek-v3`               | DeepSeek V3                            |
| DeepSeek  | `deepseek-v3-reasoning`     | DeepSeek V3 with chain-of-thought      |
| Alibaba   | `qwen3-coder`               | Qwen 3 Coder (coding-specialist)       |
| Moonshot  | `kimi-k2`                   | Kimi K2                                |
| Perplexity| `sonar-pro`                 | Perplexity Sonar Pro (web-grounded)    |
| Codestral | `codestral`                 | Mistral code-specialist model          |

**Notes:**
- Model IDs that are plain names (e.g., `mistral`) are aliases Mammouth maintains; they always resolve to the current recommended version of that model family.
- Pricing per model varies. Check the Mammouth dashboard or the [API documentation](https://info.mammouth.ai/docs/api-quick-start/) for current rates.
- Model availability may change. Always verify with the `/v1/models` endpoint.

### Quick API Test

Verify your key and connectivity with a minimal `curl` call:

```bash
curl -s https://api.mammouth.ai/v1/chat/completions \
  -H "Authorization: Bearer $MAMMOUTH_API_KEY" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "gpt-4.1",
    "messages": [{"role": "user", "content": "Say hello in one sentence."}],
    "max_tokens": 32
  }' | jq '.choices[0].message.content'
```

You can replace `gpt-4.1` with any model ID from the table above.

### Using Mammouth AI with AI CLI Tools

Because Mammouth AI exposes a standard OpenAI-compatible API, the general pattern for wiring it into any supporting tool is:

```bash
export OPENAI_API_BASE=https://api.mammouth.ai/v1
export OPENAI_API_KEY=$MAMMOUTH_API_KEY
export OPENAI_MODEL=gpt-4.1   # or any model ID from the table above
```

For tool-specific setup (Aider, Codex, OpenCode, Goose, etc.) see the **Using [Tool] with Mammouth AI** subsections in [CLI.md](./CLI.md). For spec-driven development tools (OpenSpec, GitHub Spec Kit) see [SPEC.md](./SPEC.md).

For more information, visit the [Mammouth AI website](https://mammouth.ai/) and the [Mammouth AI API documentation](https://info.mammouth.ai/docs/api-quick-start/).
