package com.example.sam.learnservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
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

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private Intent intent;
    private EditText editText;
    private TextView tvOut;
    private MyService.Binder binder=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editText= (EditText) findViewById(R.id.etData);
        tvOut= (TextView) findViewById(R.id.tvOut);
        intent=new Intent(MainActivity.this,MyService.class);
        findViewById(R.id.btnStartService).setOnClickListener(this);
        findViewById(R.id.btnStopService).setOnClickListener(this);
        findViewById(R.id.btnBindService).setOnClickListener(this);
        findViewById(R.id.btnUnbindService).setOnClickListener(this);
        findViewById(R.id.btnSyncData).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartService:
                intent.putExtra("data",editText.getText().toString());
                startService(intent);
                break;
            case R.id.btnStopService:
                stopService(intent);
                break;
            case R.id.btnBindService:
                bindService(intent,this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                unbindService(this);
                break;
            case R.id.btnSyncData:
                if(binder!=null){
                    binder.setData(editText.getText().toString());
                }
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder= (MyService.Binder) service;
        binder.getService().setCallback(new MyService.Callback(){

            @Override
            public void onDataChange(String data) {
                Message msg=new Message();
                Bundle b=new Bundle();
                b.putString("data",data);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        });
        System.out.println("service connected!");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    private android.os.Handler handler=new android.os.Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvOut.setText(msg.getData().getString("data"));
        }
    };
}
