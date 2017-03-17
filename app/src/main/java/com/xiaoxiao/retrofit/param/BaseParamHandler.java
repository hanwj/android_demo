package com.xiaoxiao.retrofit.param;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by caixiaoxiao on 21/2/17.
 */
public abstract class BaseParamHandler implements retrofit2.Callback<ResponseBody>{
    protected Map<String,String> mKeyValues;

    private String method = "";

    public BaseParamHandler(String method,String... params){
        this.method = method;
        mKeyValues = new HashMap<>();
        if (params != null){
            if (params.length % 2 != 0){
                throw new IllegalArgumentException("Illegal params:the num of params is a even number");
            }
            for (int i = 0; i < params.length; i+=2){
                mKeyValues.put(params[i],params[i+1]);
            }
        }
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }

    public String getMethod(){
        return method;
    }

    public String getRequestType(){
        return "get";
    }

    public abstract Map<String,String> getRequestParams();

}
