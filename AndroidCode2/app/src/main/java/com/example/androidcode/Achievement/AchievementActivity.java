package com.example.androidcode.Achievement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.androidcode.BlankActivity;
import com.example.androidcode.R;
import com.example.androidcode.StartUp.HomeScreenActivity;

import java.util.ArrayList;

public class AchievementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AchievementAdapter achievementAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Achievement> achievements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        achievements = new ArrayList<>();
        setData();

        recyclerView = findViewById(R.id.achievementRecycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(layoutManager);
        achievementAdapter = new AchievementAdapter(this, achievements);
        recyclerView.setAdapter(achievementAdapter);

        ImageButton back = findViewById(R.id.backbutton);
        ImageButton option = findViewById(R.id.optionbutton);

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

    public void setData(){
        Log.d("setData", "adding achievements");
        achievements.add(new Achievement(R.drawable.looping, "3 attracties met looping", "0/3"));
        achievements.add(new Achievement(R.drawable.johan, "5x in johan en de eenhoorn", "0/5"));
        achievements.add(new Achievement(R.drawable.sprookjesbos, "bezoek het sprookjesbos", "0/1"));
        achievements.add(new Achievement(R.drawable.water, "bezoek alle waterattracties", "1/5"));
        achievements.add(new Achievement(R.drawable.looping, "3 attracties met looping", "1/3"));
        achievements.add(new Achievement(R.drawable.looping, "3 attracties met looping", "1/3"));
    }
}
