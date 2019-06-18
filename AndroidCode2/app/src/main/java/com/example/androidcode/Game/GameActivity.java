package com.example.androidcode.Game;

import android.content.Intent;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.androidcode.R;
import com.example.androidcode.StartUp.HomeScreenActivity;
import com.tomer.fadingtextview.FadingTextView;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Random;

public class GameActivity extends AppCompatActivity
{

    private int playerScore = 0;
    private int comScore = 0;

    int counter = 0;

    int rockImage = R.drawable.rock;
    int paperImage = R.drawable.paper;
    int scissorImage = R.drawable.scissors;

    TextView scoreboard;

    FadingTextView gameInfoFade;

    ImageButton exitBttn;

    ImageButton cardOne;
    ImageButton cardTwo;
    ImageButton cardThree;
    ImageButton cardFour;
    ImageButton cardFive;

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
        setContentView(R.layout.activity_game);

        gameInfoFade = findViewById(R.id.fading_game_info);

        cardOne = findViewById(R.id.gameCardOne);
        cardTwo = findViewById(R.id.gameCardTwo);
        cardThree = findViewById(R.id.gameCardThree);
        cardFour = findViewById(R.id.gameCardFour);
        cardFive = findViewById(R.id.gameCardFive);

        scoreboard = findViewById(R.id.scoreboard);

        exitBttn = findViewById(R.id.gameExitBttn);

        gameInfoFade.setTexts(R.array.game_info_fade);

        Intent intent = getIntent();
        HashMap<String, String> cards = (HashMap<String, String>) intent.getSerializableExtra("map");

        connectCards(cards);

        exitBttn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(GameActivity.this, HomeScreenActivity.class));
                GameActivity.this.finishAfterTransition();
            }
        });

    }

    private int getTypeInt(String type)
    {
        if (type.equals("ROCK"))
        {
            return 0;
        }
        else if (type.equals("PAPER"))
        {
            return 1;
        }
        else if (type.equals("SCISSORS"))
        {
            return 2;
        }
        return -1;
    }

    private void connectCards(final HashMap<String, String> cards)
    {
        for (final String nr : cards.keySet())
        {
            switch (nr)
            {
                case "ONE":
                    setImages(cardOne, cards.get(nr));
                    cardOne.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            checkGameState(cardOne, getTypeInt(cards.get(nr)));
                        }
                    });
                    break;
                case "TWO":
                    setImages(cardTwo, cards.get(nr));
                    cardTwo.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            checkGameState(cardTwo, getTypeInt(cards.get(nr)));
                        }
                    });
                    break;
                case "THREE":
                    setImages(cardThree, cards.get(nr));
                    cardThree.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            checkGameState(cardThree, getTypeInt(cards.get(nr)));
                        }
                    });
                    break;
                case "FOUR":
                    setImages(cardFour, cards.get(nr));
                    cardFour.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            checkGameState(cardFour, getTypeInt(cards.get(nr)));
                        }
                    });
                    break;
                case "FIVE":
                    setImages(cardFive, cards.get(nr));
                    cardFive.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            checkGameState(cardFive, getTypeInt(cards.get(nr)));
                        }
                    });
                    break;
            }
        }
    }

    private void setImages(ImageButton button, String type)
    {
        switch (type)
        {
            case "ROCK":
                button.setImageResource(rockImage);
                break;
            case "PAPER":
                button.setImageResource(paperImage);
                break;
            case "SCISSORS":
                button.setImageResource(scissorImage);
                break;
        }
    }

    private void checkGameState(ImageButton button, final int i)
    {

        counter++;
        int random = new Random().nextInt(2);
        button.setClickable(false);
        whoWon(i, random);
        scoreboard.setText(playerScore + " - " + comScore);

        continueGame();

    }


    private void whoWon(int player, int random)
    {
        String[] matchup = new String[1];

        if (player != random)
        {                                                                      // 0 = rock , 1 = paper, 2 = scissors
            if (player == 0 && random == 2)
            {
                matchup[0] = "Rock beats Scissors!";
                gameInfoFade.setTexts(matchup);
                playerScore++;
                return;
            }
            if (player == 1 && random == 0)
            {
                matchup[0] = "Paper beats Rock!";
                gameInfoFade.setTexts(matchup);
                playerScore++;
                return;
            }
            if (player == 2 && random == 1)
            {
                matchup[0] = "Scissors beats Paper!";
                gameInfoFade.setTexts(matchup);
                playerScore++;
                return;
            }

            if (player == 0 && random == 1)
            {
                matchup[0] = "Paper beats Rock!";
                gameInfoFade.setTexts(matchup);
                comScore++;
                return;
            }
            if (player == 1 && random == 2)
            {
                matchup[0] = "Scissors beats Paper!";
                gameInfoFade.setTexts(matchup);
                comScore++;
                return;
            }
            if (player == 2 && random == 0)
            {
                matchup[0] = "Rock beats Scissors!";
                gameInfoFade.setTexts(matchup);
                comScore++;
                return;
            }
        }
        matchup[0] = "It's a draw";
        gameInfoFade.setTexts(matchup);
    }

    private void continueGame()
    {

        if (counter != 5)
        {

        } else
        {
            if (playerScore > comScore)
            {
                gameInfoFade.setTexts(R.array.player_wins);
            }
            if (playerScore < comScore)
            {
                gameInfoFade.setTexts(R.array.com_wins);
            }
            if (playerScore == comScore)
            {
                gameInfoFade.setTexts(R.array.noone_wins);
            }
        }
    }
}

