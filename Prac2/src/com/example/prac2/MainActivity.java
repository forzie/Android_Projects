package com.example.prac2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	
	Button btnInc,btnDec;
	int no=30;
	TextView tvMsg;
	ToggleButton tbSwitch;
	EditText et1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnInc= (Button) findViewById(R.id.btnIncrease);
        btnDec= (Button) findViewById(R.id.btnDecrease);
        tvMsg= (TextView) findViewById(R.id.textView1);
        tbSwitch= (ToggleButton) findViewById(R.id.tbSwitch);
        tbSwitch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(tbSwitch.isChecked())
				{
					et1.setEnabled(true);
				}
				else
				{
					et1.setEnabled(false);
				}
			}
		});
        
        btnInc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				tvMsg.setTextSize(no+5);
			}
		});
        
        btnDec.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				tvMsg.setTextSize(no-5);
			}
		});
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
