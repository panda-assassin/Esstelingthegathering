package com.example.androidcode.StartUp;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidcode.DataBase.DatabaseHelper;
import com.example.androidcode.QueueList.CheckInActivity;
import com.example.androidcode.R;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper DBHelper;
    private SQLiteDatabase Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBHelper = new DatabaseHelper(this);

        //Button btn = (Button)findViewById(R.id.RegisterButton);
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

        Button login = findViewById(R.id.LogInButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HomeScreenActivity.class);
                startActivity(i);
            }
        });

        DBHelper.openDataBase();
        Button registerButton = findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

}
