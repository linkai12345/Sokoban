package com.example.sokoban;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements Callback, Runnable {

	private Bitmap box,person,cover,button,button_press,red_box,game_win,blue_brick,brick,food,one,two,three;
	SurfaceHolder holder;
	Paint paint;
	public static int width,height;//屏幕的宽和高
	boolean flag=false;
	//定义游戏状态常量
	public static final int GAME_MENU = 0;//游戏菜单
	public static final int GAMEINGONE= 1;//游戏第一关
	public static final int GAMEINGTWO= 2;//游戏第二关
	public static final int GAMEINGTHREE= 3;//游戏第三关
	public static final int GAME_WIN = 4;//游戏胜利
	//当前游戏状态(默认初始在游戏菜单界面)
	public static int gameState = GAME_MENU;
	private GameMenu gameMenu;//首页菜单
	private Game_Win gamewin;//游戏结束界面
	private Gameing_two gameing_two;//游戏第二关
	private Gameing_one gameing_one;//游戏第一关
	private Gameing_three gameing_three;//游戏第三关
	public MySurfaceView(Context context) {
		super(context);
		holder=this.getHolder();
		holder.addCallback(this);
		paint=new Paint();
		paint.setColor(Color.BLACK);
		paint.setAntiAlias(true);
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setKeepScreenOn(true);
		this.setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		width=this.getWidth();
		height=this.getHeight();
		initGame();
		flag=true;
		new Thread(this).start();
	}

	private void initGame(){

		if (gameState==GAME_MENU) {

			person = BitmapFactory.decodeResource(this.getResources(), R.drawable.person);
			box = BitmapFactory.decodeResource(this.getResources(), R.drawable.box);
			cover = BitmapFactory.decodeResource(this.getResources(), R.drawable.cover);
			button = BitmapFactory.decodeResource(this.getResources(), R.drawable.button);
			button_press = BitmapFactory.decodeResource(this.getResources(), R.drawable.button_press);
			red_box=BitmapFactory.decodeResource(this.getResources(), R.drawable.red_box);
			game_win=BitmapFactory.decodeResource(this.getResources(), R.drawable.game_win);
			blue_brick=BitmapFactory.decodeResource(this.getResources(), R.drawable.blue_brick);
			brick=BitmapFactory.decodeResource(this.getResources(), R.drawable.brick);
			food=BitmapFactory.decodeResource(this.getResources(), R.drawable.food);
			one=BitmapFactory.decodeResource(this.getResources(), R.drawable.one);
			two=BitmapFactory.decodeResource(this.getResources(), R.drawable.two);
			three=BitmapFactory.decodeResource(this.getResources(), R.drawable.three);
			gameMenu=new GameMenu(cover,button,button_press);
			gameing_one=new Gameing_one(box,blue_brick,brick,red_box,person,food,one);
			gameing_two=new Gameing_two(box,blue_brick,brick,red_box,person,food,two);
			gameing_three=new Gameing_three(box,blue_brick,brick,red_box,person,food,three);
			gamewin=new Game_Win(game_win);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		flag=false;
	}
	
	public void myDraw()
	{
		Canvas canvas=null;
		try {
			canvas=holder.lockCanvas();
			if(canvas!=null)
			{
				//绘图
				canvas.drawColor(Color.BLACK);

				switch (gameState) {
					case GAME_MENU:
						//菜单的绘图函数
						gameMenu.draw(canvas, paint);
						break;
					case GAMEINGONE:
						gameing_one.draw(canvas,paint);
						break;
					case GAMEINGTWO:
						gameing_two.draw(canvas,paint);
						break;
					case GAMEINGTHREE:
						gameing_three.draw(canvas,paint);
						break;
					case GAME_WIN:
						gamewin.draw(canvas,paint);
						break;
				}

			}
		} catch (Exception e) {
		}
		finally
		{
			if(canvas!=null)
				holder.unlockCanvasAndPost(canvas);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (gameState) {
			case GAME_MENU:
				//菜单的触屏事件处理
				gameMenu.onTouchEvent(event);
				break;
			case GAMEINGONE:
				break;
			case GAMEINGTWO:
				break;
			case GAMEINGTHREE:
				break;
			case GAME_WIN:
				break;
		}

		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		//处理back返回按键
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//游戏胜利，失败进行时都默认返回菜单
			if (gameState == GAMEINGONE || gameState == GAMEINGTWO  || gameState == GAMEINGTHREE  || gameState == GAME_WIN ) {
				gameState = GAME_MENU;
				//重置游戏
				initGame();
			} else if (gameState == GAME_MENU) {
				//当前游戏状态在菜单界面，默认返回按键退出游戏
				MainActivity.instance.finish();
				System.exit(0);
			}
			//表示此按键已处理，不再交给系统处理
			//从而避免游戏被切入后台
			return true;
		}

		switch (gameState) {
			case GAME_MENU:
				break;
			case GAMEINGONE:
				gameing_one.onKeyDown(keyCode,event);
				break;
			case GAMEINGTWO:
				gameing_two.onKeyDown(keyCode,event);
				break;
			case GAMEINGTHREE:
				gameing_three.onKeyDown(keyCode,event);
				break;
			case GAME_WIN:
				break;

		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void run() {
		while(flag)
		{
			long start=System.currentTimeMillis();
			myDraw();
			long end=System.currentTimeMillis();
			try {
				if(end-start<50)
					Thread.sleep(50-(end-start));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}



}
