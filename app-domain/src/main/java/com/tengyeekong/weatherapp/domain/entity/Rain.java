package com.tengyeekong.weatherapp.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rain implements Serializable {
    @SerializedName("3h")
    private Double threeH;

    public Double getThreeH() {
        return this.threeH;
    }

    public void setThreeH(Double threeH) {
        this.threeH = threeH;
    }
}
