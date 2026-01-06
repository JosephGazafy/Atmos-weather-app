#!/bin/bash
PROJECT_DIR="/data/data/com.termux/files/home/Atmos/Atmos/Atmos"
FILES=("gui.py" "health.sh" "data.json" "Makefile" "bin/atmos_doctor.py")

echo "🛠️  Starting Constitutional Auto-Repair..."

for file in "${FILES[@]}"; do
    if [ ! -f "$PROJECT_DIR/$file" ]; then
        echo "⚠️  Missing: $file | Restoring from Sovereign Blueprint..."
        git checkout HEAD -- "$file"
    else
        echo "✅ Verified: $file"
    fi
done

# Repair Symbolic Links for Legacy Paths
ln -sf "$PROJECT_DIR/autopilot.sh" /data/data/com.termux/files/home/Atmos/autopilot.sh
ln -sf "$PROJECT_DIR/health.sh" /data/data/com.termux/files/home/Atmos/start_stack.sh

echo "✨ System Restored to Sovereign Baseline."

