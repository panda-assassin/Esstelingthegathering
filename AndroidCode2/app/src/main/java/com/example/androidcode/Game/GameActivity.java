package com.example.androidcode.Game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.androidcode.R;
import com.example.androidcode.StartUp.HomeScreenActivity;
import com.tomer.fadingtextview.FadingTextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private int playerScore = 0;
    private int comScore = 0;

    int counter = 0;

    int rockImage = R.drawable.ic_launcher_background;
    int paperImage = R.color.colorAccent;
    int scissorImage = R.color.colorPrimaryDark;

    TextView scoreboard;

    FadingTextView gameInfoFade;

    ImageButton exitBttn;

//    ArrayList<ImageButton> cardButtons;

    ImageButton cardOne;
    ImageButton cardTwo;
    ImageButton cardThree;
    ImageButton cardFour;
    ImageButton cardFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int currentOrientation = this.getResources().getConfiguration().orientation;
//        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT){
//            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }
//        else{
//            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }
        setContentView(R.layout.activity_game);

        gameInfoFade = findViewById(R.id.fading_game_info);

        cardOne = findViewById(R.id.gameCardOne);
        cardTwo = findViewById(R.id.gameCardTwo);
        cardThree = findViewById(R.id.gameCardThree);
        cardFour = findViewById(R.id.gameCardFour);
        cardFive = findViewById(R.id.gameCardFive);

        scoreboard = findViewById(R.id.scoreboard);

        cardOne.setImageResource(rockImage);
        cardTwo.setImageResource(rockImage);
        cardThree.setImageResource(scissorImage);
        cardFour.setImageResource(paperImage);
        cardFive.setImageResource(scissorImage);

        exitBttn = findViewById(R.id.gameExitBttn);

        gameInfoFade.setTexts(R.array.game_info_fade);

//        Intent intent = getIntent();
//        Bundle items = intent.getBundleExtra("BUNDLE");
//        cardButtons = (ArrayList<ImageButton>) items.getSerializable("ARRAYLIST");

//        cardOne = cardButtons.get(0);
//        cardTwo = cardButtons.get(1);
//        cardThree = cardButtons.get(2);
//        cardFour = cardButtons.get(3);
//        cardFive = cardButtons.get(4);


        exitBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameActivity.this, HomeScreenActivity.class));
                GameActivity.this.finishAfterTransition();
            }
        });

        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGameState(cardOne, 0);
            }
        });

        cardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGameState(cardTwo, 0);

            }
        });

        cardThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGameState(cardThree, 2);

            }
        });

        cardFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGameState(cardFour, 1);
            }
        });

        cardFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGameState(cardFive, 1);
            }
        });
    }

    private void checkGameState(ImageButton button, final int i) {

        counter++;
        int random = new Random().nextInt(2);
        button.setClickable(false);
//            gameInfoFade.setTexts(R.array.game_wait_fade);
        whoWon(i, random);
        scoreboard.setText(playerScore + " - " + comScore);

//       new CountDownTimer(1500, 1000) {
//           @Override
//           public void onTick(long millisUntilFinished) {
//           }
//
//           @Override
//           public void onFinish() {
//               continueGame();
//           }
//       };

        continueGame();

    }


    private void whoWon(int player, int random) {
        String[] matchup = new String [1];

        if (player != random) {                                                                      // 0 = rock , 1 = paper, 2 = scissors
            if (player == 0 && random == 2) {
                matchup[0] = "Rock beats Scissors!";
                gameInfoFade.setTexts(matchup);
                playerScore++;
                return;
            }
            if (player == 1 && random == 0) {
                matchup[0] = "Paper beats Rock!";
                gameInfoFade.setTexts(matchup);
                playerScore++;
                return;
            }
            if (player == 2 && random == 1) {
                matchup[0] = "Scissors beats Paper!";
                gameInfoFade.setTexts(matchup);
                playerScore++;
                return;
            }

            if (player == 0 && random == 1) {
                matchup[0] = "Paper beats Rock!";
                gameInfoFade.setTexts(matchup);
                playerScore++;
                return;
            }
            if (player == 1 && random == 2) {
                matchup[0] = "Scissors beats Paper!";
                gameInfoFade.setTexts(matchup);
                playerScore++;
                return;
            }
            if (player == 2 && random == 0) {
                matchup[0] = "Rock beats Scissors!";
                gameInfoFade.setTexts(matchup);
                playerScore++;
                return;
            }
        }
        matchup[0] ="It's a draw";
        gameInfoFade.setTexts(matchup);
    }

    private void continueGame() {

        if (counter != 5) {

        } else {
            if (playerScore > comScore) {
                gameInfoFade.setTexts(R.array.player_wins);
            }
            if (playerScore < comScore) {
                gameInfoFade.setTexts(R.array.com_wins);
            }
            if (playerScore == comScore) {
                gameInfoFade.setTexts(R.array.noone_wins);
            }
        }
    }
}

