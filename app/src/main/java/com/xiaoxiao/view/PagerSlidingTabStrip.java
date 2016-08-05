package com.xiaoxiao.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by caixiaoxiao on 4/8/16.
 * 参考：https://github.com/astuetz/PagerSlidingTabStrip 简化版
 */
public class PagerSlidingTabStrip extends HorizontalScrollView{
    private static String TAG = "PagerSlidingTabStrip";
    private PagerListener pageListener = new PagerListener();
    private LinearLayout tabContainer;
    private ViewPager pager;
    private int tabCount;
    private int curItem = 0;
    private float scrollOffset = 0;

    //底部指示线信息
    private int indicatorColor = Color.BLACK;
    private int indicatorHeight = 10;

    //tab的相关信息
    private int tabTextSize = 30;
    private int tabPadding = 10;

    //tab与tab之间的分割线
    private int dividerColor = Color.LTGRAY;
    private int dividerWidth = 2;
    private int dividerPadding = 50;
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
        text.setPadding(tabPadding, 0, tabPadding, 0);
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
        text.setText(title);
        tabContainer.addView(text);
    }

    private void scrollTab(int position,float positionOffset){
        curItem = position;
        scrollOffset = positionOffset;
        View tab = tabContainer.getChildAt(position);
        scrollTo((int)(tab.getX() + tab.getWidth() * positionOffset * 0.5),0);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        if (tabCount > 0){
            Paint indicatorPaint = new Paint();
            indicatorPaint.setAntiAlias(true);
            indicatorPaint.setColor(indicatorColor);
            indicatorPaint.setStrokeWidth(indicatorHeight);
            View curTab = tabContainer.getChildAt(curItem);
            float startX,endX;
            startX = curTab.getX() + curTab.getWidth() * scrollOffset;
            if (curItem == tabCount - 1){
                endX = curTab.getX() + curTab.getWidth();
            }else {
                View nextTab = tabContainer.getChildAt(curItem + 1);
                endX = nextTab.getX() + nextTab.getWidth() * scrollOffset;
            }
            canvas.drawLine(startX, getMeasuredHeight(), endX, getMeasuredHeight(), indicatorPaint);
        }

        for (int i = 0;i < tabCount - 1;i++){
            Paint dividerPaint = new Paint();
            dividerPaint.setAntiAlias(true);
            dividerPaint.setColor(dividerColor);
            dividerPaint.setStrokeWidth(dividerWidth);
            View tab = tabContainer.getChildAt(i + 1);
            canvas.drawLine(tab.getX(),dividerPadding,tab.getX(),getMeasuredHeight() - dividerPadding,dividerPaint);
        }
        canvas.restore();
    }

    class PagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            Log.e(TAG,"position:" + position + ",positionOffset:" + positionOffset + ",positionOffsetPixels:" + positionOffsetPixels);
            scrollTab(position,positionOffset);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
