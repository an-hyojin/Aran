package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class SmallDrawActivity extends AppCompatActivity {

    Button clearBtn, drawBtn, saveBtn;
    ImageButton backBtn;
    EditText textInput;
    LinearLayout drawLinear;
    ImageView showImg;
    ArrayList<Point> points = new ArrayList<Point>();
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_draw);
        textInput = (EditText)findViewById(R.id.emotion);
        drawBtn = (Button)findViewById(R.id.paint);
        saveBtn = (Button)findViewById(R.id.save);
        backBtn = (ImageButton)findViewById(R.id.back);
        showImg = (ImageView)findViewById(R.id.backgroundImage);
        Intent intent = getIntent();
        byte[] arr = getIntent().getByteArrayExtra("image");
        Bitmap img = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        showImg.setImageBitmap(img);
        final MyView m = new MyView(this);

        clearBtn = (Button)findViewById(R.id.clear);
        drawLinear = (LinearLayout)findViewById(R.id.drawLayout);
        clearBtn.setOnClickListener(new View.OnClickListener() { //지우기 버튼 눌렸을때
            @Override
            public void onClick(View v) {
                points.clear();
                m.invalidate();
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
        drawLinear.addView(m);

    }

    class Point {
        float x;
        float y;
        boolean check;
        int color;

        public Point(float x, float y, boolean check, int color) {
            this.x = x;
            this.y = y;
            this.check = check;
            this.color = color;
        }
    }

    class MyView extends View {
        int color = Color.BLACK;
        public MyView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint p = new Paint();
            p.setStrokeWidth(15);
            for (int i = 1; i < points.size(); i++) {
                p.setColor(points.get(i).color);
                if (!points.get(i).check)
                    continue;
                canvas.drawLine(points.get(i - 1).x, points.get(i - 1).y, points.get(i).x, points.get(i).y, p);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    points.add(new Point(x, y, false, color));
                case MotionEvent.ACTION_MOVE:
                    points.add(new Point(x, y, true, color));
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            invalidate();
            return true;
        }
    }
}

