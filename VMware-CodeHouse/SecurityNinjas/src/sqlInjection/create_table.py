import sqlite3
import hashlib

con = sqlite3.connect('./saveMe.db')
cur = con.cursor()

cur.execute("DROP TABLE if EXISTS users")
cur.execute("CREATE TABLE users(uid INT, username TEXT, password TEXT)")
cur.execute("INSERT INTO users VALUES(101,'ccAdmin','"+hashlib.md5('pwdAdmin').hexdigest()+"')")
cur.execute("INSERT INTO users VALUES(201,'ccMod','"+hashlib.md5('pwdMod').hexdigest()+"')")
cur.execute("INSERT INTO users VALUES(301,'ccStudent','"+hashlib.md5('pwdStud').hexdigest()+"')")

cur.execute("DROP TABLE if EXISTS balances")
cur.execute("CREATE TABLE balances(uid INT, accountType TEXT, balance INT)")
cur.execute("INSERT INTO balances VALUES(101, 'SAVING', 20000)")
cur.execute("INSERT INTO balances VALUES(201, 'CHECKING', 5000)")
cur.execute("INSERT INTO balances VALUES(301, 'CHECKING', 4000)")
con.commit()
con.close()
