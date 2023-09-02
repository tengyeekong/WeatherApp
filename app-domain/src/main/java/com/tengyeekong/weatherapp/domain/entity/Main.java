package com.tengyeekong.weatherapp.domain.entity;

import java.io.Serializable;

public class Main implements Serializable {
    private Double temp;

    private Double temp_min;

    private Integer grnd_level;

    private Integer humidity;

    private Integer pressure;

    private Integer sea_level;

    private Double feels_like;

    private Double temp_max;

    public Double getTemp() {
        return this.temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getTemp_min() {
        return this.temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Integer getGrnd_level() {
        return this.grnd_level;
    }

    public void setGrnd_level(Integer grnd_level) {
        this.grnd_level = grnd_level;
    }

    public Integer getHumidity() {
        return this.humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getPressure() {
        return this.pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getSea_level() {
        return this.sea_level;
    }

    public void setSea_level(Integer sea_level) {
        this.sea_level = sea_level;
    }

    public Double getFeels_like() {
        return this.feels_like;
    }

    public void setFeels_like(Double feels_like) {
        this.feels_like = feels_like;
    }

    public Double getTemp_max() {
        return this.temp_max;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }
}
