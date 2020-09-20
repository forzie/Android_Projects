package com.example.foramshah.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ContextMenuActivity extends AppCompatActivity {

    TextView Txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

        Txt = findViewById(R.id.txt);
        registerForContextMenu(Txt);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.Lowercase1)
        {
            String str = (String) Txt.getText();
            Txt.setText(str.toLowerCase());
            return super.onContextItemSelected(item);
        }
        else if(id==R.id.Upercase1)
        {
            String str = (String) Txt.getText();
            Txt.setText(str.toUpperCase());
            return super.onContextItemSelected(item);
        }
        else if(id==R.id.TrimOfString)
        {
            String str = (String) Txt.getText();

            Txt.setText(str.trim());
            return super.onContextItemSelected(item);
        }
        return super.onContextItemSelected(item);

    }
}
