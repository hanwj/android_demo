package com.xiaoxiao.retrofit.callback;

/**
 * Created by caixiaoxiao on 21/2/17.
 */
public interface ICallback {
    void onOK(Object data);
    void onError(int code,String msg);
}
