package com.filipesilvestre.beerme.db;

import com.filipesilvestre.beerme.core.Brewery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by filipesilvestre on 3/14/17.
 */
public class BreweryDAO {
    private static Map<Integer, Brewery> breweries = new HashMap<>(); // create our fake breweries

    static {
        breweries.put(1, new Brewery(1, "Allagash"));
        breweries.put(2, new Brewery(2, "No Use For A Name"));
        breweries.put(3, new Brewery(3, "Fine Elegant Beers"));
        breweries.put(4, new Brewery(4, "Garbage Beer Brewing"));
    }

    public static Brewery getById(int id) {
        return breweries.get(id);
    }

    // if I had a DB this would be done via SQL joins
    public static List<Brewery> getAll() {
        List<Brewery> result = new ArrayList<>();
        for (Integer key : breweries.keySet()) {
            Brewery brewery = breweries.get(key);
            result.add(brewery);
        }
        return result;
    }

//    public static List<Beer> getBreweryBeers(int id) {
//
//        return BeerDB.getBeerByBreweryId(id);
//    }
//
//    public static Brewery getBreweryInfo(int id) {
//        Brewery brewery = breweries.get(id);
//        if (brewery.getBeerList().isEmpty()) {
//            brewery.setBeerList(BeerDB.getBeerByBreweryId(id));
//        }
//        return breweries.get(id);
//    }
//
//    /**
//     * If I used a DB then there would be an update query here
//     * */
//    public static Brewery addBeerToBrewery(int id, Beer newBeer) {
//        if (breweries.get(id) != null) {
//            newBeer.setBreweryId(id);
//            BeerDB.save(newBeer);
//        }
//        return breweries.get(id);
//    }

}
