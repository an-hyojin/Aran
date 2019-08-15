package com.example.practice;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RetryDialog extends Dialog implements  View.OnClickListener {
    Button positiveBtn;
    Button negativeBtn;
    TextView textView;
    TextView title;
    LinearLayout container;

    DialogListenerInterface customDialogLister;
    public RetryDialog(Context context, String text, String left, String right){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.retrydialog);
        container = findViewById(R.id.container);
        positiveBtn = (Button)findViewById(R.id.positiveBtn);
        negativeBtn = (Button)findViewById(R.id.negativeBtn);
        textView = (TextView)findViewById(R.id.text);
        title = (TextView)findViewById(R.id.title);
        positiveBtn.setOnClickListener(this);
        negativeBtn.setOnClickListener(this);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) container.getLayoutParams();
        int width = context.getResources().getDisplayMetrics().widthPixels;
        width =(int) (width* 0.7);
        params.width = width;
        int height = context.getResources().getDisplayMetrics().heightPixels;
        params.height = (int) (height * 0.4);
        container.setLayoutParams(params);

        title.setText(text);
        positiveBtn.setText(left);
        negativeBtn.setText(right);
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
