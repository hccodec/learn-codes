package com.physswjtu.srtp2019;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.physswjtu.srtp2019.Utils.MyTask;
import com.physswjtu.srtp2019.Utils.OtherUtil;
import com.physswjtu.srtp2019.Utils.SoundUtil;
import com.physswjtu.srtp2019.data.SRTPBean;
import com.physswjtu.srtp2019.data.Settings.Settings;
import com.physswjtu.srtp2019.widgets.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.physswjtu.srtp2019.Utils.BitmapUtil.getImageFromAssetsFile;
import static com.physswjtu.srtp2019.Utils.BitmapUtil.tintBitmap;
import static com.physswjtu.srtp2019.Utils.OtherUtil.setFullScreen;
import static com.physswjtu.srtp2019.Utils.SoundUtil.BUTTON;
import static com.physswjtu.srtp2019.Utils.SoundUtil.NOTIFICATION2;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.welcome_pic)
    ImageView welcomeBtn;
    @BindView(R.id.welcome_content)
    RelativeLayout welcomeContent;
    @BindView(R.id.welcome_txt)
    TextView welcomeTxt;
    @BindView(R.id.welcome_txt_description)
    TextView welcomeDescription;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.fullscreen_content)
    TextView appName;
    @BindView(R.id.leigo_button)
    Button btn0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow());
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initView();
    }

    void initView() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/DejaVuSans-ExtraLight.ttf");
        final Intent intent = new Intent(this, LoginActivity.class);
        Bitmap img = getImageFromAssetsFile(this, "pics/timg.jpeg");
        Bitmap logoImg = getImageFromAssetsFile(this, "pics/logo.png");

        appName.setTypeface(tf);
        welcomeTxt.setText("@新浪微博 | 人类首次发现黑洞");
        welcomeDescription.setText("事件视界望远镜合作组织供图");
        welcomeBtn.setScaleType(ImageView.ScaleType.CENTER_CROP);
        welcomeBtn.setImageBitmap(img);
        logo.setImageBitmap(tintBitmap(logoImg, 0xFF4196E1));
        welcomeTxt.setOnClickListener(v -> {
            Toast.makeText(this, "这就切换到下个页面", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        });
        //监听button事件
        welcomeContent.setOnClickListener(v -> {
            Snackbar.make(findViewById(R.id.welcome_pic), "为什么点我鸭_(:з」∠)_", Snackbar.LENGTH_SHORT).show();
            SoundUtil.ding(SplashActivity.this, BUTTON);
        });
        btn0.setOnClickListener(v -> {
            SoundUtil.ding(SplashActivity.this, NOTIFICATION2);
            OtherUtil.showBadge(getApplicationContext(), 0);
            Snackbar.make(findViewById(R.id.welcome_pic), "已清除华为桌面应用图标的未读角标（声音模式检测及声音播放测试）", Snackbar.LENGTH_SHORT).show();
        });
        Log.d("hbj", Settings.getString(Settings.KEY_USER_ID, ""));
        Log.d("hbj", Settings.getBoolean(Settings.KEY_USER_TYPE, true) ? "true" : "false");
        if (!Settings.getBoolean(Settings.KEY_IS_IN_DEVELOPER_MODE, false))
            if (Settings.getString(Settings.KEY_USER_ID, "").isEmpty()) {
                startActivity(intent);
                finish();
            } else {
                new MyTask.getRoleInformation(this, new SRTPBean.Role(Settings.getBoolean(Settings.KEY_USER_TYPE, true))).execute(Settings.getString(Settings.KEY_USER_ID, null));
            }
    }
}
