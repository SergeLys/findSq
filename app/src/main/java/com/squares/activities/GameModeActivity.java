package com.squares.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.squares.R;
import com.squares.core.Data;

public class GameModeActivity extends Activity {

    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        TextView bestScore = this.findViewById(R.id.best_score);
        long score = 0;
        mSettings = getSharedPreferences(Data.APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(Data.APP_COUNTER)) {
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putLong(Data.APP_COUNTER, 0);
            editor.apply();
            score =  mSettings.getLong(Data.APP_COUNTER,0);
        } else{
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putLong(Data.APP_COUNTER, 0);
            editor.apply();
        }
        if(score == Long.MAX_VALUE)
            bestScore.setText(R.string.king_score);
        else
            bestScore.setText(String.valueOf(score));
    }

    public void clickTrainingBtn(View view){

        Intent intent = new Intent(this, TrainingActivity.class);
        startActivity(intent);
    }

    public void click6x6Btn(View view){

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        TextView bestScore = this.findViewById(R.id.best_score);
        long score = mSettings.getLong(Data.APP_COUNTER,0);
        if(score == Long.MAX_VALUE)
            bestScore.setText(R.string.king_score);
        else
            bestScore.setText(String.valueOf(score));
    }

    public void clickBackBtn(View view){
        onBackPressed();
    }
}
