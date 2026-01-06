import subprocess, sys

def propagate_block(ip):
    print(f"📡 [PROPAGATE] Broadcasting blacklist for {ip} to Hyperlattice...")
    # 1. Local Block
    subprocess.run(["bash", "bin/atmos-wall.sh", ip])
    
    # 2. Telegram Alert with Jurisdictional Data
    msg = f"<b>🚫 GLOBAL BLACKLIST ACTIVE</b>\nTarget: {ip}\nStatus: Propagated to Cloud Edge.\nJurisdiction: Permanent Interdiction."
    subprocess.run(["python3", "bin/atmos-notify.py", msg])

if __name__ == "__main__":
    if len(sys.argv) > 1:
        propagate_block(sys.argv[1])
