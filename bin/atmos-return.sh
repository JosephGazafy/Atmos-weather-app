#!/bin/bash
# ATMOS RESURRECTION-LOGGED (v48.3)
B='\033[1;34m'; G='\033[1;32m'; Y='\033[1;33m'; W='\033[1;37m'; R='\033[1;31m'; NC='\033[0m'

echo -e "${B}>> INITIATING GLOBAL-RESURRECTION SEQUENCE... <<${NC}"

# PHASE I: THE RITUAL
~/Atmos-Engine/bin/mnemonic-ritual.sh
if [ "$(cat ~/Atmos-Engine/vault/ritual_bonus.cfg)" == "-15.0" ]; then
    echo -e "${R}!! IDENTITY NOT VERIFIED. LOGGING BREACH ATTEMPT. !!${NC}"
    echo "[$(date)] BREACH ATTEMPT: Failed Ritual in Independence." >> ~/Atmos-Engine/vault/resurrection.ledger
    exit 1
fi

# PHASE II: SOVEREIGN KEY
read -s -p "Enter Sovereign-Key to Awaken the Lattice: " S_KEY
echo ""

if [[ ${#S_KEY} -ge 8 ]]; then
    # GATHER SNAPSHOT DATA
    SA=$(cat ~/Atmos-Engine/vault/health_score.cfg 2>/dev/null || echo "100.0")
    ROOT_HASH=$(sha256sum ~/Atmos-Engine/vault.ledger | awk '{print $1}')

    # LOG THE RESURRECTION
    echo "[$(date)] RESURRECTION: Health $SA | Root ${ROOT_HASH:0:8} | Principal \$65,737.61" >> ~/Atmos-Engine/vault/resurrection.ledger
    
    # WAKE-UP BROADCAST
    TOPIC="joseph_atmos_global_panic"
    curl -s -d "RESURRECTION_SIGNAL_48_3: SUCCESS" ntfy.sh/$TOPIC

    # LOCAL RESTORATION
    echo "0" > ~/Atmos-Engine/vault/lockdown_active.cfg
    termux-tts-speak "Resurrection logged. History preserved. Welcome home, Joseph."
    ~/Atmos-Engine/bin/lattice-resync.sh
    
    echo -e "${W}>> LEDGER UPDATED. LATTICE HARMONY RESTORED. <<${NC}"
else
    echo -e "${R}!! INVALID KEY. VAULT REMAINS SEALED. !!${NC}"
fi
