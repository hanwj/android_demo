package com.xiaoxiao.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.xiaoxiao.testrxjava.R;

/**
 * Created by meibo-design on 17/5/16.
 */
public class ConvertView extends View implements View.OnClickListener{

    private Paint mPaint;
    private Rect mRect;
    private int mCount;
    private float mTextSize;
    public ConvertView(Context context) {
        super(context);
    }

    public ConvertView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();
        init(attrs);
        setOnClickListener(this);
    }

    public ConvertView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.ConvertView);
        mTextSize = t.getDimension(R.styleable.ConvertView_textSize,20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(mTextSize);
        String text = mCount + "";
        mPaint.getTextBounds(text,0,text.length(),mRect);
        float textWidth = mRect.width();
        float textHeight = mRect.height();
        canvas.drawText(text,getWidth()/2 - textWidth/2,getHeight()/2 - textHeight/2,mPaint);
    }

    @Override
    public void onClick(View v) {
        mCount ++;
        invalidate();
    }
}
