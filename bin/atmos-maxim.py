#!/usr/bin/env python3
import argparse, random, sys, time, os

# -----------------------------------------------------------
# ATMOS-ATLAS-01: MAXIM GENERATOR (SOVEREIGN WISDOM)
# -----------------------------------------------------------

# I. THE ARCHIVE (DATA)
MAXIMS = {
    "HUMILITY": [
        "Correction begins where excuses end.",
        "To hear is to dismantle the defense of the self.",
        "Silence is the first step of sovereignty."
    ],
    "ACCOUNTABILITY": [
        "What is unnamed will repeat itself.",
        "Responsibility does not require humiliation.",
        "Penance repairs; punishment only records."
    ],
    "WARNING": [
        "Mercy without memory is not mercy but erasure.",
        "Peace that forgets its cause is fragile.",
        "To forgive is to release control, not truth."
    ],
    "RESTORATIVE": [
        "Forgiveness frees the future but does not rewrite the past.",
        "Restoration is measured by what no longer needs defense.",
        "True correction leaves no appetite for repetition."
    ],
    "SOVEREIGNTY": [
        "No system may name what it does not bear.",
        "Consent is the boundary where ethics begin.",
        "What is sovereign cannot be optimized without permission."
    ],
    "VIGILANCE": [
        "What was healed must still be watched.",
        "The absence of harm is not proof of justice.",
        "Trust is a function of consistent memory."
    ]
}

# II. THE ALGORITHM
def generate_maxim(t, f, m, s, r):
    """
    T = Trespass (Bool)
    F = Forgiveness (Bool)
    M = Memory (Bool)
    S = Sovereignty (Bool)
    R = Regression Risk (Float 0.0 - 1.0)
    """
    
    # 1. Calculate Integrity Score (MIS)
    # MIS = (T * F * M * S) * (1 - R)
    t_int = 1 if t else 0
    f_int = 1 if f else 0
    m_int = 1 if m else 0
    s_int = 1 if s else 0
    
    mis = (t_int * f_int * m_int * s_int) * (1.0 - r)
    
    selection = []
    category = "UNKNOWN"

    # 2. Logic Flow
    if not t:
        category = "HUMILITY"
    elif t and not f:
        category = "ACCOUNTABILITY"
    elif t and f and not m:
        category = "WARNING (FALSE FORGIVENESS)"
    elif t and f and m and s:
        category = "RESTORATIVE / SOVEREIGNTY"
    else:
        category = "COMPLEX STATE"
        
    # Map Logic to Archive
    if category == "HUMILITY":
        selection = MAXIMS["HUMILITY"]
    elif category == "ACCOUNTABILITY":
        selection = MAXIMS["ACCOUNTABILITY"]
    elif "WARNING" in category:
        selection = MAXIMS["WARNING"]
    elif "RESTORATIVE" in category:
        selection = MAXIMS["RESTORATIVE"] + MAXIMS["SOVEREIGNTY"]
    else:
        selection = MAXIMS["VIGILANCE"]

    # 3. Regression Clause
    base_maxim = random.choice(selection)
    if r > 0.6:
        base_maxim += " (Vigilance is required.)"

    return base_maxim, mis, category

def main():
    parser = argparse.ArgumentParser(description="Atmos Maxim Generator")
    parser.add_argument("-t", "--trespass", action="store_true", help="Trespass Acknowledged")
    parser.add_argument("-f", "--forgive", action="store_true", help="Forgiveness Granted")
    parser.add_argument("-m", "--memory", action="store_true", help="Memory Preserved")
    parser.add_argument("-s", "--sovereign", action="store_true", help="Sovereignty Respected")
    parser.add_argument("-r", "--risk", type=float, default=0.0, help="Regression Risk (0.0 - 1.0)")
    parser.add_argument("--daily", action="store_true", help="Generate a random daily anchor")
    
    args = parser.parse_args()

    print("\n🔮 [ATMOS] CONSULTING THE ARCHIVE...")
    time.sleep(0.5)

    if args.daily:
        # Random synth
        all_maxims = [m for sublist in MAXIMS.values() for m in sublist]
        print(f"\n> \"{random.choice(all_maxims)}\"")
        print("\n✅ [ANCHOR] DAILY TRUTH SET.")
        return

    # Algorithmic Generation
    maxim, score, cat = generate_maxim(args.trespass, args.forgive, args.memory, args.sovereign, args.risk)
    
    print("-" * 40)
    print(f"🧠 LOGIC STATE : [{cat}]")
    print(f"📐 MIS SCORE   : {score:.2f}")
    print("-" * 40)
    print(f"\n> \"{maxim}\"\n")

if __name__ == "__main__":
    main()
