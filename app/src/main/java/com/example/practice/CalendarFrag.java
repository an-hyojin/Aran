package com.example.practice;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarFrag extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    View view;
    public static int SUNDAY        = 1;

    DayEmotionDBHelper dayEmotionDBHelper;
    private TextView mTvCalendarTitle;
    private GridView mGvCalendar;

    private ArrayList<DayInfo> mDayList;
    private CalendarAdapter mCalendarAdapter;

    Calendar mThisMonthCalendar;
    SQLiteDatabase sqlDB;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calendar, container, false);
        Button bLastMonth = view.findViewById(R.id.gv_calendar_activity_b_last);
        Button bNextMonth =view.findViewById(R.id.gv_calendar_activity_b_next);

        mTvCalendarTitle = view.findViewById(R.id.gv_calendar_activity_tv_title);
        mGvCalendar =view.findViewById(R.id.gv_calendar_activity_gv_calendar);


        bLastMonth.setOnClickListener(this);
        bNextMonth.setOnClickListener(this);
        mGvCalendar.setOnItemClickListener(this);

        mDayList = new ArrayList<DayInfo>();
        mThisMonthCalendar = Calendar.getInstance();
        mThisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        getCalendar(mThisMonthCalendar);
        return view;
    }

    private void getCalendar(Calendar calendar)
    {
        int lastMonthStartDay;
        int dayOfMonth;
        int thisMonthLastDay;

        mDayList.clear();

        // 이번달 시작일의 요일을 구한다. 시작일이 일요일인 경우 인덱스를 1(일요일)에서 8(다음주 일요일)로 바꾼다.)
        dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, -1);

        // 지난달의 마지막 일자를 구한다.
        lastMonthStartDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, 1);

        if(dayOfMonth == SUNDAY)
        {
            dayOfMonth += 7;
        }

        lastMonthStartDay -= (dayOfMonth-1)-1;


        // 캘린더 타이틀(년월 표시)을 세팅한다.
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "년 "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "월");
        dayEmotionDBHelper = new DayEmotionDBHelper(getContext());
        sqlDB = dayEmotionDBHelper.getWritableDatabase();

/*
        SQLiteStatement p1 = sqlDB.compileStatement("INSERT INTO dayEmotionDB VALUES (?,?, ?, ?,?);");

        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");
        String str = dayTime.format(today);
        String[] infos = str.split("-");
        p1.bindString(1, str);
        p1.bindLong(2, Long.valueOf(infos[0]));
        p1.bindLong(3, Long.valueOf(infos[1]));
        p1.bindLong(4, Long.valueOf(infos[2]));
        p1.bindString(5, "뿌듯함");
        p1.execute();
*/
        DayInfo day;
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM dayEmotionDB WHERE (year="+mThisMonthCalendar.get(Calendar.YEAR)+" and month="+(mThisMonthCalendar.get(Calendar.MONTH) + 1)+") ORDER BY day;", null);

        System.out.println("카운트=" + cursor.getCount());
        cursor.moveToFirst();



//        if(cursor.getCount()!=0){
//            cursor.moveToFirst();
//
//            System.out.println("들어옴1");
//
//        }
        for(int i=0; i<dayOfMonth-1; i++) {//이번 달 이전
            int date = lastMonthStartDay+i;
            day = new DayInfo();
            day.setDay(Integer.toString(date));
            day.setInMonth(false);

            mDayList.add(day);
        }
        for(int i=1; i <= thisMonthLastDay; i++) {//이번달
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInMonth(true);
            if(!cursor.isAfterLast()) {
                if (i == (int)(cursor.getLong(3))) {
                    day.setEmotion(cursor.getString(4));
                    cursor.moveToNext();
                }
           }

            mDayList.add(day);

        }
        for(int i=1; i<42-(thisMonthLastDay+dayOfMonth-1)+1; i++) {//다음달
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInMonth(false);
            mDayList.add(day);
        }

        initCalendarAdapter();
    }

    /**
     * 지난달의 Calendar 객체를 반환합니다.
     *
     * @param calendar
     * @return LastMonthCalendar
     */
    private Calendar getLastMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "년 "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "월");
        return calendar;
    }

    /**
     * 다음달의 Calendar 객체를 반환합니다.
     *
     * @param calendar
     * @return NextMonthCalendar
     */
    private Calendar getNextMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, +1);
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "년 "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "월");
        return calendar;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long arg3)
    {

    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.gv_calendar_activity_b_last:
                mThisMonthCalendar = getLastMonth(mThisMonthCalendar);
                getCalendar(mThisMonthCalendar);
                break;
            case R.id.gv_calendar_activity_b_next:
                mThisMonthCalendar = getNextMonth(mThisMonthCalendar);
                getCalendar(mThisMonthCalendar);
                break;
        }
    }

    private void initCalendarAdapter()
    {
        mCalendarAdapter = new CalendarAdapter(getContext(), R.layout.day, mDayList);
        mGvCalendar.setAdapter(mCalendarAdapter);
    }
}


