package com.example.sam.drawerlayout;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout mDrawerlayout;
    private ListView mDrawerListView;
    private ArrayList<String> menuList;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerlayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerListView= (ListView) findViewById(R.id.left_drawer);
        menuList=new ArrayList<String>();
        for (int i=0;i<5;i++){
            menuList.add("choice"+i);
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuList);
        mDrawerListView.setAdapter(adapter);
        mDrawerListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment contentFragment=new ContentFragment();
        Bundle args=new Bundle();
        args.putString("text",menuList.get(position));
        contentFragment.setArguments(args);
        FragmentManager fm=getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame,contentFragment).commit();

        mDrawerlayout.closeDrawer(mDrawerListView);
    }
}
