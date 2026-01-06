#!/bin/bash
# ATMOS AUTO-PUSH SENTINEL
TOKEN="$ATMOS_TOKEN"
REMOTES=("origin" "logs-repo" "weather-app")

cd ~/Atmos-Engine
git add .
git commit -m "SENTINEL-SYNC: $(date +'%Y-%m-%d %H:%M:%S')"

for remote in "${REMOTES[@]}"; do
    git push $remote main --force
done
~/Atmos-Engine/bin/atmos-haptic-confirm.sh
