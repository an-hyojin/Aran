package com.example.practice.Sticker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class StickerAdapter extends BaseAdapter {
    ArrayList<String> gameList = new ArrayList<String>();
    ArrayList<String> dateList = null;
    Context context;

    public StickerAdapter(Context context,ArrayList<String> game, ArrayList<String> date){
        this.context = context;
        this.gameList = game;
        this.dateList = date;
    }
    @Override
    public int getCount() {
        if(gameList == null) {
            gameList = new ArrayList<String>();
            return 0;
        }
        return gameList.size();
    }

    @Override
    public Object getItem(int position) {
        return gameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = new StickerItem(context, gameList.get(position), dateList.get(position));
            int width = getWidthDP();
            convertView.setLayoutParams(new GridView.LayoutParams(width,width));
        };
        return convertView;

    }
    private int getWidthDP(){
        return  (int)((context.getResources().getDisplayMetrics().widthPixels)/(4));
    }


}
