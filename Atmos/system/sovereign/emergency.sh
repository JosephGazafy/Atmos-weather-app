#!/bin/bash
# ATMOS CORE v3.0 - EMERGENCY BYPASS PROTOCOL
./logo.sh

echo -e "\e[1;33m⚠️ WARNING: BIOMETRIC GATE BYPASSED.\e[0m"
echo -e "\e[1;34m[SYSTEM IN OVERRIDE MODE]\e[0m"

# Directly trigger the autopilot logic
./autopilot.sh

