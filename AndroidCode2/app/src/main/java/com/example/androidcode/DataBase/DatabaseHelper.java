package com.example.androidcode.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.androidcode.Model.Image;
import com.example.androidcode.Model.User;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String DB_NAME = "userdb.db";
    private static final Integer DB_VER = 1;
    private static final String TABLE_NAME = "Registered";
    private static final String IMAGE_TABLE_NAME = "Images";
    // User Table Columns names
    private static final String COLUMN_USER_ID = "ID";
    private static final String COLUMN_USER_NAME = "Username";
    private static final String COLUMN_USER_EMAIL = "Email";
    private static final String COLUMN_USER_PASSWORD = "Password";
    private static final String COLUMN_USER_EXP = "Exp";

    private static final String COLUMN_IMAGE_ID = "ID";
    private static final String COLUMN_IMAGE_NAME = "Name";
    private static final String COLUMN_IMAGE_MAP = "Bitmap";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public void addUser(String username, String password, String email) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, username.toLowerCase());
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_PASSWORD, password);
        values.put(COLUMN_USER_EXP, 0.0);

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void addImage(String name, byte[] image) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE_NAME, name);
        values.put(COLUMN_IMAGE_MAP, image);

        db.insert(IMAGE_TABLE_NAME, null, values);
        db.close();
    }


    public Bitmap getImage(String name) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {COLUMN_IMAGE_ID, COLUMN_IMAGE_NAME, COLUMN_IMAGE_MAP};
        qb.setTables(IMAGE_TABLE_NAME);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        ArrayList<Image> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Image image = new Image();
                image.setID(cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE_ID)));
                image.setName(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_NAME)));
                image.setBitmap(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE_MAP)));

                result.add(image);
            } while (cursor.moveToNext());
        }
        for (Image image : result) {
            if (image.getName().equals(name)) {
                image.getBitmap();
                return BitmapFactory.decodeByteArray(image.getBitmap(),0,image.getBitmap().length);
            }
        }
        return null;
    }

    public boolean checkUser(String username) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {COLUMN_USER_NAME};
        qb.setTables(TABLE_NAME);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        ArrayList<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
            } while (cursor.moveToNext());
        }
        if (result.contains(username)) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_USER_EMAIL, COLUMN_USER_PASSWORD, COLUMN_USER_EXP};
        qb.setTables(TABLE_NAME);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        ArrayList<User> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)).toLowerCase());
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                user.setExp(cursor.getDouble(cursor.getColumnIndex(COLUMN_USER_EXP)));

                result.add(user);
            } while (cursor.moveToNext());
        }

        for (User user : result) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}

