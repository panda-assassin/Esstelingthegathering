package com.example.androidcode.Game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.androidcode.R;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    ArrayList<ImageButton> cardButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int currentOrientation = this.getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT){
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else{
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        Bundle items = intent.getBundleExtra("BUNDLE");
        ArrayList<ImageButton> imageButtonArrayList = (ArrayList<ImageButton>) items.getSerializable("ARRAYLIST");

        this.cardButtons = imageButtonArrayList;


    }
}
