package com.example.sam.anotalk.atys;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sam.anotalk.Config;
import com.example.sam.anotalk.R;
import com.example.sam.anotalk.localdata.MyContacts;
import com.example.sam.anotalk.net.Message;
import com.example.sam.anotalk.net.Timeline;
import com.example.sam.anotalk.net.UploadContacts;
import com.example.sam.anotalk.tools.MD5Tool;


import java.util.List;

/**
 * Created by sam on 2016/2/23.
 */
public class AtyTimeline extends ListActivity {

    private String phone_md5,token;
    private AtyTimelineListAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_timeline);

        adapter=new AtyTimelineListAdapter(this);
        setListAdapter(adapter);
        phone_md5=MD5Tool.md5(getIntent().getStringExtra(Config.KEY_PHONE_NUM));
        token=getIntent().getStringExtra(Config.KEY_TOKEN);

        final ProgressDialog pd=ProgressDialog.show(this,getResources().getString(R.string.conn_title),getResources().getString(R.string.conn_message));
        new UploadContacts(phone_md5,token, MyContacts.getContactsJSONString(this), new UploadContacts.SuccessCallback() {
            @Override
            public void onSuccess() {
                loadMessage();
                pd.dismiss();
            }
        }, new UploadContacts.FailCallback() {
            @Override
            public void onFail(int errorCode) {
                pd.dismiss();
                if(errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
                    startActivity(new Intent(AtyTimeline.this,AtyLogin.class));
                    finish();
                }else {
                    loadMessage();
                }
            }
        });
    }

    private void loadMessage(){

        //ystem.out.println("LOADMSG");
        final ProgressDialog pd=ProgressDialog.show(this,getResources().getString(R.string.conn_title),getResources().getString(R.string.conn_message));
        new Timeline(phone_md5,token,1,20,new Timeline.SuccessCallback() {
            @Override
            public void onSuccess(int page, int perpage, List<Message> timeline) {
                pd.dismiss();
                adapter.clear();
                adapter.addAll(timeline);

            }
        }, new Timeline.FailCallback() {
            @Override
            public void onFail(int errorCode) {
                pd.dismiss();
                if(errorCode==Config.RESULT_STATUS_INVALID_TOKEN){
                    startActivity(new Intent(AtyTimeline.this,AtyLogin.class));
                    finish();
                }else {
                    Toast.makeText(AtyTimeline.this,R.string.fail_to_load_timeline_data,Toast.LENGTH_LONG ).show();
                }

            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Message msg= (Message) adapter.getItem(position);
        Intent i=new Intent(this,AtyMessage.class);
        i.putExtra(Config.KEY_MESSAGE,msg.getMsg());
        i.putExtra(Config.KEY_MESSAGE_ID,msg.getMsgId());
        i.putExtra(Config.KEY_PHONE_MD5,msg.getPhone_md5());
        i.putExtra(Config.KEY_TOKEN,token);
        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_aty_timeline,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuShowAddMsg:
                Intent i=new Intent(AtyTimeline.this,AtyPublishMessage.class);
                i.putExtra(Config.KEY_PHONE_MD5,phone_md5);
                i.putExtra(Config.KEY_TOKEN,token);
                startActivityForResult(i, 0);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Config.ACTIVITY_RESULT_NEED_REFRESH:
                loadMessage();
                break;
        }
    }
}
