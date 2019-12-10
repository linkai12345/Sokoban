package com.example.sokoban;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_Win {

    private Bitmap game_win;

    public Game_Win(Bitmap game_win){
        this.game_win=game_win;
    }

    public void draw(Canvas canvas, Paint paint) {

        canvas.drawBitmap(game_win,0,0,paint);

    }

}
