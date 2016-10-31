package com.example.sam.anotalk.net;

import com.example.sam.anotalk.Config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sam on 2016/2/24.
 */
public class SMSCode {

    public SMSCode(String phone, final SuccessCallback successCallback,final FailCallback failCallback){

        new NetConnection(Config.SERVER_URL,HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObj=new JSONObject(result);
                    switch (jsonObj.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if(successCallback!=null){
                                successCallback.onSuccess();
                            }
                            break;
                        default:
                            if(failCallback!=null){
                                failCallback.onFail();
                            }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (failCallback!=null){
                        failCallback.onFail();
                    }
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                if (failCallback!=null){
                    failCallback.onFail();
                }
            }
        },Config.KEY_ACTION,Config.ACTION_SMS_CODE,Config.KEY_PHONE_NUM,phone);
    }

    public static interface SuccessCallback{
        void onSuccess();
    }

    public static interface FailCallback{
        void onFail();
    }
}
