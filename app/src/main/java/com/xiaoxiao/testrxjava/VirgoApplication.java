package com.xiaoxiao.testrxjava;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.xiaoxiao.testrxjava.service.LocalService;
import com.xiaoxiao.utils.LogUtils;

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
        MyActivityLifecycle.getInstance().init(this);
        initThird();
        LogUtils.e("VirgoApplication","app onCreate");

        startService(new Intent(this, LocalService.class));
    }

    private void initThird(){
        Fresco.initialize(this);
    }

    @Override
    public void onTerminate() {
        LogUtils.e("VirgoApplication","onTerminate");
        super.onTerminate();
    }
}
