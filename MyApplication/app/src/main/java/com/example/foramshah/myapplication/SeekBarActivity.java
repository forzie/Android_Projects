package com.example.foramshah.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    SeekBar sbRed,sbBlue,sbGreen;
    TextView tvBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        sbRed=findViewById(R.id.sbRed);
        sbBlue=findViewById(R.id.sbBlue);
        sbGreen=findViewById(R.id.sbGreen);
        tvBg=findViewById(R.id.tvBg);

        sbRed.setOnSeekBarChangeListener(this);
        sbBlue.setOnSeekBarChangeListener(this);
        sbGreen.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        int id=seekBar.getId();
        if(id==R.id.sbRed)
        {
            tvBg.setBackgroundColor(Color.rgb(sbRed.getProgress(),sbBlue.getProgress(),sbGreen.getProgress()));
        }
        else if(id==R.id.sbBlue)
        {
            tvBg.setBackgroundColor(Color.rgb(sbRed.getProgress(),sbBlue.getProgress(),sbGreen.getProgress()));
        }
        else
        {
            tvBg.setBackgroundColor(Color.rgb(sbRed.getProgress(),sbBlue.getProgress(),sbGreen.getProgress()));
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
