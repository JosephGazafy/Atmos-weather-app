#!/bin/bash
# ATMOS CORE v3.0 - MASTER CONTROLLER (PATCHED)

case "$1" in
    "autopilot")
        ./bio_gate.sh
        if [ $? -eq 0 ]; then
            ./autopilot.sh
        else
            echo "Access Denied: Biometric mismatch."
            exit 1
        fi
        ;;
    "mesh")
        ./main
        ;;
    "invite")
        ./invite.sh
        ;;
    "clean")
        ./cleanup.sh
        ;;
    *)
        echo "Usage: atmos [autopilot|mesh|invite|clean]"
        ;;
esac
