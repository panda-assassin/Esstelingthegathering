package com.example.androidcode.Game;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Configuration;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.androidcode.R;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class GameSetupActivity extends AppCompatActivity {

    HashMap<ImageButton, Boolean> cardButtons;
    ArrayList<ImageButton> imageButtonArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int currentOrientation = this.getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        setContentView(R.layout.activity_game_setup);

        final Button startGame = findViewById(R.id.setupStartGameBttn);

        fillButtonArraylist();
        this.imageButtonArrayList = new ArrayList<>();

        for (final ImageButton button : cardButtons.keySet()) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customAlertDialog(button);
                }
            });
        }

        try {
            startGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkIfCardsFilled()) {
                        Intent intent = new Intent(GameSetupActivity.this, GameActivity.class);

                        Bundle items = new Bundle();
                        items.putSerializable("ARRAYLIST", imageButtonArrayList);
                        intent.putExtra("BUNDLE", items);

                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Please select 5 cards", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean checkIfCardsFilled() {
        int amount = 0;
        for (Boolean b : cardButtons.values()) {
            if (b == true) {
                amount++;
            }
        }
        if (amount == 5) {
            return true;
        } else {
            return false;
        }
    }

    private void fillButtonArraylist() {
        cardButtons = new HashMap<>();

        ImageButton card1 = findViewById(R.id.setupCardOne);
        ImageButton card2 = findViewById(R.id.setupCardTwo);
        ImageButton card3 = findViewById(R.id.setupCardThree);
        ImageButton card4 = findViewById(R.id.setupCardFour);
        ImageButton card5 = findViewById(R.id.setupCardFive);

        cardButtons.put(card1, false);
        cardButtons.put(card2, false);
        cardButtons.put(card3, false);
        cardButtons.put(card4, false);
        cardButtons.put(card5, false);

    }

    public void customAlertDialog(final ImageButton button) {
        final Dialog dialog = new Dialog(GameSetupActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_pop_up);

        ImageButton paper = dialog.findViewById(R.id.paperBttn);
        ImageButton rock = dialog.findViewById(R.id.rockBttn);
        ImageButton scissors = dialog.findViewById(R.id.scissorsBttn);

        rock.setEnabled(true);
        paper.setEnabled(true);
        scissors.setEnabled(true);

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " You've selected rock !", Toast.LENGTH_LONG).show();
                button.setImageResource(R.drawable.ic_launcher_background);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    cardButtons.replace(button, true);
                }

                dialog.cancel();
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " You've selected paper !", Toast.LENGTH_LONG).show();
                button.setImageResource(R.color.colorAccent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    cardButtons.replace(button, true);
                }
                dialog.cancel();
            }
        });

        scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " You've selected scissors !", Toast.LENGTH_LONG).show();
                button.setImageResource(R.color.colorPrimaryDark);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    cardButtons.replace(button, true);
                }

                dialog.cancel();
            }
        });

        dialog.show();
    }
}

