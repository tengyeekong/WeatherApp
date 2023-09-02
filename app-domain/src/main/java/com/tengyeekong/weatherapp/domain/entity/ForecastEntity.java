package com.tengyeekong.weatherapp.domain.entity;

import java.io.Serializable;
import java.util.List;

public class ForecastEntity implements Serializable {
    private City city;

    private Integer cnt;

    private String cod;

    private Integer message;

    private List<WeatherList> list;

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getCnt() {
        return this.cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getCod() {
        return this.cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Integer getMessage() {
        return this.message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public List<WeatherList> getList() {
        return this.list;
    }

    public void setList(List<WeatherList> list) {
        this.list = list;
    }
}
