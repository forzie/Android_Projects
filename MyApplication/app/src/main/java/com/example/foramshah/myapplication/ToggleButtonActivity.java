package com.example.foramshah.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class ToggleButtonActivity extends AppCompatActivity {

    ToggleButton tbSwitch;
    EditText etDisp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle_button);

        tbSwitch = findViewById(R.id.tbSwitch);
        etDisp = findViewById(R.id.etDisp);
        etDisp.setEnabled(false);

        tbSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((ToggleButton)view).isChecked()){
                    etDisp.setEnabled(true);
                }
                else
                    etDisp.setEnabled(false);
            }
        });

    }
}
