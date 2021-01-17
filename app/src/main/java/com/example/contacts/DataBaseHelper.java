package com.example.contacts;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CONTACT_TABLE = "CONTACT_TABLE";
    public static final String CONTACT_ID = "ID";
    public static final String CONTACT_FNAME = "CONTACT_FNAME";
    public static final String CONTACT_LNAME = "CONTACT_LNAME";
    public static final String CONTACT_EMAIL = "CONTACT_EMAIL";
    public static final String CONTACT_BIRTHDAY = "CONTACT_BIRTHDAY";
    public static final String CONTACT_FIX = "CONTACT_FIX";
    public static final String CONTACT_MOBILE = "CONTACT_MOBILE";
    public static final String CONTACT_FAX = "CONTACT_FAX";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "contacts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CONTACT_TABLE + " (" + CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CONTACT_FNAME + " TEXT, " + CONTACT_LNAME + " TEXT, " + CONTACT_EMAIL + " TEXT, " + CONTACT_BIRTHDAY + " TEXT, " + CONTACT_FIX + " TEXT, " + CONTACT_MOBILE + " TEXT, " + CONTACT_FAX + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(ContactModel contactModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CONTACT_FNAME, contactModel.getFirstName());
        contentValues.put(CONTACT_LNAME, contactModel.getLastName());
        contentValues.put(CONTACT_EMAIL, contactModel.getEmail());
        contentValues.put(CONTACT_BIRTHDAY, contactModel.getBirthday());
        contentValues.put(CONTACT_FIX, contactModel.getFix());
        contentValues.put(CONTACT_MOBILE, contactModel.getMobile());
        contentValues.put(CONTACT_FAX, contactModel.getFax());

        long insert = db.insert(CONTACT_TABLE, null, contentValues);

        return insert != -1;
    }

    public List<ContactModel> getAll() {
        List<ContactModel> list = new ArrayList<>();

        String query = "SELECT * FROM " + CONTACT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int contactId = cursor.getInt(0);
                String contactFName = cursor.getString(1);
                String contactLName = cursor.getString(2);
                String contactEmail = cursor.getString(3);
                String contactBirthday = cursor.getString(4);
                String contactFix = cursor.getString(5);
                String contactMobile = cursor.getString(6);
                String contactFax = cursor.getString(7);

                ContactModel newContact = new ContactModel(contactId, contactFName, contactLName, contactEmail, contactBirthday, contactFix, contactMobile, contactFax);
                list.add(newContact);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }

    public boolean updateOne(String contactId, ContactModel contactModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CONTACT_FNAME, contactModel.getFirstName());
        contentValues.put(CONTACT_LNAME, contactModel.getLastName());
        contentValues.put(CONTACT_EMAIL, contactModel.getEmail());
        contentValues.put(CONTACT_BIRTHDAY, contactModel.getBirthday());
        contentValues.put(CONTACT_FIX, contactModel.getFix());
        contentValues.put(CONTACT_MOBILE, contactModel.getMobile());
        contentValues.put(CONTACT_FAX, contactModel.getFax());

        long update = db.update(CONTACT_TABLE, contentValues, "ID=?", new String[]{contactId});

        return update != -1;
    }

    public boolean deleteOne(String contactId) {
        SQLiteDatabase db = this.getWritableDatabase();

        long delete = db.delete(CONTACT_TABLE, "ID=?", new String[]{contactId});

        return delete != -1;
    }

    public List<ContactModel> search(String keyword) {
        List<ContactModel> list = new ArrayList<>();

        String query = "SELECT * FROM " + CONTACT_TABLE + " WHERE " + CONTACT_FNAME + " OR " + CONTACT_LNAME + " LIKE ?";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, new String[]{"%" + keyword + "%"});

        if (cursor.moveToFirst()) {
            do {
                int contactId = cursor.getInt(0);
                String contactFName = cursor.getString(1);
                String contactLName = cursor.getString(2);
                String contactEmail = cursor.getString(3);
                String contactBirthday = cursor.getString(4);
                String contactFix = cursor.getString(5);
                String contactMobile = cursor.getString(6);
                String contactFax = cursor.getString(7);

                ContactModel newContact = new ContactModel(contactId, contactFName, contactLName, contactEmail, contactBirthday, contactFix, contactMobile, contactFax);
                list.add(newContact);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }
}
