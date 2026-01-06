#!/bin/bash
while true; do
    if ! curl -s http://127.0.0.1:5000/stats > /dev/null; then
        echo "[!] Failure detected. Restarting Stack..."
        nohup python atmos_api.py > /dev/null 2>&1 &
        nohup ./bridge > /dev/null 2>&1 &
    fi
    sleep 30
done
