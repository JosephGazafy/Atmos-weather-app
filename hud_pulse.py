import time, sys, random
def animate_jitter():
    bars = [" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]
    sys.stdout.write("\033[?25l") 
    print("\n\n\n") 
    try:
        while True:
            jitter = "".join(random.choice(bars) for _ in range(12))
            # Pattern matching simulation (Sparkler Logic)
            eeg = f"{random.randint(40,49)}.{random.randint(10,99)}"
            
            # --- THE V428.0 SPARKLER STACK ---
            sys.stdout.write("\033[4A")
            # Layer 1: FIELD (Cyan)
            sys.stdout.write(f"\r  \033[1;36m📡 FIELD:\033[0m    [MATCH: NOMINAL] [dB: 12] \033[K\n")
            # Layer 2: ACUITY (Magenta) - Reflecting Pattern Matching
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [SPARKLER-MATCH: {eeg}] \033[K\n")
            # Layer 3: BLOWBACK (Red)
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [RST: ARMED] [PATTERN: SAFE] \033[K\n")
            # Layer 4: JOULE (Yellow)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [r:0.0] \033[K\n")
            
            sys.stdout.flush()
            time.sleep(0.08)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")
if __name__ == "__main__":
    animate_jitter()
