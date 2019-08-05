package com.example.practice;

import android.widget.ImageButton;

class Card{
    private final static int backImageID = R.drawable.back;
    private final static int[] frontImageID = {R.drawable.smilecard, R.drawable.sadcard, R.drawable.angrycard,R.drawable.disgustcard, R.drawable.fullcard, R.drawable.surprisedcard,R.drawable.heartcard, R.drawable.scarycard};

    int value;
    private boolean isBack;
    ImageButton card;

    Card(int value){
        this.value = value;
    }


    public boolean isBack(){
        return this.isBack;
    }
    public void setIsBack(Boolean isBack){
        this.isBack = isBack;
    }

    public void back(){
        if(!isBack){
            card.setBackgroundResource(backImageID);
            setIsBack(true);
        }
    }


    public void front(){
        if(isBack()){
            card.setBackgroundResource(frontImageID[value]);
            setIsBack(false);
        }
    }

}
