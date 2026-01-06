import json
import sys
import os

class ConstitutionalLogicEngine:
    def __init__(self):
        # Default gates; can be extended via env variables in Termux
        self.valid_amendments = {4, 5, 6, 8, 14}

    def process_case(self, case_data):
        case_id = case_data.get("case_id", "UNKNOWN")
        violation = case_data.get("rights_violated", False)
        amendment = case_data.get("amendment_gate")
        procedural_integrity = case_data.get("procedural_integrity", True)

        if not procedural_integrity:
            return self.finalize_action(case_id, "KILL", "Fundamental procedural failure.")

        if violation and amendment in self.valid_amendments:
            mapping = {
                4: ("RELEASE", "4th Amdt: Exclusionary Rule applied."),
                5: ("RELIEF", "5th Amdt: Due Process violation found."),
                6: ("APPEAL", "6th Amdt: Ineffective assistance of counsel loop.")
            }
            action, reason = mapping.get(amendment, ("PROCEED", "No specific gate match."))
            return self.finalize_action(case_id, action, reason)
        
        return self.finalize_action(case_id, "PROCEED", "No constitutional barriers detected.")

    def finalize_action(self, case_id, action, reason):
        return {
            "case_id": case_id,
            "action_scope": action,
            "justification": reason,
            "system_exit": action in ["KILL", "RELEASE"]
        }

if __name__ == "__main__":
    engine = ConstitutionalLogicEngine()
    
    # Allow Termux to pipe in JSON data
    if not sys.stdin.isatty():
        try:
            input_data = json.load(sys.stdin)
            result = engine.process_case(input_data)
            print(json.dumps(result, indent=2))
        except Exception as e:
            print(json.dumps({"error": str(e)}))
    else:
        print("Termux Engine: Waiting for JSON input (Pipe data or use --help)")

