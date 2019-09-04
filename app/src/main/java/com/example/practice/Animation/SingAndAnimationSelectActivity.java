package com.example.practice.Animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.practice.Dialog.CustomDialog;
import com.example.practice.R;

public class SingAndAnimationSelectActivity extends AppCompatActivity implements View.OnClickListener {

    Button backBtn;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_and_animation_select);

        backBtn = (Button)findViewById(R.id.back);
        backBtn.setOnClickListener(this);

        button1 = (Button) findViewById(R.id.animationButtonn);
        button2 = (Button) findViewById(R.id.singButtonn);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AnimationList.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SingList.class);
                startActivity(intent);
            }
        });
        boolean isRead = getIntent().getBooleanExtra("onOffSound", false);
        CustomDialog customDialog = new CustomDialog(SingAndAnimationSelectActivity.this, "애니메이션", "동요와 애니메이션 중 선택하여 원하는 영상을 시청해보세요.","확인", R.drawable.animation,isRead);
        customDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
        }
    }
}
