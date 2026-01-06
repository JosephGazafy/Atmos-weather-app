#!/bin/bash
# ATMOS-HYDROGEN AUDIO TRIGGER

TYPE=$1
case $TYPE in
  "SENTRY")  play -q -n synth 0.1 sine 100 bass 20  ;; # Kick Drum (Low C)
  "LOGIC")   play -q -n synth 0.05 noise amod 100   ;; # Closed Hi-Hat
  "SWARM")   play -q -n synth 0.2 square 440 fade 0.1 ;; # Snare Hit
  "CORE")    play -q -n synth 0.5 sine 880 pulse 0.1  ;; # Crash Cymbal
esac
