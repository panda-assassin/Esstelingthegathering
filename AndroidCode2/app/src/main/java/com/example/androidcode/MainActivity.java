package com.example.androidcode;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper DBHelper;
    private SQLiteDatabase Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBHelper = new DatabaseHelper(this);

        Button btn = (Button)findViewById(R.id.RegisterButton);
        try {
            DBHelper.updateDataBase();
        } catch(IOException e) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            Db = DBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DBHelper.openDataBase();
    }

}
