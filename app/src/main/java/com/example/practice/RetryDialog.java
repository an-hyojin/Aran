package com.example.practice;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RetryDialog extends Dialog implements  View.OnClickListener {
    Button positiveBtn;
    Button negativeBtn;
    TextView textView;
    TextView title;
    DialogListenerInterface customDialogLister;
    public RetryDialog(Context context, int caseNum){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.retrydialog);
        positiveBtn = (Button)findViewById(R.id.positiveBtn);
        negativeBtn = (Button)findViewById(R.id.negativeBtn);
        textView = (TextView)findViewById(R.id.text);
        title = (TextView)findViewById(R.id.title);
        positiveBtn.setOnClickListener(this);
        negativeBtn.setOnClickListener(this);

        title.setText("다시 시작하시겠습니까?");

    }


    public void setDialogListener(DialogListenerInterface customDialogListener){
        this.customDialogLister = customDialogListener;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.positiveBtn:
                customDialogLister.onPositiveClicked();
                dismiss();
                break;
            case R.id.negativeBtn:
                dismiss();
                break;
        }
    }
}
