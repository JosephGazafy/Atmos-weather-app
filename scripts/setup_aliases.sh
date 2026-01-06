#!/bin/bash
# 🏛️ ATMOS v126.0: MASTER ALIAS DEPLOYMENT

TARGET_FILE="$HOME/.bashrc"
[ -f "$HOME/.bash_profile" ] && TARGET_FILE="$HOME/.bash_profile"

echo "⚙️  RE-ANCHORING FORENSIC ALIASES TO $TARGET_FILE..."

# 1. Clean legacy aliases to prevent 'pathway drift'
sed -i '/# ATMOS CORE/d' "$TARGET_FILE"
sed -i '/alias atmos=/d' "$TARGET_FILE"
sed -i '/alias bedrock=/d' "$TARGET_FILE"
sed -i '/alias sync=/d' "$TARGET_FILE"

# 2. Inject high-density command deck
cat <<EOT >> "$TARGET_FILE"
# ATMOS CORE v126.0
alias atmos='sh \$HOME/bin/atmos_hud.sh'
alias bedrock='cat \$HOME/Atmos-Engine/telemetry/raw/manifest.txt && echo "" && cat \$HOME/Atmos-Engine/telemetry/raw/equity_ledger.txt'
alias sync='cd \$HOME/Atmos-Engine && git pull origin Master && git push origin Master'
EOT

# 3. Finalize environment
source "$TARGET_FILE"
echo "✅ ALIASES LOCKED: [atmos, bedrock, sync] ARE NOW VIVUS."
