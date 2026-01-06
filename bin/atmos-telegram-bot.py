import os, subprocess, telebot

TOKEN = "8568251192:AAEMBNZrAcyq_BpVgX-ribE7bnNmy8aFwaE"
CHAT_ID = "8200573061"
bot = telebot.TeleBot(TOKEN)

@bot.message_group_handler(func=lambda message: str(message.chat.id) == CHAT_ID)
@bot.message_handler(commands=['start', 'status'])
def send_status(message):
    uptime = subprocess.check_output("uptime -p", shell=True).decode()
    resp = f"<b>🏰 [ATMOS-STATUS]</b>\n📍 Location: Independence, MO\n⏱️ Uptime: {uptime}\n🛡️ Posture: LOCKED\n⚖️ Ω Status: 26.1"
    bot.reply_to(message, resp, parse_mode='HTML')

@bot.message_handler(commands=['strike'])
def manual_strike(message):
    try:
        ip = message.text.split()[1]
        os.system(f"python3 bin/atmos-retaliate.py --target {ip}")
        bot.reply_to(message, f"⚡ Strike initiated against {ip}")
    except:
        bot.reply_to(message, "⚠️ Usage: /strike [IP_ADDRESS]")

@bot.message_handler(commands=['lockdown'])
def lockdown(message):
    os.system("bash bin/atmos-wall.sh")
    bot.reply_to(message, "🧱 Aegis Wall Hardened. Lockdown ACTIVE.")

@bot.message_handler(commands=['open'])
def open_vault(message):
    os.system("bash bin/atmos-vault.sh open")
    bot.reply_to(message, "🔓 Sovereign Vault DECRYPTED.")

@bot.message_handler(commands=['close'])
def close_vault(message):
    os.system("bash bin/atmos-vault.sh close")
    bot.reply_to(message, "🔒 Sovereign Vault SEALED & SHREDDED.")

print("📡 [SENTINEL] Command Bridge is Live...")
bot.polling(non_stop=True)
