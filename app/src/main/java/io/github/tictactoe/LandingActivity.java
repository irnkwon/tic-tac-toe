package io.github.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);
    }

    public void onBtnStartGameClick(View v) {
        Intent myIntent = new Intent(getBaseContext(), GameActivity.class);
        startActivity(myIntent);
    }

    public void onBtnSelectPlayerClick(View v) {
        Intent myIntent = new Intent(getBaseContext(), SelectPlayerActivity.class);
        startActivity(myIntent);
    }

    public void onBtnScoreboardClick(View v) {
        Intent myIntent = new Intent(getBaseContext(), ScoreboardActivity.class);
        startActivity(myIntent);
    }
}
