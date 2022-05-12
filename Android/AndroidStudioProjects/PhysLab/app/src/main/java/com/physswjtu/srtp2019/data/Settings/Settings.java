/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.data.Settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.content.Context.MODE_PRIVATE;

/**
 * 由 84697 创建
 * 日期为 2019/7/30
 * 工程 PhysLab
 */
public class Settings {
    //*******************************************设置项*******************************************//

    /**
     * @hide
     */
    @StringDef(value = {
            KEY_PIC_SIZE_WIDTH, KEY_PIC_SIZE_HEIGHT, KEY_TOKEN,
            KEY_USER_ID, KEY_USER_TYPE, KEY_IS_SILENT, KEY_DEV_DATA_SOURCE,
            KEY_SERVER_ADDRESS, KEY_IS_IN_DEVELOPER_MODE, DEVELOPER_PASSWD
    })
    @Retention(RetentionPolicy.SOURCE)
    private @interface SRTPSettings {
        String param1() default "";
    }

    public static final String KEY_PIC_SIZE_WIDTH = "picWidth";     //图片高，int
    public static final String KEY_PIC_SIZE_HEIGHT = "picHeight";   //图片宽，int

    public static final String ERROR = "error";
    public static final String KEY_USER_ID = "usrName";             //用户ID，String
    public static final String KEY_USER_TYPE = "usrType";           //用户类型，boolean(生/师)
    public static final String KEY_TOKEN = "token";                 //token
    public static final String KEY_IS_SILENT = "isSilent";          //是否静音，boolean(静音/响铃)
    public static final String KEY_DEV_DATA_SOURCE = "dataSource";  //数据来源，boolean(云端/本地)
    public static final String KEY_SERVER_ADDRESS = "server";       //服务器地址，String
    public static final String KEY_IS_IN_DEVELOPER_MODE = "dev";    //是否为开发者模式
    //*******************************************设置项*******************************************//
    public static final String SERVER_ADDRESS_DEFAULT = "http://222.18.57.34:10001";
    public static final String DEVELOPER_PASSWD = "srtp6221";       //开发者密码
    //*******************************************设置项*******************************************//
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public static void initSettings(Context context, String name) {
        sharedPreferences = context.getSharedPreferences(name, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static void putBoolean(@SRTPSettings String key, boolean value, boolean needsConfirming) {
        if (needsConfirming) editor.putBoolean(key, value).apply();
        else editor.putBoolean(key, value).commit();
    }

    public static void putInt(@SRTPSettings String key, int value, boolean needsConfirming) {
        if (needsConfirming) editor.putInt(key, value).apply();
        else editor.putInt(key, value).commit();
    }

    public static void putString(@SRTPSettings String key, String value, boolean needsConfirming) {
        if (needsConfirming) editor.putString(key, value).apply();
        else editor.putString(key, value).commit();
    }

    public static void putLong(@SRTPSettings String key, long value, boolean needsConfirming) {
        if (needsConfirming) editor.putLong(key, value).apply();
        else editor.putLong(key, value).commit();
    }

    public static void putFloat(@SRTPSettings String key, float value, boolean needsConfirming) {
        if (needsConfirming) editor.putFloat(key, value).apply();
        else editor.putFloat(key, value).commit();
    }

    public static boolean getBoolean(@SRTPSettings String key, boolean defValue) {
        Log.d("hbj", "Settings: getBoolean(): " + sharedPreferences.getBoolean(key, defValue));
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static int getInt(@SRTPSettings String key, int defValue) {
        Log.d("hbj", "Settings: getBoolean(): " + sharedPreferences.getInt(key, defValue));
        return sharedPreferences.getInt(key, defValue);
    }

    public static String getString(@SRTPSettings String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public static Float getFloat(@SRTPSettings String key, Float defValue) {
        Log.d("hbj", "Settings: getBoolean(): " + sharedPreferences.getFloat(key, defValue));
        return sharedPreferences.getFloat(key, defValue);
    }

    public static Long getLong(@SRTPSettings String key, Long defValue) {
        Log.d("hbj", "Settings: getBoolean(): " + sharedPreferences.getLong(key, defValue));
        return sharedPreferences.getLong(key, defValue);
    }

    public static void deletePreference(@SRTPSettings String key, boolean needsConfirming) {
        if (needsConfirming) editor.remove(key).apply();
        else editor.remove(key).commit();
    }

    public static void wipeData() {
        editor.clear().commit();
    }

    public static void confirmSettings() {
        editor.commit();
    }


}
