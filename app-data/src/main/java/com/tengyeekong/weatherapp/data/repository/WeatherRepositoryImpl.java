package com.tengyeekong.weatherapp.data.repository;

import com.google.gson.Gson;
import com.tengyeekong.weatherapp.common.BuildConfig;
import com.tengyeekong.weatherapp.data.api.HttpRequestUtil;
import com.tengyeekong.weatherapp.domain.entity.ForecastEntity;
import com.tengyeekong.weatherapp.domain.entity.WeatherEntity;
import com.tengyeekong.weatherapp.domain.repository.WeatherRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class WeatherRepositoryImpl extends WeatherRepository {

    private final String baseUrl = "http://api.openweathermap.org/data/2.5";

    private final HttpRequestUtil httpRequestUtil;
    private final Gson            gson;

    @Inject
    WeatherRepositoryImpl(HttpRequestUtil httpRequestUtil, Gson gson) {
        this.httpRequestUtil = httpRequestUtil;
        this.gson = gson;
    }

    @Override
    public WeatherEntity getWeather(int lat, int lon) {
        final ArrayList<String> requestData = new ArrayList<>();
        requestData.add("appid=" + BuildConfig.API_KEY);
        requestData.add("lat=" + lat);
        requestData.add("lon=" + lon);
        requestData.add("units=metric");
        String response = httpRequestUtil.getRequest(baseUrl + "/weather", requestData);
        return gson.fromJson(response, WeatherEntity.class);
    }

    @Override
    public ForecastEntity getForecast(int lat, int lon) {
        final ArrayList<String> requestData = new ArrayList<>();
        requestData.add("appid=" + BuildConfig.API_KEY);
        requestData.add("lat=" + lat);
        requestData.add("lon=" + lon);
        requestData.add("units=metric");
        String response = httpRequestUtil.getRequest(baseUrl + "/forecast", requestData);
        return gson.fromJson(response, ForecastEntity.class);
    }
}
