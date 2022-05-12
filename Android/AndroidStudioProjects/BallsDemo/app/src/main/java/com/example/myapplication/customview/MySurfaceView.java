package com.example.myapplication.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements Callback{

    private SurfaceHolder holder;
    private DrawThread dt;

    //带一个参数的构造方法，并调用带两个参数的构造方法
    public MySurfaceView(Context context) {
        this(context, null);
    }

    //带两个参数的构造方法，并调用带三个参数的构造方法
    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    //带三个参数的构造方法，并继承父类中的方法
    public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //获取画布
        holder = this.getHolder();
        //回调方法
        holder.addCallback(this);

//      setFocusable(true);
    }

    //当surfaceview被创建时调用
    public void surfaceCreated(SurfaceHolder holder) {

        System.out.println("surfaceCreated");

        //创建线程对象，并开始线程
        dt = new DrawThread(this,holder);
        dt.start();

    }

    //当横竖屏幕切换时调用
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        System.out.println("surfaceChanged");

    }

    //当surfaceview 被销毁时结束线程
    public void surfaceDestroyed(SurfaceHolder holder) {
        System.out.println("surfaceDestroyed");
        //结束线程
        dt.setRunflag(false);
    }

    //添加小球的方法
    public void addBall(){
        Ball ball = new Ball(this);
        Data.list.add(ball);

    }

}