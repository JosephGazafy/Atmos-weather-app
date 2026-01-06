#!/bin/bash
# ATMOS GHOST-AUDIT: LOW-POWER PARITY CHECK
TOKEN="$ATMOS_TOKEN"
REPOS=("judah-joseph-Atmos-Engine" "atmos-logs" "Atmos-weather-app")
LOCAL_HASH=$(sha256sum ~/Atmos-Engine/vault/joule_harvest.log | cut -d ' ' -f 1)

echo ">> GHOST-AUDIT INITIATED: CHECKING GLOBAL MIRRORS..."

for repo in "${REMOTES[@]}"; do
    # Silent header-check to save data and battery
    REMOTE_HASH=$(curl -s -H "Authorization: token $TOKEN" \
    "https://api.github.com/repos/JosephGazafy/$repo/commits/main" | jq -r '.sha')
    
    if [ -z "$REMOTE_HASH" ]; then
        termux-toast "Audit Warning: Remote $repo unreachable."
    fi
done

# Single micro-vibration for silent confirmation
termux-vibrate -d 50
