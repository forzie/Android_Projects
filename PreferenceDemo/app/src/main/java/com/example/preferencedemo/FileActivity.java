package com.example.preferencedemo;

import android.os.Environment;
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

public class FileActivity extends AppCompatActivity {
    EditText txtData,FileName;
    Button btnWriteSDFile;
    Button btnReadSDFile;
    Button btnClearScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        txtData = (EditText) findViewById(R.id.txtData);
        txtData.setHint("Enter some lines of data here...");
        FileName = (EditText) findViewById(R.id.filename);
        btnWriteSDFile = (Button) findViewById(R.id.btnWriteSDFile);
        btnReadSDFile = (Button)findViewById(R.id.btnReadSDFile);
        btnClearScreen = (Button)findViewById(R.id.btnClearScreen);

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
            File myFile=null;
            String temp = FileName.getText().toString();
            if(temp==null)
            {
                Toast.makeText(getBaseContext(), "enter file name",Toast.LENGTH_LONG).show();
                FileName.requestFocus();
            }
            String fname=null;
            if(temp.contains("/")){
                String dirname=temp.substring(0, temp.indexOf("/"));
                fname = temp.substring(temp.indexOf("/")+1, temp.length());
                //Writting Data to intenal memory
             	 File dirFile =  new File(getApplicationContext().getFilesDir() + File.separator + dirname);
				 dirFile.mkdir();
				 myFile = new File(getApplicationContext().getFilesDir() + File.separator + dirname+ File.separator+fname);
            }
            else{
                fname = temp;
                 myFile = new File(getApplicationContext().getFilesDir() + File.separator + fname);
            }
            // Writting Data to internal Storage
             myFile = new File(getApplicationContext().getFilesDir(),fname);

            myFile.createNewFile();

            FileOutputStream fOut = new FileOutputStream(myFile,true);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(txtData.getText().toString());
            myOutWriter.close();
            fOut.close();
            Toast.makeText(getBaseContext(),
                    "Done writing  ",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "in write method " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        } // end of catch

    } // end of write


    public void read(View v)
    {
        try {
            String fname = FileName.getText().toString();
            //  txtData.setText(fname);

            if(fname.length()!=0){
                // for internal storage
                  File myFile = new File(getApplicationContext().getFilesDir(),fname);

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
            }
            else{
                Toast.makeText(getBaseContext(), "enter file name", Toast.LENGTH_LONG).show();
                FileName.requestFocus();

            }

        } catch (Exception e) {
            Toast.makeText(getBaseContext(),"in read method" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        } // end of catch


    }// end of read



}
