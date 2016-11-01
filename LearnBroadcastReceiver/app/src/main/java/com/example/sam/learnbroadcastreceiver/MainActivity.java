package com.example.sam.learnbroadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyReceiver myReceiver=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSendMsg).setOnClickListener(this);
        findViewById(R.id.btnReg).setOnClickListener(this);
        findViewById(R.id.btnUnreg).setOnClickListener(this);
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
            case R.id.btnSendMsg:
                //Intent i=new Intent(this,MyReceiver.class);
                Intent i=new Intent(MyReceiver.ACTION);
                i.putExtra("data","helloworld");
                //sendBroadcast(i);
                sendOrderedBroadcast(i,null);
                break;
            case R.id.btnReg:
                if(myReceiver==null){
                    myReceiver=new MyReceiver();
                    registerReceiver(myReceiver,new IntentFilter(MyReceiver.ACTION));
                }
                break;
            case R.id.btnUnreg:
                if(myReceiver!=null){
                    unregisterReceiver(myReceiver);
                    myReceiver=null;
                }
                break;
        }
    }
}
