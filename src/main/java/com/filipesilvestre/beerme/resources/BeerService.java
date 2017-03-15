package com.filipesilvestre.beerme.resources;

import com.codahale.metrics.annotation.Timed;
import com.filipesilvestre.beerme.core.Beer;
import com.filipesilvestre.beerme.db.BeerDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/beer")
public class BeerService {
    private final BeerDAO beerDAO;

    public BeerService(BeerDAO beerDAO) {
        this.beerDAO = beerDAO;
    }

    @GET
    @Timed
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Optional<Beer> getBeerById(@PathParam("id") int id) {
        return beerDAO.findById(id);
    }

    @GET
    @Timed
    @Path("/remove")
    @Produces(MediaType.TEXT_PLAIN)
    @UnitOfWork
    public String removeBeer(@PathParam("id") int id) {
        return beerDAO.removeBeerById(id);
    }

    @GET
    @Timed
    @Path("/style")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Beer> getBeerByStyle(@QueryParam("q") String query) {
        return beerDAO.filterByStyle(query);
    }

    @GET
    @Timed
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Beer> getBeers() {
        return beerDAO.findAll();
    }

    @POST
    @Timed
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    @UnitOfWork
    public Beer addBeer(Beer beer) {
        return beerDAO.create(beer);
    }
}