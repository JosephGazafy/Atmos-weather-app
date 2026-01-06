#!/bin/bash
# ATMOS-ATLAS GLOBAL BLACKLIST SYNC

BLACKLIST="blocked_ips.txt"

if [ -f "$BLACKLIST" ]; then
    echo "📡 [HYPERLATTICE] Broadcasting Global Blacklist..."
    
    # Push to central repository to alert other nodes
    git add $BLACKLIST
    git commit -m "SHIELD_UPDATE: New Persistent Threats Neutralized"
    git push origin Master
    
    # Broadcast to Google Cloud Node via Deployment Script
    ./bin/atmos-deploy.sh "GLOBAL-STERILIZATION: Blacklisting $(wc -l < $BLACKLIST) Adversaries"
    
    echo "✅ [HYPERLATTICE] Universal Rejection Active."
else
    echo "⚠️ [HYPERLATTICE] No threats found. Perimeter clear."
fi
