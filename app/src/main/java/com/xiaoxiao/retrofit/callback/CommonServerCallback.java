package com.xiaoxiao.retrofit.callback;

import com.xiaoxiao.utils.LogUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by caixiaoxiao on 21/2/17.
 */
public class CommonServerCallback implements retrofit2.Callback<ResponseBody>{
    private ICallback mCallback;
    public CommonServerCallback(ICallback callback){
        this.mCallback = callback;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()){
            try {
                String responseStr = response.body().string();
                LogUtils.e("CommonServerCallback",call.request().url().toString());
                LogUtils.e("CommonServerCallback",responseStr);
                if (mCallback != null){
                    mCallback.onOK(responseStr);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            if (mCallback != null){
                mCallback.onError(-1112,"请求出错,http状态:" + response.code());
            }
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        if (mCallback != null){
            mCallback.onError(-1111,"请求发生异常");
        }
    }
}
