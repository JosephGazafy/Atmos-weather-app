use std::process::Command;
use std::thread;
use std::time::Duration;
use std::fs;

fn get_threat_level() -> i32 {
    let content = fs::read_to_string("sovereign_log.txt").unwrap_or_default();
    content.lines().rev().take(20).filter(|l| l.contains("INTERCEPT") || l.contains("CRITICAL")).count() as i32
}

fn play_bass_drone(threat: i32) {
    Command::new("bash")
        .arg("bin/atmos-bass-drone.sh")
        .arg(threat.to_string())
        .spawn()
        .ok();
}

fn main() {
    println!("🎸 [ATMOS-SYNTH] SUBTRACTIVE BASS & HYDROGEN ACTIVE");
    loop {
        let t = get_threat_level();
        
        // Trigger the evolving drone in the background
        play_bass_drone(t);
        
        // Wait for drone duration before next evolution
        thread::sleep(Duration::from_secs(4));
    }
}
