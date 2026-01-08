import os, sys

class SovereignCore:
    def __init__(self):
        self.principal = "65,737.61"
        self.sigma = "300.00"
        self.mode = "PPM-REIFIED" # Predictive Process Monitoring Enabled

    def render_hud(self):
        # FIXED: Clean sequential calls without escaped newlines
        print(f"\033[1;32m⚖️ [SIGMA] {self.sigma} | MODE: {self.mode}\033[0m")
        print(f"\033[1;32m🏰 [EQUITY] ${self.principal} [LOCKED]\033[0m")

if __name__ == "__main__":
    core = SovereignCore()
    core.render_hud()
