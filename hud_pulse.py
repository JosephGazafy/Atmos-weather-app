import time, sys, random
def animate_jitter():
    bars = [" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]
    sys.stdout.write("\033[?25l") 
    print("\n\n\n") 
    try:
        while True:
            jitter = "".join(random.choice(bars) for _ in range(12))
            # LINE 1: FIELD (Fleet Drift)
            sys.stdout.write("\033[4A")
            sys.stdout.write(f"\r  \033[1;36m📡 FLEET:\033[0m    [NODES: 11] [DRIFT: 0.00] \033[K\n")
            # LINE 2: ACUITY (Efficiency)
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [η: 0.98] [M_t: LOW-PASS] \033[K\n")
            # LINE 3: BLOWBACK (OIDC Security)
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [RST: ARMED] [OIDC: SECURE] \033[K\n")
            # LINE 4: JOULE (Energy)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [r:0.00] \033[K\n")
            sys.stdout.flush()
            time.sleep(0.08)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")
if __name__ == "__main__":
    animate_jitter()
