/*
    SelectPlayerActivity.java

    Created by Irene Kwon
    Last Modified at Nov 23, 2019
*/

package io.github.tictactoe;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    public static String playerOName = "";
    public static String playerXName = "";

    private Dialog dialog;
    private PlayerDB db;
    private ListView listview;
    private EditText etPlayerName;
    private Button goBackButton;
    private TextView noPlayers;
    private TextView playerStatus;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_player);

        listview = findViewById(R.id.listview);
        goBackButton = findViewById(R.id.go_back);
        noPlayers = findViewById(R.id.no_players);
        playerStatus = findViewById(R.id.player_stauts);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        goBackButton.setOnClickListener(this);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        dialog = new Dialog(this);
        db = new PlayerDB(this);

        updateScreen();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                String itemDetails = listview.getItemAtPosition(pos).toString();
                Toast.makeText(SelectPlayerActivity.this,
                        itemDetails, Toast.LENGTH_SHORT).show();
                v.setSelected(true);

                Map<String, Object> map = (Map<String, Object>) listview.getItemAtPosition(pos);
                String name = (String) map.get("name");
                playerOName = name;
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new PlayerOFragment(), "Player O");
        viewPagerAdapter.addFragment(new PlayerXFragment(), "Player X");
        viewPager.setAdapter(viewPagerAdapter);

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
                dialog.dismiss();
            }
        });

        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    // Add a new player
    private void addPlayer() {
        String name = etPlayerName.getText().toString().trim();

        if (name.isEmpty()) {
        } else {
            try {
                db.addPlayer(name.toUpperCase());
                updateScreen();
                etPlayerName.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateScreen() {
        ArrayList<HashMap<String, String>> data = db.getPlayers();

        if (data.equals(null)) {
            noPlayers.setVisibility(View.VISIBLE);
        }

        int res = R.layout.player_list;
        String[] from = {"name"};
        int[] to = {R.id.player_name};

        SimpleAdapter adapter = new SimpleAdapter(this, data, res, from, to);
        listview.setAdapter(adapter);

    }

}

