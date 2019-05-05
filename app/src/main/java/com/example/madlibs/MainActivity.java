package com.example.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startButtonClicked(View view) {

        setContentView(R.layout.story_options);
    }

    public void storyButtonClicked(View view) {
        int id = view.getId();

        switch(id) {
            case R.id.simpleButton:
                break;
            case R.id.tarzanButton:
                break;
            case R.id.universityButton:
                break;
            case R.id.clothesButton:
                break;
            case R.id.danceButton:
                break;
        }
        setContentView(R.layout.fill_placeholders);
    }
}
