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
    Bitmap id, image;
    String emotion;
    Long date;
    private final int GALLERY_CODE=1112;
    public GridItem(Context context, Bitmap id, Bitmap image, Long date, String emotion) {
        super(context);
        init(context, id, image, date, emotion);
    }


    public void init(Context context,Bitmap id, Bitmap image, Long date, String emotion){
        View view = LayoutInflater.from(context).inflate(R.layout.griditem,this);
        btn = (ImageButton)findViewById(R.id.facebutton);
        Drawable bitmap = new BitmapDrawable(id);
        btn.setBackgroundDrawable(bitmap);
        final FaceAndImageDialog faceAndImageDialog = new FaceAndImageDialog(context, id, image, emotion, date);
        btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                faceAndImageDialog.show();
            }
        });
    }


}
