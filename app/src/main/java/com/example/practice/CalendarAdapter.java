package com.example.practice;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * BaseAdapter를 상속받아 구현한 CalendarAdapter
 *
 * @author croute
 * @since 2011.03.08
 */
public class CalendarAdapter extends BaseAdapter {
    private ArrayList<DayInfo> mDayList;
    private Context mContext;
    private int mResource;
    private LayoutInflater mLiInflater;


    public CalendarAdapter(Context context, int textResource, ArrayList<DayInfo> dayList) {
        this.mContext = context;
        this.mDayList = dayList;
        this.mResource = textResource;
        this.mLiInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mDayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DayInfo day = mDayList.get(position);
        DayViewHolder dayViewHolder;

        if (convertView == null) {
            convertView = mLiInflater.inflate(mResource, null);
            int width = getCellWidthDP();
            int restWidth = getRestCellWidthDP();
            double height = 1.4*width;
            if (position % 7 == 6) {
                convertView.setLayoutParams(new GridView.LayoutParams(width+restWidth, (int)height));
            } else {
                convertView.setLayoutParams(new GridView.LayoutParams(width,(int)height));
            }

            dayViewHolder = new DayViewHolder();
            dayViewHolder.llBackground = (LinearLayout) convertView.findViewById(R.id.day_cell_ll_background);
            dayViewHolder.tvDay = (TextView) convertView.findViewById(R.id.dateNumber);
            dayViewHolder.emotionImg = (ImageView) convertView.findViewById(R.id.emotionImg);
            convertView.setTag(dayViewHolder);

        } else {
            dayViewHolder = (DayViewHolder) convertView.getTag();
        }

        if (day != null) {
            dayViewHolder.tvDay.setText(day.getDay());
            int resource = stringToEmotion(day.getEmotion());
            if(resource!=-1){
                dayViewHolder.emotionImg.setImageResource(resource);
            }
            if (day.isInMonth()) {
                if (position % 7 == 0) {
                    dayViewHolder.tvDay.setTextColor(Color.RED);
                } else if (position % 7 == 6) {
                    dayViewHolder.tvDay.setTextColor(Color.BLUE);
                } else {
                    dayViewHolder.tvDay.setTextColor(Color.BLACK);
                }
            } else {
                dayViewHolder.tvDay.setTextColor(Color.GRAY);
            }
        }
        return convertView;
    }
    public int stringToEmotion(String emotion){
        if(emotion==null){
            return -1;
        }else if(emotion.equals("기쁨")) {
            return R.drawable.smile;
        }else if(emotion.equals("슬픔")){
            return R.drawable.sad;
        }else if(emotion.equals("놀라움")){
            return R.drawable.surprised;
        }else if(emotion.equals("화남")){
            return R.drawable.angry;
        }else if(emotion.equals("싫어함")){
            return R.drawable.disgust;
        }else if(emotion.equals("사랑")){
            return R.drawable.heart;
        }else if(emotion.equals("무서움")){
            return R.drawable.scary;
        }else if(emotion.equals("뿌듯함")){
            return R.drawable.full;
        }else{
            return R.drawable.yesbtn;
        }
    }
    public class DayViewHolder {
        public LinearLayout llBackground;
        public TextView tvDay;
        public ImageView emotionImg;
    }
    private int getCellWidthDP() {
        int width = mContext.getResources().getDisplayMetrics().widthPixels;
        return width / 7;
    }

    private int getRestCellWidthDP() {
        int width = mContext.getResources().getDisplayMetrics().widthPixels;
        return width % 7;
    }


}

