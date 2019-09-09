package com.example.practice.Record;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practice.Dialog.CustomDialog;
import com.example.practice.Dialog.DialogListenerInterface;
import com.example.practice.Draw.DrawImageActivity;
import com.example.practice.MainActivity;
import com.example.practice.R;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RecordActivity extends AppCompatActivity {
    MediaRecorder recorder;
    String filename;
    MediaPlayer player;
    private TextView question;
    // 완료버튼
    Button saveBtn;
    private boolean isRead;
    int position = 0; // 다시 시작 기능을 위한 현재 재생 위치 확인 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        permissionCheck(); // 권한 확인

        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, "recorded.mp4");
        filename = file.getAbsolutePath();
        Log.d("MainActivity", "저장할 파일 명 : " + filename);
        question =  findViewById(R.id.question);
        saveBtn = findViewById(R.id.save);
        showText();


        // 저장하기 누르면 녹음파일이 db에 저장되어야함
        // intent를 했을때 drawImageActivity로 값을 전달하는 동시에 main으로 intent해야하는데 그걸 모르겠음
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outintent = new Intent(getApplication(), MainActivity.class);
                setResult(RESULT_OK,outintent);
                finish();
            }
        });

        // 재생
        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              playAudio();
            }
        });
        //일시정지
        findViewById(R.id.pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseAudio();
            }
        });
        // 재시작
        findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resumeAudio();
            }
        });
        // 정지
        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAudio();
            }
        });
        // 녹음하기
        findViewById(R.id.record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordAudio();
            }
        });
        // 녹음중지
        findViewById(R.id.recordStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecording();
            }
        });
    }

    String EmotionData[] = {
            "고마움", "슬픔", "사랑", "무서움", "미안함", "부끄러움", "화남", "궁금함",
                           "놀람", "서운함", "외로움", "실망"};
    String QThanks[] = {"~ 해서 고마워라고 말해보세요","오늘 하루 부모님에게 고마웠던 점을 말해보렴",
            "니가 나한테 이렇게 해서 참 고마웠어"};
    String QSad[] ={"~해서 슬프다","슬픈영화를 보면 어떤 느낌이 드니?","친한친구가 이사를 가면 어떠니"};
    String QLove[] = {"사랑해", "부모님에게 사랑한다고 말해보세요","어떨때 사랑받는느낌이 드나요 "};
    String QScary[] = {"무섭다","혼자있으면 무서웡","놀이공원 귀신의 집이 무섭다"};
    String QSorry[] = {"미안하다","내가 이렇게 해서 미안해","내가 다 잘못했어"};
    String QShy[] = {"부끄럽다","새로온 친구가 있어서 부끄러워","발표할때 부끄러워"};
    String QAngry[] = {"화난다","니가 이렇게 해서 난 화가났어","이렇게 히면 내가 화날수밖에 없자나"};
    String QCurious[] = {"궁금하다","어떤 상황일때 궁금한지 말해보세요"};
    String QSurprised[] = {"놀람","어떨때 놀라는지 말해보세요","이렇게 해서 놀랬어라고 말해보세요"};
    String QHurt[] = {"서운함","니가 이렇게 해서 서운했어","엄마가 장난감 안사줘서 서운했어"};
    String QLonely[] = {"외로움","집에 혼자있으니까 외롭다"};
    String QDisappoint[] = {"실망","니가 이렇게 해서 내가 실망했어","니가 이렇게 하면 난 실망할거야"};

    private void showText(){
        Random random =  new Random();
        // i를 랜덤으로 받는게 아니라 앞에서 주제 정하면 그걸 intent로 받아와야함.
        int i = random.nextInt((EmotionData.length)-1);
        String text;
        switch (i){
            case 0: // 고마움
                i = random.nextInt(QThanks.length-1);
                question.setText(QThanks[i]);
                break;
            case 1: // 슬픔
                i = random.nextInt(QSad.length-1);
                question.setText(QSad[i]);
                break;
            case 2: // 사랑
                i = random.nextInt(QLove.length-1);
                question.setText(QLove[i]);
                break;
            case 3: // 무서움
                i = random.nextInt(QScary.length-1);
                question.setText(QScary[i]);
                break;
            case 4: // 미안함
                i = random.nextInt(QSorry.length-1);
                question.setText(QSorry[i]);
                break;
            case 5: // 부끄러움
                 i = random.nextInt(QShy.length-1);
                 question.setText(QShy[i]);
                  break;
            case 6: // 화남
                i = random.nextInt(QAngry.length-1);
                question.setText(QAngry[i]);
                break;
            case 7: // 궁금함
                i = random.nextInt(QCurious.length-1);
                question.setText(QCurious[i]);
                break;
            case 8: // 놀람
                i = random.nextInt(QSurprised.length-1);
                question.setText(QSurprised[i]);
                break;
            case 9: // 서운함
                i = random.nextInt(QHurt.length-1);
                question.setText(QHurt[i]);
                break;
            case 10: // 외로움
                i = random.nextInt(QLonely.length-1);
                question.setText(QLonely[i]);
                break;
            case 11: // 실망
                i = random.nextInt(QDisappoint.length-1);
                question.setText(QDisappoint[i]);
                break;

        }
    }
    // 녹음하기
    private void recordAudio() {
        recorder = new MediaRecorder();

        /* 그대로 저장하면 용량이 크다.
         * 프레임 : 한 순간의 음성이 들어오면, 음성을 바이트 단위로 전부 저장하는 것
         * 초당 15프레임 이라면 보통 8K(8000바이트) 정도가 한순간에 저장됨
         * 따라서 용량이 크므로, 압축할 필요가 있음 */
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC); // 어디에서 음성 데이터를 받을 것인지
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); // 압축 형식 설정
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

        recorder.setOutputFile(filename);

        try {
            recorder.prepare();
            recorder.start();

            Toast.makeText(this, "녹음 시작됨.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 녹음 중지
    private void stopRecording() {
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
            Toast.makeText(this, "녹음 중지됨.", Toast.LENGTH_SHORT).show();
        }
    }
    // 재생
    private void playAudio() {
        try {
            closePlayer();

            player = new MediaPlayer();
            player.setDataSource(filename);
            player.prepare();
            player.start();

            Toast.makeText(this, "재생 시작됨.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 일시정지
    private void pauseAudio() {
        if (player != null) {
            position = player.getCurrentPosition();
            player.pause();

            Toast.makeText(this, "일시정지됨.", Toast.LENGTH_SHORT).show();
        }
    }
    // 재시작
    private void resumeAudio() {
        if (player != null && !player.isPlaying()) {
            player.seekTo(position);
            player.start();

            Toast.makeText(this, "재시작됨.", Toast.LENGTH_SHORT).show();
        }
    }
    // 정지
    private void stopAudio() {
        if (player != null && player.isPlaying()) {
            player.stop();

            Toast.makeText(this, "중지됨.", Toast.LENGTH_SHORT).show();
        }
    }

    public void closePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    // 권한체크
    public void permissionCheck(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1);
        }
    }
}

