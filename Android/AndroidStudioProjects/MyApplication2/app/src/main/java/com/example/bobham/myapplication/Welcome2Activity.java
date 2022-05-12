package com.example.bobham.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import static android.util.Pair.create;


public class Welcome2Activity extends Activity {

    Button btn1;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        tle.setTypeface(Typeface.createFromAsset(getAssets(), "font/myFont.otf"), Typeface.NORMAL);
        copyright.setTypeface(Typeface.createFromAsset(getAssets(), "font/simFang.ttf"));
    }

    protected void ListenerSetting() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share_more(v);
                finish();
            }
        });
    }


    //共享多个多个元素
    public void share_more(View v){

        Intent intent = new Intent(this,LoginActivity.class);

        ActivityOptions options;
        options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.<View, String>create(btn1,"share"),
                Pair.create(v,"share1"));

        startActivity(intent,options.toBundle());
    }

}
