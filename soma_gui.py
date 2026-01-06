import os
import time
import json
import datetime
import sys

# Theme Definitions (ANSI)
THEMES = {
    "blue":  {"edge": "\033[1;36m", "label": "\033[1;34m", "val": "\033[0;36m"}, # Cosmological
    "amber": {"edge": "\033[1;33m", "label": "\033[0;33m", "val": "\033[1;33m"}, # Quantum
    "red":   {"edge": "\033[1;31m", "label": "\033[0;31m", "val": "\033[1;31m"}  # Relativistic
}

def get_data():
    try:
        with open(os.path.expanduser('~/physics_chronicle/atmos_physics_update.json'), 'r') as f:
            return json.load(f)
    except: return None

def render(theme_name="blue"):
    theme = THEMES.get(theme_name, THEMES["blue"])
    while True:
        data = get_data()
        os.system('clear')
        now = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        
        E, L, V = theme["edge"], theme["label"], theme["val"]
        
        print(f"{E}╔══════════════════════════════════════════════════════════╗\033[0m")
        print(f"{E}║  SOMA ENGINE: {theme_name.upper()} STRATA MONITOR      {now}  ║\033[0m")
        print(f"{E}╠══════════════════════════════════════════════════════════╣\033[0m")
        
        if data:
            consts = data['fundamental_constants']
            print(f"  {L}PRINCIPAL:{V} $65,737.61          {L}STATUS:{V} ZERO-DRIFT")
            print(f"  {L}LOCATION:{V}  Independence, MO   {L}STRATA:{V} STERILE")
            print(f"{E}╟──────────────────────────────────────────────────────────╢\033[0m")
            print(f"  [01] Schrodinger (hbar): {consts['schrodinger']['hbar']}")
            print(f"  [03] Einstein    (G)   : {consts['einstein']['G']}")
            print(f"  [03] Einstein    (c)   : {consts['einstein']['c']}")
            print(f"  [05] Planck      (k_b) : {consts['planck']['k_b']}")
            print(f"{E}╟──────────────────────────────────────────────────────────╢\033[0m")
            
            start = datetime.datetime(1, 1, 1)
            delta = datetime.datetime.now() - start
            print(f"  {L}DEEP-TIME EXPANSION:{V} {delta.total_seconds() * 299792458:.4e} m")
        else:
            print("  \033[1;31mERROR: DATA VAULT OFFLINE. RUN 'chronos'\033[0m")
            
        print(f"{E}╚══════════════════════════════════════════════════════════╝\033[0m")
        time.sleep(2)

if __name__ == "__main__":
    t = sys.argv[1] if len(sys.argv) > 1 else "blue"
    try: render(t)
    except KeyboardInterrupt: pass
