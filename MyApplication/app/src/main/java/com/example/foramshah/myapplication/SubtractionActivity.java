package com.example.foramshah.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SubtractionActivity extends AppCompatActivity {

    EditText Ed1,Ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction);

        Ed1 = findViewById(R.id.ED1);
        Ed2 = findViewById(R.id.ED2);

        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
    public void sub(View view) {
        int i = Integer.parseInt(String.valueOf(Ed1.getText()));
        int j = Integer.parseInt(String.valueOf(Ed2.getText()));
        String str = String.valueOf(i-j);
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home)
        {
            Intent intent = new Intent(SubtractionActivity.this,OptionMenuActivity.class  );
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
