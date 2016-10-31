package com.example.sam.anotalk.net;

import com.example.sam.anotalk.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 2016/2/26.
 */
public class Timeline {

    public Timeline(String phone_md5,String token,int page,int perpage,final SuccessCallback successCallback,final FailCallback failCallback){

        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    switch (jsonObject.getInt(Config.KEY_STATUS)){
                        case  Config.RESULT_STATUS_SUCCESS:
                            if(successCallback!=null){
                                List<Message> msgs=new ArrayList<Message>();
                                JSONArray msgJsonArr=jsonObject.getJSONArray(Config.KEY_TIMELINE);
                                JSONObject msgObj;
                                for(int i=0;i<msgJsonArr.length();i++){
                                    msgObj=msgJsonArr.getJSONObject(i);
                                    msgs.add(new Message(
                                            msgObj.getString(Config.KEY_MESSAGE_ID),
                                            msgObj.getString(Config.KEY_MESSAGE),
                                            msgObj.getString(Config.KEY_PHONE_MD5)));
                                }
                                successCallback.onSuccess(jsonObject.getInt(Config.KEY_PAGE),jsonObject.getInt(Config.KEY_PERPAGE),msgs);
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
        },Config.KEY_ACTION,Config.ACTION_TIMELINE,
                Config.KEY_PHONE_MD5,phone_md5,
                Config.KEY_TOKEN,token,
                Config.KEY_PAGE,page+"",Config.KEY_PERPAGE,perpage+"");
    }

    public static interface SuccessCallback{

        void onSuccess(int page,int perpage,List<Message> timeline);
    }

    public static interface FailCallback{

        void onFail(int errorCode);
    }
}
