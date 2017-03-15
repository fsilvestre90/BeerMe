package com.filipesilvestre.beerme.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Beer {
    private int id;
    private String beerName;
    private String style;
    private int ibu;
    private double alcPercent;

    public Beer() {
        // Needed by Jackson deserialization
    }

    public Beer(int id, String beerName, String style, int ibu, double alcPercent) {
        this.id = id;
        this.beerName = beerName;
        this.style = style;
        this.ibu = ibu;
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
        return style;
    }

    @JsonProperty
    public int getIbus() {
        return ibu;
    }

    @JsonProperty
    public double getAlcPercent() {
        return alcPercent;
    }

}

