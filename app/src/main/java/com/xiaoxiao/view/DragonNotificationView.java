package com.xiaoxiao.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoxiao.testrxjava.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caixiaoxiao on 31/12/17.
 */

public class DragonNotificationView<T>{
    public static final int TYPE_SINGLE = 1;
    public static final int TYPE_MULTI = 2;

    private Context mCtx;
    private LayoutInflater mInflater;
    private HorizontalScrollView mRootView;
    private FrameLayout mContainerView;

    private List<T> mList;
    private int type = TYPE_SINGLE;
    private int count = 0;
    public DragonNotificationView(Context context){
        mCtx = context;
        mInflater = LayoutInflater.from(context);
        mList = new ArrayList<>();
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mRootView = new HorizontalScrollView(context);
        mRootView.setLayoutParams(lp);
        mContainerView = new FrameLayout(context);
        lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContainerView.setLayoutParams(lp);
        mRootView.addView(mContainerView);
    }

    public void addNotification(T item){
        if (item != null){
            mList.add(item);
        }
        showNotification();
    }
    private void showNotification(){
        if (TYPE_SINGLE == type && count >= 1){
            return;
        }
        if (mList.size() == 0){
            return;
        }
        ++count;
        T item = mList.remove(0);
        final View itemView = createNotificationView(item);
        mContainerView.addView(itemView);

        itemView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int width = itemView.getMeasuredWidth();
        int screenWidth = mCtx.getResources().getDisplayMetrics().widthPixels;
        ObjectAnimator enterAnim = ObjectAnimator.ofFloat(itemView,"translationX",screenWidth,-width);
        enterAnim.setInterpolator(new AccelerateInterpolator());
        enterAnim.setDuration(10000);
//        ObjectAnimator remainAnim = ObjectAnimator.ofFloat(view,"translationX",)
        enterAnim.start();
        enterAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mContainerView.removeView(itemView);
                --count;
                showNotification();
            }
        });

    }
    /**
     * 生成一个神龙通知view
     * @return
     */
    private View createNotificationView(T item){
        View view = mInflater.inflate(R.layout.view_dragon_notification,mRootView,false);
        TextView contextView = (TextView) view.findViewById(R.id.dragon_content);
        contextView.setText("蔡潇潇为蔡潇潇召唤了1条神龙" + count);
        contextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx,"第" + count + "条",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void setType(int type){
        this.type = type;
    }

    public View getView(){
        return mRootView;
    }
}
