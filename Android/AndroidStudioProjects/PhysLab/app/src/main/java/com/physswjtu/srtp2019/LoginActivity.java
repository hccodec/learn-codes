package com.physswjtu.srtp2019;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.physswjtu.srtp2019.Utils.HttpUtil;
import com.physswjtu.srtp2019.Utils.OtherUtil;
import com.physswjtu.srtp2019.Utils.authentication;
import com.physswjtu.srtp2019.data.Settings.Settings;
import com.physswjtu.srtp2019.widgets.Dialogs;
import com.physswjtu.srtp2019.widgets.TextView;
import com.physswjtu.srtp2019.widgets.Toast;

import java.lang.ref.WeakReference;
import java.text.MessageFormat;

import static com.physswjtu.srtp2019.Utils.SoundUtil.ALERT;
import static com.physswjtu.srtp2019.Utils.SoundUtil.NOTIFICATION;
import static com.physswjtu.srtp2019.Utils.SoundUtil.ding;


public class LoginActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {
    EditText username;
    EditText password;
    TextView usernameClear;
    TextView passwordClear;
    TextView showPasswd;
    TextView loginTypeView;
    PopupWindow mPopWindow;
    Animation animation;
    TextView logBtn;
    Boolean isShownPasswd;
    static boolean type;
    MyReceiver receiver;
    static Dialog dialog;

    @SuppressLint({"CutPasteId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OtherUtil.setStatusBar(getWindow(), true);
        getWindow().setWindowAnimations(R.style.mDialogAnim);
        setContentView(R.layout.activity_login);
        init();
        //initBroadcastReceiver();
    }

    void init() {
        if (getIntent().getExtras() == null) type = true;
        else type = (boolean) getIntent().getExtras().getSerializable("type");
        logBtn = findViewById(R.id.loginbtn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.passwd);
        usernameClear = findViewById(R.id.username_clear);
        passwordClear = findViewById(R.id.passwd_clear);
        isShownPasswd = false;
        showPasswd = findViewById(R.id.show_passwd_icon);
        loginTypeView = findViewById(R.id.login_type);
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);

        ((TextView) this.findViewById(R.id.title)).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "font/DejaVuSans-ExtraLight.ttf"));
        this.findViewById(R.id.title).setOnClickListener(v -> {
            username.setText("2017113903");
            password.setText("qweqweqwe");
            usernameClear.setVisibility(View.INVISIBLE);
            passwordClear.setVisibility(View.INVISIBLE);
            showPasswd.setVisibility(View.INVISIBLE);
        });
        loginTypeView.setText(type ? "学生" : "教师");
        loginTypeView.setOnClickListener(v -> {
            if (mPopWindow != null && mPopWindow.isShowing()) mPopWindow.dismiss();
            else showPopupWindow();
        });
        username.setHint(type ? getString(R.string.hint_username_student) : getString(R.string.hint_username_teacher));
        username.setOnFocusChangeListener(this);
        password.setOnFocusChangeListener(this);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                usernameClear.setVisibility(username.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (getCurrentFocus() == username && username.getText().toString().length() == 10) {
                    password.requestFocus();
                }
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordClear.setVisibility(password.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
                showPasswd.setVisibility(password.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        showPasswd.setVisibility(View.GONE);
        usernameClear.setVisibility(View.GONE);
        passwordClear.setVisibility(View.GONE);
    }

    void initBroadcastReceiver() {
        receiver = new MyReceiver();
        String action = "android.provider.Telephony.SMS_RECEIVED";
        IntentFilter intentFilter = new IntentFilter(action);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(receiver);
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_usr_type, null);
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv1 = contentView.findViewById(R.id.pop_student);
        TextView tv2 = contentView.findViewById(R.id.pop_teacher);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        //相对位置 以mMenuTv为坐标,
        mPopWindow.showAsDropDown(loginTypeView);
    }

    int testCount = 10;
    int testTempCount = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test:
                startActivity(new Intent(this, TestActivity.class));
                break;
            case R.id.circle_imageview:
                if (testTempCount == testCount) {
                    testTempCount = 0;
                    Intent intent = new Intent(this, MainActivity.class);
                    Settings.putString(Settings.KEY_USER_ID, "2017113903", false);
                    Settings.putBoolean(Settings.KEY_USER_TYPE, true, false);
                    String s = MessageFormat.format("测试账号：{0}（{1}）",
                            Settings.getString(Settings.KEY_USER_ID, Settings.ERROR),
                            Settings.getBoolean(Settings.KEY_USER_TYPE, true) ? "学生" : "教师");
                    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    new Handler().postDelayed(this::finish, 1000);
//                overridePendingTransition(R.anim.scale_fade_in, R.anim.none);
                } else {
                    testTempCount++;
                    new Handler().postDelayed(() -> testTempCount = 0, 3500);
                    if (testCount - testTempCount <= testCount / 2)
                        Toast.makeText(this, MessageFormat.format("只需再点击{0}次即可直接进入登录后的页面", testCount - testTempCount), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.loginbtn:
                String id = username.getText().toString();
                String passWd = password.getText().toString();
                if (!(id.isEmpty() || passWd.isEmpty())) {
                    // TODO: 2019/7/9 login-ing
                    logBtn.setClickable(false);
                    dialog = new Dialogs.loading(LoginActivity.this, "登录中");
                    dialog.show();
                    try {
                        authentication.login(type, handler, id, passWd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else
                    Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_LONG).show();
                break;
            case R.id.registerbtn:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.username_clear:
                username.setText("");
                break;
            case R.id.passwd_clear:
                password.setText("");
                break;
            case R.id.show_passwd_icon:
                isShownPasswd = !isShownPasswd;
                showPasswd.setText(isShownPasswd ? R.string.fa_eye_before : R.string.fa_eye_slash_before);
                password.setTransformationMethod(isShownPasswd ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                password.setSelection(password.getText().toString().length());
                Log.d("hbj", isShownPasswd.toString());
                break;
            case R.id.tempbtn:
                OtherUtil.srtpNotification(this);
                break;
            case R.id.find_passwd:
                // TODO: 2019/6/19 找回密码
                Toast.makeText(this, "该项已移除，变更为更改密码", Toast.LENGTH_SHORT).show();
                ding(this, NOTIFICATION);
                break;
            case R.id.field0:
                break;
            case R.id.field1:
                username.requestFocus();
                break;
            case R.id.field2:
                password.requestFocus();
                break;
            case R.id.pop_student:
                Toast.makeText(this, "已设为学生", Toast.LENGTH_SHORT).show();
                type = true;
                loginTypeView.setText("学生");
                if (!username.hasFocus()) username.setHint(R.string.hint_username_student);
                mPopWindow.dismiss();
                break;
            case R.id.pop_teacher:
                Toast.makeText(this, "已设为教师", Toast.LENGTH_SHORT).show();
                type = false;
                loginTypeView.setText("教师");
                if (!username.hasFocus()) username.setHint(R.string.hint_username_teacher);
                mPopWindow.dismiss();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mHandler.sendEmptyMessage(MSG_EXIT);
            return true;
        } else return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
            mPopWindow = null;
        }
        if (username.hasFocus()) username.clearFocus();
        if (password.hasFocus()) password.clearFocus();
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.username:
                username.setHint(hasFocus ? "" : (type ? getString(R.string.hint_username_student) : getString(R.string.hint_username_teacher)));
                usernameClear.setVisibility((hasFocus && !username.getText().toString().isEmpty()) ? View.VISIBLE : View.GONE);
                break;
            case R.id.passwd:
                if (hasFocus && !password.getText().toString().isEmpty()) {
                    passwordClear.setVisibility(View.VISIBLE);
                    showPasswd.setVisibility(View.VISIBLE);

                } else {
                    passwordClear.setVisibility(View.GONE);
                    showPasswd.setVisibility(View.GONE);
                }
                password.setHint(hasFocus ? "" : getString(R.string.hint_password));
                password.setSelection(password.getText().toString().length());
                break;
        }
    }

    private final Handler handler = new mHandler(this);

    private static class mHandler extends Handler {
        private final WeakReference<Activity> mTarget;

        mHandler(Activity activity) {
            mTarget = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (dialog != null) {
                LoginActivity.dialog.dismiss();
                LoginActivity.dialog = null;
            }
            Activity activity = mTarget.get();
            TextView logBtn = activity.findViewById(R.id.loginbtn);
            Log.d("hbj", "成功传到handleMessage,msg.what是" + msg.what);
            String customResponse = msg.getData().getString("customResponse");
            new Handler().postDelayed(() -> logBtn.setClickable(true), 1000);
            if (customResponse == null) {
                ding(activity, ALERT);
                // TODO: 2019/6/18 登录按钮
            } else
                switch (msg.what) {
                    case HttpUtil.SUCCESS:
                        if (customResponse.startsWith("OK")) {
                            // TODO: 2019/6/25 登录成功
                            Settings.putString(Settings.KEY_TOKEN, customResponse.substring(2), false);
                            Settings.putBoolean(Settings.KEY_USER_TYPE, type, false);
                            logBtn.setClickable(false);
                            Intent intent = new Intent(activity, MainActivity.class);
                            activity.startActivity(intent);
                            activity.finish();
                        } else {
                            Toast.makeText(activity, customResponse, Toast.LENGTH_SHORT).show();

                        }
                        break;
                    case HttpUtil.ERROR:
                        Toast.makeText(activity, customResponse, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Log.d("hbj", "这是" + msg.what);
                        break;
                }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        View[] viewMoves = new View[]{findViewById(R.id.field0), findViewById(R.id.field1), findViewById(R.id.field2)};
        //  for (View viewMove : viewMoves)
        findViewById(R.id.field3).addOnLayoutChangeListener(this::changeLayout);
        if (Settings.getBoolean(Settings.KEY_IS_IN_DEVELOPER_MODE, false)) {
            findViewById(R.id.tempbtn).setVisibility(View.VISIBLE);
            findViewById(R.id.test).setVisibility(View.VISIBLE);
            findViewById(R.id.baseline).setVisibility(View.VISIBLE);
            findViewById(R.id.find_passwd).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.tempbtn).setVisibility(View.INVISIBLE);
            findViewById(R.id.test).setVisibility(View.INVISIBLE);
            findViewById(R.id.baseline).setVisibility(View.GONE);
            findViewById(R.id.find_passwd).setVisibility(View.GONE);
        }
    }

    void changeLayout(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        Log.d("hbj", MessageFormat.format("长：{0}  宽：{1}", getResources().getDisplayMetrics().heightPixels, getResources().getDisplayMetrics().widthPixels));
        Log.d("hbj", MessageFormat.format(" v.getPivotY() 是：{0}", v.getPivotY()));
        Log.d("hbj", MessageFormat.format("top: {1}\tbottom: {3}\toldTop: {5}\toldBottom: {7}",
                left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom));
        //if (oldTop != 0) v.animate().translationYBy(top - oldTop).setDuration(1000).start();
        int[] l = new int[2];
        int[] l2 = new int[2];
        Rect rect = new Rect();
        v.getLocationOnScreen(l);
        v.getLocationInWindow(l2);
        v.getGlobalVisibleRect(rect);
        Log.d("hbj", l[0] + "      " + l[1]);
        Log.d("hbj", l2[0] + "      " + l2[1]);
        Log.d("hbj", rect.height() + "      " + rect.width());

    }
}
