package com.example.sam.getphonecontact;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 2016/1/18.
 */
public class GetContact {
    public static List<PhoneInfo> contactList=new ArrayList<PhoneInfo>();
    public static String getNumber(Context context){
        String phoneNumber;
        String phoneName;
        Cursor cursor=context.getContentResolver().query(Phone.CONTENT_URI,null,null,null,null);
        while(cursor.moveToNext()){
            phoneNumber=cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
            phoneName=cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
            PhoneInfo phoneInfo=new PhoneInfo(phoneName,phoneNumber);
            contactList.add(phoneInfo);
        }
        return null;
    }
}
