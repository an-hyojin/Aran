package com.example.practice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GridItem extends LinearLayout {
    ImageButton btn;
    int id;
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

    public void setData(int imageId){
        id = imageId;
        btn.setBackgroundResource(imageId);
    }

}
