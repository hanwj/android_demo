package com.xiaoxiao.retrofit;

import android.util.Log;

import com.xiaoxiao.retrofit.callback.ICallback;
import com.xiaoxiao.retrofit.param.BaseParamHandler;
import com.xiaoxiao.retrofit.callback.CommonServerCallback;
import com.xiaoxiao.retrofit.service.GetService;
import com.xiaoxiao.retrofit.service.GitHubService;
import com.xiaoxiao.retrofit.service.PostService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by caixiaoxiao on 16/2/17.
 */
public class RetrofitServer {
    private static String TAG = "RetrofitServerApi";
    private static String hostUrl = "http://www.xcyo.com/app/";
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder builder = request.newBuilder()
                            .header("YO-C-E", "1")
                            .header("YO-C-C", "1")
                            .method(request.method(), request.body());

                    return chain.proceed(builder.build());
                }
            })
            .build();
    private static Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .baseUrl(hostUrl)
            .addConverterFactory(new CustomConverters())
            .build();

    private static GetService get = retrofit.create(GetService.class);
    private static PostService post = retrofit.create(PostService.class);

    public static void callServer(BaseParamHandler paramHandler, ICallback callback){
//        Map<String,String> paramsMap = new HashMap<>();
//        if (params != null){
//            for (int i = 0; i < params.length;i += 2){
//                if (i < params.length - 2){
//                    paramsMap.put(params[i],params[i+1]);
//                }
//            }
//        }
        Call<ResponseBody> call;
        if ("get".equals(paramHandler.getRequestType())){
            call = get.callServer(paramHandler.getMethod(),paramHandler.getRequestParams());
        }else if ("post".equals(paramHandler.getRequestType())){
            call = post.callServer(paramHandler.getMethod(),paramHandler.getRequestParams().get("key"));
        }else {
            throw new IllegalArgumentException("Illegal request.type:" + paramHandler.getRequestType());
        }
        if (call != null){
            call.enqueue(new CommonServerCallback(callback));
        }
    }

    public static void getListRepos(){
        GitHubService service = retrofit.create(GitHubService.class);
        Call<ResponseBody> call = service.listRepos("oc tocat");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()){
                        Log.e(TAG,"success");
                        Log.e(TAG,response.body().string());
                    }else {
                        Log.e(TAG,"code:" + response.code() + ",msg:" + response.message());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static void getHomepage(){
        GitHubService service = retrofit.create(GitHubService.class);
        Call<ResponseBody> call = service.getHomepage();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e(TAG,response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
