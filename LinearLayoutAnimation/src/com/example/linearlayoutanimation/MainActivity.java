package com.example.linearlayoutanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends Activity {

	private LinearLayout rootViewLayout;
	private OnClickListener btn_onclicklistner=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			rootViewLayout.removeView(v);
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootViewLayout=(LinearLayout)findViewById(R.id.rootView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
		case R.id.action_settings:
			return true;

		case R.id.action_add:
			addButton();
			break;
		}
        
        return super.onOptionsItemSelected(item);
    }
    private void addButton(){
    	Button btn=new Button(this);
    	btn.setText("removeSelf");
    	btn.setOnClickListener(btn_onclicklistner);
    }
}
