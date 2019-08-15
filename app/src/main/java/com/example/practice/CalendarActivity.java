package com.example.practice;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

public class CalendarActivity extends AppCompatActivity {
    Button bt1,bt2;
    Button back;
    FragmentManager fm;
    FragmentTransaction tran;
    CalendarFrag calendarFrag;
    StatisticsFrag statisticsFrag;
    TabHost tabs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        calendarFrag = new CalendarFrag(); //프래그먼트 객채셍성
        statisticsFrag = new StatisticsFrag();
        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
        tran.replace(R.id.container,calendarFrag);
        tran.commit();
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getSupportFragmentManager();
                tran = fm.beginTransaction();
                tran.replace(R.id.container,calendarFrag);
                tran.commit();

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm =getSupportFragmentManager();
                tran = fm.beginTransaction();
                tran.replace(R.id.container,statisticsFrag);
                tran.commit();

            }
        });
        CustomDialog customDialog = new CustomDialog(CalendarActivity.this, "감정 캘린더", "캘린더에서 하루에 하나씩 감정을 입력하세요. \n 통계에서 입력했던 감정들을 한눈에 볼 수 있습니다.", "확인", R.drawable.calendar,false);
        customDialog.show();

    }

}