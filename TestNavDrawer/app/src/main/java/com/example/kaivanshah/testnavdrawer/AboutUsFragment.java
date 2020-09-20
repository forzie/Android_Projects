package com.example.kaivanshah.testnavdrawer;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {


    private WebView mWebview;
    private Dialog mProgress;

    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        mWebview = (WebView) view.findViewById(R.id.webview_about_us);
        mWebview.setWebViewClient(new MyWebviewClient());
        WebSettings mWebsettings = mWebview.getSettings();
        mWebsettings.setJavaScriptEnabled(true);
        mWebview.loadUrl("http://www.google.com");
        return view;
    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle("About Us");
    }

    private class MyWebviewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //mProgress = new ProgressDialog(context);
            if(mProgress == null){
                mProgress = ProgressDialog.show(getActivity(), null, "Loading...");
                mProgress.setCancelable(true);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
//            mProgress.dismiss();
//            super.onPageFinished(view, url);
            super.onPageFinished(view, url);
            if(mProgress.isShowing())
                mProgress.dismiss();
        }
    }
}