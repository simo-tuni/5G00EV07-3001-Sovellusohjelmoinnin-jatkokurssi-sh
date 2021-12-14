package com.example.week3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        // Rekisteröidään BroadcastReceiver kuuntelemaan taustalla laturin kytkentää
        MyChargerListener chargerListener = new MyChargerListener();
        IntentFilter chargerConnectedFilter = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        registerReceiver(chargerListener, chargerConnectedFilter);
         */

        // Luodaan oma BroadcastReceiver
        receiver = new MyReceiver();
        IntentFilter chargerConnectedFilter = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        registerReceiver(receiver, chargerConnectedFilter);
        MyChargerListener chargerListener = new MyChargerListener();
        IntentFilter chargerConnectedFilter2 = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        registerReceiver(chargerListener, chargerConnectedFilter2);
        MyChargerListener chargerListener2 = new MyChargerListener();
        IntentFilter chargerConnectedFilter3 = new IntentFilter(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(chargerListener, chargerConnectedFilter3);

    }

    public void openLottoActivity(View view){
        startActivity(new Intent(this, LottoActivity.class));
    }

    // niinsanottu inner class
    // inner class näkee isäntäolion toiminnot jne.
    // Aktiviteetin sisäinen broadcastReceiver
    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // Täältä pääsee käsiksi aktiviteetin UI:hin, koska tämä on ns. inner class
            TextView chargerStatusTextView = (TextView) findViewById(R.id.chargerStatusTextView);
            chargerStatusTextView.setText("Laturi kytketty");
        }
    }


}