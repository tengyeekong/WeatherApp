package com.tengyeekong.weatherapp.domain.repository;

import com.tengyeekong.weatherapp.domain.entity.ForecastEntity;
import com.tengyeekong.weatherapp.domain.entity.WeatherEntity;

import java.util.List;

public abstract class WeatherRepository {
    public abstract List<String> getLocations();
    public abstract void addLocation(double lat, double lon, String name);
    public abstract void removeLocation(double lat, double lon);
    public abstract WeatherEntity getWeather(double lat, double lon);
    public abstract ForecastEntity getForecast(double lat, double lon);
}
