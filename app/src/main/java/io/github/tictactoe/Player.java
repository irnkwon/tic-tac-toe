/*
    PlayerDB.java

    Created by Irene Kwon
    Last Modified at Nov 22, 2019
*/

package io.github.tictactoe;

public class Player {

    private int id;
    private String name, wins, losses, ties;

    public Player(int id, String name, String wins, String losses, String ties) {
        this.id = id;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getWins() { return wins; }

    public void setWins(String wins) { this.wins = wins; }

    public String getLosses() { return losses; }

    public void setLosses(String losses) { this.losses = losses; }

    public String getTies() { return ties; }

    public void setTies(String ties) { this.ties = ties; }

}
