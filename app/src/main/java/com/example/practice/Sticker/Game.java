package com.example.practice.Sticker;

import com.example.practice.R;

public enum Game {
    감정카드놀이(R.drawable.card_drawable),
    감정맞추기(R.drawable.match_emotion_drawable),
    감정따라그리기(R.drawable.drawing_drawable),;

    private final int gameResource;
    public int getGameResource(){
        return  this.gameResource;
    }

    Game(int resource){
        this.gameResource = resource;
    }
}
