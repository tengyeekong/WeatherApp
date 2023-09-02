package com.tengyeekong.weatherapp.domain.entity;

import java.io.Serializable;

public class Coord implements Serializable {
    private Integer lon;

    private Integer lat;

    public Integer getLon() {
        return this.lon;
    }

    public void setLon(Integer lon) {
        this.lon = lon;
    }

    public Integer getLat() {
        return this.lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }
}
