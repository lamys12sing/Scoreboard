package com.example.scoreboard.Module;

import android.widget.TextView;

public class Team {
    private int mScore = 0;

    public void increaseScore(){
        mScore++;
    }

    public void decreaseScore(){
        if (mScore <= 0){
            resetScore();
            return;
        }
        mScore--;
    }

    public void resetScore(){
        mScore = 0;
    }

    public void displayScore(TextView viewForDisplay){
        viewForDisplay.setText(String.valueOf(mScore));
    }
}
