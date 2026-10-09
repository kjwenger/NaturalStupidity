#!/usr/bin/env bash
# scripts/llm-firewall.sh — ufw rules for a GPU box that serves models to
# the LiteLLM router on JoNAS (the Raspberry Pi NAS, see ORCHESTRATION.md).
#
# Policy:
#   - deny all incoming by default (IPv4 and IPv6)
#   - trusted local networks keep full access, as before ufw was on
#   - Docker containers keep reaching host services
#   - the model server port (LM Studio :1234) answers ONLY the router —
#     not the rest of the LAN, since it has no authentication of its own
#
# Re-runnable; backs up /etc/ufw first. Overrides:
#   LAN_SUBNETS="192.168.178.0/24 10.223.28.0/24"  trusted networks
#   ROUTER_IP=192.168.178.17                       JoNAS
#   MODEL_PORTS="1234"                             model server port(s)
# (a phone hotspot picks a new random 10.x subnet now and then — re-run
# with the new one if a hotspot-connected machine loses access).
#
# Usage: scripts/llm-firewall.sh [apply|status]

set -euo pipefail

LAN_SUBNETS="${LAN_SUBNETS:-192.168.178.0/24 10.223.28.0/24}"
ROUTER_IP="${ROUTER_IP:-192.168.178.17}"
MODEL_PORTS="${MODEL_PORTS:-1234}"
# IPv6 link-local and unique-local (the Fritz!Box hands out an fd.. prefix)
LAN6_SUBNETS="fe80::/10 fc00::/7"
# All Docker bridge networks (docker0 and compose's br-*)
DOCKER_SUBNETS="172.16.0.0/12"

if [[ "${1:-apply}" == status ]]; then
  sudo ufw status numbered
  exit 0
fi

stamp=$(date +%Y%m%d-%H%M%S)
sudo cp -a /etc/ufw "/etc/ufw.bak-$stamp"
echo "Backed up /etc/ufw to /etc/ufw.bak-$stamp"

sudo ufw default deny incoming
sudo ufw default allow outgoing

for net in $LAN_SUBNETS $LAN6_SUBNETS $DOCKER_SUBNETS; do
  sudo ufw allow from "$net" comment 'llm: trusted local network'
done

# Prepended so they win over the broad LAN allows above: the router may
# reach the model port, nothing else on the LAN may. Prepend order is
# reversed — the deny goes in first so the allow ends up above it.
for port in $MODEL_PORTS; do
  sudo ufw prepend deny proto tcp to any port "$port" comment 'llm: model server, router only'
  sudo ufw prepend allow proto tcp from "$ROUTER_IP" to any port "$port" comment 'llm: model server, router only'
done

sudo ufw --force enable
sudo ufw reload
sudo ufw status numbered
