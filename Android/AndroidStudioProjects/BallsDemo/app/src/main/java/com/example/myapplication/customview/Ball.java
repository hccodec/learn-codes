package com.example.myapplication.customview;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball extends Entity{

    private float x, y, vx, vy, radius;

    private MySurfaceView mv;


    public Ball(MySurfaceView mv){
        this.mv = mv;

        inint();
    }

    //初始化一些参数
    public void inint(){
        //创建随机对象，让小球的移动速度与半径随机生成
        Random r = new Random();
        vx = r.nextInt(5)+5;
        vy = r.nextInt(5)+5;
        radius = r.nextInt(20)+20;
    }

    //画小球的方法
    public void draw(Canvas c){
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        c.drawCircle(x, y, radius, paint);
    }

    //小球移动的方法
    public void move(){

        x+=vx;
        y+=vy;
        if(x<=0){
            vx=5;
        }if(x>=mv.getWidth()-radius){
            vx=-5;
        }if(y<=0){
            vy=5;
        }if(y>=mv.getHeight()-radius){
            vy=-5;
        }
    }

}