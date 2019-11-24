/*
    PlayerOFragment.java

    Created by Irene Kwon
    Last Modified at Nov 24, 2019
*/

package io.github.tictactoe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerOFragment extends Fragment {

    public static String playerOName = "";

    private PlayerDB db;
    private ListView listview;
    private TextView noPlayers;

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
                Toast.makeText(getActivity(),
                        itemDetails, Toast.LENGTH_SHORT).show();
                v.setSelected(true);

                Map<String, Object> map = (Map<String, Object>) listview.getItemAtPosition(pos);
                String name = (String) map.get("name");
                playerOName = name;
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

