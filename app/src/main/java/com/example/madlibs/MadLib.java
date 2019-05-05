package com.example.madlibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;

public class MadLib extends AppCompatActivity {

    private int storyId;
    Story story;
    EditText editText = findViewById(R.id.editText);
    TextView wordCounter = findViewById(R.id.wordsToGo);

    // in the onCreate, the fill_placeholders layout is being set and the story will be loaded
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_placeholders);

        // set a story using the parameters obtained from the mainactivity
        Intent intent = getIntent();
        storyId = (int) intent.getSerializableExtra("story_Id");
        InputStream is = getResources().openRawResource(storyId);
        story = new Story(is);
        editText.setHint(story.getNextPlaceholder());

        updateCounterText();
    }

    public void clickedNext(View view) {
        story.fillInPlaceholder(editText.getText().toString());
    }

    public void clickedBack(View view) {
    }

    public void clickedReset(View view) {
    }

    private void updateCounterText() {
        int counter = story.getPlaceholderRemainingCount();
        wordCounter.setText(counter + " nouns left to fill in.");
    }
}
