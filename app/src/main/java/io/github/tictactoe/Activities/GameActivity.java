/*
    GameActivity.java

    Created by Irene Kwon
    Last Modified at Nov 23, 2019
*/

package io.github.tictactoe.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.github.tictactoe.Activities.LandingActivity;
import io.github.tictactoe.R;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private String winMsg = "winner!";
    private String drawMsg = "draw!";

    Button aButtons[][] = new Button[3][3];
    private SharedPreferences mPrefs;
    int playerOPoints;
    int playerXPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        Resources res = getResources();
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                String idName = "button" + i + j;
                aButtons[i][j] = findViewById(res.getIdentifier(idName,
                        "id", getPackageName()));
            }
        }

        Button newGameButton = findViewById(R.id.new_game);
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                aButtons[i][j].setOnClickListener(this);
                newGameButton.setOnClickListener(this);
            }
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

        TextView oTurnTxt = findViewById(R.id.oTurnTxt);
        TextView xTurnTxt = findViewById(R.id.xTurnTxt);
        ed.putString("oTurnTxt", oTurnTxt.getText().toString());
        ed.putString("xTurnTxt", xTurnTxt.getText().toString());

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

        TextView oTurnTxt = findViewById(R.id.oTurnTxt);
        TextView xTurnTxt = findViewById(R.id.xTurnTxt);
        oTurnTxt.setText(mPrefs.getString("oTurnTxt", ""));
        xTurnTxt.setText(mPrefs.getString("xTurnTxt", ""));
    }

    @Override
    public void onClick(View v) {
        String allBtnTxt = aButtons[0][0].getText().toString() + aButtons[0][1].getText() +
                aButtons[0][2].getText() + aButtons[1][0].getText() + aButtons[1][1].getText()
                + aButtons[1][2].getText() + aButtons[2][0].getText() + aButtons[2][1].getText()
                + aButtons[2][2].getText();

        int nId = v.getId();
        Button clickedBtn = findViewById(nId);
        TextView oTurnTxt = findViewById(R.id.oTurnTxt);
        TextView xTurnTxt = findViewById(R.id.xTurnTxt);
        String clickedBtnTxt = clickedBtn.getText().toString();

        if (allBtnTxt == "" && clickedBtnTxt == "") {
            clickedBtn.setText("x");
            oTurnTxt.setText("o's turn");
            xTurnTxt.setText("");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.x);
        }
        if (allBtnTxt.length() == 1 && clickedBtnTxt == "") {
            clickedBtn.setText("o");
            xTurnTxt.setText("x's turn");
            oTurnTxt.setText("");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.o);
        }
        if (allBtnTxt.length() == 2 && clickedBtnTxt == "") {
            clickedBtn.setText("x");
            oTurnTxt.setText("o's turn");
            xTurnTxt.setText("");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.x);
        }
        if (allBtnTxt.length() == 3 && clickedBtnTxt == "") {
            clickedBtn.setText("o");
            xTurnTxt.setText("x's turn");
            oTurnTxt.setText("");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.o);
        }
        if (allBtnTxt.length() == 4 && clickedBtnTxt == "") {
            clickedBtn.setText("x");
            oTurnTxt.setText("o's turn");
            xTurnTxt.setText("");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.x);
        }
        if (allBtnTxt.length() == 5 && clickedBtnTxt == "") {
            clickedBtn.setText("o");
            xTurnTxt.setText("x's turn");
            oTurnTxt.setText("");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.o);
        }
        if (allBtnTxt.length() == 6 && clickedBtnTxt == "") {
            clickedBtn.setText("x");
            oTurnTxt.setText("o's turn");
            xTurnTxt.setText("");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.x);
        }
        if (allBtnTxt.length() == 7 && clickedBtnTxt == "") {
            clickedBtn.setText("o");
            xTurnTxt.setText("x's turn");
            oTurnTxt.setText("");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.o);
        }
        if (allBtnTxt.length() == 8 && clickedBtnTxt == "") {
            clickedBtn.setText("x");
            oTurnTxt.setText("o's turn");
            xTurnTxt.setText("");
            clickedBtn.setTextScaleX(0);
            clickedBtn.setBackgroundResource(R.drawable.x);
        }

        if (clickedBtnTxt.equals("New Game")) {
            startNewGame();
        }

        checkWinner();
    }

    public void onBtnGoBackClick(View v) {
        Intent myIntent = new Intent(getBaseContext(), LandingActivity.class);
        startActivity(myIntent);
    }

    public void checkWinner() {
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

        TextView oTurnTxt = findViewById(R.id.oTurnTxt);
        TextView xTurnTxt = findViewById(R.id.xTurnTxt);
        TextView oPoints = findViewById(R.id.oWinPoint);
        TextView xPoints = findViewById(R.id.xWinPoint);

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
            playerXPoints++;
            xPoints.setText(Integer.toString(playerXPoints));
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
            playerOPoints++;
            oPoints.setText(Integer.toString(playerOPoints));
        }
        else {
            checkDraw();
        }
    }

    private void checkDraw() {
        TextView oTurnTxt = findViewById(R.id.oTurnTxt);
        TextView xTurnTxt = findViewById(R.id.xTurnTxt);

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
        }
    }

    public void startNewGame() {

        this.recreate();

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                aButtons[i][j].setText("");
                aButtons[i][j].setBackgroundResource(0);
            }
        }

        TextView oTurnTxt = findViewById(R.id.oTurnTxt);
        TextView xTurnTxt = findViewById(R.id.xTurnTxt);
        oTurnTxt.setText("");
        xTurnTxt.setText("x's turn");

        TextView oPoints = findViewById(R.id.oWinPoint);
        TextView xPoints = findViewById(R.id.xWinPoint);
        oPoints.setText(Integer.toString(playerOPoints));
        xPoints.setText(Integer.toString(playerXPoints));

    }

}