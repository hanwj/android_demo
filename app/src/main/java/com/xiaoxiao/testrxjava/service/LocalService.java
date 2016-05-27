package com.xiaoxiao.testrxjava.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by caixiaoxiao on 27/5/16.
 */
public class LocalService extends Service {
    public class SimpleBinder extends Binder {
        public LocalService getService(){
            return LocalService.this;
        }
    }

    private static String TAG = "localservice";
    private final IBinder mBinder = new SimpleBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        Log.e(TAG,"onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e(TAG,"onStart");
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestroy");
        super.onDestroy();
    }
}
