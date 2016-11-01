package com.example.sam.spinner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private List<String> itemlist;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner= (Spinner) findViewById(R.id.spinner);

        itemlist=new ArrayList<String>();
        itemlist.add("beijing");
        itemlist.add("shanghai");
        itemlist.add("chengdu");
        itemlist.add("guangzhou");

        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,itemlist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

    }
}
