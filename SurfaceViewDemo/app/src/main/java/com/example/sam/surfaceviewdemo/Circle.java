package com.example.sam.surfaceviewdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by sam on 2016/2/14.
 */
public class Circle extends Container {

    private Paint paint=null;

    public Circle(){
        paint=new Paint();
        paint.setColor(Color.BLUE);
    }

    @Override
    public void childrenView(Canvas canvas) {
        super.childrenView(canvas);
        canvas.drawCircle(50,50,50,paint);
    }
}
