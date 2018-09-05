package com.xiaoxiao.eventbus;

import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by caixiaoxiao on 23/2/17.
 */
public final class EventsConstant {
    public static final String GET_SERVER_CONFIG_OK = "get_server_config_ok"; //从服务器获取配置成功

    private static ServerEvents evt = ServerEvents.SERVER_EXCEPTION;

    public void func(){
        evt.values();
    }

    public enum ServerEvents{
        SERVER_OK(0,"success"),
        SERVER_EXCEPTION(-1,"exception"),
        SERVER_FAIL(300,"fail");

        private int code;
        private String msg;

        ServerEvents(int code,String msg){
//            EnumSet
//            ObjectOutputStream stream = new ObjectOutputStream();
//            stream.writeObject();
            this.code = code;
            this.msg = msg;
        }
    }

}
