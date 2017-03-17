package com.xiaoxiao.eventbus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by caixiaoxiao on 23/2/17.
 * eventbus事件处理接口，响应事件需实现该接口，
 * 主要是为了统一事件处理的方法
 * 实现该接口时需要把响应的注解添加上
 */
public interface IEventBusReceiver {
    @Subscribe(threadMode = ThreadMode.MAIN)
    void handleEventOnUiThread(CustomEvent event);

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    void handleEventOnBackground();

}
