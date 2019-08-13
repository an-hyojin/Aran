package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class listViewItem extends LinearLayout {
    ImageView imageView;
    TextView EmotionText, EmotionCount;

    public listViewItem(Context context, String emotion, int imageResource, int count) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.customlistitem,this);
        imageView = (ImageView)findViewById(R.id.emotionImage);
        EmotionText = (TextView)findViewById(R.id.emotionText);
        EmotionCount = (TextView)findViewById(R.id.emotionCount);
        imageView.setImageResource(imageResource);
        EmotionText.setText(emotion);
        EmotionCount.setText(count + "번 이 감정을 느꼈습니다.");
    }

}
