package com.example.practice;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

public class CalendarActivity extends AppCompatActivity {
    Button bt1,bt2,bt3;
    Button back;
    FragmentManager fm;
    FragmentTransaction tran;
    TipFrag tipFrag;
    CalendarFrag calendarFrag;
    StatisticsFrag statisticsFrag;
    TabHost tabs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tipFrag = new TipFrag(); //프래그먼트 객채셍성
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
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm =getSupportFragmentManager();
                tran = fm.beginTransaction();
                tran.replace(R.id.container,tipFrag);
                tran.commit();
            }
        });


    }

}