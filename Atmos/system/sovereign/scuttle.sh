#!/bin/bash
# ATMOS CORE v3.0 - AUTO-DESTRUCT DAEMON
VAULT=".atmos_vault"
TIMER=86400 # 24 Hours in seconds

echo "⏳ Auto-Destruct Daemon Initialized. Monitoring $VAULT..."

while true; do
    if [ -f "$VAULT" ]; then
        LAST_MOD=$(stat -c %Y "$VAULT")
        CURRENT_TIME=$(date +%s)
        ELAPSED=$((CURRENT_TIME - LAST_MOD))

        if [ $ELAPSED -ge $TIMER ]; then
            rm -f "$VAULT"
            echo "🔥 Threshold Reached. Vault Shredded." | logger
            exit 0
        fi
    fi
    sleep 3600 # Check once every hour to save battery
done

