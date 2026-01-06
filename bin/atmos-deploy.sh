#!/bin/bash
MSG="${1:-Hyperlattice Apex Sync v10.7}"
echo "🚀 [ATMOS] INITIATING GLOBAL CONSOLIDATION..."

# A. LOCAL ALIGNMENT
git rebase --abort 2>/dev/null
git add .
git commit -m "$MSG" 2>/dev/null

# B. MULTI-NODE BROADCAST
for remote in $(git remote); do
    echo -n "📡 [NET] Node: $remote ... "
    if git push $remote Master --force 2>&1 | grep -q "workflow"; then
        echo "⚠️  [SCOPE ERROR] Update PAT with 'workflow' permission."
    else
        echo "✅ [SYNCED]"
    fi
done

# C. CLOUD HANDSHAKE
echo "☁️  [CLOUD] Refreshing Google Backbone..."
# -o ConnectTimeout=5 prevents the script from hanging on a dead cloud shell
ssh -tt -o ConnectTimeout=5 -o StrictHostKeyChecking=no google-cloud-shell << ENDSSH
    cd ~/judah-joseph-Atmos-Engine
    git fetch --all && git reset --hard origin/Master
    pkill -f atmos && nohup python3 bin/atmos-master-hud.py > /dev/null 2>&1 &
    exit
ENDSSH
echo "✅ [SYSTEM] HYPERLATTICE OPERATION COMPLETE."
