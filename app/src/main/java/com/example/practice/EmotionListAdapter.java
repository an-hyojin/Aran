package com.example.practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EmotionListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> titles, texts;
    ArrayList<Integer> imageResource;
    public EmotionListAdapter(Context context, ArrayList<Integer> ImageResource, ArrayList<String> title, ArrayList<String> textView){
        this.context = context;
        titles = title;
        this.imageResource = ImageResource;
        texts = textView;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHolder holder;

        if (convertView == null) {
            holder = new ItemHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.emotions_explain, null);
            holder.emotionDrawable = (ImageView) convertView.findViewById(R.id.imageEmotion);
            holder.emotionTitle = (TextView) convertView.findViewById(R.id.emotionTitle);
            holder.emotionExplain = (TextView) convertView.findViewById(R.id.emotionExplain);
            convertView.setTag(holder);
        } else {
            holder = (ItemHolder) convertView.getTag();
        }
        holder.emotionTitle.setText(titles.get(position));
        holder.emotionExplain.setText(texts.get(position));
        holder.emotionDrawable.setBackgroundResource(imageResource.get(position));

//        convertView.setLayoutParams(new ListView.LayoutParams(getWidthDP(), getCellHeightDP()));

        return convertView;
    }

    class ItemHolder{
        TextView emotionTitle, emotionExplain;
        ImageView emotionDrawable;
    }
}
