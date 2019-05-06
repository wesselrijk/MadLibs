package com.example.madlibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    }

    private void updateText() {
        counter = story.getPlaceholderRemainingCount();
        wordCounter.setText(counter + " nouns left to fill in.");
        editText.setHint(story.getNextPlaceholder());
    }

    private void checkRemaining() {
        if (story.isFilledIn()) {
            setContentView(R.layout.story_view);
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
    }
}
