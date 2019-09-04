package com.example.practice.Sticker;

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

public class StatisticsFrag_stickerbook extends Fragment {
    View view;
    PieChart pieChart;
    ScrollView scrollView;
    ListView listView;
    SQLiteDatabase sqlDB;
    StickerDBHelper stickerDBHelper;
    TextView isEmpty;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.statistics_stickerbook, container, false);
        pieChart = (PieChart)view.findViewById(R.id.piechart);
        scrollView = (ScrollView)view.findViewById(R.id.scrollView);
        listView = (ListView)view.findViewById(R.id.listView);
        stickerDBHelper = new StickerDBHelper(getContext());
        isEmpty = view.findViewById(R.id.isEmpty);
        sqlDB = stickerDBHelper.getWritableDatabase();
        Cursor isSet = sqlDB.rawQuery("SELECT * FROM stickerTable", null);
        if(isSet.getCount()==0){
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

            ArrayList<String> gameList = new ArrayList<>();
            ArrayList<Integer> gameCountList = new ArrayList<>();
            HashMap<String, Integer> tempMap = new HashMap<>();
            for(Game g : Game.values()) {
                Cursor temp = sqlDB.rawQuery("SELECT * FROM stickerTable WHERE game = '" + g.name() + "'", null);
                int count = temp.getCount();
                if (count != 0) {
                    tempMap.put(g.name(),count);
                }
            }

            for(String key : tempMap.keySet()){
                Integer Num = tempMap.get(key);
                yValues.add(new PieEntry(Num, key));
                gameList.add(key);
                gameCountList.add(Num);
            }
            LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) listView.getLayoutParams();
            int height = getContext().getResources().getDisplayMetrics().heightPixels/10;
            param.height = height*gameList.size()+(int)(height*0.1);
            listView.setLayoutParams(param);

            LinearLayout.LayoutParams param2 = (LinearLayout.LayoutParams) pieChart.getLayoutParams();
            int height2 = getContext().getResources().getDisplayMetrics().widthPixels;
            param2.height = height2;
            pieChart.setLayoutParams(param2);

            Description description = new Description();
            description.setText("내가 한 게임들"); //라벨
            description.setTextSize(15);
            pieChart.setDescription(description);
            pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션


            PieDataSet dataSet = new PieDataSet(yValues,"게임");
            dataSet.setSliceSpace(3f);
            dataSet.setSelectionShift(5f);
            dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

            PieData data = new PieData((dataSet));
            data.setValueTextSize(10f);
            data.setValueTextColor(Color.YELLOW);
            listViewAdapter_sticker listViewAdapterSticker = new listViewAdapter_sticker(getContext(), gameList, gameCountList);
            listView.setAdapter(listViewAdapterSticker);
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