package com.example.week4_1;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherManager {
    private static WeatherManager instance;

    private WeatherDataItem currentWeather;
    private List<WeatherDataItem> weatherForecastList = new ArrayList<WeatherDataItem>();

    private String urlString = "https://api.openweathermap.org/data/2.5/forecast?q=Tampere&units=metric&appid=6c433438776b5be4ac86001dc88de74d";
    private RequestQueue requestQueue;
    private static Context context;


    private WeatherManager(Context context){
        // Haetaan data heti, kun manager luodaan
        requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlString,
                response -> {
                    Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                    parseJsonAndUpdate (response);
                },
                error -> {

                });
        requestQueue.add(stringRequest);
    } // ei voida luoda new:llä (singleton)

    private void parseJsonAndUpdate(String response) {
        try {
            if(weatherForecastList != null) {
                weatherForecastList.clear();
            }
            JSONObject rootObject = new JSONObject(response);
            JSONArray weatherForecastJsonArray = rootObject.getJSONArray("list");
            for( int i = 0; i<weatherForecastJsonArray.length(); i++){
                JSONObject weatherItem = weatherForecastJsonArray.getJSONObject(i);
                float temperature = (float) weatherItem.getJSONObject("main").getDouble("temp");
                float windSpeed = (float) weatherItem.getJSONObject("wind").getDouble("speed");
                String condition = weatherItem.getJSONArray("weather").getJSONObject(0).getString("main");
                weatherForecastList.add( new WeatherDataItem(temperature, windSpeed, condition));
                System.out.println(temperature + windSpeed + condition);
            }

        }catch (JSONException e){
            Toast.makeText(context, "Json parsing error", Toast.LENGTH_LONG).show();
            Log.e("WEATHER_MANAGER", "Json exception");
        }

    }

    public static synchronized WeatherManager getInstance(Context context){
        if(instance == null){
            instance = new WeatherManager(context);
        }
        return instance;
    }

    public List<WeatherDataItem> getWeatherForecast(){
        return weatherForecastList;
    }

    public WeatherDataItem getCurrentWeather(){
        //return new WeatherDataItem(-5.2f, 3f, "Sunny");
        return currentWeather;
    }
}
