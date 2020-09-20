package com.cs442.group3.citysbest;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cs442.group3.citysbest.Client.BaseActivity;
import com.cs442.group3.citysbest.Database.CategoryTable;
import com.cs442.group3.citysbest.Database.ProductTable;
import com.cs442.group3.citysbest.Database.Utils;

import java.io.InputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AddCategory extends BaseActivity {

    byte[] inputData;
    @InjectView(R.id.add_category_name1)
    EditText c_name;
    @InjectView(R.id.category_add_image1)
    ImageView _imgView;
    @InjectView(R.id.btn_category1)
    Button add;
    int s_id;
    private static int RESULT_LOAD_IMG1 = 1;
    Uri selectedImage;
    CategoryTable categorytable;
    int p_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_category);
        getLayoutInflater().inflate(R.layout.activity_add_category, frameLayout);
        ButterKnife.inject(this);
        categorytable=new CategoryTable(this);
        categorytable.open();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    onSignupFailed();
                    return;
                }
                add.setEnabled(false);
                String c_name1=c_name.getText().toString();
                InputStream iStream = null;
                try {
                    iStream = getContentResolver().openInputStream(selectedImage);
                    inputData = Utils.getBytes(iStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                categorytable.AddNew(c_name1,inputData);
                add.setEnabled(true);
                Toast.makeText(getApplicationContext(), "CategoryAdded",
                        Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),AddCategory.class);
                startActivity(i);
            }
        });
    }

    public void loadImageFromGallery(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == RESULT_LOAD_IMG1 && resultCode == RESULT_OK
                    && null != data) {
                selectedImage = data.getData();
                //ImageView imgView = (ImageView) findViewById(R.id.v_edit_product_image1);
                // Drawable myDrawable = getResources().getDrawable(R.drawable.logo);
                // Uri uri = Uri.parse("android.resource://com.cs442.group3.citysbest/drawable/logo");
                // Mohsin: change to setImageURI(selectedImage) after testing
                _imgView.setImageURI(selectedImage);
            }
            else
            {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();

//                // Mohsin: remove these lines
//                Uri uri = Uri.parse("android.resource://com.cs442.group3.citysbest/drawable/logo");
//                selectedImage = uri;
//                _imgView.setImageURI(uri);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

    public void onSignupFailed() {
        add.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String name = c_name.getText().toString();



        if (name.isEmpty()) {
            c_name.setError("Store Name is required");
            valid = false;
        } else {
            c_name.setError(null);
        }

        if (selectedImage ==null  ) {
            Toast.makeText(this, "Please Select a Image", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {

        }
        return valid;
    }

}
