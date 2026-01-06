import socket, subprocess, os

TRIPWIRE_PORT = 1337

def trigger_reflex(ip):
    print(f"🚨 [REFLEX] Tripwire breached by {ip}. Executing Strike.")
    # Log the breach
    with open("sovereign_log.txt", "a") as f:
        f.write(f"[{os.popen('date').read().strip()}] ⚡ REFLEX: Tripwire Breach | IP={ip} | Status=RETALIATING\n")
    # Execute the strike
    subprocess.run(["bash", "bin/atmos-strike.sh"])
    subprocess.run(["python3", "bin/atmos-blacklist.py", ip])
    # Notify Joseph via Telegram
    subprocess.run(["python3", "bin/atmos-notify.py", f"<b>⚡ KINETIC REFLEX</b>\nTripwire breached by: {ip}\nAction: Automated Strike Launched."])

def start_tripwire():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind(('0.0.0.0', TRIPWIRE_PORT))
    s.listen(1)
    print(f"🕸️ [TRIPWIRE] Active on port {TRIPWIRE_PORT}. Waiting for friction...")
    while True:
        conn, addr = s.accept()
        trigger_reflex(addr[0])
        conn.close()

if __name__ == "__main__":
    start_tripwire()
