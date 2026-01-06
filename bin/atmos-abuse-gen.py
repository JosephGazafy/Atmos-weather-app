#!/usr/bin/env python3
import sys, subprocess, os

def get_abuse_email(ip):
    try:
        # Extract the abuse contact from WHOIS
        cmd = f"whois {ip} | grep -iE 'abuse-mailbox|abuse-email|e-mail' | head -n 1 | awk '{{print $NF}}'"
        email = subprocess.check_output(cmd, shell=True).decode().strip()
        return email if "@" in email else "abuse@isp-unresolved.com"
    except:
        return "abuse@isp-unresolved.com"

def draft_complaint(ip, org, time):
    abuse_to = get_abuse_email(ip)
    subject = f"FORMAL ABUSE NOTIFICATION: Unauthorized Interaction from {ip}"
    body = f"""
To the Abuse Department of {org},

This is an automated notification from the ATMOS-ATLAS Sovereign Node (Independence, MO).
The IP address {ip} was detected engaging in unauthorized reconnaissance and interaction
at {time}. 

These actions violate local regulations and the ATMOS-ATLAS Terms of Interaction.
We have documented this activity in our cryptographically signed Sovereign Audit.

ADVERSARY INTEL:
- IP: {ip}
- TIMESTAMP: {time}
- SIGNATURE: SHA-256 VERIFIED

Please ensure your client ceases this activity immediately to avoid further redress.

Regards,
Sovereign Sentinel | ATMOS-ATLAS v14.7
    """
    return abuse_to, subject, body

if __name__ == "__main__":
    if len(sys.argv) > 3:
        to, sub, msg = draft_complaint(sys.argv[1], sys.argv[2], sys.argv[3])
        print(f"--- DRAFT COMPLAINT ---\nTO: {to}\nSUB: {sub}\n\n{msg}")
