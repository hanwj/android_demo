package com.xiaoxiao.testrxjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xiaoxiao.view.CanvasView;

import java.util.ArrayList;

/**
 * Created by caixiaoxiao on 3/8/16.
 */
public class CanvasViewActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View v;
//        v = new CanvasView(this);
//        v = new CustomView(this);
//        setContentView(v);

        setContentView(R.layout.activity_canvas);
    }

    class CustomView extends View{

        private Paint mPaint;
        private ArrayList<PointF> mGraphics = new ArrayList<>();
        public CustomView(Context context) {
            super(context);
            init();
        }

        public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        public CustomView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public void init(){
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.BLUE);
            mPaint.setStrokeWidth(10);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            mGraphics.add(new PointF(event.getX(),event.getY()));
            invalidate();
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            for (PointF p : mGraphics){
                canvas.drawPoint(p.x,p.y,mPaint);
            }
//            Path path = new Path();
//            path.moveTo(50,100);
//            path.lineTo(100, 150);
//            path.lineTo(75,300);
//            canvas.drawPath(path,mPaint);
        }
    }
}
