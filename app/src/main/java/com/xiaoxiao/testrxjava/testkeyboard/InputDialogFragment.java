package com.xiaoxiao.testrxjava.testkeyboard;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import com.xiaoxiao.testrxjava.R;
import com.xiaoxiao.utils.KeyboardStatusObserver;
import com.xiaoxiao.utils.LogUtils;
import com.xiaoxiao.utils.Util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by caixiaoxiao on 8/12/17.
 */

public class InputDialogFragment extends DialogFragment{

    private View rootView;
    private View faceView;
    ViewTreeObserver.OnGlobalLayoutListener listener;
    private KeyboardStatusObserver keyboardStatusObserver;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.dialog_input,null);
        faceView = rootView.findViewById(R.id.chat_face);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


//        listener = new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Rect r = new Rect();
//                rootView.getWindowVisibleDisplayFrame(r);
//                int heightDiff = rootView.getHeight() - (r.bottom - r.top);
//                LogUtils.e("InputDialogFragment","diff : " + heightDiff);
//                ViewGroup.LayoutParams lp = faceView.getLayoutParams();
//                if (heightDiff > 100){
//                    lp.height = heightDiff;
//                }else {
//                    lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//                }
//                faceView.setLayoutParams(lp);
//            }
//        };
//        rootView.getViewTreeObserver().addOnGlobalLayoutListener(listener);

        keyboardStatusObserver = new KeyboardStatusObserver(rootView);
        keyboardStatusObserver.setOnKeyboardStatusListener(new KeyboardStatusObserver.OnKeyboardStatusListener() {
            @Override
            public void onKeyboardOpen(int keyboardHeight) {
                ViewGroup.LayoutParams lp = faceView.getLayoutParams();
                lp.height = keyboardHeight;
                faceView.setLayoutParams(lp);
            }

            @Override
            public void onKeyboardClose() {
                ViewGroup.LayoutParams lp = faceView.getLayoutParams();
                lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                faceView.setLayoutParams(lp);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        ViewGroup.LayoutParams rootLp = rootView.getLayoutParams();
        rootLp.height = Util.getScreenHeight() - Util.getStatusBarHeight();
        rootView.setLayoutParams(rootLp);
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.dimAmount = 0.0f;
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33ffffff")));
        ViewGroup.LayoutParams rootLp = rootView.getLayoutParams();
        rootLp.height = Util.getScreenHeight() - Util.getStatusBarHeight();
        rootView.setLayoutParams(rootLp);
    }

    @Override
    public void onDestroyView() {
//        rootView.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        keyboardStatusObserver.release();
        super.onDestroyView();
    }
}
