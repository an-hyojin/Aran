package com.example.practice;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MatchEmotionActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView countLabel;
    private ImageView questionImage;
    private ImageButton emotion01;
    private ImageButton emotion02;
    private ImageButton emotion03;
    private ImageButton emotion04;
    private ImageButton emotion05;
    private ImageButton emotion06;
    private ImageButton emotion07;
    private ImageButton emotion08;
    private ImageButton retryButton;
    private ImageButton backButton;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;

    ArrayList<ArrayList<Integer>> quizArray = new ArrayList<>();

    int quizData[][] = {
            {R.drawable.angry1, R.drawable.angrycard, R.drawable.smilecard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.angry2, R.drawable.angrycard, R.drawable.smilecard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.angry3, R.drawable.angrycard, R.drawable.smilecard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.angry4, R.drawable.angrycard, R.drawable.smilecard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.angry5, R.drawable.angrycard, R.drawable.smilecard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.angry6, R.drawable.angrycard, R.drawable.smilecard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.angry7, R.drawable.angrycard, R.drawable.smilecard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.angry8, R.drawable.angrycard, R.drawable.smilecard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.delight1, R.drawable.smilecard, R.drawable.angrycard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.delight2, R.drawable.smilecard, R.drawable.angrycard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.delight3, R.drawable.smilecard, R.drawable.angrycard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.delight4, R.drawable.smilecard, R.drawable.angrycard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.delight5, R.drawable.smilecard, R.drawable.angrycard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.delight6, R.drawable.smilecard, R.drawable.angrycard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.delight7, R.drawable.smilecard, R.drawable.angrycard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.delight8, R.drawable.smilecard, R.drawable.angrycard, R.drawable.disgustcard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.hate1, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.hate2, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.hate3, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.hate4, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.hate5, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.hate6, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.hate7, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.hate8, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.heartcard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.lovely1, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.lovely2, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.lovely3, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.lovely4, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.lovely5, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.lovely6, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.lovely7, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.lovely8, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.lovely9, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.lovely10, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.lovely11, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.sadcard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.sad1, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.sad2, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.sad3, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.sad4, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.sad5, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.sad6, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.sad7, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.sad8, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.scarycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.scary1, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.scary2, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.scary3, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.scary4, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.scary5, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.scary6, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.scary7, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.scary8, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.scary9, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.scary10, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.scary11, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.surprisedcard, R.drawable.fullcard},
            {R.drawable.surprise1, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.fullcard},
            {R.drawable.surprise2, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.fullcard},
            {R.drawable.surprise3, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.fullcard},
            {R.drawable.surprise4, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.fullcard},
            {R.drawable.surprise5, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.fullcard},
            {R.drawable.surprise6, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.fullcard},
            {R.drawable.surprise7, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.fullcard},
            {R.drawable.surprise8, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard, R.drawable.fullcard},
            {R.drawable.satisfied1, R.drawable.fullcard, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard},
            {R.drawable.satisfied2, R.drawable.fullcard, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard},
            {R.drawable.satisfied3, R.drawable.fullcard, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard},
            {R.drawable.satisfied4, R.drawable.fullcard, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard},
            {R.drawable.satisfied5, R.drawable.fullcard, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard},
            {R.drawable.satisfied6, R.drawable.fullcard, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard},
            {R.drawable.satisfied7, R.drawable.fullcard, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard},
            {R.drawable.satisfied8, R.drawable.fullcard, R.drawable.surprisedcard, R.drawable.scarycard, R.drawable.sadcard, R.drawable.heartcard, R.drawable.disgustcard, R.drawable.smilecard, R.drawable.angrycard},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_emotion);

        retryButton = (ImageButton) findViewById(R.id.retry);
        backButton = (ImageButton) findViewById(R.id.back);
        retryButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        countLabel = findViewById(R.id.countLabel);
        questionImage = findViewById(R.id.emotion_image);
        emotion01 = findViewById(R.id.emotion01);
        emotion02 = findViewById(R.id.emotion02);
        emotion03 = findViewById(R.id.emotion03);
        emotion04 = findViewById(R.id.emotion04);
        emotion05 = findViewById(R.id.emotion05);
        emotion06 = findViewById(R.id.emotion06);
        emotion07 = findViewById(R.id.emotion07);
        emotion08 = findViewById(R.id.emotion08);

        //create quizArray from quizData.
        for(int i=0; i<quizData.length; i++){
            //prepare array.
            ArrayList<Integer> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); //image name
            tmpArray.add(quizData[i][1]); //right answer
            tmpArray.add(quizData[i][2]); //choice
            tmpArray.add(quizData[i][3]);
            tmpArray.add(quizData[i][4]);
            tmpArray.add(quizData[i][5]);
            tmpArray.add(quizData[i][6]);
            tmpArray.add(quizData[i][7]);
            tmpArray.add(quizData[i][8]);

            //add tmpArray to quizArray
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz(){

        //update quiz countlabel.
        countLabel.setText("Q"+quizCount);

        //generate random number between 0 and 8 (quizArray's size -1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //pick one quiz set.
        ArrayList<Integer> quiz = quizArray.get(randomNum);

        //set Image and right answer.
        //array format: {"image name", "right answer", "choice" ...}

        //수정
        questionImage.setImageResource(quiz.get(0));

        rightAnswer = quiz.get(1).toString();

//        rightAnswer.setBackgroundResource(quiz.get(1));
       // rightAnswer = quiz.get(1).toString();


//        questionImage.setImageResource(
//                getResources().getIdentifier(quiz.get(0), "drawble", getPackageName()));
//        rightAnswer = quiz.get(1);


        //remove "image name" from quiz and shuffle choices.
        quiz.remove(0);
        Collections.shuffle(quiz);

        //set choices.
        emotion01.setBackgroundResource(quiz.get(0));
        emotion02.setBackgroundResource(quiz.get(1));
        emotion03.setBackgroundResource(quiz.get(2));
        emotion04.setBackgroundResource(quiz.get(3));
        emotion05.setBackgroundResource(quiz.get(4));
        emotion06.setBackgroundResource(quiz.get(5));
        emotion07.setBackgroundResource(quiz.get(6));
        emotion08.setBackgroundResource(quiz.get(7));

        //remove this quiz from quizArray.
        quizArray.remove(randomNum);

    }

    public void showResult(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("결과");
        builder.setMessage(rightAnswerCount+" / 10");
        builder.setNegativeButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();

    }

    @Override
    public void onClick(View view) {
        //get pushed button.
        //수정
        ImageButton answerBtn = (ImageButton) findViewById(view.getId());
        String btnText = answerBtn.toString();

//        int btn = Integer.parseInt(answerBtn.toString());

        String alertTitle;

        switch(view.getId()){
            case R.id.retry:
                RetryDialog dialog = new RetryDialog(this, 1);
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
//수정
        if(btnText.equals(rightAnswer)){
            //correct!
            alertTitle = "맞았습니다!";
            rightAnswerCount++;
        }else{
            //wrong
            alertTitle = "다시 생각해보세요.";
        }

        //create dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("정답: "+ rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(quizArray.size()<1){
                    //quizArray is empty.
                    showResult();

                }
                else if(quizCount==10){
                    showResult();
                }
                else{
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}