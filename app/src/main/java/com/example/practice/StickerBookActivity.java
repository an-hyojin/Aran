package com.example.practice;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class StickerBookActivity extends AppCompatActivity implements View.OnClickListener {

    Button backBtn;
    Button button1;
    StickerDBHelper helper;
    SQLiteDatabase sqlDB;

    ArrayList<String> gameList, dateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_book);
        backBtn = (Button)findViewById(R.id.back);
        backBtn.setOnClickListener(this);

        Cursor cursor = sqlDB.rawQuery("SELECT * FROM stickerTable;", null);
        cursor.moveToFirst();

        gameList = new ArrayList<>();
        dateList = new ArrayList<>();
        while(!cursor.isAfterLast()) {
            String game = cursor.getString(0);
            String date = cursor.getString(1);
            gameList.add(game);
            dateList.add(date);
            cursor.moveToNext();
        }

        button1 = (Button)findViewById(R.id.sticker01);
        button1.setText(gameList.get(0));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
        }
    }


}
