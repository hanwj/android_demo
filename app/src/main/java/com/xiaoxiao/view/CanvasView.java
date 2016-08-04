package com.xiaoxiao.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by caixiaoxiao on 3/8/16.
 */
public class CanvasView extends View{
    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        p.setAntiAlias(true);
        canvas.translate(300, 100);
//        canvas.drawRect(0,0,100,200,p);

//        canvas.save();
//        canvas.rotate(90);
//        canvas.drawRect(0,0,100,200,p);
//        canvas.rotate(0);
//        canvas.drawRect(0,0,50,100,p);
//        canvas.restore();

        int width = 100;
        int height = 200;
        int per = 30;
        int count = 360/per;
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(4);
        canvas.drawRect(-50, 0, height - 50, height, p);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.RED);
        for (int i = 0; i < count; i++){
            canvas.save();
            canvas.rotate(i * per, 50, 100);
            canvas.drawRect(0, 0, width, height, p);
            canvas.restore();
        }


        canvas.translate(-100, 250);
        RectF rect = new RectF(0,0,100,100);
        canvas.drawRoundRect(rect,20,50,p);
    }
}
