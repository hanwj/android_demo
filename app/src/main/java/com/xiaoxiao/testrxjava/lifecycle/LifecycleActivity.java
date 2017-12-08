package com.xiaoxiao.testrxjava.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xiaoxiao.testrxjava.R;

/**
 * Created by caixiaoxiao on 14/11/17.
 */

public class LifecycleActivity extends FragmentActivity{
    private static String TAG = LifecycleActivity.class.getSimpleName();
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Log.e(TAG,"onCreateView");
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifycycle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frag_container,new TestFragment(),"test")
                .commit();
    }

    @Override
    protected void onStart() {
        Log.e(TAG,"onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e(TAG,"onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG,"onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e(TAG,"onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG,"onDestory");
        super.onDestroy();
    }
}
