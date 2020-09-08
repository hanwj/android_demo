package com.caixiaoxiao.plugin.host;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;

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
    private final static String TAG = "ProxyActivity";

    public final static String EXTRA_DEX_PATH = "dexpath";
    public final static String EXTRA_CLASS = "classname";
    public final static String EXTRA_FROM = "from";

    private String mDexPath;
    private String mClass;
    private AssetManager mAssetManager;
    private Resources mResources;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDexPath = getIntent().getStringExtra(EXTRA_DEX_PATH);
        mClass = getIntent().getStringExtra(EXTRA_CLASS);
        Log.e(TAG,"apk:" + mDexPath + "class :" + mClass);
        if (isFileExist(mDexPath)){
            loadResources();
            launchActivity(mClass);
        }else {
            Log.e(TAG,"apk's file:" + mDexPath +  "not exist");
        }
    }

    private void launchActivity(String className) {
        Log.e(TAG,"launchActivity:" + className);
        File dexOutputDir = this.getDir("dex",0);
        final String dexOutputPath = dexOutputDir.getAbsolutePath();
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        DexClassLoader dexClassLoader = new DexClassLoader(mDexPath,dexOutputPath,null,getClassLoader());

        try {
            Class<?> cls = dexClassLoader.loadClass(className);
            Constructor<?> constructor = cls.getConstructor(new Class[] {});
            Object instance = constructor.newInstance(new Object[] {});

            Method setProxy = cls.getMethod("setProxy",Activity.class);
            setProxy.setAccessible(true);
            setProxy.invoke(instance,this);

            Method onCreate = cls.getDeclaredMethod("onCreate",Bundle.class);
            onCreate.setAccessible(true);
            Bundle bundle = new Bundle();
            bundle.putInt(EXTRA_FROM,1);
            bundle.putString(EXTRA_DEX_PATH,mDexPath);
            onCreate.invoke(instance,bundle);

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

    private void loadResources(){
        Log.e(TAG,"loadResources");
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getDeclaredMethod("addAssetPath",String.class);
            addAssetPath.setAccessible(true);
            addAssetPath.invoke(assetManager,mDexPath);
            mAssetManager = assetManager;

            Resources superRes = super.getResources();
            mResources = new Resources(assetManager,superRes.getDisplayMetrics(),superRes.getConfiguration());
            Resources.Theme theme = mResources.newTheme();
            theme.setTo(super.getTheme());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return mResources != null ? mResources : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return mAssetManager != null ? mAssetManager : super.getAssets();
    }

    private boolean isFileExist(String filePath){
        File file = new File(filePath);
        if (file.exists()){
            return true;
        }
        return false;
    }
}
