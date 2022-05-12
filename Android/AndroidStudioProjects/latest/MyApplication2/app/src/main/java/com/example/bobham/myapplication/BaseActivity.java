/**
 * @date on 23:14 2018/8/29
 * @author Bob Ham (韩宝佳)
 * @email 846972585@qq.com
 * @version 1.0
 * @description 自定义BaseActivity
 */
package com.example.bobham.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

public class BaseActivity extends AppCompatActivity {
    static int i = 0; //全局变量用于判断是否退出

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        //hideStatusBar();
        hideStatusBarNavigationBar();
    }


    //只透明状态栏
    private void hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    //状态栏、导航栏都透明
    private void hideStatusBarNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }
    }

    @SuppressLint("WrongConstant")
    public void showMessage(String _s, Context context) {
        LayoutInflater inflater = getLayoutInflater();
        //根据指定的布局文件创建一个具有层级关系的View对象,第二个参数为View对象的根节点,即LinearLayout的ID
        View layout = inflater.inflate(R.layout.toast_custom, (ViewGroup) findViewById(R.id.toast_layout_root));
        //查找ImageView控件,注意是在layout中查找
        ImageView image = layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_check_green_24dp);
        TextView text = layout.findViewById(R.id.text);
        text.setText(_s);
        Toast toast = new Toast(context);
        //设置Toast的位置
        toast.setDuration(1000);
        //让Toast显示为我们自定义的样子
        toast.setView(layout);
        toast.show();
    }

    public void alert() {

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_error_red_24dp)
                .setTitle(R.string.hint_dialog)
                .setMessage(R.string.exit_dialog)
                .setNegativeButton(R.string.cancel_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton(R.string.confirm_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

    public void onBackPressed() {
        if (i == 0) {
            i++;
            showMessage(getString(R.string.exit_message), this);
            new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    i--;
                    return false;
                }
            }).sendEmptyMessageDelayed(0, 1000);
        } else {
            // TODO: 2018/8/29 完全退出应用程序
            finish();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View view = getCurrentFocus();
                UtilHelpers.hideKeyboard(ev, view, BaseActivity.this);//调用方法判断是否需要隐藏键盘
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == 108 && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                    Log.d("BaoJia", "succeeded");
                } catch (Exception e) {
                    Log.d("BaoJia", e.toString());
                }
            }
        }
        Log.d("BaoJia", String.format("%s",featureId));
        return super.onMenuOpened(featureId, menu);
    }
}
