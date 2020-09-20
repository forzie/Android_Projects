package com.example.foramshah.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    Button btnInc,btnDec;
    TextView tvMsg;
    int i=30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInc=findViewById(R.id.btnIncrease);
        btnDec=findViewById(R.id.btnDecrease);
        tvMsg=findViewById(R.id.txtMsg);

        btnInc.setOnClickListener(this);
        btnDec.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnIncrease){
            tvMsg.setTextSize(i+10);
        }
        else{
            if(i==10){
                tvMsg.setTextSize(10);
            }
            else{
                tvMsg.setTextSize(i-10);
            }
        }

    }
}
