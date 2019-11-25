/*
    ScoreboardActivity.java

    Created by Irene Kwon
    Last Modified at Nov 24, 2019
*/

package io.github.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class ScoreboardActivity extends AppCompatActivity implements View.OnClickListener{

    private PlayerDB db;
    private ListView listview;
    private Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);

        listview = findViewById(R.id.score_listview);
        goBackButton = findViewById(R.id.go_back);
        goBackButton.setOnClickListener(this);

        db = new PlayerDB(this);
        updateScreen();
    }

    @Override
    public void onClick(View v) {
        int nId = v.getId();
        Button clickedBtn = findViewById(nId);
        String clickedBtnTxt = clickedBtn.getText().toString();

        if (clickedBtnTxt.equals("Go Back")) {
            Intent myIntent = new Intent(getBaseContext(), LandingActivity.class);
            startActivity(myIntent);
        }
    }

    private void updateScreen() {
        ArrayList<HashMap<String, String>> data = db.getPlayers();

        int res = R.layout.score_list;
        String[] from = {"name", "wins", "losses", "ties"};
        int[] to = {R.id.player_name, R.id.wins, R.id.losses, R.id.ties};

        SimpleAdapter adapter = new SimpleAdapter(this, data, res, from, to);
        listview.setAdapter(adapter);
    }
    
}
