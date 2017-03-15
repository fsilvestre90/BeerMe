package com.filipesilvestre.beerme.resources;

import com.codahale.metrics.annotation.Timed;
import com.filipesilvestre.beerme.core.Beer;
import com.filipesilvestre.beerme.core.Brewery;
import com.filipesilvestre.beerme.db.BreweryDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/brewery")
public class BreweryService {
    private final BreweryDAO breweryDAO;

    public BreweryService(BreweryDAO breweryDAO) {
        this.breweryDAO = breweryDAO;
    }

    @GET
    @Timed
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Optional<Brewery> getBreweryInfo(@PathParam("id") int id) {
        return breweryDAO.findById(id);
    }

    @GET
    @Timed
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Brewery> getBrewerys() {
        return breweryDAO.findAll();
    }

    @POST
    @Timed
    @Path("/addBeer/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    @UnitOfWork
    public Brewery addBeer(@PathParam("id") int id, Beer beer) {
        return breweryDAO.addBeer(id, beer);
    }
}
