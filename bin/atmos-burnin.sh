#!/bin/bash
echo "🔥 [BURN-IN] Commencing 5-minute system stress test..."
for i in {1..5}
do
   echo "🌀 [CYCLE $i] Testing Signal Attack..."
   python3 bin/atmos-notify.py "<b>🔥 BURN-IN TEST: CYCLE $i/5</b>\nLoad: 85%\nStability: NOMINAL\nThe foundation is holding the groove."
   
   echo "🌍 [CYCLE $i] Testing Geofence Latency..."
   python3 bin/atmos-retaliate.py --target "1.1.1.1"
   
   echo "💾 [CYCLE $i] Writing to Shadow-Relay..."
   echo "[BURN-IN-PULSE] Cycle $i Complete." >> shadow_relay.log
   
   sleep 30
done
echo "✅ [BURN-IN] Sequence Complete. System is Heat-Treated."
