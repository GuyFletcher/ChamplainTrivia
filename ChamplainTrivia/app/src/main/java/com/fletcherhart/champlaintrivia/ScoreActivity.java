package com.fletcherhart.champlaintrivia;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScoreActivity extends AppCompatActivity {

    private static final String SCORE =
            "com.fletcher.SCORE";
    private static final String CATEGORY =
            "com.fletcher.CATEGORY";
    private Button saveButton;
    private EditText editText;


    private static int finalScore = 0;
    private static int fCategory = 0;
    public static Intent newIntent(Context packageContext, int mScore, int mCategory) {
        Intent i = new Intent(packageContext, ScoreActivity.class);
        i.putExtra(SCORE, mScore);
        i.putExtra(CATEGORY, mCategory);
        finalScore = mScore;
        fCategory = mCategory;
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreview);

        TextView text = (TextView) findViewById(R.id.help);
        saveButton = (Button) findViewById(R.id.save_score);
        editText = (EditText) findViewById(R.id.name);
        text.setText("You guessed " + String.valueOf(finalScore) + " questions correctly!" );


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FILENAME = "high_scores_"+fCategory;
                String information =  "Name: " + editText.getText().toString() + " Score: " + Integer.toString(finalScore);

                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    fos.write(information.getBytes());
                    System.out.println("File Saved!");
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
