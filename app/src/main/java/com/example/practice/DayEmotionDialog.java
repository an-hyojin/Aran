package com.example.practice;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class DayEmotionDialog extends Dialog implements  View.OnClickListener{
    ImageButton smile, sad, angry,  heart, full, surprised, disgust, scary;
    DayEmotionDialogListener dayEmotionDialogListener;
    public DayEmotionDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.todayemotion);
        smile = findViewById(R.id.smile);
        smile.setOnClickListener(this);
        sad = findViewById(R.id.sad);
        sad.setOnClickListener(this);
        angry = findViewById(R.id.angry);
        angry.setOnClickListener(this);
        heart = findViewById(R.id.heart);
        heart.setOnClickListener(this);
        full= findViewById(R.id.full);
        full.setOnClickListener(this);
        surprised = findViewById(R.id.surprise);
        surprised.setOnClickListener(this);
        disgust = findViewById(R.id.disgust);
        disgust.setOnClickListener(this);
        scary = findViewById(R.id.scary);
        scary.setOnClickListener(this);


    }
    public void setDayEmotionDialogListener(DayEmotionDialogListener dayEmotionDialogListener){
        this.dayEmotionDialogListener = dayEmotionDialogListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.smile:
                dayEmotionDialogListener.onSmileButtonClicked();
                dismiss();
                break;
            case R.id.sad:
                dayEmotionDialogListener.onSadButtonClicked();
                dismiss();
                break;
            case R.id.surprise:
                dayEmotionDialogListener.onSurprisedButtonClicked();
                dismiss();
                break;
            case R.id.angry:
                dayEmotionDialogListener.onAngryButtonClicked();
                dismiss();
                break;
            case R.id.scary:
                dayEmotionDialogListener.onScaryButtonClicked();
                dismiss();
                break;
            case R.id.full:
                dayEmotionDialogListener.onFullButtonClicked();
                dismiss();
                break;
            case R.id.heart:
                dayEmotionDialogListener.onHeartButtonClicked();
                dismiss();
                break;
            case R.id.disgust:
                dayEmotionDialogListener.onDisgustButtonClicked();
                dismiss();
                break;
        }
    }
}
