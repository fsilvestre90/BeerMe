package com.filipesilvestre.beerme.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Brewery {
    private int id;
    private String breweryName;
    private List<Beer> beerList;

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

    @JsonProperty
    public List<Beer> getBeerList() {
        return beerList;
    }

    public void setBeerList(List<Beer> beerList) {
        this.beerList = beerList;
    }
}
