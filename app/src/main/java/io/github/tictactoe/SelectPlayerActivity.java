/*
    SelectPlayerActivity.java

    Created by Irene Kwon
    Last Modified at Nov 20, 2019
*/

package io.github.tictactoe;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

public class SelectPlayerActivity extends AppCompatActivity {

    private Dialog dialog;
    private PlayerDB db;
    private RecyclerView recyclerView;
    EditText etPlayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_player);

        recyclerView = findViewById(R.id.test);

        dialog = new Dialog(this);
        db = new PlayerDB(this);
        updateScreen();
    }

    public void showPlayerAdd(View v) {

        Button btnAddPlayer;
        Button btnCloseDialog;

        dialog.setContentView(R.layout.add_player);
        btnAddPlayer = dialog.findViewById(R.id.btnAddPlayer);
        btnCloseDialog = dialog.findViewById(R.id.btnCloseDialog);
        etPlayerName = dialog.findViewById(R.id.etPlayerName);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPlayer();
            }
        });

        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show(); // dialog.dismiss();
    }

    public void onBtnGoBackClick(View v) {
        Intent myIntent = new Intent(getBaseContext(), LandingActivity.class);
        startActivity(myIntent);
    }

    // Add a new player
    private void addPlayer() {
        String name = etPlayerName.getText().toString().trim();

        if (name.isEmpty()) {
        } else {
            try {
                db.insertPlayer(name);
                updateScreen();
                etPlayerName.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateScreen() {
        ArrayList<HashMap<String, String>> data = db.getPlayers();

        int res = R.layout.select_player;
        String[] from = {"name"};
        int[] to = {R.id.test};

        SimpleAdapter adapter = new SimpleAdapter(this, data, res, from, to);
//        recyclerView.setAdapter(adapter);
    }
    
}
