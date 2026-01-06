#!/bin/bash
# ATMOS CORE v3.1 - FORGE MONITOR
TOKEN="<YOUR_GITHUB_TOKEN>" # Replace with your PAT
REPO="JosephGazafy/Atmos"

echo -e "\e[1;36m[*] MONITORING GITHUB FORGE FOR v3.1.0-final...\e[0m"

while true; do
    DATA=$(curl -s -H "Authorization: token $TOKEN" \
        "https://api.github.com/repos/$REPO/actions/runs?per_page=1")
    STATUS=$(echo $DATA | jq -r '.workflow_runs[0].status')
    CONCLUSION=$(echo $DATA | jq -r '.workflow_runs[0].conclusion')

    if [ "$STATUS" == "completed" ] && [ "$CONCLUSION" == "success" ]; then
        echo -e "\e[1;32m[✅] FORGE COMPLETE. APK IS READY.\e[0m"
        termux-vibrate -d 1000
        termux-tts-speak "Atmos Forge complete. Sovereign binary ready."
        termux-open-url "https://github.com/$REPO/releases/latest"
        break
    elif [ "$CONCLUSION" == "failure" ]; then
        echo -e "\e[1;31m[❌] FORGE FAILED. Check JNI/NDK logs.\e[0m"
        break
    fi
    echo -ne "\e[1;33m[*] Building... ($STATUS)\r\e[0m"
    sleep 30
done
