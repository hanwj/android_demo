package com.xiaoxiao.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

import com.xiaoxiao.testrxjava.R;

/**
 * Created by caixiaoxiao on 9/11/16.
 */
public class SlidingMenu extends HorizontalScrollView{
    private static final String TAG = "SlidingMenu";
    private boolean once = false;
    private int mScreenWidth;
    private int mMenuWidth;
    private boolean isOpen = false;

    private View mMenu;
    private View mContent;


    public SlidingMenu(Context context) {
        super(context);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    private void initView(AttributeSet attrs){
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mScreenWidth = wm.getDefaultDisplay().getWidth();
        setHorizontalScrollBarEnabled(false);
        TypedArray a = getResources().obtainAttributes(attrs, R.styleable.SlidingMenu);
        isOpen = a.getBoolean(R.styleable.SlidingMenu_menuOpen,false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once){
            ViewGroup root = (ViewGroup) getChildAt(0);
            mMenu = root.getChildAt(0);
            mContent = root.getChildAt(1);
            mMenu.measure(0,0);
            mMenuWidth = mMenu.getMeasuredWidth();
            mContent.getLayoutParams().width = mScreenWidth;
            Log.e(TAG,"menu's width:" + mMenuWidth);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!once){
            if (!isOpen){
                scrollTo(mMenuWidth,0);
            }else {
                setAnimByScroll(0);
            }
            once = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_UP){
            if (getScrollX() >= mMenuWidth / 2){
                smoothScrollTo(mMenuWidth, 0);
                isOpen = false;
            }else {
                smoothScrollTo(0, 0);
                isOpen = true;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        setAnimByScroll(l);
    }

    /**
     * 滑动时的动画
     * @param scrollX
     */
    private void setAnimByScroll(int scrollX){
        float factor = scrollX * 1.0f / mMenuWidth;
        mMenu.setScaleX(0.7f + (1 - factor) * 0.3f);
        mMenu.setScaleY(0.7f + (1 - factor) * 0.3f);
        mContent.setScaleX(0.7f + factor * 0.3f);
        mContent.setScaleY(0.7f + factor * 0.3f);
        mMenu.setTranslationX(mMenuWidth * factor * 0.3f / 2);
        mContent.setTranslationX(-(mScreenWidth * (1 - factor) * 0.3f / 2 + mMenuWidth * 0.1f * (1 - factor)));
    }
    /**
     * 打开侧边菜单
     */
    public void openMenu(){
        if (!isOpen){
            smoothScrollTo(0,0);
            isOpen = true;
        }
    }

    /**
     * 关闭侧边菜单
     */
    public void closeMenu(){
        if (isOpen){
            smoothScrollTo(mMenuWidth,0);
            isOpen = false;
        }
    }
}
