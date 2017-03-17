package com.xiaoxiao.retrofit.param;

import android.os.Build;

import com.xiaoxiao.utils.DataUtils;
import com.xiaoxiao.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caixiaoxiao on 21/2/17.
 */
public class PostParamHandler extends BaseParamHandler{
    private static String clientInfo = "{"
            + "\"os\":\"android\","
            + "\"osVersion\":\"" + Build.VERSION.RELEASE + "\","
            + "\"phoneBrand\":\"" + Build.BRAND + "\","
            + "\"phoneModel\":\"" + Build.MODEL + "\","
            + "\"uuid\":\"" + "ddddddd" + "\","
            + "\"deviceToken\":\"" + "dddddddddddd" + "\","
            + "\"appVersionName\":\"" + "1.0.0" + "\","
            + "\"appVersionCode\":\"" + "1" + "\","
            + "\"channel\":\"" + "xcyo" + "\""
            + "}";
    public PostParamHandler(String method,String... params){
        super(method,params);
    }

    public PostParamHandler addOneParam(String key,String value){
        mKeyValues.put(key,value);
        return this;
    }

    @Override
    public Map<String,String> getRequestParams(){
        StringBuilder paramsStr = new StringBuilder("{");
        for (Map.Entry<String,String> iterator : mKeyValues.entrySet()){
            paramsStr.append("\"" + iterator.getKey()  + "\":\"" + iterator.getValue() + "\",");
        }
        if (!mKeyValues.isEmpty()){
            paramsStr.deleteCharAt(paramsStr.length() - 1);
        }
        paramsStr.append("}");

        //完整的参数
        String wholeParamsStr = "{"
                + "\"p\":" + paramsStr + ","
                + "\"e\":{\"clientInfo\":" + clientInfo + "},"
                + "\"time\":\"" + System.currentTimeMillis()/1000 + "\""
                + "}";
        LogUtils.e("PostParamHandler","method:" + getMethod() + ",params:" + wholeParamsStr);
        String encryptStr = DataUtils.getBase64(DataUtils.encrypt(DataUtils.compressByZip(wholeParamsStr.getBytes())));
        LogUtils.e("PostParamHandler","method:" + getMethod() + ",encrypt's params:" + encryptStr);
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("key", encryptStr);
        return paramsMap;
    }

    @Override
    public String getRequestType() {
        return "post";
    }
}
