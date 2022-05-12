package com.physswjtu.srtp2019.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class baseView extends View {

    private Paint paint = new Paint();
    public baseView(Context context) {
        super(context);
    }
    public baseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public baseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        paint.setColor(0x34534534);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        canvas.drawCircle(160, 160, 160, paint);
    }

}
