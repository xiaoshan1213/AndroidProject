package com.example.sam.getphonecontact;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetContact.getNumber(this);
        lv= (ListView) findViewById(R.id.lv);
        adapter=new MyAdapter(GetContact.contactList,this);
        lv.setAdapter(adapter);
    }

}
