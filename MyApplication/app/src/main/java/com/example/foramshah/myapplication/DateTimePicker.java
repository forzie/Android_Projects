package com.example.foramshah.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DateTimePicker extends AppCompatActivity implements  DatePicker.OnDateChangedListener,TimePicker.OnTimeChangedListener{

    DatePicker dpDate;
    TimePicker tpTime;
    TextView tvDateMsg;
    String infoDate="Date: 8/8/2018",infoTime="Hours:14 Minutes: 25";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_picker);

        dpDate=findViewById(R.id.datePicker);
        tpTime=findViewById(R.id.timePicker);
        tvDateMsg=findViewById(R.id.tvDateMsg);

        tvDateMsg.setText(infoDate+"\n"+infoTime);

        dpDate.setOnDateChangedListener(this);
        tpTime.setOnTimeChangedListener(this);

    }

    @Override
    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
            infoDate="Date : "+i2+"/"+(i1+1)+"/"+i;
            tvDateMsg.setText(infoDate+"\n"+infoTime);
    }

    @Override
    public void onTimeChanged(TimePicker timePicker, int i, int i1) {
            infoTime="Hours: "+i+" Minutes: "+i1;
            tvDateMsg.setText(infoDate+"\n"+infoTime);
    }
}
