import time, sys, random
def animate_jitter():
    bars = [" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]
    sys.stdout.write("\033[?25l") # Hide Cursor
    
    # Pre-print 3 lines to reserve space
    print("\n\n") 
    
    try:
        while True:
            # Generate Entropy
            jitter = "".join(random.choice(bars) for _ in range(12))
            eeg_val = f"{random.randint(40,49)}.{random.randint(10,99)}"
            emf_val = f"{random.randint(40,45)}uT"
            db_val = f"{random.randint(10,15)}dB"

            # LINE 1: NEURO-ACUITY (Brain/EEG)
            # \033[2A = Move Up 2 Lines
            # \r = Start of line
            sys.stdout.write(f"\033[2A\r  \033[1;36m🧠 ACUITY:\033[0m [HD-EEG: {eeg_val}] \033[K\n")
            
            # LINE 2: FIELD SENSORS (EMF/Audio)
            sys.stdout.write(f"\r  \033[1;34m📡 FIELD:\033[0m  [{emf_val}] [{db_val}] \033[K\n")
            
            # LINE 3: JOULE HARVEST (Energy)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m  [{jitter}] \033[K")
            
            sys.stdout.flush()
            time.sleep(0.1)

    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")

if __name__ == "__main__":
    animate_jitter()
