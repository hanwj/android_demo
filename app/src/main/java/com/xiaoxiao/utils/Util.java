package com.xiaoxiao.utils;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.view.ViewConfiguration;

import java.lang.reflect.Method;

/**
 * Created by caixiaoxiao on 7/3/17.
 */
public class Util {
    public static float getScreenDensity(){
        return Resources.getSystem().getDisplayMetrics().density;
    }
    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     * @return
     */
    public static int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取状态栏高度
     * @return
     */
    public static int getStatusBarHeight(){
        int resId = Resources.getSystem().getIdentifier("status_bar_height","dimen","android");
        if (resId > 0){
            return Resources.getSystem().getDimensionPixelSize(resId);
        }
        return 1;
    }

    /**
     * dp转换px
     * @param dp
     * @return
     */
    public static int dp2px(float dp){
        return (int)(dp * getScreenDensity() + 0.5f);
    }

    /**
     * px转换dp
     * @param px
     * @return
     */
    public static int px2dp(int px){
        return (int)(px / getScreenDensity() + 0.5f);
    }

    /**
     * sp转换px
     * @param sp
     * @return
     */
    public static int sp2px(int sp){
        return (int)(sp * Resources.getSystem().getDisplayMetrics().scaledDensity + 0.5f);
    }

    /**
     * px转换sp
     * @param sp
     * @return
     */
    public static int px2sp(int sp){
        return (int)(sp / Resources.getSystem().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static int dp2sp(int dp){
        return px2sp(dp2px(dp));
    }

    public static boolean isAbsoluteUrl(String url){
        return !TextUtils.isEmpty(url) && (url.startsWith("http://") || url.startsWith("https://"));
    }

    /**
     * 获取虚拟按键栏高度
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (hasNavBar(context)){
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 检查是否存在虚拟按键栏
     * @param context
     * @return
     */
    private static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     * @return
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }

    /**
     * 判断是否是wifi
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()
                    && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }
}
