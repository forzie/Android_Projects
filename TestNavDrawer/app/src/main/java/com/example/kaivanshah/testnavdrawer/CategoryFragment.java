package com.example.kaivanshah.testnavdrawer;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
public class CategoryFragment extends Fragment {
    private ImageView imageView;
    Context context;
    //String[] mobileArray = {"Android", "IPhone", "WindowsMobile", "Blackberry","WebOS", "Ubuntu", "Windows7", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X", "Max OS X"};
    JSONParser jParser = new JSONParser();
    int num;

    String id1;

    ArrayList<HashMap<String, String>> faqList;
    protected static String url_all_faq = Server.ip1+"get_category.php";
    private ListView mList;
    List<String>  ImagesList;
    List<String>  namelist;
    List<String>  idlist;
    JSONArray faq = null;
    Dialog dialog;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        mList = (ListView) view.findViewById(R.id.list);
        context=getActivity();
        imageView = (ImageView) view.findViewById(R.id.img);


//        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(),R.layout.item_lists, mobileArray);
//        mList.setAdapter(adapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pid = ((TextView) view.findViewById(R.id.question)).getText().toString();
                String catid = ((TextView) view.findViewById(R.id.id)).getText().toString();
//                Snackbar.make(view,id+pid+catid,Snackbar.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("catid",catid);
                Fragment fragment = new SubCategoryFragment();
                fragment.setArguments(bundle);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.content_main,fragment);
                ft.commit();
            }
        });
        faqList = new ArrayList<>();

        new LoadAllFaq().execute();
        return view;
    }
    private class LoadAllFaq extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(getActivity(), "Please wait", "Loading...");
        }

        @Override
        protected String doInBackground(String... args) {
            ImagesList= new ArrayList<String>();
            namelist= new ArrayList<String>();
            idlist= new ArrayList<String>();

            //dialog = ProgressDialog.show(getActivity(), "Please wait", "Loading...");
            List<NameValuePair> params = new ArrayList<>();
            JSONObject json = jParser.makeHttpRequest(url_all_faq, "GET", params);

            try {

                int success = json.getInt("success");
                num = success;

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    faq = json.getJSONArray("category");

                    for (int i = 0; i < faq.length(); i++) {
                        JSONObject c = faq.getJSONObject(i);
                        namelist.add(c.getString("name"));
                        idlist.add(c.getString("catID"));
                        ImagesList.add( Server.ip1 + "/img/" + c.getString("photo"));
                    }
                } else {

                    Toast.makeText(getActivity(), "Category not found", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {

            dialog.dismiss();

                    myadpt adpt= new myadpt();
                    mList.setAdapter(adpt);


        }
    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle("Category");
    }


    public class myadpt extends BaseAdapter{

        LayoutInflater lif;
         public myadpt(){
             lif=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         }
        @Override
        public int getCount() {
            return ImagesList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

                if ( convertView==null)
                {
                    convertView=lif.inflate(R.layout.list_faq,null);

                }
                ImageView faqimage=(ImageView) convertView.findViewById(R.id.img);
                Picasso.with(getActivity())
                    .load(ImagesList.get(position))
                    .placeholder(R.drawable.placeholder)   // optional
                    .error(R.drawable.error)      // optional
                    .into(faqimage);

            TextView tv= (TextView) convertView.findViewById(R.id.question);
            tv.setText(namelist.get(position));

            TextView tv1= (TextView) convertView.findViewById(R.id.id);
            tv1.setText(idlist.get(position));

            return convertView;
        }
    }

}
