import time, sys, random
def animate_jitter():
    bars = [" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]
    sys.stdout.write("\033[?25l") 
    print("\n\n\n") 
    try:
        while True:
            jitter = "".join(random.choice(bars) for _ in range(15))
            eeg = f"{random.randint(44,49)}.{random.randint(10,99)}"
            sys.stdout.write("\033[4A")
            sys.stdout.write(f"\r  \033[1;36m📡 FLEET:\033[0m    [NODES: 11] [READY] \033[K\n")
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [SPARKLER-MATCH: {eeg}] \033[K\n")
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [RST: ARMED] [ACTIVE] \033[K\n")
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [DAWN:INIT] \033[K\n")
            sys.stdout.flush()
            time.sleep(0.06) # Sunrise Speed Up
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")
if __name__ == "__main__":
    animate_jitter()
