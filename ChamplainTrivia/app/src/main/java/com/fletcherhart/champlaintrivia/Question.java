package com.fletcherhart.champlaintrivia;

/**
 * Created by Tamerai on 9/29/2016.
 */

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private int mAnswer;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public Question(int textResId, int mAnswerChoice) {
        mTextResId = textResId;
        mAnswer = mAnswerChoice;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public int answerNum() {
        return mAnswer;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

}
