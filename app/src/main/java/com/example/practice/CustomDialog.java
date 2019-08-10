package com.example.practice;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CustomDialog extends Dialog implements View.OnClickListener {

    ImageButton positiveBtn;
    TextView title;
    TextView text;
    ImageView imageView;
    DialogListenerInterface customDialogLister;
    int value;
    private final static int[] faceImageID = {R.drawable.smile, R.drawable.sad, R.drawable.angry,R.drawable.disgust, R.drawable.full, R.drawable.surprised,R.drawable.heart, R.drawable.scary};


    public CustomDialog(Context context, String titleString, String textString, int topImageResource, int bottomImageResource){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.customdialog);

        title = (TextView)findViewById(R.id.title);
        text = (TextView)findViewById(R.id.text);
        imageView = (ImageView)findViewById(R.id.image);

        positiveBtn = (ImageButton)findViewById(R.id.positiveBtn);
        positiveBtn.setOnClickListener(this);
        title.setText(titleString);
        text.setText(textString);
        imageView.setBackgroundResource(topImageResource);
        positiveBtn.setBackgroundResource(bottomImageResource);
//
//        switch (caseNum){
//            case 0:
//                title.setText("기쁨");
//                text.setText("기쁘거나 좋아서 마음이 벅참.");
//                break;
//            case 1:
//                title.setText("슬픔");
//                text.setText("가슴 아프거나 불쌍한 생각이 들거나 하여 마음이 아프고 괴로움.");
//                break;
//            case 2:
//                title.setText("화남");
//                text.setText("몹시 못마땅하거나 언짢아서 성을 냄.");
//                break;
//            case 3:
//                title.setText("싫어함(증오)");
//                text.setText("마음에 들지 않거나 나쁘게 생각하여 가까이하거나 가지거나 받아들이고 싶지 않음.");
//                break;
//            case 4:
//                title.setText("뿌듯함");
//                text.setText("욕구가 충족되었을 때의 흐뭇하고 흡족한 마음이나 느낌.");
//                break;
//            case 5:
//                title.setText("놀람");
//                text.setText("기대하지 않던 일을 겪게 될 때 느끼는 감정.");
//                break;
//            case 6:
//                title.setText("사랑");
//                text.setText("남을 돕고 이해하고 가까이하려는 마음.");
//                break;
//            case 7:
//                title.setText("무서움");
//                text.setText("어떤것에 대하여 두려운 느낌이 있고 무슨일이 일어날까봐 겁남.");
//                break;
//            case 11:
//                title.setText("감정카드놀이");
//                imageView.setBackgroundResource(R.drawable.cards);
//                text.setText("같은 표정을 가진 카드를 짝지어보세요");
//                positiveBtn.setBackgroundResource(R.drawable.start);
//                break;
//            case 12:
//                title.setText("감정 따라그리기");
//                imageView.setBackgroundResource(R.drawable.drawing);
//                text.setText("갤러리에서 사진을 선택한 후 그때 느낀 감정을 따라 그려보세요");
//                positiveBtn.setBackgroundResource(R.drawable.start);
//                break;
//            case 13:
//                title.setText("이미 감정을 입력했습니다.");
//                text.setText("감정은 하루에 한번만 입력할 수 있습니다.");
//                break;
//            default:
//                title.setText("게임종료");
//                text.setText("카드게임이 끝났습니다!");
//
        //}
    }

    public void setDialogListener(DialogListenerInterface customDialogListener){
        this.customDialogLister = customDialogListener;
    }

    public void onClick(View v){
        if(v == positiveBtn){
            if(customDialogLister!=null) {
                customDialogLister.onPositiveClicked();
            }
            dismiss();
        }
    }

}
