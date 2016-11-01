package com.example.sam.learnservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import javax.security.auth.callback.Callback;

public class MyService extends Service {
    private boolean serviceRunning=false;
    private String data="default";
    private Callback callback=null;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public Callback getCallback() {
        return callback;
    }

    public static interface Callback{
        void onDataChange(String data);
    }

    public class Binder extends android.os.Binder{
        public void setData(String data){
            MyService.this.data=data;
        }
        public MyService getService(){
            return MyService.this;
        }

    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new Binder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        System.out.println("onStartCommand");
        data=intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        serviceRunning=true;
        new Thread(){
            public void run(){
                int i=0;
                String str;
                super.run();
                while(serviceRunning){
                    i++;
                    str=i+":"+data;
                    System.out.println(str);
                    if(callback!=null){
                        callback.onDataChange(str);
                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        System.out.println("service create");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        serviceRunning=false;
        System.out.println("service destroy");
    }
}
