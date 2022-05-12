/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * 由 84697 创建
 * 日期为 2019/7/10
 * 工程 PhysLab
 */
public class MyReceiver extends BroadcastReceiver {
    //public MyReceiver(){}

    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Uri uri = intent.getData();
        String host = uri.getHost();

        Log.d("hbj", TAG + "指令输入");
        if ("6221".equals(host)) {
            Intent i = new Intent(context, BrowserActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
