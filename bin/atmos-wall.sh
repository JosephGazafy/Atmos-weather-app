#!/bin/bash
echo "🧱 [WALL] Raising the Aegis Wall..."
# Termux simulated firewall (requires root for iptables, otherwise uses route-reject)
if command -v iptables &> /dev/null; then
  iptables -P INPUT DROP
  iptables -A INPUT -m state --state ESTABLISHED,RELATED -j ACCEPT
  echo "✅ IPTABLES: Total Lockdown Active."
else
  echo "⚠️ Non-root: Using internal logic-gate blocking."
  # Updates the internal Atmos-Decoys to reject all new handshakes
  touch .wall_active
fi
