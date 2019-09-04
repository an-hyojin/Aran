package com.example.practice.Animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.practice.R;

public class AnimationList extends AppCompatActivity implements View.OnClickListener {

    Button backBtn;
    ImageButton animationbutton1, animationbutton2, animationbutton3, animationbutton4, animationbutton5, animationbutton6;
    TextView ani1, ani2,ani3, ani4, ani5,ani6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_list);

        backBtn = (Button)findViewById(R.id.back);
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
        ani1 = findViewById(R.id.ani_text1);
        ani2 = findViewById(R.id.ani_text2);
        ani3 = findViewById(R.id.ani_text3);
        ani4 = findViewById(R.id.ani_text4);
        ani5 = findViewById(R.id.ani_text5);
        ani6 = findViewById(R.id.ani_text6);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.animation1: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("Id", "0_95kpvfQEg");
                intent.putExtra("name",ani1.getText());
                startActivity(intent);
                break;
            }
            case R.id.animation2: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("Id", "_866dw6Rbh4");
                intent.putExtra("name",ani2.getText());
                startActivity(intent);
                break;
            }
            case R.id.animation3: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("name",ani3.getText());
                intent.putExtra("Id", "hJorGesHoFQ");
                startActivity(intent);
                break;
            }
            case R.id.animation4: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("Id", "9coGbkCM7dQ");
                intent.putExtra("name",ani4.getText());
                startActivity(intent);
                break;
            }
            case R.id.animation5: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("Id", "vQNIcCW05Ds");
                intent.putExtra("name",ani5.getText());
                startActivity(intent);
                break;
            }
            case R.id.animation6: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("Id", "VobPmKbiUy4");
                intent.putExtra("name",ani6.getText());
                startActivity(intent);
                break;
            }
        }
    }
}
