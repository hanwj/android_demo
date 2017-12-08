package com.xiaoxiao.testrxjava.testkeyboard;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoxiao.testrxjava.R;
import com.xiaoxiao.utils.LogUtils;
import com.xiaoxiao.utils.Util;

/**
 * Created by caixiaoxiao on 11/7/16.
 */
public class TestKeyBoardActivity extends FragmentActivity{

    private View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_keyboard);
        rootView = findViewById(R.id.root_view);

        findViewById(R.id.chat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputDialogFragment dialog = new InputDialogFragment();
                dialog.show(getSupportFragmentManager(),"chat");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewGroup.LayoutParams lp = rootView.getLayoutParams();
//        lp.height = Util.getScreenHeight();
//        lp.height = Util.dp2px(1920);
        lp.height = Util.getScreenHeight() - Util.getStatusBarHeight();
        LogUtils.e("TestKeyBoardActivity",Util.getScreenHeight() + "");
        rootView.setLayoutParams(lp);
    }
}
