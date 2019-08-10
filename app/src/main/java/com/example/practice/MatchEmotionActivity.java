package com.example.practice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MatchEmotionActivity extends AppCompatActivity implements View.OnClickListener {


    Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_emotion);
        backBtn = (Button)findViewById(R.id.back);
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
