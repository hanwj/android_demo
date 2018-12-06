package com.xiaoxiao.testrxjava;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoxiao.utils.LogUtils;
import com.xiaoxiao.utils.Util;
import com.xiaoxiao.view.TestImageView;
import com.xiaoxiao.view.TestView;

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

        View testView = findViewById(R.id.test_img2);
        testView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("FrameLayoutActivity","testView");
            }
        });

        View testImg = findViewById(R.id.test_img);
//        testImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LogUtils.e("FrameLayoutActivity","testImageView");
//            }
//        });


        ListView listView = (ListView) findViewById(R.id.test_list);
        listView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View itemView = new TestView(FrameLayoutActivity.this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.dp2px(50));
            itemView.setLayoutParams(lp);
            itemView.setBackgroundColor(Color.RED);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FrameLayoutActivity.this,"条目" + position,Toast.LENGTH_SHORT);
                    LogUtils.e("ListView","条目:" + position);
                }
            });
            return itemView;
        }
    }
}
