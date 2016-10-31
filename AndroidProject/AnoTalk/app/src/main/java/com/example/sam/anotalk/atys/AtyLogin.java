package com.example.sam.anotalk.atys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sam.anotalk.Config;
import com.example.sam.anotalk.R;
import com.example.sam.anotalk.net.Login;
import com.example.sam.anotalk.net.SMSCode;
import com.example.sam.anotalk.tools.MD5Tool;

/**
 * Created by sam on 2016/2/23.
 */
public class AtyLogin extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);
        final EditText etPhoneNum= (EditText) findViewById(R.id.etPhoneNo);
        final EditText etCode= (EditText) findViewById(R.id.etCode);
        findViewById(R.id.btnGetCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etPhoneNum.getText())){
                    Toast.makeText(AtyLogin.this, R.string.phone_num_not_empty,Toast.LENGTH_SHORT).show();
                    return;
                }
                final ProgressDialog pd=ProgressDialog.show(AtyLogin.this,getString(R.string.conn_title),getString(R.string.conn_message));
                new SMSCode(etPhoneNum.getText().toString(), new SMSCode.SuccessCallback() {
                    @Override
                    public void onSuccess() {
                        pd.dismiss();
                        Toast.makeText(AtyLogin.this, R.string.success_to_get_code,Toast.LENGTH_SHORT).show();
                    }
                }, new SMSCode.FailCallback() {
                    @Override
                    public void onFail() {
                        pd.dismiss();
                        Toast.makeText(AtyLogin.this, R.string.fail_to_get_code,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etPhoneNum.getText())){
                    Toast.makeText(AtyLogin.this, R.string.phone_num_not_empty,Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(etCode.getText())){
                    Toast.makeText(AtyLogin.this, R.string.code_not_empty,Toast.LENGTH_SHORT).show();
                    return;
                }

                final ProgressDialog pd=ProgressDialog.show(AtyLogin.this,getString(R.string.conn_title),getString(R.string.conn_message));
                new Login(MD5Tool.md5(etPhoneNum.getText().toString()),etCode.getText().toString(), new Login.SuccessCallback() {
                    @Override
                    public void onSuccess(String token) {
                        pd.dismiss();
                        Config.cacheToken(AtyLogin.this, token);
                        Config.cachePhoneNum(AtyLogin.this, etPhoneNum.getText().toString());

                        Intent intent=new Intent(AtyLogin.this,AtyTimeline.class);
                        intent.putExtra(Config.KEY_TOKEN,token);
                        intent.putExtra(Config.KEY_PHONE_NUM,etPhoneNum.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                }, new Login.FailCallback() {
                    @Override
                    public void onFail() {
                        pd.dismiss();
                        Toast.makeText(AtyLogin.this, R.string.fail_to_login,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
