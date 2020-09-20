package com.example.foramshah.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class CheckBoxRating extends AppCompatActivity implements View.OnClickListener,RatingBar.OnRatingBarChangeListener{

    RatingBar rbChange;
    CheckBox cbBold,cbItalic;
    EditText etMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box_rating);

        cbBold=findViewById(R.id.cbBold);
        cbItalic=findViewById(R.id.cbItalic);
        etMsg=findViewById(R.id.etMsg);
        rbChange=findViewById(R.id.rbChange);

        cbBold.setOnClickListener(this);
        cbItalic.setOnClickListener(this);

        rbChange.setOnRatingBarChangeListener(this);
    }
    @Override
    public void onClick(View view) {
        int obj=view.getId();
        etMsg.setTypeface(Typeface.DEFAULT);
        if(obj==R.id.cbBold || obj==R.id.cbItalic)
        {
            if(cbBold.isChecked() && cbItalic.isChecked())
                etMsg.setTypeface(etMsg.getTypeface(),Typeface.BOLD_ITALIC);
            else if(!cbBold.isChecked() && cbItalic.isChecked())
                etMsg.setTypeface(etMsg.getTypeface(),Typeface.ITALIC);
            else if(cbBold.isChecked() && !cbItalic.isChecked())
                etMsg.setTypeface(etMsg.getTypeface(),Typeface.BOLD);
            else
                etMsg.setTypeface(Typeface.DEFAULT);
        }
        else
        {
            etMsg.setTypeface(Typeface.DEFAULT);
        }

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float ratingValue, boolean b) {
        etMsg.setText("Rating Value : "+Float.toString(ratingValue));
    }
}
