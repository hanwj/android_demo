package com.xiaoxiao.framework.view;

import android.view.View;

/**
 * Created by caixiaoxiao on 11/8/16.
 */
public interface ViewInterface {
    abstract void initArgs();     //变量初始化工作
    abstract void initViews();    //初始化控件
    abstract void addListeners(); //添加控件监听
    abstract int getLayoutId();   //获取界面布局ID
    abstract View getLayout();
}
