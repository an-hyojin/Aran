//package com.example.practice;
//
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//
//import java.util.ArrayList;
//
//
//public class StickerBookActivity extends AppCompatActivity{
//    Button backBtn;
//    Button button1;
//    StickerDBHelper helper;
//    SQLiteDatabase sqlDB;
//    Button btn1, btn2;
//    FragmentManager fm;
//    FragmentTransaction tran;
//    StickerFrag stickerFrag;
//    StatisticsFrag_stickerbook statisticsFragStickerbook;
//    ImageButton front, behind;
//
//    ArrayList<String> gameList, dateList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sticker_book);
//        backBtn = (Button)findViewById(R.id.back);
//        backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//
//        btn1 = (Button) findViewById(R.id.stickerbook);
//        btn2 = (Button) findViewById(R.id.stastics);
//        stickerFrag = new StickerFrag();
//        statisticsFragStickerbook = new StatisticsFrag_stickerbook();
//        fm = getSupportFragmentManager();
//        tran = fm.beginTransaction();
//        tran.replace(R.id.container,stickerFrag);
//        tran.commit();
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fm = getSupportFragmentManager();
//                tran = fm.beginTransaction();
//                tran.replace(R.id.container,stickerFrag);
//                tran.commit();
//
//            }
//        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fm =getSupportFragmentManager();
//                tran = fm.beginTransaction();
//                tran.replace(R.id.container,statisticsFragStickerbook);
//                tran.commit();
//
//            }
//        });
//
////        Cursor cursor = sqlDB.rawQuery("SELECT * FROM stickerTable;", null);
////        cursor.moveToFirst();
////
////        gameList = new ArrayList<>();
////        dateList = new ArrayList<>();
////        while(!cursor.isAfterLast()) {
////            String game = cursor.getString(0);
////            String date = cursor.getString(1);
////            gameList.add(game);
////            dateList.add(date);
////            cursor.moveToNext();
////        }
//
////        button1 = (Button)findViewById(R.id.sticker01);
////        button1.setText(gameList.get(0));
//    }
//}
//
//
package com.example.practice.Sticker;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.practice.Dialog.CustomDialog;
import com.example.practice.Dialog.DialogListenerInterface;
import com.example.practice.R;

public class StickerBookActivity extends AppCompatActivity {

    Button backBtn, stickerButton, statisticsButton;
    FragmentManager fm;
    FragmentTransaction tran;
    StickerFrag stickerFrag;
    StatisticsFrag_stickerbook statisticsFragStickerBook;
    private boolean isRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_book);

        backBtn = (Button)findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        stickerButton = (Button)findViewById(R.id.stickerbook);
        statisticsButton = (Button)findViewById(R.id.statistics);

        isRead = getIntent().getBooleanExtra("onOffSound", false);
        CustomDialog dialog = new CustomDialog(this, "스티커 북", "게임을 하여 스티커를 모아보세요.\n통계에서 내가 한 게임들을 한눈에 볼 수 있습니다.","시작",R.drawable.stickerbook, isRead);
        dialog.setDialogListener(new DialogListenerInterface() {
            @Override
            public void onPositiveClicked() {

            }

            @Override
            public void onNegativeClicked() {

            }
        });
        dialog.show();

        stickerFrag = new StickerFrag();
        statisticsFragStickerBook = new StatisticsFrag_stickerbook();
        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
        tran.replace(R.id.container, stickerFrag);
        tran.commit();
        stickerButton.setBackgroundResource(R.drawable.topbuttonborder);
        statisticsButton.setBackgroundResource(R.drawable.buttononclickborder);
        stickerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fm = getSupportFragmentManager();
                tran = fm.beginTransaction();
                tran.replace(R.id.container, stickerFrag);
                tran.commit();
                stickerButton.setBackgroundResource(R.drawable.topbuttonborder);
                statisticsButton.setBackgroundResource(R.drawable.buttononclickborder);
            }
        });
        statisticsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fm = getSupportFragmentManager();
                tran = fm.beginTransaction();
                tran.replace(R.id.container, statisticsFragStickerBook);
                tran.commit();
                stickerButton.setBackgroundResource(R.drawable.buttononclickborder);
                statisticsButton.setBackgroundResource(R.drawable.topbuttonborder);
            }
        });
    }
}