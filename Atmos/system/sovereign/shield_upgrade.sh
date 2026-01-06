#!/bin/bash
# ATMOS CORE v3.1 - ADAPTIVE ACOUSTIC SHIELD
# Rapidly varies the ultrasonic floor between 19.2kHz and 19.8kHz
while true; do
  FREQ=$(shuf -i 19200-19800 -n 1)
  play -n -q synth 10 sine $FREQ vol 0.8 &
  SLEEP_PID=$!
  sleep 10
  kill $SLEEP_PID 2>/dev/null
done


