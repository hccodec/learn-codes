/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 由 84697 创建
 * 日期为 2019/7/10
 * 工程 PhysLab
 *//**
 * 实现Activity的广播接收
 * @author LCry
 */
public class CloseActivityReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
        ((Activity)context).finish();
    }
}
