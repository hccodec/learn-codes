/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019;

import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import com.physswjtu.srtp2019.Utils.CameraUtil;
import com.physswjtu.srtp2019.Utils.HTTPTasks;
import com.physswjtu.srtp2019.data.Settings.Settings;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.net.MalformedURLException;
import java.net.URL;

import static com.physswjtu.srtp2019.data.Settings.Settings.ERROR;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_PIC_SIZE_HEIGHT;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_PIC_SIZE_WIDTH;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_SERVER_ADDRESS;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_USER_ID;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_USER_TYPE;
import static com.physswjtu.srtp2019.data.Settings.Settings.SERVER_ADDRESS_DEFAULT;
import static com.physswjtu.srtp2019.data.Settings.Settings.putInt;

public class SRTPApplication extends androidx.multidex.MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        CameraUtil.initUris(this);
        ZXingLibrary.initDisplayOpinion(this);
        Settings.initSettings(this, "fundamental");
        putInt(KEY_PIC_SIZE_WIDTH, 900, false);
        putInt(KEY_PIC_SIZE_HEIGHT, 1600, false);
        Log.d("hbj", TAG + Settings.getString(KEY_USER_ID, ERROR) + "   " + Settings.getBoolean(KEY_USER_TYPE, true));
    }

    public static final String TAG = SRTPApplication.class.getSimpleName() + ": ";

    /**
     * 根据要访问的 WEB 接口生成 url 对象
     *
     * @param service 要访问的接口
     * @return 生成的 URL 对象
     */
    public static URL url(String service) throws MalformedURLException {
        //服务器地址
        return new URL(Settings.getString(KEY_SERVER_ADDRESS, SERVER_ADDRESS_DEFAULT) + "/" + service);
    }

    public static URL url(URL service) throws MalformedURLException {
        String s = service.getPath();
        return url(s);
    }
}
