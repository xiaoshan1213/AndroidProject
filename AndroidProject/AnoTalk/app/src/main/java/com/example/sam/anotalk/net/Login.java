package com.example.sam.anotalk.net;

import com.example.sam.anotalk.Config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sam on 2016/2/25.
 */
public class Login {

    public Login(String phone_md5,String code,final SuccessCallback successCallback,final FailCallback failCallback){
        new NetConnection(Config.SERVER_URL,HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObj=new JSONObject(result);
                    switch (jsonObj.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if(successCallback!=null){
                                successCallback.onSuccess(jsonObj.getString(Config.KEY_TOKEN));
                            }
                            break;
                        default:
                            if(failCallback!=null){
                                failCallback.onFail();
                            }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                if(failCallback!=null){
                    failCallback.onFail();
                }
            }
        },Config.KEY_ACTION,Config.ACTION_LOGIN,Config.KEY_PHONE_MD5,phone_md5,Config.KEY_CODE,code);
    }

    public static interface SuccessCallback{
        void onSuccess(String token);
    }

    public static interface FailCallback{
        void onFail();
    }
}
