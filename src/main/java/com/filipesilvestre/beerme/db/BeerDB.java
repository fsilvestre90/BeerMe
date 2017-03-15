package com.filipesilvestre.beerme.db;


import com.filipesilvestre.beerme.core.Beer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * If this was connected to a DB, then this would be where business logic is located
 */

public class BeerDB {
    private static Map<Integer, Beer> beers = new HashMap<>(); // create our fake beers

    static {
        beers.put(1, new Beer(1, "Pliny the Elder", "American Double", 50, 8.00));
        beers.put(2, new Beer(2, "White Lightning", "Whitbier", 18, 6.00));
        beers.put(3, new Beer(3, "Allagash White", "Whitbier", 16, 5.01));
    }

    public static Beer getById(int id) {
        return beers.get(id);
    }

    public static List<Beer> getAll() {
        List<Beer> result = new ArrayList<>();
        for (Integer key : beers.keySet()) {
            result.add(beers.get(key));
        }
        return result;
    }

    public static int getCount() {
        return beers.size();
    }

    public static void remove() {
        if (!beers.keySet().isEmpty()) {
            beers.remove(beers.keySet().toArray()[0]);
        }
    }

    public static String save(Beer Beer) {
        String result;
        if (beers.get(Beer.getId()) != null) {
            result = "Updated Beer with id=" + Beer.getId();
        } else {
            result = "Added Beer with id=" + Beer.getId();
        }
        beers.put(Beer.getId(), Beer);
        return result;
    }
}