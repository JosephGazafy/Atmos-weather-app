#!/bin/bash
# ATMOS GLOBAL-PANIC SYNC (v47.9)
TOPIC="joseph_atmos_global_panic"

# This listener simulates a background process checking the lattice-wide status
# In a live env, this would be a webhook or websocket listener.
REMOTE_PANIC=$(curl -s "ntfy.sh/$TOPIC/raw" | tail -n 1)

if [[ "$REMOTE_PANIC" == *"CRITICAL_PANIC"* ]]; then
    echo "1" > ~/Atmos-Engine/vault/global_panic.cfg
    # Long, jarring 2-second pulse for global events
    termux-vibrate -d 2000 -f
    termux-tts-speak -p 0.5 -r 0.8 "Global lattice breach detected. Defend the principal."
else
    echo "0" > ~/Atmos-Engine/vault/global_panic.cfg
fi
