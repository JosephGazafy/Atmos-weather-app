#!/bin/bash
echo "🔄 Restoring Original Autopilot Logic..."
while true; do
    # Run the physics engine (Original Function)
    PYTHONPATH=src python -m atmos.main -a 2000 -j
    # Run the doctor check (Investigation Function)
    python bin/atmos_doctor.py
    sleep 3600
done

