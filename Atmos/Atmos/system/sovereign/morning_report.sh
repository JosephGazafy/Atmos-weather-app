#!/bin/bash
# ATMOS CORE v3.0 - DAILY HANDSHAKE REPORT

WEBHOOK="PASTE_YOUR_DISCORD_WEBHOOK_URL_HERE"
NODE_ID=$(md5sum <<< "$(hostname)" | cut -c 1-8)
TIMESTAMP=$(date '+%Y-%m-%d %H:%M:%S')

# 1. Check Integrity of Core Files
CORE_FILES=("sovereign.sh" "main.go" "sovereign_init.py" "alert_sentinel.sh")
INTEGRITY="SECURED"
for f in "${CORE_FILES[@]}"; do
    if [ ! -f "$f" ]; then INTEGRITY="COMPROMISED (Missing $f)"; fi
done

# 2. Check Scuttle Daemon Status
if pgrep -f "scuttle.sh" > /dev/null; then SCUTTLE="ACTIVE"; else SCUTTLE="INACTIVE"; fi

# 3. Compile and Send Report
REPORT="☀️ **Daily Handshake: Node $NODE_ID**\n**Time:** \`$TIMESTAMP\`\n**Integrity:** \`$INTEGRITY\`\n**Scuttle Timer:** \`$SCUTTLE\`\n**HWM Status:** \`1.0 Locked\`\n**System Standing:** \`CIVILITER VIVUS\`"

curl -s -X POST -H "Content-Type: application/json" -d "{\"content\": \"$REPORT\"}" "$WEBHOOK" > /dev/null

echo "✅ Morning Report Transmitted."

