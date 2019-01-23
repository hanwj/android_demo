package com.xiaoxiao.testrxjava;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 文件名: VirgoApplication
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019/1/17
 */
public class VirgoApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initThird();
    }

    private void initThird(){
        Fresco.initialize(this);
    }
}
