package com.example.kaivanshah.testnavdrawer;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoryFragment extends Fragment {

    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> faqList;
    private ListView mList;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CATEGORY = "subcategory";
    private static final String TAG_CATID = "subCatID";
    private static final String TAG_NAME = "subCatName";
    JSONArray faq = null;
    String value;

    public SubCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_category, container, false);
        value = getArguments().getString("catid");
//        Toast.makeText(getActivity(),value,Toast.LENGTH_SHORT).show();
        mList = (ListView) view.findViewById(R.id.list2);
        faqList = new ArrayList<>();

        new LoadAllFaq().execute();
        return view;
    }
    private class LoadAllFaq extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<>();
            JSONObject json = jParser.makeHttpRequest(Server.ip1+"get_subCategory.php?catID="+value, "GET", params);

            try {

                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    faq = json.getJSONArray(TAG_CATEGORY);

                    for (int i = 0; i < faq.length(); i++) {
                        JSONObject c = faq.getJSONObject(i);

                        String id = c.getString(TAG_CATID);
                        String question = c.getString(TAG_NAME);
                        //String answer = c.getString(TAG_ANSWER);

                        HashMap<String, String> map = new HashMap<>();
                        map.put(TAG_CATID, id);
                        map.put(TAG_NAME, question);
                        //map.put(TAG_ANSWER, answer);

                        faqList.add(map);
                    }
                } else {
                    nodata();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {

            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    ListAdapter adapter = new SimpleAdapter(
                            getActivity(), faqList,
                            R.layout.list_subcat, new String[] { TAG_CATID,TAG_NAME},
                            new int[] { R.id.subcatid, R.id.subcatname});
                    mList.setAdapter(adapter);
                }
            });
        }
    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle("Sub Category");
    }
    public void nodata() {
        Snackbar.make(getView(),"No Sub Category Found",Snackbar.LENGTH_SHORT).show();
    }

}
