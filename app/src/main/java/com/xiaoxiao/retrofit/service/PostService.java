package com.xiaoxiao.retrofit.service;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by caixiaoxiao on 16/2/17.
 */
public interface PostService {
    @POST("{method}")
    Call<ResponseBody> callServer(@Path("method") String method,@Body String params);
}