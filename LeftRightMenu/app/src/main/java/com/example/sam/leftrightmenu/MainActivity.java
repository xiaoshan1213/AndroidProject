package com.example.sam.leftrightmenu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private MyLayout myLayout;
    private LeftMenu leftMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myLayout=new MyLayout(this);
        setContentView(myLayout);
        leftMenu=new LeftMenu();
        getSupportFragmentManager().beginTransaction().add(MyLayout.LEFT_ID,leftMenu).commit();
    }
}
