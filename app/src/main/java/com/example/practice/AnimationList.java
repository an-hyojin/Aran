package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class AnimationList extends AppCompatActivity implements View.OnClickListener {

    ImageButton backBtn;
    ImageButton animationbutton1, animationbutton2, animationbutton3, animationbutton4, animationbutton5, animationbutton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_list);

        backBtn = (ImageButton)findViewById(R.id.back);
        backBtn.setOnClickListener(this);

        animationbutton1 = (ImageButton) findViewById(R.id.animation1);
        animationbutton1.setOnClickListener(this);
        animationbutton2 = (ImageButton) findViewById(R.id.animation2);
        animationbutton2.setOnClickListener(this);
        animationbutton3 = (ImageButton) findViewById(R.id.animation3);
        animationbutton3.setOnClickListener(this);
        animationbutton4 = (ImageButton) findViewById(R.id.animation4);
        animationbutton4.setOnClickListener(this);
        animationbutton5 = (ImageButton) findViewById(R.id.animation5);
        animationbutton5.setOnClickListener(this);
        animationbutton6 = (ImageButton) findViewById(R.id.animation6);
        animationbutton6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.animation1: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("videoId", "0_95kpvfQEg");
                startActivity(intent);
                break;
            }
            case R.id.animation2: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("videoId", "_866dw6Rbh4");
                startActivity(intent);
                break;
            }
            case R.id.animation3: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("videoId", "hJorGesHoFQ");
                startActivity(intent);
                break;
            }
            case R.id.animation4: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("videoId", "9coGbkCM7dQ");
                startActivity(intent);
                break;
            }
            case R.id.animation5: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("videoId", "vQNIcCW05Ds");
                startActivity(intent);
                break;
            }
            case R.id.animation6: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("videoId", "VobPmKbiUy4");
                startActivity(intent);
                break;
            }
        }
    }
}
