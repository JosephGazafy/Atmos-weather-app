#!/data/data/com.termux/files/usr/bin/sh
# ATMOS CORE v3.1 - BOOT IGNITION

# 1. Prevent Android from putting Termux to sleep
termux-wake-lock

# 2. Initialization Delay (Allows system resources to settle)
sleep 20

# 3. Launch Stability Guardian (External Watchdog)
nohup bash ~/Atmos/stability_test.sh > ~/Atmos/stability_log.txt 2>&1 &

# 4. Launch Heartbeat Jitter (Visual Shield)
# Brings the Jitter to the foreground immediately
am start --user 0 -n com.termux/.HomeActivity
bash ~/Atmos/heartbeat_jitter.sh

