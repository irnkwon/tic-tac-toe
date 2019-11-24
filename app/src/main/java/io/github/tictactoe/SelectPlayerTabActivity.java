/*
    SelectPlayerTabActivity.java

    Created by Irene Kwon
    Last Modified at Nov 24, 2019
*/

package io.github.tictactoe;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;

public class SelectPlayerTabActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button goBackButton;

    private Dialog dialog;
    private Button addPlayerButton;
    private PlayerDB db;
    private EditText playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_player_tab);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        goBackButton = findViewById(R.id.go_back);
        addPlayerButton = findViewById(R.id.add_player);
        playerName = findViewById(R.id.modal_player_name);

        goBackButton.setOnClickListener(this);
        addPlayerButton.setOnClickListener(this);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
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

        if (clickedBtn.equals(addPlayerButton)) {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Type the name of the player")
                    .setView(R.layout.add_player)
                    .setPositiveButton("Add Player", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String name = playerName.getText().toString().trim();

                            if (name.isEmpty()) {
                            } else {
                                try {
                                    db.addPlayer(name);
                                    playerName.setText("");

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    })
                    .setNegativeButton("Close", null)
                    .show();
        }

    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new PlayerOFragment(), "Player O");
        viewPagerAdapter.addFragment(new PlayerXFragment(), "Player X");
        viewPager.setAdapter(viewPagerAdapter);

    }

}

