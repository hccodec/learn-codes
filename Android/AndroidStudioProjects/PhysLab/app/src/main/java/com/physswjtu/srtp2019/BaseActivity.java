package com.physswjtu.srtp2019;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.physswjtu.srtp2019.Utils.HTTPTasks;
import com.physswjtu.srtp2019.widgets.Toast;

import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_PIC_SIZE_HEIGHT;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_PIC_SIZE_WIDTH;
import static com.physswjtu.srtp2019.data.Settings.Settings.putInt;

public class BaseActivity extends AppCompatActivity {

    static final int MSG_EXIT = 1;
    private static final int MSG_WAIT = 2;
    static final int MSG_HOME = 3;
    private static final long EXIT_DELAY_TIME = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        putInt(KEY_PIC_SIZE_WIDTH, 1200, false);
        putInt(KEY_PIC_SIZE_HEIGHT, 1600, false);
        HTTPTasks.initHttpTasks(this);
    }

    protected Handler mHandler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_HOME:
                    if (mHandler.hasMessages(MSG_WAIT)) {
                        //返回至桌面
                        Intent intent = new Intent();
                        // 为Intent设置Action、Category属性
                        intent.setAction(Intent.ACTION_MAIN);// "android.intent.action.MAIN"
                        intent.addCategory(Intent.CATEGORY_HOME); //"android.intent.category.HOME"
                        startActivity(intent);
                    } else {
                        Toast t = Toast.makeText(BaseActivity.this, "再按一次返回至桌面", Toast.LENGTH_SHORT);
                        t.setGravity(Gravity.CENTER, 0, 0);
                        t.show();
                        mHandler.sendEmptyMessageDelayed(MSG_WAIT, EXIT_DELAY_TIME);
                    }
                    break;
                case MSG_EXIT:
                    if (mHandler.hasMessages(MSG_WAIT)) {
                        finish();
                    } else {
                        Toast t = Toast.makeText(BaseActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT);
                        t.setGravity(Gravity.CENTER, 0, 0);
                        t.show();
                        mHandler.sendEmptyMessageDelayed(MSG_WAIT, EXIT_DELAY_TIME);
                    }
                    break;
                case MSG_WAIT:
                    break;
            }
            return false;
        }
    });

}