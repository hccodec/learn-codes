package com.physswjtu.srtp2019.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.physswjtu.srtp2019.data.Settings.Settings;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.physswjtu.srtp2019.SRTPApplication.url;
import static com.physswjtu.srtp2019.Utils.OtherUtil.reuseInputStream;
import static com.physswjtu.srtp2019.Utils.OtherUtil.sendMessage;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_TOKEN;

public class HttpUtil {

    //服务器返回的状态码
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    //向服务器请求的方式
    static final String ContentType_XML = "application/x-javascript text/xml";//xml数据
    static final String ContentType_JS = "application/x-javascript";//js对象
    public static final String ContentType_MAP = "application/x-www-form-urlencoded";//表单数据
    public static final String ContentType_TXT = "text/plain;charset=UTF-8";//表单数据
    static final String ContentType_JSON = "application/json";//json数据
    public static final String ContentType_MULTIPART = "multipart/form-data";//表单数据

    /**
     * 从 HTTP 请求方法的线程中提取出的可供其他方法复用一次的输入流对象
     * 再次复用需参考 {@link OtherUtil#reuseInputStream(InputStream)} 方法
     */
    private static InputStream stream;
    private static String customResponse;

    public static Bitmap getBitmap() {
        return getBitmap(true);
    }

    public static Bitmap getBitmap(boolean allowBackup) {
        if (stream != null) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                byteArrayOutputStream = reuseInputStream(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (byteArrayOutputStream != null) {
                stream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
            Bitmap result = BitmapFactory.decodeStream(stream);
            if (allowBackup && byteArrayOutputStream != null) {
                stream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
            return result;
        } else {
            Log.d("hbj", "输入流为空！");
            return null;
        }
    }

    public static String getString() {
        return customResponse;
    }

    /**
     * post方式发送HTTP请求
     *
     * @param obj     要传送的json数据对象（传进JSON对象后该方法将其转为）
     * @param service 使用的网络接口
     * @param handler 消息处理器
     */
    static void post(final JSONObject obj, final String service, final String contentType, final Handler handler) {
        new Thread() {
            public void run() {
                try {
                    String data = String.valueOf(obj);
                    // String data = obj.toString();
                    Log.d("hbj", "HttpUtil(): data : " + data);
                    Log.d("hbj", data);
                    Log.d("hbj", "要发送的数据为：" + data);
                    URL url = url(service);
                    //url = new URL("http://192.168.1.113:3000");//宋天助网页
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    Log.d("hbj", "要连接的网址为：" + url);
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(3000);
                    connection.setConnectTimeout(3000);
                    connection.setDoInput(true);    //从服务器收数据
                    connection.setDoOutput(true);   //向服务器写数据
                    connection.setUseCaches(false);
                    //设置请求方式
                    connection.setRequestProperty("Content-Length", data);
                    connection.setRequestProperty(KEY_TOKEN, Settings.getString(KEY_TOKEN, null));
                    connection.setRequestProperty("Content-Type", ContentType_JSON);
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE9.0; Windows NT 6.1; Trident/5.0)");
                    //connection.setRequestProperty("accept", "application/json");

                    DataOutputStream os = new DataOutputStream(connection.getOutputStream());
                    os.writeBytes(OtherUtil.Json2Form(obj));
                    os.flush();
                    os.close();
                    int responseCode = connection.getResponseCode();
                    Log.d("hbj", "得到的服务器返回码为：" + responseCode);
                    Message msg = handler.obtainMessage();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        Log.d("hbj", "链接服务器成功");
                        InputStream is = connection.getInputStream();
                        ByteArrayOutputStream byteArrayOutputStream = reuseInputStream(is);
                        is.close();
                        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                        customResponse = streamToString(inputStream);//解析返回的数据
                        Log.d("hbj", "服务器返回数据为" + customResponse);
                        sendMessage(msg, SUCCESS, customResponse);
                    } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND)
                        sendMessage(msg, ERROR, responseCode + "-目标位置不存在: ");
                    else if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR)
                        sendMessage(msg, ERROR, "服务器内部出现错误");
                    else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED)
                        sendMessage(msg, ERROR, "您无权查看此目录或页面。");
                    else sendMessage(msg, ERROR, "请联系管理员处理此类异常，状态码：" + responseCode);
                    connection.disconnect();
                } catch (Exception e) {
                    Message msg = handler.obtainMessage();
                    Log.d("hbj", "catch到了！msg是" + msg);
                    Log.d("hbj", "error是" + e);
                    if (String.valueOf(e).contains("ailed to connect")) {
                        if ((String.valueOf(e).endsWith("ms"))) {
                            Log.d("hbj", "服务器关闭");
                            sendMessage(msg, ERROR, "服务器关闭");
                        } else {
                            Log.d("hbj", "连接目标位置失败");
                            sendMessage(msg, ERROR, "连接目标位置失败");
                        }
                    } else if (String.valueOf(e).contains("resolve host")) {
                        Log.d("hbj", "主机解析失败");
                        sendMessage(msg, ERROR, "主机解析失败");
                    } else if (String.valueOf(e).contains("timeout")) {
                        Log.d("hbj", "超时");
                        sendMessage(msg, ERROR, "超时");
                    } else e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * <p>使用了BufferedReader将http输入流转化为字符串</p>
     *
     * @param is 输入流对象
     * @return 转化出的字符串
     */
    @NotNull
    public static String streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public void get(final String s, final Handler handler) throws MalformedURLException {
        get(s, handler, SUCCESS);
    }

    public void get(final String s, final Handler handler, int what) throws MalformedURLException {
        get(new URL(s), handler, what);
    }

    public void get(final URL s, final Handler handler) {
        get(s, handler, SUCCESS);
    }

    public void get(final URL url, final Handler handler, int what) {
        new Thread() {
            public void run() {
                try {
                    Log.d("hbj", "要连接的网址为：" + url.toString());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(3000);
                    connection.setReadTimeout(3000);
                    connection.setRequestProperty("charset", "UTF-8");
                    connection.setRequestProperty(KEY_TOKEN, Settings.getString(KEY_TOKEN, null));
                    Message msg = handler.obtainMessage();
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream is = connection.getInputStream();
                        ByteArrayOutputStream byteArrayOutputStream = reuseInputStream(is);
                        is.close();
                        stream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                        customResponse = streamToString(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                        sendMessage(msg, what, customResponse);
                    } else if (responseCode == 404) {
                        Log.d("hbj", "服务器有响应但" + responseCode + "-找不到目标位置: " + url);
                        sendMessage(msg, ERROR, responseCode + "-目标位置不存在: ");
                    } else if (responseCode == HttpURLConnection.HTTP_SERVER_ERROR) {
                        Log.d("hbj", "服务器有响应但" + responseCode + "-服务器内部出现错误");
                        sendMessage(msg, ERROR, responseCode + "-服务器内部出现错误");
                    } else {
                        Log.d("hbj", "服务器有响应但请联系管理员处理此类异常，状态码：" + responseCode);
                        sendMessage(msg, ERROR, "请联系管理员处理此类异常，状态码：" + responseCode);
                    }
                    connection.disconnect();
                } catch (Exception e) {
                    Message msg = handler.obtainMessage();
                    Log.d("hbj", "catch到了！msg是" + msg);
                    Log.d("hbj", "error是" + e);
                    if (String.valueOf(e).contains("ailed to connect") || String.valueOf(e).equals("timeout")) {
                        if ((String.valueOf(e).endsWith("ms"))) {
                            Log.d("hbj", "服务器关闭");
                            sendMessage(msg, ERROR, "服务器关闭");
                        } else {
                            Log.d("hbj", "连接目标位置失败");
                            sendMessage(msg, ERROR, "连接目标位置失败");
                        }
                    } else if (String.valueOf(e).contains("resolve host")) {
                        Log.d("hbj", "请检查您的网络连接");
                        sendMessage(msg, ERROR, "请检查您的网络连接");
                    } else if (String.valueOf(e).contains("timeout")) {
                        Log.d("hbj", "超时");
                        sendMessage(msg, ERROR, "超时");
                    } else {
                        e.printStackTrace();
                        sendMessage(msg, ERROR, "未知错误");
                    }
                }
            }
        }.start();
    }
}