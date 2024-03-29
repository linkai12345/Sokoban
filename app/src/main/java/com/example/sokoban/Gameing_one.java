package com.example.sokoban;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;

public class Gameing_one {

    private Bitmap blue_brick,brick,red_box,person,box,food,one;
    int x,y,width,height,offset_x,offset_y;
    int mx=3,my=1;
    //1墙壁 9蓝色墙壁 2主角 3箱子 5红色箱子 6食物 测试更改
    int map[][]={
            {1,1,1,1,1,1,1,1},
            {1,9,9,1,9,9,9,1},
            {1,9,3,6,6,3,9,1},
            {1,2,3,6,5,9,1,1},
            {1,9,3,6,6,3,9,1},
            {1,9,9,1,9,9,9,1},
            {1,1,1,1,1,1,1,1}
    };
    public Gameing_one(Bitmap box,Bitmap blue_brick,Bitmap brick,Bitmap red_box,Bitmap person,Bitmap food,Bitmap one){
            this.blue_brick=blue_brick;
            this.brick=brick;
            this.red_box=red_box;
            this.person=person;
            this.box=box;
            this.food=food;
            this.one=one;
        int screenHeight=MySurfaceView.height;
        int screenWidth=MySurfaceView.width;
        width=brick.getWidth();
        height=brick.getHeight();
        offset_x=(screenWidth-map[0].length*width)/2;
        offset_y=(screenHeight-map.length*width)/2;
    }


    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(one,0,0,paint);
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

                if (a==6)
                   MySurfaceView.gameState=MySurfaceView.GAMEINGTWO;
            }
    }

    public void onKeyDown(int keyCode, KeyEvent event) {

        if ((mx==2&&(my==3||my==4))||(mx==3&&(my==3||my==4))||(mx==4&&(my==3||my==4)))
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
