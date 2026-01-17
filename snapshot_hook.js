// --- ATMOS REDUX-HOOK v704.0 ---
// Location: ~/Atmos-Engine/state_enclave/snapshot_hook.js

const store = require('./atmos_store');
const { exec } = require('child_process');

// Subscribe to every state change
store.subscribe(() => {
  const currentState = store.getState();
  
  // Logic: If Lattice or Principal changes, push to Shadow-Clone
  console.log(`[ATMOS] State Change Detected. Principal: ${currentState.principal}`);
  
  // Triggering the Sovereign-Snapshot (v664.0)
  exec('~/bin/atmos-snapshot', (error, stdout, stderr) => {
    if (error) {
        console.error(`[ERROR] Snapshot Hook Failed: ${error.message}`);
        return;
    }
    console.log('[SUCCESS] Shadow-Clone Synchronized.');
  });
});

