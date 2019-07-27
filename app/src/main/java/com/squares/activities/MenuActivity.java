package com.squares.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.squares.R;

public class MenuActivity extends Activity {

    private static SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void clickStartBtn(View view){

        Intent intent = new Intent(this, GameModeActivity.class);
        startActivity(intent);
    }

    public void clickHelpBtn(View view){

        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void clickExitBtn(View view){

        finishAffinity();
    }
}
