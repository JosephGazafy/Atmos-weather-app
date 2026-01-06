#!/bin/bash
# FILE: strata_manifest.sh
# PROJECT: Judah-Joseph-Atmos-Engine
# MISSION: Readable printout of active variables within the Soma Vault

VAULT="./data_vault"
JSON_FILE="$VAULT/atmos_physics_update.json"

echo -e "\033[1;35m--- SOMA ENGINE: ACTIVE STRATA MANIFEST ---\033[0m"

if [ -f "$JSON_FILE" ]; then
    echo -e "\033[1;33m[TEMPORAL SCALES]\033[0m"
    python3 -c "import json; d=json.load(open('$JSON_FILE')); print(f'Century Span: {d[\"scaling_metrics\"][\"century_seconds\"]} seconds\nLight Year Scale: {d[\"scaling_metrics\"][\"light_year_scaling\"]} meters')"

    echo -e "\n\033[1;33m[PHYSICAL CONSTANTS]\033[0m"
    python3 -c "import json; d=json.load(open('$JSON_FILE'))['fundamental_constants']; 
for domain, params in d.items():
    print(f'-- {domain.upper()} --')
    for k, v in params.items():
        print(f'  {k}: {v}')"
else
    echo -e "\033[0;31m[ERROR]: Data Vault Offline. Run ~/rebuild_soma_vault.sh\033[0m"
fi
echo -e "\033[1;35m-------------------------------------------\033[0m"
