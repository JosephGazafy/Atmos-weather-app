#!/bin/bash
# ATMOS-ATLAS v14.1 PERSISTENCE

# Standard Engine Startup
pgrep -f "Atmos/main.py" > /dev/null || nohup python3 ~/Atmos/main.py > /dev/null 2>&1 &
pgrep -f "atmos-beacon-swarm.py" > /dev/null || nohup python3 bin/atmos-beacon-swarm.py > /dev/null 2>&1 &

# PRE-EMPTIVE DECOY TRIGGER
# Check Prediction Probability
PROB=$(python3 bin/atmos-predictor.py | grep -oP 'Prob: \K[0-9]+')
if [ "$PROB" -gt 70 ]; then
    echo "🚨 [PRE-EMPTIVE] High Siege Probability detected. Deploying Phantoms..."
    pgrep -f "atmos-decoys.py" > /dev/null || nohup python3 bin/atmos-decoys.py > /dev/null 2>&1 &
fi
