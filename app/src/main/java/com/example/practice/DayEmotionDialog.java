package com.example.practice;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DayEmotionDialog extends Dialog implements  View.OnClickListener{
    ImageButton smile, sad, angry,  heart, full, surprised, disgust, scary,etc;
    Button positiveButton, dismissButton;
    EditText emotionShow;
    LinearLayout container;
    TextView showTitle;
    DayEmotionDialogListener dayEmotionDialogListener;
    public DayEmotionDialog(Context context, String classIswhat) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.todayemotion);
        container = findViewById(R.id.container);
        smile = findViewById(R.id.smile);
        smile.setOnClickListener(this);
        showTitle = findViewById(R.id.titleText);
        if(classIswhat.equals("SmallDraw")){
            System.out.println(context.getClass());
            showTitle.setText("이때 느낀 감정을 입력해주세요");
        }
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

            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) container.getLayoutParams();
            int width = context.getResources().getDisplayMetrics().widthPixels;
            width =(int) (width* 0.7);
            params.width = width;
            int height = context.getResources().getDisplayMetrics().heightPixels;
            params.height = (int) (height * 0.6);
            container.setLayoutParams(params);

        {
            LinearLayout images1 = findViewById(R.id.images1);
            LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) images1.getLayoutParams();
            param.height = width/3;
            images1.setLayoutParams(param);
        }

        {
            LinearLayout images = findViewById(R.id.images3);
            LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) images.getLayoutParams();
            param.height = width/3;
            images.setLayoutParams(param);
        }

        {
            LinearLayout images = findViewById(R.id.images2);
            LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) images.getLayoutParams();
            param.height = width/3;
            images.setLayoutParams(param);
        }
    }


    public void setDayEmotionDialogListener(DayEmotionDialogListener dayEmotionDialogListener){
        this.dayEmotionDialogListener = dayEmotionDialogListener;
    }

    @Override
    public void onClick(View v) {
        emotionShow.setClickable(false);
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
                emotionShow.setText(Emotion.놀람.name());
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
                emotionShow.setClickable(true);
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
