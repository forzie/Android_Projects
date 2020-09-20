package com.example.foramshah.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Date;

public class EditTextActivity extends AppCompatActivity {

    EditText ename,eaddress,epass,edate,edigit;
    Button btnDisplay;
    AutoCompleteTextView eauto;
    String content;
    String item[]={
            "Male","Female"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        ename= findViewById(R.id.etName);
        eaddress= findViewById(R.id.etAdd);
        epass= findViewById(R.id.etPwd);
        edigit= findViewById(R.id.etDigit);
        eauto=  findViewById(R.id.etAuto);
        edate =  findViewById(R.id.editText4);
        btnDisplay = findViewById(R.id.btnDisplay);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,item);
        eauto.setAdapter(adapter);

        eauto.setThreshold(1);
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content = "\n Name : "+ename.getText().toString() ;
                content += "\n Password : "+epass.getText().toString() ;
                content = content + "\n Address : "+eaddress.getText().toString() ;
                content = content + "\n Gender : "+eauto.getText().toString() ;
                content = content + "\n Date : "+edate.getText().toString() ;
                content = content + "\n Phone No : "+edigit.getText().toString() ;
                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();

            }
        });
    }
}
