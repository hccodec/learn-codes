package com.example.bobham.myapplication;

import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.transition.Fade;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


public class MainActivity extends BaseActivity {
    android.support.v7.widget.Toolbar tb;
    SharedPreferences sp;
    FrameLayout layout;
    FrameLayout.LayoutParams params;
    Button btn;
    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        super.onCreate(savedInstanceState);
        // TODO: 2018/8/27 全屏4
        getWindow().setEnterTransition(new Fade().setDuration(1000));
        getWindow().setExitTransition(new Fade().setDuration(1000));
        setContentView(R.layout.activity_main);
        init();

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mNavigationView = findViewById(R.id.navigationView);
        mNavigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("mNavigationView", "head is clicked!");
            }
        });
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.favorite:
                        Log.i("mNavigationView", "收藏 is clicked!");
                        break;
                    case R.id.wallet:
                        Log.i("mNavigationView", "钱包 is clicked!");
                        break;
                    case R.id.photo:
                        Log.i("mNavigationView", "相册 is clicked!");
                        break;
                    case R.id.file:
                        Log.i("mNavigationView", "文件 is clicked!");
                        break;
                    case R.id.about:
                        Log.i("mNavigationView", "关于 is clicked!");
                        break;
                    case R.id.share:
                        Log.i("mNavigationView", "分享 is clicked!");
                        break;
                    case R.id.setting:
                        Log.i("mNavigationView", "设置 is clicked!");
                        break;
                    case R.id.exit:
                        alert();
                        break;
                }
                mDrawerLayout.closeDrawer(mNavigationView);
                return true;
            }
        });

    }

    public void animInit(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(btn, "rotation", 0f, 360f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(btn, "scaleX", 1f, 0.5f, 1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(btn, "scaleY", 1f, 0.5f, 1f);
        ObjectAnimator[] a = {animator, animator1, animator2};
        for (int i = 0; i < 3; i++) {
            a[i].setDuration(1000);
            a[i].start();
        }
    }

    public void init() {
        btn = this.findViewById(R.id.btn);
        tb = findViewById(R.id.tb);
        setSupportActionBar(tb);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("认证界面");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setSubtitle("根据提示开发");
            getSupportActionBar().setHomeAsUpIndicator(getDrawable(R.drawable.ic_menu_black_24dp));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        mDrawerLayout.openDrawer(mNavigationView);
        return super.onSupportNavigateUp();
    }

    //状态栏按钮设置点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                recreate();
                showMessage(getString(R.string.refresh), this);
                // TODO: 2018/8/30 刷新
                break;
            case R.id.action_settings:
                showMessage(getString(R.string.settings), this);
                break;
            case R.id.action_exit:
                showMessage(getString(R.string.exit), this);
                alert();
                break;
        }
        return false;
    }

    // 选项菜单被关闭事件，菜单被关闭有三种情形，menu按钮被再次点击、back按钮被点击或者用户选择了某一个菜单项
    @Override
    public void onOptionsMenuClosed(Menu menu) {
        showMessage("选项菜单关闭了", this);
    }

    // 菜单被显示之前的事件
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d("BaoJia", "选项菜单显示之前onPrepareOptionsMenu方法会被调用，你可以用此方法来根据当时的情况调整菜单");
        // 如果返回false，此方法就把用户点击menu的动作给消费了，onCreateOptionsMenu方法将不会被调用
        return true;
    }
}