package com.example.madlibs;
/**
 * The StoryView activity for the app.
 * In this activity the complete story made by the user will be displayed. There is also a reset
 * button that brings the user back to the MainActivity to pick a new story.
 */

// List of imports.
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class StoryView extends AppCompatActivity {

    private int storyId;
    private Story story;

    /*
    * In the onCreate the story is received from the intent and the layout story_view as well as the
     * TextViews are set to display the story.
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_view);

        Intent intent = getIntent();

        story = (Story) intent.getSerializableExtra("story");
        storyId = (int) intent.getSerializableExtra("story_Id");

        TextView title = findViewById(R.id.storyTitle);
        TextView storyText = findViewById(R.id.story);

        storyText.setText(story.toString());

        switch (storyId){
            case R.raw.madlib0_simple:
                title.setText("Simple");
                break;
            case R.raw.madlib1_tarzan:
                title.setText("Tarzan");
                break;
            case R.raw.madlib2_university:
                title.setText("University");
                break;
            case R.raw.madlib3_clothes:
                title.setText("Clothes");
                break;
            case R.raw.madlib4_dance:
                title.setText("Dance");
                break;
        }
    }

    // Method that resets the board if the resetButton has been clicked.
    public void clickedReset(View view) {
        Intent intent = new Intent(StoryView.this, MainActivity.class);

        // The putExtra gives the parameters to initiate a reset in the Story class.
        intent.putExtra("reset", 0);
        startActivity(intent);
    }
}
