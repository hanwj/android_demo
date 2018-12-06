package com.xiaoxiao.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.xiaoxiao.utils.LogUtils;

/**
 * 文件名: TestImageView
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2018/11/13
 */
public class TestImageView extends ImageView {
    public TestImageView(Context context) {
        super(context);
    }

    public TestImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.e("TestImageView:onTouchEvent","start");
        boolean touch = super.onTouchEvent(event);
        LogUtils.e("TestImageView:onTouchEvent","action:"+ event.getAction() + ",touch:" + touch);
//        return super.onTouchEvent(event);
        return touch;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtils.e("TestImageView:dispatchTouchEVent","start");
        boolean touch = super.dispatchTouchEvent(event);
        LogUtils.e("TestImageView:dispatchTouchEvent","action:"+ event.getAction() + ",touch:" + touch);
//        return super.onTouchEvent(event);
        return touch;
    }
}
