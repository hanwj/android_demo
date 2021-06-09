package com.xiaoxiao.testrxjava;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

/**
 * Created by caixiaoxiao on 5/9/16.
 */
public class CameraPreviewActivity extends AppCompatActivity{
    private Camera mCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initCamera(){
        mCamera = Camera.open();
//        mCamera.setPreviewDisplay();
//        getLifecycle()
//        VideoView

    }
}
