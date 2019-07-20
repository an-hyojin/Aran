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


    public CustomDialog(Context context, int caseNum){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.customdialog);

        title = (TextView)findViewById(R.id.title);
        text = (TextView)findViewById(R.id.text);
        imageView = (ImageView)findViewById(R.id.image);

        positiveBtn = (ImageButton)findViewById(R.id.positiveBtn);
        positiveBtn.setOnClickListener(this);
        if(caseNum<8) {
            imageView.setBackgroundResource(faceImageID[caseNum]);
        }
        switch (caseNum){
            case 0:
                title.setText("웃음");
                text.setText("기쁠때 어쩌구");
                break;
            case 1:
                title.setText("슬픔");
                text.setText("기쁠때 어쩌구");
                break;
            case 2:
                title.setText("화남");
                text.setText("기쁠때 어쩌구");
                break;
            case 3:
                title.setText("싫어함(증오)");
                text.setText("기쁠때 어쩌구");
                break;
            case 4:
                title.setText("뿌듯함");
                text.setText("기쁠때 어쩌구");
                break;
            case 5:
                title.setText("놀람");
                text.setText("기쁠때 어쩌구");
                break;
            case 6:
                title.setText("사랑");
                text.setText("기쁠때 어쩌구");
                break;
            case 7:
                title.setText("두려움");
                text.setText("기쁠때 어쩌구");
                break;
            case 11:
                title.setText("감정카드놀이");
                imageView.setBackgroundResource(R.drawable.cards);
                text.setText("같은 표정을 가진 카드를 짝지어보세요");
                positiveBtn.setBackgroundResource(R.drawable.start);
                break;
            default:
                title.setText("게임종료");
                text.setText("기쁠때 어쩌구");
        }
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
