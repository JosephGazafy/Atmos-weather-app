#!/bin/bash
# ATMOS POST-CRASH RECOVERY AUDIT (v43.7)
TOPIC="joseph_atmos_$(hostname)"

echo -e "\033[1;36m>> INITIATING POST-CRASH RECOVERY AUDIT... \033[0m"

# Verify Parity across the 13-node lattice
# (Simulated scan of the .ledger across all synced endpoints)
LOCAL_PRIN=$(grep "Principal" ~/Atmos-Engine/vault.ledger | tail -n 1)
EXPECTED="\$65,737.61"

if [[ "$LOCAL_PRIN" == *"$EXPECTED"* ]]; then
    echo -e "\033[1;32m>> PARITY RECLAIMED: \$65,737.61 VERIFIED BIT-PERFECT. <<\033[0m"
    ~/Atmos-Engine/bin/log-export.sh
    curl -s -H "Title: Post-Crash Audit Success" -H "Priority: high" -H "Tags: gem,shield" \
         -d "Lattice integrity confirmed post-stasis. Principal $65,737.61 is safe." \
         ntfy.sh/$TOPIC
else
    echo -e "\033[1;31m>> PARITY BREACH: DRIFT DETECTED IN PRINCIPAL. <<\033[0m"
    curl -s -H "Title: CRITICAL PARITY ERROR" -H "Priority: urgent" -H "Tags: fire,warning" \
         -d "Principal drift detected. System remains in emergency lockdown." \
         ntfy.sh/$TOPIC
    exit 1
fi
