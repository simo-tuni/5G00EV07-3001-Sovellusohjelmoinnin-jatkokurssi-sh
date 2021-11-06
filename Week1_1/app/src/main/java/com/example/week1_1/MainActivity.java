package com.example.week1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private boolean torchOn = false;
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        ReadSensors();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void TorchOnOff(View view) {
        //If torch is off, turn it on, if it is on, turn it off
        //1. Connect to CameraManager
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            // Loop through all cameras and read their characteristics
            for ( String id : cameraManager.getCameraIdList() ){
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics( id );
                if( cameraCharacteristics.get( cameraCharacteristics.FLASH_INFO_AVAILABLE)){
                    torchOn = !torchOn;
                    cameraManager.setTorchMode(id, torchOn);
                    Button button = findViewById(R.id.button);
                    if(torchOn){
                        button.setText(R.string.turn_light_off);
                    }
                    else button.setText(R.string.turn_light_on);
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void ReadSensors() {
        //Register to listen to sensors
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList= sensorManager.getSensorList(Sensor.TYPE_ALL);
        /*
        for( Sensor s : sensorList) {
            Toast.makeText(this, s.getName(), Toast.LENGTH_SHORT).show();
        }
        */
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer != null)  {
            //Register to listen to the sensor
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float xSensorValue = event.values[0];
        float ySensorValue = event.values[1];
        float zSensorValue = event.values[2];
        TextView sensorTextview = findViewById(R.id.sensorTextView);
        sensorTextview.setText("X:" + xSensorValue + " Y: " + ySensorValue + " Z: " + zSensorValue);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}