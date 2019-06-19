package com.example.androidcode.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.androidcode.Model.User;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME = "userdb.db";
    private static final Integer DB_VER = 1;
    private static final String TABLE_NAME = "Registered";
    // User Table Columns names
    private static final String COLUMN_USER_ID = "ID";
    private static final String COLUMN_USER_NAME = "Username";
    private static final String COLUMN_USER_EMAIL = "Email";
    private static final String COLUMN_USER_PASSWORD = "Password";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public void addUser(String username, String password, String email) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, username.toLowerCase());
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_PASSWORD, password);

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String username) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {COLUMN_USER_NAME};
        qb.setTables(TABLE_NAME);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        ArrayList<String> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                result.add(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
            }while(cursor.moveToNext());
        }
        if(result.contains(username)){
            return true;
        }
        return false;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {COLUMN_USER_ID,COLUMN_USER_NAME, COLUMN_USER_EMAIL,COLUMN_USER_PASSWORD};
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

                result.add(user);
            } while (cursor.moveToNext());
        }

        for(User user : result ) {
            if(user.getName().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
