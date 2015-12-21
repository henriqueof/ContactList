package net.henriqueof.contactlist.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Carlos Henrique on 21/12/2015.
 */
public class ContactDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + ContactContract.Contact.TABLE_NAME + " (" +
            ContactContract.Contact._ID + " INTEGER PRIMARY KEY," +
            ContactContract.Contact.COLUMN_NAME_CONTACT_NAME + " TEXT" +
            " )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ContactContract.Contact.TABLE_NAME;

    public ContactDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
