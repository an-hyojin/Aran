package com.example.practice;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class EasyCardActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int TOTAL_CARD_NUM = 8;


    private int[] cardId = {R.id.card01, R.id.card02, R.id.card03, R.id.card04, R.id.card05, R.id.card06, R.id.card07, R.id.card08};
    private Card[] cardArray = new Card[TOTAL_CARD_NUM];
    private Card first, second;
    private boolean isClicked = false;
    private int SUCCESS_COUNT = 0;
    private long clickTime =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_easy_card);

        setCards();

        CustomDialog dialog = new CustomDialog(this, 11);
        dialog.setDialogListener(new DialogListenerInterface() {
            @Override
            public void onPositiveClicked() {
                startGame();
            }

            @Override
            public void onNegativeClicked() {

            }
        });
        dialog.show();
        ImageButton retryButton =  findViewById(R.id.retry);
        ImageButton backButton = findViewById(R.id.back);
        retryButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

    }

    public void setCards(){
        for(int i=0; i<TOTAL_CARD_NUM; i++) {
            cardArray[i] = new Card(i/2);
            findViewById(cardId[i]).setOnClickListener(this);
            cardArray[i].card = findViewById(cardId[i]);
            cardArray[i].back();
        }
    }
    public void cardGame(View v){
        if(!isClicked) {
            long thisTime = SystemClock.elapsedRealtime();
            if (thisTime-clickTime>500) {
                for (int i=0; i<TOTAL_CARD_NUM; i++) {
                    if (cardArray[i].card == v) {
                        first = cardArray[i];
                        break;
                    }
                }
                if (first.isBack()) {
                    first.front();
                    isClicked = true;
                }
            }
        }else{
            clickTime = SystemClock.elapsedRealtime();
            for (int i=0; i<TOTAL_CARD_NUM; i++) {
                if (cardArray[i].card == v) {
                    second = cardArray[i];
                    break;
                }
            }
            if (second.isBack()) {
                second.front();
                if (first.value == second.value) {
                    SUCCESS_COUNT++;
                    if (SUCCESS_COUNT == TOTAL_CARD_NUM/2) {
                        CustomDialog finalDialog = new CustomDialog(this,10);
                        finalDialog.show();
                    }
                    CustomDialog dialog = new CustomDialog(this, first.value);
                    dialog.show();
                }
                else{
                    Timer t = new Timer(0,v);
                    t.start();
                }
                isClicked = false;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retry:
                RetryDialog dialog = new RetryDialog(this, 1);
                dialog.setDialogListener(new DialogListenerInterface() {
                    @Override
                    public void onPositiveClicked() {
                        startGame();
                    }

                    @Override
                    public void onNegativeClicked() {

                    }

                });
                dialog.show();
                return;
            case R.id.back:
                onBackPressed();
                return;
        }
      cardGame(v);
    }

    public void startGame() {
        int[] random = new int[TOTAL_CARD_NUM];

        int x;

        for (int i = 0; i < TOTAL_CARD_NUM; i++) {
            if (!cardArray[i].isBack()) {
                cardArray[i].back();
            }
        }

        boolean dob;

        for (int i = 0; i < TOTAL_CARD_NUM; i++) {
            while (true) {
                dob = false;
                x = (int) (Math.random() * TOTAL_CARD_NUM);
                for (int j = 0; j < i; j++) {
                    if (random[j] == x) {
                        dob = true;
                        break;
                    }
                }
                if (!dob) break;
            }
            random[i] = x;
        }
        for (int i = 0; i < TOTAL_CARD_NUM; i++) {
            cardArray[i].card = findViewById(cardId[random[i]]);
            cardArray[i].front();
        }
        Timer t = new Timer(1, null);

        t.start();
        SUCCESS_COUNT = 0;
        isClicked = false;


    }


    private class Timer extends Thread {
        int kind;
        View v;

        Timer(int kind, View v) {
            super();
            this.v = v;
            this.kind = kind;
        }

        @Override
        public void run() {

            try {
                if (kind == 0) {
                    Thread.sleep(500);
                    mHandler.sendEmptyMessage(0);
                } else if (kind == 1) {
                    Thread.sleep(3000);
                    mHandler.sendEmptyMessage(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                first.back();
                second.back();
                first.setIsBack(true);
                second.setIsBack(true);
            } else if (msg.what == 1) {
                //flag = true;
                for (int i = 0; i < TOTAL_CARD_NUM; i++) {
                    cardArray[i].back();
                }
            }
        }
    };
}



