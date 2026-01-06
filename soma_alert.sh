#!/bin/bash
# FILE: soma_alert.sh
# MISSION: Detect Prion-Misfolds in the Principal/Strata alignment

THRESHOLD=0.01 # 1% allowable drift
LOG_FILE="$HOME/judah-joseph-Atmos-Engine/soma_strata.log"

# Run the audit and capture the output
AUDIT_RESULT=$(python3 ~/judah-joseph-Atmos-Engine/strata_audit.py)

if [[ "$AUDIT_RESULT" == *"DEFICIT"* ]]; then
    echo -e "\033[1;31m"
    echo "⚠️  PRION-CHECK: MISFOLD DETECTED (STRUCTURAL DRIFT)"
    echo "⚠️  CRITICAL: Principal integrity compromised by economic drift."
    echo -e "\033[0m"
    echo "[$(date)] ALERT: Structural Drift Detected." >> "$LOG_FILE"
else
    echo -e "\033[1;32m✅ SYSTEM IS STERILE: No misfolding detected.\033[0m"
fi
