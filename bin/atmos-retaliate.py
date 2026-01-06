import sys, os, requests

TOKEN = "8568251192:AAEMBNZrAcyq_BpVgX-ribE7bnNmy8aFwaE"
CHAT_ID = "8200573061"

def get_geo(ip):
    try:
        # Trace the IP origin
        r = requests.get(f"http://ip-api.com/json/{ip}", timeout=5).json()
        if r.get('status') == 'success':
            return f"{r.get('city')}, {r.get('country')}"
        return "Private/Internal IP"
    except:
        return "Trace Failed"

def strike(ip):
    location = get_geo(ip)
    print(f"⚡ [STRIKE] Counter-measures active against: {ip} ({location})")
    
    # Update local ledger
    with open("blacklist.txt", "a") as f:
        f.write(f"{ip} # {location}\n")
    
    # Direct Telegram injection (No shell bypass)
    msg = f"<b>⚠️ [KINETIC-STRIKE]</b>\n<b>Target:</b> {ip}\n<b>Origin:</b> {location}\n<b>Action:</b> BLACKLISTED"
    url = f"https://api.telegram.org/bot{TOKEN}/sendMessage"
    requests.post(url, json={"chat_id": CHAT_ID, "text": msg, "parse_mode": "HTML"})

if __name__ == "__main__":
    if len(sys.argv) > 2 and sys.argv[1] == "--target":
        strike(sys.argv[2])
