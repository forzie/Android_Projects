package com.example.foramshah.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_main);

    }

    public void openDialogBox(View view) {

        AlertDialog.Builder DIALOG_ACT = new AlertDialog.Builder(this);
        DIALOG_ACT.setMessage("Select Activity");

        DIALOG_ACT.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent nav = new Intent(AlertDialogMainActivity.this,PosotiveActivity.class);
                startActivity(nav);
                finish();
            }
        });

        DIALOG_ACT.setNegativeButton("No",new DialogInterface.OnClickListener() { @Override
        public void onClick(DialogInterface dialog, int which) {
            Intent nav = new Intent(AlertDialogMainActivity.this,NegativeActivity.class);
            startActivity(nav);
            finish();
        }
        });

        AlertDialog alertDialog = DIALOG_ACT.create();
        alertDialog.show();





    }
}

