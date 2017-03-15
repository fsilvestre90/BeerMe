package com.filipesilvestre.beerme.health;

import com.codahale.metrics.health.HealthCheck;
import com.filipesilvestre.beerme.db.BeerDB;

public class BeerMeCheck extends HealthCheck {
    private final String version;

    public BeerMeCheck(String version) {
        this.version = version;
    }

    @Override
    protected Result check() throws Exception {
        if (BeerDB.getCount() == 0) {
            return Result.unhealthy("ISSUE: No beers! Version: " +
                    this.version);
        }
        return Result.healthy("OK with version: " + this.version +
                ". Beers count: " + BeerDB.getCount());
    }
}