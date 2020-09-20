package com.example.preferencedemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FilesdActivity extends AppCompatActivity {
    EditText txtData,FileName;
    Button btnWriteSDFile;
    Button btnReadSDFile;
    Button btnClearScreen;
   File myFile=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filesd);
        txtData = (EditText) findViewById(R.id.sdtxtData);
        txtData.setHint("Enter some lines of data here...");
        FileName = (EditText) findViewById(R.id.sdfilename);
        btnWriteSDFile = (Button) findViewById(R.id.sdbtnWriteSDFile);
        btnReadSDFile = (Button)findViewById(R.id.sdbtnReadSDFile);
        btnClearScreen = (Button)findViewById(R.id.sdbtnClearScreen);

        btnClearScreen.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                txtData.setText("");
            }
        });

    }

    public void write(View v)
    {
        try {
           // File myFile = null;
            String temp = FileName.getText().toString();
            if (temp == null) {
                Toast.makeText(getBaseContext(), "enter file name", Toast.LENGTH_LONG).show();
                FileName.requestFocus();
            }
            String fname = null;
            if (temp.contains("/")) {
                String dirname = temp.substring(0, temp.indexOf("/"));
                fname = temp.substring(temp.indexOf("/") + 1, temp.length());

                //  Create Directory to SDCard
                File dirFile = new File(Environment.getExternalStorageDirectory() + File.separator + dirname);
                dirFile.mkdir();
                myFile = new File(Environment.getExternalStorageDirectory() + File.separator + dirname + File.separator + fname);

            } else {
                fname = temp;
                // for external write
                myFile = new File(Environment.getExternalStorageDirectory() + File.separator + fname);
            }
      /* check for the permission */
      if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
          writedata();
      }else
      {
          requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},200 );
      }

        }catch(Exception e) {
          Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
           super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==200)
        {
            writedata();
        }
    }

    public void writedata()
    {
    try{
        myFile.createNewFile();


    FileOutputStream fOut = new FileOutputStream(myFile,true);

    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
    myOutWriter.append(txtData.getText().toString());
    myOutWriter.close();
    fOut.close();
    Toast.makeText(getBaseContext(),
            "Done writing SD ",Toast.LENGTH_SHORT).show();
} catch (Exception e) {
        Toast.makeText(getBaseContext(), "in write method " + e.getMessage(),
                Toast.LENGTH_SHORT).show();
    } // end of catch

}



    public void read(View v)
    {  myFile=null;
        try {
            String fname = FileName.getText().toString();
            //  txtData.setText(fname);

            if(fname.length()!=0){
                //  create file for external storage
                 myFile = new File(Environment.getExternalStorageDirectory() + File.separator + fname);

                if(myFile.exists()){

                    FileInputStream fIn = new FileInputStream(myFile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null) {
                        aBuffer += aDataRow + "\n";
                    }
                    txtData.setText(aBuffer);
                    myReader.close();
                    Toast.makeText(getBaseContext(),
                            "Done reading  ",
                            Toast.LENGTH_LONG).show();
                }
                else {Toast.makeText(getBaseContext(),
                        "Enter Correct file name  ",
                        Toast.LENGTH_LONG).show();
                }
            }// if file name not entered
            else{
                Toast.makeText(getBaseContext(), "enter file name", Toast.LENGTH_LONG).show();
                FileName.requestFocus();

            }

        } catch (Exception e) {
            Toast.makeText(getBaseContext(),"in read method" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        } // end of catch


    }
}
