package com.tengyeekong.weatherapp.domain.entity;

import java.io.Serializable;

public class Coord implements Serializable {
    private double lon;

    private double lat;

    public double getLon() {
        return this.lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
