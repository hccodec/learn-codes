/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.physswjtu.srtp2019.LoginActivity;
import com.physswjtu.srtp2019.data.SRTPBean;
import com.physswjtu.srtp2019.data.Settings.Settings;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static com.physswjtu.srtp2019.Utils.HttpUtil.streamToString;
import static com.physswjtu.srtp2019.Utils.OtherUtil.reuseInputStream;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_TOKEN;

public class HTTPTasks {
    private static WeakReference<Activity> mTarget;


    public static void initHttpTasks(Activity activity) {
        mTarget = new WeakReference<>(activity);
    }


    /**
     * 该类实现发送get请求
     * <p>
     * 从 execute 方法中接受的参数：按照文档格式逐一添加对象
     * <p>
     * <p>
     * 请求成功返回一个 ByteArrayOutputStream（若服务器返回结果为字符串需要自行解析）
     * 失败则直接返回字符串
     */
    public static abstract class doGet extends AsyncTask<Object, Integer, Object> {

        /**
         * 传递要访问的地址
         *
         * @param o 从 execute 方法中接受的参数
         * @return 生成的 URL 对象
         * @throws MalformedURLException 异常
         */
        public abstract URL postUrl(Object... o) throws MalformedURLException;


        /**
         * 后续操作
         *
         * @param o 从 execute 方法中接受的参数
         */
        public abstract void postExecutive(Object o);

        @Override
        protected Object doInBackground(Object... s) {
            try {
                URL url = postUrl(s);
                Log.d("hbj", "要连接的网址为：" + url.toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(3000);
                connection.setReadTimeout(3000);
                connection.setRequestProperty(KEY_TOKEN, Settings.getString(KEY_TOKEN, null));
                connection.setRequestProperty("charset", "UTF-8");
                int responseCode = connection.getResponseCode();
                ByteArrayOutputStream byteArrayOutputStream;
                if (judgeResponseCode(responseCode).equals("OK")) {
                    InputStream is = connection.getInputStream();
                    byteArrayOutputStream = reuseInputStream(is);
                    is.close();
                } else return judgeResponseCode(responseCode);
                connection.disconnect();
                return byteArrayOutputStream;
            } catch (Exception e) {
                return judgeException(e);
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (o instanceof String && o.equals("登录信息过期，请重新登录 ( • ̀ω•́ )✧")) {
                Intent i = new Intent(mTarget.get(), LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mTarget.get().startActivity(i);
                Intent intent = new Intent();
                intent.setAction("con.lcry.close.activity");
                mTarget.get().sendBroadcast(intent);
                mTarget.get().finish();
            }
            postExecutive(o);
        }
    }

    /**
     * 该类实现发送 post 请求
     * <p>
     * 从 execute 方法中接受的参数：请求体的 {@link SRTPBean} 模型
     * <p>
     * <p>
     * {@link HTTPTasks.doPost#postUrl(Object...)} 方法的使用
     * <p>
     * {@link SRTPBean} 中的 {@link httpProtocol.post#getInterface(boolean...)} 得到 API，
     * 再使用 {@link com.physswjtu.srtp2019.SRTPApplication#url(URL)} 方法拼接成完整的 url 地址
     * 例如 url(experimentImgs.getInterf("141", "2017113903")) 其中 experimentImgs 为一个数据模型
     */
    public static abstract class doPost extends AsyncTask<Object, Integer, Object> {

        /**
         * 后续操作
         *
         * @param o 从 execute 方法中接受的参数
         */
        public abstract void postExecutive(Object o);

        /**
         * 传递具体的 post 请求的操作
         *
         * @param connection 当前的 connection 形参
         * @param objects    从 execute 方法中接受的参数
         * @throws Exception 异常
         */
        public abstract void postData(URLConnection connection, Object... objects) throws Exception;

        /**
         * 传递要访问的地址
         *
         * @param o 从 execute 方法中接受的参数
         * @return 生成的 URL 对象
         * @throws MalformedURLException 异常
         */
        public abstract URL postUrl(Object... o) throws MalformedURLException;

        @Override
        protected Object doInBackground(Object... s) {
            try {
                URL url = postUrl(s);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                Log.d("hbj", "要连接的网址为：" + url);
                connection.setDoInput(true);    //从服务器收数据
                connection.setDoOutput(true);   //向服务器写数据
                connection.setUseCaches(false);
                connection.setReadTimeout(3000);
                connection.setConnectTimeout(3000);
                connection.setRequestMethod("POST");
                connection.setRequestProperty(KEY_TOKEN, Settings.getString(KEY_TOKEN, null));

                postData(connection, s);

                int responseCode = connection.getResponseCode();
                ByteArrayOutputStream byteArrayOutputStream;
                if (judgeResponseCode(responseCode).equals("OK")) {
                    InputStream is = connection.getInputStream();
                    byteArrayOutputStream = reuseInputStream(is);
                    is.close();
                } else return judgeResponseCode(responseCode);
                connection.disconnect();
                return byteArrayOutputStream;
            } catch (Exception e) {
                return judgeException(e);
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (o instanceof String && o.equals("登录信息过期，请重新登录 ( • ̀ω•́ )✧")) {
                Toast.makeText(mTarget.get(), "就是我呀", Toast.LENGTH_SHORT).show();
                mTarget.get().startActivity(new Intent(mTarget.get(), LoginActivity.class));
                mTarget.get().finish();
            }
            postExecutive(o);
        }
    }

    /**
     * 只接受一个参数，即接口
     */
    public static abstract class doDelete extends AsyncTask<Object, Integer, String> {
        public abstract void postExecutive(String s);

        /**
         * 传递要访问的地址
         *
         * @param o 从 execute 方法中接受的参数
         * @return 生成的 URL 对象
         * @throws MalformedURLException 异常
         */
        public abstract URL postUrl(Object... o) throws MalformedURLException;

        @Override
        protected String doInBackground(Object... objects) {
            try {
                String customResponse = "失败";//解析返回的数据
                int responseCode;
                URL url = postUrl(objects);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                Log.d("hbj", "要连接的网址为：" + url);
                connection.setDoInput(true);    //从服务器收数据
                connection.setUseCaches(false);
                connection.setReadTimeout(3000);
                connection.setConnectTimeout(3000);
                connection.setRequestMethod("DELETE");
                connection.setRequestProperty(KEY_TOKEN, Settings.getString(KEY_TOKEN, null));
                responseCode = connection.getResponseCode();

                if (judgeResponseCode(responseCode).equals("OK")) {
                    InputStream is = connection.getInputStream();
                    ByteArrayOutputStream byteArrayOutputStream = reuseInputStream(is);
                    is.close();
                    customResponse = streamToString(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                } else return judgeResponseCode(responseCode);
                connection.disconnect();
                return customResponse + ", " + responseCode;
            } catch (Exception e) {
                e.printStackTrace();
                return judgeException(e);
            }
        }

        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            if (o != null && o.equals("重新登录")) {
                mTarget.get().startActivity(new Intent(mTarget.get(), LoginActivity.class));
                mTarget.get().finish();
            } else postExecutive(o);
        }
    }

    /**
     * 该类实现发送 put 请求
     * <p>
     * 从 execute 方法中接受的参数：请求体的 {@link SRTPBean} 模型
     * <p>
     * <p>
     * {@link HTTPTasks.doPost#postUrl(Object...)} 方法的使用
     * <p>
     * {@link SRTPBean} 中的 {@link httpProtocol.post#getInterface(boolean...)} 得到 API，
     * 再使用 {@link com.physswjtu.srtp2019.SRTPApplication#url(URL)} 方法拼接成完整的 url 地址
     * 例如 url(experimentImgs.getInterf("141", "2017113903")) 其中 experimentImgs 为一个数据模型
     */
    public static abstract class doPut extends AsyncTask<Object, Integer, Object> {

        /**
         * 后续操作
         *
         * @param o 从 execute 方法中接受的参数
         */
        public abstract void postExecutive(Object o);

        /**
         * 传递具体的 post 请求的操作
         *
         * @param connection 当前的 connection 形参
         * @param objects    从 execute 方法中接受的参数
         * @throws Exception 异常
         */
        public abstract void putData(URLConnection connection, Object... objects) throws Exception;

        /**
         * 传递要访问的地址
         *
         * @param o 从 execute 方法中接受的参数
         * @return 生成的 URL 对象
         * @throws MalformedURLException 异常
         */
        public abstract URL putUrl(Object... o) throws MalformedURLException;

        @Override
        protected Object doInBackground(Object... s) {
            try {
                URL url = putUrl(s);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                Log.d("hbj", "要连接的网址为：" + url);
                connection.setDoInput(true);    //从服务器收数据
                connection.setDoOutput(true);   //向服务器写数据
                connection.setUseCaches(false);
                connection.setReadTimeout(3000);
                connection.setConnectTimeout(3000);
                connection.setRequestMethod("PUT");
                connection.setRequestProperty(KEY_TOKEN, Settings.getString(KEY_TOKEN, null));
                putData(connection, s);
                int responseCode = connection.getResponseCode();
                ByteArrayOutputStream byteArrayOutputStream;
                if (judgeResponseCode(responseCode).equals("OK")) {
                    InputStream is = connection.getInputStream();
                    byteArrayOutputStream = reuseInputStream(is);
                    is.close();
                } else return judgeResponseCode(responseCode);

                connection.disconnect();
                return byteArrayOutputStream;
            } catch (Exception e) {
                return judgeException(e);
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (o instanceof String && o.equals("重新登录")) {
                mTarget.get().startActivity(new Intent(mTarget.get(), LoginActivity.class));
                mTarget.get().finish();
            }
            postExecutive(o);
        }
    }

    private static String judgeResponseCode(int responseCode) {
        if (responseCode == HttpURLConnection.HTTP_OK) return "OK";
        else if (responseCode == 401 || responseCode == 403) return "登录信息过期，请重新登录 ( • ̀ω•́ )✧";
        else if (responseCode == 404) return "找不到服务器";
        else if (responseCode == 500) return "服务器内部错误 凸(艹皿艹 )";
        else return "请联系管理员处理此类异常，状态码:" + responseCode;
    }

    private static String judgeException(Exception e) {
        if (String.valueOf(e).contains("ailed to connect") || String.valueOf(e).equals("timeout")) {
            if ((String.valueOf(e).endsWith("ms"))) return "服务器关闭";
            else return "连接目标位置失败";
        } else if (String.valueOf(e).contains("resolve host")) return "请检查您的网络连接";
        else if (String.valueOf(e).contains("timeout")) return "超时";
        else e.printStackTrace();
        return "未知错误";
    }
}