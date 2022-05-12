/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

/**
 * 由 宝佳 创建
 * 日期为 2019.6.20
 * 工程 ${PROJECT_NAME}
 */
package com.physswjtu.srtp2019.widgets;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.physswjtu.srtp2019.R;

public class Toast extends android.widget.Toast {
    private static Toast result;
    public static final int TOAST_ALERT = 0;
    private static TextView toast_text;
    private static TextView toast_icon;

    private Toast(Context context) {
        super(context);
    }

    public static Toast makeText(Context context, CharSequence text, int duration) {
        try {
            cancelToast();
            if (toast_icon != null) toast_icon.clearAnimation();
            init(context);
            toast_text.setText(text);
            result.setDuration(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Toast makeText(Context context, CharSequence text, int duration, int mode) {
        makeText(context, text, duration);
        if (mode == Toast.TOAST_ALERT) {
            toast_icon.setText(R.string.fa_circle_o_notch_before);
            toast_icon.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate));
        }
        return result;
    }


    private static void init(Context context) {
        result = new Toast(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.view_toast, null);
        toast_text = layout.findViewById(R.id.toast_message);
        toast_icon = layout.findViewById(R.id.toast_icon);
        toast_text.setTextColor(Color.WHITE);
        toast_text.setBackgroundColor(Color.argb(200, 50, 50, 50));
        result.setGravity(Gravity.CENTER, 0, 0);
        result.setView(layout);
    }

    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void cancelToast() {
        if (result != null) result.cancel();
    }

    @Override
    public void cancel() {
        try {
            super.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSpannableText(Context context, CharSequence text, int duration) {
        makeText(context, text, duration);
    }
}