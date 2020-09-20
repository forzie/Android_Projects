package com.example.kaivanshah.testnavdrawer;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePassword extends Fragment {
    JSONParser jsonParser = new JSONParser();
    ArrayAdapter<String> adapter, adapt;
    private Spinner spin, spinner;
    private EditText  password, oldPassword;
    private Button button;
    private String  password1, oldPassword1;
    protected static String url_registration = Server.ip1+"changePassword.php";
    private static final String TAG_SUCCESS = "success";

    public ChangePassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        oldPassword = (EditText) view.findViewById(R.id.oldPassword);
        password = (EditText) view.findViewById(R.id.password1);
        button = (Button) view.findViewById(R.id.passBtn);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
                initial();
                if (!validate()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                } else {
//                    Toast.makeText(getActivity().getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    new CreateRegistration().execute();
                }

            }
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle("Change Password");
    }

    private class CreateRegistration extends AsyncTask<String, String,String>
    {

        String password2 = password.getText().toString();
        String oldpassword2 = oldPassword.getText().toString();
        String userid2 = (String) AppPersistance.start(getActivity()).get(AppPersistance.keys.USER_ID);


        @Override
        protected String doInBackground(String... arg0) {

            List<NameValuePair> params = new ArrayList<>();

            params.add(new BasicNameValuePair("password", password2));
            params.add(new BasicNameValuePair("oldpassword", oldpassword2));
            params.add(new BasicNameValuePair("user_id", userid2));

            JSONObject json = jsonParser.makeHttpRequest(url_registration, "POST", params);

            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    successData();
//                    Toast.makeText(getActivity().getApplicationContext(), "Password Change Successfully", Toast.LENGTH_LONG).show();
                }
                else if (success == 2) {
                    missmatch();
                }
                else {
                    nodata();
//                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void successData() {

            Snackbar.make(getView(),"Password Change Successfully",Snackbar.LENGTH_SHORT).show();
        }
        public void missmatch() {

            Snackbar.make(getView(),"Old Password Wrong",Snackbar.LENGTH_SHORT).show();
        }
            public void nodata() {
        Snackbar.make(getView(),"Error",Snackbar.LENGTH_SHORT).show();
        }

    }
    private void initial() {

        password1 = password.getText().toString().trim();
        oldPassword1 = oldPassword.getText().toString().trim();
    }
    private boolean validate() {
        boolean valid = true;

        if (password1.isEmpty() || password1.length() <= 3 || password1.length() > 32) {
            password.setError("Please Enter A Valid Password");
            valid = false;
        }
        if (oldPassword1.isEmpty() || oldPassword1.length() <= 1 || oldPassword1.length() > 32) {
            oldPassword.setError("Please Enter Old Password");
            valid = false;
        }



        return valid;
    }



}
