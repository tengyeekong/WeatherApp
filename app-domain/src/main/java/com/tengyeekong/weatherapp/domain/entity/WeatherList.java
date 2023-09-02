package com.tengyeekong.weatherapp.domain.entity;

import java.io.Serializable;
import java.util.List;

public class WeatherList implements Serializable {
    private Integer dt;

    private Double pop;

    private Rain rain;

    private Integer visibility;

    private String dt_txt;

    private List<Weather> weather;

    private Main main;

    private Clouds clouds;

    private Sys sys;

    private Wind wind;

    public Integer getDt() {
        return this.dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Double getPop() {
        return this.pop;
    }

    public void setPop(Double pop) {
        this.pop = pop;
    }

    public Rain getRain() {
        return this.rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Integer getVisibility() {
        return this.visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public String getDt_txt() {
        return this.dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public List<Weather> getWeather() {
        return this.weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
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

    public Wind getWind() {
        return this.wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public static class Sys implements Serializable {
        private String pod;

        public String getPod() {
            return this.pod;
        }

        public void setPod(String pod) {
            this.pod = pod;
        }
    }
}
