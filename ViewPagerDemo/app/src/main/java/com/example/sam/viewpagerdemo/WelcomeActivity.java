package com.example.sam.viewpagerdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by sam on 2016/1/29.
 */
public class WelcomeActivity extends Activity{

    private static final int TIME=2000;
    private static final int GO_HOME=1000;
    private static final int GO_GUIDE=1001;
    private boolean isFirstIn=false;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GO_GUIDE:

                    break;
                case GO_HOME:

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }

    private void init(){

    }

    private void goHome(){
        Intent i=new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }

    private void setGoGuide(){
        Intent i=new Intent(WelcomeActivity.this,Guide.class);
        startActivity(i);
        finish();
    }

}
