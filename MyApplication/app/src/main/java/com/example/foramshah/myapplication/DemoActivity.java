package com.example.foramshah.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DemoActivity extends AppCompatActivity {
    Button btnDIsplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        btnDIsplay=findViewById(R.id.btnClick);
    }

    protected void Display(View v)
    {
        Toast.makeText(this,"CLicked",Toast.LENGTH_LONG).show();
    }

}
