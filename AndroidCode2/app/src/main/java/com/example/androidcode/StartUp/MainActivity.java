package com.example.androidcode.StartUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidcode.DataBase.DatabaseHelper;
import com.example.androidcode.DataBase.InputValidation;
import com.example.androidcode.R;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginButton;
    private Button registerButton;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        username = (EditText)findViewById(R.id.loginUsername);
        password = (EditText)findViewById(R.id.loginPassword);
        loginButton = (Button)findViewById(R.id.logInButton);
        registerButton = (Button)findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                if (db.checkUser(user, pwd)) {
                    Intent loginIntent = new Intent(MainActivity.this, HomeScreenActivity.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(MainActivity.this, R.string.error_valid_username_password, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
