package com.example.sam.anotalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sam.anotalk.atys.AtyLogin;
import com.example.sam.anotalk.atys.AtyTimeline;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token=Config.getCachedToken(this);
        String phoneNum=Config.getCachedPhoneNum(this);
        if(token!=null && phoneNum!=null){
            //System.out.println("atyTimeline");
            Intent intent=new Intent(this, AtyTimeline.class);
            intent.putExtra(Config.KEY_TOKEN,token);
            intent.putExtra(Config.KEY_PHONE_NUM,phoneNum);
            startActivity(intent);
        }else {
//            System.out.println("atyLogin");
//            System.out.println("test");
            startActivity(new Intent(this, AtyLogin.class));
        }
        finish();
    }

}
