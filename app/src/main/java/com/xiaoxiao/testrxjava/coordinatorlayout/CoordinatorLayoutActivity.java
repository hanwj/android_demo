package com.xiaoxiao.testrxjava.coordinatorlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.xiaoxiao.testrxjava.R;
import com.xiaoxiao.utils.LogUtils;

/**
 * 文件名: CoordinatorLayoutActivity
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019-09-24
 */
public class CoordinatorLayoutActivity extends AppCompatActivity {
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                LogUtils.e("CoordinatorLayoutActivity",verticalOffset+ "");
            }
        });
        toolbar.showOverflowMenu();
    }
}
