/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.physswjtu.srtp2019.Utils.HttpUtil;
import com.physswjtu.srtp2019.Utils.MyTask;
import com.physswjtu.srtp2019.data.SRTPBean;
import com.physswjtu.srtp2019.data.Settings.Settings;
import com.physswjtu.srtp2019.widgets.TextView;
import com.physswjtu.srtp2019.widgets.Toast;

import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.physswjtu.srtp2019.SRTPApplication.url;
import static com.physswjtu.srtp2019.Utils.HttpUtil.SUCCESS;
import static com.physswjtu.srtp2019.Utils.SoundUtil.ALERT;
import static com.physswjtu.srtp2019.Utils.SoundUtil.ding;

/**
 * 由 84697 创建
 * 日期为 2019/7/24
 * 工程 PhysLab
 */
public class TestActivity extends AppCompatActivity {

    public final Handler handler = new mHandler(this);
    HttpUtil httpUtil = new HttpUtil();
    @BindView(R.id.test_img_result)
    ImageView testImgResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        String usrType = Settings.getBoolean(Settings.KEY_USER_TYPE, true) ? "true (学生)" : "false (教师)";
        String usrId = Settings.getString(Settings.KEY_USER_ID, Settings.ERROR);
        int outputX = Settings.getInt(Settings.KEY_PIC_SIZE_WIDTH, -1);
        int outputY = Settings.getInt(Settings.KEY_PIC_SIZE_HEIGHT, -1);
        boolean isDev = Settings.getBoolean(Settings.KEY_IS_IN_DEVELOPER_MODE, false);
        String dataSource = Settings.getBoolean(Settings.KEY_DEV_DATA_SOURCE, true) ? "true (云端)" : "false (本地)";
        String serverAddress = Settings.getString(Settings.KEY_SERVER_ADDRESS, Settings.ERROR);
        String isSilent = Settings.getBoolean(Settings.KEY_IS_SILENT, false) ? "true (静音模式)" : "false (响铃模式)";
        String result = MessageFormat.format(Settings.KEY_USER_TYPE + " = {0}\n" + Settings.KEY_USER_ID + " = {1}\n" +
                        Settings.KEY_PIC_SIZE_WIDTH + " = {2}\n" + Settings.KEY_PIC_SIZE_HEIGHT + " = {3}\n" +
                        Settings.KEY_DEV_DATA_SOURCE + " = {4}\n" + Settings.KEY_SERVER_ADDRESS + " = {5}\n" +
                        Settings.KEY_IS_SILENT + " = {6}\n" + Settings.KEY_IS_IN_DEVELOPER_MODE + " = {7}\n",
                usrType, usrId, outputX, outputY, dataSource, serverAddress, isSilent, isDev);
        ((TextView) findViewById(R.id.test_temp_data)).setText(MessageFormat.format("{0}\n{1}", result, Settings.getSharedPreferences().getAll().toString()));
    }

    public void getStudentInformation(View v) throws MalformedURLException {
        httpUtil.get(String.valueOf(
                url(SRTPBean.StudentChooseExperiments.getInterface(((EditText) findViewById(R.id.test_stu_id)).getText().toString()))
        ), handler);
    }

    public void getImgInformation(View v) {
        new MyTask.imgPathTask(this, new SRTPBean.ExperimentImgs()).execute("141", "2017113903");
    }

    private static class mHandler extends Handler {
        private final WeakReference<Activity> mTarget;

        mHandler(Activity activity) {
            mTarget = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity activity = mTarget.get();
            String customResponse = msg.getData().getString("customResponse");
            Log.d("hbj", "TestActivity(): " + customResponse);
            if (msg.what == SUCCESS && !((EditText) activity.findViewById(R.id.test_stu_id)).getText().toString().isEmpty()) {
                SRTPBean.StudentChooseExperiments studentChooseExperiments = new SRTPBean.StudentChooseExperiments(customResponse);
                ((TextView) activity.findViewById(R.id.test1)).setText(String.format("实验名是：\t\t%s", studentChooseExperiments.getExpName(0)));
                ((TextView) activity.findViewById(R.id.test2)).setText(MessageFormat.format("周数是：\t\t{0}", studentChooseExperiments.getWeek(0)));
                ((TextView) activity.findViewById(R.id.test3)).setText(MessageFormat.format("成绩是：\t\t{0}", studentChooseExperiments.getGrade(0)));
                ((TextView) activity.findViewById(R.id.test4)).setText(MessageFormat.format("时间段是：\t\t{0}", studentChooseExperiments.getTime(0)));
                ((TextView) activity.findViewById(R.id.test5)).setText(MessageFormat.format("课程编号是：\t\t{0}", studentChooseExperiments.getCourseId(0)));
                ((TextView) activity.findViewById(R.id.test6)).setText(MessageFormat.format("星期是：\t\t周{0}", studentChooseExperiments.getDay(0)));
                ((TextView) activity.findViewById(R.id.test7)).setText(MessageFormat.format("教室是：\t\tX{0}", studentChooseExperiments.getRoomId(0)));
                ((TextView) activity.findViewById(R.id.test8)).setText(MessageFormat.format("emmm是：\t\t{0}", studentChooseExperiments.getExpId(0)));
                ((TextView) activity.findViewById(R.id.test_result)).setText(customResponse);
            } else {
                Toast.makeText(activity, customResponse, Toast.LENGTH_SHORT).show();
                ding(activity, ALERT);
            }
        }
    }
}
