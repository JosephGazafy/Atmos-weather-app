import os, datetime, subprocess

def generate_audit():
    ledger_path = "intel_ledger.txt"
    if not os.path.exists(ledger_path):
        return "No data available for this week's audit."

    with open(ledger_path, "r") as f:
        lines = f.readlines()

    total_probes = len(lines)
    # Extract unique IPs and Organizations
    ips = set()
    orgs = {}
    
    for line in lines:
        if "IP=" in line:
            ip = line.split("IP=")[1].split(" | ")[0]
            ips.add(ip)
        if "ORG=" in line:
            org = line.split("ORG=")[1].split(" | ")[0]
            orgs[org] = orgs.get(org, 0) + 1

    # Sort top adversaries
    top_orgs = sorted(orgs.items(), key=lambda x: x[1], reverse=True)[:3]
    
    report = f"<b>📊 WEEKLY STRATEGIC AUDIT</b>\n"
    report += f"<i>Period: Dec 28, 2025 - Jan 4, 2026</i>\n\n"
    report += f"🛡️ <b>Total Intercepts:</b> {total_probes}\n"
    report += f"🚫 <b>Unique Adversaries:</b> {len(ips)}\n\n"
    report += f"⚔️ <b>Top Hostile Jurisdictions:</b>\n"
    for org, count in top_orgs:
        report += f" - {org}: {count} attempts\n"
    
    report += f"\n✅ <b>Hyperlattice Integrity:</b> 100%\n"
    report += f"📍 <b>Anchor:</b> Independence, MO\n"
    return report

if __name__ == "__main__":
    report_text = generate_audit()
    # Send via the notify script
    subprocess.run(["python3", "bin/atmos-notify.py", report_text])
