package com.xiaoxiao.testrxjava;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

//import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by caixiaoxiao on 7/9/16.
 */
public class TestMixActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mix);
        final ImageView testObj = (ImageView) findViewById(R.id.test_obj);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.rotation){
//                    testObj.setRotation(180);
                    testObj.setScaleX(-1);
                }else if (id == R.id.rotationx){
                    testObj.setRotationX(45);
                }else if (id == R.id.rotationy){
                    testObj.setRotationY(45);
                }else if (id == R.id.reset){
                    testObj.setRotation(0);
                    testObj.setRotationX(0);
                    testObj.setRotationY(0);
                }
            }
        };
        findViewById(R.id.rotation).setOnClickListener(listener);
        findViewById(R.id.rotationx).setOnClickListener(listener);
        findViewById(R.id.rotationy).setOnClickListener(listener);
        findViewById(R.id.reset).setOnClickListener(listener);
    }
}
