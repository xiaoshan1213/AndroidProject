package com.example.sam.transmitinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AnoAty extends AppCompatActivity {

    private TextView tv;
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ano_aty);
        Intent i=getIntent();
//        Bundle b=i.getExtras();
       // Bundle b2=i.getBundleExtra("b1");
       tv= (TextView) findViewById(R.id.textFromMain);
        et= (EditText) findViewById(R.id.editText);
       // tv.setText(i.getStringExtra("data"));
//        tv.setText(String.format("name=%s,age=%d",b.getString("data"),b.getInt("age")));
        User user= (User) i.getParcelableExtra("user");
        tv.setText(String.format("name=%s,age=%d",user.getName(),user.getAge()));

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.putExtra("data",et.getText().toString());
                setResult(1, i);
                finish();
            }
        });
    }

}
