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

public class QuestionActivity extends AppCompatActivity {

    private static final String QUESTIONS =
            "com.fletcher.QUESTIONS";

    private ImageButton mChoice, mChoice2;
    private Button mChoice3, mChoice4, mChoice5,mChoice6,mChoice7, mChoice8,mChoice9,mChoice10,mNextButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex, mScore=0;
    public static int mCategory;
    MediaPlayer mediaPlayer;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_street, true),
            new Question(R.string.question_statue, true),
            new Question(R.string.question_aiken, false),
            new Question(R.string.question_quad, false),
            new Question(R.string.question_spinner, true),
            new Question(R.string.question_idx, false),
            new Question(R.string.question_ccm, true),
            new Question(R.string.question_lake, false),
            new Question(R.string.question_perry, false),
            new Question(R.string.question_miller, false)
    };

    private Question[] mQuestionPeople = new Question[] {
            new Question(R.string.question_me, 1),
            new Question(R.string.question_prof, 0),
            new Question(R.string.question_laak, 5),
            new Question(R.string.question_wei, 5),
            new Question(R.string.question_bhall, 2),
            new Question(R.string.question_nhall, 4),
            new Question(R.string.question_sga, 7),
            new Question(R.string.question_samuel, 5),
            new Question(R.string.question_test, 6),
            new Question(R.string.question_test2, 3)
    };

    public static Intent newIntent(Context packageContext, int position) {
        Intent i = new Intent(packageContext, QuestionActivity.class);
        i.putExtra(QUESTIONS, position);
        mCategory = position;
        return i;
    }

    private void updateQuestion() {

        if(mCategory == 0)
        {
            int question = mQuestionBank[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);
        }
        else if (mCategory == 1)
        {
            int question = mQuestionPeople[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);
        }
        else if (mCategory == 2)
        {
            int question = mQuestionBank[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);
        }

    }

    private void checkAnswer(int userChoice) {
        int answer = mQuestionBank[mCurrentIndex].answerNum();


        if (userChoice == answer) {
            mediaPlayer = MediaPlayer.create(this, R.raw.correct);

            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                public void onCompletion(MediaPlayer player) {
                    player.release();
                }
            });
            mScore++;
        } else {
            mediaPlayer = MediaPlayer.create(this, R.raw.wrong);

            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                public void onCompletion(MediaPlayer player) {
                    player.release();
                }
            });
        }

       // Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            //    .show();
    }

    private void checkAnswer(boolean userChoice) {
        boolean answer = mQuestionBank[mCurrentIndex].isAnswerTrue();


        if (userChoice == answer) {
            mediaPlayer = MediaPlayer.create(this, R.raw.correct);

            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                public void onCompletion(MediaPlayer player) {
                    player.release();
                }
            });
            mScore++;
        } else {
            mediaPlayer = MediaPlayer.create(this, R.raw.wrong);

            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                public void onCompletion(MediaPlayer player) {
                    player.release();
                }
            });
        }

        // Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
        //    .show();
    }

    private void landMark()
    {
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mChoice = (ImageButton)findViewById(R.id.choice_button);
        mChoice2 = (ImageButton)findViewById(R.id.choice_button2);

        setImage();

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                setImage();
                updateQuestion();
            }
        });

        updateQuestion();
    }

    private void peopleQuestions()
    {
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mChoice3 = (Button)findViewById(R.id.button3);
        mChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(0);
            }
        });

        mChoice4 = (Button)findViewById(R.id.button3);
        mChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1);
            }
        });


        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionPeople.length;
                updateQuestion();
            }
        });

        updateQuestion();
    }

    private void setImage()
    {
        if(mCurrentIndex == 0)
        {
            mChoice.setImageResource(R.drawable.champ_coll);
            mChoice2.setImageResource(R.drawable.lake);
            mChoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        }
        else if (mCurrentIndex == 1)
        {
            mChoice.setImageResource(R.drawable.sam_champ);
            mChoice2.setImageResource(R.drawable.aiken);
            mChoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        }
        else if (mCurrentIndex == 2)
        {
            mChoice.setImageResource(R.drawable.test);
            mChoice2.setImageResource(R.drawable.aiken);
            mChoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
        }
        else if (mCurrentIndex == 3)
        {
            mChoice.setImageResource(R.drawable.test);
            mChoice2.setImageResource(R.drawable.quad);
            mChoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
        }
        else if (mCurrentIndex == 4)
        {
            mChoice.setImageResource(R.drawable.spinner);
            mChoice2.setImageResource(R.drawable.test);
            mChoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        }
        else if (mCurrentIndex == 5)
        {
            mChoice.setImageResource(R.drawable.test);
            mChoice2.setImageResource(R.drawable.idx);
            mChoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
        }
        else if (mCurrentIndex == 6)
        {
            mChoice.setImageResource(R.drawable.ccm);
            mChoice2.setImageResource(R.drawable.test);
            mChoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        }
        else if (mCurrentIndex == 7)
        {
            mChoice.setImageResource(R.drawable.test);
            mChoice2.setImageResource(R.drawable.lake);
            mChoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
        }
        else if (mCurrentIndex == 8)
        {
            mChoice.setImageResource(R.drawable.test);
            mChoice2.setImageResource(R.drawable.perry);
            mChoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
        }
        else if (mCurrentIndex == 9)
        {
            mChoice.setImageResource(R.drawable.test);
            mChoice2.setImageResource(R.drawable.miller);
            mChoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        LinearLayout imageLL = (LinearLayout) findViewById(R.id.layout_image);
        LinearLayout buttonLL = (LinearLayout) findViewById(R.id.layout_button);

        if(mCategory == 0)
        {
            buttonLL.setVisibility(View.GONE);
            landMark();
        }
        else if(mCategory == 1)
        {
            imageLL.setVisibility(View.GONE);
            peopleQuestions();
        }
        else if (mCategory == 2)
        {

        }

    }

}
