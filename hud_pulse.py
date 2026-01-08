import time, sys, random
def animate_jitter():
    bars = [" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]
    sys.stdout.write("\033[?25l")
    print("\n") # Reserve space
    try:
        while True:
            jitter = "".join(random.choice(bars) for _ in range(15))
            # LINE 1: FIELD (Cyan) - Vertical Lock
            sys.stdout.write(f"\033[2A\r  \033[1;36m📡 FIELD:\033[0m   [EMF: 45µT] [dB: 12]   \033[K\n")
            # LINE 2: SCALE (Yellow)
            sys.stdout.write(f"\r  \033[1;33m⚡ SCALE:\033[0m   [{jitter}] \033[K\n")
            sys.stdout.flush()
            time.sleep(0.1)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")
if __name__ == "__main__":
    animate_jitter()
