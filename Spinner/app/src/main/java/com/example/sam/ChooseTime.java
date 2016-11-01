package com.example.sam;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.sam.spinner.R;

public class ChooseTime extends AppCompatActivity {

    private Button btnChooseTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);
        btnChooseTime= (Button) findViewById(R.id.btnChooseTime);
        btnChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(ChooseTime.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String theTime=String.format("%d:%d",hourOfDay,minute);
                        System.out.println(theTime);
                        btnChooseTime.setText(theTime);
                    }
                },0,0,true).show();
            }
        });
    }

}
