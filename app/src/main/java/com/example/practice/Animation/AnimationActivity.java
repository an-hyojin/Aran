package com.example.practice.Animation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.practice.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class AnimationActivity extends YouTubeBaseActivity implements View.OnClickListener {

    Button backBtn;

    YouTubePlayerView youtubeView;
    Button button;
    YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        backBtn = (Button)findViewById(R.id.back);
        backBtn.setOnClickListener(this);
        TextView info = findViewById(R.id.info);
        String name = getIntent().getStringExtra("name");
        info.setText(name);
        button = (Button) findViewById(R.id.youtubeButton);
        youtubeView = (YouTubePlayerView) findViewById(R.id.youtubeViewer);
        listener = new YouTubePlayer.OnInitializedListener() {
            String id = getIntent().getExtras().getString("Id","");
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(id);
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
