package com.xiaoxiao.testrxjava;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by meibo-design on 16/5/16.
 */
public class SimpleService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SimpleService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
