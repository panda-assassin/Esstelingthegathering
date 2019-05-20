package com.example.esstelingthegathering;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper DBHelper;
    private SQLiteDatabase Db;
    private ImageButton test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        DBHelper = new DatabaseHelper(this);

//        try {
//            DBHelper.updateDataBase();
//        } catch(IOException e) {
//            throw new Error("UnableToUpdateDatabase");
//        }
//
//        try {
//            Db = DBHelper.getWritableDatabase();
//        } catch (SQLException mSQLException) {
//            throw mSQLException;
//        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = findViewById(R.id.queuesButton);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TestActivity.class);
                startActivity(i);
            }
        });


//        DBHelper.openDataBase();
    }

}
