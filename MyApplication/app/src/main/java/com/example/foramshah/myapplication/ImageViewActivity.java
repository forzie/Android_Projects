package com.example.foramshah.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageViewActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        iv=findViewById(R.id.ivImg);
        iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        iv.setImageResource(R.drawable.img1);
    }
}
