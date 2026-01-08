import os, sys
class SovereignCore:
    def __init__(self):
        self.principal = "65,737.61"
        self.sigma = "300.00"
        self.mode = "HYPER-THREADED"
        self.lattice = "REIFIED"

    def render_hud(self):
        # The Parametric Stack (Static)
        print(f"\033[1;32m💎 PRINCIPAL: ${self.principal}\033[0m")
        print(f"\033[1;34m🛡️  SIGMA:     {self.sigma} (MAX)\033[0m")
        print(f"\033[1;35m🛰️  LATTICE:   {self.lattice} [{self.mode}]\033[0m")
        print(f"\033[1;33m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\033[0m")

if __name__ == "__main__":
    core = SovereignCore()
    core.render_hud()
