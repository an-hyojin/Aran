package com.example.practice;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class DrawActivity extends AppCompatActivity implements View.OnClickListener {
    private final int GALLERY_CODE=1;
    private final int CROP_FROM_IMAGE=2;
    private final int DRAW_AND_SAVE=3;
    ArrayList<Bitmap> imgList, idList;
    ArrayList<String> emotionList;
    ArrayList<Long> dateList;

    ImageButton backBtn, addBtn;
    GridView gridView;
    GridAdapter adapter;
    SQLiteDatabase sqlDB;
    Bitmap realPhoto, drawImage;
    byte[] realByte, drawByte;
    FaceDBHelper faceDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        backBtn = (ImageButton)findViewById(R.id.back);
        backBtn.setOnClickListener(this);
        addBtn = (ImageButton)findViewById(R.id.add);
        addBtn.setOnClickListener(this);
        gridView = (GridView)findViewById(R.id.gridView);

        init();
        CustomDialog dialog = new CustomDialog(this, 12);

        dialog.show();

    }

    public void init(){
        imgList = new ArrayList<>();
        idList = new ArrayList<>();
        dateList = new ArrayList<>();
        emotionList = new ArrayList<>();
        faceDBHelper= new FaceDBHelper(this);
        sqlDB = faceDBHelper.getWritableDatabase();

        Cursor cursor = sqlDB.rawQuery("SELECT * FROM faceTBL;", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            long date = cursor.getLong(0);
            String emotion = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            Bitmap imgBitmap= getBitmapBybyte(image);
            byte[] photo = cursor.getBlob(3);
            Bitmap photoBitmap = getBitmapBybyte(photo);
            imgList.add(imgBitmap);
            idList.add(photoBitmap);
            dateList.add(date);
            emotionList.add(emotion);
            cursor.moveToNext();
        }

        adapter = new GridAdapter(this, imgList, idList, dateList, emotionList);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_CODE);
                break;
            case R.id.back:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        init();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GALLERY_CODE:
                    Uri ImageCaptureUri = data.getData();
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(ImageCaptureUri, "image/*");
                    intent.putExtra("outputX", 200);
                    intent.putExtra("outputY", 200);
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("scale", true);
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, CROP_FROM_IMAGE);
                    break;
                case CROP_FROM_IMAGE:
                    Bundle extras = data.getExtras();
                    if(extras!=null){
                        realPhoto = extras.getParcelable("data");
                        intent = new Intent(this, SmallDrawActivity.class);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        realPhoto.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        realByte = stream.toByteArray();
                        intent.putExtra("image",realByte);
                        startActivityForResult(intent,DRAW_AND_SAVE );
                    }
                    break;
                case DRAW_AND_SAVE:
                    Bundle extras2 = data.getExtras();
                    if(extras2!=null){
                        drawByte = extras2.getByteArray("drawing");
                        String emotion = extras2.getString("emotion");
                        Toast.makeText(this, emotion, Toast.LENGTH_SHORT).show();
                        drawImage =BitmapFactory.decodeByteArray(drawByte,0, drawByte.length);
                        sqlDB = faceDBHelper.getWritableDatabase();
                        SQLiteStatement p = sqlDB.compileStatement("INSERT INTO faceTBL VALUES (?,?, ?, ?);");
                        Date date = new Date();
                        p.bindLong(1, date.getTime());
                        p.bindString(2, emotion);
                        p.bindBlob(3, drawByte);
                        p.bindBlob(4, realByte);
                        p.execute();
                        sqlDB.close();
                        emotionList.add(emotion);
                        dateList.add(date.getTime());
                        imgList.add(drawImage);
                        idList.add(realPhoto);
                        adapter = new GridAdapter(this, imgList, idList, dateList, emotionList);
                        gridView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                    default:
                    break;

             }
        }
    }

    public Bitmap getBitmapBybyte(byte[] b){
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }
}
