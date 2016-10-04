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

    private static final String QUESTIONS =
            "com.fletcher.QUESTIONS";

    public static int mCategory;

    public static Intent newIntent(Context packageContext, int position) {
        Intent i = new Intent(packageContext, QuestionActivity.class);
        i.putExtra(QUESTIONS, position);
        mCategory = position;
        return i;
    }
}
