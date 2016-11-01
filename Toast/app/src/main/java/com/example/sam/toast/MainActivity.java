package com.example.sam.toast;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.DatagramSocketImpl;

public class MainActivity extends AppCompatActivity {

    private Button btnToast=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToast= (Button) findViewById(R.id.btnToast);
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"show toast",Toast.LENGTH_SHORT).show();
                Toast aToast=Toast.makeText(MainActivity.this,"show toast",Toast.LENGTH_SHORT);
                aToast.setGravity(Gravity.CENTER, 0, 0);
//                ImageView imgView=new ImageView(MainActivity.this);
//                imgView.setImageResource(xxx);
//                aToast.setView(imgView);
                aToast.show();

            }
        });

    }
}
