/**
 * @date on $today$
 * @author BaoHam （韩宝佳）
 * @email 846972585@qq.com
 * @version 1.0
 * @description LOGIN简易包装
 */
package com.example.bobham.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;


public class LoginActivity extends BaseActivity {
    private static final String[] m = {"CN", "US"};
    String able;//系统当前国家信息
    SharedPreferences sp;
    int i;//遍历usr的editText(与password)的循环变量
    private Double usrId;
    private EditText usr1, usr2, usr3, usr4, usr5, password;
    private TextView tip;
    private ImageView img;
    private boolean clicked = false; //此值用于判断是否显示“请谨慎输入”字样

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 2018/8/27 全屏3
        getWindow().setExitTransition(new Fade().setDuration(500));
        setContentView(R.layout.activity_login);
        init();
    }

    public void init() {
        ((TextView) this.findViewById(R.id.LOGIN)).setTypeface(Typeface.createFromAsset(getAssets(), "font/myFont.otf"), 1);
        Spinner login = this.findViewById(R.id.spinner);
        Configuration config = this.getResources().getConfiguration();

        sp = getSharedPreferences("data", MODE_PRIVATE);
        Log.d("BaoJia", "当前系统语言为" + Locale.getDefault().getLanguage() + "，【LoginActivity】当前自定义config为" + config.locale.getLanguage() + "，SharedPreferences存储的信息为" + sp.getString("languageSetting", "nothing"));
        able = getResources().getConfiguration().locale.getCountry();
        usr1 = this.findViewById(R.id.editText);
        usr2 = this.findViewById(R.id.editText2);
        usr3 = this.findViewById(R.id.editText3);
        usr4 = this.findViewById(R.id.editText4);
        usr5 = this.findViewById(R.id.editText5);
        password = this.findViewById(R.id.password);
        tip = this.findViewById(R.id.tip_text);
        img = this.findViewById(R.id.tip_image);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, m);

        adapter.setDropDownViewResource(R.layout.item_dropdown);
        login.setAdapter(adapter);
        TextView[] set_property = {usr1, usr2, usr3, usr4, usr5, password};
        for (int i = 0; i <= 5; i++) {
            set_property[i].addTextChangedListener(new HideTextWatcher());
            set_property[i].setSelectAllOnFocus(true);
            set_property[i].setTypeface(Typeface.createFromAsset(getAssets(), "font/myFont.otf"), 1);
            if (i < 5) {
                //usr文本框相关属性设置
                set_property[i].setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
                set_property[i].setTextColor(Color.parseColor("#faea00"));
                set_property[i].setTextSize(28);
            }
        }
        tip.addTextChangedListener(new TipWatcher());
        tip.setVisibility(View.GONE);
        img.setVisibility(View.GONE);
        usr1.requestFocus();
        if (sp.getString("languageSetting", "nothing").equals("Chinese")) login.setSelection(0);
        else login.setSelection(1);
    }

    public void submitOnClick(View view) {
        if (!clicked) clicked = true;
        tip.setTextColor(Color.RED);
        //先判断是否为空
        if ((usr1.getText().toString().isEmpty() || usr2.getText().toString().isEmpty() || usr3.getText().toString().isEmpty() || usr4.getText().toString().isEmpty() || usr5.getText().toString().isEmpty()) || password.getText().toString().equals("")) {
            tip.setText(R.string.tip_ifEmpty);
            img.setImageDrawable(getDrawable(R.drawable.ic_error_red_24dp));
            img.setColorFilter(Color.RED);
        }
        //再判断输入的内容的正误
        else {
            usrId = Double.valueOf(usr1.getText().toString() + usr2.getText().toString() + usr3.getText().toString() + usr4.getText().toString() + usr5.getText().toString());
            //合法
            if (usrId <= 15000)
                //合法且匹配
                if (usrId - Double.valueOf(sp.getString("Username", null)) == 0 && password.getText().toString().equals(sp.getString("Password", null))) {
                    tip.setText(R.string.correct);
                    tip.setTextColor(Color.GREEN);
                    img.setImageDrawable(getDrawable(R.drawable.ic_check_green_24dp));
                    img.setColorFilter(Color.GREEN);
                    new Handler(new Handler.Callback() {
                        @Override
                        public boolean handleMessage(Message msg) {
                            switchActivity(new Intent(LoginActivity.this, MainActivity.class));
                            new Handler(new Handler.Callback() {
                                @Override
                                public boolean handleMessage(Message msg) {
                                    finish();
                                    return false;
                                }
                            }).sendEmptyMessageDelayed(0, 1000);
                            return false;
                        }
                    }).sendEmptyMessageDelayed(0, 1000);
                } else tip.setText(R.string.incorrect);
            else
            //不合法
            {
                tip.setText(R.string.incorrect);
                tip.setTextColor(Color.RED);
                img.setImageDrawable(getDrawable(R.drawable.ic_check_green_24dp));
                img.setColorFilter(Color.RED);
            }
        }
    }

    public void deleteOnClick(View view) {
        sp.edit().remove("languageSetting").apply();
        if (sp.getString("languageSetting", "nothing").equals("nothing"))
            showMessage(getString(R.string.delete), this);
        Log.d("BaoJia", "当前系统语言为" + Locale.getDefault().getLanguage() + "，【LoginActivity】当前自定义config为" + getResources().getConfiguration().locale.getLanguage() + "，SharedPreferences存储的信息为" + sp.getString("languageSetting", "nothing"));
    }

    public void switchActivity(Intent i) {
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void temp(View view) {
        switchActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    private class TipWatcher implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s == null) {
                tip.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
            } else {
                tip.setVisibility(View.VISIBLE);
                img.setVisibility(View.VISIBLE);
            }
        }
    }

    private class HideTextWatcher implements TextWatcher {
        private final EditText[] a = {usr1, usr2, usr3, usr4, usr5};

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //当提交按钮被点击一次之后显示“请谨慎输入”提示
            tip.setTextColor(Color.WHITE);
            if (clicked) {
                tip.setText(R.string.typingTip);
                img.setImageDrawable(getDrawable(R.drawable.ic_error_red_24dp));
                img.setColorFilter(Color.parseColor("#FFFFFF"));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO: 18-8-24 五个editTest聚焦判定（已解决）
            if (!(a[0].getText().toString().isEmpty() || a[1].getText().toString().isEmpty() || a[2].getText().toString().isEmpty() || a[3].getText().toString().isEmpty() || a[4].getText().toString().isEmpty()) && a[4].isFocused()) {
                //五个都非空
                a[4].clearFocus();
                password.requestFocus();
            } else {
                for (i = 0; i < 5; i++) {
                    //定位到当前光标位置
                    if (a[i].isFocused() && a[i].getText().toString().length() == 1) {
                        a[i].clearFocus();
                        (i == 4 ? a[0] : a[i + 1]).requestFocus();
                        break;
                    }
                }
            }
        }
    }


}