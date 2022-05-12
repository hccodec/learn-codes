/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.physswjtu.srtp2019.R;

/**
 * @Created by xww.
 * @Creation time 2018/8/8.
 */

public class RoundImageView extends AppCompatImageView {

    private Paint mPaint;
    private Drawable mDrawable;
    private BitmapShader mBitmapShader;
    private int mWidth;
    private int mHeight;


    @SuppressLint("Recycle")
    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = null;
            try {
                array = getContext().obtainStyledAttributes(attrs, R.styleable.RoundImageView);
                mDrawable = array.getDrawable(R.styleable.RoundImageView_src);
                if (mDrawable == null) {
                    throw new NullPointerException("drawable should not be null");
                }
                mWidth = mDrawable.getIntrinsicWidth();
                mHeight = mDrawable.getIntrinsicHeight();
            } finally {
                if (array != null) {
                    array.recycle();
                }
            }
        }
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        initAttrs(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDrawable == null) {
            return;
        }
        final int width = canvas.getWidth();
        final int height = canvas.getHeight();

        /**
         * 绘制圆形图片
         */
        try {
            mBitmapShader = new BitmapShader(drawableToBitmap(mDrawable), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("hbj", mWidth + "是mWidth，" + mHeight + "是mHeight");
        }
        mPaint.setShader(mBitmapShader);
        canvas.drawCircle(width >> 1, height / 2, width / 2, mPaint);
    }

    private int measureWidth(int widthMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                mWidth = widthSize;
                break;
        }
        return mWidth;
    }

    private int measureHeight(int heightMeasureSpec) {
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case View.MeasureSpec.EXACTLY:
                mHeight = heightSize;
                break;
        }
        return mHeight;
    }

    private Bitmap drawableToBitmap(Drawable drawable) throws Exception {
        if (mWidth == 0 || mHeight == 0)
            throw new Exception("mWidth是：" + mWidth + "mHeight是：" + mHeight);
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(mWidth == 0 ? 55 : mWidth, mHeight == 0 ? 55 : mHeight, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, mWidth, mHeight);
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        mDrawable = drawable;
        if (mDrawable == null) {
            throw new NullPointerException("Drawable should not be null!");
        }
        mWidth = mDrawable.getIntrinsicWidth();
        mHeight = mDrawable.getIntrinsicHeight();
        invalidate();
    }

}