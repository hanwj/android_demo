package com.caixiaoxiao.plugin.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.caixiaoxiao.plugin.client.BasePluginActivity;

/**
 * 文件名: MainActivity
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019/4/23
 */
public class MainActivity extends BasePluginActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }
}
