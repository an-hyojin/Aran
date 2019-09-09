package com.example.practice.Draw;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FaceDBHelper extends SQLiteOpenHelper {

    public FaceDBHelper(Context context){
        super(context, "faceDB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 데이터 베이스  테이블 생성
        db.execSQL("CREATE TABLE faceTBL (date LONG PRIMARY KEY, emotion TEXT, realImg BLOB, drawImg BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 해당 데이터 베이스 삭제
        db.execSQL("DROP TABLE IF EXISTS faceTBL");
        onCreate(db);
    }
}
