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
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class SmallDrawActivity extends AppCompatActivity {

    Button clearBtn, drawBtn, saveBtn, blackBtn, redBtn, blueBtn, eraserBtn;
    Button backBtn, inputEmotionBtn;
    TextView textInput;

    LinearLayout drawLinear;
    ImageView showImg;

    PorterDuffXfermode clear = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

    DrawView drawView;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_draw);
        inputEmotionBtn = (Button)findViewById(R.id.inputEmotion);
        textInput = (TextView)findViewById(R.id.emotion);
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
        showImg.setImageBitmap(img);

        drawLinear = (LinearLayout)findViewById(R.id.drawLayout);
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
        inputEmotionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayEmotionDialog dayEmotionDialog = new DayEmotionDialog(SmallDrawActivity.this);
                dayEmotionDialog.setDayEmotionDialogListener(new DayEmotionDialogListener() {
                    @Override
                    public void onPositiveButtonClicked(String emotion) {
                        textInput.setText(emotion);
                    }

                    @Override
                    public void onNegativeButtonClicked() {

                    }
                });
                dayEmotionDialog.show();
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
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        if(hasFocus &&drawView==null){
            if(drawLinear != null){
                drawView = new DrawView(this, drawLinear.getMeasuredWidth(), drawLinear.getMeasuredHeight());
                drawLinear.addView(drawView);
            }
        }
        super.onWindowFocusChanged(hasFocus);
    }



    class DrawView extends View {
        PorterDuffXfermode clear = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        Path drawLine;
        Canvas canvas;
        Paint paint;
        Bitmap drawingSpace;

        int x,y;
        int pointX = -1;
        int pointY = -1;
        int color = Color.BLACK;
        PorterDuffXfermode use = null;
        public DrawView(Context context, int width, int height) {
            super(context);

            drawingSpace = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            canvas = new Canvas(drawingSpace);
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(15);
            paint.setStyle(Paint.Style.STROKE);
            drawLine =new Path();
        }

        public void setColorAndEraser(int color,PorterDuffXfermode type){
            paint.setColor(color);
            paint.setXfermode(type);
        }

        public void clear(){
            canvas = new Canvas(drawingSpace);
            invalidate();
        }

        @Override
        protected  void onDraw(Canvas canvas){
            if(drawingSpace!= null){
                canvas.drawBitmap(drawingSpace,0 ,0,null);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event){
            x =(int) event.getX(0);
            y = (int) event.getY(0);
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    drawLine.reset();
                    drawLine.moveTo(x,y);
                    pointX = x;
                    pointY = y;
                    break;
                case MotionEvent.ACTION_MOVE:
                   if(pointX != -1) {
                       pointX = x;
                       pointY = y;
                       drawLine.lineTo(x,y);
                       canvas.drawPath(drawLine, paint);
                   }
                   invalidate();
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

