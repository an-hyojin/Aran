package com.example.practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {


    ImageButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        backBtn = (ImageButton)findViewById(R.id.back);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
            case R.id.back:
                onBackPressed();

        }
    }

}
