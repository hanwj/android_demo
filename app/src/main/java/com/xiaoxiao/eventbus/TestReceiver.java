package com.xiaoxiao.eventbus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by caixiaoxiao on 23/2/17.
 */
public class TestReceiver implements IEventBusReceiver{
    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEventOnUiThread(CustomEvent event) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void handleEventOnBackground() {

    }
}