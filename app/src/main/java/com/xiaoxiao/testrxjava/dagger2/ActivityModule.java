package com.xiaoxiao.testrxjava.dagger2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by caixiaoxiao on 1/3/17.
 */
@Module
public class ActivityModule {
    @Provides
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    User provideUser(){
        return new User();
    }
}
