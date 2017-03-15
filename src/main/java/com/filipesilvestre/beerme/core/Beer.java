package com.filipesilvestre.beerme.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "beer")
@NamedQueries(
        {
                @NamedQuery(
                        name = "com.filipesilvestre.beerme.core.Beer.findAll",
                        query = "SELECT p FROM Beer p"
                )
        }
)
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "beerName", nullable = false)
    private String beerName;
    
    @Column(name = "beerStyle", nullable = false)
    private String beerStyle;
    
    @Column(name = "ibu", nullable = false)
    private int ibu;

    @Column(name = "alcPercent", nullable = false)
    private double alcPercent;

    public Beer() {
        // Needed by Jackson deserialization
    }

    public Beer(int id, String beerName, String beerStyle, int ibu, double alcPercent) {
        this.id = id;
        this.beerName = beerName;
        this.beerStyle = beerStyle;
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
        return beerStyle;
    }

    @JsonProperty
    public int getibu() {
        return ibu;
    }

    @JsonProperty
    public double getAlcPercent() {
        return alcPercent;
    }

}

