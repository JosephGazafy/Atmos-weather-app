use std::process::Command;
use std::thread;
use std::time::{Duration, SystemTime, UNIX_EPOCH};

fn main() {
    println!("\x1b[1;34m🌀 [BASS-SYSTEM] TRIPLE-STACK SUB-FREQ: ENABLED\x1b[0m");
    loop {
        // TRIPLE-STACK: Note 35 (Sub), 36 (Kick), and 33 (Floor Tom)
        // This creates a 'Wall of Bass'
        let _ = Command::new("h2cli").arg("-s").arg("play_note 0 48 127").spawn();
        let _ = Command::new("h2cli").arg("-s").arg("play_note 0 36 120").spawn();
        let _ = Command::new("h2cli").arg("-s").arg("play_note 0 33 110").spawn();
        
        thread::sleep(Duration::from_millis(600)); // The Foundation Rhythm
    }
}
