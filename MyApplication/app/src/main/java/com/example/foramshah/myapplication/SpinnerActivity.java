package com.example.foramshah.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SpinnerActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener{
    Spinner spCity,spState;
    TextView tvDisp;

    String stGuj[]={"Nadiad","Vadodara","Bharuch","Ahmedabad","Vapi"};
    String stMah[]={"Mumbai","Pune","Nashik","Nagpur","Kolhapur"};
    String stRaj[]={"Udaipur","Jodhpur","Jaipur","Bhilwada","Chhitod"};
    String stKeral[]={"Kochi","Kollam","Munnar","Kannur","Sarkhej"};
    String stTamil[]={"Ooty","Chennai","Salem","Karur","Hosur"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        spCity=findViewById(R.id.spinCity);
        spState=findViewById(R.id.spinState);
        tvDisp=findViewById(R.id.tvCity);

        spCity.setOnItemSelectedListener(this);
        spState.setOnItemSelectedListener(this);

        ArrayAdapter adapter= ArrayAdapter.createFromResource(this,R.array.state_list,android.R.layout.simple_spinner_dropdown_item);
        spState.setAdapter(adapter);
        spState.setPrompt("Select State");

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        String source[]=null;

        int obj=adapterView.getId();
        if(obj==R.id.spinState)
        {
            String txt=adapterView.getItemAtPosition(pos).toString();
            switch (txt)
            {
                case "Gujarat":
                    source=stGuj;
                    break;
                case "Maharashtra":
                    source=stMah;
                    break;
                case "Rajasthan":
                    source=stRaj;
                    break;
                case "Keral":
                    source=stKeral;
                    break;
                case "Tamilnadu":
                    source=stTamil;
                    break;
            }
            ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_checked,source);
            spCity.setAdapter(adapter);
            spCity.setClickable(true);
        }
        else
        {
            String txt=adapterView.getItemAtPosition(pos).toString();
            tvDisp.setText(txt+" City Selected");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
