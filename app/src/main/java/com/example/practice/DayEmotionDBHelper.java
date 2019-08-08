package com.example.practice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DayEmotionDBHelper extends SQLiteOpenHelper {
    public DayEmotionDBHelper(Context context){
        super(context, "DayEmotionDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE dayEmotionDB (date LONG PRIMARY KEY, year INTEGER, month INTEGER, day INTEGER, emotion TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS dayEmotionDB");
        onCreate(db);
    }
}
