package com.example.myapplication.customview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;

public class DrawThread extends Thread{
    private SurfaceHolder holder;
    private boolean runFlag=true;
    private Paint paint = new Paint();
    private Canvas canvas;
    private MySurfaceView mv;

    public DrawThread(MySurfaceView mv, SurfaceHolder holder){
        this.mv = mv;
        this.holder = holder;
    }

    //便于在其它类里面获取runFlag  
    public void setRunflag(boolean runFlag) {
        this.runFlag = runFlag;
    }

    //线程中的run方法  
    public void run(){
        while(runFlag){
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //获取Surfaceview上的画布对象，并且锁定  

            if(Data.pauseflag){
                continue;
            }
            try {
                canvas = holder.lockCanvas();
                //画的风格设为填充式、颜色为白色  
                paint.setStyle(Style.FILL);
                paint.setColor(Color.WHITE);
                //用长方形填充一下背景  
                canvas.drawRect(0, 0, mv.getWidth(), mv.getHeight(), paint);
                //遍历队列  
                for(int i=0; i<Data.list.size(); i++){
                    Ball e = Data.list.get(i);
                    e.draw(canvas);
                    e.move();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(canvas != null){
                    //画布对象使用完成后，必须要释放  
                    holder.unlockCanvasAndPost(canvas);
                }
            }



        }

    }

}  