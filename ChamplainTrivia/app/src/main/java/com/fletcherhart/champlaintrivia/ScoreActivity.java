package com.fletcherhart.champlaintrivia;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ScoreActivity extends AppCompatActivity {

    private static final String SCORE =
            "com.fletcher.SCORE";

    private static int finalScore = 0;

    public static Intent newIntent(Context packageContext, int mScore) {
        Intent i = new Intent(packageContext, ScoreActivity.class);
        i.putExtra(SCORE, mScore);
        finalScore = mScore;
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreview);

        TextView text = (TextView) findViewById(R.id.help);

        System.out.println("Hello world");
    }
}
