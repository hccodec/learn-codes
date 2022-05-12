package com.physswjtu.srtp2019.Utils;

import android.os.Handler;

import com.physswjtu.srtp2019.data.SRTPBean;

import java.net.URLEncoder;

import static com.physswjtu.srtp2019.Utils.HttpUtil.ContentType_JSON;
import static com.physswjtu.srtp2019.Utils.HttpUtil.ContentType_MAP;
import static com.physswjtu.srtp2019.Utils.HttpUtil.post;
import static com.physswjtu.srtp2019.Utils.OtherUtil.getMD5;

/**
 * 由 84697 创建: authentication 数据模型
 * 日期为 2019/6/29
 * 工程 PhysLab
 */
public class authentication {

    /**
     * 登录：
     * <p>该方法将传入的信息通过数据模型 {@link SRTPBean} 进行加密,
     * 并将加密后的 post 请求体以 JSON 数据的形式发送给服务器</p>
     *
     * @param a       生 / 师
     * @param handler activity 中的消息处理器
     * @param args    要传送的信息
     * @throws Exception 抛出的各类异常
     */
    public static void login(boolean a, Handler handler, String... args) throws Exception {
        SRTPBean.LoginData data = new SRTPBean.LoginData(args);
        //String data = "id=" + URLEncoder.encode(args[0], "utf-8") + "&passwd=" + getMD5(URLEncoder.encode(args[1], "utf-8"));
        //post(new String(data.getObject(), StandardCharsets.UTF_8).getBytes(), data.getInterface(a), ContentType_MAP, handler);
        post(data.getObject(), data.getInterface(a), ContentType_JSON, handler);
    }

    /**
     * 注册：
     * <p>该方法将传入的信息通过数据模型 {@link SRTPBean} 进行加密,
     * 并将加密后的 post 请求体以 JSON 数据的形式发送给服务器</p>
     *
     * @param handler activity 中的消息处理器
     * @param args    要传送的信息
     * @throws Exception 抛出的各类异常
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static void register(boolean a, Handler handler, String... args) throws Exception {
        SRTPBean.RegisterData regsiterData = new SRTPBean.RegisterData(args);

        String data = "id=" + URLEncoder.encode(args[0], "utf-8") +
                "&passwd=" + getMD5(URLEncoder.encode(args[1], "utf-8")) +
                "&name=" + URLEncoder.encode(args[2], "utf-8") +
                "&sex=" + URLEncoder.encode(args[3], "utf-8") +
                "&school=" + URLEncoder.encode(args[4], "utf-8") +
                "&className=" + URLEncoder.encode(args[5], "utf-8") +
                "&phoneNum=" + URLEncoder.encode(args[6], "utf-8");

        //String service = "stu-register";
        post(regsiterData.getObject(), regsiterData.getInterface(a), ContentType_MAP, handler);
    }

}