package com.xiaoxiao.testrxjava;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by meibo-design on 17/5/16.
 */
public class TestActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        final TextView txtView = (TextView)findViewById(R.id.test_textview);
        final View scrollView = findViewById(R.id.test_scroll_text);
        Button scaleBtn = (Button)findViewById(R.id.scale_btn);
        Button translateBtn = (Button)findViewById(R.id.translate_btn);
        Button scroll1 = (Button)findViewById(R.id.scroll1);
        Button scroll2 = (Button)findViewById(R.id.scroll2);
        Button scroll3 = (Button)findViewById(R.id.scroll3);
        final Animation scaleAnim = AnimationUtils.loadAnimation(this,R.anim.anim_scale);
        final Animation moveAnim = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.scale_btn){
                    txtView.startAnimation(scaleAnim);
                }else if(id == R.id.translate_btn){
                    txtView.startAnimation(moveAnim);
                }else if (id == R.id.scroll1){
                    scrollView.scrollTo(100,0);
                }else if (id == R.id.scroll2){
                    scrollView.scrollTo(0,100);
                }else if (id == R.id.scroll3){
                    scrollView.setScrollX(0);
                    scrollView.setScrollY(0);
                }
            }
        };
        scaleBtn.setOnClickListener(listener);
        translateBtn.setOnClickListener(listener);
        scroll1.setOnClickListener(listener);
        scroll2.setOnClickListener(listener);
        scroll3.setOnClickListener(listener);
    }

}
