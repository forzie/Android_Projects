package com.example.foramshah.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class CustomRatingBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_rating_bar);
    }
    public void showRatingBar(View v)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this); LayoutInflater inflater = getLayoutInflater(); builder.setTitle("With RatingBar");
        View dialogLayout = inflater.inflate(R.layout.custom_rating_layout, null);

        final RatingBar ratingBar = dialogLayout.findViewById(R.id.dialog_rb); builder.setView(dialogLayout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { @Override

        public void onClick(DialogInterface dialogInterface, int i)
        {
            Toast.makeText(CustomRatingBar.this,"Rating Bar Value: "+(ratingBar.getProgress()),Toast.LENGTH_LONG).show();
            dialogInterface.dismiss();
        }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { @Override public void onClick(DialogInterface dialog, int which) { dialog.cancel();
        }
        });
        builder.show();


    }
}
