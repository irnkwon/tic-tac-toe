/*
    SelectPlayerTabActivity.java

    Created by Irene Kwon
    Last Modified at Nov 24, 2019
*/

package io.github.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

public class SelectPlayerTabActivity extends AppCompatActivity implements View.OnClickListener {

    private PlayerDB db;
    private MaterialAlertDialogBuilder mb;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button goBackButton;
    private Button addPlayerButton;

    private String playerOName = PlayerOFragment.playerOName;
    private String playerXName = PlayerXFragment.playerXName;
    private String playerOTitle;
    private String playerXTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_player_tab);

        db = new PlayerDB(this);
        mb = new MaterialAlertDialogBuilder(this);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        goBackButton = findViewById(R.id.go_back);
        addPlayerButton = findViewById(R.id.add_player);

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

        if (clickedBtnTxt.equals("Add Player")) {

            final TextInputEditText playerName = new TextInputEditText(SelectPlayerTabActivity.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            playerName.setLayoutParams(lp);

            mb
            .setView(playerName)
            .setTitle("Type the name of the player")
            .setPositiveButton("Add Player", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    addPlayer(playerName.getText().toString().trim());
                }
            })
            .setNegativeButton("Close", null)
            .show();
        }

    }

    private void setupViewPager(ViewPager viewPager) {

//        if (playerOName.isEmpty() && playerXName.isEmpty()) {
//            playerOTitle = "Player 1: Not Selected";
//            playerXTitle = "Player 2: Not Selected";
//        } else if (playerOName.isEmpty()) {
//            playerOTitle = "Player 1: Not Selected";
//            playerXTitle = "Player 2: " + playerXName;
//        } else if (playerXName.isEmpty()) {
//            playerOTitle = "Player 1: " + playerOName;
//            playerXTitle = "Player 2: Not Selected";
//        } else {
//            playerOTitle = "Player 1: " + playerOName;
//            playerXTitle = "Player 2: " + playerXName;
//        }

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new PlayerOFragment(), "Player 1");
        viewPagerAdapter.addFragment(new PlayerXFragment(), "Player 2");
        viewPager.setAdapter(viewPagerAdapter);

    }

    private void addPlayer(String playerName) {

        String name = playerName;

        if (!name.isEmpty()) {
            try {
                db.insertPlayer(name);
                setupViewPager(viewPager);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

