package com.lantier.xxb_student.surfaceviewtemplate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by xxb_student on 2017/3/28.
 */
//继承自SurfaceView，并实现两个接口Callback，Runnable
public class SufaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private SurfaceHolder mHodler;
    private Canvas mCanvas;
    private boolean mIsDrawing;
    private Paint mPaint = new Paint();
    private Path mPath = new Path();
    private int x;
    private int y;
    public SufaceViewTemplate(Context context) {
        super(context);
        init();
    }

    public SufaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mHodler = getHolder();
        mHodler.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }
    //开启子线程进行绘制
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing =false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x  = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (mIsDrawing){
            draw();
//            x+=1;
//            y= (int) (100*Math.sin(x*2*Math.PI/180)+400);
//            mPath.lineTo(x,y);
        }
        long end = System.currentTimeMillis();
        if (end - start <100){
            try {
                Thread.sleep(100-(end - start));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void draw(){
        try {
            //lockCanvas获得canvas对象进行绘制
            mCanvas = mHodler.lockCanvas();
            //surface背景
            mCanvas.drawColor(Color.WHITE);
//            mCanvas.drawLine(0,0,200,200,mPaint);
            mCanvas.drawPath(mPath,mPaint);
        }catch (Exception e){

        }finally {
            if (mCanvas != null){
                //对画布内容进行提交
                mHodler.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
