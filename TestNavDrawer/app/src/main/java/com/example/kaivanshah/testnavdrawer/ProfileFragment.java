package com.example.kaivanshah.testnavdrawer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private static final int RESULT_OK = 1;
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    private ImageView imageView;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.ProfilePic);

        String profile = (String) AppPersistance.start(getActivity()).get(AppPersistance.keys.IMAGE_URL);
        Picasso.with(getActivity())
                .load("http://192.168.0.101/testnav/img/"+profile)
                .placeholder(R.drawable.placeholder)   // optional
                .error(R.drawable.error)      // optional
                .resize(400,400)                        // optional
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to Open Image applications like Gallery, Google Photos
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });
        return view;
    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle("Profile");
    }
}
