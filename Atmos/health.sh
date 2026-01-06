

#!/bin/bash
# 1. Update Dashboard
COUNT=$(wc -l < data.json 2>/dev/null || echo "0")
sed -i "s/Last Sync:.*/Last Sync:  $(date)/" welcome.txt
sed -i "s/Data Count:.*/Data Count: $COUNT/" welcome.txt

# 2. Parallel Sync (Background)
(git add . && git commit -m "Speed Sync: $(date)" && git push origin HEAD:Master --force-with-lease > /dev/null 2>&1 &)

echo "✅ Local processes complete. Cloud sync running in background."




# 3. Run Go Scanner
./system/sovereign/sovereign_tool || { echo "❌ Go binary error"; exit 1; }

# 4. Update Dashboard
COUNT=$(wc -l < data.json 2>/dev/null || echo "0")
sed -i "s/Last Sync:.*/Last Sync:  $(date)/" welcome.txt
sed -i "s/Data Count:.*/Data Count: $COUNT/" welcome.txt

# 5. Push to GitHub
git add .
git commit -m "Auto-update: System health check and data sync"
git push origin Master --force-with-lease

# --- 📲 Enhanced Sovereign Notification Logic ---
TOKEN="YOUR_TELEGRAM_BOT_TOKEN"
CHAT_ID="YOUR_CHAT_ID"

# Calculate metrics for the notification
ALERT_COUNT=$(wc -l < alerts.log 2>/dev/null || echo "0")
DASHBOARD_MSG=$(cat welcome.txt)

# Construct a finer, multi-line message
FINAL_MSG="🚀 ATMOS UPDATE
------------------------
$DASHBOARD_MSG
------------------------
⚠️ CRITICAL ALERTS: $ALERT_COUNT"

# Send to Telegram
curl -s -X POST "https://api.telegram.org/bot$TOKEN/sendMessage" \
     -d chat_id="$CHAT_ID" \
     -d text="$FINAL_MSG" > /dev/null

echo "📲 Sovereign transmission complete: $ALERT_COUNT alerts reported."

