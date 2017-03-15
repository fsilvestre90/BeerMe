package com.filipesilvestre.beerme.resources;

import com.codahale.metrics.annotation.Timed;
import com.filipesilvestre.beerme.core.Beer;
import com.filipesilvestre.beerme.db.BeerDB;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/beer")
public class BeerService {

    public BeerService() {}

    @GET
    @Timed
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Beer getBeerById(@PathParam("id") int id) {
        return BeerDB.getById(id);
    }

    @GET
    @Timed
    @Path("/remove")
    @Produces(MediaType.TEXT_PLAIN)
    public String removeBeer() {
        BeerDB.remove();
        return "Last Beer remove. Total count: " + BeerDB.getCount();
    }

    @GET
    @Timed
    @Path("/style")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Beer> getBeerByStyle(@QueryParam("q") String query) {
        List<Beer> matchingBeers = new ArrayList<>();
        //filter the beers depending on the ?style= from the url
        List<Beer> beers = BeerDB.getAll();
        for (Beer beer : beers) {
            if (beer.getBeerStyle().toLowerCase().contains(query.toLowerCase())) {
                matchingBeers.add(beer);
            }
        }
        return matchingBeers;
    }

    @GET
    @Timed
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Beer> getBeers() {
        return BeerDB.getAll();
    }

    @POST
    @Timed
    @Path("/save")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.APPLICATION_JSON})
    public String addBeer(Beer beer) {
        return BeerDB.save(beer);
    }
}