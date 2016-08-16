package androidgo.finalapp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidgo.finalapp.model.Contact;

/**
 * Authors: Pallavi Santhosh Kumar
 *          Akansha Patel
 *          Shyama Sankar Vellore
 *          Ketki Haridas
 * Date: 08/16/2016
 * DatabaseConnector: Utility class that provides APIs to access the database
 */
public class DatabaseConnector {

    private static final String DATABASE_NAME = "ContactDB";
    private static final String TABLE_NAME = "Contacts";
    private static final String ID_COLUMN = "_id";
    private static final String NAME_COLUMN = "Name";
    private static final String PHONE_NUMBER_COLUMN = "PhoneNumber";

    private SQLiteDatabase database;
    private ContactsDbOpenHelper databaseOpenHelper;

    /**
     * DatabaseConnector: Initialize the database open helper
     * @param context Application context
     */
    public DatabaseConnector(Context context) {
        databaseOpenHelper = new ContactsDbOpenHelper(context, DATABASE_NAME, null, 1);
    }

    /**
     * openWritableDatabase: Open database in writable mode
     * @throws SQLException
     */
    public void openWritableDatabase() throws SQLException {
        database = databaseOpenHelper.getWritableDatabase();
    }

    /**
     * openReadableDatabase: Open database in readable mode
     * @throws SQLException
     */
    public void openReadableDatabase() throws SQLException {
        database = databaseOpenHelper.getWritableDatabase();
    }

    /**
     * close: Close the database connection
     */
    public void close() {
        if (database != null)
            database.close();
    }

    /**
     * insertData: Insert a contact to the database
     * @param contact Contact to be inserted
     */
    public void insertData(Contact contact) {
        ContentValues newData = new ContentValues();
        database = databaseOpenHelper.getWritableDatabase();
        newData.put(NAME_COLUMN, String.valueOf(contact.getName()));
        newData.put(PHONE_NUMBER_COLUMN, String.valueOf(contact.getPhoneNumber()));

        openWritableDatabase();
        database.insert(TABLE_NAME, null, newData);
        close();

    }

    /**
     * getAllContacts: Get contacts from the database
     * @return ArrayList of all contacts
     */
    public ArrayList<Contact> getAllContacts() {
        openReadableDatabase();
        String selectQuery = "SELECT * from "+ TABLE_NAME + ";";
        Cursor cursor = database.rawQuery(selectQuery, null);

        ArrayList<Contact> contacts = new ArrayList<Contact>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Contact contact = new Contact();
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contacts.add(contact);
                cursor.moveToNext();
            }
        }

        return contacts;
    }

    /**
     * getContactNames: Get contact names from the database
     * @return All contact names
     */
    public String getContactNames() {
        openReadableDatabase();
        String selectQuery = "SELECT * from Contacts;";
        Cursor cursor = database.rawQuery(selectQuery, null);

        String contacts = "";
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                contacts += cursor.getString(1) + "\n";
                cursor.moveToNext();
            }
        }

        return contacts;
    }

    /**
     * ContactsDbOpenHelper: Database open helper
     */
    private class ContactsDbOpenHelper extends SQLiteOpenHelper {

        private static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME +
                " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_COLUMN + " TEXT, " + PHONE_NUMBER_COLUMN + " NUMERIC);";

        /**
         * ContactsDbOpenHelper: Initialize the database open helper
         * @param context Application context
         * @param name Database name
         * @param factory Factory
         * @param version Version
         */
        public ContactsDbOpenHelper(Context context, String name,
                                    SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        /**
         * onCreate: Create the contacts table
         * @param database Database
         */
        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(CREATE_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion,
                              int newVersion) {
        }
    }
}