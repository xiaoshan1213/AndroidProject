package com.example.sam.anotalk.net;

import android.content.Context;

import com.example.sam.anotalk.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 2016/3/2.
 */
public class GetComment {

    public GetComment(String phone_md5,String token,String msgId,int page,int perpage,final SuccessCallback successCallback,final FailCallback failCallback){

        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject=new JSONObject(result);

                    switch (jsonObject.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if(successCallback!=null){
                                JSONArray commentJSONArray=jsonObject.getJSONArray(Config.KEY_COMMENTS);
                                JSONObject commentobj;
                                List<Comment> comments=new ArrayList<Comment>();
                                for(int i=0;i<commentJSONArray.length();i++){
                                    commentobj=commentJSONArray.getJSONObject(i);
                                    comments.add(new Comment(commentobj.getString(Config.KEY_CONTENT),commentobj.getString(Config.KEY_PHONE_MD5)));
                                }
                                successCallback.onSuccess(jsonObject.getString(Config.KEY_MESSAGE_ID),jsonObject.getInt(Config.KEY_PAGE),jsonObject.getInt(Config.KEY_PERPAGE),comments);
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
        },Config.KEY_ACTION,Config.ACTION_GET_COMMENT,
                Config.KEY_TOKEN,token,
                Config.KEY_MESSAGE_ID,msgId,
                Config.KEY_PAGE,page+"",
                Config.KEY_PERPAGE,perpage+"");
    }

    public static interface SuccessCallback{
        void onSuccess(String msgId,int page,int perpage,List<Comment> comments);
    }

    public static interface FailCallback{
        void onFail(int errorCode);
    }
}
