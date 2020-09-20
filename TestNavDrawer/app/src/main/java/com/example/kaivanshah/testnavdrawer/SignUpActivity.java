dpackage com.example.kaivanshah.testnavdrawer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    JSONParser jsonParser = new JSONParser();
    ArrayAdapter<String> adapter, adapt;
    private Spinner spin, spinner;
    String[] role = {"User","Doctor"};
    String[] type = {"Male","Female"};
    private EditText name, email, password, mobile;
    private String name1, email1, password1, mobile1;
    protected static String url_registration = Server.ip1+"registration.php";
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        mobile = (EditText) findViewById(R.id.mobile);
        spin = (Spinner) findViewById(R.id.spinner2);
        spinner = (Spinner) findViewById(R.id.spinner);

        adapt = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, role);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapt);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initial();
                if (!validate()) {
                    Toast.makeText(getApplicationContext(), "SignUp Failed", Toast.LENGTH_SHORT).show();
                } else {
                    AppPersistance.start(SignUpActivity.this).save(AppPersistance.keys.USER_NAME,name1);
                    AppPersistance.start(SignUpActivity.this).save(AppPersistance.keys.EMAIL,email1);
                    Toast.makeText(getApplicationContext(), "Registraion Successfully, Login Now !", Toast.LENGTH_SHORT).show();
                    new CreateRegistration().execute();
                }
            }
        });
    }

    private class CreateRegistration extends AsyncTask<String, String,String>
    {
        String fullname = name.getText().toString();
        String emailid = email.getText().toString();
        String password2 = password.getText().toString();
        String mobile2 = mobile.getText().toString();
        String gender2 = spinner.getSelectedItem().toString();
        String role_id = spin.getSelectedItem().toString();

        @Override
        protected String doInBackground(String... arg0) {

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("roleID", role_id));
            params.add(new BasicNameValuePair("name", fullname));
            params.add(new BasicNameValuePair("gender", gender2));
            params.add(new BasicNameValuePair("email", emailid));
            params.add(new BasicNameValuePair("mobile", mobile2));
            params.add(new BasicNameValuePair("password", password2));

            JSONObject json = jsonParser.makeHttpRequest(url_registration, "POST", params);

            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(i);

                    finish();
                } else {

                    Toast.makeText(SignUpActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    private void initial() {
        name1 = name.getText().toString().trim();
        email1 = email.getText().toString().trim();
        password1 = password.getText().toString().trim();
        mobile1 = mobile.getText().toString().trim();
    }

    private boolean validate() {
        boolean valid = true;
        if (name1.isEmpty() || name1.length() <= 3 || name1.length() > 32) {
            name.setError("Please Enter A Valid Name");
            valid = false;
        }
        if (email1.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            email.setError("Please Enter A Valid Email");
            valid = false;
        }
        if (password1.isEmpty() || password1.length() <= 4 || password1.length() > 32) {
            password.setError("Please Enter A Valid Password");
            valid = false;
        }

        if (mobile1.isEmpty() || mobile1.length() < 10 || mobile1.length() > 10) {
            mobile.setError("PLease Enter a Valid Number");
            valid = false;
        }
        return valid;
    }
}