#!/bin/bash
# ATMOS CORE v3.0 - HEARTBEAT ALERT SENTINEL
WEBHOOK=https://discord.com/api/webhooks/1451503705953140890/KsrxKw4py4o0SCPFC4q02Xbf59ndVnlPEKoEqftnn0A93zWwSoNbjfnskvWB8DPzw884
THRESHOLD=1.05

echo "📡 Alert Sentinel Active. Monitoring HWM Variance..."

CGO_ENABLED=0 go run main.go | while read -r line; do
    echo "$line"
    VARIANCE=$(echo "$line" | grep -oP 'Δ: \K[0-9.]+')
    if [ ! -z "$VARIANCE" ]; then
        if (( $(echo "$VARIANCE > $THRESHOLD" | bc -l) )); then
            termux-notification --title "⚠️ ATMOS BREACH" --content "HWM Variance: $VARIANCE"
            curl -s -X POST -H "Content-Type: application/json" \
                -d "{\"content\": \"🚨 **HWM BREACH**: Variance $VARIANCE\"}" "$WEBHOOK" > /dev/null
        fi
    fi
done

