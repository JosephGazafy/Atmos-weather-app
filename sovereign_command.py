import os, sys
class SovereignCore:
    def __init__(self):
        self.principal = "65,737.61"
        self.sigma = "300.00"
    def render_hud(self):
        print(f"\033[1;32m⚖️ [STATUS] SIGMA: {self.sigma} (MAX) | MODE: KINETIC-ACTIVE\033[0m")
        print(f"\033[1;32m🏰 [ANCHOR] EQUITY: ${self.principal} [IMMUTABLE]\033[0m")
if __name__ == "__main__":
    core = SovereignCore()
    core.render_hud()
