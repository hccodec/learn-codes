/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.physswjtu.srtp2019.R;


public class TextView extends AppCompatTextView {

    Context context;

    public TextView(Context context) {
        super(context);
        this.context = context;
    }

    public TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextView);
            boolean isIconFont = a.getBoolean(R.styleable.TextView_isIconFont, false);
            if (isIconFont) {
                Typeface iconFont = Typeface.createFromAsset(context.getAssets(), "font/iconfont.ttf");
                setTypeface(iconFont);
            }
            a.recycle();
        }
    }

    public void setIsIconFont() {
        Typeface iconFont = Typeface.createFromAsset(context.getAssets(), "font/iconfont.ttf");
        setTypeface(iconFont);
    }
}
