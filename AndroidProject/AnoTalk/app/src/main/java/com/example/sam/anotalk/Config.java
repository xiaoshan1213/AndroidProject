package com.example.sam.anotalk;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sam on 2016/2/23.
 */
public class Config {

    public static final String APP_ID="com.example.sam.anotalk";
    public static final String KEY_TOKEN="token";
    public static final String KEY_PHONE_MD5="phone_md5";
    public static final String KEY_PHONE_NUM="phone";
    public static final String KEY_ACTION="action";
    public static final String KEY_STATUS="status";
    public static final String KEY_CODE ="code";
    public static final String KEY_CONTACTS ="contacts";
    public static final String KEY_PAGE ="page";
    public static final String KEY_PERPAGE ="perpage";
    public static final String KEY_TIMELINE ="timeline";
    public static final String KEY_MESSAGE_ID ="msg_id";
    public static final String KEY_MESSAGE ="message";
    public static final String KEY_COMMENTS ="comments";
    public static final String KEY_CONTENT ="content";

    public static final int RESULT_STATUS_SUCCESS=1;
    public static final int RESULT_STATUS_FAIL=0;
    public static final int RESULT_STATUS_INVALID_TOKEN=2;
    public static final int ACTIVITY_RESULT_NEED_REFRESH=10000;

    public static final String CHARSET = "utf-8";
    public static final String SERVER_URL="http://192.168.56.1:8080/TestServer/api.jsp"; //localhost无法访问，因为Android模拟器的localhost和电脑不同

    public static final String ACTION_SMS_CODE="send_pass";
    public static final String ACTION_LOGIN ="login";
    public static final String ACTION_UOLOAD_CONTACTS ="upload_contacts";
    public static final String ACTION_TIMELINE ="timeline";
    public static final String ACTION_GET_COMMENT ="get_comment";
    public static final String ACTION_PUB_COMMENT ="pub_comment";
    public static final String ACTION_PUBLISH ="publish";


    public static String getCachedToken(Context context){
        return context.getSharedPreferences(APP_ID,context.MODE_PRIVATE).getString(KEY_TOKEN,null);
    }

    public static void cacheToken(Context context,String token){
        SharedPreferences.Editor editor=context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).edit();
        editor.putString(KEY_TOKEN,token);
        editor.commit();
    }

    public static String getCachedPhoneNum(Context context){
        return context.getSharedPreferences(APP_ID,context.MODE_PRIVATE).getString(KEY_PHONE_NUM,null);
    }

    public static void cachePhoneNum(Context context,String phone){
        SharedPreferences.Editor editor=context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).edit();
        editor.putString(KEY_PHONE_NUM,phone);
        editor.commit();
    }


}
