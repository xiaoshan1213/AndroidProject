package com.example.sam.anotalk.atys;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sam.anotalk.Config;
import com.example.sam.anotalk.R;
import com.example.sam.anotalk.net.Comment;
import com.example.sam.anotalk.net.GetComment;
import com.example.sam.anotalk.net.PubComment;
import com.example.sam.anotalk.tools.MD5Tool;
import java.util.List;


/**
 * Created by sam on 2016/2/23.
 */
public class AtyMessage extends ListActivity {

    private String msg,msgId,phone_md5,token;
    private TextView tvMessage;
    private AtyMessageCommentListAdapter adapter;
    private EditText etAddComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_message);

        adapter=new AtyMessageCommentListAdapter(this);
        setListAdapter(adapter);

        tvMessage= (TextView) findViewById(R.id.tvMessage);
        etAddComment= (EditText) findViewById(R.id.etAddComment);

        Intent data=getIntent();
        msgId=data.getStringExtra(Config.KEY_MESSAGE_ID);
        msg=data.getStringExtra(Config.KEY_MESSAGE);
        phone_md5=data.getStringExtra(Config.KEY_PHONE_MD5);
        token=data.getStringExtra(Config.KEY_TOKEN);
        tvMessage.setText(msg);

        getComments();

        findViewById(R.id.btnSendComment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etAddComment.getText())){
                    Toast.makeText(AtyMessage.this, R.string.comment_cannot_be_empty,Toast.LENGTH_SHORT).show();
                    return;
                }

                final ProgressDialog pd=ProgressDialog.show(AtyMessage.this,getResources().getString(R.string.conn_title),getResources().getString(R.string.conn_message));
                new PubComment(MD5Tool.md5(Config.getCachedPhoneNum(AtyMessage.this)), token, msgId, etAddComment.getText().toString(), new PubComment.SuccessCallback() {
                    @Override
                    public void onSuccess() {
                        pd.dismiss();
                        etAddComment.setText("");
                        getComments();
                    }
                }, new PubComment.FailCallback() {
                    @Override
                    public void onFail(int errorCode) {
                        pd.dismiss();
                        if(errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
                            startActivity(new Intent(AtyMessage.this,AtyLogin.class));
                            finish();
                        }else {
                            Toast.makeText(AtyMessage.this, R.string.fail_to_pub_comment,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    private void getComments() {
        final ProgressDialog pd=ProgressDialog.show(this,getResources().getString(R.string.conn_title),getResources().getString(R.string.conn_message));
        new GetComment(phone_md5, token, msgId, 1, 20, new GetComment.SuccessCallback() {
            @Override
            public void onSuccess(String msgId, int page, int perpage, List<Comment> comments) {
                pd.dismiss();
                adapter.clear();
                adapter.addAll(comments);

            }
        }, new GetComment.FailCallback() {
            @Override
            public void onFail(int errorCode) {
                pd.dismiss();
                if(errorCode== Config.RESULT_STATUS_INVALID_TOKEN){
                    startActivity(new Intent(AtyMessage.this,AtyLogin.class));
                    finish();
                }else {
                    Toast.makeText(AtyMessage.this, R.string.fail_to_get_comment, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
