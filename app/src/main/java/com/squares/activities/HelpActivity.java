package com.squares.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.squares.R;

public class HelpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public void clickBackBtn(View view){
        onBackPressed();
    }
}
