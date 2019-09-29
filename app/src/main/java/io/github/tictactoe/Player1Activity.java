package io.github.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Player1Activity extends AppCompatActivity
    implements View.OnClickListener {

    private String oWinMsg = "O Win!";
    private String xWinMsg = "X Win!";
    private String drawMsg = "Draw!";

    Button aButtons[][] = new Button[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.player1);

        aButtons[0][0] = findViewById(R.id.button00);
        aButtons[0][1] = findViewById(R.id.button01);
        aButtons[0][2] = findViewById(R.id.button02);
        aButtons[1][0] = findViewById(R.id.button10);
        aButtons[1][1] = findViewById(R.id.button11);
        aButtons[1][2] = findViewById(R.id.button12);
        aButtons[2][0] = findViewById(R.id.button20);
        aButtons[2][1] = findViewById(R.id.button21);
        aButtons[2][2] = findViewById(R.id.button22);

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                aButtons[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        String allBtnTxt = aButtons[0][0].getText().toString() + aButtons[0][1].getText() +
                aButtons[0][2].getText() + aButtons[1][0].getText() + aButtons[1][1].getText()
                + aButtons[1][2].getText() + aButtons[2][0].getText() + aButtons[2][1].getText()
                + aButtons[2][2].getText();

        int nId = v.getId();
        Button clickedBtn = (Button) findViewById(nId);
        String clickedBtnTxt = clickedBtn.getText().toString();

        if (allBtnTxt == "" && clickedBtnTxt == "") {
            clickedBtn.setText("x");
        }
        if (allBtnTxt.length() == 1 && clickedBtnTxt == "") {
            clickedBtn.setText("o");
        }
        if (allBtnTxt.length() == 2 && clickedBtnTxt == "") {
            clickedBtn.setText("x");
        }
        if (allBtnTxt.length() == 3 && clickedBtnTxt == "") {
            clickedBtn.setText("o");
        }
        if (allBtnTxt.length() == 4 && clickedBtnTxt == "") {
            clickedBtn.setText("x");
        }
        if (allBtnTxt.length() == 5 && clickedBtnTxt == "") {
            clickedBtn.setText("o");
        }
        if (allBtnTxt.length() == 6 && clickedBtnTxt == "") {
            clickedBtn.setText("x");
        }
        if (allBtnTxt.length() == 7 && clickedBtnTxt == "") {
            clickedBtn.setText("o");
        }
        if (allBtnTxt.length() == 8 && clickedBtnTxt == "") {
            clickedBtn.setText("x");
        }

        checkWinner();
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

        if (firstRow.equals("xxx") || secondRow.equals("xxx") || lastRow.equals("xxx") ||
            firstColumn.equals("xxx") || secondColumn.equals("xxx") || lastColumn.equals("xxx") ||
            leftDiagonal.equals("xxx") || rightDiagonal.equals("xxx")) {
            Toast.makeText(this, xWinMsg, Toast.LENGTH_SHORT).show();
        }
        else if (firstRow.equals("ooo") || secondRow.equals("ooo") || lastRow.equals("ooo") ||
                firstColumn.equals("ooo") || secondColumn.equals("ooo") || lastColumn.equals("ooo")
                || leftDiagonal.equals("ooo") || rightDiagonal.equals("ooo")) {
            Toast.makeText(this, oWinMsg, Toast.LENGTH_SHORT).show();
        }
    }
}