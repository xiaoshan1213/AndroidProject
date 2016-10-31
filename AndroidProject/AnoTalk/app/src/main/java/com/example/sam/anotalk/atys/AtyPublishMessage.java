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
import com.example.sam.anotalk.net.Publish;

/**
 * Created by sam on 2016/2/23.
 */
public class AtyPublishMessage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_publish_message);

        phone_md5=getIntent().getStringExtra(Config.KEY_PHONE_MD5);
        token=getIntent().getStringExtra(Config.KEY_TOKEN);
        etMsgContent= (EditText) findViewById(R.id.etMsgContent);

        findViewById(R.id.btnPublish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etMsgContent.getText())){
                    Toast.makeText(AtyPublishMessage.this, R.string.content_cannot_be_empty,Toast.LENGTH_SHORT).show();
                    return;
                }

                final ProgressDialog pd=ProgressDialog.show(AtyPublishMessage.this,getResources().getString(R.string.conn_title),getResources().getString(R.string.conn_message));
                new Publish(phone_md5, token, etMsgContent.getText().toString(), new Publish.SuccessCallback() {
                    @Override
                    public void onSuccess() {
                        pd.dismiss();
                        setResult(Config.ACTIVITY_RESULT_NEED_REFRESH);
                        Toast.makeText(AtyPublishMessage.this, R.string.succes_to_publish_msg,Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Publish.FailCallback() {
                    @Override
                    public void onFail(int errorCode) {
                        pd.dismiss();
                        if(errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
                            startActivity(new Intent(AtyPublishMessage.this,AtyLogin.class));
                            finish();
                        }else {
                            Toast.makeText(AtyPublishMessage.this, R.string.fail_to_publish_msg,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private EditText etMsgContent;
    private String phone_md5,token;
}
