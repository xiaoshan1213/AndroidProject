package com.example.sam.checkpermission;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by sam on 2016/1/9.
 */
public class Hello {

    public  static final String PERMISSION_SAYHELLO="com.example.sam.checkpermission.permission.SAYHELLO";
    public static void sayHello(Context context){

        int checkResult=context.checkCallingOrSelfPermission(PERMISSION_SAYHELLO);
        if(checkResult!= PackageManager.PERMISSION_GRANTED){
            throw new SecurityException("sayhello need permission SAYHELLO");
        }
        System.out.println("hello world!");
    }
}
