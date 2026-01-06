#!/bin/bash
# Check if battery is above 20% before executing global saturation
BATT=$(termux-battery-status | jq -r '.percentage')
if [ "$BATT" -gt 20 ]; then
    ~/Atmos-Engine/bin/atmos-auto-push.sh
fi
