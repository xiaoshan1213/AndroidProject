package com.example.sam;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.sam.spinner.R;

public class MultiChoice extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox cb1,cb2,cb3;
    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_choice);
        cb1= (CheckBox) findViewById(R.id.checkBox);
        cb2= (CheckBox) findViewById(R.id.checkBox2);
        cb3= (CheckBox) findViewById(R.id.checkBox3);
        tvRes= (TextView) findViewById(R.id.tvRes);

        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String str="your choice:";
        if(cb1.isChecked()){
            str+=cb1.getText()+";";
        }
        if(cb2.isChecked()){
            str+=cb2.getText()+";";
        }
        if(cb3.isChecked()){
            str+=cb3.getText()+";";
        }
        tvRes.setText(str);
    }
}
