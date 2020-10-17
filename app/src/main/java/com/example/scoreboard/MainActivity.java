package com.example.scoreboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.scoreboard.Module.Team;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.this.getClass().getSimpleName();

    private TextView textView_scoreA;
    private TextView textView_scoreB;
    private Button button_min_score_teamA;
    private Button button_min_score_teamB;
    private Button button_add_score_teamA;
    private Button button_add_score_teamB;
    private Button button_reset_score;

    private Team teamA = new Team();
    private Team teamB = new Team();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();
        setListenerForViews();

        //Reset the score when reset button is clicked.
        button_reset_score.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                teamA.resetScore();
                teamA.displayScore(textView_scoreA);

                teamB.resetScore();
                teamB.displayScore(textView_scoreB);
            }
        });
    }

    private void getViews(){
        textView_scoreA = findViewById(R.id.textView_scoreA);
        button_min_score_teamA = findViewById(R.id.button_min_score_teamA);
        button_add_score_teamA = findViewById(R.id.button_add_score_teamA);

        textView_scoreB = findViewById(R.id.textView_scoreB);
        button_min_score_teamB = findViewById(R.id.button_min_score_teamB);
        button_add_score_teamB = findViewById(R.id.button_add_score_teamB);

        button_reset_score = findViewById(R.id.button_reset_score);
    }

    private void setListenerForViews(){
        button_min_score_teamA.setOnClickListener(teamAListener);
        button_add_score_teamA.setOnClickListener(teamAListener);

        button_min_score_teamB.setOnClickListener(teamBListener);
        button_add_score_teamB.setOnClickListener(teamBListener);
    }

    private Button.OnClickListener teamAListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_min_score_teamA:
                    teamA.decreaseScore();
                    teamA.displayScore(textView_scoreA);
                    break;

                case R.id.button_add_score_teamA:
                    teamA.increaseScore();
                    teamA.displayScore(textView_scoreA);
                    break;

                default:
                    Log.e(TAG, "Error with teamAListener.");
            }
        }
    };

    private Button.OnClickListener teamBListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_min_score_teamB:
                    teamB.decreaseScore();
                    teamB.displayScore(textView_scoreB);
                    break;

                case R.id.button_add_score_teamB:
                    teamB.increaseScore();
                    teamB.displayScore(textView_scoreB);
                    break;

                default:
                    Log.e(TAG, "Error with teamBListener.");
            }
        }
    };
}