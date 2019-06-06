package com.example.androidcode.StartUp;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidcode.DataBase.Database;
import com.example.androidcode.DataBase.DatabaseHelper;
import com.example.androidcode.R;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    Database db;
    EditText emailaddress;
    EditText username;
    EditText password;
    EditText repeatPassword;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new Database(this);
        emailaddress = (EditText) findViewById(R.id.registerEmail);
        username = (EditText) findViewById(R.id.registerUsername);
        password = (EditText) findViewById(R.id.registerPassword);
        repeatPassword = (EditText) findViewById(R.id.registerRepeatPassword);
        register = (Button) findViewById(R.id.registerConfirm);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailaddress.getText().toString().trim();
                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                String repeat_pwd = repeatPassword.getText().toString().trim();

                if (pwd.equals(repeat_pwd)) {
                    if (!db.checkUser(user)) {
                        db.addUser(user, pwd, email);
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, R.string.error_username_exists, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, R.string.error_password_match, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
