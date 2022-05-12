package com.physswjtu.srtp2019.Utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.physswjtu.srtp2019.LoginActivity;
import com.physswjtu.srtp2019.R;
import com.physswjtu.srtp2019.widgets.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.content.Context.NOTIFICATION_SERVICE;

public abstract class OtherUtil {

    private final Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    private static Integer badge = 0;

    // TODO: 2019-06-02 记得用这个系统提示音
    //Ringtone rt = RingtoneManager.getRingtone(getApplicationContext(), uri);

    public static void srtpNotification(Context context) {


        Intent mainIntent = new Intent(context, LoginActivity.class);
        PendingIntent mainPendingIntent = PendingIntent.getActivity(context, 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification.Action.Builder actionBuilder = null;
        Notification.Builder builder = new Notification.Builder(context);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            actionBuilder = new Notification.Action.Builder(Icon.createWithResource(context, R.drawable.ic_logo_location), "现在下载", mainPendingIntent);
            Log.d("hbj", "版本不低于SDK 23！");
        } else {
            Log.d("hbj", "版本低于SDK 23!");
        }

        final Notification notification;
        notification = builder.build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            builder.setColor(context.getColor(android.R.color.holo_blue_light));
        else
            builder.setColor(ContextCompat.getColor(context, android.R.color.holo_blue_light));

        builder.setContentTitle("请尽快下载资源包")
                .setContentText("请提前完成资料的下载。")
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.ic_logo_location)
                .setLargeIcon(BitmapUtil.getImageFromAssetsFile(context, "pics/timg.jpeg"))
                .setTicker("宝佳消息")
                .setContentIntent(mainPendingIntent);

        if (actionBuilder == null) {
            Toast.makeText(context, "actionBuilder不存在", Toast.LENGTH_LONG).show();
            Log.d("hbj", "actionBuilder不存在");
        } else {
            Toast.makeText(context, "actionBuilder存在", Toast.LENGTH_LONG).show();
            Log.d("hbj", "actionBuilder存在");
            builder.addAction(actionBuilder.build());
        }

        //兼容API 26， Android 8.0以上要设置通道(Channel)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel1 = new NotificationChannel("important", "重要消息", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel1.setDescription("这是一条重要消息的通知渠道，一般小事一些不容错过的重要讯息");
            notificationChannel1.setVibrationPattern(new long[]{0, 300, 400, 100});
            notificationChannel1.enableLights(true);
            notificationChannel1.setLightColor(0xFF35A352);
            notificationChannel1.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/raw/notification"), null);
            notificationManager.createNotificationChannel(notificationChannel1);
            builder.setChannelId("important");

            notificationManager.notify(1, notification);


            NotificationChannel notificationChannel2 = new NotificationChannel("sub_important", "次要消息", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel2.setDescription("这是一条次要消息的通知渠道，主要负责消息提醒和推送");
            notificationChannel2.enableLights(true);
            notificationChannel2.setLightColor(0xFFFF0233);
            notificationChannel2.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(notificationChannel2);
            builder.setChannelId("sub_important");

            new Handler().postDelayed(() -> {
                notificationManager.notify(2, notification);
                //handler.postDelayed(this, 1000);
            }, 5000);
        }
        showBadge(context, 1);
    }

    // TODO: 2019-05-28 适配各大手机厂商launcher桌面角标
    //华为角标
    @SuppressWarnings({"SpellCheckingInspection", "UnusedAssignment", "unused"})
    public static void showBadge(Context context, Integer num) {
        boolean mlsSupportedBade = true;
        try {
            if (num == 0) badge = 0;
            else badge += num;
            Log.d("hbj", badge.toString());
            Bundle bundle = new Bundle();
            bundle.putString("package", "com.physswjtu.srtp2019");
            bundle.putString("class", "com.physswjtu.srtp2019.SplashActivity");
            bundle.putInt("badgenumber", badge);
            context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bundle);
        } catch (Exception e) {
            android.widget.Toast.makeText(context, "HUAWEI showBadge()：\n非华为手机不展示桌面角标数字", Toast.LENGTH_SHORT).show();
            mlsSupportedBade = false;

        }
    }

    /**
     * MD5加密代码
     *
     * @param str 加密前的字符串
     * @return 加密后的字符串
     */
    public static String getMD5(String str) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            String md5Str = new BigInteger(1, md.digest()).toString(16);
            if (md5Str.length() < 32) {
                md5Str = 0 + md5Str;
            }
            return md5Str;
        } catch (Exception e) {
            throw new Exception("MD5加密出现错误");
        }
    }

    //全屏适配
    public static void setFullScreen(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //StatusBar透明
    public static void setStatusBar(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);//只设置这个仅实现状态栏半透明
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    public static void setStatusBar(Window window, boolean isDark) {
        setStatusBar(window);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            if (isDark) {
                window.getDecorView().setSystemUiVisibility(systemUiVisibility | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);     //color亮时
                window.getDecorView().setSystemUiVisibility(systemUiVisibility ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);   //color暗时
            }
        }
    }

    /**
     * 向主进程传递信息
     *
     * @param msg            要传递的信息
     * @param what           msg.what
     * @param customResponse 服务器返回的字符串
     */
    public static void sendMessage(Message msg, int what, String customResponse) {
        Bundle bundle = new Bundle();
        bundle.putString("customResponse", customResponse);
        msg.setData(bundle);
        sendMessage(msg, what);
    }

    static void sendMessage(Message msg, int what) {
        msg.what = what;
        msg.sendToTarget();
    }

    /**
     * 实现 InputSteam 的复用
     *
     * @param is 要复用的 InputStream
     * @return 从传入 InputStream 参数获得的 ByteArrayOutputStream
     * <p>
     * 需将其实例化为新 InputStream 对象以进行复用
     * <p>
     * 用法示例：
     * <p>
     * * InputStream is = connection.getInputStream();
     * * ByteArrayOutputStream byteArrayOutputStream = reuseInputStream(is);
     * * is.close();
     * * stream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
     * * stream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
     * * stream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
     * * ...
     * </p>
     * @throws IOException 抛出异常
     */
    public static ByteArrayOutputStream reuseInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > -1) {
            byteArrayOutputStream.write(buffer, 0, length);
        }
        byteArrayOutputStream.flush();
        return byteArrayOutputStream;
    }

    public static class ImageFilter {
        //图片缩放比例
        private static final float BITMAP_SCALE = 0.4f;

        /**
         * 模糊图片的具体方法
         *
         * @param context 上下文对象
         * @param image   需要模糊的图片
         * @return 模糊处理后的图片
         */
        static Bitmap blurBitmap(Context context, Bitmap image, float blurRadius) {
            // 计算图片缩小后的长宽
            int width = Math.round(image.getWidth() * BITMAP_SCALE);
            int height = Math.round(image.getHeight() * BITMAP_SCALE);

            // 将缩小后的图片做为预渲染的图片
            Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
            // 创建一张渲染后的输出图片
            Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

            // 创建RenderScript内核对象
            RenderScript rs = RenderScript.create(context);
            // 创建一个模糊效果的RenderScript的工具对象
            ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

            // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
            // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
            Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
            Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

            // 设置渲染的模糊程度, 25f是最大模糊度
            blurScript.setRadius(blurRadius);
            // 设置blurScript对象的输入内存
            blurScript.setInput(tmpIn);
            // 将输出数据保存到输出内存中
            blurScript.forEach(tmpOut);

            // 将数据填充到Allocation中
            tmpOut.copyTo(outputBitmap);

            return outputBitmap;
        }

    }

    /**
     * JSON 数组转化为 String 字符串 用于提交表单.
     * ** 现已更改为直接把JSON对象转成字符串
     *
     * @param object 要转化的 JSON 数组
     * @return 转化完成的 String 字符串
     * @throws JSONException
     */
    public static String Json2Form(JSONObject object) throws JSONException {
        StringBuilder result = new StringBuilder();
        Iterator keys = object.keys();
        Map<String, Object> map = new HashMap<>();
        while (keys.hasNext()) {
            String key = String.valueOf(keys.next());
            result.append(key).append("=").append(object.get(key)).append("&");
            map.put(key, object.get(key));
            Log.d("hbj", key + " " + object.get(key));
        }
//        return result.deleteCharAt(result.length() - 1).toString();
        return String.valueOf(object);
    }

    public static boolean isHaveNavigationBar(Context context) {

        boolean isHave = false;
        boolean hasNavigationBar;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) hasNavigationBar = rs.getBoolean(id);

        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                isHave = false;
            } else if ("0".equals(navBarOverride)) {
                isHave = true;
            }
        } catch (Exception e) {
            Log.w("TAG", e);
        }

        return isHave;
    }

    public static boolean isPermissionsGranted(Context context, int requestCode, String... permissions) {
        List<String> tempPermissions = new ArrayList<>();
        String[] array;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                //否则添加需请求的权限
                tempPermissions.add(permission);
            }
        }
        if (tempPermissions.size() > 0) {
            array = tempPermissions.toArray(new String[0]);
            ActivityCompat.requestPermissions((Activity) context, array, requestCode);
            return false;
        }
        Toast.makeText(context, "权限已申请", Toast.LENGTH_SHORT).show();
        return true;
    }

}
