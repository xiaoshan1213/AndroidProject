package com.example.sam.learnbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    public static final String ACTION="com.example.sam.learnbroadcastreceiver.intent.action.MyReceiver";

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        System.out.println("Msg received!" + intent.getStringExtra("data"));
        abortBroadcast();//中断之后的broadcast
        //System.err.println("错误日志信息");
        //System.out.println("普通日志信息");
    }
}
