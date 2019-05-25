package com.example.madlibs;
/**
 * <h1>Restuarant</h1>
 * The MainActivity for the MadLIB app.
 * The MadLib app is an app where the user can play a game that asks the user to fill in
 * placeholders that are filled into a story that the user has not seen yet.
 * The user first picks starts the app by clicking on the start button, then a new layout will be
 * displayed where the user can pick the story they want to use for the game. Clicking on a
 * storyButton corresponding to a story will send the user to the MadLib activity, where they can
 * fill in the placeholders. After all placeholders are filled the StoryView activity will start
 * and the complete story will be displayed.
 */

// List of imports.
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    /*
    * Using a layoutId integer, as suggested by an answer on stackoverflow, to remember which layout
    * is currently presented, source: https://stackoverflow.com/questions/43876667/
    * how-to-save-instance-state-of-a-layout-in-android
     */
    int layoutId = R.layout.activity_main;


    // onCreate calls super and checks if there is a saved instance state.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set MainActivity layout to activity_main or story_options whichever is saved.
        if (savedInstanceState != null) {
            layoutId = savedInstanceState.getInt("layout_id", R.layout.activity_main);
        } else {
            Intent intent = getIntent();
            if (intent.getSerializableExtra("reset") != null) {
                layoutId = R.layout.story_options;
            }
        }

        // Set stored layoutID.
        setContentView(layoutId);
    }

    // Method to set story_options layout if the startButton is clicked.
    public void startButtonClicked(View view) {
        layoutId = R.layout.story_options;
        setContentView(R.layout.story_options);
    }

    // Method to start a new intent when a storyButton is clicked.
    public void storyButtonClicked(View view) {
        int id = view.getId();
        int storyId = 0;

        // switch over the id's of the storyButtons to find the corresponding story.
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

    // onSaveInstanceState for this activity saves the layout that is currently displayed.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current layout in the onSaveInstanceState.
        outState.putSerializable("layout_id", layoutId);
    }
}
