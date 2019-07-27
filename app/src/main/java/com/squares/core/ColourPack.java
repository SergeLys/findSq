package com.squares.core;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.squares.R;

import java.util.ArrayList;
import java.util.Random;

public class ColourPack {

    private int[] colourCodes;
    private static ColourPack instance;

    private ColourPack(){

    }

    public static ColourPack getStandart(Context context) {
        if(instance == null){
            instance = new ColourPack();
            instance.colourCodes = context.getResources().getIntArray(R.array.standart);
        }
        return instance;
    }

    public int getRandomColour(){
        return colourCodes[new Random().nextInt(colourCodes.length)];
    }

    public ArrayList<ImageButton> getSquares(Context context, View.OnClickListener listener) {

        ArrayList<ImageButton> squares = new ArrayList<>();

        float scale = context.getResources().getDisplayMetrics().density;
        int px = (int) (40 * scale + 0.5f);
        int margin = (int) (5 * scale + 0.5f);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                                                        LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(margin, margin, margin, margin);
        lp.weight = px;
        lp.height = px;
        for(int colour : colourCodes){

            ImageButton square = new ImageButton(context);
            square.setBackgroundColor(colour);
            square.setMinimumWidth(px);
            square.setMinimumHeight(px);
            square.setLayoutParams(lp);
            square.setOnClickListener(listener);
            squares.add(square);
        }
        return squares;
    }

    public void setStandartColours(ArrayList<ImageButton> squares) {

        for(int i = 0; i < colourCodes.length; i++)
            squares.get(i).setBackgroundColor(colourCodes[i]);
    }
}
