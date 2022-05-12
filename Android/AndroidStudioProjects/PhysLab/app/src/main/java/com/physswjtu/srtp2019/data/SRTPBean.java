/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.data;

import android.util.Log;

import com.physswjtu.srtp2019.Utils.httpProtocol;
import com.physswjtu.srtp2019.data.Settings.Settings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;

import static com.physswjtu.srtp2019.Utils.OtherUtil.getMD5;


/**
 * 该类用于规范网络请求过程中的键值对
 */
public class SRTPBean {
    public static class StudentChooseExperiments {
        private ArrayList<String> expName = new ArrayList<>();
        private ArrayList<String> week = new ArrayList<>();
        private ArrayList<Integer> grade = new ArrayList<>();
        private ArrayList<String> time = new ArrayList<>();
        private ArrayList<Integer> courseId = new ArrayList<>();
        private ArrayList<String> day = new ArrayList<>();
        private ArrayList<String> roomId = new ArrayList<>();
        private ArrayList<Integer> expId = new ArrayList<>();
        private ArrayList<String[]> mData = new ArrayList<>(); //展示数据
        JSONArray array;

        public ArrayList<String[]> getmData() {
            return mData;
        }

        public int getCourseId(int i) {
            if (array.length() == 0) return 0;
            else
                return courseId.get(i);
        }

        public int getExpId(int i) {
            if (array.length() == 0) return 0;
            else
                return expId.get(i);
        }

        public int getGrade(int i) {
            if (array.length() == 0) return 0;
            else
                return grade.get(i);
        }

        public String getDay(int i) {
            if (array.length() == 0) return null;
            else
                return day.get(i);
        }

        public String getExpName(int i) {
            if (array.length() == 0) return null;
            else
                return expName.get(i);
        }

        public String getRoomId(int i) {
            if (array.length() == 0) return null;
            else
                return roomId.get(i);
        }

        public String getTime(int i) {
            if (array.length() == 0) return null;
            else
                return time.get(i);
        }

        public String getWeek(int i) {
            if (array.length() == 0) return null;
            else
                return week.get(i);
        }

        public StudentChooseExperiments(String textResult) {
            try {
                array = new JSONArray(textResult);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject student = array.getJSONObject(i);
                    expName.add(i, student.getString("expName"));//0
                    week.add(i, student.getString("week"));//1
                    grade.add(i, student.getInt("grade"));//2
                    time.add(i, student.getString("time"));//3
                    courseId.add(i, student.getInt("courseId"));//4
                    day.add(i, student.getString("day"));//5
                    roomId.add(i, student.getString("roomId"));//6
                    expId.add(i, student.getInt("expId"));//7
                    mData.add(i, new String[]{expName.get(i), week.get(i),
                            String.valueOf(grade.get(i)), time.get(i),
                            String.valueOf(courseId.get(i)), day.get(i),
                            roomId.get(i), String.valueOf(expId.get(i))});
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public static String getInterface(String id) {
            return "stu-courses/" + id;
        }
    }

    //身份
    public static class Role implements httpProtocol.get {
        private String school;//0
        private Integer sex;//1
        private String className;//1
        private String phoneNum;//2
        private String userName;//3
        private String id;//4
        private String[] mData; //数据集合
        boolean type;

        public Role(boolean type) {
            this.type = type;
        }

        public String getSchool() {
            return school;
        }

        public String getClassName() {
            return className;
        }

        public String getUserName() {
            return userName;
        }

        public Integer getSex() {
            return sex;
        }

        public String[] getmData() {
            return mData;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public String getId() {
            return id;
        }

        //o[0] id
        public String getInterface(String... id) {
            return (type ? "stu/" : "teacher/") + id[0];
        }

        public void handleResult(String customResponse) {
            try {
                JSONObject object = new JSONObject(customResponse);
                sex = object.getInt("sex");
                userName = object.getString("userName");
                if (type) {
                    id = object.getString("stuId");
                    school = object.getString("school");
                    className = object.getString("className");
                    phoneNum = object.getString("phoneNum");
                } else id = object.getString("teacherId");
                mData = new String[]{school, String.valueOf(sex), className, phoneNum, userName, id};
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //实验报告图片地址
    public static class ExperimentImgs implements httpProtocol.get {
        private ArrayList<Integer> num = new ArrayList<>();//1
        private ArrayList<String> fileId = new ArrayList<>();//1
        JSONArray array;

        public int getNum(int i) {
            if (array == null || array.length() == 0) return 0;
            else
                return num.get(i);
        }

        public String getFileId(int i) {
            if (array == null || array.length() == 0) return null;
            else
                return fileId.get(i);
        }

        //o[0] courseId, o[1] stuId
        public String getInterface(String... o) {
            return MessageFormat.format("/report/{0}/{1}", o[0], o[1]);
        }

        public void handleResult(String customResponse) {
            try {
                array = new JSONArray(customResponse);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    num.add(i, object.getInt("num"));
                    fileId.add(i, object.getString("fileId"));
                    Log.d("hbj", MessageFormat.format("{0} {1}", num, fileId));
//                mData = new String[]{String.valueOf(num), fileId};
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 登录数据模型，请在使用时务必按照下面介绍的各变量的顺序进行引用
     * （post 请求体中 id 为学号，passwd 为密码）
     *
     * @see #object 最后生成的 JSON 字符串
     */
    public static class LoginData implements httpProtocol.post {

        private String id;
        private String passwd;

        private JSONObject object = new JSONObject();

        @Override
        //判断用用户登录的身份
        public String getInterface(boolean... a) {
            return (a[0] ? "stu-login" : "teacher-login");//a=0对应学生，
        }

        //返回生成的 字节流 形式的 JSON 字符串
        public JSONObject getObject() {
            return object;
        }

        public LoginData(String... args) throws Exception {
            id = URLEncoder.encode(args[0], "utf-8");
            passwd = getMD5(URLEncoder.encode(args[1], "utf-8"));
            object.putOpt("id", id);
            object.putOpt("passwd", passwd);
            Settings.putString(Settings.KEY_USER_ID, id, false);
        }
    }

    /**
     * 注册数据模型，请在使用时务必按照下面介绍的各变量的顺序进行引用
     * <p>
     * 学生post 请求体中：
     * id 为学生学号，passwd 为密码，name 为姓名，sex 为性别，school 为学院，className 为班级，phoneNum 为手机号
     * <p>
     * 教师post 请求体中：id 为教师工号，passwd 为密码，name 为姓名，sex 为性别
     * </P>
     *
     * @see #object 最后生成的 JSON 字符串
     */
    public static class RegisterData implements httpProtocol.post {

        private int id;
        private String passwd;
        private String name;
        private int sex;
        private String school;
        private String className;
        private int phoneNum;

        private JSONObject object = new JSONObject();

        @Override
        //判断用用户登录的身份
        public String getInterface(boolean... a) {
            return (a[0] ? "stu-register" : "teacher-register");//a=0对应学生，
        }

        //返回生成的 字节流 形式的 JSON 字符串
        public JSONObject getObject() {
            return object;
        }

        public RegisterData(String[] args) throws Exception {
            id = Integer.parseInt(URLEncoder.encode(args[0], "utf-8"));
            passwd = getMD5(URLEncoder.encode(args[1], "utf-8"));
            name = URLEncoder.encode(args[2], "utf-8");
            sex = Integer.parseInt(URLEncoder.encode(args[3], "utf-8"));
            object.put("id", id);
            object.put("passwd", passwd);
            object.put("name", name);
            object.put("sex", sex);
            if (!args[4].isEmpty()) {
                object.put("school", school);
                school = URLEncoder.encode(args[4], "utf-8");
            }
            if (!args[5].isEmpty()) {
                object.put("className", className);
                className = URLEncoder.encode(args[5], "utf-8");
            }
            if (!args[6].isEmpty()) {
                object.put("phoneNum", phoneNum);
                phoneNum = Integer.parseInt(URLEncoder.encode(args[6], "utf-8"));
            }
        }
    }

    /**
     * 学生上传实验报告图片的数据模型，应被批量处理
     * （post请求体中stuId为学号，courseId为课程编号，file为图片，num为图片编号）
     *
     * @see #object 最后生成的 JSON 字符串
     */
    public class ImgUploadData implements httpProtocol.post {

        private String stuId;
        private String courseId;
        File file;
        int num;

        public String getStuId() {
            return stuId;
        }

        public String getCourseId() {
            return courseId;
        }

        public File getFile() {
            return file;
        }

        public int getNum() {
            return num;
        }


        private JSONObject object = new JSONObject();


        //返回生成的 字节流 形式的 JSON 字符串
        public JSONObject getObject() {
            return object;
        }

        public ImgUploadData(File file, int num, String stuId, String courseId) throws Exception {
            this.stuId = URLEncoder.encode(stuId, "utf-8");
            this.courseId = URLEncoder.encode(courseId, "utf-8");
            this.file = file;
            this.num = num;
            object.putOpt("file", file);
            object.putOpt("stuId", stuId);
            object.putOpt("courseId", courseId);
            object.putOpt("num", num);
        }

        @Override
        public String getInterface(boolean... a) {
            return "img";
        }
    }

    /**
     * 学生上传实验报告图片的数据模型，应被批量处理
     * （post请求体中stuId为学号，courseId为课程编号，file为图片，num为图片编号）
     *
     * @see #object 最后生成的 JSON 字符串
     */
    public static class changePasswordData implements httpProtocol.put {

        private String id;
        private String oldPasswd;
        private String newPasswd;

        public String getId() {
            return id;
        }

        public String getNewPasswd() {
            return newPasswd;
        }

        public String getOldPasswd() {
            return oldPasswd;
        }

        private JSONObject object = new JSONObject();

        //返回生成的 字节流 形式的 JSON 字符串
        public JSONObject getObject() {
            return object;
        }

        public changePasswordData(String... args) throws Exception {
            id = URLEncoder.encode(args[0], "utf-8");
            oldPasswd = getMD5(URLEncoder.encode(args[1], "utf-8"));
            newPasswd = getMD5(URLEncoder.encode(args[2], "utf-8"));
            object.putOpt("id", id);
            object.putOpt("oldPasswd", oldPasswd);
            object.putOpt("newPasswd", newPasswd);
        }

        @Override
        public String getInterface(boolean... a) {
            return a[0] ? "/stu-passwd" : "teacher-passwd";
        }
    }
}

