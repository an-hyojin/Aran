package com.example.practice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StickerDBHelper extends SQLiteOpenHelper {

    public StickerDBHelper(Context context) { super(context, "stickerDB", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE stickerTable (_id INTEGER PRIMARY KEY AUTOINCREMENT, game TEXT, date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS stickerDB");
        onCreate(db);
    }

//    SQLiteDatabase stickerDB = null;
//
//    public StickerDBHelper() {
//        openDB();
//    }
//
//    public void openDB() {
//        try {
//            stickerDB = SQLiteDatabase.openOrCreateDatabase("sample.db", null);
//        } catch (SQLiteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void createTable() {
//        String table = "CREATE TABLE IF NOT EXISTS STICKER (GAME TEXT, DATE TEXT)";
//        stickerDB.execSQL(table);
//    }
//
//    public void insertData(String game, String date) {
//        String data = "INSERT INTO STICKER (GAME, DATE) VALUES ('game', 'date')";
//        stickerDB.execSQL(data);
//    }
//
//    public void deleteAll() {
//        String data = "DELETE FROM STICKER";
//        stickerDB.execSQL(data);
//    }
//
//    public void initiate(String game, String date) {
//        openDB();
//        createTable();
//        insertData(game, date);
//    }
//
//    public String[] getData() {
//        String data = "SELECT * FROM STICKER";
//        Cursor cursor = null;
//        String[] realData = new String[100];
//        int count = 0;
//
//        cursor = stickerDB.rawQuery(data, null);
//        while (cursor.moveToNext()) {
//            String game = cursor.getString(0);
//            String date = cursor.getString(1);
//            realData[count] = game + "\n" + date;
//            count++;
//        }
//        return realData;
//    }
}
