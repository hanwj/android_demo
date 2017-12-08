package com.xiaoxiao.testrxjava.lifecycle;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoxiao.testrxjava.R;

/**
 * Created by caixiaoxiao on 14/11/17.
 */

public class TestFragment extends Fragment{
    private static String TAG = TestFragment.class.getSimpleName();

    @Override
    public void onAttach(Context context) {
        Log.e(TAG,"onAttach context");
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        Log.e(TAG,"onAttach activity");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG,"onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG,"onCreateView");
        return inflater.inflate(R.layout.frag_test,null);
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e(TAG,"onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.e(TAG,"onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.e(TAG,"onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.e(TAG,"onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.e(TAG,"onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG,"onDestrouView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.e(TAG,"onDetach");
        super.onDetach();
    }
}
