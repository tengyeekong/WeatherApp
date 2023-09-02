package com.tengyeekong.weatherapp.domain.entity;

import java.io.Serializable;

public class Wind implements Serializable {
    private Integer deg;

    private Double speed;

    private Double gust;

    public Integer getDeg() {
        return this.deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Double getSpeed() {
        return this.speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getGust() {
        return this.gust;
    }

    public void setGust(Double gust) {
        this.gust = gust;
    }
}
