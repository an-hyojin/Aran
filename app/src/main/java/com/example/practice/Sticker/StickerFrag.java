package com.example.practice.Sticker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.example.practice.R;

import java.util.ArrayList;

public class StickerFrag extends Fragment {

    View view;
    Button delete;
    ArrayList<String> gameList, dateList;
    StickerDBHelper stickerDB;
    SQLiteDatabase sqlDB;
    StickerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stickerbook, container, false);
        gameList = new ArrayList<>();
        dateList = new ArrayList<>();

        GridView gridView = view.findViewById(R.id.stickerGridView);
        gridView.setAdapter(new StickerAdapter(getContext(), gameList, dateList));
        delete = view.findViewById(R.id.deleteButton);
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                stickerDB.deleteAll(sqlDB);
                gameList.clear();
                dateList.clear();
                adapter.notifyDataSetChanged();
            }
        });

        stickerDB = new StickerDBHelper(getContext());
        sqlDB = stickerDB.getWritableDatabase();
        sqlDB = stickerDB.getReadableDatabase();

        Cursor cursor = sqlDB.rawQuery("SELECT * FROM stickerTable;", null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            String game = cursor.getString(1);
            String date = cursor.getString(2);
            gameList.add(game);
            dateList.add(date);
            cursor.moveToNext();
        }

        adapter = new StickerAdapter(getActivity(), gameList, dateList);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}

