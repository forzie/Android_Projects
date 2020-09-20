package com.example.kaivanshah.testnavdrawer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Silverwing on 4/9/2017.
 */

public class Imageadd extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;


    public Imageadd(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return 0;
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
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);
        TextView packageId = (TextView)vi.findViewById(R.id.packageId);
        TextView title = (TextView)vi.findViewById(R.id.packageName); // title
       /* TextView artist = (TextView)vi.findViewById(R.id.packageDes); // artist name
        TextView duration = (TextView)vi.findViewById(R.id.packagePrice); // duration
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); */// thumb image

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        // Setting all values in listview

       /* artist.setText(song.get(CategoryFragment.packageDec));
        duration.setText(song.get(CategoryFragment.packageRate));
*/
        return vi;
    }
}
