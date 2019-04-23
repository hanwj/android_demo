package com.caixiaoxiao.plugin.host;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * 文件名: ProxyActivity
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019/4/23
 */
public class ProxyActivity extends FragmentActivity{
    public final static String EXTRA_DEX_PATH = "dexpath";
    public final static String EXTRA_CLASS = "classname";

    private String mDexPath;
    private String mClass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDexPath = getIntent().getStringExtra(EXTRA_DEX_PATH);
        mClass = getIntent().getStringExtra(EXTRA_CLASS);

        launchActivity(mClass);
    }

    private void launchActivity(String className) {
        File dexOutputDir = this.getDir("dex",0);
        final String dexOutputPath = dexOutputDir.getAbsolutePath();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        DexClassLoader dexClassLoader = new DexClassLoader(mDexPath,dexOutputPath,null,classLoader);

        try {
            Class<?> cls = dexClassLoader.loadClass(className);
            Constructor<?> constructor = cls.getConstructor(new Class[] {});
            Object instance = constructor.newInstance(new Object[] {});

            Method setProxy = cls.getMethod("setProxy",new Class<?>[] {Activity.class});
            setProxy.setAccessible(true);
            setProxy.invoke(instance,new Object[] {this});

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
