package com.xiaoxiao.eventbus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by caixiaoxiao on 23/2/17.
 */
public abstract class EventBusReceiver {
    protected void register(){
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    private void onCustomEvent(CustomEvent event){
        if (event.getEvent().equals("")){

        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    private void onBackgroundEvent(){

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    private void onPostingEvent(){

    }

    protected void unRegister(){
        EventBus.getDefault().unregister(this);
    }
}
