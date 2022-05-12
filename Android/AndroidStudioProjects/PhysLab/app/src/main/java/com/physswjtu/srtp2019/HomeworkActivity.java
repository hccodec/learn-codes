/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.physswjtu.srtp2019.Utils.HttpUtil;
import com.physswjtu.srtp2019.Utils.MyTask;
import com.physswjtu.srtp2019.Utils.OtherUtil;
import com.physswjtu.srtp2019.Utils.SoundUtil;
import com.physswjtu.srtp2019.data.HomeworkAdapter;
import com.physswjtu.srtp2019.widgets.Dialogs;
import com.physswjtu.srtp2019.widgets.TextView;
import com.physswjtu.srtp2019.widgets.Toast;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.physswjtu.srtp2019.Utils.CameraUtil.REQUEST_CODE_CAPTURE;
import static com.physswjtu.srtp2019.Utils.CameraUtil.REQUEST_CODE_CUSTOM_CAMERA;
import static com.physswjtu.srtp2019.Utils.CameraUtil.REQUEST_CODE_PICK_PHOTO;
import static com.physswjtu.srtp2019.Utils.CameraUtil.REQUEST_CODE_SYSTEM_CAMERA;
import static com.physswjtu.srtp2019.Utils.CameraUtil.getTempUri;
import static com.physswjtu.srtp2019.Utils.CameraUtil.routeToCrop;
import static com.physswjtu.srtp2019.Utils.CameraUtil.startCaptureActivity;
import static com.physswjtu.srtp2019.Utils.CameraUtil.startCustomCamera;
import static com.physswjtu.srtp2019.Utils.CameraUtil.startSystemCamera;
import static com.physswjtu.srtp2019.Utils.FileUtil.fileToUri;
import static com.physswjtu.srtp2019.Utils.FileUtil.getSpecificFilePath;


/**
 * 由 84697 创建
 * 日期为 2019/7/30
 * 工程 PhysLab
 */
public class HomeworkActivity extends BaseActivity {
    @BindView(R.id.item_homework)
    RecyclerView itemHomework;
    @BindView(R.id.home)
    TextView home;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.submit)
    public Button submit;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.deadline_result)
    public TextView deadlineResult;
    private HomeworkAdapter adapter;
    Dialog loading;
    public static List<File> fileList = new ArrayList<>();
    public static List<Integer> list2 = new ArrayList<>();
    int a = 0;

    String stuId;
    String courseId;
    String expName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        ButterKnife.bind(this);
        OtherUtil.setStatusBar(getWindow());
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
        init();
    }

    public void init() {
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            stuId = (String) bundle.get("stuId");
            courseId = (String) bundle.get("courseId");
            expName = (String) bundle.get("expName");
        }
        adapter = new HomeworkAdapter(this);
        itemHomework.setLayoutManager(new GridLayoutManager(this, 6));
        itemHomework.setAdapter(adapter);
        title.setText(MessageFormat.format("上传作业 - {0}", expName));
        title.setTextSize(15);
        new hasReported(this, fileList).execute(courseId, stuId);
        new MyTask.getDeadline(this).execute(courseId);
    }

    private static class hasReported extends MyTask.hasReported {
        private final WeakReference<Activity> mTarget;

        private hasReported(Activity activity, List<File> fileList) {
            super(activity, fileList);
            mTarget = new WeakReference<>(activity);
        }

        @Override
        public void postExecutive(Object o) {
            InputStream stream;
            if (o instanceof String) {
                Toast.makeText(mTarget.get(), (String) o, android.widget.Toast.LENGTH_SHORT).show();
            } else if (o instanceof ByteArrayOutputStream) {
                stream = new ByteArrayInputStream(((ByteArrayOutputStream) o).toByteArray());
                if (HttpUtil.streamToString(stream).equals("true"))
                    ((HomeworkActivity) mTarget.get()).submit.setText("实验报告已经提交");
                else ((HomeworkActivity) mTarget.get()).submit.setText("确定");
            }
        }
    }

    // 拿到照片
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("hbj", "MainActivity: onActivityResult(): " + "requestCode:" + requestCode
                + "    resultCode: " + (resultCode == RESULT_OK ? "RESULT_OK" : (resultCode == RESULT_CANCELED ? "RESULT_CANCELED" : requestCode))
                + "      data: " + data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CUSTOM_CAMERA)
                routeToCrop(this, data, REQUEST_CODE_CUSTOM_CAMERA);
            else if (requestCode == REQUEST_CODE_SYSTEM_CAMERA)
                routeToCrop(this, data, REQUEST_CODE_SYSTEM_CAMERA);
            else if (requestCode == REQUEST_CODE_PICK_PHOTO) {
                routeToCrop(this, data, REQUEST_CODE_PICK_PHOTO);
            } else if (requestCode == REQUEST_CODE_CAPTURE) {
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) return;
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED)
                        Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            } else if (requestCode == 8)
                new Thread() {
                    public void run() {
                        try {
                            //（新文件）
                            File file2 = new File(getSpecificFilePath(HomeworkActivity.this, null) + "/hbj2-" + System.currentTimeMillis() + ".jpg");
//                            Log.d("hbj", "MainActivity: " + "onActivityResult(): " + "新文件名是  " + file2.getPath());
                            OutputStream out = new FileOutputStream(file2.getPath());
                            //压缩文件，返回结果
                            Log.d("hbj", "MainActivity: " + "onActivityResult(): " + "getTempUri(): " + getTempUri().getPath());
                            Bitmap photo1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(getTempUri()));
                            // photo1 = Bitmap.createScaledBitmap(photo1, 800, 900, true);
                            //Bitmap photo1 = data.getExtras().getParcelable("data");
                            if (photo1.compress(Bitmap.CompressFormat.JPEG, 50, out)) {
                                out.close();
                                Log.d("hbj", "MainActivity: " + "onActivityResult(): " + "文件正确关闭");
                            } else
                                Log.e("hbj", "MainActivity: " + "onActivityResult(): " + "文件未正确关闭");
                            runOnUiThread(() -> {
                                adapter.add(fileToUri(HomeworkActivity.this, file2));
                                fileList.add(file2);
                                list2.add(a++);
                                Log.d("hbj2", fileList.toString());
                                Log.d("hbj2", list2.toString());
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
        }
    }

    /**
     * 处理请求权限的响应，当用户对请求权限的dialog做出响应之后,系统会回调该函数(Activity或者Fragment中重写)
     *
     * @param requestCode  申请权限对应的requestCode
     * @param permissions  权限列表
     * @param grantResults 权限列表对应的返回值，判断permissions里面的每个权限是否申请成功
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("hbj", requestCode + "    " + permissions[0] + "      " + grantResults[0]);
        boolean result = true;
        for (int grantResult : grantResults)
            if (grantResult != 0) {
                result = false;
                android.widget.Toast.makeText(this, "有相机权限才能继续进行哦", Toast.LENGTH_SHORT).show();
                SoundUtil.ding(this, SoundUtil.ALERT);
                break;
            }
        if (result)
            switch (requestCode) {
                case REQUEST_CODE_CUSTOM_CAMERA:
                    startCustomCamera(this);
                    break;
                case REQUEST_CODE_SYSTEM_CAMERA:
                    startSystemCamera(this);
                    break;
                case REQUEST_CODE_CAPTURE:
                    startCaptureActivity(this);
                    break;
            }

    }

    @OnClick({R.id.home, R.id.toolbar, R.id.item_homework, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home:
                finish();
                break;
            case R.id.toolbar:
                break;
            case R.id.submit:
                if (submit.getText().equals("取消")) ;
                else if (fileList.size() == 0)
                    Toast.makeText(this, "请选择文件上传", Toast.LENGTH_SHORT).show();
                else
                    uploadHomeWork(stuId, courseId, fileList);
                break;
        }
    }

    public void uploadHomeWork(String stuId, String courseId, List<File> fileList) {
        new MyTask.hasReported(this, fileList).execute(courseId, stuId);
    }

    public static void picPreview(Context context, int position) {
        Dialogs.SRTPAlertDialog.BuilderPic builder = new Dialogs.SRTPAlertDialog.BuilderPic(context);
        final Dialogs.SRTPAlertDialog dialog = builder.create();
        builder.setTitle("图片预览").setIcon(R.string.fa_picture_o_before)
                .setmPic(fileToUri(context, fileList.get(position))).setMessage(position + ".jpg");
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        fileList = new ArrayList<>();
        list2 = new ArrayList<>();
        super.onDestroy();
    }

}
