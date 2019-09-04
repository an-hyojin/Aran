package com.example.practice.Draw;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.practice.Dialog.DayEmotionDialog;
import com.example.practice.Dialog.DayEmotionDialogListener;
import com.example.practice.R;

import java.io.ByteArrayOutputStream;

public class SmallDrawActivity extends AppCompatActivity {

    Button clearBtn, saveBtn, blackBtn, redBtn, orangeBtn,blueBtn,yellowBtn,skyblueBtn,purpleBtn,pinkBtn, whiteBtn,greenBtn, eraserBtn;
    Button backBtn, inputEmotionBtn,pencilBtn;
    TextView textInput,sizeInput;
    LinearLayout drawLinear;
    ImageView showImg;
    SeekBar seekBar;
    PorterDuffXfermode clear = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    DrawView drawView;

    public int number = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_draw);
        textInput = (TextView)findViewById(R.id.emotion);
        saveBtn = (Button)findViewById(R.id.save);
        eraserBtn = findViewById(R.id.eraser);
        backBtn = (Button) findViewById(R.id.back);
        showImg = (ImageView) findViewById(R.id.backgroundImage);
        showImg = (ImageView)findViewById(R.id.backgroundImage);
        sizeInput = findViewById(R.id.size);
        pencilBtn = findViewById(R.id.pencil);
        inputEmotionBtn = findViewById(R.id.)
        // 팔레트
        whiteBtn = findViewById(R.id.colorWhite);
        blackBtn = findViewById(R.id.colorBlack);
        redBtn = findViewById(R.id.colorRed);
        orangeBtn = findViewById(R.id.colorOrange);
        yellowBtn = findViewById(R.id.colorYellow2);
        greenBtn = findViewById(R.id.colorGreen);
        skyblueBtn = findViewById(R.id.colorSkyBlue);
        blueBtn = findViewById(R.id.colorBlue);
        purpleBtn = findViewById(R.id.colorPurple);
        pinkBtn = findViewById(R.id.colorPink);

        seekBar = findViewById(R.id.seekBar);

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

                // 완료하면 녹음창으로 넘어가기
                // intent 수정하기 !
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
                DayEmotionDialog dayEmotionDialog = new DayEmotionDialog(SmallDrawActivity.this, "SmallDraw");
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

        pencilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pencilBtn.getVisibility() == view.GONE) {
                    pencilBtn.setVisibility(view.VISIBLE);
                }else {
                    pencilBtn.setVisibility(view.GONE);
                }
            }
        });

        redBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColorAndEraser(R.color.colorRed, null);

            }
        });
        blackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColorAndEraser(R.color.colorBlack, null);
            }
        });
        whiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColorAndEraser(R.color.colorWhite, null);
            }
        });

        orangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColorAndEraser(R.color.colorOrange, null);
            }
        });
        yellowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColorAndEraser(R.color.colorYellow2, null);
            }
        });
        greenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColorAndEraser(R.color.colorGreen, null);
            }
        });
        skyblueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColorAndEraser(R.color.colorSkyBlue, null);
            }
        });
        blueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawView.setColorAndEraser(R.color.colorBlue, null);
            }
        });
        purpleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColorAndEraser(R.color.colorPurple, null);
            }
        });
        pinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColorAndEraser(R.color.colorPink, null);
            }
        });

        eraserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawView.setColorAndEraser(Color.BLACK, clear);

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                // 메소드 이름대로 사용자가 SeekBar를 움직일때 실행됩니다
                // 주로사용
                if (progress < 1) {
                    progress = 1;
                    seekBar.setProgress(progress);
                }
                number = seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //메소드 이름대로 사용자가 SeekBar를 터치했을때 실행됩니다
                number = seekBar.getProgress();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 메소드 이름대로 사용자가 SeekBar를 손에서 땠을때 실행됩니다
                drawView.setSize((float) seekBar.getProgress());
                number = seekBar.getProgress();
                update();
            }
        });
        //drawView.setSize()
    }

    public void update(){
        sizeInput.setText(new StringBuilder().append(number));
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
        int width,height;
        PorterDuffXfermode use = null;
        public DrawView(Context context, int width, int height) {
            super(context);
            this.width = width;
            this.height = height;
            drawingSpace = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            canvas = new Canvas(drawingSpace);
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(15);
            paint.setStyle(Paint.Style.STROKE);
            drawLine =new Path();
        }
        public void setSize(float size) {
            paint.setStrokeWidth(size);
        }
        public void setColorAndEraser(int color,PorterDuffXfermode type){
            paint.setColor(color);
            paint.setXfermode(type);
        }

        public void clear(){
            drawingSpace = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
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