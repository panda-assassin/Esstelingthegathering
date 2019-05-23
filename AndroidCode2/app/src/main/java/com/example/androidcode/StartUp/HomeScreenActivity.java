package com.example.androidcode.StartUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidcode.Inventory.InventoryActivity;
import com.example.androidcode.QueueList.CheckInActivity;
import com.example.androidcode.R;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Button checkin = findViewById(R.id.CheckInActivityBttn);
        Button inventory = findViewById(R.id.InventoryActivityBttn);
        Button nothing1= findViewById(R.id.button3);
        Button nothing2 = findViewById(R.id.button4);

        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, CheckInActivity.class));
            }
        });

        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, InventoryActivity.class));
            }
        });

    }
}