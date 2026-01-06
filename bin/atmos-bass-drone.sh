#!/bin/bash
# ATMOS-ATLAS SUBTRACTIVE BASS ENGINE

THREAT=$1
# Calculate Frequency: Starts at 110Hz (A2), drops as threat increases
FREQ=$(python3 -c "print(max(40, 110 - ($THREAT * 5)))")
# Calculate Gain: Increases with threat
GAIN=$(python3 -c "print(min(0, -20 + $THREAT))")

# Generate a 4-second Sawtooth Drone with a Low-Pass sweep
play -q -n synth 4.0 sawtooth $FREQ \
     lowpass 300 \
     tremolo 2 20 \
     vol ${GAIN}dB
