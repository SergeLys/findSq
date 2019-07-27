package com.squares.core;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class GameSteps {

    private static Random random;
    private static int previousMode = 0;

    public static void toPrepareGame(Context context,
                                    ImageButton targetSquare,
                                    GridLayout squaresTable,
                                    ArrayList<ImageButton> squares){

        int targetColour = ColourPack.getStandart(context).getRandomColour();
        targetSquare.setBackgroundColor(targetColour);
        for(ImageButton square : squares)
            squaresTable.addView(square);
    }

    public static void toUpdateGame(Context context,
                                    ImageButton targetSquare,
                                    GridLayout squaresTable,
                                    ArrayList<ImageButton> squares){

        squaresTable.removeAllViews();
        if(random == null) random = new Random();
        int gameMode = random.nextInt(10);
        if(gameMode == 1){
            getOneOtherMode(context, targetSquare, squaresTable, squares);
            previousMode = gameMode;
        }
        else if(gameMode == 2){
            getGridMode(context, targetSquare, squaresTable, squares);
            previousMode = gameMode;
        }
        else if(gameMode == 3) {
            getHorizontalLinesMode(context, targetSquare, squaresTable, squares);
            previousMode = gameMode;
        }
        else if(gameMode == 4) {
            getVerticalLinesMode(context, targetSquare, squaresTable, squares);
            previousMode = gameMode;
        }
        else if(gameMode == 5) {
            ColourPack.getStandart(context).setStandartColours(squares);
            getMixMode(context, targetSquare, squaresTable, squares);
        }
        else{
            int targetColour = ColourPack.getStandart(context).getRandomColour();
            targetSquare.setBackgroundColor(targetColour);
            int targetColourCount = random.nextInt(squares.size());
            Collections.shuffle(squares);
            for(ImageButton square : squares) squaresTable.addView(square);
            squares.get(targetColourCount).setBackgroundColor(targetColour);
        }
    }

    private static void getMixMode(Context context,
                                   ImageButton targetSquare,
                                   GridLayout squaresTable,
                                   ArrayList<ImageButton> squares){

        int targetColour = ColourPack.getStandart(context).getRandomColour();
        targetSquare.setBackgroundColor(targetColour);
        Collections.shuffle(squares);
        for(ImageButton square : squares) squaresTable.addView(square);
    }

    private static void getHorizontalLinesMode(Context context,
                                               ImageButton targetSquare,
                                               GridLayout squaresTable,
                                               ArrayList<ImageButton> squares){

        int targetColour = ColourPack.getStandart(context).getRandomColour();
        int falseColour = ColourPack.getStandart(context).getRandomColour();
        int targetColourCount = random.nextInt(squares.size());
        targetSquare.setBackgroundColor(targetColour);
        int oldRow = 1, newRow = 1;
        for(int i = 0; i < squares.size(); i++) {
            if(i % 6 == 0) newRow++;
            if(oldRow != newRow){
                oldRow = newRow;
                falseColour = ColourPack.getStandart(context).getRandomColour();
            }
            squares.get(i).setBackgroundColor(falseColour);
            squaresTable.addView(squares.get(i));
        }
        squares.get(targetColourCount).setBackgroundColor(targetColour);
    }

    private static void getVerticalLinesMode(Context context,
                                             ImageButton targetSquare,
                                             GridLayout squaresTable,
                                             ArrayList<ImageButton> squares){

        int targetColour = ColourPack.getStandart(context).getRandomColour();
        int firstColour = ColourPack.getStandart(context).getRandomColour();
        int secondColour = ColourPack.getStandart(context).getRandomColour();
        int targetColourCount = random.nextInt(squares.size());
        targetSquare.setBackgroundColor(targetColour);
        boolean isFirstColour = true;
        for(int i = 0; i < squares.size(); i++) {
            if(isFirstColour) {
                squares.get(i).setBackgroundColor(firstColour);
                isFirstColour = false;
            } else {
                squares.get(i).setBackgroundColor(secondColour);
                isFirstColour = true;
            }
            squaresTable.addView(squares.get(i));
        }
        squares.get(targetColourCount).setBackgroundColor(targetColour);
    }

    private static void getGridMode(Context context,
                                               ImageButton targetSquare,
                                               GridLayout squaresTable,
                                               ArrayList<ImageButton> squares){

        int targetColour = ColourPack.getStandart(context).getRandomColour();
        int firstColour = ColourPack.getStandart(context).getRandomColour();
        int secondColour = ColourPack.getStandart(context).getRandomColour();
        int targetColourCount = random.nextInt(squares.size());
        targetSquare.setBackgroundColor(targetColour);
        boolean isFirstColour = true;
        for(int i = 0; i < squares.size(); i++) {
            if(isFirstColour) {
                squares.get(i).setBackgroundColor(firstColour);
                isFirstColour = false;
            } else {
                squares.get(i).setBackgroundColor(secondColour);
                isFirstColour = true;
            }
            if((i + 1) % 6 == 0) isFirstColour = !isFirstColour;
            squaresTable.addView(squares.get(i));
        }
        squares.get(targetColourCount).setBackgroundColor(targetColour);
    }

    private static void getOneOtherMode(Context context,
                                    ImageButton targetSquare,
                                    GridLayout squaresTable,
                                    ArrayList<ImageButton> squares){

        int targetColour = ColourPack.getStandart(context).getRandomColour();
        int falseColour = ColourPack.getStandart(context).getRandomColour();
        targetSquare.setBackgroundColor(targetColour);
        for(ImageButton square : squares) {
            square.setBackgroundColor(falseColour);
            squaresTable.addView(square);
        }
        squares.get(0).setBackgroundColor(targetColour);
        Collections.shuffle(squares);
    }
}
