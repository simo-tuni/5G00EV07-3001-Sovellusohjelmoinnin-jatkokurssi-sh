package com.example.week4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WeatherManager weatherManager;
    private List<HashMap<String, String>> weatherDataHashItemList = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherManager = WeatherManager.getInstance(getApplicationContext());
    }

    public void getWeather(View view) {
        List<WeatherDataItem> forecast = weatherManager.getWeatherForecast();

        WeatherDataItem weatherData = new WeatherDataItem(-16, 3, "Sunny");


        for (int i = 0; i<forecast.size(); i++){
            HashMap<String, String> weatherDataHashItem = new HashMap<>();
            weatherDataHashItem.put("DESCRIPTION", forecast.get(i).mDescription);
            weatherDataHashItem.put("TEMPERATURE", "" + forecast.get(i).mTemperature + "C");
            weatherDataHashItem.put("WIND", "" + forecast.get(i).mWindSpeed + "m/s");
            weatherDataHashItemList.add(weatherDataHashItem);
        }

        ListView forecastListView = findViewById(R.id.weatherListView);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                weatherDataHashItemList,
                R.layout.forecast_list_item,
                new String[]{"DESCRIPTION", "TEMPERATURE", "WIND"},
                new int[] {R.id.descriptionTextView, R.id.temperatureTextView, R.id.windTextView}
                );

        forecastListView.setAdapter(simpleAdapter);
    }
}