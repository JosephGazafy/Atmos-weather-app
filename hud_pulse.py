import time, sys, random

def animate_jitter():
    # 1. DRASTICALLY REDUCED WIDTH (15 Chars)
    # This guarantees it won't hit the edge of a phone screen and wrap.
    bars = [" ", "▂", "▃", "▄", "▅", "▆", "▇", "█"]
    
    # Hide Cursor
    sys.stdout.write("\033[?25l")
    sys.stdout.flush()
    
    try:
        while True:
            # Short entropy string
            jitter = "".join(random.choice(bars) for _ in range(15))
            
            # THE LOGIC: 
            # \r      = Go to start of line
            # \033[K  = Clear everything after the cursor
            # NO newlines (\n) anywhere in the loop.
            output = f"\r  \033[1;33m🌀 SIGMA:\033[0m [{jitter}] \033[K"
            
            sys.stdout.write(output)
            sys.stdout.flush()
            
            time.sleep(0.1)

    except KeyboardInterrupt:
        sys.stdout.write("\033[?25h\n") # Restore Cursor
        sys.stdout.flush()

if __name__ == "__main__":
    animate_jitter()
