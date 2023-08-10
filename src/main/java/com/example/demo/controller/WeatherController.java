package com.example.demo.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(path = "/api/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
   
    public ResponseEntity<String> getWeather(@RequestParam String city, @RequestParam String unit) {
        weatherService.setCityName(city);
        weatherService.setUnit(unit);

        try {
            JSONObject weatherData = weatherService.getWeather();
            String jsonString = weatherData.toString();

            return ResponseEntity.ok(jsonString);
        } catch (IOException e) {
            // Handle IOException, maybe return an error response
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error fetching weather data");
        }
    }

    @GetMapping(path = "/api/weather/details", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    
    public ResponseEntity<String> getWeatherDetails(@RequestParam String city, @RequestParam String unit) {
        weatherService.setCityName(city);
        weatherService.setUnit(unit);

        try {
            JSONObject weatherDetails = new JSONObject();
            String cityName = weatherService.getCityName();

            weatherDetails.put("city", cityName);
            weatherDetails.put("weather", weatherService.returnWeatherArray());
            weatherDetails.put("main", weatherService.returnMainObject());
            weatherDetails.put("wind", weatherService.returnWindObject());
            weatherDetails.put("sys", weatherService.returnSysObject());


            return ResponseEntity.ok(weatherDetails.toString());
        } catch (JSONException | IOException e) {
            // Handle exceptions, maybe return an error response
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error fetching weather details");
        }
    }
}
