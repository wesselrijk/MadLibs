package com.example.madlibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;

public class MadLib extends AppCompatActivity {

    private int storyId;
    private Story story;
    private EditText editText;
    private TextView wordCounter;
    private int counter;
    private int layoutId = R.layout.fill_placeholders;

    // in the onCreate, the fill_placeholders layout is being set and the story will be loaded
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            layoutId = savedInstanceState.getInt("layout_id", R.layout.fill_placeholders);
            story = (Story) savedInstanceState.getSerializable("story");
        } else {
            // set a story using the parameters obtained from the mainactivity
            Intent intent = getIntent();
            storyId = (int) intent.getSerializableExtra("story_Id");
            InputStream is = getResources().openRawResource(storyId);
            story = new Story(is);
        }
        setContentView(layoutId);

        editText = findViewById(R.id.editText);
        wordCounter = findViewById(R.id.wordsToGo);
        updateText();
    }

    public void clickedNext(View view) {
        story.fillInPlaceholder(editText.getText().toString());
        story.getNextPlaceholder();
        editText.setText("");
        updateText();
        checkRemaining();
    }

    public void clickedBack(View view) {
    }

    public void clickedReset(View view) {
        Intent intent = new Intent(MadLib.this, MainActivity.class);
        intent.putExtra("reset", 0);
        startActivity(intent);
    }

    private void updateText() {
        counter = story.getPlaceholderRemainingCount();
        wordCounter.setText(counter + " nouns left to fill in.");
        editText.setHint(story.getNextPlaceholder());
    }

    private void checkRemaining() {
        if (story.isFilledIn()) {
            Intent intent = new Intent(MadLib.this, StoryView.class);
            intent.putExtra("story", story);
            intent.putExtra("story_Id", storyId);
            startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save the current view in the onSaveInstanceState
        outState.putSerializable("layout_id", layoutId);
        outState.putSerializable("story", story);
    }
}
