package com.filipesilvestre.beerme.db;


import com.filipesilvestre.beerme.core.Beer;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class BeerDAO extends AbstractDAO<Beer> {

    public BeerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Beer> findById(int id) {
        return Optional.ofNullable(currentSession().get(Beer.class, id));
    }

    public List<Beer> filterByStyle(String query) {
        return list(namedQuery("filterByStyle")
                .setParameter("style", "%" + query + "%"));
    }

    public Beer create(Beer beer) {
        return persist(beer);
    }

    public List<Beer> findAll() {
        return list(namedQuery("findAll"));
    }

    // for future I would absolutely return a JSON message indicating user was deleted
    // this isn't good, but it's quick
    public String removeBeerById(int id) {
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