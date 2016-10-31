package com.example.sam.anotalk.net;

import android.os.AsyncTask;

import com.example.sam.anotalk.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sam on 2016/2/23.
 */
public class NetConnection {

    public NetConnection(final String url,final HttpMethod httpMethod,final SuccessCallback successCallback,final FailCallback failCallback, final String ... kvs){

        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... params) {

                StringBuffer paramsStr=new StringBuffer();
                for(int i=0;i<kvs.length;i+=2){
                    paramsStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");  //key=value&
                }
                try {
                    URLConnection uc;
                    switch (httpMethod){
                        case POST:
                            uc=new URL(url).openConnection();
                            uc.setDoOutput(true);
                            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), Config.CHARSET));
                            bufferedWriter.write(paramsStr.toString());
                            bufferedWriter.flush();
                            break;
                        default:
                            uc=new URL(url+"?"+paramsStr.toString()).openConnection();
                            break;
                    }

                    System.out.println("request url:"+uc.getURL());
                    System.out.println("request data:"+paramsStr);

                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(uc.getInputStream(),Config.CHARSET));
                    String line=null;
                    StringBuffer result=new StringBuffer();
                    while((line=bufferedReader.readLine())!=null){
                        result.append(line);
                    }
                    System.out.println("result:"+result);
                    return result.toString();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                if(s!=null){
                    if(successCallback!=null){
                        successCallback.onSuccess(s);
                    }
                }else {
                    if (failCallback!= null) {
                        failCallback.onFail();
                    }
                }
                super.onPostExecute(s);
            }
        }.execute();
    }

    public static interface SuccessCallback{
        void onSuccess(String result);
    };

    public static interface FailCallback{
        void onFail();
    }

}
