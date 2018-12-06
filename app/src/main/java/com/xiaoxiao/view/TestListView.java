package com.xiaoxiao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import com.xiaoxiao.utils.LogUtils;

/**
 * 文件名: TestListView
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2018/11/13
 */
public class TestListView extends ListView{
    public TestListView(Context context) {
        super(context);
    }

    public TestListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean touch = super.onTouchEvent(event);
        LogUtils.d("TestListView:onTouchEvent","action:"+ event.getAction() + ",touch:" + touch);
//        return super.onTouchEvent(event);
        return touch;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean touch = super.dispatchTouchEvent(event);
        LogUtils.d("TestListView:dispatchTouchEvent","action:"+ event.getAction() + ",touch:" + touch);
//        return super.onTouchEvent(event);
        return touch;
    }


}
