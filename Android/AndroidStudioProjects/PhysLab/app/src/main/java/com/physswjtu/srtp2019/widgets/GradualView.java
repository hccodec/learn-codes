/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.widgets;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import org.jetbrains.annotations.Nullable;

public class GradualView extends View {

    private int animatedValue;
    private int colorEnd;
    private int colorStart;
    private int animatedValue1;
    LinearGradient backGradient;
    int width;
    int height;
    Paint paint;

    public GradualView(Context context) {
        super(context);
        init();
        requestLayout();
    }

    public GradualView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        requestLayout();
    }

    public GradualView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        requestLayout();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init();
    }

    public void init() {
        postInvalidate();
        //获取View的宽高
        width = getWidth();
        height = getHeight();
        backGradient = new LinearGradient(width, 0, 0, 0, new int[]{colorStart, colorEnd}, new float[]{0, 1f}, Shader.TileMode.CLAMP);
        paint = new Paint();
        ValueAnimator animator = ValueAnimator.ofInt(0, 255);
        animator.setDuration(10000);
        animator.addUpdateListener(animation -> {
            animatedValue = (int) animation.getAnimatedValue();
            if (animatedValue < 255) {
                colorStart = Color.rgb(255, animatedValue, 255 - animatedValue);
                colorEnd = Color.rgb(animatedValue, 0, 255 - animatedValue);
            } else if (animatedValue == 255) {
                ValueAnimator animator1 = ValueAnimator.ofInt(0, 255);
                animator1.setDuration(2500);
                animator1.addUpdateListener(animation1 -> {
                    animatedValue1 = (int) animation1.getAnimatedValue();
                    colorStart = Color.rgb(255 - animatedValue1, 255 - animatedValue1, animatedValue1);
                    colorEnd = Color.rgb(255, 0, animatedValue1);
                    if (animatedValue1 == 255) {
                        ValueAnimator animator2 = ValueAnimator.ofInt(0, 255);
                        animator2.setDuration(2500);
                        animator2.addUpdateListener(animation11 -> {
                            int animatedValue2 = (int) animation11.getAnimatedValue();
                            colorStart = Color.rgb(animatedValue2, 0, 255);
                            colorEnd = Color.rgb(255 - animatedValue2, 0, 255);
                            invalidate();
                        });
                        animator2.start();
                    }
                    invalidate();
                });
                animator1.start();
            }
            invalidate();
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setShader(backGradient);
        canvas.drawRect(0, 0, width, height, paint);
    }
}