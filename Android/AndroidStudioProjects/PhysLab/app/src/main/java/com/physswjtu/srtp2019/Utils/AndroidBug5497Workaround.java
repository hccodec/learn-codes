package com.physswjtu.srtp2019.Utils;/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * 修复全屏下的布局自适应问题 - 官方 bug 修复代码
 */
public class AndroidBug5497Workaround {

    // For more information, see https://code.google.com/p/android/issues/detail?id=5497
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

//    public static void assistActivity (Activity activity) {
//        new AndroidBug5497Workaround(activity);
//    }

    private View mContent;
    private int usableHeightPrevious;
    private LayoutParams layoutParams;

    public static void assistView(View v) {
        new AndroidBug5497Workaround(v);
    }

    public static void assistActivity(Activity activity) {
        new AndroidBug5497Workaround(activity);
    }

    private AndroidBug5497Workaround(Activity activity) {
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        mContent = content.getChildAt(0);
        addGlobalLayoutListener(mContent);

    }

    //有时通过Activity获取view并不能满足，所以我加了直接传view的一个构造方法满足用到Fragment的情况
    private AndroidBug5497Workaround(View v) {
        //FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        // mChildOfContent = content.getChildAt(0);
        addGlobalLayoutListener(v);

    }

    private void addGlobalLayoutListener(View v) {
        mContent = v;
        mContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        //  LayoutParams放在这里有可能得到的为空
        //  LayoutParams =  mContent.getLayoutParams();
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            layoutParams = mContent.getLayoutParams();
            int usableHeightSansKeyboard = mContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                // keyboard probably just became visible
                layoutParams.height = usableHeightSansKeyboard - heightDifference;
            } else {
                // keyboard probably just became hidden
                layoutParams.height = usableHeightSansKeyboard;
            }
            mContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mContent.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top);
    }
}