package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GridItem extends LinearLayout {
    ImageButton btn;
    TextView title;
    public GridItem(Context context, Bitmap id, Bitmap image, Long date, String emotion) {
        super(context);
        setItem(context, id, image, date, emotion);
    }


    public void setItem(final Context context, Bitmap id, Bitmap image, final Long date, String emotion){
        View view = LayoutInflater.from(context).inflate(R.layout.griditem,this);
        btn = (ImageButton)findViewById(R.id.faceButton);
        title = (TextView)findViewById(R.id.emotionGrid);
        title.setText(emotion);

        Drawable bitmap = new BitmapDrawable(id);
        btn.setBackgroundDrawable(bitmap);
        btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ImageActivity.class);
                intent.putExtra("dateKey", date);
                getContext().startActivity(intent);
            }
        });
    }


}
