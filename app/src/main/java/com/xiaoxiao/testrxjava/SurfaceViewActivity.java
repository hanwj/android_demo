package com.xiaoxiao.testrxjava;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.xiaoxiao.view.MySurfaceView;

/**
 * Created by caixiaoxiao on 2/8/16.
 */
public class SurfaceViewActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_surface);
//        MySurfaceView surfaceView = new MySurfaceView(this);
//        setContentView(surfaceView);
    }
}
