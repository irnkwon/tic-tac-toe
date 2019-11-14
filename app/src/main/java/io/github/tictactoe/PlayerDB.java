package io.github.tictactoe;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerDB {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public PlayerDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        openWritableDB();
        closeDB();
    }

    public static final String DB_NAME = "player_db";
    public static final int DB_VERSION = 1;

    // 1. DB Helper class
    private static class DBHelper extends SQLiteOpenHelper {

        // 1.1 DB Helper Constructor
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        // 1.2 onCreate
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE players (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "name VARCHAR NOT NULL, wins INTEGER NOT NULL DEFAULT 0, " +
                    "losses INTEGER NOT NULL DEFAULT 0, ties INTEGER NOT NULL DEFAULT 0)");
        }

        // 1.3 onUpgrade
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE \"players\"");
            Log.d("DB Upgrade", "Upgrading db from version " + oldVersion + " to "
                + newVersion);
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

    // CREATE
    public void insertPlayer(String name) throws Exception {

        openWritableDB();
        ContentValues content = new ContentValues();
        content.put("name", name);

        long nResult = db.insert("players", null, content);

        if (nResult == -1) {
            throw new Exception("No Data");
        }
        closeDB();
    }

    // READ
    ArrayList<HashMap<String, String>> getPlayers() {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        openReadableDB();

        Cursor cursor = db.rawQuery("SELECT name, wins, losses, ties FROM players",
                null );
        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", cursor.getString(0));
            map.put("wins", cursor.getString(1));
            map.put("losses", cursor.getString(2));
            map.put("ties", cursor.getString(3));
        }
        if (cursor != null) {
            cursor.close();
            closeDB();
        }
        return data;
    }
}
