package com.example.week4_1;

public class WeatherDataItem {
    public float mTemperature = 0.0f;
    public float mWindSpeed = 0.0f;
    public String mDescription = "Clear";

    public WeatherDataItem(float aTemperature, float aWind, String aDescription){
        mTemperature = aTemperature;
        mWindSpeed = aWind;
        mDescription = aDescription;
    }
}
