import requests
import random
import threading
import time

# Configuration
API_URL = "http://localhost:5000/evaluate"
SAMPLES = 100
CONCURRENCY = 5  # Number of simultaneous threads

def generate_random_case(id_num):
    # Fetch a random adjective from the engine's lexicon logic
    # In a real scenario, we'd query the /lexicon/ endpoint
    amendments = [4, 5, 6, 8, 14]
    return {
        "case_id": f"TEST-STRESS-{id_num}",
        "rights_violated": random.choice([True, False]),
        "amendment_gate": random.choice(amendments),
        "procedural_integrity": random.choices([True, False], weights=[0.9, 0.1])[0]
    }

def send_request(thread_id):
    for i in range(SAMPLES // CONCURRENCY):
        case = generate_random_case(f"{thread_id}-{i}")
        try:
            start = time.time()
            response = requests.post(API_URL, json=case)
            latency = time.time() - start
            print(f"[Thread {thread_id}] Case {case['case_id']} -> {response.json()['action_scope']} ({latency:.3f}s)")
        except Exception as e:
            print(f"Request failed: {e}")

# Launching the Stress Test
print(f"🚀 Starting Constitutional Load Test: {SAMPLES} cases...")
threads = []
for t in range(CONCURRENCY):
    thread = threading.Thread(target=send_request, args=(t,))
    threads.append(thread)
    thread.start()

for thread in threads:
    thread.join()

print("✅ Load Test Complete. Check /stats for final tallies.")

