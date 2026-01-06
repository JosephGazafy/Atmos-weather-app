#!/bin/bash
# 1. Start the Flask Logic Engine
nohup python atmos_api.py > /dev/null 2>&1 &
# 2. Start the Go High-Speed Bridge
nohup go run main.go > /dev/null 2>&1 &
# 3. Start the Watchdog Health Check
nohup ./health_check.sh > /dev/null 2>&1 &

echo "Constitutional Stack is initializing..."
termux-notification -t "Atmos System" -c "All gates and bridges are active."
