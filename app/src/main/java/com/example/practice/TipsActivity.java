package com.example.practice;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TipsActivity extends AppCompatActivity {
    Button bt1,bt2;
    Button back;
    FragmentManager fm;
    EmotionsFrag emotionsFrag;
    SolutionFrag solutionFrag;
    FragmentTransaction tran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        emotionsFrag = new EmotionsFrag(); //프래그먼트 객채셍성
        solutionFrag = new SolutionFrag();
        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
        tran.replace(R.id.container, emotionsFrag);
        tran.commit();
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getSupportFragmentManager();
                tran = fm.beginTransaction();
                tran.replace(R.id.container, emotionsFrag);
                tran.commit();

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm =getSupportFragmentManager();
                tran = fm.beginTransaction();
                tran.replace(R.id.container,solutionFrag);
                tran.commit();

            }
        });
        CustomDialog customDialog = new CustomDialog(TipsActivity.this, "감정들과 솔루션", "아란에서 사용하는 여덟개의 감정외에 많은 감정들을 학습하고, 아이들에게 알려주세요. \n 그리고 솔루션에서 육아 팁도 확인해보세요.", "확인", R.drawable.heart, false);
        customDialog.show();
    }
}
