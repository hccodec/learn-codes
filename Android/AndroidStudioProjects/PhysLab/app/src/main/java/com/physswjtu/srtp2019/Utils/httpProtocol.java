/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.Utils;

/**
 * 由 韩宝佳 创建
 * 日期为 2019/6/29
 * 工程 PhysLab
 */

public class httpProtocol {
    public interface post {
        String getInterface(boolean... a);
    }

    public interface get {
        String getInterface(String... s);
    }

    public interface put {
        String getInterface(boolean... a);
    }
}
