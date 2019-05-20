package com.example.madlibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class StoryView extends AppCompatActivity {

    private int storyId;
    private Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("story");
        storyId = (int) intent.getSerializableExtra("story_Id");
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

    public void clickedReset(View view) {
        Intent intent = new Intent(StoryView.this, MainActivity.class);
        intent.putExtra("reset", 0);
        startActivity(intent);
    }
}
