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
    private Button mChoice3, mChoice4, mChoice5,mChoice6,mChoice7, mChoice8,mChoice9,mChoice10, mNextButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex, mScore = 0;
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
            new Question(R.string.question_me, 0),
            new Question(R.string.question_prof, 1),
            new Question(R.string.question_laak, 3),
            new Question(R.string.question_wei, 3),
            new Question(R.string.question_bhall, 1),
            new Question(R.string.question_nhall, 3),
            new Question(R.string.question_sga, 7),
            new Question(R.string.question_samuel, 5),
            new Question(R.string.question_test, 6),
            new Question(R.string.question_test2, 3)
    };

    private Question[] mQuestionMajors = new Question[] {
            new Question(R.string.question_csi, 1),
            new Question(R.string.question_core, 0),
            new Question(R.string.question_psych, 5),
            new Question(R.string.question_ed, 5),
            new Question(R.string.question_fake1, 2),
            new Question(R.string.question_gamed, 4),
            new Question(R.string.question_fake2, 7),
            new Question(R.string.question_fake3, 5),
            new Question(R.string.question_gamea, 6),
            new Question(R.string.question_fake4, 3)
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
        mChoice4 = (Button)findViewById(R.id.button4);
        mChoice5 = (Button)findViewById(R.id.button5);
        mChoice6 = (Button)findViewById(R.id.button6);
        mChoice7 = (Button)findViewById(R.id.button7);
        mChoice8 = (Button)findViewById(R.id.button8);
        mChoice9 = (Button)findViewById(R.id.button9);
        mChoice10 = (Button)findViewById(R.id.button10);

        setQText();


        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionPeople.length;
                setQText();
                updateQuestion();
            }
        });

        updateQuestion();
    }

    private void setQText()
    {
        LinearLayout llA = (LinearLayout)findViewById(R.id.layout_button2);
        LinearLayout llB = (LinearLayout)findViewById(R.id.layout_button4);

        if(mCurrentIndex == 0)
        {
            mChoice3.setText("Fletcher and Jonah");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(0);
                }
            });
            mChoice4.setText("The Hulk");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(1);
                }
            });

            mChoice5.setText("Bernie Sanders");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(2);
                }
            });
            mChoice6.setText("Kim Kardashian");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(3);
                }
            });

            mChoice7.setText("Wei Kian-Chen");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(4);
                }
            });
            mChoice8.setText("Tony Stark");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(5);
                }
            });

            mChoice9.setText("Will Smith");
            mChoice9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(6);
                }
            });
            mChoice10.setText("Donald Trump");
            mChoice10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(7);
                }
            });
        }
        else if (mCurrentIndex == 1)
        {
            llA.setVisibility(View.GONE);
            llB.setVisibility(View.GONE);

            mChoice3.setText("John Baptist");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(0);
                }
            });
            mChoice4.setText("David Kopec");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(1);
                }
            });

            mChoice7.setText("Brian Hall");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(2);
                }
            });
            mChoice8.setText("Luke Skywalker");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(3);
                }
            });
        }
        else if (mCurrentIndex == 2)
        {
            mChoice3.setText("");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(0);
                }
            });
            mChoice4.setText("");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(1);
                }
            });

            mChoice7.setText("");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(2);
                }
            });
            mChoice8.setText("Don Laakman");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(3);
                }
            });
        }
        else if (mCurrentIndex == 3)
        {
            mChoice3.setText("");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(0);
                }
            });
            mChoice4.setText("");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(1);
                }
            });

            mChoice7.setText("");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(2);
                }
            });
            mChoice8.setText("WeiKian Chen & Brian Hall");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(3);
                }
            });
        }
        else if (mCurrentIndex == 4)
        {
            mChoice3.setText("");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(0);
                }
            });
            mChoice4.setText("Brian Hall");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(1);
                }
            });

            mChoice7.setText("");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(2);
                }
            });
            mChoice8.setText("");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(3);
                }
            });
        }
        else if (mCurrentIndex == 5)
        {
            mChoice3.setText("");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(0);
                }
            });
            mChoice4.setText("");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(1);
                }
            });

            mChoice7.setText("");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(2);
                }
            });
            mChoice8.setText("Narine Hall");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(3);
                }
            });
        }
        else if (mCurrentIndex == 6)
        {
            llA.setVisibility(View.VISIBLE);
            llB.setVisibility(View.VISIBLE);

            mChoice3.setText("Johny Bean");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(0);
                }
            });
            mChoice4.setText("Donald McGuiver");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(1);
                }
            });

            mChoice5.setText("Superman");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(2);
                }
            });
            mChoice6.setText("Son Goku");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(3);
                }
            });

            mChoice7.setText("Bobby Tables");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(4);
                }
            });
            mChoice8.setText("Georgia O'Keefe");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(5);
                }
            });

            mChoice9.setText("David Kopec");
            mChoice9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(6);
                }
            });
            mChoice10.setText("Logan Rice");
            mChoice10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(7);
                }
            });
        }
        else if (mCurrentIndex == 7)
        {
            mChoice3.setText("");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(0);
                }
            });
            mChoice4.setText("");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(1);
                }
            });

            mChoice5.setText("");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(2);
                }
            });
            mChoice6.setText("");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(3);
                }
            });
        }
        else if (mCurrentIndex == 8)
        {
            mChoice3.setText("");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(0);
                }
            });
            mChoice4.setText("");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(1);
                }
            });

            mChoice5.setText("");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(2);
                }
            });
            mChoice6.setText("");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(3);
                }
            });
        }
        else if (mCurrentIndex == 9)
        {
            mChoice3.setText("");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(0);
                }
            });
            mChoice4.setText("");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(1);
                }
            });

            mChoice5.setText("");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(2);
                }
            });
            mChoice6.setText("");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(3);
                }
            });
        }
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
        LinearLayout buttonLL2 = (LinearLayout) findViewById(R.id.layout_button2);

        if(mCategory == 0)
        {
            buttonLL.setVisibility(View.GONE);
            buttonLL2.setVisibility(View.GONE);
            landMark();
        }
        else if(mCategory == 1)
        {
            imageLL.setVisibility(View.GONE);
            peopleQuestions();
        }
        else if (mCategory == 2)
        {
            imageLL.setVisibility(View.GONE);

        }

    }

}
