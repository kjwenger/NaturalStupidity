#!/usr/bin/env bash
# scripts/llm-guest-key.sh — issue, list and revoke API keys for the LiteLLM
# router on JoNAS (JoNAS repo: stacks/litellm/). Public endpoint:
# https://pornbach.ddns.net:8443/litellm (and pornbach.myddns.me), via JoNAS's
# Caddy; LAN-direct: http://192.168.178.17:4000.
#
# Usage:
#   scripts/llm-guest-key.sh add <name> [--models m1,m2] [--rpm N] [--parallel N] [--days N]
#   scripts/llm-guest-key.sh list
#   scripts/llm-guest-key.sh revoke <name>
#
# Defaults: all router models, 20 requests/minute, 2 requests at a time,
# no expiry. The key is printed once — send it to your friend over a
# private channel (Signal etc.), not email.
#
# The master key is read from $LITELLM_MASTER_KEY, or fetched over SSH
# from JoNAS's gitignored config/litellm.env (key-based SSH required).

set -euo pipefail

URL="${LITELLM_URL:-http://192.168.178.17:4000}"
PUBLIC_URL="${LITELLM_PUBLIC_URL:-https://pornbach.ddns.net:8443/litellm}"
JONAS="${JONAS_HOST:-jonas.local}"
JONAS_ENV="${JONAS_ENV:-/com.github/kjwenger/JoNAS/config/litellm.env}"

MASTER="${LITELLM_MASTER_KEY:-$(ssh -o BatchMode=yes "$JONAS" \
  "grep '^LITELLM_MASTER_KEY=' '$JONAS_ENV' | cut -d= -f2-")}"
[[ -n "$MASTER" ]] || { echo "Could not get the master key from $JONAS:$JONAS_ENV" >&2; exit 1; }

api() {  # api METHOD PATH [JSON]
  curl -fsS -X "$1" "$URL$2" -H "Authorization: Bearer $MASTER" \
    -H 'Content-Type: application/json' ${3:+-d "$3"}
}

cmd="${1:-}"; shift || true
case "$cmd" in
  add)
    name="${1:?Usage: $0 add <name> [--models m1,m2] [--rpm N] [--parallel N] [--days N]}"; shift
    models="" rpm=20 parallel=2 days=""
    while (($#)); do
      case "$1" in
        --models)   models="$2"; shift 2 ;;
        --rpm)      rpm="$2"; shift 2 ;;
        --parallel) parallel="$2"; shift 2 ;;
        --days)     days="$2"; shift 2 ;;
        *) echo "Unknown option: $1" >&2; exit 1 ;;
      esac
    done
    body=$(python3 -c '
import json, sys
name, models, rpm, parallel, days = sys.argv[1:]
b = {"key_alias": name, "rpm_limit": int(rpm), "max_parallel_requests": int(parallel),
     "metadata": {"guest": name}}
if models: b["models"] = models.split(",")
if days: b["duration"] = days + "d"
print(json.dumps(b))' "$name" "$models" "$rpm" "$parallel" "$days")
    key=$(api POST /key/generate "$body" | python3 -c 'import json,sys; print(json.load(sys.stdin)["key"])')
    cat <<EOF
Key for $name (shown once):

  $key

Send them this. OpenAI-compatible tools:

  export OPENAI_API_BASE=$PUBLIC_URL/v1
  export OPENAI_API_KEY=$key

Claude Code (settings.json "env" block, or exported):

  ANTHROPIC_BASE_URL=$PUBLIC_URL
  ANTHROPIC_AUTH_TOKEN=$key
EOF
    ;;
  list)
    api GET "/key/list?return_full_object=true&size=100" | python3 -c '
import json, sys
row = "{:20} {:28} {:>4} {:>4}  {}"
print(row.format("alias", "models", "rpm", "par", "expires"))
for k in json.load(sys.stdin).get("keys", []):
    if not isinstance(k, dict) or not k.get("key_alias"):
        continue
    print(row.format(k["key_alias"], ",".join(k.get("models") or ["(all)"]),
                     str(k.get("rpm_limit") or "-"), str(k.get("max_parallel_requests") or "-"),
                     str(k.get("expires") or "never")[:19]))'
    ;;
  revoke)
    name="${1:?Usage: $0 revoke <name>}"
    api POST /key/delete "$(python3 -c 'import json,sys; print(json.dumps({"key_aliases":[sys.argv[1]]}))' "$name")" >/dev/null
    echo "Revoked all keys with alias '$name'."
    ;;
  *)
    sed -n '2,19p' "$0" | sed 's/^# \{0,1\}//'
    exit 1
    ;;
esac
