package com.filipesilvestre.beerme.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Brewery {
    private int id;
    private String breweryName;

    public Brewery() {
        // Needed by Jackson deserialization
    }

    public Brewery(int id, String breweryName) {
        this.id = id;
        this.breweryName = breweryName;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public String getBreweryName() {
        return breweryName;
    }

}
