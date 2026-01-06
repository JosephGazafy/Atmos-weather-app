import speech_recognition as sr
import os

# ATMOS VOCAL COMMANDER
# Mapping Nemotron-Speech-Streaming STT to Atmos Binaries

def listen_for_sovereign():
    r = sr.Recognizer()
    with sr.Microphone() as source:
        print(">> OMEGA-LATTICE LISTENING...")
        audio = r.listen(source)

    try:
        command = r.recognize_google(audio).lower()
        print(f">> HEARD: {command}")
        
        # Guardrail: Only Joseph's predefined commands are executed
        if "verify principal" in command:
            os.system("~/Atmos-Engine/bin/atmos-verify-remote.sh")
        elif "push to cloud" in command:
            os.system("~/Atmos-Engine/bin/atmos-push-fix.sh")
        elif "status report" in command:
            os.system("~/Atmos-Engine/bin/atmos-report.sh")
        else:
            print(">> COMMAND REJECTED: Safety Guardrail Violation.")

    except Exception as e:
        pass

if __name__ == "__main__":
    while True:
        listen_for_sovereign()
