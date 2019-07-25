package com.example.practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.ArrayList;

public class DrawActivity extends AppCompatActivity implements View.OnClickListener {
    private final int GALLERY_CODE=1112;
    Button galleryBtn;
    ImageButton backBtn;
    GridView gridView;
    GridAdapter adapter;
    ArrayList<Face> faces = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        galleryBtn = (Button) findViewById(R.id.gallary);
        galleryBtn.setOnClickListener(this);
        backBtn = (ImageButton)findViewById(R.id.back);
        backBtn.setOnClickListener(this);
        gridView = (GridView)findViewById(R.id.gridview);
        ArrayList<Integer> img = new ArrayList<>();
        img.add(R.drawable.heart);
        img.add(R.drawable.heart);
        img.add(R.drawable.heart);
        img.add(R.drawable.heart);
        img.add(R.drawable.heart);

        adapter = new GridAdapter(this, img);

        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gallary:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_CODE);
            case R.id.back:
                onBackPressed();

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GALLERY_CODE:
                    break;
                default:
                    break;
             }
        }
    }
}
