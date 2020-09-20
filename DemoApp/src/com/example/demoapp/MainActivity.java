package com.example.demoapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	Button btnInc,btnDec;
	TextView tvDisplay;
	int i=30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnInc=(Button)findViewById(R.id.Incbtn);
        btnDec=(Button)findViewById(R.id.DecBtn);
        tvDisplay=(TextView)findViewById(R.id.tvDisplay); 
        tvDisplay.setTextSize(i);
    }

    public void increase(View v)
    {
    	tvDisplay.setTextSize(i);
    	i=i+5;
    }
    public void decraese(View v)
    {
    	tvDisplay.setTextSize(i);
    	i=i-5;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
