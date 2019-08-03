package com.example.practice;

import android.widget.ImageButton;

class Card{
    private final static int backImageID = R.drawable.back;
    private final static int[] frontImageID = {R.drawable.smilecard, R.drawable.sadcard, R.drawable.angrycard,R.drawable.disgustcard, R.drawable.fullcard, R.drawable.surprisedcard,R.drawable.heartcard, R.drawable.scarycard};

    int value;
    boolean isBack;
    ImageButton card;

    Card(int value){
        this.value = value;
    }

    public void back(){
        if(!isBack){
            card.setBackgroundResource(backImageID);
            isBack = true;
        }
    }


    public void turn(){
        if(!isBack){
            card.setBackgroundResource(backImageID);
            isBack = true;
        }else{
            card.setBackgroundResource(frontImageID[value]);
            isBack = false;
        }
    }

    public void front(){
        if(isBack){
            card.setBackgroundResource(frontImageID[value]);
            isBack = false;
        }
    }

}
