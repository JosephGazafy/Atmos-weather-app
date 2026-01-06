#!/bin/bash
# FILE: soma_log.sh
# MISSION: Chronological logging of Soma-Strata metrics

LOG_FILE="$HOME/judah-joseph-Atmos-Engine/soma_strata.log"
TIMESTAMP=$(date +"%Y-%m-%d %H:%M:%S")

echo "--- LOG ENTRY: $TIMESTAMP ---" >> "$LOG_FILE"

# 1. Log Principal and Audit Status
python3 ~/judah-joseph-Atmos-Engine/strata_audit.py >> "$LOG_FILE" 2>&1

# 2. Log Geodesic Efficiency
python3 ~/judah-joseph-Atmos-Engine/geodesic_path.py >> "$LOG_FILE" 2>&1

# 3. Log Physics Manifest Status
if [ -f "$HOME/judah-joseph-Atmos-Engine/data_vault/atmos_physics_update.json" ]; then
    echo "Vault: ONLINE" >> "$LOG_FILE"
else
    echo "Vault: OFFLINE" >> "$LOG_FILE"
fi

echo "--------------------------------------" >> "$LOG_FILE"
echo "[SUCCESS]: Metrics recorded to $LOG_FILE"
