package com.example.practice.Animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.practice.R;

public class SingList extends AppCompatActivity implements View.OnClickListener {

    Button backBtn;
    ImageButton singbutton1, singbutton2, singbutton3, singbutton4, singbutton5, singbutton6;
    TextView sing1,sing2,sing3,sing4,sing5,sing6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_list);

        backBtn = (Button)findViewById(R.id.back);
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
        sing1 = findViewById(R.id.sing_txt1);
        sing2 = findViewById(R.id.sing_txt2);
        sing3 = findViewById(R.id.sing_txt3);
        sing4 = findViewById(R.id.sing_txt4);
        sing5= findViewById(R.id.sing_txt5);
        sing6= findViewById(R.id.sing_txt6);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.sing1: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("Id","eDb8dZRaUVM");
                intent.putExtra("name",sing1.getText());
                startActivity(intent);
                break;
            }
            case R.id.sing2: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("Id","8JvitEXoeM0");
                intent.putExtra("name",sing2.getText());
                startActivity(intent);
                break;
            }
            case R.id.sing3: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("Id","6X7NGNnsM2U");
                intent.putExtra("name",sing3.getText());
                startActivity(intent);
                break;
            }
            case R.id.sing4: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("Id","KCtiEGC2gM4");
                intent.putExtra("name",sing4.getText());
                startActivity(intent);
                break;
            }
            case R.id.sing5: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("Id","-_r0U21K0dU");
                intent.putExtra("name",sing5.getText());
                startActivity(intent);
                break;
            }
            case R.id.sing6: {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                intent.putExtra("Id","wX7wi7P3IcE");
                intent.putExtra("name",sing6.getText());
                startActivity(intent);
                break;
            }
        }
    }
}
