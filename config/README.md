# Config

Configuration and settings files for the local-LLM setup on BosGameM5, kept here so they're versioned and can be restored. Each subfolder mirrors the tool and the place the file goes on the machine. The launchers that use them are in [`../scripts/`](../scripts/).

**No file here contains a key.** The only place the router key lives is `~/.claude/litellm-remote-ddns-net.json` on the machine; every launcher and config reads it from there (or from an environment variable you set yourself). Get a key with `scripts/llm-guest-key.sh add <name>`.

## Harnesses on the remote LiteLLM router

Five agent harnesses pointed at the LiteLLM router on JoNAS (`https://pornbach.ddns.net:8443/litellm`), all on `qwen3-coder-next` on BosGameM5. Background and measured overhead: [ORCHESTRATION.md — Using It From Other Harnesses](../ORCHESTRATION.md#using-it-from-other-harnesses).

| Harness | Launcher (`scripts/` → `~/.local/bin/`) | Config here → where it goes | Key comes from |
|---|---|---|---|
| Claude Code | `claude-remote` | `claude/litellm-remote-ddns-net.json` → `~/.claude/` (put your key in, `chmod 600`) | the file itself |
| Pi Agent | `pi-remote` | `pi/remote.ts` → `~/.pi/agent/extensions/` | `$LITELLM_REMOTE_API_KEY`, else the Claude file |
| Hermes Agent | `hermes-remote` | `hermes/config-providers.yaml` → append to `~/.hermes/config.yaml` | `key_cmd` reading the Claude file |
| OpenCode | `opencode-remote` | `opencode/opencode.jsonc` → `~/.config/opencode/` | `$LITELLM_REMOTE_API_KEY`, set by the launcher |
| DeepSeek Harness | `dsh-remote` | `dsh/cordis.patch.yml` → `~/.dsh/profiles/<profile>/` for **each** profile you use (`web`, `headless`) | `$DSH_REMOTE_API_KEY`, set by the launcher |

Install the launchers with `install -m 755 scripts/{claude,pi,hermes,opencode,dsh}-remote ~/.local/bin/`. They need `jq`.

### Smoke test

Each should print `pong`. They all name `qwen3-coder-next` explicitly, so none of them can fall back to a harness's local default model and make LM Studio load something else (on BosGameM5, a second large model next to `qwen3-coder-next` doesn't fit in GPU memory).

```bash
claude-remote -p "Reply with just: pong"
pi-remote -p "Reply with just: pong"
hermes-remote -z "Reply with just: pong"
opencode-remote run -m litellm/qwen3-coder-next "Reply with just: pong"
dsh-remote headless "Reply with just: pong"
```

All five passed on 2026-10-15.

### Pitfalls hit while setting these up

- **Pi** crashed with `Cannot read properties of undefined (reading 'includes')` when the model entry lacked `input`. Pi requires `name`, `input`, `cost` and `reasoning` on every model.
- **Hermes** never reads a separate `config-remote.yaml`; named providers go under `providers:` in the main `~/.hermes/config.yaml`. Its main config still defaults to the local `qwen3.8-27b`, which is why `hermes-remote` always passes `--provider litellm-remote -m qwen3-coder-next`.
- **OpenCode** ignores files like `providers.json`. Its config is `opencode.jsonc` with a `provider` entry using the `@ai-sdk/openai-compatible` adapter. The first run installs that adapter and can take minutes; later runs start right away.
- **DeepSeek Harness** reads `~/.dsh/settings.yaml` only once: on the next start it imports it into the profile being started (`profiles/<profile>/cordis.patch.yml`) and renames it `settings.yaml.imported`. Other profiles don't get it, so copy `cordis.patch.yml` into each one. The very first headless run after the import still used the built-in DeepSeek provider (and failed on an expired DeepSeek key); later runs used the router.

## Claude Code on the local LiteLLM

`claude/litellm-settings.json` → `~/.claude/` points Claude Code at the personal LiteLLM on BosGameM5's `localhost:4000` (no real key needed; `lm-studio` is a placeholder). `bash/bashrc-llm.sh` holds the matching `claude-local` alias for `~/.bashrc`.

## LM Studio per-model defaults

`lmstudio/user-concrete-model-default-config/` → `~/.lmstudio/.internal/user-concrete-model-default-config/`. LM Studio applies these load settings whenever it loads the model, including on-demand loads triggered by the router, which otherwise use LM Studio's own defaults (an on-demand reload once came up with an 8192-token context and broke a long session).

| Model | Settings |
|---|---|
| `lmstudio-community/Qwen3-Coder-Next-GGUF` (Q6_K) | context 262144, 2 parallel slots |
| `unsloth/Qwen3.8-27B-GGUF` (UD-Q4_K_M) | context 262144 |
| `unsloth/Qwen3.8-27B-GGUF` (BF16) | context 100000 |
| `bottlecapai/ThinkingCap-Qwen3.8-27B-GGUF` (Q4_K_M) | context 262144 |
| `unsloth/gpt-oss-20b-GGUF` (Q4_K_M) | context 131072 |

Why 2 slots and the default batch size for Qwen3-Coder-Next: [ORCHESTRATION.md — Choosing the Model](../ORCHESTRATION.md#choosing-the-model-reading-the-prompt-is-the-bottleneck). Setting keys LM Studio accepts here include `llm.load.contextLength`, `llm.load.numParallelSessions`, `llm.load.llama.evalBatchSize`, `llm.load.llama.physicalBatchSize` and `llm.load.llama.gpuOffload`.
