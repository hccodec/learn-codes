package com.physswjtu.srtp2019;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.physswjtu.srtp2019.Utils.HttpUtil;
import com.physswjtu.srtp2019.Utils.MyTask;
import com.physswjtu.srtp2019.Utils.SoundUtil;
import com.physswjtu.srtp2019.Utils.StatusBarUtil;
import com.physswjtu.srtp2019.data.DrawerAdapter;
import com.physswjtu.srtp2019.data.SRTPBean;
import com.physswjtu.srtp2019.data.Settings.Settings;
import com.physswjtu.srtp2019.ui.fragment.CourseSelectFragment;
import com.physswjtu.srtp2019.ui.fragment.FragAdapter;
import com.physswjtu.srtp2019.widgets.Dialogs;
import com.physswjtu.srtp2019.widgets.RoundImageView;
import com.physswjtu.srtp2019.widgets.TextView;
import com.physswjtu.srtp2019.widgets.Toast;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.physswjtu.srtp2019.Utils.BitmapUtil.drawable2Bitmap;
import static com.physswjtu.srtp2019.Utils.CameraUtil.REQUEST_CODE_CAPTURE;
import static com.physswjtu.srtp2019.Utils.CameraUtil.REQUEST_CODE_CUSTOM_CAMERA;
import static com.physswjtu.srtp2019.Utils.CameraUtil.REQUEST_CODE_SYSTEM_CAMERA;
import static com.physswjtu.srtp2019.Utils.CameraUtil.getTempUri;
import static com.physswjtu.srtp2019.Utils.CameraUtil.routeToCrop;
import static com.physswjtu.srtp2019.Utils.CameraUtil.startCaptureActivity;
import static com.physswjtu.srtp2019.Utils.CameraUtil.startCustomCamera;
import static com.physswjtu.srtp2019.Utils.CameraUtil.startSystemCamera;
import static com.physswjtu.srtp2019.Utils.FileUtil.getSpecificFilePath;
import static com.physswjtu.srtp2019.Utils.HttpUtil.ERROR;
import static com.physswjtu.srtp2019.Utils.OtherUtil.isPermissionsGranted;
import static com.physswjtu.srtp2019.Utils.OtherUtil.setStatusBar;
import static com.physswjtu.srtp2019.Utils.SoundUtil.ALERT;


public class MainActivity extends BaseActivity implements DrawerAdapter.OnItemClickListener {


    @BindView(R.id.home)
    RoundImageView home;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView rvDrawer;
    @BindView(R.id.temp_view)
    ConstraintLayout rvDrawer1;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.my_title)
    TextView myTitle;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    int j = 1;
    static Dialog dialog;
    FragAdapter adapter;
    static DrawerAdapter drawerAdapter;
    HttpUtil httpUtil = new HttpUtil();
    private final Handler handler = new mHandler(this);
    int[] radioGroupIds = new int[]{R.id.radio1, R.id.radio2, R.id.radio3, R.id.radio4};


    private static class mHandler extends Handler {
        private final WeakReference<MainActivity> mTarget;////////////////////

        mHandler(MainActivity activity) {
            mTarget = new WeakReference<>(activity);/////////
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            MainActivity activity = mTarget.get();//////////////////////
            Log.d("hbj", "成功传到MainActivity的handleMessage,msg.what是" + msg.what);
            if (dialog != null) {
                dialog.dismiss();
            } else Log.d("hbj", "没有");
            if (msg.what == 10) {
                SoundUtil.ding(activity, ALERT);
                //activity.rvDrawer.setBackground(new BitmapDrawable(activity.getResources(), OtherUtil.blurImageBitmap(activity, HttpUtil.getBitmap())));
                drawerAdapter.setIcon(HttpUtil.getBitmap());
                ((CourseSelectFragment) activity.adapter.getItem(1)).update(HttpUtil.getBitmap(false));
            } else if (msg.what == ERROR) {
                String customResponse = msg.getData().getString("customResponse");
                Toast.makeText(activity, customResponse, Toast.LENGTH_SHORT).show();
            }
            drawerAdapter.notifyItemChanged(0, "hbj");
            //drawerAdapter.itemMove(1, 4);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setStatusBar(getWindow(), true);
        StatusBarUtil.setStatusTextColor(false, this);
        setSupportActionBar(toolbar);
        initBottomNavigationBar();
        initFragment();
        initView();
        closeReceiver = new CloseActivityReceiver();
        IntentFilter intentFilter = new IntentFilter("con.lcry.close.activity");
        registerReceiver(closeReceiver, intentFilter);
    }

    CloseActivityReceiver closeReceiver;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(closeReceiver);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        initDrawerLayout();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
    }

    // 拿到照片
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("hbj", "MainActivity: onActivityResult(): " + "requestCode:" + requestCode
                + "    resultCode: " + (resultCode == RESULT_OK ? "RESULT_OK" : (resultCode == RESULT_CANCELED ? "RESULT_CANCELED" : requestCode))
                + "      data: " + data);
        if (requestCode == 2) finish();
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CUSTOM_CAMERA)
                routeToCrop(this, data, REQUEST_CODE_CUSTOM_CAMERA);
            else if (requestCode == REQUEST_CODE_SYSTEM_CAMERA)
                routeToCrop(this, data, REQUEST_CODE_SYSTEM_CAMERA);
            else if (requestCode == REQUEST_CODE_CAPTURE) {
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) return;
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED)
                        Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            } else if (requestCode == 8)
                new Thread() {
                    public void run() {
                        try {
                            //（新文件）
                            File file2 = new File(getSpecificFilePath(MainActivity.this, null) + "/hbj2-" + System.currentTimeMillis() + ".jpg");
                            Log.d("hbj", "MainActivity: " + "onActivityResult(): " + "新文件名是  " + file2.getPath());
                            OutputStream out = new FileOutputStream(file2.getPath());
                            //压缩文件，返回结果
                            Log.d("hbj", "MainActivity: " + "onActivityResult(): " + "getTempUri(): " + getTempUri().getPath());
                            Bitmap photo1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(getTempUri()));
                            // photo1 = Bitmap.createScaledBitmap(photo1, 800, 900, true);
                            //Bitmap photo1 = data.getExtras().getParcelable("data");
                            if (photo1.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
                                out.close();
                                Log.d("hbj", "MainActivity: " + "onActivityResult(): " + "文件正确关闭");
                            } else
                                Log.e("hbj", "MainActivity: " + "onActivityResult(): " + "文件未正确关闭");
                            runOnUiThread(() -> {
//                                ((CourseSelectFragment) adapter.getItem(1)).update(photo1);
//                                home.setImageBitmap(photo1);
//                                Log.d("hbj", "MainActivity: " + "onActivityResult(): " + "进行到最后一步了");
                            });
                            //FileUtil.removeFile(CameraUtil.getTemp());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
        }
    }

    /**
     * 处理请求权限的响应，当用户对请求权限的dialog做出响应之后,系统会回调该函数(Activity或者Fragment中重写)
     *
     * @param requestCode  申请权限对应的requestCode
     * @param permissions  权限列表
     * @param grantResults 权限列表对应的返回值，判断permissions里面的每个权限是否申请成功
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("hbj", requestCode + "    " + permissions[0] + "      " + grantResults[0]);
        boolean result = true;
        for (int grantResult : grantResults)
            if (grantResult != 0) {
                result = false;
                break;
            }
        if (result)
            switch (requestCode) {
                case REQUEST_CODE_CUSTOM_CAMERA:
                    startCustomCamera(this);
                    break;
                case REQUEST_CODE_SYSTEM_CAMERA:
                    startSystemCamera(this);
                    break;
                case REQUEST_CODE_CAPTURE:
                    startCaptureActivity(this);
                    break;
            }

    }

    @Override
    protected void onPause() {
        Log.d("hbj", "MainActivity: " + "onPause()");
        if (dialog != null) {
            dialog.dismiss();
        }
        super.onPause();
    }

    private void initView() {
        dialog = new Dialogs.loading(MainActivity.this, "刷新中");
    }

    private void initBottomNavigationBar() {
        radioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }

    @Override
    public void itemClick(DrawerAdapter.DrawerItemNormal drawerItemNormal) {
        switch (drawerItemNormal.titleRes) {
            case R.string.action_profile://修改密码
                Dialogs.SRTPAlertDialog.BuilderEdit3 builder = new Dialogs.SRTPAlertDialog.BuilderEdit3(this);
                final Dialogs.SRTPAlertDialog dialog = builder.create();
                builder.getEditText(1).setHint("原密码");
                builder.getEditText(2).setHint("新密码");
                builder.getEditText(3).setHint("确认新密码");
                builder.setTitle("修改密码").setIcon(R.string.fa_lock_before).setMessage("请在下方修改密码").setButton(getString(android.R.string.ok), v -> {
                    if (builder.getEditText(2).getText().toString().equals(builder.getEditText(3).getText().toString()))
                        try {
                            new MyTask.changePasswd(this).execute(new SRTPBean.changePasswordData(
                                    Settings.getString(Settings.KEY_USER_ID, null),
                                    builder.getEditText(1).getText().toString(),
                                    builder.getEditText(2).getText().toString()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    else
                        android.widget.Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                });
                dialog.show();
                break;
            case R.string.action_contact_teacher://联系教师
                Toast.makeText(this, "未来的联系教师，在这里你可以联系教师……", Toast.LENGTH_SHORT).show();
                break;
            case R.string.action_test:// FIXME: 2019/7/29 调试
                drawerLayout.closeDrawers();
                startActivity(new Intent(this, TestActivity.class));
                break;
            case R.string.action_video://视频
                drawerLayout.closeDrawers();
                startActivity(new Intent(this, VideoActivity.class));
                break;
            case R.string.action_live://直播
                Toast.makeText(this, "适配 EasyDarwin 中", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(this, LiveActivity.class));
                break;
            case R.string.action_create_qr://生成码
                drawerLayout.closeDrawers();
                startActivity(new Intent(this, CreateQRActivity.class));
                break;
            case R.string.action_scan_qr://扫码
                if (isPermissionsGranted(this, REQUEST_CODE_CAPTURE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    drawerLayout.closeDrawers();
                    startCaptureActivity(this);
                }
                break;
            case R.string.action_built_in_browser://内置浏览器
                drawerLayout.closeDrawers();
                startActivity(new Intent(this, BrowserActivity.class));
                break;
        }
    }

    @Override
    public void itemClick(View v, DrawerAdapter.DrawerItemHeader drawerItemHeader) {
        switch (v.getId()) {
            case R.id.bg:
                String[] s = {"http://news.qingdaonews.com/images/attachement/jpg/site1/20170720/48d224f8c5531ada49cc0a.jpg",
                        "http://a1.att.hudong.com/14/15/19300534771702135956159171804.jpg"};
                try {
                    httpUtil.get(s[j], handler, 10);
                    if (dialog != null) dialog.show();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                drawerAdapter.setIcon(drawable2Bitmap(this, R.drawable.timg));
                if (j == s.length - 1) j = 0;
                else j++;
                break;
        }
    }

    @OnClick({R.id.home, R.id.toolbar, R.id.drawer_layout, R.id.action_settings, R.id.action_signout, R.id.action_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home:
                drawerLayout.openDrawer(rvDrawer1);
                break;
            case R.id.imageview:
                if (isPermissionsGranted(this, REQUEST_CODE_CAPTURE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    startCaptureActivity(this);
                break;
            case R.id.action_settings:
                startSettingsActivity(this);
                break;
            case R.id.action_signout:
                signOut();
                break;
            case R.id.action_exit:
                exit(this);
                break;
        }
    }

    // todo 设置页面
    public static void startSettingsActivity(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
        /*
        Toast.makeText(context, "暂时没有可设置的选项emmm", Toast.LENGTH_SHORT).show();*/
    }

    public void signOut() {
        Settings.deletePreference(Settings.KEY_USER_ID, false);
        Settings.deletePreference(Settings.KEY_USER_TYPE, false);
        new MyTask.logout(this).execute();
    }

    // todo 退出程序
    public static void exit(Context context) {
        //context.startActivity(new Intent(context, LoginActivity.class));
        ((Activity) context).finish();
    }

    void initFragment() {
        adapter = new FragAdapter(this, getSupportFragmentManager());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                myTitle.setText(adapter.getPageTitle(position));
                viewPager.setCurrentItem(position);
                radioGroup.check(radioGroupIds[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(adapter);
    }

    void initDrawerLayout() {
        drawerAdapter = new DrawerAdapter(this);
        drawerAdapter.init(Settings.getBoolean(Settings.KEY_IS_IN_DEVELOPER_MODE, false));
        drawerAdapter.setOnItemClickListener(this);
        rvDrawer.setLayoutManager(new LinearLayoutManager(this));
        rvDrawer.setAdapter(drawerAdapter);
        drawerAdapter.setUserName(Settings.getString(Settings.KEY_USER_ID, null));
        drawerAdapter.notifyItemChanged(0, "hbj");
    }

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = (group, checkedId) -> {
        switch (checkedId) {
            case R.id.radio1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.radio2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.radio3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.radio4:
                viewPager.setCurrentItem(3);
                break;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mHandler.sendEmptyMessage(MSG_HOME);
            return true;
        } else return super.onKeyDown(keyCode, event);
    }
}
