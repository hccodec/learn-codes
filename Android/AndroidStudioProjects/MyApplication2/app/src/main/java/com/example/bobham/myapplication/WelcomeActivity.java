package com.example.bobham.myapplication;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.transition.Fade;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class WelcomeActivity extends Activity {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Button confirm;
    Spinner spin;
    String[] languages;
    String SystemLanguage = Locale.getDefault().getLanguage();//读取系统语言设置
    String customLanguage;
    Dialog dialog;
    Intent i = new Intent();
    Configuration config;
    DisplayMetrics dm;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        sp = getSharedPreferences("data", Context.MODE_PRIVATE);
        customLanguage = sp.getString("languageSetting", "nothing");//读取自定义语言设置
        init();
    }

    public void init() {
        config = this.getResources().getConfiguration();
        dm = this.getResources().getDisplayMetrics();

        if (customLanguage.equals("nothing")) config.setLocale(Locale.ENGLISH);
        getBaseContext().getResources().updateConfiguration(config, dm);//应用自定义语言设置
        Log.d("BaoJia", "当前系统语言为" + SystemLanguage + "，【WelcomeActivity】当前自定义config为" + config.locale.getLanguage() + "，SharedPreferences存储的信息为" + customLanguage);


        editor = sp.edit();
        dialog = new Dialog(WelcomeActivity.this, R.style.MyDialog);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setClass(getApplicationContext(), Welcome2Activity.class);
        editor.putString("Username", "11275");
        editor.putString("Password", "123");
        editor.apply();
        dialog();

    }

    //dialog实现
    public void dialog() {
        final String customLanguage = sp.getString("languageSetting", "nothing");//读取自定义语言设置

        languages = getResources().getStringArray(R.array.languages);

        dialog.setContentView(R.layout.custom_dialog);
        if (!WelcomeActivity.this.isFinishing()) dialog.show();

        confirm = dialog.findViewById(R.id.dialog_button_ok);

        //下拉菜单设置
        spin = dialog.findViewById(R.id.languageChooser);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(dialog.getContext(), R.layout.spinner_item, languages);
        adapter.setDropDownViewResource(R.layout.dropdown_style);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new OnItemSelected());
        //设置默认选项
        Log.d("BaoJia", "系统语言设置为" + SystemLanguage + "，SharedPreferences存储的信息为" + customLanguage);
        // TODO: 2018/8/17 若SharedPreferences已定义值，则直接跳转页面；否则弹出对话框选择语言
        if (customLanguage.equals("nothing")) {
            if (SystemLanguage.equals("zh")) spin.setSelection(1, true);
            else if (SystemLanguage.equals("en")) spin.setSelection(2, true);
        } else {
            //将config与SharedPreferences同步
            if (customLanguage.equals("Chinese")) config.setLocale(Locale.SIMPLIFIED_CHINESE);
            else if (customLanguage.equals("English")) config.setLocale(Locale.ENGLISH);
            getBaseContext().getResources().updateConfiguration(config, dm);//应用自定义语言设置
            if (customLanguage.equals("Chinese") && getResources().getConfiguration().locale.getLanguage().equals(Locale.SIMPLIFIED_CHINESE.toString()) || customLanguage.equals("English") && getResources().getConfiguration().locale.getLanguage().equals(Locale.ENGLISH.toString()))
                Log.d("BaoJia", "升级成功");
            dialog.dismiss();
            startActivity(i);
            finish();
        }


        //给dialog中的按钮设置监听
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.commit();
                dialog.dismiss();
                if (spin.getSelectedItemId() == 1) config.setLocale(Locale.SIMPLIFIED_CHINESE);//选中文
                else if (spin.getSelectedItemId() == 2) config.setLocale(Locale.ENGLISH);//选英文
                getResources().updateConfiguration(config, dm);
                startActivity(i);
                finish();

            }
        });
    }

    public class OnItemSelected implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (pos == 0) {
                confirm.setClickable(false);
            } else {
                confirm.setClickable(true);
                if (pos == 1) editor.putString("languageSetting", "Chinese");
                else if (pos == 2) editor.putString("languageSetting", "English");
                Log.d("BaoJia", "您选择的为：" + languages[pos]);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }
}
