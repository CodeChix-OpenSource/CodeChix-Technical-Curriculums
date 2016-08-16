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
	uidQuery  = "SELECT uid,username  FROM users WHERE username = ? AND password = ?"
	uidQueryOut = cur.execute(uidQuery, (uname, hashlib.md5(pwd).hexdigest())).fetchall()
	user = ' '.join([' '.join(map(str,row)) for row in uidQueryOut])
	print "Hello "+ user
	accType = raw_input('Enter your account type (SAVING/CHECKING):') 
	balQuery = "SELECT balance from balances WHERE uid = ? AND accountType = ?" 
	balQueryOut = cur.execute(balQuery,(user.split()[0], accType)).fetchall()
	balance = ' '.join([str(row[0]) for row in balQueryOut])
	print "Your balance is "+balance
	close(con)

if __name__ == "__main__":
    main()