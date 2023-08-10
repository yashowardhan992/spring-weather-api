package com.example.demo.controller;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

import java.io.IOException;

public class WeatherService {

    private OkHttpClient client;
    private Response response;
    private String CityName;
    String unit;

    private final String API = "83256463b39d35f2f297bad03ee128dc";

    public JSONObject getWeather() throws IOException {
        client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q="
                        +getCityName()+"&units="+getUnit()+"&appid=83256463b39d35f2f297bad03ee128dc")
                .build();

        try {
            response = client.newCall(request).execute();
            assert response.body() != null;
            return new JSONObject(response.body().string());
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    //Getting required data from Weather JSON API
    //JSON Objects and JSON Arrays


    public JSONArray returnWeatherArray() throws JSONException, IOException {
        JSONArray weatherJsonArray = getWeather().getJSONArray("weather");
        return weatherJsonArray;
    }

    public JSONObject returnMainObject() throws JSONException, IOException {
        JSONObject mainObject = getWeather().getJSONObject("main");
        return mainObject;
    }


    public JSONObject returnWindObject() throws JSONException, IOException {
        JSONObject wind = getWeather().getJSONObject("wind");
        return wind;
    }

    public JSONObject returnSysObject() throws JSONException, IOException {
        JSONObject sys = getWeather().getJSONObject("sys");
        return sys;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
