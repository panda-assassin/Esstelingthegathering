package com.example.androidcode.StartUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidcode.Achievement.Achievement;
import com.example.androidcode.Achievement.AchievementActivity;
import com.example.androidcode.Game.GameSetupActivity;
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
        Button achievement = findViewById(R.id.achievementButton);
        Button game = findViewById(R.id.GameActivityBttn);

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

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, GameSetupActivity.class));
            }
        });

        achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, AchievementActivity.class));
            }
        });

    }

}
