package com.xiaoxiao.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by caixiaoxiao on 4/8/16.
 */
public class PagerSlidingTabStrip extends HorizontalScrollView{
    private ViewPager pager;
    private int tabCount;
    private LinearLayout tabContainer;
    private PagerListener pageListener = new PagerListener();
    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        tabContainer = new LinearLayout(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        tabContainer.setLayoutParams(layoutParams);
        tabContainer.setOrientation(LinearLayout.HORIZONTAL);
        addView(tabContainer);
    }

    public void setViewPager(ViewPager pager){
        this.pager = pager;
        this.pager.addOnPageChangeListener(pageListener);
        notifyPagerChanged();
    }

    public void notifyPagerChanged(){
        tabCount = pager.getAdapter().getCount();
        tabContainer.removeAllViews();
        for (int i = 0;i < tabCount;i++){
            addTextTab(pager.getAdapter().getPageTitle(i));
        }
    }

    public void addTextTab(CharSequence title){
        TextView text = new TextView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
        text.setLayoutParams(lp);
        text.setGravity(Gravity.CENTER);
        text.setText(title);
        text.setPadding(10,0,10,0);
        tabContainer.addView(text);
    }

    class PagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
