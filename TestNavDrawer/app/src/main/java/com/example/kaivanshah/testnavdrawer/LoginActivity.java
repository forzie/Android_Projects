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

import static com.example.kaivanshah.testnavdrawer.AppPersistance.keys.USER_ID;
import static com.example.kaivanshah.testnavdrawer.R.id.email;

public class LoginActivity extends AppCompatActivity {

    JSONParser jsonParser = new JSONParser();
    JSONObject jObj;
    private EditText editText1, editText2;
    private String email1, password1;
    public static final String USER_NAME = "Emailid";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_NAME = "name";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_USER_ID = "user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        editText1 = (EditText) findViewById(email);
        editText2 = (EditText) findViewById(R.id.password);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initial();
                if (!validate()) {
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                } else {
                    login(email1,password1);
                }
            }
        });
        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(signup);
            }
        });
        findViewById(R.id.forgot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot = new Intent(getApplicationContext(), Forgot.class);
                startActivity(forgot);
                finish();
            }
        });
        //Log.d("Auth","Printed in log");
    }
    private void initial() {
        email1 = editText1.getText().toString().trim();
        password1 = editText2.getText().toString().trim();
    }
    private boolean validate() {
        boolean valid;
        valid = true;
        if (email1.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            editText1.setError("Please Enter A Valid Email");
            editText1.setFocusable(true);
            valid = false;
        }
        if (password1.isEmpty() || password1.length() <= 4 || password1.length() > 32) {
            editText2.setError("Please Enter A Valid Password");
            valid = false;
        }
        return valid;
    }
    public void onBackPressed() {
        super.onBackPressed();
    }
    private void login(final String emailid, String password) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(LoginActivity.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String emailid = params[0];
                String pass = params[1];
                //name = params[2];

                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("emailid", emailid));
                nameValuePairs.add(new BasicNameValuePair("password", pass));
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(Server.ip1+"login.php");
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
                    String q = jObj.optString(TAG_NAME);
                    String p = jObj.optString(TAG_PROFILE);
                    String r = jObj.optString(TAG_USER_ID);
                    AppPersistance.start(LoginActivity.this).save(AppPersistance.keys.USER_NAME,q);
                    AppPersistance.start(LoginActivity.this).save(AppPersistance.keys.IMAGE_URL,p);
                    AppPersistance.start(LoginActivity.this).save(AppPersistance.keys.EMAIL,email1);
                    AppPersistance.start(LoginActivity.this).save(USER_ID,r);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(USER_NAME, emailid);
                    intent.putExtra("profile", p);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_LONG).show();
                }
            }
        }
        LoginAsync la = new LoginAsync();
        la.execute(emailid, password);
    }
}

//success{"success":1,"name":"kaivan"}
