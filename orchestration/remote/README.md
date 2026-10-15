# Harnesses on the remote LiteLLM router

Configurations and launchers that point five agent harnesses at the LiteLLM router on JoNAS (`https://pornbach.ddns.net:8443/litellm`), all using `qwen3-coder-next` on BosGameM5. See [ORCHESTRATION.md — Using It From Other Harnesses](../../ORCHESTRATION.md#using-it-from-other-harnesses) for the background and the measured overhead of each.

**No file here contains a key.** Every launcher and config reads it from `~/.claude/litellm-remote-ddns-net.json` (the Claude Code settings file, the one place the key is stored), unless you set the harness's environment variable yourself. Get a key with `scripts/llm-guest-key.sh add <name>`.

| Harness | Launcher → `~/.local/bin/` | Config → where it goes | Key comes from |
|---|---|---|---|
| Claude Code | `claude --settings …` | `claude/litellm-remote-ddns-net.json` → `~/.claude/` (put your key in, `chmod 600`) | the file itself |
| Pi Agent | `bin/pi-remote` | `pi/remote.ts` → `~/.pi/agent/extensions/` | `$LITELLM_REMOTE_API_KEY`, else the Claude file |
| Hermes Agent | `bin/hermes-remote` | `hermes/config-providers.yaml` → append to `~/.hermes/config.yaml` | `key_cmd` reading the Claude file |
| OpenCode | `bin/opencode-remote` | `opencode/opencode.jsonc` → `~/.config/opencode/` | `$LITELLM_REMOTE_API_KEY`, set by the launcher |
| DeepSeek Harness | `bin/dsh-remote` | `dsh/cordis.patch.yml` → `~/.dsh/profiles/<profile>/` for **each** profile you use (`web`, `headless`) | `$DSH_REMOTE_API_KEY`, set by the launcher |

The launchers need `jq`.

## Smoke test

Each should print `pong`. They all name `qwen3-coder-next` explicitly, so none of them can fall back to a harness's local default model and make LM Studio load something else (on BosGameM5, a second large model next to `qwen3-coder-next` doesn't fit in GPU memory).

```bash
claude --settings ~/.claude/litellm-remote-ddns-net.json -p "Reply with just: pong"
pi-remote -p "Reply with just: pong"
hermes-remote -z "Reply with just: pong"
opencode-remote run -m litellm/qwen3-coder-next "Reply with just: pong"
dsh-remote headless "Reply with just: pong"
```

## Pitfalls hit while setting these up

- **Pi** crashed with `Cannot read properties of undefined (reading 'includes')` when the model entry lacked `input`. Pi requires `name`, `input`, `cost` and `reasoning` on every model.
- **Hermes** never reads a separate `config-remote.yaml`; named providers go under `providers:` in the main `~/.hermes/config.yaml`. Its main config still defaults to the local `qwen3.8-27b`, which is why `hermes-remote` always passes `--provider litellm-remote -m qwen3-coder-next`.
- **OpenCode** ignores files like `providers.json`. Its config is `opencode.jsonc` with a `provider` entry using the `@ai-sdk/openai-compatible` adapter. The first run installs that adapter and can take minutes; later runs start right away.
- **DeepSeek Harness** reads `~/.dsh/settings.yaml` only once: on the next start it imports it into the profile being started (`profiles/<profile>/cordis.patch.yml`) and renames it `settings.yaml.imported`. Other profiles don't get it, so copy `cordis.patch.yml` into each one. The very first headless run after the import still used the built-in DeepSeek provider (and failed on an expired DeepSeek key); later runs used the router.
