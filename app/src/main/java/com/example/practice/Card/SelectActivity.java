package com.example.practice.Card;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.practice.R;

public class SelectActivity extends AppCompatActivity {
    Button backBtn;
    private final int END_GAME =1;
    Button easyBtn, middleBtn, hardBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        final boolean isSound = getIntent().getBooleanExtra("onOffSound", false);
        easyBtn = findViewById(R.id.easy);
        easyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent easyIntent = new Intent(getApplicationContext(), EasyCardActivity.class);
                easyIntent.putExtra("level", 0);
                easyIntent.putExtra("onOffSound",isSound);
                startActivityForResult(easyIntent, END_GAME);
            }
        });

        middleBtn = findViewById(R.id.middle);
        middleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent middleIntent = new Intent(getApplicationContext(), MiddleCardActivity.class);
                middleIntent.putExtra("level", 1);
                middleIntent.putExtra("onOffSound",isSound);
                startActivityForResult(middleIntent, END_GAME);
            }

        });

        hardBtn = findViewById(R.id.hard);
        hardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hardIntent = new Intent(getApplicationContext(), CardActivity.class);
                hardIntent.putExtra("onOffSound",isSound);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            onBackPressed();
        }
    }
}
