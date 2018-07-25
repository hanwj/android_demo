package com.xiaoxiao.view.dragon;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoxiao.testrxjava.R;
import com.xiaoxiao.utils.LogUtils;
import com.xiaoxiao.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caixiaoxiao on 31/12/17.
 * 神龙滚屏通知
 */

public class DragonNotificationView extends FrameLayout {
//    public static final int TYPE_SINGLE = 1; //单个
//    public static final int TYPE_MULTI = 2; //追加
    private final static String TAG = DragonNotificationView.class.getSimpleName();
    private LayoutInflater mInflater;
//    private FrameLayout mContainerView;
    private List<NotificationBean> mList;
    private DragonListener mListener;
    private int num; //动画数量
    private int maxNum; //最大数量
//    private int mWidth;
    private int mSpeed;
    private int mSpeedFactor;
    public DragonNotificationView(Context context) {
        this(context,null);
    }

    public DragonNotificationView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DragonNotificationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        mInflater = LayoutInflater.from(context);
        //高度固定35dp
//        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                getResources().getDimensionPixelSize(R.dimen.dragon_notification_height));
//        setLayoutParams(lp);
//        setBackgroundColor(Color.BLACK);
//        mContainerView = new FrameLayout(context);
//        lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT);
//        mContainerView.setLayoutParams(lp);
//        addView(mContainerView);
//        mContainerView.setBackgroundColor(Color.RED);
        mList = new ArrayList<>();
        num = 0;
        maxNum = 1;
        mSpeed = Util.dp2px(1);
        mSpeedFactor = 15;
//        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        int minWidth = Math.max(displayMetrics.widthPixels,displayMetrics.heightPixels);
//        mContainerView.setMinimumWidth(minWidth);
    }

    public void addNotification(NotificationBean item){
        if (item != null){
            mList.add(item);
        }
        showNotification(true);
    }
    private void showNotification(final boolean repeat){
        if (repeat) {
            int childCount = getChildCount();
            float rightMargin = 0F;
            float space = getResources().getDisplayMetrics().density * 20;
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                if (true) {
                    int itemWidth = (int) child.getTag();
                    float childMargin = child.getX() + itemWidth + space;
                    if (childMargin > rightMargin) {
                        rightMargin = childMargin;
                    }
                } else {
                    return;
                }
            }

//            int maxSumWidth = mContainerView.getWidth();
//            if (mContainerView.getWidth() > getResources().getDisplayMetrics().widthPixels) {
//                maxSumWidth = getResources().getDisplayMetrics().widthPixels;
//            }
            if (mList.size() <= 0 || rightMargin > getWidth()) {
                return;
            }
            LogUtils.e(TAG,"rightMargin:" + rightMargin + ",width:" + getWidth());
        } else {
            if (num >= maxNum || mList.size() <= 0) {
                return;
            }
        }

        ++num;
        NotificationBean item = mList.remove(0);
        LogUtils.e(TAG,"index:" + item.getFrom());
        final View itemView = createNotificationView(item);
        itemView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int width = itemView.getMeasuredWidth();
        itemView.setTag(width);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(width,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(itemView,lp);


//        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        ObjectAnimator enterAnim = ObjectAnimator.ofFloat(itemView,"translationX",getWidth(),-width);
        enterAnim.setInterpolator(new LinearInterpolator());
        int duration = ((getWidth() + width) / mSpeed) * mSpeedFactor;
        LogUtils.e("DragonNotificationView","speed:" + mSpeed + ",duration:" + duration);
        enterAnim.setDuration(duration);
        enterAnim.start();
        enterAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeView(itemView);
                --num;
                showNotification(repeat);
            }
        });

        updateListener.equals(repeat ? 1 : 0);
        enterAnim.addUpdateListener(updateListener);
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
////        return super.onTouchEvent(ev);
//        return false;
//    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LogUtils.e("DragonNotifiationView","w:" + w);
//        mWidth = w;
//        if (mContainerView != null){
//            LogUtils.e("DragonNotifiationView2","w:" + w);
//            mContainerView.setMinimumWidth(w);
//        }
    }

    /**
     * 生成一个神龙通知view
     * @return
     */
    private View createNotificationView(final NotificationBean item){
        View view = mInflater.inflate(R.layout.view_dragon_notification,this,false);
        TextView contextView = (TextView) view.findViewById(R.id.dragon_content);
        SpannableStringBuilder builder = new SpannableStringBuilder("");
        SpannableString span = new SpannableString(item.getFrom());
        span.setSpan(new ForegroundColorSpan(Color.RED),0,span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new StyleSpan(Typeface.BOLD),0,span.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(span);
        builder.append("蔡潇潇蔡潇潇蔡潇潇蔡潇潇蔡潇潇蔡潇潇蔡潇潇蔡潇潇蔡潇潇蔡潇潇蔡潇潇蔡潇潇");
        contextView.setText(builder);
        contextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), item.getFrom(), Toast.LENGTH_SHORT).show();
                if (mListener != null){
                    mListener.onClick();
                }
            }
        });
        return view;
    }

    public void setListener(DragonListener listener) {
        this.mListener = listener;
    }

    ValueAnimator.AnimatorUpdateListener updateListener = new ValueAnimator.AnimatorUpdateListener() {

        long triggerTime = System.currentTimeMillis();

        boolean isRepeat = false;

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Integer){
                this.isRepeat = (int)obj == 1;
            }
            return super.equals(obj);
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            if (System.currentTimeMillis() - 1500 > triggerTime) {
                showNotification(isRepeat);
            }
        }
    };
}
