package com.example.sam.countdowntime;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputTime;
    private TextView tv;
    private int temp=0;
    private Timer timer=null;
    private TimerTask timerTask=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParams();
    }
    public void initParams(){
        inputTime= (EditText) findViewById(R.id.inputTime);
        tv= (TextView) findViewById(R.id.tv);
        findViewById(R.id.btnGetTime).setOnClickListener(this);
        findViewById(R.id.btnStart).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGetTime:
                tv.setText(inputTime.getText().toString());
                temp=Integer.parseInt(inputTime.getText().toString());
                break;
            case R.id.btnStart:
                startCount();
                break;
            case R.id.btnStop:
                stopCount();
                break;
        }
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            tv.setText(msg.arg1 + "");
            if(msg.arg1==0){
                stopCount();
            }else{
                startCount();
            }
        }
    };

    public void startCount(){
        timer=new Timer();
        timerTask=new TimerTask() {
            @Override
            public void run() {
                temp--;
                Message msg=handler.obtainMessage();
                msg.arg1=temp;
                handler.sendMessage(msg);
            }
        };
        timer.schedule(timerTask,1000);
    }

    public void stopCount(){
        timer.cancel();
    }
}
