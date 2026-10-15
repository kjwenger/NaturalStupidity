import type { ExtensionAPI } from "pi-coding-agent";

// The LiteLLM router on JoNAS (see NaturalStupidity's ORCHESTRATION.md).
// Key: $LITELLM_REMOTE_API_KEY if set, otherwise the one already stored in
// the Claude Code remote settings file, so there is only one copy of it.
export default async function (pi: ExtensionAPI) {
  pi.registerProvider("litellm", {
    baseUrl: "https://pornbach.ddns.net:8443/litellm/v1",
    apiKey: process.env.LITELLM_REMOTE_API_KEY
      ? "$LITELLM_REMOTE_API_KEY"
      : "!jq -r .env.ANTHROPIC_AUTH_TOKEN ~/.claude/litellm-remote-ddns-net.json",
    api: "openai-completions",
    models: [
      {
        id: "qwen3-coder-next",
        name: "Qwen3-Coder-Next (BosGameM5 via JoNAS)",
        // Pi requires these; without `input` it crashes with
        // "Cannot read properties of undefined (reading 'includes')".
        input: ["text"],
        reasoning: false,
        cost: { input: 0, output: 0, cacheRead: 0, cacheWrite: 0 },
        contextWindow: 262144,
        maxTokens: 8192,
      },
    ],
  });
}
