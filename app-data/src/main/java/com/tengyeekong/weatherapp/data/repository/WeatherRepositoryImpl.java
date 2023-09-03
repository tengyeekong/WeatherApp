package com.tengyeekong.weatherapp.data.repository;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tengyeekong.weatherapp.data.api.HttpRequestUtil;
import com.tengyeekong.weatherapp.domain.BuildConfig;
import com.tengyeekong.weatherapp.domain.entity.ForecastEntity;
import com.tengyeekong.weatherapp.domain.entity.WeatherEntity;
import com.tengyeekong.weatherapp.domain.repository.WeatherRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

public class WeatherRepositoryImpl extends WeatherRepository {

    private final String BASE_URL = "http://api.openweathermap.org/data/2.5";
    private final String KEY_LOCATIONS  = "KEY_LOCATIONS";

    private final HttpRequestUtil httpRequestUtil;
    private final Gson              gson;
    private final SharedPreferences sharedPreferences;

    @Inject
    WeatherRepositoryImpl(HttpRequestUtil httpRequestUtil,
                          Gson gson,
                          SharedPreferences sharedPreferences) {
        this.httpRequestUtil = httpRequestUtil;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public List<String> getLocations() {
        return new ArrayList<>(sharedPreferences.getStringSet(KEY_LOCATIONS, new HashSet<>()));
    }

    @Override
    public void addLocation(double lat, double lon, String name) {
        HashSet<String> locations = new HashSet<>(sharedPreferences.getStringSet(KEY_LOCATIONS, new HashSet<>()));
        for (String location : locations) {
            if (location.startsWith(lat + "," + lon)) return;
        }
        locations.add(lat + "," + lon + "," + name);
        sharedPreferences.edit().putStringSet(KEY_LOCATIONS, locations).apply();
    }

    @Override
    public void removeLocation(double lat, double lon) {
        HashSet<String> locations = new HashSet<>(sharedPreferences.getStringSet(KEY_LOCATIONS, new HashSet<>()));
        for (String location : locations) {
            if (location.startsWith(lat + "," + lon)) {
                locations.remove(location);
                sharedPreferences.edit().putStringSet(KEY_LOCATIONS, locations).apply();
                return;
            }
        }
    }

    @Override
    public void removeAllLocations() {
        sharedPreferences.edit().remove(KEY_LOCATIONS).apply();
    }

    @Override
    public WeatherEntity getWeather(double lat, double lon) {
        final ArrayList<String> requestData = new ArrayList<>();
        requestData.add("appid=" + BuildConfig.API_KEY);
        requestData.add("lat=" + lat);
        requestData.add("lon=" + lon);
        requestData.add("units=metric");
        String response = httpRequestUtil.getRequest(BASE_URL + "/weather", requestData);
        return gson.fromJson(response, WeatherEntity.class);
    }

    @Override
    public ForecastEntity getForecast(double lat, double lon) {
        final ArrayList<String> requestData = new ArrayList<>();
        requestData.add("appid=" + BuildConfig.API_KEY);
        requestData.add("lat=" + lat);
        requestData.add("lon=" + lon);
        requestData.add("units=metric");
        String response = httpRequestUtil.getRequest(BASE_URL + "/forecast", requestData);
        return gson.fromJson(response, ForecastEntity.class);
    }
}
