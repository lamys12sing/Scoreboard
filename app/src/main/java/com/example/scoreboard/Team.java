package com.example.scoreboard;

public class Team {
    private int mScore = 0;

    private void increaseScore(){
        mScore++;
    }

    private void decreaseScore(){
        if (mScore <= 0){
            resetScore();
            return;
        }
        mScore--;
    }

    private void resetScore(){
        mScore = 0;
    }
}
