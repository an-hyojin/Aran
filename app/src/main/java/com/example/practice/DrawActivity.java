package com.example.practice;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class DrawActivity extends AppCompatActivity implements View.OnClickListener {
    private final int GALLERY_CODE=1;
    private final int CROP_FROM_IMAGE=2;
    private final int DRAW_AND_SAVE=3;
    ArrayList<Bitmap> imgList;
    ArrayList<Bitmap> idList;
    ArrayList<String> emotionList;
    ArrayList<Long> dateList;

    Button galleryBtn;
    ImageButton backBtn, addBtn;
    GridView gridView;
    GridAdapter adapter;
    ImageView showView;
    SQLiteDatabase sqlDB;
    Bitmap realPhoto, drawImage;
    byte[] realByte, drawByte;
    private Uri ImageCaptureUri;
    myDBHelper faceDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        backBtn = (ImageButton)findViewById(R.id.back);
        backBtn.setOnClickListener(this);
        addBtn = (ImageButton)findViewById(R.id.add);
        addBtn.setOnClickListener(this);
        gridView = (GridView)findViewById(R.id.gridview);
        showView = (ImageView)findViewById(R.id.imageshow);
        imgList = new ArrayList<>();
        idList = new ArrayList<>();
        dateList = new ArrayList<>();
        emotionList = new ArrayList<>();

        faceDBHelper= new myDBHelper(this);
        sqlDB = faceDBHelper.getWritableDatabase();

        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM faceTBL;", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            long date = cursor.getLong(0);
            String emotion = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            Bitmap imgBitmap= getAppIcon(image);
            byte[] photo = cursor.getBlob(3);
            Bitmap photoBitmap = getAppIcon(photo);
            imgList.add(imgBitmap);
            idList.add(photoBitmap);
            dateList.add(date);
            emotionList.add(emotion);
            cursor.moveToNext();
        }
        /*
        sqlDB = faceDBHelper.getWritableDatabase();
        faceDBHelper.onUpgrade(sqlDB, 1, 2);

        SQLiteStatement p = sqlDB.compileStatement("INSERT INTO faceTBL VALUES (1,?, ?, ?);");
        p.bindString(1, "행복");
        p.bindBlob(2, getByteArrayFromDrawable(getResources().getDrawable(R.drawable.heart)));
        p.bindBlob(3, getByteArrayFromDrawable(getResources().getDrawable(R.drawable.heart)));
        p.execute();
        sqlDB.close();
        */
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
                /*sqlDB = faceDBHelper.getWritableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM faceTBL;", null);
                cursor.moveToFirst();
                Integer date = cursor.getInt(0);
                String emotion = cursor.getString(1);
                byte[] img1 = cursor.getBlob(2);
                Bitmap icon = getAppIcon(img1);
                Toast.makeText(getApplicationContext(), emotion + " " + date, Toast.LENGTH_LONG).show();
                */
                break;

            case R.id.back:
                onBackPressed();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GALLERY_CODE:
                    ImageCaptureUri = data.getData();
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
                    if(resultCode!=RESULT_OK){
                        return;
                    }
                    final Bundle extras = data.getExtras();
                    if(extras!=null){
                        realPhoto = extras.getParcelable("data");
                        showView.setImageBitmap(realPhoto);
                        intent = new Intent(this, SmallDrawActivity.class);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        realPhoto.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        realByte = stream.toByteArray();
                        intent.putExtra("image",realByte);

                        startActivityForResult(intent,DRAW_AND_SAVE );

                    }
                    break;
                case DRAW_AND_SAVE:
                    final Bundle extras2 = data.getExtras();
                    if(extras2!=null){
                        drawByte = extras2.getByteArray("drawing");
                        String emotion = extras2.getString("emotion");
                        Toast.makeText(this, emotion, Toast.LENGTH_SHORT).show();
                        drawImage =BitmapFactory.decodeByteArray(drawByte,0, drawByte.length);
                        showView.setImageBitmap(drawImage);
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
    public byte[] getByteArrayFromDrawable(Drawable d){
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] data = stream.toByteArray();
        return data;
    }

    public Bitmap getAppIcon(byte[] b){
        Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        return bitmap;
    }
}
