package com.example.foramshah.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class RadioButtonActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    RadioGroup rgAlignment,rgTextColor;
    EditText etTextMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);

        rgAlignment=findViewById(R.id.rgAlignment);
        rgTextColor=findViewById(R.id.rgTextColor);
        etTextMsg=findViewById(R.id.etTextMsg);

        rgAlignment.setOnCheckedChangeListener(this);
        rgTextColor.setOnCheckedChangeListener(this);

    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int id=radioGroup.getId();

        if(id==R.id.rgAlignment)
        {
            switch (i)
            {
                case R.id.rbCenter:
                    etTextMsg.setGravity(Gravity.CENTER);
                    break;
                case R.id.rbLeft:
                    etTextMsg.setGravity(Gravity.LEFT);
                    break;
                case R.id.rbRight:
                    etTextMsg.setGravity(Gravity.RIGHT);
                    break;
            }
        }
        else//Code for rgTextColor
        {
            switch (i)
            {
                case R.id.rbRed:
                    etTextMsg.setTextColor(Color.RED);
                    break;
                case R.id.rbBlack:
                    etTextMsg.setTextColor(Color.BLACK);
                    break;
                case R.id.rbPink:
                    etTextMsg.setTextColor(Color.DKGRAY);
                    break;
            }
        }
    }
}
