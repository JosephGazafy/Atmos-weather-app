#!/bin/bash
# ATMOS DMS-WARNING ENGINE (v44.0)
LAST_SEEN_FILE=~/Atmos-Engine/vault/last_seen
NOW=$(date +%s)
TOPIC="joseph_atmos_$(hostname)"

LAST_SEEN=$(cat $LAST_SEEN_FILE)
DIFF_SECONDS=$(( NOW - LAST_SEEN ))
DIFF_DAYS=$(( DIFF_SECONDS / 86400 ))

# WARNING: 60 DAYS
if [ $DIFF_DAYS -eq 60 ]; then
    curl -s -H "Title: LATTICE WARNING (60D)" \
         -H "Priority: high" -H "Tags: hourglass_flowing_sand,warning" \
         -d "60 days of inactivity. Lattice entering Pre-Stasis in 30 days." \
         ntfy.sh/$TOPIC
fi

# CRITICAL: 80 DAYS
if [ $DIFF_DAYS -eq 80 ]; then
    curl -s -H "Title: !! CRITICAL DMS ALERT (80D) !!" \
         -H "Priority: urgent" -H "Tags: alert,rotating_light" \
         -d "80 days of inactivity. Final Seal in 10 days. Run 'atmos' to reset." \
         ntfy.sh/$TOPIC
fi

# FINAL TRIGGER: 90 DAYS
if [ $DIFF_DAYS -ge 90 ]; then
    echo -e "\033[1;31m!! DEAD-MAN'S SWITCH TRIGGERED !!\033[0m"
    ~/Atmos-Engine/bin/panic-lock.sh
    exit 1
fi
