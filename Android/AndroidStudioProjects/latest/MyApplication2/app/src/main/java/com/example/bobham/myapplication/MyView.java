package com.example.bobham.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    /**
     * 1. Color
     * 2. Paint
     * 3. Canvas
     */
    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas canvas) {
        int x = 600, y = 1100;
        Paint mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(100);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        canvas.drawLine(x, y, 200 + x, 200 + y, mPaint);
        canvas.drawRect(200 + x, 500 + y, 300 + x, 300 + y, mPaint);
        canvas.drawCircle(200 + x, 200 + y, 100, mPaint);
        canvas.drawText("apple", 60 + x, 60 + y, mPaint);
        canvas.drawLine(x, 60 + y, 500 + x, 60 + y, mPaint);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.titlebar_back_press), 150 + x, 150 + y, mPaint);
        super.onDraw(canvas);
    }
}
