package com.filipesilvestre.beerme.db;

import com.filipesilvestre.beerme.core.Beer;
import com.filipesilvestre.beerme.core.Brewery;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.*;

public class BreweryDAO extends AbstractDAO<Brewery> {

    public BreweryDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Brewery> findById(int id) {
        return Optional.ofNullable(currentSession().get(Brewery.class, id));
    }

    public Brewery create(Brewery brewery) {
        return persist(brewery);
    }

    public List<Brewery> findAll() {
        return list(namedQuery("findAll"));
    }

    // for future I would absolutely return a JSON message indicating user was deleted
    // this isn't good, but it's quick
    public String removeById(int id) {
        // get brewery entity in db then pass it in the remove
        Optional<Brewery> breweryToRemove = Optional.ofNullable(currentSession().get(Brewery.class, id));
        if (breweryToRemove.isPresent()) {
            currentSession().remove(breweryToRemove.get());
            return String.format("Removed %s", breweryToRemove.get().getBreweryName());
        } else {
            return "Brewery not found";
        }
    }

    public Brewery addBeer(int id, Beer beer) {
        return new Brewery();
    }
}
