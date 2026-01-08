import time, sys, random
def animate_jitter():
    bars = [" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]
    sys.stdout.write("\033[?25l") # Hide Cursor
    try:
        while True:
            jitter = "".join(random.choice(bars) for _ in range(12))
            # LINE 1: SENSORS (FUSED)
            sys.stdout.write(f"\r  \033[1;36m📡 SENSORS:\033[0m [EMF: 45uT] [AUDIO: 12dB] \033[K\n")
            # LINE 2: PULSE (SCALED)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m   [{jitter}] \033[K")
            sys.stdout.write(f"\033[1A") # Lock Vertical Position
            sys.stdout.flush()
            time.sleep(0.1)
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n\n")
if __name__ == "__main__":
    animate_jitter()
