#!/bin/bash
# ATMOS-ATLAS FILTERED VOICE COMMAND (v16.1)

echo "🎙️ [VOICE] Sovereign Listener Active. Filtering Signal Noise..."

while true; do
    # Capture the output
    RAW_COMMAND=$(termux-speech-to-text 2>/dev/null)
    
    # Filter out the "Google Play Issue 29" noise
    if [[ "$RAW_COMMAND" == *"github.com"* ]]; then
        # Silent ignore of the API error to keep logs clean
        continue
    fi

    COMMAND=$(echo "$RAW_COMMAND" | tr '[:upper:]' '[:lower:]')

    if [[ -n "$COMMAND" ]]; then
        echo "🗣️ [HEARD]: $COMMAND"

        if [[ "$COMMAND" == *"lockdown"* ]]; then
            termux-tts-speak "Acknowledged. Initiating port rotation and kinetic block."
            bash bin/atmos-lockdown.sh
        elif [[ "$COMMAND" == *"status"* ]] || [[ "$COMMAND" == *"report"* ]]; then
            termux-tts-speak "Analyzing hyperlattice. Generating tactical debrief."
            python3 bin/atmos-debrief.py
        elif [[ "$COMMAND" == *"shutdown"* ]] || [[ "$COMMAND" == *"stop"* ]]; then
            termux-tts-speak "Atmos Engine entering hibernation mode."
            pkill -f atmos
            break
        fi
    fi
    sleep 1
done
