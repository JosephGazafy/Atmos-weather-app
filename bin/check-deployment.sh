#!/bin/bash
echo ">> ATMOS: MONITORING GITHUB PAGES BUILD STATUS..."
while true; do
  STATUS=$(curl -s -H "Authorization: token $ATMOS_TOKEN" \
    https://api.github.com/repos/JosephGazafy/Atmos-weather-app/pages/builds/latest | \
    grep -o '"status": "[^"]*"' | head -1)
  
  if [[ "$STATUS" == *"built"* ]]; then
    echo -e "\033[1;32m>> DEPLOYMENT SUCCESSFUL: THE DASHBOARD IS LIVE. <<\033[0m"
    termux-tts-speak "Joseph. The Atmos dashboard is live. Sovereignty has been broadcast."
    break
  else
    echo ">> STATUS: $STATUS ... Retrying in 10s"
    sleep 10
  fi
done
