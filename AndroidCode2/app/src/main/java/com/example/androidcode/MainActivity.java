package com.example.androidcode;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper DBHelper;
    private SQLiteDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBHelper = new DatabaseHelper(this);

        try {
            DBHelper.updateDataBase();
        } catch(IOException e) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            DB = DBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DBHelper.openDataBase();
        Button btn = findViewById(R.id.RegisterButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

}
