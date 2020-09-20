package com.example.foramshah.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class OptionMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.Personal)
        {
            Intent intent = new Intent(OptionMenuActivity.this,PersonalActivity.class  );
            startActivity(intent);
            //Toast.makeText(getApplicationContext(),"P1",Toast.LENGTH_LONG).show();
            return  true;
        }
        else if (id == R.id.Acedemic)
        {
            Intent intent = new Intent(OptionMenuActivity.this,AcademicActivity.class  );
            startActivity(intent);

            return  true;
        }
        else if (id == R.id.OtherItems)
        {
            Intent intent = new Intent(OptionMenuActivity.this,OtherItemsActivity.class  );
            startActivity(intent);

            return true;
        }
        else if (id == R.id.ADD)
        {
            Intent intent = new Intent(OptionMenuActivity.this,AdditionActivity.class  );
            startActivity(intent);

            return  true;
        }
        else if(id == R.id.SUB)
        {
            Intent intent = new Intent(OptionMenuActivity.this,SubtractionActivity.class  );
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
