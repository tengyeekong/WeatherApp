package com.tengyeekong.weatherapp.domain.entity;

import java.io.Serializable;

public class City implements Serializable {
    private String country;

    private Object coord;

    private Integer sunrise;

    private Integer timezone;

    private Integer sunset;

    private String name;

    private Integer id;

    private Integer population;

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Object getCoord() {
        return this.coord;
    }

    public void setCoord(Object coord) {
        this.coord = coord;
    }

    public Integer getSunrise() {
        return this.sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getTimezone() {
        return this.timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getSunset() {
        return this.sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPopulation() {
        return this.population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
