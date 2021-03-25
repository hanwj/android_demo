package com.xiaoxiao.utils;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 文件名: SystemBarUtil
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2020/12/17
 */
public class SystemBarUtil {

    public static void setAndroidNativeLightStatusBar(Window window, boolean dark)
    {
        if (window == null)
        {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            View view = window.getDecorView();
            if (view == null)
            {
                return;
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.WHITE);
            if (dark)
            {
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
            else
            {
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }
    }

}
