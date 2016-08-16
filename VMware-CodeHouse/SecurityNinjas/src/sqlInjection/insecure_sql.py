import sqlite3
import hashlib

sqlite_file = "./saveMe.db"

def connect():
	con = sqlite3.connect(sqlite_file)
	return con

def close(con):
	con.commit()
	con.close()

def main():
	con = connect()
	cur = con.cursor()
	uname = raw_input('Enter your username:')
	pwd = raw_input('Enter your password:')
	uidQuery  = "SELECT uid,username  FROM users WHERE username = '"+ uname+"' AND password = '"+hashlib.md5(pwd).hexdigest() +"'"
	uidQueryOut = cur.execute(uidQuery).fetchall()
	user = ' '.join([' '.join(map(str,row)) for row in uidQueryOut])
	print "Hello "+ user
	accType = raw_input('Enter your account type (SAVING/CHECKING):') 
	balQuery = "SELECT balance from balances WHERE uid = "+user.split()[0]+" AND accountType = '"+accType+"'" 
	balQueryOut = cur.execute(balQuery).fetchall()
	balance = ' '.join([str(row[0]) for row in balQueryOut])
	print "Your balance is "+balance
	close(con)

if __name__ == "__main__":
    main()