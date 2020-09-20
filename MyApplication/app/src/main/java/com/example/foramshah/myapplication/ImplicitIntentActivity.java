package com.example.foramshah.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ImplicitIntentActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCall,btnDial,btnDisplayContact,btnOpenWebpage,btnGeoLocation;
    Intent i;
    final int MY_PERMISSIONS_REQUEST_CALL_PHONE=200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);

        btnCall=findViewById(R.id.btnCall);
        btnDial=findViewById(R.id.btnDial);
        btnDisplayContact=findViewById(R.id.btnDisplayContact);
        btnOpenWebpage=findViewById(R.id.btnOpenWebpage);
        btnGeoLocation=findViewById(R.id.btnGeoLocation);

        btnCall.setOnClickListener(this);
        btnDial.setOnClickListener(this);
        btnDisplayContact.setOnClickListener(this);
        btnOpenWebpage.setOnClickListener(this);
        btnGeoLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnCall:
                i=new Intent(Intent.ACTION_CALL, Uri.parse("tel:9409611240"));
                // Here, thisActivity is the current activity
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);

                    // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                } else {
                    //You already have permission
                    try {
                        startActivity(i);
                    } catch(SecurityException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btnDial:
                i=new Intent(Intent.ACTION_DIAL);
                startActivity(i);
                break;
            case R.id.btnDisplayContact:
                i=new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                startActivity(i);
                break;
            case R.id.btnOpenWebpage:
                i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(i);
                break;
            case R.id.btnGeoLocation:
                i=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:22.6798,72.8806?z=16"));
                startActivity(i);
                break;
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the phone call

                } else {
                    Toast.makeText(this, "Error: Permission Denial", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
