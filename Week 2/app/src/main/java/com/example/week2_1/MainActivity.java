package com.example.week2_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createTextMessage(View view) {
        String message = "Test message";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        String smsNumber = String.format("smsto: +358401234567");
        intent.setData(Uri.parse(smsNumber));
        intent.putExtra("sms_body", message);
        startActivity(intent);
    }

    public void openMap(View view) {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 61.503934, 23.8103385);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    public void setAlarm(View view) {
        String message = "Hi";
        int hour = 16;
        int minutes = 00;
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
            .putExtra(AlarmClock.EXTRA_MESSAGE, message)
            .putExtra(AlarmClock.EXTRA_HOUR, hour)
            .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void callNumber(View view) {
        // Tarkistetaan, onko käyttäjä antanut oikeuden soittaa ja hallinnoida puheluita
        // Jos ei, pyydetään oikeus. Jos ei käyttäjä anna oikeutta, palataan.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 0);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL);
        EditText editText = (EditText) findViewById(R.id.editTextNumber);
        String number_to_call = editText.getText().toString();
        intent.setData(Uri.parse("tel:" + number_to_call));
        if(intent.resolveActivity((getPackageManager())) != null) {
            startActivity(intent);
        }
    }

    public void trackUser(View view) {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if ((ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                && (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)){
                if((ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                    return;
                 }
                if((ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
                    return;
                }
        }
        Location currentLocation  = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        TextView textView = (TextView) findViewById(R.id.gpsTextView);
        textView.setText("LAT: " + currentLocation.getLatitude() + " LONg: " + currentLocation.getLongitude());
    }
}