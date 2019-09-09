package com.example.practice.Draw;

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
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.practice.Dialog.CustomDialog;
import com.example.practice.R;
import com.example.practice.Sticker.StickerDBHelper;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DrawActivity extends AppCompatActivity implements View.OnClickListener {
    private final int GALLERY_CODE=1;
    private final int CROP_FROM_IMAGE=2;
    private final int DRAW_AND_SAVE=3;
    ArrayList<Bitmap> imgList, idList;
    ArrayList<String> emotionList;
    ArrayList<Long> dateList;

    Button backBtn, addBtn;
    // gridview ; 2차원의 그리드에 항목들을 표시하는 뷰그룹
    GridView gridView;
    GridAdapter adapter;
    SQLiteDatabase sqlDB, sqlDB2;
    StickerDBHelper stickerDB;
    Bitmap realPhoto, drawImage;
    byte[] realByte, drawByte;
    FaceDBHelper faceDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        backBtn = (Button)findViewById(R.id.back);
        backBtn.setOnClickListener(this);
        addBtn = (Button)findViewById(R.id.add);
        addBtn.setOnClickListener(this);
        gridView = (GridView)findViewById(R.id.gridView);
        init();
        boolean isRead = getIntent().getBooleanExtra("onOffSound", false);
        // 팝업창
        CustomDialog dialog = new CustomDialog(this, "감정 따라그리기", "갤러리에서 사진을 선택한 후 그때 느낀 감정을 따라 그려보세요","확인", R.drawable.drawing, isRead);

        dialog.show();

    }

    public void init(){
        imgList = new ArrayList<>();
        idList = new ArrayList<>();
        dateList = new ArrayList<>();
        emotionList = new ArrayList<>();
        faceDBHelper= new FaceDBHelper(this);
        sqlDB = faceDBHelper.getWritableDatabase();
        stickerDB = new StickerDBHelper(this);
        sqlDB2 = stickerDB.getWritableDatabase();
        // cursor ; db에서 가져온 데이터를 쉽게 처리하기 위해서
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM faceTBL;", null);
        // 제일 첫번째 행으로 이동
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            long date = cursor.getLong(0);
            String emotion = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            Bitmap imgBitmap= getBitmapBybyte(image);
            byte[] photo = cursor.getBlob(3);
            Bitmap photoBitmap = getBitmapBybyte(photo);

            // 리스트에 추가
            imgList.add(imgBitmap);
            idList.add(photoBitmap);
            dateList.add(date);
            emotionList.add(emotion);

            // 다음 행으로 이동
            cursor.moveToNext();
        }
                                                                            //emotionList
        adapter = new GridAdapter(this, imgList, idList, dateList);
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
                // 다음으로 넘겨주는 코드
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
//                        p.bindString(2, emotion);
                        p.bindBlob(3, drawByte);
                        p.bindBlob(4, realByte);
                        p.execute();
                        sqlDB.close();
//                        emotionList.add(emotion);
                        dateList.add(date.getTime());
                        imgList.add(drawImage);
                        idList.add(realPhoto);
                                                                                       // , emotionList
                        adapter = new GridAdapter(this, imgList, idList, dateList);
                        gridView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        sqlDB2 = stickerDB.getWritableDatabase();
                        SQLiteStatement pp = sqlDB2.compileStatement("INSERT INTO stickerTable VALUES (?, ?, ?);");
                        pp.bindString(2, "감정따라그리기");
                        pp.bindString(3, getToday());
                        pp.execute();
                        sqlDB2.close();
                    }
                default:
                    break;

            }
        }
    }

    public Bitmap getBitmapBybyte(byte[] b){
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }
    public String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}