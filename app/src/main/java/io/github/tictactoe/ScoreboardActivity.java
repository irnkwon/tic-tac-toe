package io.github.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

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
