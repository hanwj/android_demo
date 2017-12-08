package com.xiaoxiao.utils;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by caixiaoxiao on 8/12/17.
 * 监听键盘的弹出、收起
 * 获取键盘的高度
 */

public class KeyboardStatusObserver {
    private static final int KEYBOARD_MIN_HEIGHT = 100;

    private View rootView;
    private boolean keyboardOpen = false;
    private ViewTreeObserver.OnGlobalLayoutListener listener;
    private OnKeyboardStatusListener onKeyboardStatusListener;

    public KeyboardStatusObserver(final View rootView){
        this.rootView = rootView;
        listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                rootView.getWindowVisibleDisplayFrame(rect);
                int heightDiff = rootView.getHeight() - (rect.bottom - rect.top);
                if (heightDiff > KEYBOARD_MIN_HEIGHT){
                    if (!keyboardOpen){
                        //键盘打开
                        keyboardOpen = true;
                        if (onKeyboardStatusListener != null){
                            onKeyboardStatusListener.onKeyboardOpen(heightDiff);
                        }
                    }
                }else {
                    if (keyboardOpen){
                         //键盘关闭
                        keyboardOpen = false;
                        if (onKeyboardStatusListener != null){
                            onKeyboardStatusListener.onKeyboardClose();
                        }
                    }
                }
            }
        };
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(listener);
    }

    public void setOnKeyboardStatusListener(OnKeyboardStatusListener listener){
        this.onKeyboardStatusListener = listener;
    }

    public void release(){
        rootView.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
    }

    public interface OnKeyboardStatusListener{
        void onKeyboardOpen(int keyboardHeight);
        void onKeyboardClose();
    }
}
