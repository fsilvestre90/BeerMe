package com.filipesilvestre.beerme.db;


import com.filipesilvestre.beerme.core.Beer;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class BeerDAO extends AbstractDAO<Beer> {

    /**
     * Initialize BeerDAO object
     * @param sessionFactory the hibernate session factory
     */
    public BeerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Find an individual beer based on its ID
     * @param id
     * @return Beer Object
     */
    public Optional<Beer> findById(int id) {
        return Optional.ofNullable(currentSession().get(Beer.class, id));
    }

    /**
     * Filters beers based on their brewing style
     * @param query Choose a type of beer to filter, e.g. "IPA"
     * @return Beer object
     */
    public List<Beer> filterByStyle(String query) {
        return list(namedQuery("filterByStyle")
                .setParameter("style", "%" + query + "%"));
    }

    /**
     * Create a beer
     * @param Beer the beer object from JSON
     * @return Beer Object
     */
    public Beer create(Beer beer) {
        return persist(beer);
    }

    /**
     * Get a list of all the beers
     * @return Beer List Object
     */
    public List<Beer> findAll() {
        return list(namedQuery("findAllBeers"));
    }

    /**
     * Remove a beer
     * @param id
     * @return String
     */
    public String removeBeerById(int id) {
        // for future I would absolutely return a JSON message indicating user was deleted
        // this isn't good, but it's quick

        // get beer entity in db then pass it in the remove
        Optional<Beer> beerToRemove = Optional.ofNullable(currentSession().get(Beer.class, id));
        if (beerToRemove.isPresent()) {
            currentSession().remove(beerToRemove.get());
            return String.format("Removed %s", beerToRemove.get().getBeerName());
        } else {
            return "Beer not found";
        }
    }

}