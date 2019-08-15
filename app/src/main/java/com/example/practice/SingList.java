package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class SingList extends AppCompatActivity implements View.OnClickListener {

    ImageButton backBtn;
    ImageButton singbutton1, singbutton2, singbutton3, singbutton4, singbutton5, singbutton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_list);

        backBtn = (ImageButton)findViewById(R.id.back);
        backBtn.setOnClickListener(this);

        singbutton1 = (ImageButton) findViewById(R.id.sing1);
        singbutton1.setOnClickListener(this);
        singbutton2 = (ImageButton) findViewById(R.id.sing2);
        singbutton2.setOnClickListener(this);
        singbutton3 = (ImageButton) findViewById(R.id.sing3);
        singbutton3.setOnClickListener(this);
        singbutton4 = (ImageButton) findViewById(R.id.sing4);
        singbutton4.setOnClickListener(this);
        singbutton5 = (ImageButton) findViewById(R.id.sing5);
        singbutton5.setOnClickListener(this);
        singbutton6 = (ImageButton) findViewById(R.id.sing6);
        singbutton6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.sing1: {
                Intent intent = new Intent(getApplicationContext(), SingActivity.class);
                intent.putExtra("singId","eDb8dZRaUVM");
                startActivity(intent);
                break;
            }
            case R.id.sing2: {
                Intent intent = new Intent(getApplicationContext(), SingActivity.class);
                intent.putExtra("singId","8JvitEXoeM0");
                startActivity(intent);
                break;
            }
            case R.id.sing3: {
                Intent intent = new Intent(getApplicationContext(), SingActivity.class);
                intent.putExtra("singId","6X7NGNnsM2U");
                startActivity(intent);
                break;
            }
            case R.id.sing4: {
                Intent intent = new Intent(getApplicationContext(), SingActivity.class);
                intent.putExtra("singId","KCtiEGC2gM4");
                startActivity(intent);
                break;
            }
            case R.id.sing5: {
                Intent intent = new Intent(getApplicationContext(), SingActivity.class);
                intent.putExtra("singId","-_r0U21K0dU");
                startActivity(intent);
                break;
            }
            case R.id.sing6: {
                Intent intent = new Intent(getApplicationContext(), SingActivity.class);
                intent.putExtra("singId","wX7wi7P3IcE");
                startActivity(intent);
                break;
            }
        }
    }
}
