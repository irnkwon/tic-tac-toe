/*
    ScoreboardActivity.java

    Created by Irene Kwon
    Last Modified at Nov 22, 2019
*/

package io.github.tictactoe.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import io.github.tictactoe.Activities.LandingActivity;
import io.github.tictactoe.R;

public class ScoreboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);
    }

    public void onBtnGoBackClick(View v) {
        Intent myIntent = new Intent(getBaseContext(), LandingActivity.class);
        startActivity(myIntent);
    }
    
}
