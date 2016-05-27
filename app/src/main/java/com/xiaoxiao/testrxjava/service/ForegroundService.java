package com.xiaoxiao.testrxjava.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xiaoxiao.testrxjava.R;

/**
 * Created by caixiaoxiao on 27/5/16.
 */
public class ForegroundService extends Service {
    private static String TAG = "ForegroundService";
    private NotificationManager mNM;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        super.onCreate();
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

//        Notification notification = new Notification(R.drawable.ic_menu_camera,
//                "ForegroundService started",System.currentTimeMillis());
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,new Intent(this,ServiceActivity.class),0);

//        notification.setLatestEventInfo(this,"ForegroundService","ForegroundService started",contentIntent);
        Notification.Builder builder = new Notification.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("Foreground Service")
                .setContentText("Foreground Service started")
                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_menu_camera)
                .setWhen(System.currentTimeMillis())
                .setOngoing(true);
        Notification notification = builder.getNotification();
        startForeground(1,notification);

    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestory");
        super.onDestroy();
        stopForeground(true);
    }
}
