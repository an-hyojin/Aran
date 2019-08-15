
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
    private Button retryButton;
    private Button backButton;

    private int rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;

    ArrayList<ArrayList<Integer>> quizArray = new ArrayList<>();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_emotion);
        backButton = (Button)findViewById(R.id.back);


        retryButton = (Button) findViewById(R.id.retry);

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

            //add tmpArray to quizArray
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz(){
        //update quiz countlabel.
        countLabel.setText("Q"+quizCount);

        Random random = new Random();

        int randomNum = random.nextInt(quizArray.size());

        ArrayList<Integer> quiz = quizArray.get(randomNum);

        questionImage.setImageResource(quiz.get(0));
        rightAnswer = quiz.get(1);

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

    public int getIdFromButton(ImageButton imageButton){
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
        ImageButton answerBtn = (ImageButton) findViewById(view.getId());

        int id = getIdFromButton(answerBtn);
        if(id==rightAnswer){

            CustomDialog custom = new CustomDialog(this, "정답입니다!",getIdText(rightAnswer),"확인", getIdResource(rightAnswer),false);

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
                        showNextQuiz();
                    }

                }
                @Override
                public void onNegativeClicked() {
                }
            });
            custom.show();
            rightAnswerCount++;
        }else {

            CustomDialog custom = new CustomDialog(this, "다시 생각해보세요!", getIdText(rightAnswer),"확인", getIdResource(rightAnswer),false);

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
                        showNextQuiz();
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
                return "놀람";
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
}