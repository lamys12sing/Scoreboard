package com.example.scoreboard.Module;

import android.widget.TextView;

public class Round {
    private int mRound = 1;

    public void increaseRound(){
        mRound++;
    }

    public void decreaseRound(){
        if (mRound <= 1){
            return;
        }

        mRound--;
    }

    public void displayRound(TextView viewForDisplay){
        viewForDisplay.setText(String.valueOf(mRound));
    }

    public void setRound(int round){
        mRound = round;
    }

    public int getRound(){
        return mRound;
    }
}
