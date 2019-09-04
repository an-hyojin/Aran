package com.example.practice.Calendar;

public class DayInfo
{
    private String day;
    private boolean inMonth;
    private String emotion;
    public String dayFull ="";
    public void setEmotion(String emotion){this.emotion = emotion;}
    public void setDayFull(String dayFull){this.dayFull = dayFull;}
    public String getDayFull(){return  this.dayFull;}
    public String getEmotion(){return this.emotion;}


    public String getDay()
    {
        return day;
    }


    public void setDay(String day)
    {
        this.day = day;
    }


    public boolean isInMonth()
    {
        return inMonth;
    }


    public void setInMonth(boolean inMonth)
    {
        this.inMonth = inMonth;
    }

}

