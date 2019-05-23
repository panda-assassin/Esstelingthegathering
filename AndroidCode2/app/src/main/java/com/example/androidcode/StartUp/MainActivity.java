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

    private void initViews() {

        inputTextEmail = (EditText)findViewById(R.id.registerEmail);
        inputTextPassword = (EditText) findViewById(R.id.registerPassword);

        loginButton = (Button) findViewById(R.id.LogInButton);

        registerButton = (Button) findViewById(R.id.RegisterButton);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyFromSQLite();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(inputTextEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(inputTextEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(inputTextPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkUser(inputTextEmail.getText().toString().trim()
                , inputTextPassword.getText().toString().trim())) {


            Intent accountsIntent = new Intent(activity, UsersListActivity.class);
            accountsIntent.putExtra("EMAIL", inputTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        inputTextEmail.setText(null);
        inputTextPassword.setText(null);
    }

}
