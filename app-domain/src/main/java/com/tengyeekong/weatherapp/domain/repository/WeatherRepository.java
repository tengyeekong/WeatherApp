package com.tengyeekong.weatherapp.domain.repository;

import com.tengyeekong.weatherapp.domain.entity.ForecastEntity;
import com.tengyeekong.weatherapp.domain.entity.WeatherEntity;

public abstract class WeatherRepository {
    public abstract WeatherEntity getWeather(int lat, int lon);
    public abstract ForecastEntity getForecast(int lat, int lon);
}
