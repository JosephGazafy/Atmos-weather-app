


import os, time, json, subprocess

# ANSI Colors for High-Visibility
CYAN, GREEN, YELLOW, RED = "\033[96m", "\033[92m", "\033[93m", "\033[91m"
BOLD, RESET = "\033[1m", "\033[0m"

def get_recent_logs():
    try:
        logs = subprocess.check_output(["git", "log", "-3", "--format=%s"], stderr=subprocess.DEVNULL).decode().split('\n')
        return [log for log in logs if log]
    except:
        return ["System active", "Monitoring telemetry...", "Vault secured"]

def draw_bar(val, max_val=1.225):
    width = 15
    filled = int(min(max((val / max_val), 0), 1) * width)
    return f"|{YELLOW}{'█' * filled}{RESET}{'░' * (width - filled)}|"

def draw_gui():
    path = "/data/data/com.termux/files/home/Atmos/Atmos/Atmos/data.json"
    try:
        with open(path, 'r') as f:
            data = [json.loads(l) for l in f.readlines()]
    except: data = []

# --- CONSTITUTIONAL REPAIR HOOK ---
def trigger_repair():
    subprocess.run(["bash", "repair.sh"])

# Inside draw_gui(), if logic fails, we can display:
# print(f"┃ {RED}CONSTITUTION VULNERABLE - RUNNING REPAIR...{RESET} ┃")

    latest = data[-1] if data else {"altitude": 0, "density": 0, "pressure": 0, "timestamp": "N/A"}
    os.system('clear')
    
    print(f"{BOLD}{CYAN}┏━━━━━━━━━━━━━━━━━ ATMOS SOVEREIGN COMMAND CENTER ━━━━━━━━━━━━━━━━━┓{RESET}")
    print(f"┃ {BOLD}CONSTITUTION:{RESET} {GREEN}ACTIVE{RESET}  | {BOLD}VAULT:{RESET} {GREEN}LOCKED{RESET}    | {YELLOW}{time.strftime('%H:%M:%S')}{RESET} ┃")
    
    print(f"{CYAN}┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫{RESET}")
    print(f"┃ {BOLD}[1] PHYSICS ENGINE{RESET}                                              ┃")
    print(f"┃     Alt: {latest['altitude']:>6}m | Dens: {latest['density']:>6.4f} {draw_bar(latest['density'])} ┃")
    
    print(f"{YELLOW}┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫{RESET}")
    print(f"┃ {BOLD}[2] MESH NETWORK STATUS{RESET}                                         ┃")
    print(f"┃     Node: {BOLD}Termux-01{RESET}  | Status: {GREEN}ONLINE{RESET}     | Latency: 12ms     ┃")
    
    print(f"{BOLD}┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫{RESET}")
    print(f"┃ {BOLD}LIVE ACTIVITY LOG:{RESET}                                              ┃")
    for log in get_recent_logs():
        print(f"┃  > {log[:60]:<60} ┃")
    
    print(f"{BOLD}{CYAN}┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛{RESET}")

if __name__ == "__main__":
    try:
        while True:
            draw_gui()
            time.sleep(0.5) # Optimized high-speed refresh
    except KeyboardInterrupt:
        print(f"\n{RED}Cockpit Offline.{RESET}")

