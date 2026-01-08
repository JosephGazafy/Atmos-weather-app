import time, sys, random
def animate_jitter():
    bars = [" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]
    sys.stdout.write("\033[?25l")
    try:
        while True:
            # High-density jitter (increased resolution)
            jitter = "".join(random.choice(bars) for _ in range(25))
            sys.stdout.write(f"\r  \033[1;36m📡 ACUITY:\033[0m [HD-EEG: {random.randint(40,50)}.28uT] \033[1;33m⚡ JOULE:\033[0m [{jitter}] \033[K")
            sys.stdout.flush()
            time.sleep(0.08) # Faster ITR rate
    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")
if __name__ == "__main__":
    animate_jitter()
