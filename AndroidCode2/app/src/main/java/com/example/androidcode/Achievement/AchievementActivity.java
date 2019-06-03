package com.example.androidcode.Achievement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.androidcode.BlankActivity;
import com.example.androidcode.R;
import com.example.androidcode.StartUp.HomeScreenActivity;

public class AchievementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AchievementAdapter achievementAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        recyclerView = findViewById(R.id.achievementRecycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(layoutManager);
        achievementAdapter = new AchievementAdapter();
        recyclerView.setAdapter(achievementAdapter);

        Button back = findViewById(R.id.backbutton);
        Button option = findViewById(R.id.optionsbutton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AchievementActivity.this, HomeScreenActivity.class));
            }
        });

        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AchievementActivity.this, BlankActivity.class));
            }
        });
    }
}