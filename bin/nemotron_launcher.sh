#!/bin/bash
# ATMOS NEURAL-LAUNCHER (v53.5)
while true; do
    python3 ~/Atmos-Engine/bin/nemotron_ear.py >> ~/Atmos-Engine/vault/neural_debug.log 2>&1
    echo "Neural Agent crashed. Re-centering in 5 seconds..."
    sleep 5
done
