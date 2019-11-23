/*
    ScoreboardActivity.java

    Created by Irene Kwon
    Last Modified at Nov 23, 2019
*/

package io.github.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class ScoreboardActivity extends AppCompatActivity {

    private PlayerDB db;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);

        listview = findViewById(R.id.score_listview);
        db = new PlayerDB(this);
        updateScreen();
    }

    public void onBtnGoBackClick(View v) {
        Intent myIntent = new Intent(getBaseContext(), LandingActivity.class);
        startActivity(myIntent);
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
