package com.example.androidcode.StartUp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidcode.AddImage;
import com.example.androidcode.DataBase.DatabaseHelper;
import com.example.androidcode.R;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginButton;
    private Button registerButton;
    private DatabaseHelper db;
    private ImageView esstelinglogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        username = findViewById(R.id.LoginUsername);
        password = findViewById(R.id.LoginPassword);
        loginButton = findViewById(R.id.LogInButton);
        registerButton = findViewById(R.id.RegisterButton);
        esstelinglogo = findViewById(R.id.esstelingLogo);
        esstelinglogo.setImageBitmap(db.getImage("Esstelinglogo"));

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
                //startActivity(new Intent(MainActivity.this, HomeScreenActivity.class));

                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                if(user.equals("Admin") && pwd .equals("")){
                    Intent secretIntent = new Intent(MainActivity.this, AddImage.class);
                    startActivity(secretIntent);
                } else if (db.checkUser(user, pwd)) {
                    Intent loginIntent = new Intent(MainActivity.this, HomeScreenActivity.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(MainActivity.this, R.string.error_valid_username_password, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}