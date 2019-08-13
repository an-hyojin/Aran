package com.example.practice;

public enum Emotion {
    기쁨(R.drawable.smile),
    슬픔(R.drawable.sad),
    놀라움(R.drawable.surprised),
    화남(R.drawable.angry),
    싫어함(R.drawable.disgust),
    사랑(R.drawable.heart),
    무서움(R.drawable.scary),
    뿌듯함(R.drawable.full);

    private final int emotionResource;
    public int getEmotionResource(){
        return  this.emotionResource;
    }

    Emotion(int resource){
        this.emotionResource = resource;
    }
}
