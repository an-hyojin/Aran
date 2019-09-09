package com.example.practice.Draw;

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

public class GridItem extends LinearLayout {
    ImageButton btn;
    TextView title;
    //, String emotion
    public GridItem(Context context, Bitmap id, Bitmap image, Long date) {
        super(context);
        //, emotion
        setItem(context, id, image, date);
    }

//, String emotion
    public void setItem(final Context context, Bitmap id, Bitmap image, final Long date){
        View view = LayoutInflater.from(context).inflate(R.layout.griditem,this);
        btn = (ImageButton)findViewById(R.id.faceButton);
        title = (TextView)findViewById(R.id.emotionGrid);
//        title.setText(emotion);

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
