package com.example.bobham.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.widget.Toast.LENGTH_SHORT;


public class LoginActivity extends Activity {
    String country;//系统当前国家信息
    private static final String[] m = {"CN", "US"};
    private EditText username, password;
    private TextView tip;
    private boolean clicked = false; //此值用于判断是否显示“请谨慎输入”字样
    SharedPreferences sp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init() {
        ((TextView) this.findViewById(R.id.LOGIN)).setTypeface(Typeface.createFromAsset(getAssets(), "font/myFont.otf"));
        Spinner login = this.findViewById(R.id.spinner);
        Configuration config = this.getResources().getConfiguration();

        sp = getSharedPreferences("data", MODE_PRIVATE);
        Log.d("BaoJia", "当前系统语言为" + Locale.getDefault().getLanguage() + "，【LoginActivity】当前自定义config为" + config.locale.getLanguage() + "，SharedPreferences存储的信息为" + sp.getString("languageSetting", "nothing"));
        country = getResources().getConfiguration().locale.getCountry();
        username = this.findViewById(R.id.username);
        password = this.findViewById(R.id.password);
        tip = this.findViewById(R.id.tip);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, m);

        adapter.setDropDownViewResource(R.layout.dropdown_style);
        login.setAdapter(adapter);
        username.addTextChangedListener(new HideTextWatcher());
        password.addTextChangedListener(new HideTextWatcher());
        tip.addTextChangedListener(new TipWatcher());
        tip.setVisibility(View.GONE);
        username.requestFocus();
        login.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if (sp.getString("languageSetting", "nothing").equals("Chinese")) login.setSelection(0);
        else login.setSelection(1);

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
            if (s == null) tip.setVisibility(View.GONE);
            else tip.setVisibility(View.VISIBLE);
        }
    }

    private class HideTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //当提交按钮被点击一次之后显示“请谨慎输入”提示
            tip.setTextColor(Color.WHITE);
            if (clicked) tip.setText(R.string.typingTip);
        }

        @Override
        public void afterTextChanged(Editable s) {
            tip.setTextColor(Color.WHITE);
            if (username.isFocused() && s.length() >= 5)
                if (s.length() == 5) {
                    if (username.isFocused()) {
                        username.clearFocus();
                        password.requestFocus();
                        username.setSelectAllOnFocus(true);
                        password.setSelectAllOnFocus(true);
                    }
                } else {
                    int index = username.getSelectionStart();
                    username.getText().delete(index - 1, index);
                    showMessage("不要再输入了");
                }
        }
    }

    public void submitOnClick(View view) {
        if (!clicked) clicked = true;
        tip.setTextColor(Color.RED);
        //合法
        if (username.length() <= 5 || Double.valueOf(username.getText().toString()) > 15000) {
            //
            if (username.length() <= 5) {
                //合法且匹配
                if (username.getText().toString().equals(sp.getString("Username", null)) && password.getText().toString().equals(sp.getString("Password", null))) {
                    tip.setText(R.string.correct);
                    tip.setTextColor(Color.GREEN);
                    switchActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                //合法但有空值
                else if (username.getText().toString().equals("") || password.getText().toString().equals(""))
                    tip.setText(R.string.tip_ifEmpty);
                else {
                    tip.setText(R.string.incorrect);
                }
            }
            //合法但不匹配
            else {
                tip.setText(R.string.tip_ifError);
                tip.setTextColor(Color.RED);
            }
        }
        //不合法
        else {
            tip.setText(R.string.incorrect);
            tip.setTextColor(Color.RED);
        }
    }

    @SuppressLint("CommitPrefEdits")
    public void deleteOnClick(View view) {
        sp.edit().remove("languageSetting").apply();
        if (sp.getString("languageSetting", "nothing").equals("nothing"))
            Log.d("BaoJia", sp.getString("languageSetting", "deleteSuccessfully"));
        Log.d("BaoJia", "当前系统语言为" + Locale.getDefault().getLanguage() + "，【LoginActivity】当前自定义config为" + getResources().getConfiguration().locale.getLanguage() + "，SharedPreferences存储的信息为" + sp.getString("languageSetting", "nothing"));
    }

    @Deprecated
    public void showMessage(String _s) {
        Toast.makeText(this, _s, LENGTH_SHORT).show();
    }

    public void switchActivity(Intent i) {
        //startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        startActivity(i);
    }


    int pressed = 0;
    int time = LENGTH_SHORT;
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            pressed--;
        }
    };

    public void onBackPressed() {
        if (pressed++ == 0) {
            showMessage("再按一次退出 hbj");
            timer.schedule(timerTask,time);
        } else finish();
    }
}