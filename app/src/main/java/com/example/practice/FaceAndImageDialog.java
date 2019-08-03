package com.example.practice;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FaceAndImageDialog extends Dialog implements View.OnClickListener {
    ImageButton admitBtn;
    ImageView photo, image;
    TextView emotion, date;
    DialogListenerInterface customDialogLister;

    public FaceAndImageDialog(Context context, Bitmap photoBitmap, Bitmap imageBitmap, String emotionString, Long dateString){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.faceandimagedialog);
        admitBtn= (ImageButton)findViewById(R.id.admit);
        photo = (ImageView)findViewById(R.id.photo);
        image = (ImageView) findViewById(R.id.image);
        emotion = (TextView)findViewById(R.id.emotion);
        date = (TextView)findViewById(R.id.date);
        admitBtn.setOnClickListener(this);
        Drawable photoDrawable = new BitmapDrawable(photoBitmap);
        Drawable imageDrawable = new BitmapDrawable(imageBitmap);
        photo.setBackgroundDrawable(photoDrawable);
        image.setBackgroundDrawable(imageDrawable);
        SimpleDateFormat dayTime = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분ss초");
        String str = dayTime.format(new Date(dateString));
        emotion.setText(emotionString);
        date.setText(str);
    }


    public void setDialogListener(DialogListenerInterface customDialogListener){
        this.customDialogLister = customDialogListener;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.admit:
                dismiss();
                break;
        }
    }
}
