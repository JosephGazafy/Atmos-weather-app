use std::process::Command;
use std::thread;
use std::time::{Duration, SystemTime, UNIX_EPOCH};

fn get_entropy() -> f64 {
    let nanos = SystemTime::now().duration_since(UNIX_EPOCH).unwrap().as_nanos();
    (nanos % 1000) as f64 / 100.0
}

fn main() {
    let mut joule_energy: f64 = 0.0;
    let truth_freq: f64 = 41.2;
    let mut temp: f64 = 0.1;

    println!("\x1b[1;36m┌────────────────────────────────────────────────────────┐\x1b[0m");
    println!("\x1b[1;36m│\x1b[0m \x1b[1;31m🔥 [RUST JOULE-THIEF] SOVEREIGN ACTIVATION\x1b[0m \x1b[1;36m       │\x1b[0m");
    println!("\x1b[1;36m└────────────────────────────────────────────────────────┘\x1b[0m");

    loop {
        // 1. SIMULATE SYSTEMIC DRIFT (The Gaslighting)
        let raw_drift = (get_entropy() - 5.0) * temp;
        
        // 2. QUASI-CASTRATION LOGIC
        let clean_drift = if raw_drift.abs() > 1.5 {
            joule_energy += raw_drift.abs() * 0.20; // HARVEST
            0.0 // CASTRATED
        } else {
            raw_drift
        };

        // 3. SONIC OUTPUT (Triggering the 41.2Hz Foundation)
        // We use Note 35 for the deep anchor
        let velocity = (100.0 + (joule_energy * 2.0)).min(127.0) as u8;
        let _ = Command::new("h2cli")
            .arg("-s")
            .arg(format!("play_note 0 35 {}", velocity))
            .spawn();

        // 4. KINETIC UI
        let bar_len = (joule_energy as usize).min(20);
        let harvest_bar = "⚡".repeat(bar_len);
        print!("\r\x1b[1;35mTRUTH: {:.1}Hz | JOULES: [{:<20}] {:.2}J\x1b[0m", 
               truth_freq + clean_drift, harvest_bar, joule_energy);
        
        // 5. ACCELERATION (The Joule-Thief Tick)
        let delay = (450.0 - (joule_energy * 15.0)).max(50.0) as u64;
        thread::sleep(Duration::from_millis(delay));

        if temp < 1.0 { temp += 0.02; }
    }
}
