package com.example.androidcode.StartUp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidcode.DataBase.DatabaseHelper;
import com.example.androidcode.DataBase.InputValidation;
import com.example.androidcode.DataBase.DatabaseHelper;
import com.example.androidcode.QueueList.CheckInActivity;
import com.example.androidcode.R;

public class MainActivity extends AppCompatActivity {
    private final AppCompatActivity activity = MainActivity.this;

    private EditText inputTextEmail;
    private EditText inputTextPassword;

    private Button loginButton;

    private Button registerButton;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



}
