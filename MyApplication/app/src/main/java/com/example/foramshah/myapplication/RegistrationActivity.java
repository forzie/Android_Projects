package com.example.foramshah.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText etContactNo,etEmail,etRegUname;
    TextView tvWelcome;
    Button btnRegister;
    String str="",uname,email,no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etContactNo = findViewById(R.id.etContact);
        etRegUname = findViewById(R.id.etUname);
        etEmail = findViewById(R.id.etEmail);
        tvWelcome=findViewById(R.id.tvWelcome);
        btnRegister = findViewById(R.id.btnRegister);

        Bundle extras =getIntent().getExtras();
        uname=extras.getString("uname");

        tvWelcome.setText("Welcome "+uname);
        etRegUname.setText(uname);
        etRegUname.setEnabled(false);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=etEmail.getText().toString();
                no=etContactNo.getText().toString();

                str="Name : "+uname;
                str+="\nE-mail : "+email;
                str+="\nContact no : "+no;

                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();

            }
        });
    }
}
