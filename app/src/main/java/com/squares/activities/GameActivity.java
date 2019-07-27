package com.squares.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squares.R;
import com.squares.core.ColourPack;
import com.squares.core.GameSteps;

import java.util.ArrayList;


public class GameActivity extends Activity implements Game {

    private ImageButton targetSquare;
    private GridLayout squaresTable;
    private TextView scoreView;
    private Chronometer timer;
    private ArrayList<ImageButton> squares;
    private long score;
    private static final int easyTimeMode = 5;
    private static final int mediumTimeMode = 3;
    private static final int hardTimeMode = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        squaresTable = this.findViewById(R.id.squares_table);
        scoreView = this.findViewById(R.id.score_value);
        targetSquare = this.findViewById(R.id.target_square);
        timer = this.findViewById(R.id.timer);

        scoreView.setText("0");
        squares = ColourPack.getStandart(this).getSquares(this, squareClickListener);
        toPrepareGame();
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMillis = timer.getBase() -  SystemClock.elapsedRealtime();
                if (elapsedMillis < 0) {
                    Intent intent = new Intent(getBaseContext(), GameOverActivity.class);
                    intent.putExtra("score", score);
                    onBackPressed();
                    startActivity(intent);
                }
            }
        });
        toSetTimerTime();
        timer.start();
    }

    private void toSetTimerTime(){

        int coefficient = easyTimeMode;
        if(score > 30 && score <= 60) coefficient = mediumTimeMode;
        if(score > 60) coefficient = hardTimeMode;
        timer.setBase(SystemClock.elapsedRealtime() + 1000 * coefficient);
    }

    View.OnClickListener squareClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            ColorDrawable chosenColour = (ColorDrawable) v.getBackground();
            ColorDrawable targetColour = (ColorDrawable) targetSquare.getBackground();
            if(chosenColour.getColor() == targetColour.getColor()){
                if(score == Long.MAX_VALUE){
                    Intent intent = new Intent(getBaseContext(), GameOverActivity.class);
                    intent.putExtra("score", score);
                    onBackPressed();
                    startActivity(intent);
                } else{
                    scoreView.setText(String.valueOf(++score));
                    scoreView.refreshDrawableState();
                    toUpdateGame();
                    toSetTimerTime();
                }
            }
        }
    };

    public void toPrepareGame(){

        GameSteps.toPrepareGame(this, targetSquare, squaresTable, squares);
    }

    public void toUpdateGame(){

        GameSteps.toUpdateGame(this, targetSquare, squaresTable, squares);
    }

    public void clickBackBtn(View view){
        onBackPressed();
    }
}
