/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.physswjtu.srtp2019.widgets.TextView;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 由 84697 创建
 * 日期为 2019/7/27
 * 工程 PhysLab
 */
public class CaptureActivity extends BaseActivity {
    @BindView(R.id.home)
    TextView home;
    @BindView(R.id.second_button1)
    Button secondButton1;
    boolean isLightEnable = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        // 执行扫面Fragment的初始化操作
        CaptureFragment captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.view_my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        // 替换我们的扫描控件
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            setResult(RESULT_OK, resultIntent);
            finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    };

    @OnClick({R.id.home, R.id.second_button1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home:
                finish();
                break;
            case R.id.second_button1:
                isLightEnable = !isLightEnable;
                CodeUtils.isLightEnable(isLightEnable);
                secondButton1.setText(isLightEnable ? "关闭闪光灯" : "开启闪光灯");
                break;
        }
    }
}
