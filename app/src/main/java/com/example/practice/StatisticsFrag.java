package com.example.practice;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

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
    ScrollView scrollView;
    ListView listView;
    SQLiteDatabase sqlDB;
    DayEmotionDBHelper dayEmotionDBHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.statistics, container, false);
        pieChart = (PieChart)view.findViewById(R.id.piechart);
        scrollView = (ScrollView)view.findViewById(R.id.scrollView);
        listView = (ListView)view.findViewById(R.id.listView);
        dayEmotionDBHelper = new DayEmotionDBHelper(getContext());
        sqlDB = dayEmotionDBHelper.getWritableDatabase();

        pieChart.setUsePercentValues(true);
        pieChart.setTouchEnabled(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();
        Cursor all = sqlDB.rawQuery("SELECT * FROM dayEmotionDB",null);
        int allCnt = all.getCount();

        ArrayList<String> emotionList = new ArrayList<>();
        ArrayList<Integer> emotionCountList = new ArrayList<>();

        for(Emotion e : Emotion.values()) {
            Cursor temp = sqlDB.rawQuery("SELECT * FROM dayEmotionDB WHERE emotion = '" + e.name() + "'", null);
            int count = temp.getCount();
            if (count != 0) {
                emotionList.add(e.name());
                emotionCountList.add(count);
                allCnt -= count;
                yValues.add(new PieEntry(count, e.name()));
            }
        }
        yValues.add(new PieEntry(allCnt, "기타"));
        Description description = new Description();
        description.setText("기록한 감정들"); //라벨
        description.setTextSize(15);
        pieChart.setDescription(description);
        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션


        PieDataSet dataSet = new PieDataSet(yValues,"감정");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);
        listViewAdapter listAdapter = new listViewAdapter(getContext(), emotionList, emotionCountList);
        listView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        pieChart.setData(data);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        return view;
    }
}
