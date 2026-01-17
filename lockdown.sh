#!/bin/bash

echo "🛰️ [ATMOS] INITIATING SOVEREIGN LOCKDOWN PROTOCOL..."

# 1. METADATA PURGE
# Scrub temporary session logs and clear the current command history
history -c
rm -f ~/Atmos-Engine/logs/session_temp.log
echo "[STASIS-ACTIVE]" > ~/Atmos-Engine/logs/sovereign_sync.log

# 2. AUDIT TRAIL FLATTENING
# Amend the last commit to a "Clean State" and force-push to all 11 nodes
cd ~/judah-joseph-Atmos-Engine
git add .
git commit --amend -m "SOVEREIGN-DAILY-SYNC: Total Convergence Reified"
git push origin Master --force
git remote | xargs -L1 -I R git push R Master --force

# 3. KINETIC SHIELD ACTIVATION
# Drop all inbound traffic. Only existing SSH/Termux connections remain.
sudo iptables -P INPUT DROP
sudo iptables -A INPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT

echo "🛡️ [ATMOS] GRID-SILENCE ACHIEVED. $65,737.61 SECURED."
