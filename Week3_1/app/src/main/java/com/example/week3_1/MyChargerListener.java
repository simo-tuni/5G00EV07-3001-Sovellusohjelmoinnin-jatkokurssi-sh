package com.example.week3_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyChargerListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Tänne tulee eventit, kun event tapahtuu
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            // Laturi kytkettiin
            Toast.makeText(context, "Laturi kytkettiin", Toast.LENGTH_LONG).show();
            Log.d("MY_CHARGER_LISTENER", "Charger connected");
        }
        else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            Toast.makeText(context, "Laturi irroitettiin", Toast.LENGTH_LONG).show();
            Log.d("MY_CHARGER_LISTENER", "Charger disconnected");
        }
        /*
        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        */
    }
}