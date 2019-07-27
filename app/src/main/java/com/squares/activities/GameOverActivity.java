package com.squares.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.squares.R;
import com.squares.core.Data;

public class GameOverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        TextView score = this.findViewById(R.id.game_over_score_value);
        Bundle arguments = getIntent().getExtras();

        if(arguments!=null){

            SharedPreferences mSettings = getSharedPreferences(Data.APP_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSettings.edit();
            long newScore = arguments.getLong("score");
            if(newScore == Long.MAX_VALUE)
                score.setText(String.valueOf("King"));
            else
                score.setText(String.valueOf(newScore));
            long oldScore =  mSettings.getLong(Data.APP_COUNTER,0);
            if(newScore > oldScore){
                    editor.putLong(Data.APP_COUNTER, newScore);
                    editor.apply();
            }
        }

    }

    public void clickRetryBtn(View view){

        finish();
    }
}
