package com.xiaoxiao.testrxjava.dagger2;

import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by caixiaoxiao on 1/3/17.
 */
public class ActivityModule {

    @Provides
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder().build();
    }
}
