package com.example.sam.anotalk.net;

import com.example.sam.anotalk.Config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sam on 2016/3/4.
 */
public class Publish {

    public Publish(String phone_md5,String token,String msg,final SuccessCallback successCallback,final FailCallback failCallback){

        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    switch (jsonObject.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if(successCallback!=null){
                                successCallback.onSuccess();
                            }
                            break;
                        case Config.RESULT_STATUS_INVALID_TOKEN:
                            if(failCallback!=null){
                                failCallback.onFail(Config.RESULT_STATUS_INVALID_TOKEN);
                            }
                            break;
                        default:
                            if(failCallback!=null){
                                failCallback.onFail(Config.RESULT_STATUS_FAIL);
                            }
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if(failCallback!=null){
                        failCallback.onFail(Config.RESULT_STATUS_FAIL);
                    }
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                if(failCallback!=null){
                    failCallback.onFail(Config.RESULT_STATUS_FAIL);
                }
            }
        },Config.KEY_ACTION,Config.ACTION_PUBLISH,
                Config.KEY_PHONE_MD5,phone_md5,
                Config.KEY_TOKEN,token,
                Config.KEY_MESSAGE,msg);
    }

    public interface SuccessCallback{
        void onSuccess();
    }

    public interface FailCallback{
        void onFail(int errorCode);
    }
}
