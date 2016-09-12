from Crypto.Cipher import DES
from Crypto import Random

def encrypt(plain_text, key):
	iv = "DeadBeef"
	des = DES.new(key, DES.MODE_CFB, iv)
	cipher_text = des.encrypt(plain_text)
	return cipher_text

def decrypt(cipher_text, key):
	iv = "DeadBeef"
	des = DES.new(key, DES.MODE_CFB, iv)
	decrypted_text = des.decrypt(cipher_text)
	return decrypted_text

if __name__ == "__main__":
	plain_text = "This is a secret message"
	key = "Good key"
	cipher_text = encrypt(plain_text, key)
	decrypted_text = decrypt(cipher_text, key)
	if (plain_text == decrypted_text):
		print "Successful but bad coding practice!"
	else:
		print "Failed"
