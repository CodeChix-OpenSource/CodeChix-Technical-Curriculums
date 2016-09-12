from Crypto.Cipher import AES
from Crypto.Cipher import DES
from Crypto import Random

def encrypt(plain_text, key, iv):
	des = DES.new(key, DES.MODE_CFB, iv)
	cipher_text = des.encrypt(plain_text)
	return cipher_text

def decrypt(cipher_text, key, iv):
	des = DES.new(key, DES.MODE_CFB, iv)
	decrypted_text = des.decrypt(cipher_text)
	return decrypted_text

if __name__ == "__main__":
	plain_text = "This is a secret message"
	iv = Random.get_random_bytes(8)
	key = "Good key"
	cipher_text = encrypt(plain_text, key, iv)
	decrypted_text = decrypt(cipher_text, key, iv)
	if (plain_text == decrypted_text):
		print "Successful"
	else:
		print "Failed"
