package com.example.practice;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class StatisticsFrag extends Fragment {
    View view;
    PieChart pieChart;
    ListView listView;
    SQLiteDatabase sqlDB;
    DayEmotionDBHelper dayEmotionDBHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.statistics, container, false);
        pieChart = (PieChart)view.findViewById(R.id.piechart);

        listView = (ListView)view.findViewById(R.id.listView);
        dayEmotionDBHelper = new DayEmotionDBHelper(getContext());
        sqlDB = dayEmotionDBHelper.getWritableDatabase();

        Cursor smile = sqlDB.rawQuery("SELECT * FROM dayEmotionDB WHERE emotion='기쁨'",null);
        int smileCnt = smile.getCount();
        System.out.println("smile"+smile.getCount());

        Cursor sad = sqlDB.rawQuery("SELECT * FROM dayEmotionDB WHERE emotion='슬픔'",null);
        System.out.println("sad"+sad.getCount());
        int sadCnt = sad.getCount();
        Cursor surprise = sqlDB.rawQuery("SELECT * FROM dayEmotionDB WHERE emotion='놀라움'",null);
        int surpriseCnt = surprise.getCount();
        Cursor angry = sqlDB.rawQuery("SELECT * FROM dayEmotionDB WHERE emotion='화남'",null);
        int angryCnt = angry.getCount();
        Cursor disgust = sqlDB.rawQuery("SELECT * FROM dayEmotionDB WHERE emotion='싫어함'",null);
        int disgustCnt = disgust.getCount();
        Cursor heart = sqlDB.rawQuery("SELECT * FROM dayEmotionDB WHERE emotion='사랑'",null);
        int heartCnt = heart.getCount();
        Cursor scary = sqlDB.rawQuery("SELECT * FROM dayEmotionDB WHERE emotion='무서움'",null);
        int scaryCnt = scary.getCount();
        Cursor full = sqlDB.rawQuery("SELECT * FROM dayEmotionDB WHERE emotion='뿌듯함'",null);
        int fullCnt = full.getCount();
        Cursor all = sqlDB.rawQuery("SELECT * FROM dayEmotionDB",null);
        int allCnt = all.getCount();
        int etcCnt = allCnt - ( smileCnt + sadCnt + angryCnt + disgustCnt + heartCnt + scaryCnt + fullCnt + surpriseCnt);

        pieChart.setUsePercentValues(true);
        pieChart.setTouchEnabled(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        yValues.add(new PieEntry(smileCnt,"기쁨"));
        yValues.add(new PieEntry(sadCnt,"슬픔"));
        yValues.add(new PieEntry(surpriseCnt,"놀라움"));
        yValues.add(new PieEntry(angryCnt,"화남"));
        yValues.add(new PieEntry(disgustCnt,"싫어함"));
        yValues.add(new PieEntry(heartCnt,"사랑"));
        yValues.add(new PieEntry(scaryCnt,"무서움"));
        yValues.add(new PieEntry(fullCnt,"뿌듯함"));
        yValues.add(new PieEntry(etcCnt, "기타"));
        Description description = new Description();
        description.setText("기록한 감정들"); //라벨
        description.setTextSize(15);
        pieChart.setDescription(description);

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션


        PieDataSet dataSet = new PieDataSet(yValues,"Countries");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);
        return view;
    }
}
