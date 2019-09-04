package com.example.practice.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.practice.Calendar.Emotion;
import com.example.practice.R;

import java.util.ArrayList;

public class listViewAdapter extends BaseAdapter {
    ArrayList<String> emotionList;
    ArrayList<Integer> emotionCountList;

    Context context;
    public listViewAdapter(Context context, ArrayList<String> emotionList, ArrayList<Integer> emotionCountList){
        this.context = context;
        this.emotionList = emotionList;
        this.emotionCountList = emotionCountList;
        System.out.println("크기 : " + emotionCountList.size());
    }

    @Override
    public int getCount() {
        return emotionCountList.size();
    }

    @Override
    public Object getItem(int position) {
        return emotionList.get(position);
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
            convertView = inflater.inflate(R.layout.customlistitem, null);
            holder.emotion1 = (TextView) convertView.findViewById(R.id.emotionText);
            holder.emotionCount2 = (TextView) convertView.findViewById(R.id.emotionCount);
            holder.emotionImage = (ImageView) convertView.findViewById(R.id.emotionImage);
            convertView.setTag(holder);
        } else {
            holder = (ListViewHolder) convertView.getTag();
        }
        holder.emotion1.setText(emotionList.get(position));
        holder.emotionCount2.setText("이 감정을 " +emotionCountList.get(position).toString()+"번 느꼈습니다.");

        int resource = R.drawable.what_emotion;

        for (Emotion e : Emotion.values()) {
            if (emotionList.get(position).equals(e.name())) {
                resource = e.getEmotionResource();
                break;
            }
        }
        holder.emotionImage.setImageResource(resource);
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
        TextView emotion1, emotionCount2;
        ImageView emotionImage;
    }
}
