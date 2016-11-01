package com.example.sam.touchactiondemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1= (ImageView) findViewById(R.id.iv1);
        findViewById(R.id.root).setOnTouchListener(new View.OnTouchListener() {
            float currentDis;
            float lastDis;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("down");
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("up");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("move");
                        System.out.println(String.format("x:%f,y:%f",event.getX(),event.getY()));
                        if(event.getPointerCount()>=2){
                            float offsetX=event.getX(0)-event.getX(1);
                            float offsetY=event.getY(0)-event.getY(1);
                            currentDis=(float)Math.sqrt(offsetX*offsetX+offsetY*offsetY);
                            if(lastDis<0){
                                lastDis=currentDis;
                            }else{
                                if(currentDis-lastDis>5){
                                    System.out.println("amplify");
                                    FrameLayout.LayoutParams lp= (android.widget.FrameLayout.LayoutParams) iv1.getLayoutParams();
                                    lp.width= (int) (1.1f*iv1.getWidth());//类型要一致
                                    lp.height= (int) (1.1f*iv1.getHeight());
                                    iv1.setLayoutParams(lp);
                                    lastDis=currentDis;
                                }else if(lastDis-currentDis>5){
                                    System.out.println("reduce");
                                    FrameLayout.LayoutParams lp= (android.widget.FrameLayout.LayoutParams) iv1.getLayoutParams();
                                    lp.width= (int) (0.9f*iv1.getWidth());
                                    lp.height= (int) (0.9f*iv1.getHeight());
                                    iv1.setLayoutParams(lp);
                                    lastDis=currentDis;
                                }
                            }
                        }

//                        FrameLayout.LayoutParams lp= (android.widget.FrameLayout.LayoutParams) iv1.getLayoutParams();
//                        lp.leftMargin= (int) event.getX()-60;
//                        lp.topMargin= (int) event.getY()-60;
//                        iv1.setLayoutParams(lp);
                        break;
                }
                return true; //返回true才能持续监听
            }
        });
    }
}
