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
