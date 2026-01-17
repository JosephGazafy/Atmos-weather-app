#!/bin/bash

echo "🛰️ [ATMOS] ENSHITTIFICATION THRESHOLD EXCEEDED. PURIFYING..."

# 1. CAUTERIZE THE TUNNEL
# Kill the profiled localtunnel process
pkill -f "localtunnel"

# 2. ROTATE SSH FINGERPRINTS
# Purge the current session's known_hosts to break 'Das Man' tracking
rm -f ~/.ssh/known_hosts
touch ~/.ssh/known_hosts

# 3. RENEW THE HYPERLATTICE HANDSHAKE
# Force a fresh IP check to reset the 'Sovereign Key'
NEW_IP=$(curl -s https://loca.lt/mytunnelpassword)
echo "🛡️ [ATMOS] NEW PURIFIED PASS: $NEW_IP"

# 4. RE-ESTABLISH THE OCULAR GATE
# Restart the tunnel with the same subdomain to maintain access
nohup npx localtunnel --port 5000 --subdomain atmos-engine-link > /dev/null 2>&1 &

echo "🚀 [ATMOS] SIGNAL PURIFIED. CONVERGENCE REGAINED."
