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

    private static final String SQL_CREATE_CONTACT_TABLE = "CREATE TABLE " + ContactContract.Contact.TABLE_NAME + "(" +
            ContactContract.Contact._ID + " INTEGER PRIMARY KEY," +
            ContactContract.Contact.COLUMN_NAME_CONTACT_NAME + " TEXT" +
            ")";
    private static final String SQL_CREATE_GROUP_TABLE = "CREATE TABLE " + ContactContract.Group.TABLE_NAME + "(" +
            ContactContract.Group._ID + " INTEGER PRIMARY KEY, " +
            ContactContract.Group.COLUMN_NAME_GROUP_NAME + " TEXT" +
            ")";
    private static final String SQL_CREATE_CONTACT_GROUP_TABLE = "CREATE TABLE " + ContactContract.ContactGroup.TABLE_NAME + "(" +
            ContactContract.ContactGroup.COLUMN_NAME_CONTACT_ID + " INTEGER, " +
            ContactContract.ContactGroup.COLUMN_NAME_GROUP_ID + " INTEGER" +
            ")";
    private static final String SQL_CREATE_PHONE_TABLE = "CREATE TABLE " + ContactContract.Phone.TABLE_NAME + "(" +
            ContactContract.Phone.COLUMN_NAME_CONTACT_ID + " INTEGER, " +
            ContactContract.Phone.COLUMN_NAME_TYPE + " INTEGER, " +
            ContactContract.Phone.COLUMN_NAME_NUMBER + " TEXT" +
            ")";
    private static final String SQL_CREATE_EMAIL_TABLE = "CREATE TABLE " + ContactContract.Email.TABLE_NAME + " (" +
            ContactContract.Email.COLUMN_NAME_CONTACT_ID + " INTEGER, " +
            ContactContract.Email.COLUMN_NAME_TYPE + " INTEGER, " +
            ContactContract.Email.COLUMN_NAME_EMAIL + " TEXT" +
            " )";

    public ContactDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CONTACT_TABLE);
        db.execSQL(SQL_CREATE_GROUP_TABLE);
        db.execSQL(SQL_CREATE_CONTACT_GROUP_TABLE);
        db.execSQL(SQL_CREATE_PHONE_TABLE);
        db.execSQL(SQL_CREATE_EMAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + ContactContract.Contact.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ContactContract.Group.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ContactContract.ContactGroup.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ContactContract.Phone.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ContactContract.Email.TABLE_NAME);

        onCreate(db);
    }
}
