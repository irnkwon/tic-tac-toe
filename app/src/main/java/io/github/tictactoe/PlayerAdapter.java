/*
    PlayerAdapter.java

    Created by Irene Kwon
    Last Modified at Nov 22, 2019
*/

package io.github.tictactoe;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlayerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context c;
    ArrayList<PlayerModel> players;

    public PlayerAdapter(Context c, ArrayList<PlayerModel> players) {
        this.c = c;
        this.players = players;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_details,
                null);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int pos) {
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

    }

}
