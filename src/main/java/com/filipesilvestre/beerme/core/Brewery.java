package com.filipesilvestre.beerme.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brewery")
@NamedQueries(
        {
                @NamedQuery(
                        name = "findAllBrewerys",
                        query = "SELECT p FROM Brewery p"
                )
        }
)
public class Brewery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brewery_name", nullable = false)
    private String breweryName;

    // one-to-many table so we can get a list of beers associated to brewery
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "BREWERY_BEERS", joinColumns = { @JoinColumn(name = "BREWERY_ID") }, inverseJoinColumns = { @JoinColumn(name = "BEER_ID") })
    private List<Beer> beerList;

    public Brewery() {
        // Needed by Jackson deserialization
    }

    public Brewery(String breweryName) {
        this.breweryName = breweryName;
    }


    @JsonProperty
    public int getId() {
        return id;
    }

    public Brewery setId(int id) {
        this.id = id;
        return this;
    }

    @JsonProperty
    public String getBreweryName() {
        return breweryName;
    }

    public Brewery setBreweryName(String name) {
        this.breweryName = name;
        return this;
    }

    @JsonProperty
    public List<Beer> getBeerList() {
        return beerList;
    }

    public Brewery setBeerList(ArrayList<Beer> beerList) {
        this.beerList = beerList;
        return this;
    }
}
