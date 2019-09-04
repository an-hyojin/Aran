package com.example.practice.Calendar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.practice.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class StatisticsFrag extends Fragment {
    View view;
    PieChart pieChart;
    ScrollView scrollView;
    ListView listView;
    SQLiteDatabase sqlDB;
    DayEmotionDBHelper dayEmotionDBHelper;
    TextView isEmpty;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.statistics, container, false);
        pieChart = (PieChart)view.findViewById(R.id.piechart);
        scrollView = (ScrollView)view.findViewById(R.id.scrollView);
        listView = (ListView)view.findViewById(R.id.listView);
        dayEmotionDBHelper = new DayEmotionDBHelper(getContext());
        sqlDB = dayEmotionDBHelper.getWritableDatabase();
        isEmpty = view.findViewById(R.id.isEmpty);
        Cursor isSet = sqlDB.rawQuery("SELECT * FROM dayEmotionDB",null);
        int num = isSet.getCount();
        if(num==0){
            isEmpty.setVisibility(View.VISIBLE);
        }else{
            isEmpty.setVisibility(View.INVISIBLE);
            pieChart.setUsePercentValues(true);
            pieChart.setTouchEnabled(false);
            pieChart.getDescription().setEnabled(false);
            pieChart.setExtraOffsets(5,10,5,5);

            pieChart.setDragDecelerationFrictionCoef(0.95f);

            pieChart.setDrawHoleEnabled(false);
            pieChart.setHoleColor(Color.WHITE);
            pieChart.setTransparentCircleRadius(61f);
            ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

            ArrayList<String> emotionList = new ArrayList<>();
            ArrayList<Integer> emotionCountList = new ArrayList<>();
            HashMap<String, Integer> tempMap = new HashMap<>();
            for(Emotion e : Emotion.values()) {
                Cursor temp = sqlDB.rawQuery("SELECT * FROM dayEmotionDB WHERE emotion = '" + e.name() + "'", null);
                int count = temp.getCount();
                if (count != 0) {
                    tempMap.put(e.name(),count);
                }
            }
            String not="";
            for(Emotion e : Emotion.values()){
                if(e.name()!="뿌듯함"){
                    not +="emotion != '" + e.name() +"' and ";
                }else{
                    not += "emotion != '" + e.name() + "'";
                }
            }
            Cursor etc = sqlDB.rawQuery("SELECT * FROM dayEmotionDB WHERE ("+ not +");",null);
            if(etc.getCount()!= 0) {

                etc.moveToFirst();

                while(!etc.isAfterLast()) {
                    String emotion = etc.getString(4);
                    if(tempMap.containsKey(emotion)){
                        Integer Num = tempMap.get(emotion);
                        tempMap.put(emotion,Num+1);
                    }else{
                        tempMap.put(emotion,1);
                    }
                    etc.moveToNext();
                }

            }
            for(String key : tempMap.keySet()){
                Integer Num = tempMap.get(key);
                yValues.add(new PieEntry(Num, key));
                emotionList.add(key);
                emotionCountList.add(Num);
            }
            LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) listView.getLayoutParams();
            int height = getContext().getResources().getDisplayMetrics().heightPixels/10;
            param.height = height*emotionList.size()+(int)(height*0.2);
            listView.setLayoutParams(param);

            LinearLayout.LayoutParams param2 = (LinearLayout.LayoutParams) pieChart.getLayoutParams();
            int height2 = getContext().getResources().getDisplayMetrics().widthPixels;
            param2.height = height2;
            pieChart.setLayoutParams(param2);
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
            pieChart.setData(data);
            scrollView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    listView.requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });

        }
        return view;

    }
}
