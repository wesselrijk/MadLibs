package com.example.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    /*
    using a layoutId integer, as suggested by an answer on stackoverflow, to remember which layout
    is currently presented (source: https://stackoverflow.com/questions/43876667/
    how-to-save-instance-state-of-a-layout-in-android)
     */
    int layoutId = R.layout.activity_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            layoutId = savedInstanceState.getInt("layout_id", R.layout.activity_main);
        } else {
            Intent intent = getIntent();
            if (intent.getSerializableExtra("reset") != null) {
                // Log.d("myTag", "message"); TODO
                layoutId = R.layout.story_options;
            }
        }
        setContentView(layoutId);
    }

    public void startButtonClicked(View view) {
        layoutId = R.layout.story_options;
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save the current view in the onSaveInstanceState
        outState.putSerializable("layout_id", layoutId);
    }
}
