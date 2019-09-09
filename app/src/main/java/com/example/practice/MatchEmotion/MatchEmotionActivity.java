package com.example.practice.MatchEmotion;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

import com.example.practice.Dialog.CustomDialog;
import com.example.practice.Dialog.DialogListenerInterface;
import com.example.practice.Dialog.RetryDialog;
import com.example.practice.R;
import com.example.practice.Sticker.StickerDBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MatchEmotionActivity extends AppCompatActivity implements View.OnClickListener, OnInitListener{
    private TextView countLabel;
    private TextView sentence;
    private ImageView questionImage;
    private Button emotion01;
    private Button emotion02;
    private Button emotion03;
    private Button emotion04;
    private Button emotion05;
    private Button emotion06;
    private Button emotion07;
    private Button emotion08;
    private Button retryButton;
    private Button backButton;
    private boolean isRead;
    private TextToSpeech tell;
    private int go = 0;
    private int rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;

    ArrayList<ArrayList<Integer>> quizArray = new ArrayList<>();
    ArrayList<ArrayList<String>> quizArray2 = new ArrayList<>();

    SQLiteDatabase sqlDB;
    StickerDBHelper stickerDB;

    public String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    // 사진 데이터
    int quizData[][] = {
            {R.drawable.angry1, 1},
            {R.drawable.angry2, 1},
            {R.drawable.angry3, 1},
            {R.drawable.angry4, 1},
            {R.drawable.angry5, 1},
            {R.drawable.angry6, 1},
            {R.drawable.angry7, 1},
            {R.drawable.angry8, 1},
            {R.drawable.delight1, 2},
            {R.drawable.delight2, 2},
            {R.drawable.delight3, 2},
            {R.drawable.delight4, 2},
            {R.drawable.delight5, 2},
            {R.drawable.delight6, 2},
            {R.drawable.delight7, 2},
            {R.drawable.delight8, 2},
            {R.drawable.hate1, 3},
            {R.drawable.hate2, 3},
            {R.drawable.hate3, 3},
            {R.drawable.hate4, 3},
            {R.drawable.hate5, 3},
            {R.drawable.hate6, 3},
            {R.drawable.hate7, 3},
            {R.drawable.hate8, 3},
            {R.drawable.lovely1, 4},
            {R.drawable.lovely2, 4},
            {R.drawable.lovely3, 4},
            {R.drawable.lovely4, 4},
            {R.drawable.lovely5, 4},
            {R.drawable.lovely6, 4},
            {R.drawable.lovely7, 4},
            {R.drawable.lovely8, 4},
            {R.drawable.lovely9, 4},
            {R.drawable.lovely10, 4},
            {R.drawable.lovely11, 4},
            {R.drawable.sad1, 5},
            {R.drawable.sad2, 5},
            {R.drawable.sad3, 5},
            {R.drawable.sad4, 5},
            {R.drawable.sad5, 5},
            {R.drawable.sad6, 5},
            {R.drawable.sad7, 5},
            {R.drawable.sad8, 5},
            {R.drawable.scary1, 6},
            {R.drawable.scary2, 6},
            {R.drawable.scary3, 6},
            {R.drawable.scary4, 6},
            {R.drawable.scary5, 6},
            {R.drawable.scary6, 6},
            {R.drawable.scary7, 6},
            {R.drawable.scary8, 6},
            {R.drawable.scary9, 6},
            {R.drawable.scary10, 6},
            {R.drawable.scary11, 6},
            {R.drawable.surprise1, 7},
            {R.drawable.surprise2, 7},
            {R.drawable.surprise3, 7},
            {R.drawable.surprise4, 7},
            {R.drawable.surprise5, 7},
            {R.drawable.surprise6, 7},
            {R.drawable.surprise7, 7},
            {R.drawable.surprise8, 7},
            {R.drawable.satisfied1, 8},
            {R.drawable.satisfied2, 8},
            {R.drawable.satisfied3, 8},
            {R.drawable.satisfied4, 8},
            {R.drawable.satisfied5, 8},
            {R.drawable.satisfied6, 8},
            {R.drawable.satisfied7, 8},
            {R.drawable.satisfied8, 8},
    };

    String quizSentence [][] = {
            {"내가 아끼는 정원에 누군가가 쓰레기를 버리고 있습니다."},
            {"친구가 나의 장난감이 자신의 것이라고 고집을 부립니다."},
            {"동생이 장난을 치며 나를 때립니다."},
            {"졸린데 형제가 계속 잠을 못 자게 합니다."},
            {"게임을 번갈아 가면서 하기로 했는데 형, 누나가 게임기를 주지 않습니다."},
            {"주변에서 “넌 나중에 멋진 사과가 못되겠다!”이라고 놀립니다."},
            {"아빠가 나의 옷이 이상하다며 다른 옷을 강요합니다."},
            {"옆집 강아지가 좋아하는 우리 주인을 물려고 합니다."},
            {"친구와 우리집 마당에서 함께 놀고 있습니다."},
            {"엄마와 함께 재미있는 책을 읽고 있습니다."},
            {"가족과 함께 바다로 여행을 가서 뛰어놀고 있습니다."},
            {"선물 받은 분홍 모자가 너무 마음에 듭니다."},
            {"친구랑 놀기로 한 날, 비가 올 줄 알았는데 날씨가 너무 좋습니다."},
            {"누워있는 카페트가 너무 부드럽습니다."},
            {"아팠던 친구가 다 나아서 함께 놀기로 했습니다."},
            {"친구와 함께 맛있는 음료수를 먹고 있습니다."},
            {"조용히 해달라고 부탁했는데 시끄럽게 합니다."},
            {"친구가 말하는 스타일이 나를 깎아내립니다."},
            {"친구가 내가 좋아하는 것을 무시합니다."},
            {"친구가 아무일도 아닌것에 삐쳐있습니다."},
            {"친구가 내 물건을 훔쳤는데 오히려 화를 냅니다."},
            {"이상한 냄새가 납니다."},
            {"가족과 멀리 떨어져 있어야 합니다."},
            {"이상한 사람이 와서 놀자고 합니다."},
            {"서로를 믿으며 행복한 미래를 생각합니다."},
            {"엄마가 아기를 소중히 생각합니다."},
            {"서로의 이끌림을 느낍니다."},
            {"환한 미소를 지으며 뽀뽀를 합니다."},
            {"서로를 아끼며 감정을 나눕니다."},
            {"엄마와 아이가 행복해하며 뽀뽀를 합니다."},
            {"오랜만에 보는 연인을 안고 있습니다."},
            {"아빠와 아이가 서로를 귀중하게 생각합니다."},
            {"서로 마주보며 둘만의 시간을 보냅니다."},
            {"언니와 동생이 서로 아끼는 만큼 안고 있습니다."},
            {"연인에게 속삭이며 감정을 표현합니다."},
            {"같이 키우던 강아지가 하늘나라에 갔습니다."},
            {"엄마가 놀이터에서 그만 놀고 집에 가자고 합니다."},
            {"회사에서 돌아온 아빠의 표정이 좋지않습니다."},
            {"친구랑 유치원에서 다퉜습니다."},
            {"친한 친구가 다른 지역으로 이사를 갑니다."},
            {"어릴 때부터 살던 곳에서 다른 곳으로 이사를 갑니다."},
            {"영화에서 아이의 집이 타고있어 아이가 갈 곳이 없습니다."},
            {"부모님이 안아주지 않습니다."},
            {"유치원에 가야하는데 늦잠을 잤습니다."},
            {"엄마가 화를 내고있습니다."},
            {"오늘 무서운 영화를 보고 왔습니다."},
            {"꿈속에서 무서운 괴물이 쫓아오고있습니다."},
            {"놀이공원에서 롤러코스터를 탔습니다."},
            {"할로윈 파티에서 친구가 무서운 분장을 하고 왔습니다."},
            {"백화점에서 길을 잃었습니다."},
            {"길을 걸어가고 있는데 건물에서 화분이 떨어졌습니다."},
            {"오늘 병원가는 날입니다."},
            {"집에 혼자 있는데 밖에서 문을 두드리는 소리가 났습니다."},
            {"사진사의 카메라에 귀신이 찍혔습니다."},
            {"옆에 생일선물이 놓여져 있었습니다."},
            {"동영상에 아는사람이 나왔습니다."},
            {"갑자기 집에 친구들이 찾아왔습니다."},
            {"밥을 먹었는데 정말 맛이 있었습니다."},
            {"안경을 샀는데 정말 잘 어울립니다."},
            {"눈 앞에서 믿을 수 없는 일이 일어났습니다."},
            {"부모님이 깜짝 선물을 사들고 오셨습니다."},
            {"채소를 싫어했는데 먹어보니까 맛있습니다."},
            {"다른사람들이 못해낸 일을 내가 해냈습니다."},
            {"새로산 옷이 잘 어울려서 사길 잘했다고 생각했습니다."},
            {"키가 커서 높은 곳에 있는 물건을 주었습니다."},
            {"달리기 대회를 해서 1등을 했습니다."},
            {"마음이 잘 맞는 친구를 사귀게 되었습니다."},
            {"어려운 문제를 스스로 풀었습니다."},
            {"새로산 노트북이 좋다고 친구에게 칭찬 받았습니다."},
            {"부모님에게 생신선물을 사드렸습니다."},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_emotion);
        backButton = (Button)findViewById(R.id.back);
        retryButton = (Button) findViewById(R.id.retry);
        retryButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        isRead = getIntent().getBooleanExtra("onOffSound", false);
        tell = new TextToSpeech(this,this);
        CustomDialog dialog = new CustomDialog(this, "감정 맞추기", "사진 속 감정과 같은 감정을 찾아보세요","시작",R.drawable.match_emotion, isRead);
        dialog.setDialogListener(new DialogListenerInterface() {
            @Override
            public void onPositiveClicked() {
//                onInit(0);
                showNext();
            }

            @Override
            public void onNegativeClicked() {

            }
        });
        dialog.show();
        countLabel = findViewById(R.id.countLabel);
        sentence = findViewById(R.id.sentence);
        questionImage = findViewById(R.id.emotion_image);
        emotion01 = findViewById(R.id.emotion01);
        emotion02 = findViewById(R.id.emotion02);
        emotion03 = findViewById(R.id.emotion03);
        emotion04 = findViewById(R.id.emotion04);
        emotion05 = findViewById(R.id.emotion05);
        emotion06 = findViewById(R.id.emotion06);
        emotion07 = findViewById(R.id.emotion07);
        emotion08 = findViewById(R.id.emotion08);

//        create quizArray from quizData.
        for(int i=0; i<quizData.length; i++){
            //prepare array.
            ArrayList<Integer> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); //image name
            tmpArray.add(quizData[i][1]); //right answer
            ArrayList<String> tmpArray2 = new ArrayList<>();
            tmpArray2.add(quizSentence[i][0]); //string

            //add tmpArray to quizArray
            quizArray.add(tmpArray);
            quizArray2.add(tmpArray2);
        }
//        onInit(1);
    }

    public void showNext(){
        //update quiz countlabel.
        countLabel.setText("Q" + quizCount);

        Random random = new Random();

        int randomNum = random.nextInt(quizArray.size());

        ArrayList<Integer> quiz = quizArray.get(randomNum);
        ArrayList<String> quiz2 = quizArray2.get(randomNum);

        questionImage.setImageResource(quiz.get(0));
        sentence.setText(quiz2.get(0));
        rightAnswer = quiz.get(1);

        if (isRead == true) {
            tell.setLanguage(Locale.KOREAN);
            tell.setPitch(0.6f);
            tell.setSpeechRate(0.95f);
            tell.speak(quiz2.get(0), TextToSpeech.QUEUE_FLUSH, null);
        }
        //remove this quiz from quizArray.
        quizArray.remove(randomNum);
        quizArray2.remove(randomNum);
    }

    public void showResult(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("결과");
        builder.setMessage(rightAnswerCount+" / 10");

        stickerDB = new StickerDBHelper(this);
        sqlDB = stickerDB.getWritableDatabase();

        sqlDB = stickerDB.getWritableDatabase();
        SQLiteStatement p = sqlDB.compileStatement("INSERT INTO stickerTable VALUES (?, ?, ?);");
        p.bindString(2, "감정맞추기");
        p.bindString(3, getToday());
        p.execute();;
        sqlDB.close();
        builder.setNegativeButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();

    }

    public int getIdFromButton(Button imageButton){
        int id = imageButton.getId();
        if(id ==R.id.emotion01){
            return 1;
        }else if(id ==R.id.emotion02){
            return 2;
        }else if(id ==R.id.emotion03){
            return 3;
        }else if(id ==R.id.emotion04){
            return 4;
        }else if(id ==R.id.emotion05){
            return 5;
        }else if(id ==R.id.emotion06){
            return 6;
        }else if(id ==R.id.emotion07){
            return 7;
        }else{
            return 8;
        }

    }

    @Override
    public void onClick(View view) {
        //get pushed button.

        switch(view.getId()){
            case R.id.retry:
                RetryDialog dialog = new RetryDialog(this, "다시하시겠습니까?","네","아니오");
                dialog.setDialogListener(new DialogListenerInterface(){
                    @Override
                    public void onPositiveClicked() {
                        recreate();
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
        Button answerBtn = (Button) findViewById(view.getId());

        int id = getIdFromButton(answerBtn);
        if(id==rightAnswer){

            CustomDialog custom = new CustomDialog(this, "정답입니다!",getIdText(rightAnswer),"확인", getIdResource(rightAnswer), isRead);

            custom.setDialogListener(new DialogListenerInterface() {
                @Override
                public void onPositiveClicked() {
                    if(quizArray.size()<1){
                        //quizArray is empty.
                        showResult();

                    }
                    else if(quizCount==10){
                        showResult();
                    }
                    else{
                        quizCount++;
//                        onInit(1);
                        showNext();
                    }

                }
                @Override
                public void onNegativeClicked() {
                }
            });
            custom.show();
            rightAnswerCount++;
        }else {

            CustomDialog custom = new CustomDialog(this, "다시 생각해보세요!", getIdText(rightAnswer),"확인", getIdResource(rightAnswer), isRead);
            custom.setDialogListener(new DialogListenerInterface() {
                @Override
                public void onPositiveClicked() {
                    if (quizArray.size() < 1) {
                        //quizArray is empty.
                        showResult();

                    } else if (quizCount == 10) {
                        showResult();
                    } else {
                        quizCount++;
                        showNext();
//                        onInit(1);
                    }

                }

                @Override
                public void onNegativeClicked() {

                }
            });
            custom.show();
        }
    }

    public String getIdText(int id){
        switch (id){
            case 1:
                return "화남";
            case 2:
                return "기쁨";
            case 3:
                return "싫어함";
            case 4:
                return "사랑";
            case 5:
                return "슬픔";
            case 6:
                return "무서움";
            case 7:
                return "놀라움";
            case 8:
                return "뿌듯함";
            default:
                return "";
        }
    }

    public int getIdResource(int id){
        switch (id){
            case 1:
                return R.drawable.angry;
            case 2:
                return R.drawable.smile;
            case 3:
                return R.drawable.disgust;
            case 4:
                return R.drawable.heart;
            case 5:
                return R.drawable.sad;
            case 6:
                return R.drawable.scary;
            case 7:
                return R.drawable.surprised;
            case 8:
                return R.drawable.full;
            default:
                return R.drawable.angry;
        }
    }

    @Override
    public void onInit(int j) {
//            //update quiz countlabel.
//            countLabel.setText("Q" + quizCount);
//
//            Random random = new Random();
//
//            int randomNum = random.nextInt(quizArray.size());
//
//            ArrayList<Integer> quiz = quizArray.get(randomNum);
//            ArrayList<String> quiz2 = quizArray2.get(randomNum);
//
//            questionImage.setImageResource(quiz.get(0));
//            sentence.setText(quiz2.get(0));
//            rightAnswer = quiz.get(1);
//
//            if (isRead == true) {
//                tell.setLanguage(Locale.KOREAN);
//                tell.setPitch(0.6f);
//                tell.setSpeechRate(0.95f);
//                tell.speak(quiz2.get(0), TextToSpeech.QUEUE_FLUSH, null);
//            }
//            //remove this quiz from quizArray.
//            quizArray.remove(randomNum);
//            quizArray2.remove(randomNum);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        tell.shutdown();
    }
}