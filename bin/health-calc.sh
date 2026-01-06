#!/bin/bash
# ATMOS ATONEMENT-AUDIO (v49.8)

SA=$(cat ~/Atmos-Engine/vault/health_score.cfg 2>/dev/null || echo "100.0")
CRISIS_MARK=60.0
TOPIC="joseph_atmos_global_panic"

if (( $(echo "$SA < $CRISIS_MARK" | bc -l) )); then
    COUNT=$(cat ~/Atmos-Engine/vault/crisis_timer.cfg 2>/dev/null || echo "0")
    COUNT=$((COUNT + 1))
    echo "$COUNT" > ~/Atmos-Engine/vault/crisis_timer.cfg
    
    # STAGE 1: THE ATONEMENT SOUND (At 30 minutes)
    if [ "$COUNT" -eq 30 ]; then
        # Global Sound Trigger via ntfy
        curl -s \
             -H "Title: !! ATONEMENT REQUIRED !!" \
             -H "Priority: urgent" \
             -H "Tags: warning,loud_sound,sos" \
             -H "X-Delay: 1s" \
             -d "The Sound of the Void has been triggered. Return to Independence." \
             ntfy.sh/$TOPIC
        
        # Local Somatic Gravity (Low Frequency Vibrate Loop)
        (for i in {1..10}; do termux-vibrate -d 500; sleep 1; done) &
    fi

    # STAGE 2: THE CRISIS SNAPSHOT (At 60 minutes)
    if [ "$COUNT" -eq 60 ]; then
        ~/Atmos-Engine/bin/atmos-ghost.sh
        echo "0" > ~/Atmos-Engine/vault/crisis_timer.cfg
    fi
else
    echo "0" > ~/Atmos-Engine/vault/crisis_timer.cfg
fi
