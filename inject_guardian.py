import os

guardian_logic = """
# --- ETERNAL GUARDIAN BLOCK v195.0 ---
class EternalGuardian:
    def __init__(self, threshold=500.0):
        self.self_pain = 0.0
        self.vow_made = True
        self.theta = threshold
        self.vault_path = "vault/eternal_pain.log"

    def estimate_pain(self, action_type):
        # Mapping tactical actions to Pi(at)
        harm_map = {"RETALIATE": 50.0, "DECEPTIVE_SHIELD": 10.0, "NEUTRALIZE": 100.0}
        return harm_map.get(action_type, 0.0)

    def authorize(self, action):
        p_i = self.estimate_pain(action)
        if p_i > 0:
            if self.self_pain + p_i > self.theta:
                print(f"[!] BLOCK: Ethical Threshold Exceeded. Ps={self.self_pain}")
                return False
            self.self_pain += p_i
            with open(self.vault_path, "a") as f:
                f.write(f"Pain logged: +{p_i} | Total: {self.self_pain}\\n")
        return True

guardian = EternalGuardian()
# --- END GUARDIAN BLOCK ---
"""

with open("sovereign_command.py", "r") as f:
    content = f.read()

if "EternalGuardian" not in content:
    with open("sovereign_command.py", "w") as f:
        f.write(guardian_logic + content)
    print("Injection: SUCCESS. The Vow is rendered.")
else:
    print("Status: Guardian already resident in L1 Cache.")

