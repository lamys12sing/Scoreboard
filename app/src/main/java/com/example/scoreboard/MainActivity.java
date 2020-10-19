package com.example.scoreboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import com.example.scoreboard.Module.Round;
import com.example.scoreboard.Module.Team;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.this.getClass().getSimpleName();

    private TextView textView_title_A;
    private TextView textView_scoreA;
    private TextView textView_title_B;
    private TextView textView_scoreB;
    private TextView textView_round;
    private Button button_min_score_teamA;
    private Button button_min_score_teamB;
    private Button button_add_score_teamA;
    private Button button_add_score_teamB;
    private Button button_reset_score;
    private Button button_min_round;
    private Button button_add_round;
    private Button button_timer;
    private Button button_reset_time;
    private Chronometer timer;

    private boolean timerIsRunning;
    private long timeDifferent; //Store the time different between start and pause time of the timer.

    private Team teamA = new Team();
    private Team teamB = new Team();
    private Round round = new Round();

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
        textView_title_A = findViewById(R.id.textView_title_A);
        textView_scoreA = findViewById(R.id.textView_scoreA);
        textView_title_B = findViewById(R.id.textView_title_B);
        textView_scoreB = findViewById(R.id.textView_scoreB);
        textView_round = findViewById(R.id.textView_round);

        button_min_score_teamA = findViewById(R.id.button_min_score_teamA);
        button_add_score_teamA = findViewById(R.id.button_add_score_teamA);
        button_min_score_teamB = findViewById(R.id.button_min_score_teamB);
        button_add_score_teamB = findViewById(R.id.button_add_score_teamB);
        button_reset_score = findViewById(R.id.button_reset_score);
        button_min_round = findViewById(R.id.button_min_round);
        button_add_round = findViewById(R.id.button_add_round);
        button_timer = findViewById(R.id.button_timer);
        button_reset_time = findViewById(R.id.button_reset_time);

        timer = findViewById(R.id.timer);
    }

    private void setListenerForViews(){
        textView_title_A.setOnClickListener(teamAListener);
        button_min_score_teamA.setOnClickListener(teamAListener);
        button_add_score_teamA.setOnClickListener(teamAListener);

        textView_title_B.setOnClickListener(teamBListener);
        button_min_score_teamB.setOnClickListener(teamBListener);
        button_add_score_teamB.setOnClickListener(teamBListener);

        textView_round.setOnClickListener(roundListener);
        button_min_round.setOnClickListener(roundListener);
        button_add_round.setOnClickListener(roundListener);

        button_timer.setOnClickListener(timerListener);

        button_reset_time.setOnClickListener(resetTimeListener);
    }

    private Button.OnClickListener teamAListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.textView_title_A:
                    showChangeTeamNameDialog(textView_title_A);
                    break;

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
                case R.id.textView_title_B:
                    showChangeTeamNameDialog(textView_title_B);
                    break;

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

    private void showChangeTeamNameDialog(final TextView nameText){
        final EditText editText_for_alertDialog = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(editText_for_alertDialog)
                .setMessage(R.string.input_team_name)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String customName = editText_for_alertDialog.getText().toString();

                        if (customName.equals("")){
                            dialog.dismiss();
                        }
                        else {
                            nameText.setText(customName);
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private Button.OnClickListener roundListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.textView_round:
                    showChangeRoundDialog(textView_round);
                    break;

                case R.id.button_min_round:
                    round.decreaseRound();
                    round.displayRound(textView_round);
                    break;

                case R.id.button_add_round:
                    round.increaseRound();
                    round.displayRound(textView_round);
                    break;

                default:
                    Log.e(TAG, "Error with roundListener.");
            }
        }
    };

    private void showChangeRoundDialog(final TextView roundText){
        final EditText editText_for_alertDialog = new EditText(this);
        editText_for_alertDialog.setInputType(InputType.TYPE_CLASS_NUMBER);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(editText_for_alertDialog)
                .setMessage(R.string.enter_round)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String customRound = editText_for_alertDialog.getText().toString();

                        if (customRound.equals("")){
                            dialog.dismiss();
                        } else {
                            round.setRound(Integer.parseInt(customRound));
                            roundText.setText(String.valueOf(round.getRound()));
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private Button.OnClickListener timerListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            if (!timerIsRunning){
                button_timer.setText(R.string.stop);
                button_reset_time.setVisibility(View.GONE);
                startTimer();
                timerIsRunning = true;
            } else {
                button_timer.setText(R.string.start);
                button_reset_time.setVisibility(View.VISIBLE);
                pauseTimer();
                timerIsRunning = false;
            }
        }
    };

    private void startTimer(){
        if (!timerIsRunning){
            timer.setBase(SystemClock.elapsedRealtime() - timeDifferent);
            timer.start();
        }
    }

    private void pauseTimer(){
        if (timerIsRunning){
            timer.stop();
            timeDifferent = SystemClock.elapsedRealtime() - timer.getBase();
        }
    }

    private Button.OnClickListener resetTimeListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            resetTimer();
        }
    };

    private void resetTimer(){
        timer.setBase(SystemClock.elapsedRealtime());
        timeDifferent = 0;
    }
}