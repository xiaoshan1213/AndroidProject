package com.example.sam.anotalk.localdata;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import com.example.sam.anotalk.Config;
import com.example.sam.anotalk.tools.MD5Tool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sam on 2016/2/23.
 */
public class MyContacts {

    public static String getContactsJSONString(Context context){
        Cursor cursor=context.getContentResolver().query(Phone.CONTENT_URI,null,null,null,null);
        String phoneNum;
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject;

        while(cursor.moveToNext()){
            phoneNum=cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
            if(phoneNum.charAt(0)=='+' && phoneNum.charAt(1)=='8' && phoneNum.charAt(2)=='6'){
                phoneNum=phoneNum.substring(3);
            }

            jsonObject=new JSONObject();
            try {
                jsonObject.put(Config.KEY_PHONE_MD5, MD5Tool.md5(phoneNum));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);

            //System.out.println(phoneNum);
        }
        return jsonArray.toString();
    }

}
