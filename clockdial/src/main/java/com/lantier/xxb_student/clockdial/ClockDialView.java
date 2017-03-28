package com.lantier.xxb_student.clockdial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xxb_student on 2017/3/28.
 */

public class ClockDialView extends View {

    //画外圆
    Paint paintCircle = new Paint();
    //画刻度
    Paint painDegree = new Paint();
    //画指针
    Paint paintHour = new Paint();
    Paint paintMinute = new Paint();
    private int width = 0;
    private int height = 0;
    public ClockDialView(Context context) {
        super(context);
    }

    public ClockDialView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    private void initView() {
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5);
        paintHour.setStrokeWidth(20);
        paintMinute.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(width/2,height/2,width/2,paintCircle);
        painDegree.setStrokeWidth(3);
        for (int i = 0; i < 24 ; i++) {
            //区分整点与非整点
            if (i == 0 || i == 6 || i == 12 || i == 18){

                painDegree.setStrokeWidth(5);
                painDegree.setTextSize(30);
                canvas.drawLine(width/2,height/2-width/2,width/2,height/2-width/2+60,painDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,width/2 -painDegree.measureText(degree)/2,height/2-width/2+90,painDegree);
            } else {
                painDegree.setStrokeWidth(3);
                painDegree.setTextSize(15);
                canvas.drawLine(width/2,height/2-width/2,width/2,height/2-width/2+30,painDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,width/2 -painDegree.measureText(degree)/2,height/2-width/2+60,painDegree);
            }
            canvas.rotate(15 , width/2 ,height/2);
        }
        canvas.save();
        canvas.translate(width/2,height/2);
        canvas.drawLine(0,0,100,100,paintHour);
        canvas.drawLine(0,0,100,200,paintMinute);
        canvas.restore();
    }
}
