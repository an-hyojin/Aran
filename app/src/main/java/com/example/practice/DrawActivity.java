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
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DrawActivity extends AppCompatActivity implements View.OnClickListener {
    private final int GALLERY_CODE=1;
    private final int CROP_FROM_IMAGE=2;
    private final int DRAW_AND_SAVE=3;
    Button galleryBtn;
    ImageButton backBtn, addBtn;
    GridView gridView;
    GridAdapter adapter;
    ImageView showView;
    SQLiteDatabase sqlDB;
    private Uri ImageCaptureUri;
    myDBHelper faceDBHelper;
    ArrayList<Face> faces = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        galleryBtn = (Button) findViewById(R.id.gallary);
        galleryBtn.setOnClickListener(this);
        backBtn = (ImageButton)findViewById(R.id.back);
        backBtn.setOnClickListener(this);
        addBtn = (ImageButton)findViewById(R.id.add);
        addBtn.setOnClickListener(this);
        gridView = (GridView)findViewById(R.id.gridview);
        showView = (ImageView)findViewById(R.id.imageshow);
        ArrayList<Integer> img = new ArrayList<>();
        img.add(R.drawable.heart);
        img.add(R.drawable.heart);
        img.add(R.drawable.heart);
        img.add(R.drawable.heart);
        img.add(R.drawable.heart);
        img.add(R.drawable.plusbtn);
        ArrayList<Integer> id = new ArrayList<>();
        id.add(1);
        id.add(2);
        id.add(3);
        id.add(4);
        id.add(5);
        id.add(-1);
        faceDBHelper= new myDBHelper(this);
        sqlDB = faceDBHelper.getWritableDatabase();
        faceDBHelper.onUpgrade(sqlDB, 1, 2);

        adapter = new GridAdapter(this, img, id);

        SQLiteStatement p = sqlDB.compileStatement("INSERT INTO faceTBL VALUES (1,?, ?, ?);");
        p.bindString(1, "행복");
        p.bindBlob(2, getByteArrayFromDrawable(getResources().getDrawable(R.drawable.heart)));
        p.bindBlob(3, getByteArrayFromDrawable(getResources().getDrawable(R.drawable.heart)));
        p.execute();
        sqlDB.close();
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
                        Bitmap photo = extras.getParcelable("data");
                        showView.setImageBitmap(photo);
                        intent = new Intent(this, SmallDrawActivity.class);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        intent.putExtra("image",byteArray);

                        startActivityForResult(intent,DRAW_AND_SAVE );
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
