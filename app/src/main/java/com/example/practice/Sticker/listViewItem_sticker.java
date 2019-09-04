package com.example.practice.Sticker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.practice.R;

public class listViewItem_sticker extends LinearLayout {
    ImageView imageView;
    TextView GameText, GameCount;

    public listViewItem_sticker(Context context, String game, int imageResource, int count) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.customlistitem_sticker,this);
        imageView = (ImageView)findViewById(R.id.gameImage);
        GameText = (TextView)findViewById(R.id.gameText);
        GameCount = (TextView)findViewById(R.id.gameCount);
        imageView.setImageResource(imageResource);
        GameText.setText(game);
        GameCount.setText(count + "번 이 게임을 했습니다.");
    }

}
