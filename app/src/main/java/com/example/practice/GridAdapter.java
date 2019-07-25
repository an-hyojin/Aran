package com.example.practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    ArrayList<Integer> img;
    Context context;
    public GridAdapter(Context context, ArrayList<Integer> img){
        this.context = context;
        this.img = img;
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
            convertView = new GridItem(context);
        };
        ((GridItem)convertView).setData(img.get(position));

        return convertView;
    }


}
