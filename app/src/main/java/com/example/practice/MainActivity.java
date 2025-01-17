package com.example.practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3,button4,button5,button6;
    @Override
    // 액티비티가 생성되는 순간에 딱 한번 호출
    // 모든 초기화와 사용자 인터페이스 설정이 여기에 들어감
    protected void onCreate(Bundle savedInstanceState) {
        // 부모클래스인 appCompatiactivity 클래스의 onCreate 호출
        super.onCreate(savedInstanceState);
        //setContentView() 함수는 액티비티의 화면을 설정 하는 함수
        setContentView(R.layout.activity_main);
        button1 = (Button)findViewById(R.id.btn1);
        button1.setOnClickListener(this);
        button2 = (Button)findViewById(R.id.btn2);
        button2.setOnClickListener(this);

        button3 = (Button)findViewById(R.id.btn3);
        button3.setOnClickListener(this);
        button4 = (Button)findViewById(R.id.btn4);
        button4.setOnClickListener(this);
        button5 = (Button)findViewById(R.id.btn5);
        button5.setOnClickListener(this);
        button6 = (Button)findViewById(R.id.btn6);
        button6.setOnClickListener(this);

    }

    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn1:
                intent = new Intent(this, DrawActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                intent = new Intent(this, MatchEmotionActivity.class);
                startActivity(intent);
                break;
            case R.id.btn3:
                intent = new Intent(this, SelectActivity.class);
                startActivity(intent);
                break;
            case R.id.btn4:
                intent = new Intent(this, SingAndAnimationSelectActivity.class);
                startActivity(intent);
                break;
            case R.id.btn5:
                intent = new Intent(this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.btn6:
                intent = new Intent(this, StickerBookActivity.class);
                startActivity(intent);
                break;
        }
    }
}
