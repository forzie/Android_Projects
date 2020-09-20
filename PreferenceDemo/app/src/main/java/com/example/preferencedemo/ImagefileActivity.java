package com.example.preferencedemo;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class ImagefileActivity extends AppCompatActivity {
Button b;
Bitmap imageBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagefile);
        b = findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // if we dont have permission camera
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                } else  {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 10);
                }

            }
        });
    }  // end of on create

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try {
            if (requestCode == 100) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 10);
                }
            } else if (requestCode == 200) {

                //    mImageView.setImageBitmap(imageBitmap);
                File myFile = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
                FileOutputStream stream = new FileOutputStream(myFile);
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                stream.close();
                Toast.makeText(getBaseContext(), "file saved",Toast.LENGTH_SHORT).show();


            }
        }catch(Exception e){
         Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode==10)
        {  Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");

            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                try{
                    //    mImageView.setImageBitmap(imageBitmap);
                    File myFile = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
                    Toast.makeText(getBaseContext(), myFile.getAbsolutePath(),Toast.LENGTH_SHORT).show();
                    FileOutputStream stream = new FileOutputStream(myFile);
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    stream.close();
                    Toast.makeText(getBaseContext(), "file saved",Toast.LENGTH_SHORT).show();
                }catch(Exception e){}

            }
            else
            { requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},200);

            }
        }
    }


    public void open(View v){
        try{
            File  myFile = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
            if(myFile.exists())
            {
                Bitmap myBitmap = BitmapFactory.decodeFile(myFile.getAbsolutePath());
                ImageView myImage =  findViewById(R.id.imageView1);
                myImage.setImageBitmap(myBitmap);
            }
            else {
                Toast.makeText(v.getContext(),"no IMAGE IS PRESENT'",Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){}
    }

}