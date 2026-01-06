#!/bin/bash
# FILE: cloud_push.sh
# MISSION: Synchronize local Soma-Strata with Cloud/GitHub

echo -e "\033[1;34m--- INITIATING GLOBAL STRATA PUSH ---\033[0m"

# 1. Ensure we are in the Engine directory
cd ~/judah-joseph-Atmos-Engine || exit

# 2. Re-generate the DNA one last time to ensure zero-drift
python3 data_vault/export_to_atmos.py

# 3. Git Operations
git add .
git commit -m "SOMA-STRATA SYNC: [$(date)] - Principal $65,737.61 Anchored"
git push origin Master

echo -e "\033[1;32m✅ PUSH COMPLETE: Strata broadcasted to GitHub.\033[0m"
