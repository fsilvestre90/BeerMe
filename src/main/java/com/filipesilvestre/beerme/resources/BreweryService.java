package com.filipesilvestre.beerme.resources;

import javax.ws.rs.Path;

@Path("/brewery")
public class BreweryService {
    public BreweryService() {
    }
//
//    @GET
//    @Timed
//    @Path("/taplist/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Beer> getBeersFromBrewery(@PathParam("id") int id) {
//        return BreweryDAO.getBreweryBeers(id);
//    }
//
//    @GET
//    @Timed
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Brewery getBreweryInfo(@PathParam("id") int id) {
//        return BreweryDAO.getBreweryInfo(id);
//    }
//
//    @GET
//    @Timed
//    @Path("/all")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Brewery> getBrewerys() {
//        return BreweryDAO.getAll();
//    }
//
//    @POST
//    @Timed
//    @Path("/addBeer/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes({MediaType.APPLICATION_JSON})
//    public Brewery addBeer(@PathParam("id") int id, Beer beer) {
//        return BreweryDAO.addBeerToBrewery(id, beer);
//    }
}
