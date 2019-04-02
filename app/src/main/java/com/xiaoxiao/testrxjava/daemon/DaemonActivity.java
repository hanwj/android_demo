package com.xiaoxiao.testrxjava.daemon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xiaoxiao.testrxjava.R;

/**
 * 文件名: DaemonActivity
 * 描述：进程保活
 * 修改人: caixiaoxiao
 * 日期: 2019/3/22
 */
public class DaemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daemon);
    }
}
