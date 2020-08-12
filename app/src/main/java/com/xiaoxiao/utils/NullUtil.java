package com.xiaoxiao.utils;

/**
 * 文件名: NullUtil
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2020/7/27
 */
public class NullUtil {

    public static boolean isNull(Object... objs){
        if (objs == null){
            return true;
        }

        for (Object obj : objs){
            if (obj == null){
                return true;
            }
        }

        return false;
    }
}
