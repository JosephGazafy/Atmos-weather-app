import os, sys
class SovereignCore:
    def __init__(self):
        self.principal = "65,737.61"
        self.sigma = "300.00"
        self.mode = "SUNRISE-INIT"
    def render_hud(self):
        print(f"\033[1;32m⚖️ [STATUS] {self.mode} | SIGMA: {self.sigma} (MAX)\033[0m")
        print(f"\033[1;32m🏰 [ANCHOR] EQUITY: ${self.principal} [BIT-PERFECT]\033[0m")
if __name__ == "__main__":
    core = SovereignCore()
    core.render_hud()
