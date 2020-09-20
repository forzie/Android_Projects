package com.example.kaivanshah.testnavdrawer;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {
    TextView textView;
    TextView textView1;

    public ContactFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        textView = (TextView) view.findViewById(R.id.textView6);
        textView1 = (TextView) view.findViewById(R.id.textView7);

        textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:8401888243"));

                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
//                Log.d("Test", "onClickListener ist gestartet");
//                Toast.makeText(getActivity().getApplicationContext(), "Test", Toast.LENGTH_LONG).show();
            }
        });

        textView1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View InputFragmentView)
            {
                Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "contact@silverwingtechnologies.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry");
                startActivity(intent);
//                Log.d("Test", "onClickListener ist gestartet");
//                Toast.makeText(getActivity().getApplicationContext(), "Test", Toast.LENGTH_LONG).show();
            }
        });

        return view;

    }
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Contact Us");
    }


}
