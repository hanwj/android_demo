package com.xiaoxiao.eventbus;

/**
 * Created by caixiaoxiao on 23/2/17.
 */
public class ServerErrorEvent {
    private String eventName;
    private int errorCode;
    private String errorMsg;

    public ServerErrorEvent(String eventName,int errorCode,String errorMsg){
        this.eventName = eventName;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getEventName(){
        return eventName;
    }

    public int getErrorCode(){
        return errorCode;
    }

    public String getErrorMsg(){
        return errorMsg;
    }
}
