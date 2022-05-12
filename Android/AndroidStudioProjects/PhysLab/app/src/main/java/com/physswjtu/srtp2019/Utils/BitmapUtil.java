/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.util.TypedValue;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

/**
 * 由 84697 创建
 * 日期为 2019/8/9
 * 工程 PhysLab
 */
public class BitmapUtil {


    /**
     * 将drawable转换成Bitmap
     *
     * @param drawable 要转换的drawable
     * @return 转换出的Bitmap
     */
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) return ((BitmapDrawable) drawable).getBitmap();
        return drawable2Bitmap(drawable, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    public static Bitmap drawable2Bitmap(Context context, @Nullable int ResID) {
        Drawable drawable = context.getResources().getDrawable(ResID, context.getTheme());
        return drawable2Bitmap(drawable);
    }

    public static Bitmap drawable2Bitmap(Drawable drawable, int w, int h) {
        if (drawable instanceof BitmapDrawable) return ((BitmapDrawable) drawable).getBitmap();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 从assets文件夹中读取图像
     *
     * @param fileName 要读取的文件的路径
     * @return 将读取到的图片转化成Bitmap对象
     */
    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 给Bitmap着色
     *
     * @param inBitmap  原来的Bitmap
     * @param tintColor 要着色的颜色
     * @return 着色后的Bitmap对象
     */
    public static Bitmap tintBitmap(Bitmap inBitmap, int tintColor) {
        if (inBitmap == null) return null;
        Bitmap outBitmap = Bitmap.createBitmap(inBitmap.getWidth(), inBitmap.getHeight(), inBitmap.getConfig());
        Canvas canvas = new Canvas(outBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(inBitmap, 0, 0, paint);
        return outBitmap;
    }

    public static Bitmap blurImageBitmap(Context context, Bitmap bitmap) {
        return OtherUtil.ImageFilter.blurBitmap(context, bitmap, 20f);
    }

    public static Bitmap blurImageBitmap(Context context, Bitmap bitmap, float blurRadius) {
        return OtherUtil.ImageFilter.blurBitmap(context, bitmap, blurRadius);
    }

    public static int sp2px(Context context, int size) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size, context.getResources().getDisplayMetrics());
    }

    //根据手机的分辨率从 dp 的单位 转成为 px(像素)
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 创建缩略图
     */
    public static Bitmap createThumbnail(Bitmap source, int width, int height) {
        return ThumbnailUtils.extractThumbnail(source, width, height);
    }

}
