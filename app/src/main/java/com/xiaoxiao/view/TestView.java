package com.xiaoxiao.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xiaoxiao.utils.LogUtils;

/**
 * 文件名: TestView
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2018/11/13
 */
public class TestView extends View {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean touch = super.onTouchEvent(event);
        LogUtils.e("TestView:onTouchEvent","action:"+ event.getAction() + ",touch:" + touch);
//        return super.onTouchEvent(event);
        return touch;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean touch = super.dispatchTouchEvent(event);
        LogUtils.e("TestView:dispatchTouchEvent","action:"+ event.getAction() + ",touch:" + touch);
//        return super.onTouchEvent(event);
        return touch;
    }
}
