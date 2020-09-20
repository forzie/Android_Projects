package com.example.kaivanshah.testnavdrawer;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.example.kaivanshah.testnavdrawer.JSONParser.jObj;

public class Forgot extends AppCompatActivity {
    private EditText editText1;
    private String email1;
    public static final String USER_NAME = "Emailid";
    private static final String TAG_SUCCESS = "success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot2);

        editText1 = (EditText) findViewById(R.id.input_email);

        findViewById(R.id.btn_forgot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initial();
                if (!validate()) {
                    Toast.makeText(getApplicationContext(), "Enter Email Address", Toast.LENGTH_SHORT).show();
                } else {
                    login(email1);
                }
            }
        });
        findViewById(R.id.link_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(forgot);
                finish();
            }
        });
    }
    private void initial() {
        email1 = editText1.getText().toString().trim();
    }
    private boolean validate() {
        boolean valid;
        valid = true;
        if (email1.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            editText1.setError("Please Enter A Valid Email");
            editText1.setFocusable(true);
            valid = false;
        }

        return valid;
    }

    private void login(final String emailid) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Forgot.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String emailid = params[0];
                //name = params[2];

                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("emailid", emailid));
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            Server.ip1+"forgot.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();


                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                try {
                    jObj = new JSONObject(result);
                } catch (JSONException e) {
                    Log.e("JSON Parser", "Error parsing data " + e.toString());
                }
                return result;
            }
            @Override
            protected void onPostExecute(String result){
                loadingDialog.dismiss();
                String total = jObj.optString(TAG_SUCCESS);
                if (total.equals("success")) {
                    Toast.makeText(getApplicationContext(), "Password Send to your Email", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Forgot.this, LoginActivity.class);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_LONG).show();
//                    nodata();
                }
            }
        }
        LoginAsync la = new LoginAsync();
        la.execute(emailid);
    }
//    public void nodata() {
//        Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_LONG).show();
//    }
}
