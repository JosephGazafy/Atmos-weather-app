import time, sys, random
def animate_jitter():
    bars = [" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]
    sys.stdout.write("\033[?25l") 
    print("\n\n\n") 
    try:
        while True:
            jitter = "".join(random.choice(bars) for _ in range(12))
            eeg = f"{random.randint(44,49)}.{random.randint(10,99)}"
            # --- THE V433.0 PROSOCIAL TOWER ---
            sys.stdout.write("\033[4A")
            # Layer 1: FIELD (Social Context)
            sys.stdout.write(f"\r  \033[1;36m📡 FIELD:\033[0m    [CONTEXT: SECURE] [dB: 12] \033[K\n")
            # Layer 2: ACUITY (Executive Inhibition)
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [PFC-INHIBIT: {eeg}] [MIRROR: ON] \033[K\n")
            # Layer 3: BLOWBACK (Linguistic RST)
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [PEJORATIVE: NULL] [SAFE] \033[K\n")
            # Layer 4: JOULE (Prosocial Energy)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [r:0.00] \033[K\n")
            sys.stdout.flush()
            time.sleep(0.08)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")
if __name__ == "__main__":
    animate_jitter()
