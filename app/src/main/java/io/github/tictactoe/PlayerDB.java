/*
    PlayerDB.java

    Created by Irene Kwon
    Last Modified at Nov 24, 2019
*/

package io.github.tictactoe;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerDB {

    // Constants

    public static final String DB_NAME = "player_db";
    public static final int DB_VERSION = 1;

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // PlayerDB Constructor

    public PlayerDB(Context context) {

        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        openWritableDB();
        closeDB();

    }

    // Database Helper

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE players (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "name VARCHAR NOT NULL, wins INTEGER NOT NULL DEFAULT 0, " +
                    "losses INTEGER NOT NULL DEFAULT 0, ties INTEGER NOT NULL DEFAULT 0)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS \"players\"");
            this.onCreate(db);
        }

    }

    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWritableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null) {
            db.close();
        }
    }

    // User Defined Methods

    public void insertPlayer(String name) throws Exception {

        openWritableDB();
        ContentValues cv = new ContentValues();
        cv.put("name", name);

        long nResult = db.insert("players", null, cv);

        if (nResult == -1) {
            throw new Exception("No Data");
        }

        closeDB();

    }

    public ArrayList<HashMap<String, String>> getPlayers() {

        openReadableDB();
        ArrayList<HashMap<String, String>> data = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT name, wins, losses, ties FROM players " +
            "ORDER BY wins DESC", null );
        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", cursor.getString(0));
            map.put("wins", cursor.getString(1));
            map.put("losses", cursor.getString(2));
            map.put("ties", cursor.getString(3));
            data.add(map);
        }
        if (cursor != null) {
            cursor.close();
            closeDB();
        }

        return data;
    }

}
