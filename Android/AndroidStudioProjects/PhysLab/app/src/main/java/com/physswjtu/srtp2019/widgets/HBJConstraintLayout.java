/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.WindowInsets;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * 由 84697 创建
 * 日期为 2019/7/29
 * 工程 PhysLab
 */
public class HBJConstraintLayout extends ConstraintLayout {

    public HBJConstraintLayout(Context context) {
        super(context);
    }

    public HBJConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HBJConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HBJConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected boolean fitSystemWindows(Rect insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            insets.left = 0;
            insets.top = 0;
            insets.right = 0;
        }
        return super.fitSystemWindows(insets);
    }

    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return super.onApplyWindowInsets(insets.replaceSystemWindowInsets(0, 0, 0, insets.getSystemWindowInsetBottom()));
        } else {
            return insets;
        }
    }
}
