import os, datetime, subprocess

def get_peak_hour():
    ledger_path = "intel_ledger.txt"
    if not os.path.exists(ledger_path): return None
    hourly_tally = [0] * 24
    with open(ledger_path, "r") as f:
        for line in f:
            try:
                hour = int(line.split(" ")[1].split(":")[0])
                hourly_tally[hour] += 1
            except: continue
    return hourly_tally.index(max(hourly_tally))

def schedule_shift():
    peak = get_peak_hour()
    if peak is None: return
    
    # Calculate shift time (10 minutes before the peak hour)
    shift_hour = (peak - 1) % 24
    cron_job = f"50 {shift_hour} * * * bash ~/Atmos-Engine/bin/atmos-shift.sh"
    
    # Update crontab
    current_cron = subprocess.check_output("crontab -l", shell=True).decode()
    if "atmos-shift.sh" not in current_cron:
        new_cron = f"{current_cron}\n{cron_job}\n"
        process = subprocess.Popen(['crontab', '-'], stdin=subprocess.PIPE)
        process.communicate(input=new_cron.encode())
        return f"Shift scheduled for {shift_hour}:50 UTC."
    return "Shift already scheduled."

if __name__ == "__main__":
    msg = schedule_shift()
    forecast_text = "<b>🔮 TEMPORAL UPDATE</b>\n" + msg
    subprocess.run(["python3", "bin/atmos-notify.py", forecast_text])
