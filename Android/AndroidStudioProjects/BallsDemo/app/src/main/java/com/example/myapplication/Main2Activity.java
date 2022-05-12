package com.example.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.customview.Ball;
import com.example.myapplication.customview.Data;
import com.example.myapplication.customview.MySurfaceView;

public class Main2Activity extends AppCompatActivity {

    private MySurfaceView mv;


    protected void onCreate(Bundle savedInstanceState) {
        //调用父类中的onCreate方法
        super.onCreate(savedInstanceState);
        //设置界面图层
        setContentView(R.layout.activity_main2);

        //根据id地址找到mySurfaceView对象
        mv=(MySurfaceView)findViewById(R.id.mySurfaceView1);

    }

    //实现给按钮添加监听器的方法
    public void found(View v){
        //根据id地址判断那一个按钮
        if(v.getId() == R.id.button1){
            System.out.println("======");
            mv.addBall();
        }
        if(v.getId() == R.id.button2){
            Data.setPauseflag(true);
        }
        if(v.getId() == R.id.button3){
            Data.setPauseflag(false);
        }
        if(v.getId() == R.id.button4){
            while(!Data.list.isEmpty()){
                Ball ba = Data.list.remove(0);
            }
        }
    }
}