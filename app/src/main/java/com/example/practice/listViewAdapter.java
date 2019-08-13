package com.example.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class listViewAdapter extends BaseAdapter {
    ArrayList<String> emotionList;
    ArrayList<Integer> emotionCountList;

    Context context;
    public listViewAdapter(Context context, ArrayList<String> emotionList, ArrayList<Integer> emotionCountList){
        this.context = context;
        this.emotionList = emotionList;
        this.emotionCountList = emotionCountList;
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
        if(convertView == null){
            int resource = R.drawable.circle;
            String emotionString = emotionList.get(position);
            for(Emotion e : Emotion.values()){
               if(emotionList.get(position).equals(e.name())){
                   resource= e.getEmotionResource();
                   break;
               }
           }

            convertView = new listViewItem(context,emotionString, resource, emotionCountList.get(position));
            convertView.setLayoutParams(new ListView.LayoutParams(getWidthDP(), getCellHeightDP()));
        };
        return convertView;
    }


    private int getCellHeightDP() {
        int height = context.getResources().getDisplayMetrics().heightPixels;
        return height/10 ;
    }
    private int getWidthDP(){
        return  context.getResources().getDisplayMetrics().widthPixels;
    }

}
