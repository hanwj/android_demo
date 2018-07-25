package com.xiaoxiao.testrxjava;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by caixiaoxiao on 5/8/16.
 */
public class FrameLayoutActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);

        ImageView frameImg = (ImageView) findViewById(R.id.liao_frame);
        AnimationDrawable animDrawable = (AnimationDrawable) frameImg.getDrawable();
        animDrawable.start();

        ImageView frameImg2 = (ImageView) findViewById(R.id.liao_frame2);
        AnimationDrawable animDrawable2 = (AnimationDrawable) frameImg2.getDrawable();
        animDrawable2.start();

        final TextView txt = (TextView) findViewById(R.id.test_txt);
        final TextView txt2 = (TextView) findViewById(R.id.test_txt2);
        Button testBtn = (Button) findViewById(R.id.testBtn1);
        testBtn.setOnClickListener(new View.OnClickListener() {
            int translationX = 0;
            @Override
            public void onClick(View v) {
//                txt.setTranslationX(translationX+=20);
                txt2.setTranslationY(translationX-=20);
            }
        });
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FrameLayoutActivity.this,"ddddd",Toast.LENGTH_SHORT).show();
            }
        });

        TranslateAnimation translateAnimation = new TranslateAnimation(0,500,0,-100);
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
        txt.startAnimation(translateAnimation);
    }
}
