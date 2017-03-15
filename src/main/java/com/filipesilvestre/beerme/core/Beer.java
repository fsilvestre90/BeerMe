package com.filipesilvestre.beerme.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Beer {
    private int id;
    private String beerName;
    private String beerStyle;
    private int ibus;
    private double alcPercent;

    public Beer() {
        // Needed by Jackson deserialization
    }

    public Beer(int id, String beerName, String beerStyle, int ibus, double alcPercent) {
        this.id = id;
        this.beerName = beerName;
        this.beerStyle = beerStyle;
        this.ibus = ibus;
        this.alcPercent = alcPercent;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public String getBeerName() {
        return beerName;
    }

    @JsonProperty
    public String getBeerStyle() {
        return beerStyle;
    }

    @JsonProperty
    public int getIbus() {
        return ibus;
    }

    @JsonProperty
    public double getAlcPercent() {
        return alcPercent;
    }

}

