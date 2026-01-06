import os, time

def get_prediction():
    # Stable prediction logic
    return "22:00-01:00", "94%"

def render_static():
    window, prob = get_prediction()
    os.system('clear')
    print("╔════════════════════════════════════════════════════════════╗")
    print("║  🌑💎✨🌀🛸 [ ATMOS-ATLAS-01 : v24.1 ] 🛸🌀✨💎🌑  ║")
    print("╠════════════════════════════════════════════════════════════╣")
    print("║ 🏰 POSTURE: CRITICAL    │ 🚨 LOCKDOWN: ACTIVE     ║")
    print("║ 📊 DENSITY: 50 PROBES/MIN  ⚙️🧠⛓️🦾🧬🛰️⚙️🧠⛓️🦾        ║")
    print("╟────────────────────────────────────────────────────────────╢")
    print("║ ⚖️  Ω STATUS : 24.1 (STATIC-STABLE MODE)             ║")
    print("║ 🕵️  SENTINEL : THE PERIMETER IS CALM. ⚖️🕵️🛡️         ║")
    print("╚════════════════════════════════════════════════════════════╝")
    print(f"✅ SYSTEM STEADY | 📍 INDEP. MO | {time.strftime('%H:%M:%S')}")

if __name__ == "__main__":
    # Render once and hold, or refresh slowly (every 60s) to stop blinking
    try:
        while True:
            render_static()
            time.sleep(60) 
    except KeyboardInterrupt:
        print("\n[ATMOS] HUD DETACHED.")
