package com.example.practice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class SingActivity extends YouTubeBaseActivity implements View.OnClickListener {

    YouTubePlayerView youtubeView;
    Button backBtn;
    Button button;
    YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing);

        backBtn = (Button)findViewById(R.id.back);
        backBtn.setOnClickListener(this);

        button = (Button) findViewById(R.id.singButton);
        youtubeView = (YouTubePlayerView) findViewById(R.id.singViewer);

        listener = new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("eDb8dZRaUVM");
            }//유튜브 로드 성공했을 때

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                onBackPressed();
            }//유튜브 로드 실패했을 때
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youtubeView.initialize("AIzaSyDjVs7eH1IZAxpoEjufARWr_sMrM8KbV98", listener);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
        }
    }
}
