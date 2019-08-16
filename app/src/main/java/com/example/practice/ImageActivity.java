package com.example.practice;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ImageActivity extends AppCompatActivity implements  View.OnClickListener {
    FaceDBHelper faceDBHelper;
    SQLiteDatabase sqlDB;
    byte[] photo, drawing;
    String emotion;
    Button removeBtn;
    Long key;
    Button backBtn;
    ImageView photoView, drawingView;
    TextView emotionView, dateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        faceDBHelper = new FaceDBHelper(this);
        sqlDB = faceDBHelper.getWritableDatabase();
        photoView = (ImageView) findViewById(R.id.photo);
        drawingView = (ImageView)findViewById(R.id.drawing);
        emotionView = (TextView)findViewById(R.id.emotion);
        dateView = (TextView)findViewById(R.id.date);
        removeBtn = (Button)findViewById(R.id.remove);
        backBtn = (Button)findViewById(R.id.back);
        Intent intent = getIntent();
        key = intent.getLongExtra("dateKey", 0);
        SimpleDateFormat dayTime = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분");
        String str = dayTime.format(new Date(key));
        dateView.setText(str);
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM faceTBL WHERE date=="+key+";", null);
        cursor.moveToFirst();
        long date = cursor.getLong(0);
        emotion = cursor.getString(1);
        drawing = cursor.getBlob(2);
        photo = cursor.getBlob(3);
        photoView.setImageDrawable(BitmapToDrawable(photo));
        emotionView.setText(emotion);
        drawingView.setImageDrawable(BitmapToDrawable(drawing));
        removeBtn.setOnClickListener(this);
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
    }

    public Drawable BitmapToDrawable(byte[] b){
        Bitmap temp = BitmapFactory.decodeByteArray(b, 0, b.length);
        return new BitmapDrawable(temp);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.remove:
                RetryDialog retryDialog = new RetryDialog(ImageActivity.this, "삭제하시겠습니까?", "확인", "취소");
                retryDialog.setDialogListener(new DialogListenerInterface() {
                    @Override
                    public void onPositiveClicked() {
                        SQLiteStatement p = sqlDB.compileStatement("DELETE FROM faceTBL WHERE date = (?);");
                        p.bindLong(1,key);
                        p.execute();
                        sqlDB.close();
                        onBackPressed();
                    }

                    @Override
                    public void onNegativeClicked() {

                    }
                });
                retryDialog.show();
                break;
        }
    }
}