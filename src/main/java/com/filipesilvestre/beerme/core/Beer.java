package com.filipesilvestre.beerme.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "beer")
@NamedQueries(
        {
                @NamedQuery(
                        name = "findAll",
                        query = "SELECT p FROM Beer p"
                ),
                @NamedQuery(
                        name = "filterByStyle",
                        query = "select p from Beer p where beer_style like :style"
                )
        }
)
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "beer_name", nullable = false)
    private String beerName;
    @Column(name = "beer_style")
    private String beerStyle;
    @Column(name = "ibu")
    private int ibu;
    @Column(name = "alc_percent")
    private double alcPercent;

    public Beer() {
        // Needed by Jackson deserialization
    }

    public Beer(String beerName, String beerStyle, int ibu, double alcPercent) {
        this.beerName = beerName;
        this.beerStyle = beerStyle;
        this.ibu = ibu;
        this.alcPercent = alcPercent;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    public Beer setId(int id) {
        this.id = id;
        return this;
    }

    @JsonProperty
    public String getBeerName() {
        return beerName;
    }

    public Beer setBeerName(String beerName) {
        this.beerName = beerName;
        return this;
    }

    @JsonProperty
    public String getBeerStyle() {
        return beerStyle;
    }

    public Beer setBeerStyle(String style) {
        this.beerStyle = style;
        return this;
    }

    @JsonProperty
    public int getIbu() {
        return ibu;
    }

    public Beer setIbu(int ibu) {
        this.ibu = ibu;
        return this;
    }

    @JsonProperty
    public double getAlcPercent() {
        return alcPercent;
    }

    public Beer setBeerAlcPercent(double alcPercent) {
        this.alcPercent = alcPercent;
        return this;
    }

}

