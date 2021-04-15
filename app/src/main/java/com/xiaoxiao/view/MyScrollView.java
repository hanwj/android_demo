package com.xiaoxiao.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.ScrollView;

import com.xiaoxiao.utils.LogUtils;

/**
 * Created by caixiaoxiao on 14/7/16.
 */
public class MyScrollView extends ScrollView{
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.e("MyScrollView","dispatchTouchEvent#ev:" + ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() != MotionEvent.ACTION_MOVE){
            LogUtils.e("MyScrollView","onTouchEvent#ev:" + ev);
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.e("MyScrollView","onInterceptTouchEvent#ev:" + ev);
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastTouchPosY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float curTouchPosY = ev.getRawY();
                mDirect = curTouchPosY - mLastTouchPosY > 0 ? 0 : 1;
                mLastTouchPosY = curTouchPosY;
                break;
        }
        if (!isScroll(ev)){
            LogUtils.e("MyScrollView","onInterceptTouchEvent#1");
            return false;
        }
        boolean touch = super.onInterceptTouchEvent(ev);
        LogUtils.e("MyScrollView","onInterceptTouchEvent#2:" + touch);
        return touch;
    }

    private ListView mListView;
    private float mLastTouchPosY;
    private int mDirect = -1; // 1：向上 0:向下
    public void setList(ListView listview){
        mListView = listview;
    }

    public boolean isScroll(MotionEvent ev){
        if (mListView != null){
            int [] location = new int[2];
            mListView.getLocationOnScreen(location);
//            LogUtils.e("MyScrollView","location:" + location[0] + "," + location[1]);
            Rect rect = new Rect();
            mListView.getGlobalVisibleRect(rect);
//            LogUtils.e("MyScrollView","rect:" + rect);
            if (ev.getRawX() >= location[0] && ev.getRawX() <= location[0] + mListView.getWidth()
                    && ev.getRawY() >= location[1] && ev.getRawY() <= location[1] + mListView.getHeight()){
                if (mListView.getAdapter() != null && mListView.getAdapter().getCount() > 0){
                    if (1 == mDirect){
                        if (mListView.getLastVisiblePosition() != mListView.getCount() - 1){
                            return false;
                        }
                    }else {
                        if (mListView.getFirstVisiblePosition() != 0){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
