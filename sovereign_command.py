import math

class BioFrequencyCore:
    def __init__(self):
        self.principal = "65,737.61"
        self.sigma = "300.00"
        self.vibration_dose = 0.0 # VDV (Fourth-power method)
        
    def calculate_vdv(self, acceleration_data, time_step):
        # penalizing high-amplitude "jolts" at the boundaries of motion
        sum_fourth = sum([a**4 for a in acceleration_data])
        self.vibration_dose = (sum_fourth * time_step)**(0.25)
        return self.vibration_dose

    def render_hud(self):
        print(f"\033[1;32m⚖️ [SPECTRUM] VDV: {self.vibration_dose:.2f} | RESONANCE: 4-8Hz\033[0m")
        print(f"\033[1;32m🏰 [ANCHOR]   EQUITY: ${self.principal} [BIT-PERFECT]\033[0m")

if __name__ == "__main__":
    core = BioFrequencyCore()
    core.render_hud()
