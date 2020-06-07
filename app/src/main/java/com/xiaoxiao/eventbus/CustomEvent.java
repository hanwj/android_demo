package com.xiaoxiao.eventbus;

import android.os.AsyncTask;
import android.text.TextUtils;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;
import java.util.Optional;
import java.util.concurrent.Executors;

/**
 * Created by caixiaoxiao on 23/2/17.
 */
public class CustomEvent {
    private String event;
    private Object data;

    public CustomEvent(String event,Object data){
        if (TextUtils.isEmpty(event)){
            throw new IllegalArgumentException("CustomEvent:event is not null");
        }
        this.event = event;
        this.data = data;

//        Executors.newCachedThreadPool()
    }

    public String getEvent(){
        return event;
    }

    public Object getData(){
        return data;
    }

    AsyncTask asyncTask = new AsyncTask() {
        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }
    };
}
