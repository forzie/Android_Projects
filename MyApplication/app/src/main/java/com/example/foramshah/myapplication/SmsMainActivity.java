package com.example.foramshah.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsMainActivity extends AppCompatActivity {

    EditText no;
    EditText msg;
    Button btnSendSms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_main);

        no=(EditText)this.findViewById(R.id.etNumber);
        msg=(EditText)this.findViewById(R.id.etMessage);

        btnSendSms=(Button)this.findViewById(R.id.btnSend);

        btnSendSms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String number = no.getText().toString();
                String message = msg.getText().toString();
                if (number.length()>0 && message.length()>0) {
                    SendSms(number,message);
                }else {
                    Toast.makeText(getBaseContext(), "Enter Details", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void SendSms(String number, String message) { try {
        SmsManager smsManager = SmsManager.getDefault(); smsManager.sendTextMessage(number, null, message, null, null); Toast.makeText(getApplicationContext(), "Sent Successfully",
                Toast.LENGTH_SHORT).show();
    } catch (Exception e) {

        Toast.makeText(getApplicationContext(), "Sending Failed", Toast.LENGTH_SHORT).show(); e.printStackTrace();
    }

    }

}


