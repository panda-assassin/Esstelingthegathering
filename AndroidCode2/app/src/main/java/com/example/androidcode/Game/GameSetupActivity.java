package com.example.androidcode.Game;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Configuration;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.androidcode.R;

import java.util.ArrayList;
import java.util.HashMap;

public class GameSetupActivity extends AppCompatActivity
{

    HashMap<ImageButton, Boolean> booleanSetHashmap;
    HashMap<String, String> selectedImagesHashmap;
    private ImageButton card1;
    private ImageButton card2;
    private ImageButton card3;
    private ImageButton card4;
    private ImageButton card5;

    int rockImage = R.drawable.rock;
    int paperImage = R.drawable.paper;
    int scissorImage = R.drawable.scissors;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        int currentOrientation = this.getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT)
        {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else
        {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.activity_game_setup);

        this.card1 = findViewById(R.id.setupCardOne);
        this.card2 = findViewById(R.id.setupCardTwo);
        this.card3 = findViewById(R.id.setupCardThree);
        this.card4 = findViewById(R.id.setupCardFour);
        this.card5 = findViewById(R.id.setupCardFive);

        selectedImagesHashmap = new HashMap<>();
        booleanSetHashmap = new HashMap<>();


        Button startGame = findViewById(R.id.setupStartGameBttn);

        fillButtonArraylist();
        setOnClickListeners();


        startGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (checkIfCardsFilled())
                {
                    Intent intent = new Intent(GameSetupActivity.this, GameActivity.class);
                    intent.putExtra("map", selectedImagesHashmap);

                    startActivity(intent);

                } else
                {
                    Toast.makeText(getApplicationContext(), "Please select 5 cards", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setOnClickListeners()
    {
        this.card1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                customAlertDialog(card1, "ONE");
            }
        });
        this.card2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                customAlertDialog(card2, "TWO");
            }
        });
        this.card3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                customAlertDialog(card3, "THREE");
            }
        });
        this.card4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                customAlertDialog(card4, "FOUR");
            }
        });
        this.card5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                customAlertDialog(card5, "FIVE");
            }
        });
    }

    private boolean checkIfCardsFilled()
    {
        int amount = 0;
        for (Boolean b : booleanSetHashmap.values())
        {
            if (b == true)
            {
                amount++;
            }
        }
        if (amount == 5)
        {
            return true;
        } else
        {
            return false;
        }
    }

    private void fillButtonArraylist()
    {
        booleanSetHashmap.put(card1, false);
        booleanSetHashmap.put(card2, false);
        booleanSetHashmap.put(card3, false);
        booleanSetHashmap.put(card4, false);
        booleanSetHashmap.put(card5, false);

        selectedImagesHashmap.put("ONE", "");
        selectedImagesHashmap.put("TWO", "");
        selectedImagesHashmap.put("THREE", "");
        selectedImagesHashmap.put("FOUR", "");
        selectedImagesHashmap.put("FIVE", "");

    }

    public void customAlertDialog(final ImageButton button, final String cardNo)
    {
        final Dialog dialog = new Dialog(GameSetupActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_pop_up);

        ImageButton paper = dialog.findViewById(R.id.paperBttn);
        ImageButton rock = dialog.findViewById(R.id.rockBttn);
        ImageButton scissors = dialog.findViewById(R.id.scissorsBttn);

        rock.setImageResource(rockImage);
        paper.setImageResource(paperImage);
        scissors.setImageResource(scissorImage);

        rock.setEnabled(true);
        paper.setEnabled(true);
        scissors.setEnabled(true);

        rock.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), " You've selected rock !", Toast.LENGTH_LONG).show();
                button.setImageResource(rockImage);
                booleanSetHashmap.put(button, true);
                selectedImagesHashmap.put(cardNo, "ROCK");

                dialog.cancel();
            }
        });

        paper.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), " You've selected paper !", Toast.LENGTH_LONG).show();
                button.setImageResource(paperImage);
                booleanSetHashmap.put(button, true);
                selectedImagesHashmap.put(cardNo, "PAPER");

                dialog.cancel();
            }
        });

        scissors.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), " You've selected scissors !", Toast.LENGTH_LONG).show();
                button.setImageResource(scissorImage);
                booleanSetHashmap.put(button, true);
                selectedImagesHashmap.put(cardNo, "SCISSORS");

                dialog.cancel();
            }
        });

        dialog.show();
    }
}

