package com.example.practice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class SingAndAnimationSelectActivity extends YouTubeBaseActivity implements View.OnClickListener {

    ImageButton backBtn;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singAndAnimation);

        backBtn = (ImageButton)findViewById(R.id.back);
        backBtn.setOnClickListener(this);

        button1 = (Button) findViewById(R.id.singButtonA);
        button2 = (Button) findViewById(R.id.animationButtonA);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.back:
                onBackPressed();
        }
    }
}
