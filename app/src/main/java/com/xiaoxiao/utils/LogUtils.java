package com.xiaoxiao.utils;

import android.util.Log;

import com.xiaoxiao.testrxjava.BuildConfig;

/**
 * Created by caixiaoxiao on 21/2/17.
 */
public final class LogUtils {
    private static boolean DEBUG = BuildConfig.DEBUG;
    public static void v(String tag,String msg){
        if (DEBUG){
            Log.v(tag,msg);
        }
    }

    public static void d(String tag,String msg){
        if (DEBUG){
            Log.d(tag, msg);
        }
    }

    public static void i(String tag,String msg){
        if (DEBUG){
            Log.i(tag, msg);
        }
    }

    public static void w(String tag,String msg){
        if (DEBUG){
            Log.w(tag,msg);
        }
    }

    public static void e(String tag,String msg){
        if (DEBUG){
            Log.e(tag, msg);
        }
    }

    public static String getTag(){
        return "";
    }
}
