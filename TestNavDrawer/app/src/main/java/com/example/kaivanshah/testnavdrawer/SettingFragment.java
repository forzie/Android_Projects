package com.example.kaivanshah.testnavdrawer;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    private ListView listView;
    String[] arra = {"Faq","Profile","Faq","Profile","Faq","Profile","Faq","Profile","Faq","Profile","Faq","Profile"};

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        listView = (ListView) view.findViewById(R.id.ltview);
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(),R.layout.item_lists,arra);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pid = ((TextView) view.findViewById(R.id.label)).getText().toString();
                Snackbar.make(view,pid,Snackbar.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle("Setting");
    }
}
