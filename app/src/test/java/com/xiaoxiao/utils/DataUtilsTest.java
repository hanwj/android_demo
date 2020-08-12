package com.xiaoxiao.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 文件名: DataUtilsTest
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2020/7/29
 */
public class DataUtilsTest {

    @Test
    public void md5() {
        String md5 = DataUtils.md5("caixiaoxiao");
        System.out.println("md5:" + md5);
    }
}