package com.example.sokoban;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;

public class Gameing_two {

    private Bitmap blue_brick,brick,red_box,person,box,food,two;
    int x,y,width,height,offset_x,offset_y;
    int mx=1,my=5;
    //1墙壁 9蓝色墙壁 2主角 3箱子 5红色箱子 6食物
    int map[][]={
            {0,0,1,1,1,1,1,0},
            {1,1,1,9,9,2,1,0},
            {1,9,9,3,6,9,1,1},
            {1,9,9,6,3,6,1,1},
            {1,1,1,9,5,3,9,1},
            {0,0,1,9,9,9,1,1},
            {0,0,1,1,1,1,1,0}
    };
    public Gameing_two(Bitmap box,Bitmap blue_brick,Bitmap brick,Bitmap red_box,Bitmap person,Bitmap food,Bitmap two){
        this.blue_brick=blue_brick;
        this.brick=brick;
        this.red_box=red_box;
        this.person=person;
        this.box=box;
        this.food=food;
        this.two=two;
        int screenHeight=MySurfaceView.height;
        int screenWidth=MySurfaceView.width;
        width=brick.getWidth();
        height=brick.getHeight();
        offset_x=(screenWidth-map[0].length*width)/2;
        offset_y=(screenHeight-map.length*width)/2;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(two,0,0,paint);
        int a=0;
        for(int i=0;i<map.length;i++)
            for(int j=0;j<map[i].length;j++)
            {
                //1墙壁 9蓝色墙壁 2主角 3箱子 5红色箱子 6食物
                //Bitmap blue_brick,brick,red_box,person;
                x=j*width+offset_x;
                y=i*height+offset_y;
                int t=map[i][j];
                if(t==1)
                    canvas.drawBitmap(brick,x,y,paint);
                if(t==5) {
                    canvas.drawBitmap(red_box, x, y, paint);
                    a++;
                }
                if(t==9)
                    canvas.drawBitmap(blue_brick,x,y,paint);
                if (t==3)
                    canvas.drawBitmap(box,x,y,paint);
                if (t==6)
                    canvas.drawBitmap(food, x, y, paint);
                if (t==2)
                    canvas.drawBitmap(person,x,y,paint);
                if (a==4)
                    MySurfaceView.gameState=MySurfaceView.GAMEINGTHREE;
            }
    }
    public void onKeyDown(int keyCode, KeyEvent event) {

        if ((mx==2&&my==4)||(mx==3&&my==3)||(mx==3&&my==5))
            map[mx][my]=6;
        else
            map[mx][my]=9;

        if(keyCode== KeyEvent.KEYCODE_DPAD_LEFT)
        { //1墙壁 9蓝色墙壁 2主角 3箱子 5红色箱子 6食物
            int t=map[mx][my-1];
            if(t!=1){
                if (t==9||t==6){
                    my--;
                }else if (t==3||t==5){
                    int h=map[mx][my-2];
                    if (h!=1&&h!=3&&h!=5){
                        if (h==6)
                            map[mx][my-2]=5;
                        else
                            map[mx][my-2]=3;

                        my--;
                    }
                }
            }
        }

        if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT)
        {
            //1墙壁 9蓝色墙壁 2主角 3箱子 5红色箱子 6食物
            int t=map[mx][my+1];
            if(t!=1){
                if (t==9||t==6){
                    my++;
                }else if (t==3||t==5){
                    int h=map[mx][my+2];
                    if (h!=1&&h!=3&&h!=5){
                        if (h==6)
                            map[mx][my+2]=5;
                        else
                            map[mx][my+2]=3;

                        my++;
                    }
                }
            }
        }

        if(keyCode==KeyEvent.KEYCODE_DPAD_UP)
        {
            //1墙壁 9蓝色墙壁 2主角 3箱子 5红色箱子 6食物
            int t=map[mx-1][my];
            if(t!=1){
                if (t==9||t==6){
                    mx--;
                }else if (t==3||t==5){
                    int h=map[mx-2][my];
                    if (h!=1&&h!=3&&h!=5){
                        if (h==6)
                            map[mx-2][my]=5;
                        else
                            map[mx-2][my]=3;

                        mx--;
                    }
                }
            }
        }

        if(keyCode==KeyEvent.KEYCODE_DPAD_DOWN)
        {

            //1墙壁 9蓝色墙壁 2主角 3箱子 5红色箱子 6食物
            int t=map[mx+1][my];
            if(t!=1){
                if (t==9||t==6){
                    mx++;
                }else if (t==3||t==5){
                    int h=map[mx+2][my];
                    if (h!=1&&h!=3&&h!=5){
                        if (h==6)
                            map[mx+2][my]=5;
                        else
                            map[mx+2][my]=3;

                        mx++;
                    }
                }
            }
        }

        map[mx][my]=2;
    }


}
