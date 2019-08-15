package com.example.practice;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SingActivity extends YouTubeBaseActivity implements View.OnClickListener {

    YouTubePlayerView youtubeView;
    ImageButton backBtn;
    Button button;
    YouTubePlayer.OnInitializedListener listener;
    SQLiteDatabase sqlDB;
    StickerDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing);

        backBtn = (ImageButton)findViewById(R.id.back);
        backBtn.setOnClickListener(this);

        button = (Button) findViewById(R.id.singButton);
        youtubeView = (YouTubePlayerView) findViewById(R.id.singViewer);

        helper = new StickerDBHelper(this);

        listener = new YouTubePlayer.OnInitializedListener() {
            String id = getIntent().getExtras().getString("singId");
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                sqlDB = helper.getWritableDatabase();
                SQLiteStatement p = sqlDB.compileStatement("INSERT INTO stickerTable VALUES(?, ?, ?);");
                p.bindString(2, "노래");
                p.bindString(3, getToday());
                p.execute();
                sqlDB.close();
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

    public String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}
