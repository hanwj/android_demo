package com.xiaoxiao.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by caixiaoxiao on 2/8/16.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder mHolder;
    private MyThread mThread;
    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        mHolder = getHolder();
        mHolder.addCallback(this);
        mThread = new MyThread(mHolder);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread.isRun = true;
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread.isRun = false;
    }

    class MyThread extends Thread{
        private SurfaceHolder holder;
        private boolean isRun = false;
        Paint p;
        public MyThread(SurfaceHolder holder){
            this.holder = holder;
            isRun = true;
            p = new Paint();
            p.setTextSize(20);
            p.setColor(Color.WHITE);

        }

        @Override
        public void run() {
            int count = 0;
            long lastTime = -1;
            Canvas c;
//            c = holder.lockCanvas();   //锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas,在其上面画图等操作
//            c.drawColor(Color.BLACK);  //设置画布背景色
//            c.drawRect(new Rect(100, 50, 300, 250), p);
//            c.drawText("这是第" + count + "秒",100,310,p);
//            holder.unlockCanvasAndPost(c);

            while(isRun){
                synchronized (holder){
                    long curTime = System.currentTimeMillis();
                    if (curTime - lastTime >= 1000){
                        c = holder.lockCanvas();//锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas,在其上面画图等操作
                        c.drawColor(Color.BLACK);  //设置画布背景色
                        c.drawRect(new Rect(100, 50, 300, 250), p);
                        c.drawText("这是第" + (count++) + "秒", 100, 310, p);
                        lastTime = curTime;
                        holder.unlockCanvasAndPost(c);
                    }
                }

            }
        }

    }
}
