package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;


    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void point(View View) {

        int value = 3;
        scoreTeamA = scoreTeamA + value;

        displayForTeamA(scoreTeamA);


    }

    public void point2(View View) {

        int value = 2;
        scoreTeamA = scoreTeamA + value;

        displayForTeamA(scoreTeamA);


    }

    public void point3(View View) {
        int value = 1;
        scoreTeamA = scoreTeamA + value;

        displayForTeamA(scoreTeamA);
    }


    public void displayForTeamB(int scoreb) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(scoreb));
    }


    public void score(View View) {

        int value = 3;
        scoreTeamB = scoreTeamB + value;
        displayForTeamB(scoreTeamB);
    }

    public void score2(View View) {

        int value = 2;
        scoreTeamB = scoreTeamB + value;
        displayForTeamB(scoreTeamB);
    }

    public void score3(View View) {
        int value = 1;
        scoreTeamB = scoreTeamB + value;
        displayForTeamB(scoreTeamB);
    }


    public void restoreData(View view) {

        int restore = 0;
        scoreTeamA = scoreTeamB = restore;
        displayForTeamB(restore);
        displayForTeamA(restore);
 }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(0);
    }
}
