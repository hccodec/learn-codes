/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.Utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.physswjtu.srtp2019.HomeworkActivity;
import com.physswjtu.srtp2019.LoginActivity;
import com.physswjtu.srtp2019.MainActivity;
import com.physswjtu.srtp2019.R;
import com.physswjtu.srtp2019.SRTPApplication;
import com.physswjtu.srtp2019.data.SRTPBean;
import com.physswjtu.srtp2019.data.Settings.Settings;
import com.physswjtu.srtp2019.widgets.Dialogs;
import com.physswjtu.srtp2019.widgets.Toast;

import org.json.JSONException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.List;

import static com.physswjtu.srtp2019.SRTPApplication.url;
import static com.physswjtu.srtp2019.Utils.HttpUtil.ContentType_JSON;
import static com.physswjtu.srtp2019.Utils.HttpUtil.ContentType_MULTIPART;
import static com.physswjtu.srtp2019.Utils.HttpUtil.ContentType_TXT;

/**
 * 由 84697 创建
 * 日期为 2019/8/10
 * 工程 PhysLab
 * <p>
 * 各种 网络请求 以及 耗时任务
 * <p>
 * 所有继承自 {@link HTTPTasks.doGet} 的请求接受的参数都只为 url
 * 故该类下的所有子类重写其下的 {@link HTTPTasks.doGet#postUrl(Object...)} 方法将参数按照接口文档中的定义规范成url
 *
 * @see HTTPTasks.doGet#postUrl(Object...)
 */
public class MyTask {
    interface SRTPTask {
    }

    //获取学生或教师信息, 从 execute 方法中接受的参数: o[0] id
    public static class getRoleInformation extends HTTPTasks.doGet implements SRTPTask {
        private final WeakReference<Activity> mTarget;
        SRTPBean.Role role;

        public getRoleInformation(Activity activity, SRTPBean.Role role) {
            mTarget = new WeakReference<>(activity);
            this.role = role;
        }

        /**
         * @param o 从 execute 方法中接受的参数: o[0] id
         * @return 生成的URL
         */
        @Override
        public URL postUrl(Object... o) throws MalformedURLException {
            return url(role.getInterface((String) o[0]));
        }

        @Override
        public void postExecutive(Object o) {
            Activity activity = mTarget.get();
            InputStream stream;
            if (o instanceof String) {
                Log.d("hbj", (String) o);
                Toast.makeText(activity, (String) o, Toast.LENGTH_SHORT).show();
                activity.startActivity(new Intent(activity, LoginActivity.class));
            } else if (o instanceof ByteArrayOutputStream) {
                stream = new ByteArrayInputStream(((ByteArrayOutputStream) o).toByteArray());
                Log.d("hbj", HttpUtil.streamToString(new ByteArrayInputStream(((ByteArrayOutputStream) o).toByteArray())));
                role.handleResult(HttpUtil.streamToString(stream));
                if (role.getId().equals(Settings.getString(Settings.KEY_USER_ID, null)))
                    activity.startActivity(new Intent(activity, MainActivity.class));
                else activity.startActivity(new Intent(activity, LoginActivity.class));
            }
            activity.finish();
        }
    }

    //获取Deadline, 从 execute 方法中接受的参数: o[0] id
    public static class getDeadline extends HTTPTasks.doGet implements SRTPTask {
        private final WeakReference<Activity> mTarget;

        public getDeadline(Activity activity) {
            mTarget = new WeakReference<>(activity);
        }

        /**
         * @param o 从 execute 方法中接受的参数: o[0] courseId
         * @return 生成的URL
         */
        @Override
        public URL postUrl(Object... o) throws MalformedURLException {
            return url("deadline/" + o[0]);
        }

        @Override
        public void postExecutive(Object o) {
            Activity activity = mTarget.get();
            InputStream stream;
            if (o instanceof String) {
                Toast.makeText(activity, (String) o, android.widget.Toast.LENGTH_SHORT).show();
            } else if (o instanceof ByteArrayOutputStream) {
                stream = new ByteArrayInputStream(((ByteArrayOutputStream) o).toByteArray());
                String s = HttpUtil.streamToString(stream);
                if (Integer.valueOf(s.trim()) < 0) {
                    ((HomeworkActivity) mTarget.get()).deadlineResult.setText("提交截止日期已过");
                    ((HomeworkActivity) mTarget.get()).submit.setAlpha((float) 0.4);
                    ((HomeworkActivity) mTarget.get()).submit.setClickable(false);
                    ((HomeworkActivity) mTarget.get()).submit.setOnClickListener(v -> {
                        android.widget.Toast.makeText(activity, "提交时间已过", Toast.LENGTH_SHORT).show();
                        SoundUtil.ding(activity, SoundUtil.ALERT);
                    });
                } else
                    ((HomeworkActivity) mTarget.get()).deadlineResult.setText(MessageFormat.format("距离提交截止还有 {0} 天", s.trim()));
            }
        }
    }

    //（试验中）读取实验报告图片路径,从 execute 方法中接受的参数: o[0] courseId, o[1] stuId
    public static class imgPathTask extends HTTPTasks.doGet implements SRTPTask {
        private final WeakReference<Activity> mTarget;
        SRTPBean.ExperimentImgs experimentImgs;

        public imgPathTask(Activity activity, SRTPBean.ExperimentImgs imgs) {
            mTarget = new WeakReference<>(activity);
            experimentImgs = imgs;
        }

        /**
         * @param o 从 execute 方法中接受的参数: o[0] courseId, o[1] stuId
         * @return 生成的URL
         */
        @Override
        public URL postUrl(Object... o) throws MalformedURLException {
            return url(experimentImgs.getInterface((String) o[0], (String) o[1]));
        }

        @Override
        public void postExecutive(Object o) {
            Activity activity = mTarget.get();
            InputStream stream;
            if (o instanceof String) {
                Toast.makeText(activity, (String) o, android.widget.Toast.LENGTH_SHORT).show();
            } else if (o instanceof ByteArrayOutputStream) {
                stream = new ByteArrayInputStream(((ByteArrayOutputStream) o).toByteArray());
                experimentImgs.handleResult(HttpUtil.streamToString(stream));
                new imgLoadTask(mTarget.get()).execute((experimentImgs.getFileId(0)));
            }
        }
    }

    //（实验中）读取实验报告图片路径后 加载实验报告图片
    private static class imgLoadTask extends HTTPTasks.doGet implements SRTPTask {
        private final WeakReference<Activity> mTarget;

        private imgLoadTask(Activity activity) {
            mTarget = new WeakReference<>(activity);
        }

        @Override
        public URL postUrl(Object... o) throws MalformedURLException {
            // TODO: 2019/8/12 改呀 不然加载不了图片
            return new URL("http://222.18.57.34:8003/" + o[0]);
        }

        @Override
        public void postExecutive(Object o) {
            Activity activity = mTarget.get();
            InputStream stream;
            if (o instanceof String) {
                Toast.makeText(activity, (String) o, Toast.LENGTH_SHORT).show();
            } else if (o instanceof ByteArrayOutputStream) {
                stream = new ByteArrayInputStream(((ByteArrayOutputStream) o).toByteArray());
                // TODO: 2019/8/11 图片上传完成后的操作
                ((ImageView) activity.findViewById(R.id.test_img_result)).setImageBitmap(BitmapFactory.decodeStream(stream));
            }
        }
    }

    /**
     * 查询某个学生是否提交某个实验报告
     * 若已提交实验报告
     * 并且提交时间未截止，则询问是否删除已提交的实验报告，然后上传新的实验报告
     * 若还未提交实验报告 则直接提交实验报告
     */
    public static class hasReported extends HTTPTasks.doGet implements SRTPTask {
        private final WeakReference<Activity> mTarget;
        List<File> fileList;
        String getStuId;
        String getCourseId;

        public hasReported(Activity activity, List<File> fileList) {
            mTarget = new WeakReference<>(activity);
            this.fileList = fileList;
        }

        @Override
        public URL postUrl(Object... o) throws MalformedURLException {
            getCourseId = (String) o[0];
            getStuId = (String) o[1];
            return SRTPApplication.url("hasReported/" + o[0] + "/" + o[1]);
        }

        @Override
        public void postExecutive(Object o) {
            Activity activity = mTarget.get();
            InputStream stream;
            if (o instanceof String) {
                Toast.makeText(activity, (String) o, android.widget.Toast.LENGTH_SHORT).show();
            } else if (o instanceof ByteArrayOutputStream) {
                stream = new ByteArrayInputStream(((ByteArrayOutputStream) o).toByteArray());
                if (HttpUtil.streamToString(stream).equals("true")) {
                    SoundUtil.ding(activity, SoundUtil.NOTIFICATION);
                    Toast.makeText(activity, "实验报告已经提交了哦", Toast.LENGTH_SHORT).show();

                    Dialogs.SRTPAlertDialog.Builder builder = new Dialogs.SRTPAlertDialog.Builder(mTarget.get());
                    final Dialogs.SRTPAlertDialog[] dialog = {builder.create()};
                    View.OnClickListener listener = v -> new deleteReportPics((HomeworkActivity) mTarget.get(), fileList).execute(getCourseId, getStuId);
                    View.OnClickListener listener2 = v -> {
                    };
                    builder.setTitle("实验报告已经提交了哦").setIcon(R.string.fa_camera_retro_before).setMessage("是否覆盖之前的内容重新提交？").setButton("还是重传吧", listener).setButton2("算了算了", listener2);
                    dialog[0].show();

                } else {
                    new deleteReportPics((HomeworkActivity) mTarget.get(), fileList).execute(getCourseId, getStuId);
                }
            }
        }
    }

    private static class deleteReportPics extends HTTPTasks.doDelete implements SRTPTask {
        private final WeakReference<HomeworkActivity> mTarget;
        List<File> fileList;
        String stuId;
        String courseId;

        @Override
        public URL postUrl(Object... o) throws MalformedURLException {
            courseId = (String) o[0];
            stuId = (String) o[1];
            return url(MessageFormat.format("/report/{0}/{1}", o[0], o[1]));
        }

        private deleteReportPics(HomeworkActivity activity, List<File> fileList) {
            mTarget = new WeakReference<>(activity);
            this.fileList = fileList;
        }

        @Override
        public void postExecutive(String o) {
//            ExecutorService exec = Executors.newFixedThreadPool(fileList.size());
//            ExecutorService exec = Executors.newFixedThreadPool(fileList.size());
            if (o.contains("OK")) {
                Log.d("hbj", "一共有多少个文件呢：" + fileList.size());
                try {
                    for (int i = 0; i < fileList.size(); i++) {
                        new uploadReportPics(mTarget.get())
                                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                        (new SRTPBean()).new ImgUploadData(fileList.get(i), i, stuId, courseId));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else
                Toast.makeText(mTarget.get(), "无法删除您已上传过的那份作业：" + o, Toast.LENGTH_SHORT).show();
            SoundUtil.ding(mTarget.get(), SoundUtil.NOTIFICATION);
        }
    }

    public static class logout extends HTTPTasks.doDelete implements SRTPTask {
        private final WeakReference<Activity> mTarget;

        @Override
        public URL postUrl(Object... o) throws MalformedURLException {
            return url("/logout");
        }

        public logout(Activity activity) {
            mTarget = new WeakReference<>(activity);

        }

        @Override
        public void postExecutive(String o) {
            if (o.contains("OK")) {
                Settings.deletePreference(Settings.KEY_USER_ID, false);
                Settings.deletePreference(Settings.KEY_USER_TYPE, false);
                mTarget.get().startActivity(new Intent(mTarget.get(), LoginActivity.class));
                mTarget.get().finish();
            } else
                Toast.makeText(mTarget.get(), "注销登录失败", Toast.LENGTH_SHORT).show();
            SoundUtil.ding(mTarget.get(), SoundUtil.NOTIFICATION);
        }

    }

    // 上传实验报告的 post 模型，需要activity中一个 submit 对象回调显示结果
    private static class uploadReportPics extends HTTPTasks.doPost implements SRTPTask {
        static String BOUNDARY = "srtp6221srtp6221";
        static String PREFIX = "--";
        static String LINE_END = "\r\n";

        private static WeakReference<HomeworkActivity> mTarget = null;

        private uploadReportPics(HomeworkActivity activity) {
            mTarget = new WeakReference<>(activity);
        }

        //objects[0] 是上传作业的数据模型
        @Override
        public URL postUrl(Object... o) throws MalformedURLException {
            return url(((SRTPBean.ImgUploadData) o[0]).getInterface());
        }

        @Override
        public void postData(URLConnection connection, Object... objects) throws IOException {
            SRTPBean.ImgUploadData imgData = (SRTPBean.ImgUploadData) objects[0];
            Log.d("hbj", "现在是第 " + imgData.getNum() + " 个");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; DigExt)");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", ContentType_MULTIPART + "; boundary=" + BOUNDARY);
            StringBuffer sb = new StringBuffer();
            sb = sb.append(PREFIX).append(BOUNDARY).append(LINE_END)
                    .append("Content-Type: " + ContentType_TXT).append(LINE_END)
                    .append("Content-Disposition: form-data; name=\"num\"").append(LINE_END).append(LINE_END)
                    .append(imgData.getNum()).append("\r\n");

            sb.append(PREFIX).append(BOUNDARY).append(LINE_END)
                    .append("Content-Type: " + ContentType_TXT).append(LINE_END)
                    .append("Content-Disposition: form-data; name=\"stuId\"").append(LINE_END).append(LINE_END)
                    .append(imgData.getStuId()).append("\r\n");

            sb.append(PREFIX).append(BOUNDARY).append(LINE_END)
                    .append("Content-Type: " + ContentType_TXT).append(LINE_END)
                    .append("Content-Disposition: form-data; name=\"courseId\"").append(LINE_END).append(LINE_END)
                    .append(imgData.getCourseId()).append("\r\n");

            sb.append(PREFIX).append(BOUNDARY).append(LINE_END)
                    .append("Content-Type: application/octet-stream").append(LINE_END)
                    .append("Content-Disposition: form-data; name=\"file\"; filename=\"").append(imgData.getNum()).append(".jpg\"").append(LINE_END).append(LINE_END);

            DataOutputStream os = new DataOutputStream(connection.getOutputStream());
            os.writeBytes(String.valueOf(sb));
            Log.d("hbj", "以下是报头▄︻┻┳═一…… ☆(>○<)\n" + sb.toString() + "文件………………" + (LINE_END + PREFIX + BOUNDARY + PREFIX + LINE_END));
            InputStream fIs = new FileInputStream(imgData.getFile());
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fIs.read(bytes)) != -1) os.write(bytes, 0, len);
            os.writeBytes(LINE_END + PREFIX + BOUNDARY + PREFIX + LINE_END);
            os.flush();
            os.close();
        }


        @Override
        public void postExecutive(Object o) {
            Activity activity = mTarget.get();
            InputStream stream;
            if (o instanceof String) {
                Toast.makeText(activity, (String) o, android.widget.Toast.LENGTH_SHORT).show();
            } else if (o instanceof ByteArrayOutputStream) {
                stream = new ByteArrayInputStream(((ByteArrayOutputStream) o).toByteArray());
                if (HttpUtil.streamToString(new ByteArrayInputStream(((ByteArrayOutputStream) o).toByteArray())).equals("OK"))
                    mTarget.get().submit.setText("实验报告上传完成");
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mTarget.get().submit.setText("取消");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mTarget.get().submit.setText(MessageFormat.format("进度是 {0}， 点击取消", (Object) values));
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();
            mTarget.get().submit.setText("确定");
        }
    }

    // 改密码的 post 模型，需要activity中一个 submit 对象回调显示结果
    public static class changePasswd extends HTTPTasks.doPut implements SRTPTask {
        private static WeakReference<Activity> mTarget = null;

        public changePasswd(Activity activity) {
            mTarget = new WeakReference<>(activity);
        }

        //objects[0] 是改密的数据模型
        @Override
        public URL putUrl(Object... o) throws MalformedURLException {
            return url(((SRTPBean.changePasswordData) o[0]).getInterface(Settings.getBoolean(Settings.KEY_USER_TYPE, true)));
        }

        @Override
        public void putData(URLConnection connection, Object... objects) throws IOException, JSONException {
            SRTPBean.changePasswordData passwordData = (SRTPBean.changePasswordData) objects[0];
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; DigExt)");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", ContentType_JSON);
            DataOutputStream os = new DataOutputStream(connection.getOutputStream());
            os.writeBytes(OtherUtil.Json2Form(passwordData.getObject()));
            os.flush();
            os.close();
        }

        @Override
        public void postExecutive(Object o) {
            Activity activity = mTarget.get();
            if (o instanceof String) {
                Toast.makeText(activity, (String) o, android.widget.Toast.LENGTH_SHORT).show();
            } else if (o instanceof ByteArrayOutputStream) {
                String s = HttpUtil.streamToString(new ByteArrayInputStream(((ByteArrayOutputStream) o).toByteArray()));
                if (s.equals("OK"))
                    Toast.makeText(activity, "您的密码已成功修改", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
                    SoundUtil.ding(activity, SoundUtil.ALERT);
                }
            }
        }
    }
}