#!/bin/bash
# ATMOS-ATLAS SOVEREIGN LAUNCHER

# Ensure the Headless Engine is running in the background
if ! pgrep -f "Atmos/main.py" > /dev/null; then
    nohup python3 ~/Atmos/main.py > /dev/null 2>&1 &
    sleep 1
fi

# Ensure the Swarm is broadcasting the Anthem
if ! pgrep -f "atmos-beacon-swarm.py" > /dev/null; then
    nohup python3 bin/atmos-beacon-swarm.py > /dev/null 2>&1 &
fi

# Launch the Visual HUD in the foreground
python3 bin/atmos-master-hud.py
