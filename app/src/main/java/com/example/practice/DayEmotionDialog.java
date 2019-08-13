package com.example.practice;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class DayEmotionDialog extends Dialog implements  View.OnClickListener{
    ImageButton smile, sad, angry,  heart, full, surprised, disgust, scary,etc;
    Button positiveButton, dismissButton;
    EditText emotionShow;
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
        etc = findViewById(R.id.etc);
        etc.setOnClickListener(this);
        emotionShow = findViewById(R.id.emotionText);
        emotionShow.setInputType(InputType.TYPE_NULL);
        positiveButton = findViewById(R.id.admit);
        positiveButton.setOnClickListener(this);
        dismissButton = findViewById(R.id.dismiss);
        dismissButton.setOnClickListener(this);

    }
    public void setDayEmotionDialogListener(DayEmotionDialogListener dayEmotionDialogListener){
        this.dayEmotionDialogListener = dayEmotionDialogListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.smile:
                emotionShow.setText(Emotion.기쁨.name());
                emotionShow.setInputType(InputType.TYPE_NULL);

                break;
            case R.id.sad:
                emotionShow.setText(Emotion.슬픔.name());
                emotionShow.setInputType(InputType.TYPE_NULL);
                break;
            case R.id.surprise:
                emotionShow.setText(Emotion.놀라움.name());
                emotionShow.setInputType(InputType.TYPE_NULL);
                break;
            case R.id.angry:
                emotionShow.setText(Emotion.화남.name());
                emotionShow.setInputType(InputType.TYPE_NULL);
                break;
            case R.id.scary:
                emotionShow.setText(Emotion.무서움.name());
                emotionShow.setInputType(InputType.TYPE_NULL);
                break;
            case R.id.full:
                emotionShow.setText(Emotion.뿌듯함.name());
                emotionShow.setInputType(InputType.TYPE_NULL);
                break;
            case R.id.heart:
                emotionShow.setText(Emotion.사랑.name());
                emotionShow.setInputType(InputType.TYPE_NULL);
                break;
            case R.id.disgust:
                emotionShow.setText(Emotion.싫어함.name());
                emotionShow.setInputType(InputType.TYPE_NULL);
                break;
            case R.id.etc:
                emotionShow.setText("감정을 입력해주세요!");
                emotionShow.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                break;
            case R.id.admit:
                dayEmotionDialogListener.onPositiveButtonClicked(emotionShow.getText().toString());
                dismiss();
                break;
            case R.id.dismiss:
                dismiss();
                break;
        }
    }
}
