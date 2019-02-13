package com.xiaoxiao.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xiaoxiao.utils.LogUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件名: LargestImageView
 * 描述：加载超大高清图片
 * 修改人: caixiaoxiao
 * 日期: 2019/2/12
 */
public class LargestImageView extends View{
    private int imgWidth;
    private int imgHeight;
    private Rect curRect = new Rect();

    private BitmapRegionDecoder regionDecoder;
    private BitmapFactory.Options options = new BitmapFactory.Options();

    private MotionEvent preMotionEvent;

    public LargestImageView(Context context) {
        super(context);
    }

    public LargestImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LargestImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setInputStream(InputStream inputStream){

        try {

            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream,null,opt);
            imgWidth = opt.outWidth;
            imgHeight = opt.outHeight;

            regionDecoder = BitmapRegionDecoder.newInstance(inputStream,false);

            requestLayout();
            invalidate();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        curRect.left = (imgWidth - width) / 2;
        curRect.right = curRect.left + width;
        curRect.top = (imgHeight - height) / 2;
        curRect.bottom = curRect.top + height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (regionDecoder != null){
            canvas.drawBitmap(regionDecoder.decodeRegion(curRect,options),0,0,null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                preMotionEvent = MotionEvent.obtain(event);
                break;
            case MotionEvent.ACTION_MOVE:
                handleDisplayRect(event);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                break;
        }
        return true;
    }

    private void handleDisplayRect(MotionEvent event){
        LogUtils.e("LargestImageView",preMotionEvent.toString());
        LogUtils.e("LargestImageView",event.toString());
        PointF prePoint = caculateFocalPointer(preMotionEvent);
        PointF curPoint = caculateFocalPointer(event);

        float moveX = curPoint.x - prePoint.x;
        float moveY = curPoint.y - prePoint.y;

        curRect.offset((int)-moveX,(int)-moveY);
        checkRect();

        invalidate();
        preMotionEvent = MotionEvent.obtain(event);
    }

    private PointF caculateFocalPointer(MotionEvent event){
        int count = event.getPointerCount();
        LogUtils.e("LargestImageView","point's count:" + count);
        float totalX = 0;
        float totalY = 0;
        for (int i = 0; i < count ;i++){
            totalX += event.getX(i);
            totalY += event.getY(i);
        }
        return new PointF(totalX/count,totalY/count);
    }

    private void checkRect(){
        //检查width
        if (imgWidth < getWidth()){
            if (curRect.left > 0){
                curRect.left = 0;
                curRect.right = getWidth();
            }
            if (curRect.right < imgWidth){
                curRect.left = imgWidth - getWidth();
                curRect.right = imgWidth;
            }
        }else {
            if (curRect.left < 0){
                curRect.left = 0;
                curRect.right = getWidth();
            }

            if (curRect.right > imgWidth){
                curRect.left = imgWidth - getWidth();
                curRect.right = imgWidth;
            }
        }

        //检查height
        if (imgHeight < getHeight()){
            if (curRect.top > 0){
                curRect.top = 0;
                curRect.bottom = getHeight();
            }
            if (curRect.bottom < imgHeight){
                curRect.top = imgHeight - getHeight();
                curRect.bottom = imgHeight;
            }
        }else {
            if (curRect.top < 0){
                curRect.top = 0;
                curRect.bottom = getHeight();
            }

            if (curRect.bottom > imgHeight){
                curRect.top = imgHeight - getHeight();
                curRect.bottom = imgHeight;
            }
        }
    }
}
