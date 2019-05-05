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
        int storyId = 0;

        switch(id) {
            case R.id.simpleButton:
                storyId = R.raw.madlib0_simple;
                break;
            case R.id.tarzanButton:
                storyId = R.raw.madlib1_tarzan;
                break;
            case R.id.universityButton:
                storyId = R.raw.madlib2_university;
                break;
            case R.id.clothesButton:
                storyId = R.raw.madlib3_clothes;
                break;
            case R.id.danceButton:
                storyId = R.raw.madlib4_dance;
                break;
        }

        Intent intent = new Intent(MainActivity.this, MadLib.class);
        intent.putExtra("story_Id", storyId);
        startActivity(intent);
    }
}
