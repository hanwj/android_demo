package com.xiaoxiao.testrxjava.dagger2;

import com.xiaoxiao.testrxjava.MainActivity;

import dagger.Component;

/**
 * Created by caixiaoxiao on 1/3/17.
 */
@Component(modules = ActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
