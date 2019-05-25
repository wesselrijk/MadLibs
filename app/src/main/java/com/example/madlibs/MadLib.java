package com.example.madlibs;
/**
 * The MadLib activity for the app.
 * In this activity the user will have to input placeholders that are used for the story. The
 * nextButton will give the u ser the next placeholder to be filled. If there are no more
 * placeholders to be filled, which is checked through the isFilledIn in the Story
 * class, the nextButton will create an intent to start the StoryView activity.
 */

// List of imports.
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.io.InputStream;

public class MadLib extends AppCompatActivity {

    private int storyId;
    private Story story;
    private EditText editText;
    private TextView wordCounter;
    private int counter;
    private int layoutId = R.layout.fill_placeholders;

    // In the onCreate, the fill_placeholders layout is being set and the story will be loaded.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if there is a story saved in the savedInstanceState
        if (savedInstanceState != null) {
            layoutId = savedInstanceState.getInt("layout_id", R.layout.fill_placeholders);
            story = (Story) savedInstanceState.getSerializable("story");
        } else {

            // Set a new story using the parameters obtained from the MainActivity.
            Intent intent = getIntent();

            storyId = (int) intent.getSerializableExtra("story_Id");
            InputStream is = getResources().openRawResource(storyId);
            story = new Story(is);
        }

        setContentView(layoutId);

        // Set parameters.
        editText = findViewById(R.id.editText);
        wordCounter = findViewById(R.id.wordsToGo);

        updateText();
    }

    // Method that activated when the nextButton is clicked.
    public void clickedNext(View view) {

        // Sets filled in placeholder to the story,empties the input and gets the next placeholder.
        story.fillInPlaceholder(editText.getText().toString());
        story.getNextPlaceholder();
        editText.setText("");

        updateText();
        checkRemaining();
    }

    // Method that is activated when the resetButton is clicked.
    public void clickedReset(View view) {

        // Go back to the MainActivity and resets the story.
        Intent intent = new Intent(MadLib.this, MainActivity.class);
        intent.putExtra("reset", 0);
        startActivity(intent);
    }

    // Method that updates the displayed nouns to be filled and hint for the current placeholder.
    private void updateText() {
        counter = story.getPlaceholderRemainingCount();
        wordCounter.setText(counter + " nouns left to fill in.");
        editText.setHint(story.getNextPlaceholder());
    }

    // Method that checks if all placeholders are filled, if so: start StoryView activity.
    private void checkRemaining() {
        if (story.isFilledIn()) {
            Intent intent = new Intent(MadLib.this, StoryView.class);
            intent.putExtra("story", story);
            intent.putExtra("story_Id", storyId);
            startActivity(intent);
            finish();
        }
    }

    // Save the current view in the onSaveInstanceState.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("layout_id", layoutId);
        outState.putSerializable("story", story);
    }
}
