package com.example.foramshah.alarmservicedemo;


import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class MyBroadcastReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent inte = new Intent(context.getApplicationContext(),MainActivity.class);
        PendingIntent pi= PendingIntent.getActivity(context.getApplicationContext(), 0, inte,0);

        SmsManager sms= SmsManager.getDefault();
        sms.sendTextMessage("9033563246", null, "Hello! It's Foram Here...", pi,null);

    }
}
