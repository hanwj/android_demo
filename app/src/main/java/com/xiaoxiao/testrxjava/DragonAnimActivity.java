package com.xiaoxiao.testrxjava;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.xiaoxiao.view.dragon.DragonLwfView;
import com.xiaoxiao.view.dragon.DragonNotificationView;
import com.xiaoxiao.view.dragon.NotificationBean;

/**
 * Created by caixiaoxiao on 31/12/17.
 */

public class DragonAnimActivity extends FragmentActivity {
    private DragonNotificationView dragonView;
    private DragonLwfView dragonLwfView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup rootView = new FrameLayout(this);
        setContentView(rootView);

        Button addBtn = new Button(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-1,-2);
        lp.setMargins(30,200,30,0);
        addBtn.setLayoutParams(lp);
        addBtn.setText("添加");
        rootView.addView(addBtn);

        dragonView = new DragonNotificationView(this);
//        rootView.addView(dragonView.getView());
        rootView.addView(dragonView);

        dragonLwfView = new DragonLwfView(this);
        rootView.addView(dragonLwfView);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dragonView.addNotification("ddd");
//                dragonLwfView.addNotification("ddd");
                NotificationBean bean = new NotificationBean();
                dragonView.addNotification(bean);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
//            dragonView.setType(DragonNotificationView.TYPE_MULTI);
//        }else {
//            dragonView.setType(DragonNotificationView.TYPE_SINGLE);
//        }
    }
}
