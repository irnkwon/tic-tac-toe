/*
    GameActivity.java

    Created by Irene Kwon
    Last Modified at Nov 24, 2019
*/

package io.github.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mPrefs;
    private PlayerDB db;

    private Button aButtons[][] = new Button[3][3];
    private Button newGameButton;
    private Button goBackButton;

    private int playerOPoints;
    private int playerXPoints;
    private int playerOWins;
    private int playerODraw;
    private int playerOLosses;
    private int playerXWins;
    private int playerXDraw;
    private int playerXLosses;
    private int PlayerOId = PlayerOFragment.playerOId;
    private int PlayerXId = PlayerXFragment.playerXId;

    private String winMsg = " wins!";
    private String drawMsg = "draw!";
    private String playerOName;
    private String playerXName;

    private TextView oPoints;
    private TextView xPoints;
    private TextView oPlayerName;
    private TextView xPlayerName;
    private TextView oTurnTxt;
    private TextView xTurnTxt;

    private ImageView oImage;
    private ImageView xImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        oPoints = findViewById(R.id.oWinPoint);
        xPoints = findViewById(R.id.xWinPoint);
        oPlayerName = findViewById(R.id.oPlayerName);
        xPlayerName = findViewById(R.id.xPlayerName);
        oTurnTxt = findViewById(R.id.oTurnTxt);
        xTurnTxt = findViewById(R.id.xTurnTxt);
        oImage = findViewById(R.id.oImage);
        xImage = findViewById(R.id.xImage);

        playerOName = PlayerOFragment.playerOName;
        playerXName = PlayerXFragment.playerXName;

        newGameButton = findViewById(R.id.new_game);
        goBackButton = findViewById(R.id.go_back);

        oPlayerName.setText(playerOName);
        xPlayerName.setText(playerXName);

        db = new PlayerDB(this);

        Resources res = getResources();
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                String idName = "button" + i + j;
                aButtons[i][j] = findViewById(res.getIdentifier(idName,
                        "id", getPackageName()));
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                aButtons[i][j].setOnClickListener(this);
                newGameButton.setOnClickListener(this);
                goBackButton.setOnClickListener(this);
            }
        }

        if (playerOName.isEmpty() || playerXName.isEmpty()) {
            oPlayerName.setText("PLAYER 1");
            xPlayerName.setText("PLAYER 2");

            new MaterialAlertDialogBuilder(this)
                    .setTitle("Would you like to select players?")
                    .setMessage("You can save your game scores and see them " +
                            "in the scoreboard. To do that, please select both player 1 and 2.")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getBaseContext(),
                                    SelectPlayerTabActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("No, Thanks", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .show();
        }

        mPrefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
    }

    @Override
    protected void onPause() {

        String button00 = aButtons[0][0].getText().toString();
        String button01 = aButtons[0][1].getText().toString();
        String button02 = aButtons[0][2].getText().toString();
        String button10 = aButtons[1][0].getText().toString();
        String button11 = aButtons[1][1].getText().toString();
        String button12 = aButtons[1][2].getText().toString();
        String button20 = aButtons[2][0].getText().toString();
        String button21 = aButtons[2][1].getText().toString();
        String button22 = aButtons[2][2].getText().toString();

        SharedPreferences.Editor ed = mPrefs.edit();

        ed.putString("button00", button00);
        ed.putString("button01", button01);
        ed.putString("button02", button02);
        ed.putString("button10", button10);
        ed.putString("button11", button11);
        ed.putString("button12", button12);
        ed.putString("button20", button20);
        ed.putString("button21", button21);
        ed.putString("button22", button22);

//        ed.putString("oTurnTxt", oTurnTxt.getText().toString());
//        ed.putString("xTurnTxt", xTurnTxt.getText().toString());

        ed.commit();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        aButtons[0][0].setText(mPrefs.getString("button00", ""));
        aButtons[0][1].setText(mPrefs.getString("button01", ""));
        aButtons[0][2].setText(mPrefs.getString("button02", ""));
        aButtons[1][0].setText(mPrefs.getString("button10", ""));
        aButtons[1][1].setText(mPrefs.getString("button11", ""));
        aButtons[1][2].setText(mPrefs.getString("button12", ""));
        aButtons[2][0].setText(mPrefs.getString("button20", ""));
        aButtons[2][1].setText(mPrefs.getString("button21", ""));
        aButtons[2][2].setText(mPrefs.getString("button22", ""));

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                String idName = "button" + i + j;
                if (mPrefs.getString(idName, "") == "o") {
                    aButtons[i][j].setBackgroundResource(R.drawable.o);
                }
                if (mPrefs.getString(idName, "") == "x") {
                    aButtons[i][j].setBackgroundResource(R.drawable.x);
                }
                aButtons[i][j].setTextScaleX(0);
            }
        }

//        oTurnTxt.setText(mPrefs.getString("oTurnTxt", ""));
//        xTurnTxt.setText(mPrefs.getString("xTurnTxt", ""));
    }

    @Override
    public void onClick(View v) {
        String allBtnTxt = aButtons[0][0].getText().toString() + aButtons[0][1].getText() +
                aButtons[0][2].getText() + aButtons[1][0].getText() + aButtons[1][1].getText()
                + aButtons[1][2].getText() + aButtons[2][0].getText() + aButtons[2][1].getText()
                + aButtons[2][2].getText();

        int nId = v.getId();
        Button clickedBtn = findViewById(nId);
        String clickedBtnTxt = clickedBtn.getText().toString();

        if (allBtnTxt == "" && clickedBtnTxt == "") {
            clickedBtn.setText("x");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.x);
            oImage.setImageAlpha(255);
            xImage.setImageAlpha(50);
        }
        if (allBtnTxt.length() == 1 && clickedBtnTxt == "") {
            clickedBtn.setText("o");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.o);
            oImage.setImageAlpha(50);
            xImage.setImageAlpha(255);
        }
        if (allBtnTxt.length() == 2 && clickedBtnTxt == "") {
            clickedBtn.setText("x");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.x);
            oImage.setImageAlpha(255);
            xImage.setImageAlpha(50);
        }
        if (allBtnTxt.length() == 3 && clickedBtnTxt == "") {
            clickedBtn.setText("o");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.o);
            oImage.setImageAlpha(50);
            xImage.setImageAlpha(255);
        }
        if (allBtnTxt.length() == 4 && clickedBtnTxt == "") {
            clickedBtn.setText("x");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.x);
            oImage.setImageAlpha(255);
            xImage.setImageAlpha(50);
        }
        if (allBtnTxt.length() == 5 && clickedBtnTxt == "") {
            clickedBtn.setText("o");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.o);
            oImage.setImageAlpha(50);
            xImage.setImageAlpha(255);
        }
        if (allBtnTxt.length() == 6 && clickedBtnTxt == "") {
            clickedBtn.setText("x");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.x);
            oImage.setImageAlpha(255);
            xImage.setImageAlpha(50);
        }
        if (allBtnTxt.length() == 7 && clickedBtnTxt == "") {
            clickedBtn.setText("o");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.o);
            oImage.setImageAlpha(50);
            xImage.setImageAlpha(255);
        }
        if (allBtnTxt.length() == 8 && clickedBtnTxt == "") {
            clickedBtn.setText("x");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.x);
            oImage.setImageAlpha(255);
            xImage.setImageAlpha(50);
        }

        if (clickedBtnTxt.equals("New Game")) {
            startNewGame();
        }

        if (clickedBtnTxt.equals("Go Back")) {
            Intent myIntent = new Intent(getBaseContext(), LandingActivity.class);
            startActivity(myIntent);
        }

        checkWinner();
    }

    private void checkWinner() {
        String firstRow = aButtons[0][0].getText().toString() + aButtons[0][1].getText() +
                aButtons[0][2].getText();
        String secondRow = aButtons[1][0].getText().toString() + aButtons[1][1].getText() +
                aButtons[1][2].getText();
        String lastRow = aButtons[2][0].getText().toString() + aButtons[2][1].getText() +
                aButtons[2][2].getText();
        String firstColumn = aButtons[0][0].getText().toString() + aButtons[1][0].getText() +
                aButtons[2][0].getText();
        String secondColumn = aButtons[0][1].getText().toString() + aButtons[1][1].getText() +
                aButtons[2][1].getText();
        String lastColumn = aButtons[0][2].getText().toString() + aButtons[1][2].getText() +
                aButtons[2][2].getText();
        String leftDiagonal
                = aButtons[0][0].getText().toString() + aButtons[1][1].getText() +
                aButtons[2][2].getText();
        String rightDiagonal
                = aButtons[0][2].getText().toString() + aButtons[1][1].getText() +
                aButtons[2][0].getText();

        if (firstRow.equals("xxx") || secondRow.equals("xxx") || lastRow.equals("xxx") ||
                firstColumn.equals("xxx") || secondColumn.equals("xxx") || lastColumn.equals("xxx") ||
                leftDiagonal.equals("xxx") || rightDiagonal.equals("xxx")) {

            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    aButtons[i][j].setEnabled(false);
                }
            }
            oTurnTxt.setText("");
            xTurnTxt.setText(winMsg);
            oImage.setImageAlpha(255);
            xImage.setImageAlpha(255);
            playerXWins++;
            playerOLosses++;
            playerXPoints++;
            xPoints.setText(Integer.toString(playerXPoints));
            updatePlayerScores();

        }
        else if (firstRow.equals("ooo") || secondRow.equals("ooo") || lastRow.equals("ooo") ||
                firstColumn.equals("ooo") || secondColumn.equals("ooo") || lastColumn.equals("ooo")
                || leftDiagonal.equals("ooo") || rightDiagonal.equals("ooo")) {

            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    aButtons[i][j].setEnabled(false);
                }
            }
            xTurnTxt.setText("");
            oTurnTxt.setText(winMsg);
            oImage.setImageAlpha(255);
            xImage.setImageAlpha(255);
            playerOWins++;
            playerXLosses++;
            playerOPoints++;
            oPoints.setText(Integer.toString(playerOPoints));
            updatePlayerScores();

        }
        else {
            checkDraw();
        }
    }

    private void checkDraw() {

        if (aButtons[0][0].getText().length() > 0 && aButtons[0][1].getText().length() > 0 &&
                aButtons[0][2].getText().length() > 0 && aButtons[1][0].getText().length() > 0 &&
                aButtons[1][1].getText().length() > 0 && aButtons[1][2].getText().length() > 0 &&
                aButtons[2][0].getText().length() > 0 && aButtons[2][1].getText().length() > 0 &&
                aButtons[2][2].getText().length() > 0) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    aButtons[i][j].setEnabled(false);
                }
            }
            oTurnTxt.setText(drawMsg);
            xTurnTxt.setText(drawMsg);
            oImage.setImageAlpha(255);
            xImage.setImageAlpha(255);
            playerODraw++;
            playerXDraw++;
            playerOPoints++;
            playerXPoints++;
            oPoints.setText(Integer.toString(playerOPoints));
            xPoints.setText(Integer.toString(playerXPoints));
            updatePlayerScores();
        }
    }

    private void startNewGame() {

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                aButtons[i][j].setEnabled(true);
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                aButtons[i][j].setText("");
                aButtons[i][j].setBackgroundResource(android.R.drawable.btn_default);
            }
        }

        oTurnTxt.setText("");
        xTurnTxt.setText("");
        oImage.setImageAlpha(255);
        xImage.setImageAlpha(255);

        oPoints.setText(Integer.toString(playerOPoints));
        xPoints.setText(Integer.toString(playerXPoints));
    }

    private void updatePlayerScores() {

        try {
            db.updateScores(PlayerOId, playerOWins, playerOLosses, playerODraw);
            db.updateScores(PlayerXId, playerXWins, playerXLosses, playerXDraw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}