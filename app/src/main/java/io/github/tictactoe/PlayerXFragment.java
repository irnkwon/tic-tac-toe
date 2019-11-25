/*
    PlayerXFragment.java

    Created by Irene Kwon
    Last Modified at Nov 24, 2019
*/

package io.github.tictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerXFragment extends Fragment {

    public static int playerXId;
    public static String playerXName = "";

    private PlayerDB db;
    private ListView listview;
    private TextView noPlayers;
    private int playerOId = PlayerOFragment.playerOId;
    private int pink = Color.parseColor("#E75480");

    public PlayerXFragment() { }

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
                final int pid = Integer.valueOf((String) map.get("id"));
                String pname = (String) map.get("name");
                playerXId = pid;
                playerXName = pname;

                new MaterialAlertDialogBuilder(getActivity())
                        .setMessage(pname + " has been selected as player 2.")
                        .setPositiveButton("Ok", null)
                        .setNegativeButton("Delete Player",
                                new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deletePlayer(pid);

                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                if (Build.VERSION.SDK_INT >= 26) {
                                    ft.setReorderingAllowed(false);
                                }
                                ft.detach(PlayerXFragment.this).attach(PlayerXFragment.this).commit();
                            }
                        })
                        .show();
            }
        });

        updateScreen();
    }

    private void deletePlayer(int playerId) {
        int id = playerId;

        if (!String.valueOf((Integer) id).isEmpty()) {
            try {
                db.removePlayer(id);
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

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, res, from, to);
        listview.setAdapter(adapter);

    }

}

