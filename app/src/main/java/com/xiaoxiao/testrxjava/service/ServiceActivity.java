package com.xiaoxiao.testrxjava.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.*;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xiaoxiao.testrxjava.R;

/**
 * Created by caixiaoxiao on 27/5/16.
 */
public class ServiceActivity extends FragmentActivity {
    private static String TAG = "ServiceActivity";
    private ServiceConnection mSC;
    private ServiceConnection mRemoteSC;
    private boolean isBind = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        stopService(new Intent(ServiceActivity.this, ForegroundService.class));

        mSC = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                LocalService.SimpleBinder binder = (LocalService.SimpleBinder)service;
                Log.e(TAG,binder.getService().toString() + " is connected");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        mRemoteSC = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                RemoteAIDLService binder = RemoteAIDLService.Stub.asInterface(service);
                try {
                    Log.e(TAG,"result is " + binder.plus(3,5));
                    Log.e(TAG,"upperStr is " + binder.toUpperCase("hello,remoteService"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        //startService stopService
        ((Button)findViewById(R.id.start_service)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(ServiceActivity.this, LocalService.class));
            }
        });
        ((Button)findViewById(R.id.stop_service)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(ServiceActivity.this, LocalService.class));
            }
        });

        //bindService unbindService
        ((Button)findViewById(R.id.bind_service)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(ServiceActivity.this, LocalService.class), mSC, BIND_AUTO_CREATE);
                isBind = true;
            }
        });
        ((Button)findViewById(R.id.unbind_service)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBind){
                    unbindService(mSC);
                    isBind = false;
                }
            }
        });

        //前台服务
        ((Button)findViewById(R.id.foreground_service_start)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(ServiceActivity.this,ForegroundService.class));
            }
        });
        ((Button)findViewById(R.id.foreground_service_stop)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(ServiceActivity.this,ForegroundService.class));
            }
        });

        //启动远程服务
        ((Button)findViewById(R.id.remote_service_start)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"process id is " + android.os.Process.myPid());
                startService(new Intent(ServiceActivity.this, RemoteService.class));
            }
        });
        ((Button)findViewById(R.id.remote_service_stop)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(ServiceActivity.this, RemoteService.class));
            }
        });

        //绑定远程服务
        ((Button)findViewById(R.id.remote_service_bind)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"process id is " + android.os.Process.myPid());
                bindService(new Intent(ServiceActivity.this,RemoteService.class),mRemoteSC,BIND_AUTO_CREATE);
            }
        });
        ((Button)findViewById(R.id.remote_service_unbind)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mRemoteSC);
            }
        });
    }

    private void testMessenger(){
//        Messenger messenger = new Messenger();
//        Message
    }
}
