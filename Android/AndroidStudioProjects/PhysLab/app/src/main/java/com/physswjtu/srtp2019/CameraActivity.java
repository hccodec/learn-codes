/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.physswjtu.srtp2019.Utils.OtherUtil;
import com.physswjtu.srtp2019.Utils.SoundUtil;
import com.physswjtu.srtp2019.widgets.Dialogs;
import com.physswjtu.srtp2019.widgets.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.List;

import static com.physswjtu.srtp2019.Utils.FileUtil.getSpecificFilePath;
import static com.physswjtu.srtp2019.Utils.SoundUtil.ALERT;


/**
 * 由 84697 创建
 * 日期为 2019/7/12
 * 工程 PhysLab
 */
public class CameraActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    CameraHelper cameraHelper;
    SurfaceHolder surfaceHolder;
    TextView captureBtn;
    static Dialog dialog;
    private final Handler handler = new mHandler(this);

    private static class mHandler extends Handler {
        private final WeakReference<Activity> mTarget;

        mHandler(Activity activity) {
            mTarget = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (dialog != null) {
                dialog.dismiss();
                dialog = null;
            }
            Activity activity = mTarget.get();
            if (msg.what == 12) {
                Log.d("hbj", "这是" + msg.what);
                String customResponse = msg.getData().getString("customResponse");
                if (!(customResponse != null && customResponse.equals("OK"))) {
                    SoundUtil.ding(activity, ALERT);
                    // TODO: 2019/6/18 登录按钮
                    Log.d("hbj", "最新消息 " + customResponse);
                    nice(activity, customResponse);
                }
            }
        }
    }

    public static void nice(Activity activity, String path) {
        Intent intent = new Intent();
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        Bundle bundle = new Bundle();
        bundle.putString("path", path);
        if (path != null) Log.d("hbj", "initUris(): " + path);
        else throw new NullPointerException("kokongkgnokgn");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtras(bundle);
        activity.setResult(RESULT_OK, intent);
        activity.finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        surfaceView = findViewById(R.id.surface_view);
        cameraHelper = new CameraHelper(this, surfaceView);
        captureBtn = findViewById(R.id.capture_image_button);
        cameraHelper.init();
    }


    class CameraHelper implements Camera.PreviewCallback {

        private Camera mCamera = null;
        private Camera.Parameters mParameters;
        private SurfaceView mSurfaceView;
        private SurfaceHolder mSurfaceHolder;
        String path;
        File file;

        Activity mActivity;
        //CallBack mCallBack = null;

        int mCameraFacing = Camera.CameraInfo.CAMERA_FACING_BACK;
        int mDisplayOrientation = 0;

        private int picWidth = 2160;
        private int picHeight = 3840;

        CameraHelper(Activity activity, SurfaceView surfaceView) {
            mActivity = activity;
            mSurfaceView = surfaceView;
            mSurfaceHolder = mSurfaceView.getHolder();
        }

        void init() {
            try {
                file = new File(getSpecificFilePath(CameraActivity.this, null) + "Custom" + System.currentTimeMillis() + ".jpg");
                //改为如下语句后报错：java.lang.IllegalArgumentException: Failed to find configured root that contains /SRTP_files/Android/data/com.physswjtu.srtp2019/filesCustom1564211022333.jpg
                //path = fileToUri(CameraActivity.this, file).getPath();
                path = file.getPath();
            } catch (IOException e) {
                e.printStackTrace();
            }
            captureBtn.setOnClickListener(v -> {
                cameraHelper.takePic();
                dialog = new Dialogs.loading(CameraActivity.this, "请稍后……");
                dialog.show();
            });
            mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    if (openCamera(mCameraFacing)) startPreview();
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    if (mSurfaceHolder.getSurface() != null) {
                        mCamera.stopPreview();
                        startPreview();
                    }

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    // TODO: 2019/7/13 释放相机资源
                    // 回收Camera资源
                    if (cameraHelper.mCamera != null) {
                        cameraHelper.mCamera.stopPreview();
                        cameraHelper.mCamera.setPreviewCallback(null);
                        cameraHelper.mCamera.release();
                        cameraHelper.mCamera = null;
                    }
                }
            });
        }

        private boolean openCamera(int cameraFacing) {
            //cameraFacing = Camera.CameraInfo.CAMERA_FACING_BACK;
            boolean supportCameraFacing = supportCameraFacing(cameraFacing);
            if (supportCameraFacing) {
                try {
                    mCamera = Camera.open(cameraFacing);
                    initParameters(mCamera);
                    mCamera.setPreviewCallback(this);
                } catch (Exception e) {
                    Toast.makeText(mActivity, "打开相机失败", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            return supportCameraFacing;
        }

        /**
         * 判断是否支持某个相机
         *
         * @param cameraFacing 相机朝向
         * @return
         */
        private boolean supportCameraFacing(int cameraFacing) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            for (int i = 0; i <= Camera.getNumberOfCameras(); i++) {
                Camera.getCameraInfo(i, info);
                if (info.facing == cameraFacing) return true;
            }
            return false;
        }

        private void initParameters(Camera camera) {
            try {
                mParameters = camera.getParameters();
                mParameters.setPreviewFormat(ImageFormat.NV21);

                Camera.Size bestPreviewSize = getBestSize(1, mSurfaceView.getWidth(), mSurfaceView.getHeight(), mParameters.getSupportedPreviewSizes());
                Camera.Size bestPicSize = getBestSize(2, picWidth, picHeight, mParameters.getSupportedPictureSizes());
                mParameters.setPreviewSize(bestPreviewSize.width, bestPreviewSize.height);
                mParameters.setPictureSize(bestPicSize.width, bestPicSize.height);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(mActivity, "相机初始化失败!", Toast.LENGTH_SHORT).show();
            }
        }

        private Camera.Size getBestSize(int a, int targetWidth, int targetHeight, List<Camera.Size> sizeList) {
            String TAG = a == 1 ? "预览尺寸 - " : "图片尺寸 - ";
            Camera.Size bestSize = null;
            double targetRatio = (1.0 * targetHeight / targetWidth);
            double minDiff = targetRatio;
            for (int size = 0; size < sizeList.size(); size++) {
                double supportedRatio = (sizeList.get(size).width * 1.0 / sizeList.get(size).height);
                //Log.d("hbj", TAG + "系统支持的尺寸 " + size + "  : " + sizeList.get(size).height + " * " + sizeList.get(size).width + " ,    比例" + supportedRatio);
            }
            for (int size = 0; size < sizeList.size(); size++) {
                if (sizeList.get(size).width == targetHeight && sizeList.get(size).height == targetWidth) {
                    bestSize = sizeList.get(size);
                    break;
                }
                double supportedRatio = (1.0 * sizeList.get(size).width / sizeList.get(size).height);
                if (Math.abs(supportedRatio - targetRatio) < minDiff) {
                    minDiff = Math.abs(supportedRatio - targetHeight);
                    bestSize = sizeList.get(size);
                }
            }
            Log.d("hbj", TAG + "目标尺寸 ：" + targetWidth + " * " + targetHeight + " ，   比例  " + targetRatio);
            if (bestSize != null) {
                Log.d("hbj", TAG + "最优尺寸 ：" + bestSize.height + " * " + bestSize.width);
            } else Log.d("hbj", TAG + "莫得最优尺寸 ：");
            return bestSize;
        }

        private void startPreview() {
            try {
                mCamera.setPreviewDisplay(mSurfaceHolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setCameraDisplayOrientation(mActivity);
            mCamera.startPreview();
        }

        private void setCameraDisplayOrientation(Activity activity) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(mCameraFacing, info);
            int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
            int screenDegree = 0;
            switch (rotation) {
                case Surface.ROTATION_0:
                    screenDegree = 0;
                    break;
                case Surface.ROTATION_90:
                    screenDegree = 90;
                    break;
                case Surface.ROTATION_180:
                    screenDegree = 180;
                    break;
                case Surface.ROTATION_270:
                    screenDegree = 270;
                    break;
            }
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                mDisplayOrientation = (info.orientation + screenDegree) % 360;
                mDisplayOrientation = (360 - mDisplayOrientation) % 360;
            } else mDisplayOrientation = (info.orientation - screenDegree + 360) % 360;
            mCamera.setDisplayOrientation(mDisplayOrientation);
            Log.d("hbj", "屏幕的旋转角度 : " + rotation);
            Log.d("hbj", "setDisplayOrientation(result) : " + mDisplayOrientation);
        }

        void takePic() {
            mCamera.takePicture(null, null, (data, camera) -> {
                final Bitmap[] result = new Bitmap[1];
                new Thread() {
                    public void run() {
                        if (data != null && data.length > 0) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            Matrix matrix = new Matrix();
                            int rotation = mDisplayOrientation;
                            if (mCameraFacing == Camera.CameraInfo.CAMERA_FACING_BACK)
                                matrix.setRotate(rotation);
                            else {
                                rotation = (360 - rotation) % 360;
                                matrix.setRotate(rotation);
                                matrix.postScale(-1, 1);
                            }
                            result[0] = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                            try {
                                Log.d("hbj", "takePic(): " + "拍下来的图片的保存位置: " + path);
                                Log.d("hbj", "喂喂喂");
                                OutputStream out = new FileOutputStream(path);
                                if (result[0].compress(Bitmap.CompressFormat.JPEG, 100, out)) {
                                    out.close();
                                    Log.d("hbj", "takePic(): " + "文件流正确关闭");
                                    Message msg = handler.obtainMessage();
                                    OtherUtil.sendMessage(msg, 12, path);
                                    Log.d("hbj", "takePic(): " + "图片已保存");
                                } else Log.e("hbj", "takePic(): " + "文件流未正确关闭");
                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.e("hbj", "takePic(): " + "图片保存失败");
                            }
                        }
                    }
                }.start();
            });
        }

        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
        }

    }

}