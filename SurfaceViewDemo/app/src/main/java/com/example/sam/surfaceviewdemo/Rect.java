package com.example.sam.surfaceviewdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by sam on 2016/2/14.
 */
public class Rect extends Container {

    private Paint paint=null;

    public Rect(){
        paint=new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    public void childrenView(Canvas canvas) {
        super.childrenView(canvas);
        canvas.drawRect(0,0,100,100,paint);
        this.setX(getX()+1);
        this.setY(getY()+1);
    }
}
