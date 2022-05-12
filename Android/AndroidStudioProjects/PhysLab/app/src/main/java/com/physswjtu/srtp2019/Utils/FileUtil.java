/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 由 84697 创建
 * 日期为 2019/7/17
 * 工程 PhysLab
 */
public class FileUtil {


    /**
     * 获取外部存储目录，一般为 /storage/emulated/0/SRTPfile_recv
     *
     * @return 返回外部存储目录的 File 对象
     * @throws IOException 捕捉异常
     */
    public static File getExternalFilePath() throws IOException {
        //（新目录）外部储存目录
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/SRTPfile_recv");
        Log.d("hbj", "目录是  " + file.getPath());
        if (file.exists()) Log.d("hbj", "getExternalFilePath: " + "目录已存在");
        else if (file.mkdirs())
            if (!file.exists() && !file.mkdirs())
                throw new IOException("目录创建失败");
        return file;
    }

    /**
     * 获取APP专属目录
     *
     * @param context 传入上下文
     * @return 文件对象
     * @throws IOException 异常
     */
    public static File getSpecificFilePath(Context context, String type) throws IOException {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(context.getExternalFilesDir(type).getPath());
            Log.d("hbj", "目录是  " + file.getPath());
            if (file.exists()) Log.d("hbj", "getExternalFilePath: " + "目录已存在");
            else if (file.mkdirs())
                if (!file.exists() && !file.mkdirs())
                    throw new IOException("目录创建失败");
            return file;
        }
        Log.e("hbj", "SD卡未装入");
        return null;
    }

    public static Uri stringToUri(Context context, String path) {
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //如果是7.0android系统
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, path);
            uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        } else {
            uri = Uri.fromFile(new File(path));
        }
        return uri;
    }

    static Uri pathToUri(Context context, String path) {
        File file = new File(path);
        return fileToUri(context, file);
    }

    public static Uri fileToUri(Context context, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context,
                    context.getPackageName() + ".fileProvider", file);
        } else return Uri.fromFile(file);
    }

    /**
     * 创建文件
     *
     * @param filePath 目标路径
     * @param fileName 文件名称
     * @return
     */
    public static File newFile(String filePath, String fileName) {
        if (filePath == null || filePath.length() == 0
                || fileName == null || fileName.length() == 0) {
            return null;
        }
        try {
            //判断目录是否存在，如果不存在，递归创建目录
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            //组织文件路径
            StringBuilder sbFile = new StringBuilder(filePath);
            if (!filePath.endsWith("/")) {
                sbFile.append("/");
            }
            sbFile.append(fileName);

            //创建文件并返回文件对象
            File file = new File(sbFile.toString());
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件或目录
     *
     * @param filePath 目标路径
     */
    private void removeFile(String filePath) {
        if (filePath == null || filePath.length() == 0) {
            return;
        }
        try {
            File file = new File(filePath);
            if (file.exists()) {
                removeFile(file);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 删除文件或目录
     *
     * @param file 文件
     */
    private static void removeFile(File file) {
        //如果是文件直接删除
        if (file.isFile()) {
            file.delete();
            return;
        }
        //如果是目录，递归判断，如果是空目录，直接删除，如果是文件，遍历删除
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                removeFile(f);
            }
            file.delete();
        }
    }

    private float size = 0;

    /**
     * @param filePath
     * @return
     */
    public float getFileSize(String filePath) {
        if (filePath == null || filePath.length() == 0) {
            return 0;
        }
        try {
            File file = new File(filePath);
            if (file.exists()) {
                size = 0;
                return getSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private float getSize(File file) {
        try {
            //如果是目录则递归计算其内容的总大小
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                for (File f : children) {
                    size += getSize(f);
                }
                return size;
            }
            //如果是文件则直接返回其大小
            else {
                return (float) file.length();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 拷贝文件
     *
     * @param filePath   文件路径
     * @param newDirPath 拷贝目标路径
     */
    public void copyFile(String filePath, String newDirPath) {
        if (filePath == null || filePath.length() == 0) {
            return;
        }
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return;
            }
            //判断目录是否存在，如果不存在，则创建
            File newDir = new File(newDirPath);
            if (!newDir.exists()) {
                newDir.mkdirs();
            }
            //创建目标文件
            File newFile = newFile(newDirPath, file.getName());
            InputStream is = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(newFile);
            byte[] buffer = new byte[4096];
            int byteCount = 0;
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
            is.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拷贝目录
     *
     * @param dirPath    源目标路径
     * @param newDirPath 拷贝目标路径
     */
    public void copyDir(String dirPath, String newDirPath) {
        if (dirPath == null || dirPath.length() == 0
                || newDirPath == null || newDirPath.length() == 0) {
            return;
        }
        try {
            File file = new File(dirPath);
            if (!file.exists() && !file.isDirectory()) {
                return;
            }
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                return;
            }
            File newFile = new File(newDirPath);
            newFile.mkdirs();
            for (File fileTemp : childFile) {
                if (fileTemp.isDirectory()) {
                    copyDir(fileTemp.getPath(), newDirPath + "/" + fileTemp.getName());
                } else {
                    copyFile(fileTemp.getPath(), newDirPath);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 剪切文件
     *
     * @param filePath   源文件路径
     * @param newDirPath 拷贝目标路径
     */
    public void moveFile(String filePath, String newDirPath) {
        if (filePath == null || filePath.length() == 0
                || newDirPath == null || newDirPath.length() == 0) {
            return;
        }
        try {
            //拷贝文件
            copyFile(filePath, newDirPath);
            //删除原文件
            removeFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 剪切目录
     *
     * @param dirPath    源目录路径
     * @param newDirPath 拷贝目标路径
     */
    public void moveDir(String dirPath, String newDirPath) {
        if (dirPath == null || dirPath.length() == 0
                || newDirPath == null || newDirPath.length() == 0) {
            return;
        }
        try {
            //拷贝目录
            copyDir(dirPath, newDirPath);
            //删除目录
            removeFile(dirPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
