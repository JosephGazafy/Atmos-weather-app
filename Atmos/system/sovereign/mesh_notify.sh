#!/bin/bash
# ATMOS CORE v3.0 - STATUS NOTIFICATION
# Requires: pkg install termux-api

# 1. Gather Metrics
NODE_ID=$(md5sum <<< "$(hostname)" | cut -c 1-8)
DRIFT=$(./sovereign.sh mesh | grep -oP '(?<=Delta: ).*' || echo "1.00")
SHIELD_STATUS=$(pgrep -f "acoustic_shield.py" > /dev/null && echo "ACTIVE" || echo "OFFLINE")

# 2. Push Notification
termux-notification \
  --id "atmos_status" \
  --title "🌐 ATMOS CORE v3.0 | $NODE_ID" \
  --content "HWM Drift: $DRIFT | Shield: $SHIELD_STATUS | US-East Active" \
  --priority high \
  --ongoing \
  --icon security

