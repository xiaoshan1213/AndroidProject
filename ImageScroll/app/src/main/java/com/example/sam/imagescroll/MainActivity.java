package com.example.sam.imagescroll;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv1,iv2;

    private ScaleAnimation scaleAnimation0=new ScaleAnimation(1,0,1,1,Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
    private ScaleAnimation scaleAnimation1=new ScaleAnimation(0,1,1,1,Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iv1.getVisibility()==View.VISIBLE){
                    iv1.startAnimation(scaleAnimation0);
                }else{
                    iv2.startAnimation(scaleAnimation0);
                }
            }
        });
    }

    private void showImage1(){
        iv1.setVisibility(View.VISIBLE);
        iv2.setVisibility(View.INVISIBLE);
    }
    private void showImage2(){
        iv1.setVisibility(View.INVISIBLE);
        iv2.setVisibility(View.VISIBLE);
    }

    private void initView(){
        iv1= (ImageView) findViewById(R.id.iv1);
        iv2= (ImageView) findViewById(R.id.iv2);
        showImage1();
        scaleAnimation0.setDuration(500);
        scaleAnimation1.setDuration(500);

        scaleAnimation0.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(iv1.getVisibility()==View.VISIBLE){
                    iv1.setAnimation(null);
                    showImage2();
                    iv2.startAnimation(scaleAnimation1);
                }else {
                    iv2.setAnimation(null);
                    showImage1();
                    iv1.startAnimation(scaleAnimation1);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
