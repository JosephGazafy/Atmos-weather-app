#!/bin/bash
# ATMOS-ATLAS KINETIC FIREWALL

LEDGER="intel_ledger.txt"
THRESHOLD=3

echo "🛡️ [FIREWALL] Scanning for Persistent Threats..."

# Identify IPs that appear 3 or more times
REPEAT_OFFENDERS=$(grep -oP 'IP=\K[0-9.]+' $LEDGER | sort | uniq -c | awk -v t=$THRESHOLD '$1 >= t {print $2}')

for IP in $REPEAT_OFFENDERS; do
    if ! grep -q "$IP" blocked_ips.txt 2>/dev/null; then
        echo "🚫 [BLOCKING] Persistent Threat Detected: $IP"
        python3 bin/atmos-notify.py "<b>Kinetic Block Executed</b>
Target: $IP
Status: Severed"
        python3 bin/atmos-notify.py "<b>Kinetic Block Executed</b>
Target: $IP
Status: Severed"
        python3 bin/atmos-notify.py "<b>Kinetic Block Executed</b>
Target: $IP
Status: Severed"
        # Termux Note: True iptables requires root. 
        # We use a routing reject or internal app-level block list.
        echo "$IP" >> blocked_ips.txt
        
        # Log the Kinetic Action
        echo "[$(date +"%Y-%m-%d %H:%M:%S")] 🛡️ KINETIC: IP=$IP | STATUS=SEVERED | REASON=PERSISTENT_THREAT" >> sovereign_log.txt
    fi
done
bash ~/Atmos-Engine/bin/atmos-sync-blacklist.sh
python3 ~/judah-joseph-Atmos-Engine/bin/atmos-debrief.py
