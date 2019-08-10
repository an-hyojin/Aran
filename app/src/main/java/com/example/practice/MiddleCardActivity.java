package com.example.practice;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class MiddleCardActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int TOTAL_CARD_NUM = 12;


    private int[] cardId = {R.id.card01, R.id.card02,R.id.card03,R.id.card04,R.id.card05,R.id.card06,R.id.card07,R.id.card08,R.id.card09,R.id.card10,R.id.card11,R.id.card12};
    private Card[] cardArray = new Card[TOTAL_CARD_NUM];

    private Card first, second;
    private Boolean isClicked = false;
    private int SUCCESS_COUNT = 0;
    private long clickTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_easy_card);

        setCards();

        CustomDialog dialog = new CustomDialog(this, "감정 카드놀이", "같은 표정을 가진 카드를 짝지어보세요","시작",R.drawable.cards);
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
        Button retryButton =  findViewById(R.id.retry);
        Button backButton = findViewById(R.id.back);
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
                        CustomDialog finalDialog = new CustomDialog(this,"게임종료","게임이 끝났습니다.","확인", R.drawable.cards);
                        finalDialog.show();
                    }
                    CustomDialog dialog = valueToDialog(first.value, this);
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
    public CustomDialog valueToDialog(int value, Context context){
        CustomDialog customDialog;
        switch (value){
            case 0:
                customDialog = new CustomDialog(context,"기쁨","기쁘거나 좋아서 마음이 벅참", "확인",R.drawable.smile);
                break;
            case 1:
                customDialog = new CustomDialog(context,"슬픔","가슴 아프거나 불쌍한 생각이 들거나 하여 마음이 아프고 괴로움.","확인", R.drawable.sad);
                break;
            case 2:
                customDialog = new CustomDialog(context,"화남","몹시 못마땅하거나 언짢아서 성을 냄.","확인", R.drawable.angry);
                break;
            case 3:
                customDialog = new CustomDialog(context,"싫어함(증오)","마음에 들지 않거나 나쁘게 생각하여 가까이하거나 가지거나 받아들이고 싶지 않음.","확인", R.drawable.disgust);
                break;
            case 4:
                customDialog = new CustomDialog(context,"뿌듯함","욕구가 충족되었을 때의 흐뭇하고 흡족한 마음이나 느낌.", "확인",R.drawable.full);
                break;
            case 5:
                customDialog = new CustomDialog(context,"놀람","기대하지 않던 일을 겪게 될 때 느끼는 감정.", "확인",R.drawable.surprised);
                break;
            case 6:
                customDialog = new CustomDialog(context,"사랑","남을 돕고 이해하고 가까이하려는 마음.","확인", R.drawable.heart);
                break;
            case 7:
                customDialog = new CustomDialog(context, "무서움","어떤것에 대하여 두려운 느낌이 있고 무슨일이 일어날까봐 겁남.", "확인",R.drawable.scary);
                break;
            default:
                customDialog = null;
        }
        return customDialog;
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
    public void startGame(){
        int[] random = new int[TOTAL_CARD_NUM];
        int x;

        for(int i = 0; i<TOTAL_CARD_NUM; i++){
            if(!cardArray[i].isBack()){
                cardArray[i].back();
            }
        }

        boolean dob;

        for(int i = 0; i<TOTAL_CARD_NUM; i++){
            while(true) {
                dob = false;
                x = (int) (Math.random() * TOTAL_CARD_NUM);
                for (int j=0; j<i; j++) {
                    if (random[j] == x) {
                        dob = true;
                        break;
                    }
                }
                if (!dob) break;
            }
            random[i] = x;
        }
        for (int i=0; i<TOTAL_CARD_NUM; i++) {
            cardArray[i].card = findViewById(cardId[random[i]]);
            cardArray[i].front();
        }
        Timer t = new Timer(1,null);

        t.start();
        SUCCESS_COUNT = 0;
        isClicked = false;


    }


    private class Timer extends Thread {
        int type;
        View v;
        Timer (int type, View v) {
            super();
            this.v = v;
            this.type = type;
        }

        @Override
        public void run() {

            try {
                if (type == 0) {
                    v.setClickable(false);
                    Thread.sleep(500);
                    mHandler.sendEmptyMessage(0);
                    v.setClickable(true);
                }
                else if (type == 1) {
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
            }
            else if (msg.what == 1) {
                //flag = true;
                for (int i=0; i<TOTAL_CARD_NUM; i++) {
                    cardArray[i].back();
                }
            }
        }
    };
}

