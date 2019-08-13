package com.example.practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SelectActivity extends AppCompatActivity {
    Button backBtn;
    private final int END_GAME =1;
    Button easyBtn, middleBtn, hardBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        easyBtn = findViewById(R.id.easy);
        easyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent easyIntent = new Intent(getApplicationContext(), EasyCardActivity.class);
                easyIntent.putExtra("level", 0);
                startActivityForResult(easyIntent, END_GAME);
            }
        });

        middleBtn = findViewById(R.id.middle);
        middleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent middleIntent = new Intent(getApplicationContext(), MiddleCardActivity.class);
                middleIntent.putExtra("level", 1);
                startActivityForResult(middleIntent, END_GAME);
            }

        });

        hardBtn = findViewById(R.id.hard);
        hardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hardIntent = new Intent(getApplicationContext(), CardActivity.class);
                hardIntent.putExtra("level", 2);
                startActivityForResult(hardIntent, END_GAME);
            }
        });
        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
