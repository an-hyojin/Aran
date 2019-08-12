package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class SmallDrawActivity extends AppCompatActivity {

    Button clearBtn, drawBtn, saveBtn, blackBtn, redBtn, blueBtn, eraserBtn;
    Button backBtn;
    EditText textInput;
    LinearLayout drawLinear;
    ImageView showImg;
    PorterDuffXfermode clear = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

    DrawView drawView;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_draw);

        textInput = (EditText)findViewById(R.id.emotion);
        drawBtn = (Button)findViewById(R.id.paint);
        saveBtn = (Button)findViewById(R.id.save);
        blackBtn = findViewById(R.id.black);
        redBtn = findViewById(R.id.red);
        blueBtn = findViewById(R.id.blue);
        eraserBtn = findViewById(R.id.eraser);
        backBtn = (Button)findViewById(R.id.back);
        showImg = (ImageView)findViewById(R.id.backgroundImage);

        byte[] arr = getIntent().getByteArrayExtra("image");
        Bitmap img = BitmapFactory.decodeByteArray(arr, 0, arr.length);
       // showImg.setImageBitmap(img);

        drawLinear = (LinearLayout)findViewById(R.id.drawLayout);
        drawView = new DrawView(this);
        drawLinear.addView(drawView);
        clearBtn = (Button)findViewById(R.id.clear);
        clearBtn.setOnClickListener(new View.OnClickListener() { //지우기 버튼 눌렸을때
            @Override
            public void onClick(View v) {
                drawView.clear();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawLinear.setDrawingCacheEnabled(true);    // 캐쉬허용
                Bitmap screenshot = Bitmap.createBitmap(drawLinear.getDrawingCache());
                drawLinear.setDrawingCacheEnabled(false);   // 캐쉬닫기

                Intent outIntent = new Intent(getApplicationContext(), DrawActivity.class);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                screenshot.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Bitmap bitmap;
                bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                showImg.setImageBitmap(bitmap);

                outIntent.putExtra("drawing",byteArray);
                outIntent.putExtra("emotion", textInput.getText().toString());
                setResult(RESULT_OK, outIntent);
                finish();
            }

        });
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
        redBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              drawView.setColorAndEraser(Color.RED,null);

            }
        });
        blackBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                drawView.setColorAndEraser(Color.BLACK,null);
            }
        });
        blueBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                drawView.setColorAndEraser(Color.BLUE,null);
            }
        });
        eraserBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                drawView.setColorAndEraser(Color.BLACK,clear);

            }
        });

    }



    class DrawView extends View {
        PorterDuffXfermode clear = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        Path drawLine;
        Canvas canvas = new Canvas();
        Paint paint;
        int x,y;
        float pointX = -1, pointY = -1;
        int color = Color.BLACK;
        PorterDuffXfermode use = null;
        public DrawView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(15);
            paint.setStyle(Paint.Style.STROKE);

        }

        public void setColorAndEraser(int color,PorterDuffXfermode type){
            paint.setColor(color);
            paint.setXfermode(type);
        }

        public void clear(){
            drawLine.reset();
            invalidate();
        }

        @Override
        protected  void onDraw(Canvas canvas){
            canvas.drawLine(pointX, pointY, x, y, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event){
            x =(int) event.getX(0);
            y = (int) event.getY(0);
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    pointX = x;
                    pointY = y;
                    break;
                case MotionEvent.ACTION_MOVE:
                   if(pointX != -1) {
                       invalidate();
                       pointX = x;
                       pointY = y;
                   }
                    break;
                case MotionEvent.ACTION_UP:
                    if(pointX != -1){
                        invalidate();
                    }
                    pointX = -1;
                    pointY = -1;
                    break;

            }

            System.out.println("그림");
            invalidate();
            return true;
        }
    }
}

