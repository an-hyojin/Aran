package com.example.practice.Draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    ArrayList<Bitmap> img;
    ArrayList<Bitmap> id;
    ArrayList<Long> date;
//    ArrayList<String> emotion;
    Context context;
                                                                                                        //, ArrayList<String> emotion
    public GridAdapter(Context context, ArrayList<Bitmap> img, ArrayList<Bitmap> id, ArrayList<Long> date){
        this.context = context;
        this.img = img;
        this.id = id;
        this.date = date;
//        this.emotion = emotion;
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
            //, emotion.get(position)
            convertView = new GridItem(context, img.get(position), id.get(position), date.get(position));
            int width = getWidthDP();
            int height =(int)( 1.1*width);
            convertView.setLayoutParams(new GridView.LayoutParams(width,height));
        };
        return convertView;


    }
    private int getWidthDP(){
        return  (int)((context.getResources().getDisplayMetrics().widthPixels)/(3.2));
    }


}
