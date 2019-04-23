package com.caixiaoxiao.plugin.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

/**
 * 文件名: BasePluginActivity
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019/4/23
 */
public class BasePluginActivity extends FragmentActivity{
    public final static String EXTRA_DEX_PATH = "dexpath";
    public final static String EXTRA_CLASS = "classname";
    public final static String EXTRA_FROM = "from";
    public final static int FROM_INTERNAL = 0;
    public final static int FROM_EXTERNAL = 1;


    private int mFrom = FROM_INTERNAL;
    private Activity mProxyAct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null){
            mFrom = savedInstanceState.getInt(EXTRA_FROM);
        }

        if (mFrom == FROM_INTERNAL){
            mProxyAct = this;
            super.onCreate(savedInstanceState);
        }
    }

    public void setProxy(Activity proxy){
        this.mProxyAct = proxy;
    }

    public void startActivityByProxy(String className){
        if (this.mProxyAct == this){
            Intent intent = new Intent();
            intent.setClassName(this,className);
            startActivity(intent);
        }else {
            Intent intent = new Intent();
            intent.setClass(mProxyAct,mProxyAct.getClass());
            intent.putExtra(EXTRA_CLASS,className);
            intent.putExtra(EXTRA_DEX_PATH,mProxyAct.getIntent().getStringExtra(EXTRA_DEX_PATH));
            mProxyAct.startActivity(intent);
        }
    }

    @Override
    public void setContentView(View view) {
        if (this.mProxyAct == this){
            super.setContentView(view);
        }else {
            this.mProxyAct.setContentView(view);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (this.mProxyAct == this){
            super.setContentView(layoutResID);
        }else {
            this.mProxyAct.setContentView(layoutResID);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (this.mProxyAct == this){
            super.setContentView(view,params);
        }else {
            this.mProxyAct.setContentView(view,params);
        }
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        if (this.mProxyAct == this){
            super.addContentView(view,params);
        }else {
            this.mProxyAct.addContentView(view,params);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (this.mProxyAct == this){
            return super.findViewById(id);
        }else {
            return this.mProxyAct.findViewById(id);
        }
    }
}
