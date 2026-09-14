#!/usr/bin/env bash
# orchestration/fleet.sh — start/stop/restart/status across all three machines.
#
# This is the thin remote-control layer ON TOP OF the systemd units
# (orchestration/systemd/) and launchd plist (orchestration/launchd/) —
# it assumes those are already installed on each machine, not a substitute
# for them. See ORCHESTRATION.md#starting-and-supervising-the-fleet.
#
# Requires passwordless sudo on each host for exactly these commands (or
# expect an interactive password prompt per host, per run) — e.g. via a
# narrowly-scoped /etc/sudoers.d entry, not blanket NOPASSWD.
#
# Usage: ./fleet.sh {start|stop|restart|status}

set -euo pipefail

ACTION="${1:?Usage: $0 {start|stop|restart|status}}"

STRIX_HOST=strix-halo.local
GS63_HOST=msi-laptop.local
MAC_HOST=mac-mini.local
MAC_LABEL=system/com.local.mlx-server

case "$ACTION" in
  start)
    ssh "$STRIX_HOST" sudo systemctl start llama-server
    ssh "$GS63_HOST"  sudo systemctl start llama-server
    # launchd has no direct "start if stopped" verb distinct from kickstart;
    # if bootout was used to stop it, this won't bring it back — see stop) below.
    ssh "$MAC_HOST"   sudo launchctl kickstart "$MAC_LABEL"
    ;;
  stop)
    ssh "$STRIX_HOST" sudo systemctl stop llama-server
    ssh "$GS63_HOST"  sudo systemctl stop llama-server
    # bootout fully unloads the job (KeepAlive included); bringing it back
    # needs `launchctl bootstrap`, not `kickstart` — see the plist's comments.
    ssh "$MAC_HOST"   sudo launchctl bootout "$MAC_LABEL"
    ;;
  restart)
    ssh "$STRIX_HOST" sudo systemctl restart llama-server
    ssh "$GS63_HOST"  sudo systemctl restart llama-server
    ssh "$MAC_HOST"   sudo launchctl kickstart -k "$MAC_LABEL"
    ;;
  status)
    ssh "$STRIX_HOST" sudo systemctl status llama-server --no-pager
    ssh "$GS63_HOST"  sudo systemctl status llama-server --no-pager
    ssh "$MAC_HOST"   sudo launchctl print "$MAC_LABEL"
    ;;
  *)
    echo "Unknown action: $ACTION" >&2
    exit 1
    ;;
esac
