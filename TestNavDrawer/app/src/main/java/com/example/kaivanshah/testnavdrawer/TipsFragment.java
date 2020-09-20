package com.example.kaivanshah.testnavdrawer;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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
public class TipsFragment extends Fragment {

    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> faqList;
    protected static String url_all_faq = Server.ip1+"get_category.php";
    private ListView mList;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_TIPS = "tips";
    private static final String TAG_TIPID = "tipID";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";
    JSONArray faq = null;

    public TipsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tips, container, false);
        mList = (ListView) view.findViewById(R.id.ltview1);
        faqList = new ArrayList<>();

        new LoadAllFaq().execute();
        return view;
    }
    private class LoadAllFaq extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<>();
            JSONObject json = jParser.makeHttpRequest(url_all_faq, "GET", params);

            try {

                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    faq = json.getJSONArray(TAG_TIPS);

                    for (int i = 0; i < faq.length(); i++) {
                        JSONObject c = faq.getJSONObject(i);

                        String id = c.getString(TAG_TIPID);
                        String question = c.getString(TAG_TITLE);
                        String answer = c.getString(TAG_DESCRIPTION);

                        HashMap<String, String> map = new HashMap<>();
                        map.put(TAG_TIPID, id);
                        map.put(TAG_TITLE, question);
                        map.put(TAG_DESCRIPTION, answer);

                        faqList.add(map);
                    }
                } else {

                    Toast.makeText(getActivity(), "Tips not found", Toast.LENGTH_LONG).show();
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
                            R.layout.list_tips, new String[] { TAG_TIPID,TAG_TITLE,TAG_DESCRIPTION},
                            new int[] { R.id.id, R.id.question,R.id.answer});
                    mList.setAdapter(adapter);
                }
            });
        }
    }
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Tips");
    }

}
