package com.example.practice.Sticker;

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

import com.example.practice.R;

public class StickerItem extends LinearLayout {

    ImageButton btn;
    TextView title;

    public StickerItem(Context context, String game, String date) {
        super(context);
        setItem(context, game, date);
    }

    public void setItem(final Context context, String game, String date){
        View view = LayoutInflater.from(context).inflate(R.layout.sticker,this);
//        btn = (ImageButton)findViewById(R.id.whiteButton);
        title = (TextView)findViewById(R.id.stickerGrid);
        if(game.equals("노래")){
            title.setBackgroundResource(R.drawable.color);
        }else if(game.equals("감정맞추기")){
            title.setBackgroundResource(R.drawable.color2);
        }else if(game.equals("감정따라그리기")){
            title.setBackgroundResource(R.drawable.color3);
        }else if(game.equals("감정카드놀이")){
            title.setBackgroundResource(R.drawable.color4);
        }else{
            title.setBackgroundResource(R.drawable.color5);
        }
        title.setText(game +"\n"+date);
    }


}
