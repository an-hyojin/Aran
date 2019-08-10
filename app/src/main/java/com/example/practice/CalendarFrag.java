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
    Date date;
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
        Button addEmotion = view.findViewById(R.id.addDayemotion);

        mTvCalendarTitle = view.findViewById(R.id.gv_calendar_activity_tv_title);
        mGvCalendar =view.findViewById(R.id.gv_calendar_activity_gv_calendar);


        bLastMonth.setOnClickListener(this);
        bNextMonth.setOnClickListener(this);
        mGvCalendar.setOnItemClickListener(this);

        mDayList = new ArrayList<DayInfo>();
        mThisMonthCalendar = Calendar.getInstance();
        mThisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        getCalendar(mThisMonthCalendar);
        addEmotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayEmotionDBHelper dayEmotionDBHelper = new DayEmotionDBHelper(getContext());
                sqlDB = dayEmotionDBHelper.getWritableDatabase();
                date = new Date();
                SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");
                String str = dayTime.format(date);
                String[] infos = str.split("-");
                Long year = Long.valueOf(infos[0]);
                Long month = Long.valueOf(infos[1]);
                Long day = Long.valueOf(infos[2]);
                Cursor temp = sqlDB.rawQuery("SELECT * FROM dayEmotionDB WHERE (year="+year+" and month="+month+" and day =" + day +");", null);
                if(temp.getCount()==0) {
                    DayEmotionDialog dayEmotionDialog = new DayEmotionDialog(getContext());
                    dayEmotionDialog.setDayEmotionDialogListener(new DayEmotionDialogListener() {
                        @Override
                        public void onSmileButtonClicked() {
                            saveDayEmotion("기쁨");
                        }

                        @Override
                        public void onAngryButtonClicked() {
                            saveDayEmotion("화남");
                        }

                        @Override
                        public void onSadButtonClicked() {
                            saveDayEmotion("슬픔");
                        }

                        @Override
                        public void onFullButtonClicked() {
                            saveDayEmotion("뿌듯함");
                        }

                        @Override
                        public void onDisgustButtonClicked() {
                            saveDayEmotion("싫어함");
                        }

                        @Override
                        public void onSurprisedButtonClicked() {
                            saveDayEmotion("놀라움");
                        }

                        @Override
                        public void onHeartButtonClicked() {
                            saveDayEmotion("사랑");
                        }

                        @Override
                        public void onScaryButtonClicked() {
                            saveDayEmotion("무서움");
                        }
                    });
                    dayEmotionDialog.show();
                }else{
                    CustomDialog customDialog = new CustomDialog(getContext(),"이미 감정을 입력했습니다.","감정은 하루에 한번만 입력할 수 있습니다.","확인",R.drawable.surprised);
                    customDialog.show();
                }
            }
        });
        return view;
    }
    private void saveDayEmotion(String emotion){
        dayEmotionDBHelper = new DayEmotionDBHelper(getContext());
        sqlDB = dayEmotionDBHelper.getWritableDatabase();

        SQLiteStatement p1 = sqlDB.compileStatement("INSERT INTO dayEmotionDB VALUES (?,?, ?, ?,?);");

        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");
        String str = dayTime.format(date);
        String[] infos = str.split("-");
        p1.bindString(1, str);
        p1.bindLong(2, Long.valueOf(infos[0]));
        p1.bindLong(3, Long.valueOf(infos[1]));
        p1.bindLong(4, Long.valueOf(infos[2]));
        p1.bindString(5, emotion);
        p1.execute();
        getCalendar(mThisMonthCalendar);
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

        lastMonthStartDay -= (dayOfMonth-1)-1;


        // 캘린더 타이틀(년월 표시)을 세팅한다.
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "년 "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "월");
        dayEmotionDBHelper = new DayEmotionDBHelper(getContext());
        sqlDB = dayEmotionDBHelper.getWritableDatabase();


        DayInfo day;
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM dayEmotionDB WHERE (year="+mThisMonthCalendar.get(Calendar.YEAR)+" and month="+(mThisMonthCalendar.get(Calendar.MONTH) + 1)+") ORDER BY day;", null);

        System.out.println("카운트=" + cursor.getCount());
        cursor.moveToFirst();


        for(int i=0; i<dayOfMonth-1; i++) {//이번 달 이전

            day = new DayInfo();
            day.setDay("");
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
        cursor.close();
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

