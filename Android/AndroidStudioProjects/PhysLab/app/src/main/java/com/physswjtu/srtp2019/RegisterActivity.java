package com.physswjtu.srtp2019;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.widget.Toolbar;

import com.physswjtu.srtp2019.Utils.HttpUtil;
import com.physswjtu.srtp2019.Utils.OtherUtil;
import com.physswjtu.srtp2019.Utils.authentication;
import com.physswjtu.srtp2019.widgets.Dialogs;
import com.physswjtu.srtp2019.widgets.TextView;
import com.physswjtu.srtp2019.widgets.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.physswjtu.srtp2019.Utils.SoundUtil.ALERT;
import static com.physswjtu.srtp2019.Utils.SoundUtil.ding;


public class RegisterActivity extends BaseActivity {

    @BindView(R.id.home)
    TextView home;
    @BindView(R.id.register_id)
    EditText registerId;
    @BindView(R.id.register_id_clear)
    TextView registerIdClear;
    @BindView(R.id.register_passwd)
    EditText registerPasswd;
    @BindView(R.id.register_passwd_clear)
    TextView registerPasswdClear;
    @BindView(R.id.register_name)
    EditText registerName;
    @BindView(R.id.register_name_clear)
    TextView registerNameClear;
    @BindView(R.id.register_sex_male)
    RadioButton registerSexMale;
    @BindView(R.id.register_sex_female)
    RadioButton registerSexFemale;
    @BindView(R.id.register_sex)
    RadioGroup registerSex;
    @BindView(R.id.register_school)
    EditText registerSchool;
    @BindView(R.id.register_school_clear)
    TextView registerSchoolClear;
    @BindView(R.id.register_className)
    EditText registerClassName;
    @BindView(R.id.register_className_clear)
    TextView registerClassNameClear;
    @BindView(R.id.register_phoneNum)
    EditText registerPhoneNum;
    @BindView(R.id.register_phoneNum_clear)
    TextView registerPhoneNumClear;
    @BindView(R.id.submit)
    Button submit;

    @BindView(R.id.login_dialog)
    LinearLayout loginDialog;

    @BindView(R.id.register_show_passwd_icon)
    TextView registerShowPasswdIcon;
    @BindView(R.id.register_show_passwd_confirm_icon)
    TextView registerShowPasswdConfirmIcon;
    @BindView(R.id.register_passwd_confirm)
    EditText registerPasswdConfirm;
    @BindView(R.id.register_passwd_confirm_clear)
    TextView registerPasswdConfirmClear;

    private final Handler handler = new mHandler(this);

    String regId;
    String regPasswd;
    String regPasswdConfirm;
    String regName;
    String regSchool;
    String regClassName;
    String regPhoneNum;

    ArrayList<String> information = new ArrayList<>();
    Boolean isShownPasswd = false;
    Boolean isShownPasswd1 = false;
    //以下两个 EditText[] 数组都是页面中的输入框
    EditText[] editTexts;
    EditText[] editTexts1;
    @BindView(R.id.login_type)
    TextView loginType;
    boolean type;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String sex;
    PopupWindow mPopWindow;
    static Dialog dialog;

    public void submit() {
        getParameters();
        //判定条件第一：所有信息是否全部填写完整
        if (regId == null || regPasswd == null || regPasswdConfirm == null || regName == null ||
                sex == null || regSchool == null || regClassName == null || regPhoneNum == null) {
            ding(this, ALERT);
            Toast.makeText(this, "请将信息填写完整", Toast.LENGTH_SHORT).show();
        } else {
            //判定条件第二：密码与确认密码输入框取值是否一致
            information.add(0, regId);
            information.add(1, regPasswd);
            information.add(2, regName);
            information.add(3, sex);
            information.add(4, regSchool);
            information.add(5, regClassName);
            information.add(6, regPhoneNum);
            String[] data = information.toArray(new String[]{});
            Log.d("hbj", Arrays.toString(data));
            Log.d("hbj", "RegisterActivity(): submit(): " + information);
            if (!regPasswd.equals(regPasswdConfirm)) {
                ding(this, ALERT);
                Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                registerPasswdConfirm.requestFocus();
                registerPasswdConfirm.setSelection(regPasswd.length());
            } else {
                dialog = new Dialogs.loading(RegisterActivity.this, "注册中");
                dialog.show();
                try {
                    authentication.register(type, handler, data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClick({R.id.home, R.id.register_id_clear, R.id.register_passwd_clear,
            R.id.register_name_clear, R.id.register_school_clear, R.id.register_className_clear,
            R.id.register_phoneNum_clear, R.id.submit, R.id.register_show_passwd_icon,
            R.id.register_show_passwd_confirm_icon, R.id.register_passwd_confirm_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.submit:
                submit();
                break;
            case R.id.register_id_clear:
                registerId.setText("");
                break;
            case R.id.register_passwd_clear:
                registerPasswd.setText("");
                break;
            case R.id.register_passwd_confirm_clear:
                registerPasswd.setText("");
                break;
            case R.id.register_name_clear:
                registerName.setText("");
                break;
            case R.id.register_school_clear:
                registerSchool.setText("");
                break;
            case R.id.register_className_clear:
                registerClassName.setText("");
                break;
            case R.id.register_phoneNum_clear:
                registerPhoneNum.setText("");
                break;
            case R.id.register_show_passwd_icon:
                isShownPasswd = !isShownPasswd;
                registerShowPasswdIcon.setText(isShownPasswd ? R.string.fa_eye_before : R.string.fa_eye_slash_before);
                registerPasswd.setTransformationMethod(isShownPasswd ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                registerPasswd.setSelection(registerPasswd.getText().toString().length());
                break;
            case R.id.register_show_passwd_confirm_icon:
                isShownPasswd1 = !isShownPasswd1;
                Log.d("hbj", "什什么情况！！？？");
                registerShowPasswdConfirmIcon.setText(isShownPasswd1 ? R.string.fa_eye_before : R.string.fa_eye_slash_before);
                registerPasswdConfirm.setTransformationMethod(isShownPasswd1 ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                registerPasswdConfirm.setSelection(registerPasswdConfirm.getText().toString().length());
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OtherUtil.setStatusBar(getWindow());
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
        setSupportActionBar(toolbar);
        init();
    }

    public void init() {
        initViews();
    }

    void initViews() {
        loginType.setText(type ? "学生" : "教师");
        loginType.setOnClickListener(v -> {
            if (mPopWindow != null && mPopWindow.isShowing()) mPopWindow.dismiss();
            else showPopupWindow();
        });
        registerSex.setOnCheckedChangeListener((radioGroup, checked) -> {
            RadioButton RadioButtonChecked = radioGroup.findViewById(checked);
            String sexText = RadioButtonChecked.getText().toString();
            sex = sexText.equals("男") ? "1" : "2";
        });
        String regIdHint = getString(R.string.hint_username_student);
        String regPasswdHint = getString(R.string.hint_password);
        String regPasswdConfirmHint = getString(R.string.hint_password_confirm);
        String regNameHint = getString(R.string.hint_name);
        String regSchoolHint = getString(R.string.hint_school);
        String regClassNameHint = getString(R.string.hint_classname);
        String regPhoneNumHint = getString(R.string.hint_phone_num);
        //以下三个数组中仅包含下面 5 个参数（不含 passwd: 密码，passwdConfirm: 确认密码，sex: 性别）
        //id: 学号，name: 姓名，school: 学院，className: 班级，phoneNum: 手机号）
        editTexts = new EditText[]{registerId, registerName, registerSchool, registerClassName, registerPhoneNum};
        String[] hints = new String[]{regIdHint, regNameHint, regSchoolHint, regClassNameHint, regPhoneNumHint};
        View[] views = new View[]{registerIdClear, registerNameClear, registerSchoolClear, registerClassNameClear, registerPhoneNumClear};
        //以下三个数组中仅包含下面 2 个参数（不含 passwd: 密码，passwdConfirm: 确认密码，sex: 性别）
        //id: 学号，name: 姓名，school: 学院，className: 班级，phoneNum: 手机号）
        editTexts1 = new EditText[]{registerPasswd, registerPasswdConfirm};
        String[] hints1 = new String[]{regPasswdHint, regPasswdConfirmHint};
        View[] views1 = new View[]{registerPasswdClear, registerPasswdConfirmClear};
        View[] views2 = new View[]{registerShowPasswdIcon, registerShowPasswdConfirmIcon};
        initEditTexts(editTexts, hints, views);
        initEditTexts(editTexts1, hints1, views1, views2);
    }

    // 5 个参数
    void initEditTexts(EditText[] editTexts, String[] hints, View[] views) {
        for (int i = 0; i < editTexts.length; i++) {
            editTexts[i].setHint(hints[i]);
            editTexts[i].addTextChangedListener(new mTextWatcher(editTexts[i], views[i]));
            editTexts[i].setOnFocusChangeListener(new mFocusChangeListener(editTexts[i], views[i], hints[i]));
            views[i].setVisibility(View.GONE);
        }
    }

    // 2 个参数（passwd: 密码，passwdConfirm: 确认密码）
    void initEditTexts(EditText[] editTexts, String[] hints, View[] views, View[] views2) {
        for (int i = 0; i < editTexts.length; i++) {
            editTexts[i].setHint(hints[i]);
            editTexts[i].addTextChangedListener(new mTextWatcher(editTexts[i], views[i], views2[i]));
            editTexts[i].setOnFocusChangeListener(new mFocusChangeListener(editTexts[i], views[i], hints[i], views2[i]));
            views[i].setVisibility(View.GONE);
            views2[i].setVisibility(View.GONE);
        }
    }

    // 获取 EditText 中的值
    void getParameters() {
        regId = registerId.getText().toString();
        regPasswd = registerPasswd.getText().toString();
        regPasswdConfirm = registerPasswdConfirm.getText().toString();
        regName = registerName.getText().toString();
        regSchool = registerSchool.getText().toString();
        regClassName = registerClassName.getText().toString();
        regPhoneNum = registerPhoneNum.getText().toString();
    }

    public void testFill(View view) {
        registerId.setText("2017113903");
        registerPasswd.setText("qweqweqwe");
        registerPasswdConfirm.setText("qweqweqwe");
        registerName.setText("stztc");
        registerSex.check(registerSexMale.getId());
        registerSchool.setText("SRTP");
        registerClassName.setText("srtp");
        registerPhoneNum.setText("123456");
    }

    private static class mHandler extends Handler {
        private final WeakReference<Activity> mTarget;

        mHandler(Activity activity) {
            mTarget = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (dialog != null) {
                dialog.dismiss();
                dialog = null;
            }
            Activity activity = mTarget.get();
            Log.d("hbj", "成功传到handleMessage,msg.what是" + msg.what);
            String customResponse = msg.getData().getString("customResponse");
            if (!(customResponse != null && customResponse.equals("OK")))
                ding(activity, ALERT);
            switch (msg.what) {
                case HttpUtil.SUCCESS:
                    if ("OK".equals(customResponse)) {
                        Toast.makeText(activity, "注册成功，请稍候", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(activity, customResponse, Toast.LENGTH_SHORT).show();
                    }
                case HttpUtil.ERROR:
                    Toast.makeText(activity, customResponse, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Log.d("hbj", "这是" + msg.what);
                    break;
            }
        }
    }

    private class mTextWatcher implements TextWatcher {
        //若 v1 中的数值为空，则 v2 隐藏
        EditText v1;
        View v2;
        View v3 = null;

        mTextWatcher(EditText v1, View v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        mTextWatcher(EditText v1, View v2, View v3) {
            this.v1 = v1;
            this.v2 = v2;
            this.v3 = v3;
        }


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            v2.setVisibility(v1.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
            if (v3 != null)
                v3.setVisibility(v1.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class mFocusChangeListener implements View.OnFocusChangeListener {
        //若 v1 获取焦点则hint隐藏，否则显示相应hint（s）
        // 若 v1 获取焦点并且 v1 输入框有内容显示清空按钮，否则隐藏
        EditText v1;
        String s;
        View v2;
        View v3;

        mFocusChangeListener(EditText v1, View v2, String s) {
            this.v1 = v1;
            this.v2 = v2;
            this.s = s;
        }

        mFocusChangeListener(EditText v1, View v2, String s, View v3) {
            this.v1 = v1;
            this.v2 = v2;
            this.s = s;
            this.v3 = v3;
        }


        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            v1.setHint(hasFocus ? "" : s);
            v2.setVisibility((hasFocus && !v1.getText().toString().isEmpty()) ? View.VISIBLE : View.GONE);
            if (v3 != null)
                v3.setVisibility((hasFocus && !v1.getText().toString().isEmpty()) ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
            mPopWindow = null;
        }
        super.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
            mPopWindow = null;
        }
        for (EditText editText : editTexts) if (editText.hasFocus()) editText.clearFocus();
        for (EditText editText : editTexts1) if (editText.hasFocus()) editText.clearFocus();
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        return super.onTouchEvent(event);
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_usr_type, null);
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv1 = contentView.findViewById(R.id.pop_student);
        TextView tv2 = contentView.findViewById(R.id.pop_teacher);
        tv1.setOnClickListener(v -> {
            Toast.makeText(RegisterActivity.this, "已设为学生", Toast.LENGTH_SHORT).show();
            type = true;
            loginType.setText("学生");
            if (!registerId.hasFocus()) registerId.setHint(R.string.hint_username_student);
            mPopWindow.dismiss();
        });
        tv2.setOnClickListener(v -> {
            Toast.makeText(RegisterActivity.this, "已设为教师", Toast.LENGTH_SHORT).show();
            type = false;
            loginType.setText("教师");
            if (!registerId.hasFocus()) registerId.setHint(R.string.hint_username_teacher);
            mPopWindow.dismiss();
        });
        //相对位置 以mMenuTv为坐标,
        mPopWindow.showAsDropDown(loginType);
    }
    /*
    //填充ToolBar右侧按钮菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //覆写ToolBar右侧按钮功能
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.menu_notice) toolbarDialog(this);
        return super.onOptionsItemSelected(item);
    }
    */

}
