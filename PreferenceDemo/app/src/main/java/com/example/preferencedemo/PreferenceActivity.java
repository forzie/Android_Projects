package com.example.preferencedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class PreferenceActivity extends AppCompatActivity {
    TextView name ;
    TextView Salary;
    CheckBox cb;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String active = "activeKey";
    public static final String salary = "salKey";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        name = (TextView)findViewById(R.id.editText1);
        Salary = (TextView)findViewById(R.id.editText2);
        cb = (CheckBox)findViewById(R.id.checkBox1);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name))
        {
            name.setText(sharedpreferences.getString(Name, ""));

        }
        if (sharedpreferences.contains(salary))
        {
            float f = sharedpreferences.getFloat(salary, 0.0F);
            String temp = Float.toString(f);
            Salary.setText(temp);
        }
        if (sharedpreferences.contains(active))
        {
            boolean bb = sharedpreferences.getBoolean(active, false);
            cb.setChecked(bb);

        }

         }
    public void run(View v)
    {
        String vnm  = name.getText().toString();
        String vsal  = Salary.getText().toString();

        float vfsal = Float.parseFloat(vsal);
        boolean chk = cb.isChecked();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, vnm);
        editor.putFloat(salary,vfsal);
        editor.putBoolean(active, chk);

        editor.commit();
        Toast.makeText(this,"data saved",Toast.LENGTH_LONG).show();

    }
    public void clear(View v){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        //editor.clear();
        Toast.makeText(getApplicationContext(),"Data Clear",Toast.LENGTH_SHORT).show();
    }



}
