#!/bin/bash
# ATMOS CRASH-PROTECTOR ENGINE (v43.6)
NEWS_FILE=~/Atmos-Engine/news_pulse.txt
LOCKED_FILE=~/Atmos-Engine/vault/.lattice_lock

# Simulate market crash check (Logic: Scan for "-10%" or "CRASH")
if grep -qiE "CRASH|-10%|-15%|-20%|PLUMMET" "$NEWS_FILE"; then
    echo -e "\033[1;31m>> MARKET COLLAPSE DETECTED. INITIATING SOVEREIGN STASIS. <<\033[0m"
    # TRIGGER THE v42.8 PANIC-LOCK
    ~/Atmos-Engine/bin/panic-lock.sh
    exit 1
fi

# Baseline Momentum Check (v43.5)
SENTIMENT_SCORE=$(( ( RANDOM % 100 ) ))
if [ $SENTIMENT_SCORE -gt 70 ]; then
    GROWTH=1.15
else
    GROWTH=1.00
fi
echo "$GROWTH" > ~/Atmos-Engine/vault/momentum.cfg
