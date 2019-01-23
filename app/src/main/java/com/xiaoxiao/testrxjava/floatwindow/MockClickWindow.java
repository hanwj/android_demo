package com.xiaoxiao.testrxjava.floatwindow;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.xiaoxiao.utils.Util;

/**
 * 文件名: MockClickWindow
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019/1/8
 */
public class MockClickWindow {
    private static MockClickWindow instance = new MockClickWindow();

    public static MockClickWindow getInstance() {
        return instance;
    }

    public void show(Context context){
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.height = Util.dp2px(200);
        lp.gravity = Gravity.TOP;
        lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        lp.packageName = context.getPackageName();
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

        View mockClickView = new MockClickView(context);

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(mockClickView,lp);
    }
}
