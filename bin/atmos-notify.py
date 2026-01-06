import sys, requests, datetime

TOKEN = "8568251192:AAEMBNZrAcyq_BpVgX-ribE7bnNmy8aFwaE"
CHAT_ID = "8200573061"

def send_message(text):
    # Adding Visual 'Attack' with Emojis and Formatting
    timestamp = datetime.datetime.now().strftime('%H:%M:%S')
    header = "<b>🚨🚨🚨 [ATMOS-REFLEX-TRIGGER] 🚨🚨🚨</b>\n"
    footer = f"\n\n🕒 <i>LOGGED AT: {timestamp} CST</i>"
    
    full_msg = header + text + footer
    
    url = f"https://api.telegram.org/bot{TOKEN}/sendMessage"
    payload = {"chat_id": CHAT_ID, "text": full_msg, "parse_mode": "HTML"}
    try:
        requests.post(url, json=payload)
    except Exception as e:
        print(f"Bypass Error: {e}")

if __name__ == "__main__":
    if len(sys.argv) > 1:
        msg = " ".join(sys.argv[1:])
        send_message(msg)
