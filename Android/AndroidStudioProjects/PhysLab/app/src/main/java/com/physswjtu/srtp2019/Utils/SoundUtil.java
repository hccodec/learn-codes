/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.Utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.physswjtu.srtp2019.R;
import com.physswjtu.srtp2019.data.Settings.Settings;

import java.util.HashMap;

import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_IS_SILENT;

/**
 * 由 84697 创建
 * 日期为 2019/8/9
 * 工程 PhysLab
 */
public class SoundUtil {
    private static SoundPool sp = new SoundPool(100, AudioManager.STREAM_SYSTEM, 5);
    private static HashMap<Integer, Integer> soundMap = new HashMap<>();
    private static int[] rawId = new int[]{R.raw.alert, R.raw.notification, R.raw.notification_2, R.raw.button, R.raw.qq_care};
    public static final int ALERT = 0;
    public static final int NOTIFICATION = 1;
    public static final int NOTIFICATION2 = 2;
    public static final int BUTTON = 3;
    public static final int QQ_CARE = 4;

    static void spInit(Context context, int ResId) {
        soundMap.put(ResId, sp.load(context, rawId[ResId], 1));
    }

    /**
     * 判断是否为静音模式
     *
     * @param context 当前activity的上下文
     * @return 是否为静音模式
     */
    private static boolean notSilent(Context context) {
        AudioManager audioService = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        boolean systemAudioStatus = audioService.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;//系统设置 响铃为 true

        boolean customAudioStatus = Settings.getBoolean(KEY_IS_SILENT, false);//自定义设置 静音为true
        Log.d("hbj", systemAudioStatus && !customAudioStatus ? "响铃模式" : "静音模式");
        return systemAudioStatus && !customAudioStatus;
    }

    /**
     * 播放声音
     *
     * @param context 上下文
     * @param ResId   资源ID
     * @throws ArrayIndexOutOfBoundsException 意在提醒用已有的 tag 定位 raw 音频资源, 而非数字
     */
    public static void ding(Context context, final int ResId) throws ArrayIndexOutOfBoundsException {
        if (ResId > rawId.length)
            throw new ArrayIndexOutOfBoundsException("超出音频文件引用数组的维度！请检查数组");
        else {
            if (notSilent(context)) {
                spInit(context, ResId);
                Log.d("hbj", "OtherUtil: " + notSilent(context));
                if (soundMap.get(ResId) != null)
                    sp.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
                        try {
                            sp.play(soundMap.get(ResId), 1, 1, 0, 0, 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("hbj", "soundID出问题.数组越界异常");
                        }
                    });
            } else Log.d("hbj", "静音模式不发出声音");
        }
    }
}

