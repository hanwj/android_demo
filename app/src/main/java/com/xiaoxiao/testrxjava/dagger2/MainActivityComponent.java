package com.xiaoxiao.testrxjava.dagger2;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.xiaoxiao.testrxjava.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by caixiaoxiao on 1/3/17.
 */
@Singleton
@Component(modules = ActivityModule.class)
public interface MainActivityComponent {
    TestActivityComponent provideTestComponent();
    void inject(MainActivity mainActivity);
}
