package com.xiaoxiao.server;

import com.xiaoxiao.eventbus.CustomEvent;
import com.xiaoxiao.eventbus.EventsConstant;
import com.xiaoxiao.eventbus.ServerErrorEvent;
import com.xiaoxiao.retrofit.RetrofitServer;
import com.xiaoxiao.retrofit.callback.ICallback;
import com.xiaoxiao.retrofit.param.PostParamHandler;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by caixiaoxiao on 22/2/17.
 */
public class HomeServerApi {
    /***
     * 从服务器获取配置
     * @param channelName
     */
    public static void getConfigs(String channelName){
        RetrofitServer.callServer(new PostParamHandler("system/start", "channel", channelName), new ICallback() {
            @Override
            public void onOK(Object data) {
                EventBus.getDefault().post(new CustomEvent(EventsConstant.GET_SERVER_CONFIG_OK,(String)data));
            }

            @Override
            public void onError(int code, String msg) {
                EventBus.getDefault().post(new ServerErrorEvent(EventsConstant.GET_SERVER_CONFIG_OK,code,msg));
            }
        });
    }


}
