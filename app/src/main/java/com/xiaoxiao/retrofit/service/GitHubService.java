package com.xiaoxiao.retrofit.service;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

/**
 * Created by caixiaoxiao on 16/2/17.
 */
public interface GitHubService {
//    @GET("user/{user}/repos")
//    Call<Object> listRepos(@Path("user") String user);
    @GET("https://api.github.com/")
    Call<ResponseBody> getHomepage();

    @GET("http://api.github.com/user/{user}/repos")
    Call<ResponseBody> listRepos(@Path("user") String user);
    
}
