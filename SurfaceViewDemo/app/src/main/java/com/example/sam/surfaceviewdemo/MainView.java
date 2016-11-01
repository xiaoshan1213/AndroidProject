package com.example.sam.surfaceviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CancellationException;

/**
 * Created by sam on 2016/2/14.
 */
public class MainView extends SurfaceView implements SurfaceHolder.Callback{

    private Container container;
    private Rect rect;
    private Circle circle;

    public MainView(Context context) {
        super(context);
        container=new Container();
        rect=new Rect();
        circle=new Circle();
        rect.addChildrenView(circle);
        container.addChildrenView(rect);
        getHolder().addCallback(this);
    }

    public void draw(){
        Canvas canvas=getHolder().lockCanvas();
        canvas.drawColor(Color.WHITE);
        container.draw(canvas);
        getHolder().unlockCanvasAndPost(canvas);
    }

    private Timer timer=null;
    private TimerTask timerTask=null;

    public void startTimer(){
        timer=new Timer();
        timerTask=new TimerTask() {
            @Override
            public void run() {
                draw();
            }
        };
        timer.schedule(timerTask,100,100);
    }

    public void stopTimer(){
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startTimer();
        //draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopTimer();
    }
}
