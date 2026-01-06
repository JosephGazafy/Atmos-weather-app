#!/bin/bash
# ATMOS HALL OF FAME DASHBOARD (v49.2)
DASH_FILE=~/Atmos-Engine/dashboard.html
HEALTH_LOCAL=$(cat ~/Atmos-Engine/vault/health_score.cfg 2>/dev/null || echo "100.0")
PRIN="\$65,737.61"

cat << HTML > $DASH_FILE
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="refresh" content="5">
    <title>ATMOS | Hall of Fame</title>
    <style>
        body { background: #000; color: #00ff41; font-family: 'Courier New', monospace; padding: 20px; }
        .container { border: 1px solid #00ff41; padding: 20px; border-radius: 10px; max-width: 500px; margin: auto; background: #050505; }
        .fame-table { width: 100%; font-size: 0.7rem; border-collapse: collapse; margin-top: 15px; }
        .fame-table th, .fame-table td { border: 1px solid #222; padding: 8px; text-align: left; }
        .fame-table th { background: #111; color: #fff; }
        .header { font-size: 1.2rem; font-weight: bold; border-bottom: 2px solid #00ff41; padding-bottom: 10px; }
        .badge { background: #00ff41; color: #000; padding: 2px 5px; border-radius: 3px; font-weight: bold; }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">🏛️ LATTICE HALL OF FAME</div>
        <p style="font-size: 0.8rem;">Sovereign: Joseph | Node: Independence, MO</p>
        
        <table class="fame-table">
            <tr><th>EPOCH</th><th>MILESTONE</th><th>STATUS</th></tr>
            $(grep "MILESTONE" ~/Atmos-Engine/vault/resurrection.ledger | tail -n 5 | awk -F'|' '{print "<tr><td>"$1"</td><td>"$2"</td><td><span class=\"badge\">TRANSCEEDED</span></td></tr>"}')
        </table>

        <div style="margin-top: 20px; padding-top: 15px; border-top: 1px solid #222;">
            <div style="font-size: 1.3rem; color: #fff;">PRINCIPAL: $PRIN</div>
            <div style="font-size: 0.6rem; color: #444; margin-top: 5px;">v49.2 | MONUMENTAL-REIFIED</div>
        </div>
    </div>
</body>
</html>
HTML
echo -e "\033[1;32m>> HALL OF FAME REIFIED <<\033[0m"
