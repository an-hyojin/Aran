package com.example.practice;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;



public class GridItemClickListener implements OnClickListener {
    Context context;
    Long dateKey;

    public GridItemClickListener(Context context, Long dateKey) {
        this.context = context;
        this.dateKey = dateKey;
    }

    public void onClick(View v) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra("dateKey", dateKey);
        context.startActivity(intent);
    }
}