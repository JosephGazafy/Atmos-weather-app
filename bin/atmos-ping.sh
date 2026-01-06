#!/bin/bash
# ATMOS PROOF-OF-LIFE (v49.4)
TOPIC="joseph_atmos_heartbeat_$(hostname)"

# Transmit "I Am Alive" signal to the Cloud Monitor
# The monitor is configured to alert if silent for 72 hours.
curl -s -H "Title: INDEPENDENCE_NODE_PULSE" \
     -H "Priority: min" \
     -H "Tags: heartbeat" \
     -d "Sovereign $65,737.61: Logic is Active. Hardware is Intact." \
     ntfy.sh/$TOPIC

echo "[$(date)] HEARTBEAT: Proof-of-life transmitted to Cloud-Logic." >> ~/Atmos-Engine/vault/resurrection.ledger
