package com.example.practice.Sticker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.practice.R;

import java.util.ArrayList;

public class listViewAdapter_sticker extends BaseAdapter {
    ArrayList<String> gameList;
    ArrayList<Integer> gameCountList;

    Context context;
    public listViewAdapter_sticker(Context context, ArrayList<String> gameList, ArrayList<Integer> gameCountList){
        this.context = context;
        this.gameList = gameList;
        this.gameCountList = gameCountList;
        System.out.println("크기 : " + gameCountList.size());
    }

    @Override
    public int getCount() {
        return gameCountList.size();
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
        ListViewHolder holder;

        if (convertView == null) {
            holder = new ListViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.customlistitem_sticker, null);
            holder.game1 = (TextView) convertView.findViewById(R.id.gameText);
            holder.gameCount2 = (TextView) convertView.findViewById(R.id.gameCount);
            holder.gameImage = (ImageView) convertView.findViewById(R.id.gameImage);
            convertView.setTag(holder);
        } else {
            holder = (ListViewHolder) convertView.getTag();
        }
        holder.game1.setText(gameList.get(position));
        holder.gameCount2.setText("이 게임을 " +gameCountList.get(position).toString()+"번 했습니다.");

        int resource = R.drawable.what_game;

        for (Game g : Game.values()) {
            if (gameList.get(position).equals(g.name())) {
                resource = g.getGameResource();
                break;
            }
        }
        holder.gameImage.setImageResource(resource);
        convertView.setLayoutParams(new ListView.LayoutParams(getWidthDP(), getCellHeightDP()));

        return convertView;
    }


    private int getCellHeightDP() {
        int height = context.getResources().getDisplayMetrics().heightPixels;
        return height/10 ;
    }
    private int getWidthDP(){
        return  context.getResources().getDisplayMetrics().widthPixels;
    }

    private class ListViewHolder{
        TextView game1, gameCount2;
        ImageView gameImage;
    }
}
