package com.example.foramshah.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etPwd,etUname;
    Button btnLogin;
    String uname,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUname=findViewById(R.id.etUname);
        etPwd=findViewById(R.id.etPwd);
        btnLogin=findViewById(R.id.btnRegister);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname=etUname.getText().toString();
                pwd=etPwd.getText().toString();
                if(uname.equals("admin") && pwd.equals("admin"))
                {
                    Intent i=new Intent(getApplicationContext(),RegistrationActivity.class);
                    i.putExtra("uname",uname);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid Username and Password",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
