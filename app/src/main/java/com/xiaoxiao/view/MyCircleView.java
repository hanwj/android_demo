package com.xiaoxiao.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by caixiaoxiao on 2/8/16.
 */
public class MyCircleView extends View{
    private static String TAG = "MyCircleView";
    private int radius = 10;
    private int color = 0xff00000;
    public MyCircleView(Context context) {
        super(context);
    }

    public MyCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (MeasureSpec.AT_MOST == heightSpecMode){
            Log.e(TAG,"specMode:" + "AT_MOST(" + heightSpecMode + "),size:" + height);
        }else if (MeasureSpec.EXACTLY == heightSpecMode){
            Log.e(TAG,"specMode:" + "EXACTLY(" + heightSpecMode + "),size:" + height);
        }else if (MeasureSpec.UNSPECIFIED == heightSpecMode){
            Log.e(TAG,"specMode:" + "UNSPECIFIED(" + heightSpecMode + "),size:" + height);
        }

        int widthSize = radius * 2 + getPaddingLeft() + getPaddingRight();
        int heightSize = radius * 2 + getPaddingTop() + getPaddingBottom();
        Log.e(TAG,"heightsize:" + heightSize);

        widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize,MeasureSpec.getMode(widthMeasureSpec));
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize,MeasureSpec.getMode(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    public void setColor(int color){
        this.color = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(color);
        p.setAntiAlias(true);
        canvas.drawCircle(radius + getPaddingLeft(),radius + getPaddingTop(),radius,p);
    }
}
