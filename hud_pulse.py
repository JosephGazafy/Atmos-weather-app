import time, sys, random
def animate_jitter():
    bars = [" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]
    sys.stdout.write("\033[?25l") 
    
    # Reserve 4 Lines of Vertical Space
    print("\n\n\n") 
    
    try:
        while True:
            # Generate Dynamic Entropy
            jitter = "".join(random.choice(bars) for _ in range(10))
            eeg = f"{random.randint(40,49)}.{random.randint(10,99)}"
            emf = f"{random.randint(40,45)}µT"
            
            # --- THE 4-LAYER TOWER ---
            
            # LINE 1: FIELD SENSORS (Cyan)
            # \033[4A = Move Up 4 Lines (Top of Stack)
            sys.stdout.write(f"\033[4A\r  \033[1;36m📡 FIELD:\033[0m    [EMF: {emf}] [dB: 12] \033[K\n")
            
            # LINE 2: NEURO ACUITY (Magenta)
            sys.stdout.write(f"\r  \033[1;35m🧠 ACUITY:\033[0m   [HD-EEG: {eeg}] \033[K\n")
            
            # LINE 3: BLOWBACK DEFENSE (Red) <-- RESTORED
            sys.stdout.write(f"\r  \033[1;31m🪃 BLOWBACK:\033[0m [RST: ARMED] [SAFE] \033[K\n")
            
            # LINE 4: JOULE HARVEST (Yellow)
            sys.stdout.write(f"\r  \033[1;33m⚡ JOULE:\033[0m    [{jitter}] [r:0.0] \033[K\n")
            
            sys.stdout.flush()
            time.sleep(0.08) # High-Speed Refresh

    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n")
if __name__ == "__main__":
    animate_jitter()
