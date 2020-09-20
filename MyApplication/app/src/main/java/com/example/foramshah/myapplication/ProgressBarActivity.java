package com.example.foramshah.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProgressBarActivity extends AppCompatActivity {
    int progress_status=1;
    ProgressBar pb;
    TextView tv;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        pb=findViewById(R.id.progressBar);
        tv=findViewById(R.id.textView);
        Thread t=new Thread(new Task());
        t.start();
        handler=new Handler();

    }

    private class Task implements Runnable {

        @Override
        public void run() {
            while(progress_status<=100)
            {
                progress_status++;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        pb.setProgress(progress_status);
                        tv.setText(pb.getProgress()+"%");
                        if(progress_status==100)
                        {
                            Toast.makeText(getApplicationContext(),"Progress Completed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                try{
                    Thread.sleep(200);
                }
                catch (Exception e){}
            }
        }
    }
}
