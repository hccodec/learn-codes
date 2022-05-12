/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.widgets;

/**
 * 由 84697 创建
 * 日期为 2019/8/2
 * 工程 PhysLab
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.webkit.WebView;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.VelocityTrackerCompat;
import androidx.core.view.ViewCompat;

/**
 * Created by hanks on 16/8/30.
 */
public class CodeWebView extends WebView implements NestedScrollingChild {

    public static final String TAG = CodeWebView.class.getSimpleName();
    public static final int UP = 1;
    public static final int DOWN = -1;
    private final int[] mScrollOffset = new int[2];
    private final int[] mScrollConsumed = new int[2];
    private final int mTouchSlop;
    private final int mMinimumVelocity;
    private final int mMaximumVelocity;
    private int direction = DOWN; // TODO 还需要同步到父布局的方向
    private int mLastMotionY;
    private int mNestedYOffset;
    private NestedScrollingChildHelper mChildHelper;
    private VelocityTracker mVelocityTracker;
    private boolean allowFly;
    private int downY;
    private float mDownY = -1;

    public CodeWebView(Context context) {
        this(context, null);
    }

    public CodeWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CodeWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mChildHelper = new NestedScrollingChildHelper(this); // 辅助类
        setNestedScrollingEnabled(true); // 设置支持 NestedScrolling

        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean eventAddedToVelocityTracker = false;

        final int action = MotionEventCompat.getActionMasked(event);
        final int actionIndex = MotionEventCompat.getActionIndex(event);

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                allowFly = false;
                downY = (int) event.getRawY();
                // 开始 NestedScroll
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) event.getRawY();
                int dy = -(moveY - downY); //滚动方法的方向跟坐标是相反的，所以这里要加一个负号
                downY = moveY;
                //在consumed 中就是父类滑动的距离，
                if (dispatchNestedPreScroll(0, dy, mScrollConsumed, mScrollOffset)) {
                    dy -= mScrollConsumed[1]; // 减去父类消费的距离
                    scrollBy(0, dy); // 剩下的自己消费
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                mVelocityTracker.addMovement(event);
                eventAddedToVelocityTracker = true;
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                int mScrollPointerId = MotionEventCompat.getPointerId(event, actionIndex);
                float vY = -VelocityTrackerCompat.getYVelocity(mVelocityTracker, mScrollPointerId);
                // 产生 fling 事件
                if (Math.abs(vY) > mMinimumVelocity && !dispatchNestedPreFling(0, vY)) {
                    dispatchNestedFling(0, vY, true);
                    logi("dispatchNestedFling");
                }
                resetTouch();
                break;
        }
        if (!eventAddedToVelocityTracker) {
            mVelocityTracker.addMovement(event);
        }
        return true;

    }

    private void resetTouch() {
        if (mVelocityTracker != null) {
            mVelocityTracker.clear();
        }
        stopNestedScroll();
    }


    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return mChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        mChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return mChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    public void log(String s) {
        Log.e(TAG, s);
    }

    public void logi(String s) {
        Log.i(TAG, s);
    }

    public void logw(String s) {
        Log.w(TAG, s);
    }
}
