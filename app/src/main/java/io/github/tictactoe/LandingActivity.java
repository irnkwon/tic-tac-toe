/*
    LandingActivity.java

    Created by Irene Kwon
    Last Modified at Nov 24, 2019
*/

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
        Intent i = new Intent(getBaseContext(), GameActivity.class);
        startActivity(i);
    }

    public void onBtnSelectPlayerClick(View v) {
        Intent i = new Intent(getBaseContext(), SelectPlayerTabActivity.class);
        startActivity(i);
    }

    public void onBtnScoreboardClick(View v) {
        Intent i = new Intent(getBaseContext(), ScoreboardActivity.class);
        startActivity(i);
    }

}
