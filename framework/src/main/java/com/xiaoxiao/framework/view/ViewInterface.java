package com.xiaoxiao.framework.view;

import android.view.View;

/**
 * Created by caixiaoxiao on 11/8/16.
 */
public interface ViewInterface {
    void initArgs();     //变量初始化工作
    void initViews();    //初始化控件
    void addListeners(); //添加控件监听
    void loadData();     //加载数据
    int getLayoutId();   //获取界面布局ID
}
