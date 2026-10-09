#!/usr/bin/env bash
# scripts/fleet-check.sh — are the LLM fleet machines online, and what are
# their real hostnames and IP addresses?
#
# For each machine: resolve its mDNS name (getent, falling back to
# avahi-resolve), ping it, probe the ports our LLM servers use, and — if
# key-based SSH is already set up — ask it for its own hostname. Then sweep
# the local /24 and list mDNS-advertised hosts, so a machine whose name we
# got wrong still shows up somewhere.
#
# Usage: scripts/fleet-check.sh [--no-sweep]
#
# Override a machine's name via env, e.g. GS63_HOST=my-laptop.local
# (the GS63's real hostname was unknown when this was written — it was
# offline; msi-laptop.local is the placeholder from orchestration/).

set -uo pipefail

STRIX_HOST="${STRIX_HOST:-BosGameM5.local}"
MAC_HOST="${MAC_HOST:-EmmFour.local}"
GS63_HOST="${GS63_HOST:-msi-laptop.local}"

# 22 = ssh, 8080 = llama-server/mlx_lm.server, 1234 = LM Studio,
# 50052 = llama.cpp rpc-server, 52415 = EXO
PORTS=(22 8080 1234 50052 52415)

SWEEP=1
[[ "${1:-}" == "--no-sweep" ]] && SWEEP=0

resolve() {
  local name="$1" ip
  ip=$(getent ahostsv4 "$name" 2>/dev/null | awk 'NR==1{print $1}')
  if [[ -z "$ip" ]] && command -v avahi-resolve >/dev/null; then
    ip=$(timeout 5 avahi-resolve -4 -n "$name" 2>/dev/null | awk '{print $2}')
  fi
  echo "$ip"
}

port_open() { timeout 2 bash -c "</dev/tcp/$1/$2" 2>/dev/null; }

remote_hostname() {
  # BatchMode: never prompt for a password or an unknown host key.
  local out
  if out=$(ssh -o BatchMode=yes -o ConnectTimeout=5 "$1" \
      'scutil --get ComputerName 2>/dev/null || hostname' 2>&1); then
    echo "$out" | head -1
  else
    echo "(ssh failed: $(echo "$out" | tail -1) — run 'ssh $1' once by hand)"
  fi
}

check() {
  local label="$1" name="$2" ip open=() p
  printf '\n== %s (%s)\n' "$label" "$name"

  # The machine running this script: report locally, no network round-trip.
  if [[ "${name%.local}" == "$(hostname)" ]]; then
    echo "   status:   ONLINE (this machine)"
    echo "   hostname: $(hostname)"
    ip -4 -br addr | awk '$1!="lo" && $1!~/^(docker|br-|veth)/ {print "   ip:       "$3" ("$1")"}'
    return
  fi

  ip=$(resolve "$name")
  if [[ -z "$ip" ]]; then
    echo "   status:   OFFLINE (name does not resolve)"
    return
  fi
  echo "   ip:       $ip"
  if ping -c1 -W2 "$ip" >/dev/null 2>&1; then
    echo "   status:   ONLINE"
  else
    echo "   status:   no ping reply (may still be up with ICMP blocked)"
  fi
  for p in "${PORTS[@]}"; do port_open "$ip" "$p" && open+=("$p"); done
  echo "   ports:    ${open[*]:-none of ${PORTS[*]}}"
  if [[ " ${open[*]} " == *" 22 "* ]]; then
    echo "   hostname: $(remote_hostname "$ip")"
  fi
}

check "Strix Halo" "$STRIX_HOST"
check "Mac Mini M4" "$MAC_HOST"
check "MSI GS63"   "$GS63_HOST"

if (( SWEEP )); then
  printf '\n== mDNS-advertised hosts\n'
  if command -v avahi-browse >/dev/null; then
    timeout 8 avahi-browse -arpt 2>/dev/null | awk -F';' '$1=="=" && $3=="IPv4" {print "   "$7"  "$8}' | sort -u
  else
    echo "   (avahi-browse not installed)"
  fi

  iface=$(ip route show default | awk 'NR==1{print $5}')
  cidr=$(ip -4 -o addr show dev "$iface" | awk 'NR==1{print $4}')
  if [[ "$cidr" == */24 ]]; then
    net=${cidr%.*}
    printf '\n== Ping sweep of %s.0/24 (%s)\n' "$net" "$iface"
    for i in $(seq 1 254); do
      ( ping -c1 -W1 "$net.$i" >/dev/null 2>&1 && echo "$net.$i" ) &
    done | sort -t. -k4 -n | while read -r ip; do
      echo "   $ip  $(getent hosts "$ip" | awk '{print $2}')"
    done
    wait
  else
    echo "   (skipping sweep: $iface is $cidr, not a /24)"
  fi
fi
