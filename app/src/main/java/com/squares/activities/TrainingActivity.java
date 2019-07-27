package com.squares.activities;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.squares.R;
import com.squares.core.ColourPack;
import com.squares.core.GameSteps;

import java.util.ArrayList;


public class TrainingActivity extends Activity implements Game {

    private ImageButton targetSquare;
    private GridLayout squaresTable;
    private ArrayList<ImageButton> squares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        squaresTable = this.findViewById(R.id.squares_table);
        targetSquare = this.findViewById(R.id.target_square);
        squares = ColourPack.getStandart(this).getSquares(this, squareClickListener);
        toPrepareGame();
    }

    View.OnClickListener squareClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            ColorDrawable chosenColour = (ColorDrawable) v.getBackground();
            ColorDrawable targetColour = (ColorDrawable) targetSquare.getBackground();
            if(chosenColour.getColor() == targetColour.getColor()) toUpdateGame();
        }
    };

    public void clickBackBtn(View view){
        onBackPressed();
    }

    public void toPrepareGame(){

        GameSteps.toPrepareGame(this, targetSquare, squaresTable, squares);
    }

    public void toUpdateGame(){

        GameSteps.toUpdateGame(this, targetSquare, squaresTable, squares);
    }
}
