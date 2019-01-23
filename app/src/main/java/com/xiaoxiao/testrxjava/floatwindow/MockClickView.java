package com.xiaoxiao.testrxjava.floatwindow;

import android.app.Instrumentation;
import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.xiaoxiao.testrxjava.R;
import com.xiaoxiao.utils.Util;

import java.io.IOException;

/**
 * 文件名: MockClickView
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019/1/8
 */
public class MockClickView extends FrameLayout{

    private int screenHeight;
    private View clickAreaView;
    private Button switchBtn;
    public MockClickView(@NonNull Context context) {
        this(context,null);
    }

    public MockClickView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MockClickView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        screenHeight = Util.getScreenHeight();
        setBackgroundColor(Color.TRANSPARENT);
        inflate(context, R.layout.view_mock_click,this);
        clickAreaView = findViewById(R.id.click_area);
        switchBtn = (Button) findViewById(R.id.btn_switch);

//        clickAreaView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mockClick();
//            }
//        });
        clickAreaView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                }
                float x = event.getX();
                float y = event.getY();
                mockClick(x,y,event.getAction());
                return false;
            }
        });
    }

    private void mockClick(final float x,final float y,final int action)
    {
        final float mockY = screenHeight - clickAreaView.getHeight() + y;
//        String[] order = {"input","tap","" + x,"" + y};
//        try {
//            new ProcessBuilder(order).start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                Instrumentation inst = new Instrumentation();
                inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_DOWN, x, mockY, 0));
                inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_UP, x, mockY, 0));
            }
        }.start();
    }
}
