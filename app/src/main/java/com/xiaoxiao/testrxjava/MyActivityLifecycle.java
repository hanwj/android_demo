package com.xiaoxiao.testrxjava;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.xiaoxiao.utils.LogUtils;

/**
 * @author caixiaoxiao
 * @desc 监听activity生命周期
 * @date 4/29/21
 */
public class MyActivityLifecycle implements Application.ActivityLifecycleCallbacks {
    private final String TAG = MyActivityLifecycle.class.getSimpleName();
    private static MyActivityLifecycle INSTANCE = new MyActivityLifecycle();


    public static MyActivityLifecycle getInstance(){
        return INSTANCE;
    }

    private MyActivityLifecycle(){
    }

    public void init(Application app){
        app.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogUtils.d(TAG,activity + "#onCreate");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogUtils.d(TAG,activity + "#onStart");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        LogUtils.d(TAG,activity + "#onResume");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogUtils.d(TAG,activity + "#onPause");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogUtils.d(TAG,activity + "#onStop");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtils.d(TAG,activity + "#onDestroy");
    }
}
