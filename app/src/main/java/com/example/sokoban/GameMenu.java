package com.example.sokoban;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
/**
 *首页界面类
 * */
public class GameMenu {

    //菜单背景图
    private Bitmap cover;
    //按钮图片资源(按下和未按下图)
    private Bitmap button, button_press;
    //按钮的坐标
    private int btnX, btnY;
    //按钮是否按下标识位
    private Boolean isPress;
    //菜单初始化
    public GameMenu(Bitmap cover, Bitmap button, Bitmap button_press) {
        this.cover = cover;
        this.button = button;
        this.button_press = button_press;
        //x居中，y紧接屏幕底部
        btnX = (MySurfaceView.width - button.getWidth())/ 2;
        btnY = MySurfaceView.height - button.getHeight();
        isPress = false;
    }
    //菜单绘图函数
    public void draw(Canvas canvas, Paint paint) {
        //绘制菜单背景图
        canvas.drawBitmap(cover, 0, 0, paint);
        //绘制未按下按钮图
        if (isPress) {//根据是否按下绘制不同状态的按钮图
            canvas.drawBitmap(button_press, btnX, btnY, paint);
        } else {
            canvas.drawBitmap(button, btnX, btnY, paint);
        }
    }
    //菜单触屏事件函数，主要用于处理按钮事件
    public void onTouchEvent(MotionEvent event) {
        //获取用户当前触屏位置
        int pointX = (int) event.getX();
        int pointY = (int) event.getY();
        //当用户是按下动作或移动动作
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            //判断用户是否点击了按钮
            if (pointX > btnX && pointX < btnX + button.getWidth()) {
                if (pointY > btnY && pointY < btnY + button.getHeight()) {
                    isPress = true;
                } else {
                    isPress = false;
                }
            } else {
                isPress = false;
            }
            //当用户是抬起动作
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            //抬起判断是否点击按钮，防止用户移动到别处
            if (pointX > btnX && pointX < btnX + button.getWidth()) {
                if (pointY > btnY && pointY < btnY + button.getHeight()) {
                    //还原button状态未按下状态
                    isPress = false;
                    //改变当前游戏状态为开始游戏
                    MySurfaceView.gameState = MySurfaceView.GAMEINGONE;
                }
            }
        }
    }
}
