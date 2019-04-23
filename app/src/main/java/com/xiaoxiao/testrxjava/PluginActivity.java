package com.xiaoxiao.testrxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.caixiaoxiao.plugin.host.ProxyActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件名: PluginActivity
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019/4/23
 * https://blog.csdn.net/singwhatiwanna/article/details/22597587
 * http://www.cnblogs.com/lee0oo0/p/3665066.html
 */
public class PluginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
        findViewById(R.id.plugin_test_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlguinActivity();
            }
        });
    }

    private void startPlguinActivity(){
        String dexPath = "/sdcard/plugintest/plugintest.apk";
        Intent intent = new Intent();
        intent.setClass(this,ProxyActivity.class);
        intent.putExtra(ProxyActivity.EXTRA_DEX_PATH,dexPath);
        intent.putExtra(ProxyActivity.EXTRA_CLASS,"com.caixiaoxiao.plugin.test.MainActivity");
        startActivity(intent);
    }
}
