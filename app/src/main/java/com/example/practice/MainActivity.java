package com.example.practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button3 = (Button)findViewById(R.id.btn3);

        button3.setOnClickListener(this);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn3:
                Intent intent = new Intent(this, CardActivity.class);
                startActivity(intent);
        }
    }
}
