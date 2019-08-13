package com.example.practice;

public class DayInfo
{
    private String day;
    private boolean inMonth;
    private String emotion;

    public void setEmotion(String emotion){this.emotion = emotion;}


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

