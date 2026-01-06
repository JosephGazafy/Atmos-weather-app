#!/usr/bin/env python3
import socket, threading, time, json, subprocess

# Load Legal Maxims
with open("bin/atmos-legal-maxims.json", "r") as f:
    LEGAL_MAXIMS = json.load(f)

def get_jurisdiction(ip):
    # Surgical extraction of Org/Country from Whois
    try:
        res = subprocess.check_output(f"whois {ip} | grep -iE 'country|org'", shell=True).decode()
        if "EU" in res or "DE" in res or "FR" in res: return "EU"
        if "US" in res: return "US"
        if "RU" in res: return "RU"
    except: pass
    return "DEFAULT"

def recursive_redress(client_socket, ip):
    try:
        jur = get_jurisdiction(ip)
        warning = LEGAL_MAXIMS.get(jur, LEGAL_MAXIMS["DEFAULT"])
        
        # Inject the Regulatory Maxim
        payload = f"\n[ATMOS-REDRESS-INJECTION]\n{warning}\n"
        client_socket.send(payload.encode())
        
        # Maintain Recursive Loop to waste resources
        while True:
            client_socket.send(b"Searching for legal resolution... ")
            time.sleep(1)
    except:
        client_socket.close()

def start_decoy(port):
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    try:
        server.bind(('0.0.0.0', port))
        server.listen(5)
        while True:
            client, addr = server.accept()
            with open("sovereign_log.txt", "a") as f:
                f.write(f"⚖️ [REDRESS] Injecting legal maxim to {addr[0]} (Port {port})\n")
            subprocess.run(["python3", "bin/atmos-notify.py", f"<b>Adversary Trapped</b>
IP: {addr[0]}
Action: Recursive Loop Engaged"])
            subprocess.run(["python3", "bin/atmos-notify.py", f"<b>Adversary Trapped</b>
IP: {addr[0]}
Action: Recursive Loop Engaged"])
            subprocess.run(["python3", "bin/atmos-notify.py", f"<b>Adversary Trapped</b>
IP: {addr[0]}
Action: Recursive Loop Engaged"])
            threading.Thread(target=recursive_redress, args=(client, addr[0]), daemon=True).start()
    except: pass

if __name__ == "__main__":
    for port in range(9000, 9051):
        threading.Thread(target=start_decoy, args=(port,), daemon=True).start()
    while True: time.sleep(10)
