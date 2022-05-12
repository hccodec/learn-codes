package com.example.myapplication.customview;

import java.util.ArrayList;

public class Data {

    public static ArrayList<Ball> list = new ArrayList<Ball>();
    public static boolean pauseflag = false;

    //便于在其它类里面获取pauseflag
    public static void setPauseflag(boolean pauseflag) {
        Data.pauseflag = pauseflag;
    }

}