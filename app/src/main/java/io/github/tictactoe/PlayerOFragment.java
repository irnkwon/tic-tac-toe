/*
    PlayerOFragment.java

    Created by Irene Kwon
    Last Modified at Nov 24, 2019
*/

package io.github.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerOFragment extends Fragment {

    public static int playerOId;
    public static String playerOName = "";

    private PlayerDB db;
    private ListView listview;
    private TextView noPlayers;
    private int playerXId = PlayerXFragment.playerXId;
    private int pink = Color.parseColor("#E75480");

    public PlayerOFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.select_player, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listview = view.findViewById(R.id.listview);
        noPlayers = view.findViewById(R.id.no_players);

        db = new PlayerDB(getActivity());

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                String itemDetails = listview.getItemAtPosition(pos).toString();
                v.setSelected(true);

                Map<String, Object> map = (Map<String, Object>) listview.getItemAtPosition(pos);
                int pid = Integer.valueOf((String) map.get("id"));
                String pname = (String) map.get("name");
                playerOId = pid;
                playerOName = pname;

                new MaterialAlertDialogBuilder(getActivity())
                        .setMessage(pname + " has been selected as player 1.")
                        .setPositiveButton("Ok", null)
                        .show();
            }
        });

        updateScreen();
    }

    private void updateScreen() {
        ArrayList<HashMap<String, String>> data = db.getPlayers();

        if (data.equals(null)) {
            noPlayers.setVisibility(View.VISIBLE);
        }

        int res = R.layout.player_list;
        String[] from = {"name"};
        int[] to = {R.id.player_name};

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, res, from, to);
        listview.setAdapter(adapter);

    }

}

