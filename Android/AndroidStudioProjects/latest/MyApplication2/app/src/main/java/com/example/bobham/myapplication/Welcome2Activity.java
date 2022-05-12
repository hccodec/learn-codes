package com.example.bobham.myapplication;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.util.Pair.create;
import static android.widget.Toast.LENGTH_SHORT;


public class Welcome2Activity extends BaseActivity {

    Button btn1;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);
        init();
        ListenerSetting();
    }

    protected void init() {
        //指定id
        btn1 = this.findViewById(R.id.login_button);
        TextView tle = this.findViewById(R.id.title);
        TextView copyright = this.findViewById(R.id.copyright);
        //字体设置
        btn1.setTypeface(Typeface.createFromAsset(getAssets(), "font/myFont.otf"));
        tle.setTypeface(Typeface.createFromAsset(getAssets(), "font/myFont.otf"), 1);
        copyright.setTypeface(Typeface.createFromAsset(getAssets(), "font/simFang.ttf"));
    }

    protected void ListenerSetting() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share_more(v);
                new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        finish();
                        return false;
                    }
                }).sendEmptyMessageDelayed(0, 1500);
            }
        });
    }

    //共享元素转场动画
    public void share_more(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, v, "share").toBundle());
    }

}
