package com.filipesilvestre.beerme.db;

import com.filipesilvestre.beerme.core.Beer;
import com.filipesilvestre.beerme.core.Brewery;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.*;

public class BreweryDAO extends AbstractDAO<Brewery> {

    /**
     * Initialize BreweryDAO object
     * @param sessionFactory the hibernate session factory
     */
    public BreweryDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Find a brewery based on id
     * @param id
     * @return Brewery object
     */
    public Optional<Brewery> findById(int id) {
        return Optional.ofNullable(currentSession().get(Brewery.class, id));
    }

    /**
     * Create a brewery
     * @param brewery
     * @return Brewery object
     */
    public Brewery create(Brewery brewery) {
        return persist(brewery);
    }

    /**
     * Get a list of all the breweries
     * @return Brewery list
     */
    public List<Brewery> findAll() {
        return list(namedQuery("findAllBrewerys"));
    }

    /**
     * Remove a brewery based on its id
     * @param id
     * @return String
     */
    public String removeById(int id) {
        // for future I would absolutely return a JSON message indicating user was deleted
        // this isn't good practice, but it's quick

        // get brewery entity in db then pass it in the remove
        Optional<Brewery> breweryToRemove = Optional.ofNullable(currentSession().get(Brewery.class, id));
        if (breweryToRemove.isPresent()) {
            currentSession().remove(breweryToRemove.get());
            return String.format("Removed %s", breweryToRemove.get().getBreweryName());
        } else {
            return "Brewery not found";
        }
    }

    /**
     * Add a beer to a brewery
     * @param id the brewery id
     * @param beer the beer object from json
     * @return Brewery object
     */
    public Brewery addBeer(int id, Beer beer) {
        Brewery brewery = currentSession().get(Brewery.class, id);
        brewery.getBeerList().add(beer);
        currentSession().update(brewery);
        return brewery;
    }
}
