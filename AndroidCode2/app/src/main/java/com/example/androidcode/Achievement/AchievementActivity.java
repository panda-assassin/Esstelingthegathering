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
    private static ArrayList<Achievement> achievements = new ArrayList<>();
    private static boolean dataSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);


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

    public static ArrayList<Achievement> getAchievements() {
        return achievements;
    }

    public static void setData(){
        if (!dataSet) {
            Log.d("setData", "adding achievements");
            achievements.add(new Achievement(R.drawable.looping, "3 attracties met looping", 3));
            achievements.add(new Achievement(R.drawable.johan, "5x in johan en de eenhoorn", 5));
            achievements.add(new Achievement(R.drawable.sprookjesbos, "bezoek het sprookjesbos", 1));
            achievements.add(new Achievement(R.drawable.water, "bezoek alle waterattracties", 5));
            achievements.add(new Achievement(R.drawable.looping, "3 attracties met looping", 3));
            dataSet = true;
        }
    }

    public static void updateAchievement(String achievement){

        if(achievement==null){
            achievements = new ArrayList<>();
            setData();
        }

        for (Achievement achievementi : achievements) {
            if(achievementi.getAchievementName().equalsIgnoreCase(achievement)){
                achievementi.addProgress(1);
                System.out.println(achievementi.getAchievementName()+": "+achievementi.getProgress());
            }
        }
    }

}
