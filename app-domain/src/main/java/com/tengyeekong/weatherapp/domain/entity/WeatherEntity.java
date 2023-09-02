package com.tengyeekong.weatherapp.domain.entity;

import java.io.Serializable;
import java.util.List;

public class WeatherEntity implements Serializable {
    private Integer visibility;

    private Integer timezone;

    private Main main;

    private Clouds clouds;

    private Sys sys;

    private Integer dt;

    private Coord coord;

    private List<Weather> weather;

    private String name;

    private Integer cod;

    private Integer id;

    private String base;

    private Wind wind;

    public Integer getVisibility() {
        return this.visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Integer getTimezone() {
        return this.timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Main getMain() {
        return this.main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Clouds getClouds() {
        return this.clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Sys getSys() {
        return this.sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getDt() {
        return this.dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Coord getCoord() {
        return this.coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return this.weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return this.cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBase() {
        return this.base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Wind getWind() {
        return this.wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public static class Sys implements Serializable {
        private Integer sunrise;

        private Integer sunset;

        public Integer getSunrise() {
            return this.sunrise;
        }

        public void setSunrise(Integer sunrise) {
            this.sunrise = sunrise;
        }

        public Integer getSunset() {
            return this.sunset;
        }

        public void setSunset(Integer sunset) {
            this.sunset = sunset;
        }
    }
}
