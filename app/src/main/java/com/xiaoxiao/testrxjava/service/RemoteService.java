package com.xiaoxiao.testrxjava.service;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.support.annotation.Nullable;
import android.util.Log;

import org.w3c.dom.Text;

/**
 * Created by caixiaoxiao on 27/5/16.
 */
public class RemoteService extends Service{
    public class SimpleBinder extends Binder{
        public RemoteService getService(){
            return RemoteService.this;
        }
    }

    private static String TAG = "remoteService";
//    private final IBinder mBinder = new SimpleBinder();

    RemoteAIDLService.Stub mBinder = new RemoteAIDLService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int plus(int a, int b){
            return a + b;
        }

        @Override
        public String toUpperCase(String str){
            if (str != null){
                return str.toUpperCase();
            }

            return null;
        }
    };

    @Override
    public void onCreate() {
        Log.e(TAG,"onCreate");
        Log.e(TAG,"process id is " + android.os.Process.myPid());
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"onBind");
        return mBinder.asBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "Received start id " + startId + " : " + intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestory");
        super.onDestroy();
    }

}
