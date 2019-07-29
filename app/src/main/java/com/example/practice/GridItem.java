package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GridItem extends LinearLayout {
    ImageButton btn;
    int id;
    private final int GALLERY_CODE=1112;
    public GridItem(Context context) {
        super(context);
        init(context);
        id = 0;
    }


    public void init(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.griditem,this);
        btn = (ImageButton)findViewById(R.id.facebutton);
        btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "하이", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setData(Bitmap bitmap){
        Drawable background = new BitmapDrawable(bitmap);
        btn.setBackground(background);
    }
    public void setId(int id){
        this.id = id;
    }
}
