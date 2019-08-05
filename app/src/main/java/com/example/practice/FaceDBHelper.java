package com.example.practice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FaceDBHelper extends SQLiteOpenHelper {

    public FaceDBHelper(Context context){
        super(context, "faceDB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE faceTBL (date LONG PRIMARY KEY, emotion TEXT, realImg BLOB, drawImg BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS faceTBL");
        onCreate(db);
    }
}
