package com.fletcherhart.champlaintrivia;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.*;

public class QuestionActivity extends AppCompatActivity {

    private static final String QUESTIONS =
            "com.fletcher.QUESTIONS";

    private ImageButton mChoice, mChoice2;
    private Button mChoice3, mChoice4, mChoice5, mChoice6, mChoice7, mChoice8, mChoice9, mChoice10, mNextButton, mHint;
    private TextView mQuestionTextView;
    private int mCurrentIndex, mScore = 0;
    public static int mCategory;
    private boolean peopleAnswer = false;
    MediaPlayer mediaPlayer;
    ArrayList<View> touchables = new ArrayList<>();

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_street, true),
            new Question(R.string.question_statue, true),
            new Question(R.string.question_aiken, true),
            new Question(R.string.question_quad, true),
            new Question(R.string.question_spinner, true),
            new Question(R.string.question_idx, true),
            new Question(R.string.question_ccm, true),
            new Question(R.string.question_lake, true),
            new Question(R.string.question_perry, true),
            new Question(R.string.question_miller, true)
    };

    private Question[] mQuestionPeople = new Question[]{
            new Question(R.string.question_me, true),
            new Question(R.string.question_prof, true),
            new Question(R.string.question_laak, true),
            new Question(R.string.question_wei, true),
            new Question(R.string.question_bhall, true),
            new Question(R.string.question_nhall, true),
            new Question(R.string.question_sga, true),
            new Question(R.string.question_samuel, true),
            new Question(R.string.question_test, true),
            new Question(R.string.question_test2, true)
    };

    private Question[] mQuestionMajors = new Question[]{
            new Question(R.string.question_csi, true),
            new Question(R.string.question_core, true),
            new Question(R.string.question_psych, true),
            new Question(R.string.question_ed, true),
            new Question(R.string.question_fake1, true),
            new Question(R.string.question_gamed, true),
            new Question(R.string.question_fake2, true),
            new Question(R.string.question_fake3, true),
            new Question(R.string.question_gamea, true),
            new Question(R.string.question_fake4, true)
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public static Intent newIntent(Context packageContext, int position) {
        Intent i = new Intent(packageContext, QuestionActivity.class);
        i.putExtra(QUESTIONS, position);
        mCategory = position;
        return i;
    }

    private void updateQuestion() {

        if (mCategory == 0) {
            int question = mQuestionBank[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);
        } else if (mCategory == 1) {
            int question = mQuestionPeople[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);
        } else if (mCategory == 2) {
            int question = mQuestionMajors[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);
        }

    }

    private void checkAnswer(boolean userChoice) {

        boolean answer = false;

        if (mCategory == 0) {
            answer = mQuestionBank[mCurrentIndex].isAnswerTrue();
        } else if (mCategory == 1) {
            answer = mQuestionPeople[mCurrentIndex].isAnswerTrue();
        } else if (mCategory == 2) {
            answer = mQuestionMajors[mCurrentIndex].isAnswerTrue();
        }


        if (userChoice == answer) {
            mediaPlayer = MediaPlayer.create(this, R.raw.correct);

            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer player) {
                    player.release();
                }
            });
            mScore++;
        } else {
            mediaPlayer = MediaPlayer.create(this, R.raw.wrong);

            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer player) {
                    player.release();
                }
            });
        }

        if (mCurrentIndex == 9) {
            Intent i = ScoreActivity.newIntent(QuestionActivity.this, mScore, mCategory);
            startActivity(i);
            finish();
        }
    }

    private void landMark() {
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mChoice = (ImageButton) findViewById(R.id.choice_button);
        mChoice2 = (ImageButton) findViewById(R.id.choice_button2);
        mHint = (Button) findViewById(R.id.hint_button);

        LinearLayout ln = (LinearLayout) findViewById(R.id.linear);
        touchables = ln.getTouchables();

        setImage();

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex == mQuestionBank.length - 1) {

                } else {
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    setImage();
                    updateQuestion();

                    for (View touchable : touchables) {
                        if (touchable instanceof ImageButton) {
                            touchable.setEnabled(true);
                        }
                    }
                }
            }
        });

        updateQuestion();
    }

    private void theQuestions() {
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mChoice3 = (Button) findViewById(R.id.button3);
        mChoice4 = (Button) findViewById(R.id.button4);
        mChoice5 = (Button) findViewById(R.id.button5);
        mChoice6 = (Button) findViewById(R.id.button6);
        mChoice7 = (Button) findViewById(R.id.button7);
        mChoice8 = (Button) findViewById(R.id.button8);
        mChoice9 = (Button) findViewById(R.id.button9);
        mChoice10 = (Button) findViewById(R.id.button10);
        mHint = (Button) findViewById(R.id.hint_button);

        LinearLayout ln = (LinearLayout) findViewById(R.id.linear);
        touchables = ln.getTouchables();


        if (mCategory == 1) {
            setPeopleQText();
            mNextButton = (Button) findViewById(R.id.next_button);
            mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCurrentIndex == mQuestionPeople.length - 1) {

                    } else {
                        mCurrentIndex = (mCurrentIndex + 1) % mQuestionPeople.length;
                        setPeopleQText();
                        updateQuestion();

                        for (View touchable : touchables) {
                            if (touchable instanceof Button) {
                                ((Button) touchable).setEnabled(true);
                            }
                        }
                    }
                }
            });

            updateQuestion();
        } else {
            setMajorQText();
            mNextButton = (Button) findViewById(R.id.next_button);
            mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCurrentIndex == mQuestionBank.length - 1) {

                    } else {
                        mCurrentIndex = (mCurrentIndex + 1) % mQuestionMajors.length;
                        setMajorQText();
                        updateQuestion();
                        for (View touchable : touchables) {
                            if (touchable instanceof Button) {
                                ((Button) touchable).setEnabled(true);
                            }
                        }
                    }
                }
            });

            updateQuestion();
        }

    }

    private void setPeopleQText() {
        LinearLayout llA = (LinearLayout) findViewById(R.id.layout_button2);
        LinearLayout llB = (LinearLayout) findViewById(R.id.layout_button4);

        peopleAnswer = true;

        if (mCurrentIndex == 0) {
            mChoice3.setText("Fletcher and Jonah");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice4.setText("The Hulk");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice4.setEnabled(false);
                }
            });

            mChoice5.setText("Bernie Sanders");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice6.setText("Kim Kardashian");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("Wei Kian-Chen");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("Tony Stark");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice9.setText("Will Smith");
            mChoice9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice10.setText("Donald Trump");
            mChoice10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        } else if (mCurrentIndex == 1) {
            llA.setVisibility(View.GONE);
            llB.setVisibility(View.GONE);

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice7.setEnabled(false);
                }
            });

            mChoice3.setText("John Baptist");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice4.setText("David Kopec");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });

            mChoice7.setText("Brian Hall");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("Luke Skywalker");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        } else if (mCurrentIndex == 2) {
            mChoice3.setText("Peter Baelish");
            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice3.setEnabled(false);
                }
            });
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice4.setText("Jonah Allibone");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("Morgan Donnell");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("Don Laakman");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
        } else if (mCurrentIndex == 3) {
            mChoice3.setText("Michael Jackson");

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice4.setEnabled(false);
                }
            });

            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice4.setText("Santa Claus");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("Frank Sinatra");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("WeiKian Chen & Brian Hall");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
        } else if (mCurrentIndex == 4) {
            mChoice3.setText("Fletcher Hart");

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice8.setEnabled(false);
                }
            });

            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice4.setText("Brian Hall");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });

            mChoice7.setText("Guillermo del Toro");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("Steven Spielberg");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        } else if (mCurrentIndex == 5) {

            mChoice3.setText("Spiderman");

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice3.setEnabled(false);
                }
            });

            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice4.setText("Master Splinter");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("Will Farrell");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("Narine Hall");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
        } else if (mCurrentIndex == 6) {
            llA.setVisibility(View.VISIBLE);
            llB.setVisibility(View.VISIBLE);

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice9.setEnabled(false);
                }
            });

            mChoice3.setText("Johny Bean");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice4.setText("Donald McGuiver");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice5.setText("Superman");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice6.setText("Son Goku");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("Bobby Tables");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("Georgia O'Keefe");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice9.setText("David Kopec");
            mChoice9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice10.setText("Logan Rice");
            mChoice10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
        } else if (mCurrentIndex == 7) {

            mChoice3.setText("Lou Ferrigno");
            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice4.setEnabled(false);
                }
            });

            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice4.setText("Christopher Colombus");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice5.setText("Marshel Hindenburg");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice6.setText("George Washington");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("Abraham Lincoln");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("Karl Marx");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice9.setText("Samuel De Champlain");
            mChoice9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice10.setText("Alexander the Great");
            mChoice10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        } else if (mCurrentIndex == 8) {

            mChoice3.setText("Leonardo Da Vinci");

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice4.setEnabled(false);
                }
            });

            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice4.setText("Sarah Q. Dalton");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice5.setText("Corey Daniels");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice6.setText("Hillary Clinton");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("Michael Fassbender");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("Jeffery Dahmers");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice9.setText("David Finney");
            mChoice9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice10.setText("He was always president");
            mChoice10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        } else if (mCurrentIndex == 9) {
            llA.setVisibility(View.GONE);
            llB.setVisibility(View.GONE);

            mChoice3.setText("Rally the Catamount");
            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice3.setEnabled(false);
                }
            });

            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice4.setText("Chauncey T. Beaver");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });

            mChoice5.setText("Bully the Bulldog");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice6.setText("Irish Rhino");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        }
    }

    private void setMajorQText() {
        LinearLayout llA = (LinearLayout) findViewById(R.id.layout_button2);
        LinearLayout llB = (LinearLayout) findViewById(R.id.layout_button4);

        peopleAnswer = true;

        if (mCurrentIndex == 0) {

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice4.setEnabled(false);
                }
            });


            mChoice3.setText("Computer Science and Innovation");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice4.setText("Philosophy of Cybernetics");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice5.setText("Professional Curling");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice6.setText("Dog Breeding and Lawn Care");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("Medicine");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("History");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice9.setText("Physics");
            mChoice9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice10.setText("African Studies");
            mChoice10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        } else if (mCurrentIndex == 1) {
            llA.setVisibility(View.GONE);
            llB.setVisibility(View.GONE);

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice4.setEnabled(false);
                }
            });

            mChoice3.setText("Accounting");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice4.setText("Computer Science");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("Core");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice8.setText("Film Making");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        } else if (mCurrentIndex == 2) {

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice4.setEnabled(false);
                }
            });

            mChoice3.setText("Psychology");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice4.setText("Animal Science");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("Art History");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("Biochemistry");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        } else if (mCurrentIndex == 3) {

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice4.setEnabled(false);
                }
            });

            mChoice3.setText("Elementary Teacher Education");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice4.setText("Civil Engineering");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("Counseling");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("Exercise and Movement Education");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        } else if (mCurrentIndex == 4) {

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice3.setEnabled(false);
                }
            });

            mChoice3.setText("Data Analytics");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice4.setText("European Studies");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });

            mChoice7.setText("Criminal Justice");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("Filmmaking");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        } else if (mCurrentIndex == 5) {

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice4.setEnabled(false);
                }
            });

            mChoice3.setText("Game Design");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice4.setText("Plant Biology");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("French");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("Geology");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        } else if (mCurrentIndex == 6) {

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice4.setEnabled(false);
                }
            });

            llA.setVisibility(View.VISIBLE);
            llB.setVisibility(View.VISIBLE);

            mChoice3.setText("Marketing");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice4.setText("Digital Forensics");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice5.setText("Creative Media");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice6.setText("Computer Science");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("Gerontology");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice8.setText("Professional Writing");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice9.setText("Psychology");
            mChoice9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice10.setText("Radiography");
            mChoice10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        } else if (mCurrentIndex == 7) {

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice4.setEnabled(false);
                }
            });

            mChoice3.setText("Marketing");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice4.setText("Graphic Design");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice5.setText("Law");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice6.setText("Psychology");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("Linguistics");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice8.setText("Managment and Innovation");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice9.setText("Finance");
            mChoice9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice10.setText("Creative Media");
            mChoice10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        } else if (mCurrentIndex == 8) {

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice4.setEnabled(false);
                }
            });

            mChoice3.setText("Music");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice4.setText("Religion");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice5.setText("Molecular Genetics");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice6.setText("Sociology");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice7.setText("Microbiology");
            mChoice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice8.setText("Mechanical Engineering");
            mChoice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice9.setText("Game Art and Animation");
            mChoice9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice10.setText("Japanese");
            mChoice10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        } else if (mCurrentIndex == 9) {
            llA.setVisibility(View.GONE);
            llB.setVisibility(View.GONE);

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice4.setEnabled(false);
                }
            });

            mChoice3.setText("Vermont Studies");
            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
            mChoice4.setText("Game Programming");
            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });

            mChoice5.setText("International Business");
            mChoice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
            mChoice6.setText("Communication");
            mChoice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(false);
                }
            });
        }
    }

    private void setImage() {
        if (mCurrentIndex == 0) {
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

            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice2.setEnabled(false);
                }
            });

        } else if (mCurrentIndex == 1) {
            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice2.setEnabled(false);
                }
            });
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
        } else if (mCurrentIndex == 2) {
            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice.setEnabled(false);
                }
            });
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
        } else if (mCurrentIndex == 3) {
            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice.setEnabled(false);
                }
            });
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
        } else if (mCurrentIndex == 4) {
            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice2.setEnabled(false);
                }
            });
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
        } else if (mCurrentIndex == 5) {
            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice.setEnabled(false);
                }
            });
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
        } else if (mCurrentIndex == 6) {
            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice2.setEnabled(false);
                }
            });
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
        } else if (mCurrentIndex == 7) {
            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice.setEnabled(false);
                }
            });
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
        } else if (mCurrentIndex == 8) {
            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice.setEnabled(false);
                }
            });
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
        } else if (mCurrentIndex == 9) {
            mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChoice.setEnabled(false);
                }
            });
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        LinearLayout imageLL = (LinearLayout) findViewById(R.id.layout_image);
        LinearLayout buttonLL = (LinearLayout) findViewById(R.id.layout_strings);
        LinearLayout buttonLL2 = (LinearLayout) findViewById(R.id.layout_button2);

        if (mCategory == 0) {
            buttonLL.setVisibility(View.GONE);
            buttonLL2.setVisibility(View.GONE);
            landMark();
        } else if (mCategory == 1) {
            imageLL.setVisibility(View.GONE);
            theQuestions();
        } else if (mCategory == 2) {
            imageLL.setVisibility(View.GONE);
            theQuestions();
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Question Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
