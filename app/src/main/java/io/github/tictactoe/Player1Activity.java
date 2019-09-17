package io.github.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Player1Activity extends AppCompatActivity
    implements View.OnClickListener {

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

        aButtons[0][0].setOnClickListener(this);
        aButtons[0][1].setOnClickListener(this);
        aButtons[0][2].setOnClickListener(this);
        aButtons[1][0].setOnClickListener(this);
        aButtons[1][1].setOnClickListener(this);
        aButtons[1][2].setOnClickListener(this);
        aButtons[2][0].setOnClickListener(this);
        aButtons[2][1].setOnClickListener(this);
        aButtons[2][2].setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int nId = v.getId();
        if (nId == aButtons[0][0].getId()) {

        } else {

        }
    }
}
