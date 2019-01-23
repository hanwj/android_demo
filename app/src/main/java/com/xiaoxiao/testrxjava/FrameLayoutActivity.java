package com.xiaoxiao.testrxjava;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaoxiao.utils.LogUtils;
import com.xiaoxiao.utils.Util;
import com.xiaoxiao.view.TestImageView;
import com.xiaoxiao.view.TestView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caixiaoxiao on 5/8/16.
 */
public class FrameLayoutActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);

        ImageView frameImg = (ImageView) findViewById(R.id.liao_frame);
        AnimationDrawable animDrawable = (AnimationDrawable) frameImg.getDrawable();
        animDrawable.start();

        ImageView frameImg2 = (ImageView) findViewById(R.id.liao_frame2);
        AnimationDrawable animDrawable2 = (AnimationDrawable) frameImg2.getDrawable();
        animDrawable2.start();

        SimpleDraweeView netImg = (SimpleDraweeView) findViewById(R.id.fresco_img);
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(7);
//        RoundingParams roundingParams = RoundingParams.asCircle();
        roundingParams.setOverlayColor(Color.WHITE);
        netImg.setHierarchy(new GenericDraweeHierarchyBuilder(getResources()).setRoundingParams(roundingParams).build());
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
//                .setUri(Uri.parse("http://sr3.pplive.cn//cms//26//64//79272b5317bd2db955b2e281d699d2cd.gif"))
                .setUri(Uri.parse("http://sr1.pplive.cn//cms//12//69//0b3c7ad0f65bf9aab527c655e658d8eb.jpg"))
                .setAutoPlayAnimations(true).build();
        netImg.setController(draweeController);
//        netImg.setImageURI(Uri.parse("http://sr3.pplive.cn//cms//26//64//79272b5317bd2db955b2e281d699d2cd.gif"));


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

        final ListView listView = (ListView) findViewById(R.id.test_list);
        final MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                LogUtils.e("FrameLayoutActivity","list's onScrollStateChanged");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                LogUtils.e("FrameLayoutActivity","list's onScroll");
            }
        });

        Button preBtn = (Button) findViewById(R.id.testBtn2);
        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int firstPos = listView.getFirstVisiblePosition();
                View firstChild = listView.getChildAt(0);
                final int firstTop = firstChild != null ? firstChild.getTop() : 0;
                adapter.pre();
//                listView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        listView.scrollBy(0,Util.dp2px(50));
//                        listView.setSelectionFromTop(firstPos + 1,firstTop);
//                    }
//                },50);
                listView.setSelectionFromTop(firstPos + 1,firstTop);
            }
        });

        Button nextBtn = (Button) findViewById(R.id.testBtn3);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add();
            }
        });

    }

    class MyAdapter extends BaseAdapter{

        private List<String> list;
        private int nextNum = 0;
        private int preNum = 0;
        public MyAdapter() {
            list = new ArrayList<>();
            for (int i = 0; i < 10; i++){
                list.add("正常" + i);
            }
        }

        @Override
        public int getCount() {
            return list.size();
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
            TextView itemView = new TextView(FrameLayoutActivity.this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.dp2px(50));
            itemView.setLayoutParams(lp);
            itemView.setBackgroundColor(Color.WHITE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FrameLayoutActivity.this,"条目" + position,Toast.LENGTH_SHORT);
                    LogUtils.e("ListView","条目:" + position);
                }
            });
            itemView.setText(list.get(position));
            itemView.setTextSize(20);
            itemView.setGravity(Gravity.CENTER);
            return itemView;
        }

        public void add() {
            nextNum++;
            list.add("追加" + nextNum);
            notifyDataSetChanged();
        }

        public void pre() {
            preNum++;
            list.add(0,"前插" + preNum);
            notifyDataSetChanged();
        }
    }
}
