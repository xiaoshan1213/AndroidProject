package com.example.sam;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sam.spinner.R;

public class SingleChoice extends AppCompatActivity {

    private Button btnSubmit;
    private RadioButton rb1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_choice);
        btnSubmit= (Button) findViewById(R.id.btnSubmit);
        rb1= (RadioButton) findViewById(R.id.rb1);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb1.isChecked()){
                    Toast.makeText(SingleChoice.this,"correct!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SingleChoice.this,"wrong!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
