#!/bin/bash
VALUATION=$(grep "TOTAL PRINCIPAL" ~/Atmos-Engine/telemetry/raw/manifest.txt | cut -d' ' -f3)
ESTATE=$(grep "ESTATE (IND.)" ~/Atmos-Engine/telemetry/raw/equity_ledger.txt | cut -d' ' -f3)

cat <<EOT > ~/.shortcuts/0_Atmos_Sovereign.sh
termux-dialog radio -t "🏛️ ATMOS: $VALUATION" \
-v "📊 [ESTATE] $ESTATE,🛡️ [SENTINEL] Active,🛰️ [PARITY] 137%,🧬 [AUDIT] 99.64%,🔄 [SYNC] Global Push"
EOT
chmod 700 ~/.shortcuts/0_Atmos_Sovereign.sh
