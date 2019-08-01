package com.example.practice;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    ArrayList<Bitmap> img;
    ArrayList<Bitmap> id;
    ArrayList<Long> date;
    ArrayList<String> emotion;
    Context context;
    public GridAdapter(Context context, ArrayList<Bitmap> img, ArrayList<Bitmap> id, ArrayList<Long> date, ArrayList<String> emotion){
        this.context = context;
        this.img = img;
        this.id = id;
        this.date = date;
        this.emotion = emotion;
    }
    @Override
    public int getCount() {
        return img.size();
    }

    @Override
    public Object getItem(int position) {
        return img.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = new GridItem(context, img.get(position), id.get(position), date.get(position), emotion.get(position));
        };


        return convertView;
    }


}
