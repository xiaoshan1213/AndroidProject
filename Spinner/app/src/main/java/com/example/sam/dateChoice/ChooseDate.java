package com.example.sam.dateChoice;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.sam.spinner.R;

public class ChooseDate extends AppCompatActivity {

    private Button btnChooseDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date);

        btnChooseDate= (Button) findViewById(R.id.btnChooseDate);
        btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ChooseDate.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String theDate=String.format("%d-%d-%d", year, monthOfYear+1, dayOfMonth);
                        System.out.println(theDate);
                        btnChooseDate.setText(theDate);
                    }
                },2015,3,30).show();
            }
        });
    }
}
