/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.physswjtu.srtp2019.CameraActivity;
import com.physswjtu.srtp2019.CaptureActivity;
import com.physswjtu.srtp2019.R;
import com.physswjtu.srtp2019.data.Settings.Settings;
import com.physswjtu.srtp2019.widgets.Dialogs;
import com.physswjtu.srtp2019.widgets.Toast;

import java.io.File;
import java.text.MessageFormat;

import static com.physswjtu.srtp2019.Utils.FileUtil.fileToUri;
import static com.physswjtu.srtp2019.Utils.OtherUtil.isPermissionsGranted;
import static com.physswjtu.srtp2019.Utils.SoundUtil.ALERT;
import static com.physswjtu.srtp2019.Utils.SoundUtil.ding;

/**
 * 由 84697 创建
 * 日期为 2019/7/11
 * 工程 PhysLab
 */
public class CameraUtil {
    public static final int REQUEST_CODE_PICK_PHOTO = 0;
    public static final int REQUEST_CODE_SYSTEM_CAMERA = 1;
    public static final int REQUEST_CODE_CAPTURE = 2;
    public static final int REQUEST_CODE_CUSTOM_CAMERA = 3;
    private static File mImageCapture = null; //文件名
    private static Uri mImageCaptureUri = null; //文件名
    private static File mTemp = null;
    private static Uri mTempUri = null;

    /**
     * 相册照片的临时文件
     */
    public static Uri getTempUri() {
        return mTempUri;
    }

    public static File getmTemp() {
        return mTemp;
    }

    public static void initUris(Context context) {
        mImageCapture = new File(context.getExternalFilesDir("pics"), "capture.jpg");
        mImageCaptureUri = fileToUri(context, mImageCapture);
        mTemp = new File(context.getExternalCacheDir(), "mTempUri.jpg");
        mTempUri = fileToUri(context, mTemp);
    }

    public static Uri getmImageCaptureUri() {
        return mImageCaptureUri;
    }

    private static void askForDifferentCameras(Context context) {
        Dialogs.SRTPAlertDialog.Builder builder = new Dialogs.SRTPAlertDialog.Builder(context);
        final Dialogs.SRTPAlertDialog[] dialog = {builder.create()};
        String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        View.OnClickListener listener = v -> {
            if (isPermissionsGranted(context, REQUEST_CODE_SYSTEM_CAMERA, permissions))
                startSystemCamera(context);// 101 系统相机
        };
        View.OnClickListener listener2 = v -> {
            android.widget.Toast.makeText(context, "暂仅开放系统相机拍照功能", Toast.LENGTH_SHORT).show();
            ding(context, ALERT);
//            if (isPermissionsGranted(context, REQUEST_CODE_CUSTOM_CAMERA, permissions))
//                startCustomCamera(context); // 100 自定义相机
        };
        builder.setTitle("拍照上传作业").setIcon(R.string.fa_camera_retro_before).setMessage("请选择您要启动的相机类别：").setButton("系统", listener).setButton2("软件", listener2);
        dialog[0].show();
    }

    public static void askForDifferentMethods(Context context) {
        Dialogs.SRTPAlertDialog.Builder builder = new Dialogs.SRTPAlertDialog.Builder(context);
        final Dialogs.SRTPAlertDialog[] dialog = {builder.create()};
        View.OnClickListener listener = v -> {
            android.widget.Toast.makeText(context, "打开系统图库", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setType("image/*");
            ((Activity) context).startActivityForResult(intent, CameraUtil.REQUEST_CODE_PICK_PHOTO);
        };
        View.OnClickListener listener2 = v -> {
            askForDifferentCameras(context);// 101 系统相机
        };
        builder.setTitle("上传作业图片").setIcon(R.string.fa_camera_retro_before).setMessage("请选择方式：").setButton("图库", listener).setButton2("拍照", listener2);
        dialog[0].show();
    }

    //todo 系统相机
    public static void startSystemCamera(Context context) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.d("hbj", "mImageCaptureUri: " + mImageCaptureUri.toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_SYSTEM_CAMERA);
        ((Activity) context).overridePendingTransition(R.anim.scale_fade_in, R.anim.none);
    }

    // todo 自定义相机
    public static void startCustomCamera(Context context) {
        Toast.makeText(context, "让我们切换到拍照界面", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, CameraActivity.class);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_CUSTOM_CAMERA);
        ((Activity) context).overridePendingTransition(R.anim.scale_fade_in, R.anim.none);
    }

    // todo 扫码
    public static void startCaptureActivity(Context context) {
        Intent intent = new Intent(context, CaptureActivity.class);
        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_CAPTURE);
    }


    public static void routeToCrop(Context context, Intent data, int requestCode) {
        new Thread() {
            public void run() {
                Intent intent = new Intent("com.android.camera.action.CROP");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                if (requestCode == REQUEST_CODE_SYSTEM_CAMERA) {
                    Log.d("hbj", "routeToCrop (SYSTEM_CAMERA): " + mImageCaptureUri);
                    intent.setDataAndType(mImageCaptureUri, "image/*");
                }
                if (requestCode == REQUEST_CODE_CUSTOM_CAMERA) {
                    if (data == null) throw new NullPointerException("data为空");
                    Uri uri;
                    //uri = mImageCaptureUri;
                    /* content://com.physswjtu.srtp2019.fileProvider/external_files/Android/data/com.physswjtu.srtp2019/filesCustom1563606470391.jpg
                                        huawei: 无法加载此图片
                                        API27: OK
                                        API29: OK */
                    uri = fileToUri(context, new File(data.getExtras().getString("path")));
                    /* file:///storage/emulated/0/Android/data/com.physswjtu.srtp2019/filesCustom1563606523119.jpg
                                        huawei: 无法加载此图片
                                         API29: OK  */
                    //uri = Uri.fromFile(new File(data.getExtras().getString("path")));
                    /* /storage/emulated/0/Android/data/com.physswjtu.srtp2019/filesCustom1563606610227.jpg
                                        huawei: 无法加载此图片
                                        API29: 发生错误，无法加载媒体 */
                    //uri = Uri.parse(data.getExtras().getString("path"));
                    Log.d("hbj", "routeToCrop (CUSTOM_CAMERA): " + uri);
                    intent.setDataAndType(uri, "image/*");
                }
                if (requestCode == REQUEST_CODE_PICK_PHOTO) {
                    Uri uri = data.getData();
                    intent.setDataAndType(uri, "image/*");
                }
                intent.putExtra("crop", "true");
                // aspectX,aspectY 是宽高的比例，这里设置正方形
                String sss = android.os.Build.MODEL;
                Log.d("hbj", "routeToCrop(): " + "android.os.Build.MODEL 是 " + sss);
                //裁剪图片宽高
                int outputX;
                int outputY;
                outputX = Settings.getInt(Settings.KEY_PIC_SIZE_WIDTH, -1);
                outputY = Settings.getInt(Settings.KEY_PIC_SIZE_HEIGHT, -1);
                if (sss.contains("BKL-AL20") || sss.contains("HUAWEI")) {//华为特殊处理 不然会显示圆
                    intent.putExtra("aspectX", outputX);
                    intent.putExtra("aspectY", outputY);
                } else {
                    intent.putExtra("aspectX", outputX);
                    intent.putExtra("aspectY", outputY);
                }
                Log.d("hbj", MessageFormat.format("宽{0}高{1}", outputX, outputY));
                if (outputX != -1)
                    intent.putExtra("outputX", outputX);
                if (outputY != -1)
                    intent.putExtra("outputY", outputY);
                intent.putExtra("scale", true);
                //注意：此处应设置return-data为false
                // 如果设置为true，是直接返回bitmap格式的数据，耗费内存。
                intent.putExtra("return-data", false);
                intent.putExtra("noFaceDetection", true);//不检测面部
                intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTemp));
                intent.putExtra("output", Uri.fromFile(mTemp));
                ((Activity) context).startActivityForResult(intent, 8);
            }
        }.start();
    }
}
